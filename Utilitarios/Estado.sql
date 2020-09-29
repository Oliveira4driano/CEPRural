use cep_rural;
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
