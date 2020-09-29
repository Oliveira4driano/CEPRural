-- -----------------------------------------------------
-- Table `Comunidade`.`inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirComunidade;

delimiter #
create procedure sp_inserirComunidade(p_comnome varchar(45), p_comqtdprop int(11), p_comestcodigo int(11))
	begin
		insert into comunidade 
			values (comcodigo, p_comnome, p_comqtdprop, p_comestcodigo);
    
    end #
delimiter ;

call sp_inserirComunidade('Manaus',100,1);
call sp_inserirComunidade('comunidade Esperenaça',7,1);
call sp_inserirComunidade('Vila Batista',10,1);
call sp_inserirComunidade('comunidade Acre',50,2);

-- select *from comunidade;

-- alter table comunidade change comidcodigo comcodigo int(11) auto_increment;

-- alter table comunidade change estestcodigo comestcodigo int(11) ;


-- -----------------------------------------------------
-- Table `Comunidade`.`Listar`
-- -----------------------------------------------------

DROP PROCEDURE IF EXISTS sp_listarComunidade;

delimiter #
create procedure sp_listarComunidade()
	begin
		select comcodigo, comnome 
			from comunidade
            order by comnome;
    end #
delimiter ;

call sp_listarComunidade();

 
-- -----------------------------------------------------
-- Table `Comunidade`.`Alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarComunidade;
delimiter #
create procedure sp_alterarComunidade(p_comcodigo int(11), p_comnome varchar(45), p_comqtdprop int(11), p_comestcodigo int(11))
	begin
		update comunidade 
			set comnome= p_comnome,comqtdprop= p_comqtdprop, comestcodigo=p_comestcodigo
            where comcodigo = p_comcodigo;
    end #

delimiter ;

-- call sp_alterarComunidade(1,'Vila',5,1);


-- -----------------------------------------------------
-- Table `Comunidade`.`excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirComunidade;

delimiter #
create procedure sp_excluirComunidade(p_comcodigo int(11))
	begin 
		delete 
			from comunidade
            where comcodigo=p_comcodigo;
    end #
delimiter ;

-- call sp_excluirComunidade(0);




-- -----------------------------------------------------
-- Table `Comunidade`.`Pesquisar`
-- -----------------------------------------------------
drop procedure if exists sp_pesquisarComunidade;

delimiter #
create procedure sp_pesquisarComunidade(p_comnome varchar(45))
	begin
		select comcodigo, comnome, comqtdprop, estsigla
        from comunidade
        inner join estado on estcodigo=comestcodigo
        where comnome like concat('%',p_comnome,'%');
    end #

delimiter ;


-- -----------------------------------------------------
-- Table `Comunidade`.`Buscar por id`
-- -----------------------------------------------------
drop procedure if exists sp_buscarIdComunidade;
delimiter #
create procedure sp_buscarIdComunidade(p_comcodigo int(11))
	begin
		select comcodigo, comnome
			from comunidade
			where comcodigo =p_comcodigo;
	end #
delimiter ;

-- call sp_buscarIdComunidade(2);

-- call sp_pesquisarComunidade('');

-- -----------------------------------------------------
-- Table `Comunidade`.`filtrarComunidade`
-- -----------------------------------------------------

delimiter $$
create procedure sp_filtarComunidade(
					p_comunidade varchar(45)
                )
	begin
    
		declare v_existecm boolean;
        set v_existecm = false;
		
        set v_existecm = (select count(*)
							from comunidade
							where comnome = p_comunidade);
        if v_existecm then
			select comcodigo 'Codigo', comnome 'Comunidade', comqtdprop 'Quantidade Propriedade', estnome 'Estado'
			from comunidade
			inner join estado on estcodigo = estado_estcodigo
            where comnome = p_comunidade;
		else 
			select 'Comunidade nao existe!!!' as reposta;
		end if;
	end$$
delimiter ;

-- call sp_filtarComunidade('Nossa Senhora das Gracas');