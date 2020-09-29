/********* USUARIOS - CRIAR, EDITAR, EXCLUIR ************************/
/**********************************************************/
select *from mysql.user;

 select *
     from dba_tablespace_usage_metrics
     where tablespace_name in ('ts1', 'ts2', 'ts3')
     order by 1;


FLUSH PRIVILEGES;

GRANT SELECT ON `mysql`.`proc` TO 'JonathanADM'@'localhost';

drop procedure sp_criarUsuario;
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
call sp_criarUsuario('JonathanADM', '123', 2);

select * from usuario;
    delete from usuario where usulogin = 'TesteTeste';
    
-- EXCLUIR USUARIO
drop procedure sp_excluirUsuario;
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
call sp_excluirUsuario('JonathanADM');


/*
-- ALTERAR SENHA USUARIO
drop procedure sp_alterarSenha;
delimiter #
create procedure sp_alterarSenha(p_usuario varchar(50), p_senha varchar(50))
begin
	declare variavel varchar(150);
    set @variavel = concat('set password for ', p_usuario, '@localhost = password("',p_senha,'")');
    prepare execucao from @variavel;
    execute execucao;
    deallocate prepare execucao;
end#
delimiter ;
call sp_alterarSenha('JonathanFeitosafbg','123');
*/

-- ALTERAR SENHA USUARIO
drop procedure sp_alterarSenhaUsuario;
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
call sp_alterarSenhaUsuario('JonathanFeitosa','123');

-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

use cep_rural;
/************************ PERMISSAO USUARIO ************************/
/**********************************************************/
select * from permissao;
-- DAR PERMISSAO AO USUARIO A UMA DETERMINADA TABELA !!
call sp_darPermissao('JonathanADM','cep_rural', 'grupo', 'insert');
drop procedure sp_darPermissao;
delimiter #
create procedure sp_darPermissao(
				p_nomeusuario varchar(50), p_nomebanco varchar(50), p_tabela varchar(50), p_manipulacao varchar(50))
begin
	declare variavel varchar(150);
    declare idUsuario integer;
    
	set @variavel = concat('GRANT ', p_manipulacao, ' ON ', p_nomebanco, '.',p_tabela, ' TO ', p_nomeusuario/*CURRENT_USER()*/, '@localhost;');
        
	prepare execucao from @variavel;
	execute execucao;
	deallocate prepare execucao;
        
	set @idUsuario := (select usucodigo from usuario where usulogin = p_nomeusuario/* CURRENT_USER()*/);

	insert into permissao(pertabela, perdescricao, perusucodigo)
		values(p_tabela, p_manipulacao, @idUsuario);
end#
delimiter ;
select * from permissao;
-- PERMISSAO DE ACESSO AOS METADADOS !!!!
call sp_darPermissaoMD('JonathanADM', 'select');
drop procedure sp_darPermissaoMD;
delimiter #
create procedure sp_darPermissaoMD(
				p_nomeusuario varchar(50), p_manipulacao varchar(50))
begin
	declare variavel varchar(150);
    declare idUsuario integer;
    
	set @variavel = concat('GRANT ', p_manipulacao, ' ON `mysql`.`proc` TO ',p_nomeusuario, '@localhost');   
	
	prepare execucao from @variavel;
    execute execucao;
	deallocate prepare execucao;
        
	set @idUsuario := (select usucodigo from usuario where usulogin = p_nomeusuario/* CURRENT_USER()*/);

	insert into permissao(pertabela, perdescricao, perusucodigo)
		values('mysql.proc', p_manipulacao, @idUsuario);
end#
delimiter ;
    
    
-- PERMISSAO DE PROCEDURE
select * from permissao;


call sp_darPermissaoP('JonathanADM','cep_rural', 'sp_pesquisarCEP');
drop procedure sp_darPermissaoP;
delimiter #
create procedure sp_darPermissaoP(
				p_nomeusuario varchar(50), p_nomebanco varchar(50), p_procedure varchar(50))
begin
	declare variavel varchar(150);
    declare idUsuario integer;
    
	set @variavel = concat('GRANT EXECUTE ON PROCEDURE ', p_nomebanco, '.',p_procedure, ' TO ', p_nomeusuario/*CURRENT_USER()*/, '@localhost;');
        
	prepare execucao from @variavel;
	execute execucao;
	deallocate prepare execucao;
        
	set @idUsuario := (select usucodigo from usuario where usulogin = p_nomeusuario/* CURRENT_USER()*/);

	insert into permissao(pertabela, perdescricao, perusucodigo)
		values(p_procedure, 'procedure', @idUsuario);
end#
delimiter ;

select * from permissao;
-- REMOVER PERMISSAO DO USUARIO A DETERMINADA TABELA
drop procedure if exists sp_removerPrivilegios;
call sp_removerPrivilegios('JonathanADM', 'cep_rural', 'grupo', 'insert');

delimiter #
create procedure sp_removerPrivilegios(p_usuario varchar(50), p_nomebanco varchar(50), p_tabela varchar(50), p_manipulacao varchar(50))
begin
	declare variavel varchar(150);
	declare idUsuario integer;
     
    set @variavel = concat('REVOKE ',p_manipulacao, ' ON ', p_nomebanco, '.',p_tabela, ' FROM ', p_usuario, '@localhost;');
    prepare execucao from @variavel;
    execute execucao;
    deallocate prepare execucao;
	
    set @idUsuario := (select usucodigo from usuario where usulogin = p_usuario/* CURRENT_USER()*/);
    
    delete from permissao 
		where perusucodigo = @idUsuario and pertabela = p_tabela and p_manipulacao = perdescricao;
        
end #
delimiter ;

-- REMOVER TODAS AS PERMISSOES DE UM USUARIO 
/*
delimiter #
create procedure sp_removerTodosPrivilegios(p_usuario varchar(50), p_nomebanco varchar(50))
begin
	declare variavel varchar(150);
    declare idUsuario integer;
    
    set @variavel = concat('REVOKE ALL PRIVILEGES ON ', p_nomebanco,'.* FROM ', p_usuario, '@localhost');
    prepare execucao from @variavel;
    execute execucao;
    deallocate prepare execucao;

    set @idUsuario := (select usucodigo from usuario where usulogin = p_nomeusuario/* CURRENT_USER()*\/);
    
	delete from permissao 
		where perusucodigo = @idUsuario;
end#
delimiter ;
drop procedure if exists sp_removerTodosPrivilegios;
call sp_removerTodosPrivilegios('Teste', 'cep_rural'); */

select * from permissao;


-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- Obs[2].: Para definir as mesmas permissões para conexões externas, devemos repetir o comando e substituir o localhost por % .
SELECT CURRENT_USER();
SHOW GRANTS FOR 'JonathanADM'@'localhost';
drop procedure sp_darTodosPrivilegios;
call sp_darTodosPrivilegios('JonathanFeitosa', 'cep_rural');
use cep_rural;
show databases;
select *from mysql.user;

FLUSH PRIVILEGES;

select * from mysql






-- =============================================================================
/*************** PERMISSAO ************************************/

SELECT * FROM permissao;

INSERT INTO permissao values(1,'V'),(2,'F');
-- =============================================================================
/*************** Pesquisar PERMISSAO ************************************/
select * from permissao;
desc permissao;
drop procedure if exists sp_pesquisarPermissao;
delimiter #
create procedure sp_pesquisarPermissao(p_status char(1) )
	begin
		select percodigo, perstatus 
			from permissao
            where p_status like perstatus;
    end #
    
delimiter ;

call sp_pesquisarPermissao("F");
 
 
 -- //////////////////// USUARIO
 /*LISTAR*************/
DROP PROCEDURE IF EXISTS sp_listarUsuario;
DELIMITER #
CREATE PROCEDURE sp_listarUsuario(p_grudescricao varchar(45))
	begin
		select usucodigo, usulogin, grudescricao from usuario
		inner join grupo on grucodigo = usugrucodigo;
    end #
DELIMITER ;

call sp_listarUsuario();

-- =============================================================================
/************** GRUPO USUARIO***********************************************/

 /*LISTAR*************/
DROP PROCEDURE IF EXISTS sp_listarGrupo;
DELIMITER #
CREATE PROCEDURE sp_listarGrupo()
	begin
		select grucodigo, grudescricao from grupo;
    end #
DELIMITER ;

call sp_listarGrupo();
/* INSERIR *************/
drop procedure sp_inserirGrupo;
delimiter #
create procedure sp_inserirGrupo(p_grudescricao varchar(45))
	begin
		insert into grupo values(grucodigo, p_grudescricao);
	end #
delimiter ;
call sp_inserirGrupo('Funcionario');


/*ATUALIZAR*************/
drop procedure if exists sp_alterarGrupo;
delimiter #
 create procedure sp_alterarGrupo(p_grudescricao varchar(45), p_grupercodigo int(11), p_grucodigo int(11))
	 begin
		update grupo 
		set grudescricao = p_grudescricao, grupercodigo = p_grupercodigo 
		where grucodigo=p_grucodigo;
	 
	 end #

delimiter ;

call sp_alterarGrupo('adm',1,1);

/*LISTAR*************/
DROP PROCEDURE IF EXISTS sp_listarGrupo;
DELIMITER #
CREATE PROCEDURE sp_listarGrupo()
	begin
		select grucodigo, grudescricao
			from grupo;
    end #

DELIMITER ;

call sp_listarGrupo();

select * from grupo;

/*buscar por ID ***************/
drop procedure if exists sp_buscarPorId;

delimiter #
	create procedure sp_buscarPorId( p_grucodigo int(11))
	begin
		select grucodigo, grudescricao, perstatus
			from grupo
            inner join permissao on percodigo=grupercodigo
			where grucodigo = p_grucodigo;
    end #

delimiter ;


call sp_buscarPorId(1);

/* escluir grupo ************/
drop procedure if exists sp_excluirGrupo;
delimiter #
	create procedure sp_excluirGrupo(p_grucodigo int(11))
    begin 
		delete 
        from grupo 
        where p_grucodigo =grucodigo;
    end #

delimiter ;

call sp_excluirGrupo(10);

/*pesquisar*****************************************/
drop procedure if exists sp_pesquisarUsuario;
delimiter #
	create procedure sp_pesquisarUsuario(p_usudescricao varchar(45))
		begin	
			select usucodigo, usulogin,  grudescricao from usuario
				inner join grupo on grucodigo = usugrucodigo
					where usulogin like concat('%',p_usudescricao,'%');

        
		end #

delimiter ;


call sp_pesquisarUsuario('Tes');

/*pesquisar*****************************************/
drop procedure if exists sp_pesquisarGrupo;
delimiter #
	create procedure sp_pesquisarGrupo(p_grudescricao varchar(45))
		begin	
			select grucodigo, grudescricao
				from grupo
                where  grudescricao like concat('%',p_grudescricao,'%');
    
		end #

delimiter ;


call sp_pesquisarGrupo('adm');

-- =============================================================================

/******************************************************/
/*inserir ******************************/
drop procedure if exists sp_inserirEstado;

delimiter #
create procedure sp_inserirEstado( p_estnome varchar(45), p_estsigla varchar(2) )
	begin 
		insert into estado values( estcodigo, p_estnome, p_estsigla); 
    
    end #

delimiter ;

call sp_inserirEstado('Amazonas','AM');
select * from estado;

/******************************************************/
/*alterar ******************************/

drop procedure if exists sp_alterarEstado;

delimiter #
create procedure sp_alterarEstado( p_estcodigo int(11), p_estnome varchar(45), p_estsigla varchar(2))
	begin
		update estado
        set estnome = p_estnome, estsigla = p_estsigla
        where estcodigo =p_estcodigo;
    end #
    
delimiter ;

call sp_alterarEstado(1,'Amazonas','AM');


/******************************************************/
/*excluir ******************************/

drop procedure if exists sp_excluirEstado;

delimiter #
create procedure sp_excluirEstado(p_estcodigo int(11))
	begin 
		delete 
			from estado
			where estcodigo = p_estcodigo;
    end #

delimiter ;


call sp_excluirEstado(1);
select * from estado;

/******************************************************/
/*pesquisar ******************************/

drop procedure sp_pesquisarEstado;

delimiter #
create procedure sp_pesquisarEstado(p_estnome varchar(45))
	begin 
		select estcodigo, estnome, estsigla
        from estado
        where estnome like concat('%', p_estnome,'%'); 
        
       
    
    end #
    
delimiter ;

call sp_pesquisarEstado('Amazonas');
select * from estado;

/******************************************************/
/*inserir ******************************/
drop procedure if exists sp_inserirTipo;
delimiter #
create procedure sp_inserirTipo(p_tprtipo varchar(45))
	begin
		insert 
        into tipopropriedade 
        values (tprcodigo, p_tprtipo);
    end#

delimiter ;

call sp_inserirTipo('Casa Alvenaria');

/******************************************************/
/*alterar ******************************/

drop procedure if exists sp_alterarTipo;
delimiter #
create procedure sp_alterarTipo(p_tprcodigo int(11), p_tprtipo varchar(45))
	begin
		update tipopropriedade
        set tprtipo = p_tprtipo
        where tprcodigo =p_tprcodigo;
    end #
delimiter ;

call sp_alterarTipo(1,'casa Mista');

/******************************************************/
/*excluir ******************************/
drop procedure sp_excluirTipo;
delimiter #
create procedure sp_excluirTipo(p_tprcodigo int(11))
	begin
		delete 
			from tipopropriedade
			where tprcodigo = p_tprcodigo;
	end #

delimiter ;

call sp_excluirTipo(2);

/******************************************************/
/*pesquisar ******************************/
drop procedure if exists sp_pesquisarTipo;

delimiter #
create procedure sp_pesquisarTipo(p_tprtipo varchar(45))
	begin
		select tprcodigo, tprtipo
			from tipopropriedade
            where tprtipo like concat('%',p_tprtipo,'%');
    end#

delimiter ;

call sp_pesquisarTipo('ca');

/******************************************************/
/*inserir ******************************/
drop procedure if exists sp_inserirComunidade;

delimiter #
create procedure sp_inserirComunidade(p_comnome varchar(45), p_comqtdprop int(11), p_comestcodigo int(11))
	begin
		insert into comunidade 
			values (comcodigo, p_comnome, p_comqtdprop, p_comestcodigo);
    
    end #
delimiter ;

call sp_inserirComunidade('Vila Batista',4,1);

 
/******************************************************/
/*alterar ******************************/

drop procedure if exists sp_alterarComunidade;
delimiter #
create procedure sp_alterarComunidade(p_comcodigo int(11), p_comnome varchar(45), p_comqtdprop int(11), p_comestcodigo int(11))
	begin
		update comunidade 
			set comnome= p_comnome,comqtdprop= p_comqtdprop, comestcodigo=p_comestcodigo
            where comcodigo = p_comcodigo;
    end #

delimiter ;

call sp_alterarComunidade(1,'Vila',5,1);


/******************************************************/
/*excluir ******************************/
drop procedure sp_excluirComunidade;

delimiter #
create procedure sp_excluirComunidade(p_comcodigo int(11))
	begin 
		delete 
			from comunidade
            where comcodigo=p_comcodigo;
    end #
delimiter ;

call sp_excluirComunidade(0);


/******************************************************/
/*pesquisar ******************************/
drop procedure sp_pesquisarComunidade;

delimiter #
create procedure sp_pesquisarComunidade(p_comnome varchar(45))
	begin
		select comcodigo, comnome, comqtdprop, estsigla
        from comunidade
        inner join estado on estcodigo=comestcodigo
        where comnome like concat('%',p_comnome,'%');
    end #

delimiter ;

call sp_pesquisarComunidade('');

-- Populando Banco de Dados
insert into cep (cepcodigo, ceplat,ceplongi, cepnomeprop, cepcomcodigo, ceptprcodigo) values 
	
	('09210001','-3.339666667','-60.57413889','Evandro',1,1),
    ('09210002','-3.339611111','-60.57575','Joacir',1,1),
    ('09210003','-3.339944444','-60.57647222','José Vitor',1,1),
    ('09210004','-3.340083333','-60.57644444','Rosa',1,1),
    ('09210005','-3.340527778','-60.58022222','Valdecir',1,1)
;

insert into comunidade (comcodigo, comnome, comqtdprop, comestcodigo) values
	(1, 'Nossa Senhora das Gracas', 5, 1);

insert into usuarios(usulogin,ususenha,usugrucodigo) values 
	('aa', 'aa', 1);

insert into estado (estnome, estsigla) values
	('Teste', '93');
  
insert into tipopropriedade (tprtipo) values
	('Casa');
      
      select * from grupo;
        
      
-- =============================================================================
-- Procedure de Pesquisa CEP !!!! 

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

call sp_pesquisarCEP('092');

select * from comunidade;
-- //////////////////////////////////////////////


-- Procedure de Filtar Comunidade !!!!           
 
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

call sp_filtarComunidade('Nossa Senhora das Gracas');
-- =============================================================================
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





