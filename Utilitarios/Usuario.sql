
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