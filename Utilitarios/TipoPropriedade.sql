


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

-- call sp_listarTipo;
