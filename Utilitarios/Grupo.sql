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