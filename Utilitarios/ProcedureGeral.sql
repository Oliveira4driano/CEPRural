use cep_rural;

/* comando necessrio
-- alter table comunidade change comidcodigo comcodigo int(11) auto_increment
-- rename table tipoprop to tipopropriedade;*/


-- -----------------------------------------------------
-- Table `estado`.`inserir`
-- -----------------------------------------------------

drop procedure if exists sp_inserirEstado;
delimiter #
create procedure sp_inserirEstado( p_estnome varchar(45), p_estsigla varchar(2) )
	begin 
		insert into estado values( estcodigo, p_estnome, p_estsigla); 
    
    end #

delimiter ;

call sp_inserirEstado('Amazonas','AM');
call sp_inserirEstado('Acre','AC');
call sp_inserirEstado('São Paulo','SP');


-- -----------------------------------------------------
-- Table `estado`.`alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarEstado;

delimiter #
create procedure sp_alterarEstado( p_estcodigo int(11), p_estnome varchar(45), p_estsigla varchar(2))
	begin
		update estado
        set estnome = p_estnome, estsigla = p_estsigla
        where estcodigo =p_estcodigo;
    end #
    
delimiter ;

-- call sp_alterarEstado(1,'Amazonas','AM');

-- -----------------------------------------------------
-- Table `estado`.`listar`
-- -----------------------------------------------------
drop procedure if exists sp_listarEstado;
delimiter #
create procedure sp_listarEstado()
	begin
		select  estcodigo, estsigla
			from estado;
    end #
delimiter ;


call sp_listarEstado();

-- -----------------------------------------------------
-- Table `estado`.`buscar por id`
-- -----------------------------------------------------
drop procedure if exists sp_buscarIdEstado;
delimiter #
create procedure sp_buscarIdEstado(p_estcodigo int(11))
	begin
		select estcodigo, estnome, estsigla
			from estado
            where estcodigo = p_estcodigo;
    end #

delimiter ;

call sp_buscarIdEstado(1);
-- -----------------------------------------------------
-- Table `estado`.`excluir`
-- -----------------------------------------------------

drop procedure if exists sp_excluirEstado;

delimiter #
create procedure sp_excluirEstado(p_estcodigo int(11))
	begin 
		delete 
			from estado
			where estcodigo = p_estcodigo;
    end #

delimiter ;
-- call sp_excluirEstado(1);


-- -----------------------------------------------------
-- Table `estado`.`pesquisar`
-- -----------------------------------------------------

drop procedure if exists sp_pesquisarEstado;

delimiter #
create procedure sp_pesquisarEstado(p_estnome varchar(45))
	begin 
		select estcodigo, estnome, estsigla
        from estado
        where estnome like concat('%', p_estnome,'%'); 
    end #
    
delimiter ;

-- call sp_pesquisarEstado('Amazonas');

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




-- -----------------------------------------------------
-- Table `TipoPropriedade`.`inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirTipo;
delimiter #
create procedure sp_inserirTipo(p_tprtipo varchar(45))
	begin
		insert into tipopropriedade 
			values (tprcodigo, p_tprtipo);
    end#

delimiter ;

call sp_inserirTipo('Casa Alvenaria');
call sp_inserirTipo('Casa Madeira');
call sp_inserirTipo('Casa Mista');


-- -----------------------------------------------------
-- Table `TipoPropriedade`.`alterar`
-- -----------------------------------------------------

drop procedure if exists sp_alterarTipo;
delimiter #
create procedure sp_alterarTipo(p_tprcodigo int(11), p_tprtipo varchar(45))
	begin
		update tipopropriedade
        set tprtipo = p_tprtipo
        where tprcodigo =p_tprcodigo;
    end #
delimiter ;

-- call sp_alterarTipo(1,'casa Mista');

-- -----------------------------------------------------
-- Table `TipoPropriedade`.`excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirTipo;
delimiter #
create procedure sp_excluirTipo(p_tprcodigo int(11))
	begin
		delete 
			from tipopropriedade
			where tprcodigo = p_tprcodigo;
	end #

delimiter ;

-- call sp_excluirTipo(2);

-- -----------------------------------------------------
-- Table `TipoPropriedade`.`pesquisar`
-- -----------------------------------------------------
drop procedure if exists sp_pesquisarTipo;

delimiter #
create procedure sp_pesquisarTipo(p_tprtipo varchar(45))
	begin
		select tprcodigo, tprtipo
			from tipopropriedade
            where tprtipo like concat('%',p_tprtipo,'%');
    end#

delimiter ;

-- call sp_pesquisarTipo('ca');

-- -----------------------------------------------------
-- Table `TipoPropriedade`.`ListarTipo`
-- -----------------------------------------------------
drop procedure if exists sp_listarTipo;

delimiter #
create procedure sp_listarTipo()
	begin
		select tprcodigo, tprtipo
			from tipopropriedade;
            
    end#

delimiter ;

call sp_listarTipo;

-- -----------------------------------------------------
-- Table `Grupo`.`Inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirGrupo;

delimiter #
create procedure sp_inserirGrupo(p_grudescricao varchar(45), p_grupercodigo int(11))
	begin
		insert into grupo values(grucodigo, p_grudescricao, p_grupercodigo);
	end #
delimiter ;

call sp_inserirGrupo('Administrador',1);
call sp_inserirGrupo('Usuario',2);

-- -----------------------------------------------------
-- Table `Grupo`.`Alterar`
-- -----------------------------------------------------
drop procedure if exists sp_alterarGrupo;
delimiter #
 create procedure sp_alterarGrupo(p_grudescricao varchar(45), p_grupercodigo int(11), p_grucodigo int(11))
	 begin
		update grupo 
		set grudescricao = p_grudescricao, grupercodigo = p_grupercodigo 
		where grucodigo=p_grucodigo;
	 
	 end #

delimiter ;

-- call sp_alterarGrupo('adm',1,1);

-- -----------------------------------------------------
-- Table `Grupo`.`Listar`
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS sp_listarGrupo;
DELIMITER #
CREATE PROCEDURE sp_listarGrupo()
	begin
		select grucodigo, grudescricao, perstatus
			from grupo
            inner join permissao on percodigo=grupercodigo;
    end #

DELIMITER ;

-- call sp_listarGrupo;


-- -----------------------------------------------------
-- Table `Grupo`.`Excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirGrupo;
delimiter #
	create procedure sp_excluirGrupo(p_grucodigo int(11))
    begin 
		delete 
        from grupo 
        where p_grucodigo =grucodigo;
    end #

delimiter ;

-- call sp_excluirGrupo(10);


-- -----------------------------------------------------
-- Table `Grupo`.`Pesquisar`
-- -----------------------------------------------------
drop procedure if exists sp_pesquisarGrupo;
delimiter #
	create procedure sp_pesquisarGrupo(p_grudescricao varchar(45))
		begin	
			select grucodigo, grudescricao, perstatus
				from grupo
				inner join permissao on percodigo=grupercodigo
                where  grudescricao like concat('%',p_grudescricao,'%');
		end #

delimiter ;

-- call sp_pesquisarGrupo("adm");


select *from mysql.user;

-- -----------------------------------------------------
-- Table `Usuario`.`Inserir`
-- -----------------------------------------------------
drop procedure if exists sp_inserirGrupo;

delimiter #
create procedure sp_inserirGrupo(p_grudescricao varchar(45), p_grupercodigo int(11))
	begin
		insert into grupo values(grucodigo, p_grudescricao, p_grupercodigo);
	end #
delimiter ;

call sp_inserirGrupo('Administrador',1);
call sp_inserirGrupo('Usuario',2);

-- -----------------------------------------------------
-- Table `Grupo`.`Alterar`
-- -----------------------------------------------------
drop procedure if exists sp_alterarGrupo;
delimiter #
 create procedure sp_alterarGrupo(p_grudescricao varchar(45), p_grupercodigo int(11), p_grucodigo int(11))
	 begin
		update grupo 
		set grudescricao = p_grudescricao, grupercodigo = p_grupercodigo 
		where grucodigo=p_grucodigo;
	 
	 end #

delimiter ;

-- call sp_alterarGrupo('adm',1,1);

-- -----------------------------------------------------
-- Table `Grupo`.`Listar`
-- -----------------------------------------------------
DROP PROCEDURE IF EXISTS sp_listarGrupo;
DELIMITER #
CREATE PROCEDURE sp_listarGrupo()
	begin
		select grucodigo, grudescricao, perstatus
			from grupo
            inner join permissao on percodigo=grupercodigo;
    end #

DELIMITER ;

-- call sp_listarGrupo;


-- -----------------------------------------------------
-- Table `Grupo`.`Excluir`
-- -----------------------------------------------------
drop procedure if exists sp_excluirGrupo;
delimiter #
	create procedure sp_excluirGrupo(p_grucodigo int(11))
    begin 
		delete 
        from grupo 
        where p_grucodigo =grucodigo;
    end #

delimiter ;

-- -----------------------------------------------------
-- Table `Usuario`.`Pesquisar`
-- -----------------------------------------------------
drop procedure if exists sp_pesquisarGrupo;
delimiter #
	create procedure sp_pesquisarGrupo(p_grudescricao varchar(45))
		begin	
			select grucodigo, grudescricao, perstatus
				from grupo
				inner join permissao on percodigo=grupercodigo
                where  grudescricao like concat('%',p_grudescricao,'%');
		end #

delimiter ;

-- call sp_pesquisarGrupo("adm");

-- -----------------------------------------------------
-- Table `Usuario`.`Criar Usuario`
-- -----------------------------------------------------

drop procedure if exists sp_criarUsuario;
delimiter #
create procedure sp_criarUsuario(p_usuario varchar(50), p_senha varchar(50), /* p_diasexpirar int ,*/ p_grupo int)
begin
	declare variavel varchar(150);
    set @variavel = concat('create user ', p_usuario, '@localhost', ' IDENTIFIED BY \'', p_senha/*, '\' PASSWORD EXPIRE INTERVAL ', p_diasexpirar, ' DAY*/,'\';');

    prepare execucao from @variavel;
    execute execucao;

    deallocate prepare execucao;
    insert into usuario values (usucodigo, p_usuario, p_grupo);
end#
delimiter ;

-- call sp_criarUsuario('AdrianoADM', '123', 2);

-- -----------------------------------------------------
-- Table `Usuario`.`Alterar Senha`
-- -----------------------------------------------------
    
drop procedure if exists sp_alterarSenhaUsuario;
delimiter #
create procedure sp_alterarSenhaUsuario(p_usuario varchar(50), p_novaSenha varchar(50)/*, p_novoDia int*/)
begin
	declare variavel varchar(150);
  
    set @variavel = concat('ALTER USER ', p_usuario, '@localhost IDENTIFIED BY \'', p_novaSenha/*, '\' PASSWORD EXPIRE INTERVAL ', p_novoDia, ' DAY;*/,'\';');
    prepare execucao from @variavel;
    execute execucao;
    deallocate prepare execucao;
end#
delimiter ;
-- call sp_alterarSenhaUsuario('JonathanFeitosa','123');

-- -----------------------------------------------------
-- Table `Usuario`.`Excluir Usuario`
-- -----------------------------------------------------

drop procedure if exists sp_excluirUsuario;
delimiter #
create procedure sp_excluirUsuario(p_usuario varchar(50))
begin
	declare variavel varchar(150);
    declare idUsuario INTEGER;
    
    set @variavel = concat('DROP USER ', p_usuario, '@localhost');
    prepare execucao from @variavel;
    execute execucao;
    deallocate prepare execucao;
	
    set @idUsuario := (select usucodigo from usuario where usulogin = p_usuario/* CURRENT_USER()*/);

    delete from permissao 
		where perusucodigo = @idUsuario;
    
    delete from usuario where usulogin = p_usuario;
end#
delimiter ;
-- call sp_excluirUsuario('JonathanADM');

-- call sp_darPermissaoP('Oliveira','cep_rural', 'sp_pesquisarCEP');
-- call sp_darPermissaoP('Oliveira','cep_rural', 'sp_listarCep');




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