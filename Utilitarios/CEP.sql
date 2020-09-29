

-- -----------------------------------------------------
-- Table `Cep`.`Inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirCep;

delimiter #
create procedure sp_inserirCep(p_cepcodigo varchar(45), p_ceplat varchar(45), p_ceplongi varchar(45),p_cepnomeprop varchar(45), p_cepcomcodigo int(11), p_ceptprcodigo int(11))
	begin
		insert into cep values(cepidcodigo, p_cepcodigo, p_ceplat, p_ceplongi,p_cepnomeprop, p_cepcomcodigo, p_ceptprcodigo);
    
    end#

delimiter ;

call sp_inserirCep('09210001','-3.1342618','-60.0128036','IFAM_CMC',1,1);
call sp_inserirCep('09210002','-3.1342612','-60.0478577','IFAM_CDI',1,1);
call sp_inserirCep('09210003','-3.0787106','-59.967926','IFAM_CMZL',1,1);

call sp_inserirCep('09210004','-3.339666667','-60.57413889', 'Evandro', 2,1);
call sp_inserirCep('09210005','-3.339944444','-60.57647222','José Vitor',2,1);
call sp_inserirCep('09210006','-3.340083333','-60.57644444','Rosa',3,1);
call sp_inserirCep('09210007','-3.340527778','-60.58022222','Valdecir',3,1);


-- -----------------------------------------------------
-- Table `Cep`.`Pesquisar`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarCEP;

delimiter #
create procedure sp_pesquisarCEP(p_cep varchar(45))
	begin
		select cepcodigo, ceplat, ceplongi, cepnomeprop, 
			comnome, tprtipo, estnome 
			from cep
			inner join comunidade on comcodigo = cepcomcodigo
			inner join estado on estcodigo = comestcodigo
			inner join tipopropriedade on tprcodigo = ceptprcodigo
            where cepcodigo like concat('%',p_cep,'%');
    end#
delimiter ;


-- -----------------------------------------------------
-- Table `Cep`.`Listar`
-- -----------------------------------------------------

drop procedure if exists sp_listarCEP;
delimiter #
create procedure sp_listarCEP(p_cep varchar(45))
	begin
		select cepcodigo, ceplat, ceplongi, cepnomeprop, 
			comnome, tprtipo, estnome 
			from cep
			inner join comunidade on comcodigo = cepcomcodigo
			inner join estado on estcodigo = comestcodigo
			inner join tipopropriedade on tprcodigo = ceptprcodigo
            where cepcodigo like concat('%',p_cep,'%');
		
    end#
delimiter ;

-- call sp_listarCEP('09210001');


-- -----------------------------------------------------
-- Table `Cep`.`Excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirCep;

delimiter #
create procedure sp_excluirCep(p_cepidcodigo int(11))
	begin 
		delete 
			from cep
            where cepidcodigo=p_cepidcodigo;
    end #
delimiter ;



-- -----------------------------------------------------
-- Table `Cep`.`Pesquisa CEP`
-- -----------------------------------------------------

drop procedure sp_consultarCEP;   
delimiter $$
create procedure sp_consultarCEP(
					p_cep varchar(45)
                ) 
	begin
    
		declare v_existeec boolean;
        set v_existeec = false;
		
        set v_existeec = (select count(*)
							from cep
							where cepcodigo = p_cep);
        if v_existeec then
			select cepcodigo 'Codigo', ceplat 'Latitude', ceplongi 'Longitude', cepnomeprop 'Nome Proprietario', 
			comnome 'Comunidade', tprtipo 'Tipo Propriedade', estnome 'Estado' 
			from cep
			inner join comunidade on comcodigo = cepcomcodigo
			inner join estado on estcodigo = estado_estcodigo
			inner join tipopropriedade on tprcodigo = ceptprcodigo
            where cepcodigo = p_cep;
		else 
			select 'CEP nao existe!!!' as reposta;
		end if;
	end$$
delimiter ;
-- call sp_consultarCEP('09210001');

-- -----------------------------------------------------
-- Table `Cep`.`Lista CEP`
-- -----------------------------------------------------
delimiter $$
	create procedure listaCEP()
	begin
		declare v_existeec boolean;
        set v_existeec = false;
		
        set v_existeec = (select count(*)
							from cep);
        if v_existeec then
			select cepcodigo 'Codigo', ceplat 'Latitude', ceplongi 'Longitude', cepnomeprop 'Nome Proprietario', 
			comnome 'Comunidade', tprtipo 'Tipo Propriedade', estnome 'Estado'
			from cep
			inner join comunidade on comcodigo = cepcomcodigo
			inner join estado on estcodigo = estado_estcodigo
			inner join tipoprop on tprcodigo = ceptprcodigo;
		else 
			select 'Nenhum CEP no Banco!!!' as reposta;
		end if;
    end$$
delimiter ;