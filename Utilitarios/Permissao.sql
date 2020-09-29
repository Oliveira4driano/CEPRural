
-- DAR PERMISSAO AO USUARIO A UMA DETERMINADA TABELA !!
call sp_darPermissao('Oliveira','cep_rural', 'cep', 'select');
call sp_darPermissao('JonathanADM','cep_rural', 'cep', 'insert');

select * from permissao;

-- -----------------------------------------------------
-- Table `permisao`.`dar Permissao`
-- -----------------------------------------------------

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
call sp_darPermissaoMD('Oliveira', 'select');
select * from mysql.proc;
drop procedure sp_darPermissaoMD;

select * from usuario;
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


call sp_darPermissaoP('Oliveira','cep_rural', 'sp_pesquisarCEP');
call sp_darPermissaoP('Oliveira','cep_rural', 'sp_listarCep');
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
