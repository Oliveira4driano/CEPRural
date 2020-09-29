create database teste;
 
flush tables ;

select @@optmizer_switch; -- Otilizador de consultas mudanças consideráveis aconteceram o otilizador de consulas,
--  que tem suas suas variveis que influenciam na hora de determinar a melhor estratégia de recuperação de dados;
select * from employees partition(p0,p2); -- seleciona dados diretamento de uma particao. 
delete from employees partition(p1,p2);
select * from partiton;


show tables;
SELECT *
	FROM  information_schema.files;

SELECT *
	FROM  information_schema.INNODB_SYS_TABLESPACES;

SELECT * 
	FROM INFORMATION_SCHEMA.INNODB_SYS_DATAFILES;  -- lista a space e path

-- -----------------------------------------------------
-- Table `Tablespace`.` listar`
-- -----------------------------------------------------
 SELECT FILE_ID, NAME, FILE_NAME,FILE_SIZE,ALLOCATED_SIZE
			FROM INFORMATION_SCHEMA.INNODB_SYS_TABLESPACES
			INNER JOIN INFORMATION_SCHEMA.files ON SPACE=FILE_ID
            order by FILE_ID, NAME, FILE_NAME,FILE_SIZE,ALLOCATED_SIZE;-- lista space , name, file_format, file_size, allocated_size

-- -----------------------------------------------------
-- Table `Tablespace`.` pesquisar`
-- -----------------------------------------------------
 SELECT FILE_ID, NAME, FILE_NAME,FILE_SIZE,ALLOCATED_SIZE
			FROM INFORMATION_SCHEMA.INNODB_SYS_TABLESPACES
			INNER JOIN INFORMATION_SCHEMA.files ON SPACE=FILE_ID
            WHERE NAME LIKE concat('cep','%');
		
-- -----------------------------------------------------
-- Table `Tablespace`.` Excluir`
-- -----------------------------------------------------

drop tablespace registro3 engine = InnoDB; -- Excluir registro
 -- -----------------------------------------------------
-- Table `Tablespace`.` Alterar`
-- -----------------------------------------------------

ALTER TABLE  t1 TABLESPACE ts3;


  -- -----------------------------------------------------
-- Table `Tablespace`.` Criando um tablespace geral no diretório de dados MySQL`
-- -----------------------------------------------------

CREATE TABLESPACE ts1 ADD DATAFILE  'ts1.ibd' Engine=InnoDB;


  -- -----------------------------------------------------
-- Table `Tablespace`.` Criando um tablespace geral em um diretório fora do diretório de dados MySQL`
-- -----------------------------------------------------

create tablespace ts2 ADD DATAFILE 'C://TesteBanco/ts2.ibd'  Engine=InnoDB;

create tablespace ts3 ADD DATAFILE 'F:/Utilitario/Datafile/ts3.ibd'  Engine=InnoDB;

  -- -----------------------------------------------------
-- Table `Tablespace`.` Criando uma tabela com e sem tablespace MySQL`
-- -----------------------------------------------------

        
CREATE TABLE tabela5 (
		tabid INT UNSIGNED NOT NULL AUTO_INCREMENT,
        tabnome VARCHAR(50) NOT NULL,	
        PRIMARY KEY(tabid))
       -- TABLESPACE tabela5 STORAGE DISK 
        ENGINE=InnoDB;  
        
CREATE TABLE tabela5 (
		tabid INT UNSIGNED NOT NULL AUTO_INCREMENT,
        tabnome VARCHAR(50) NOT NULL,	
        PRIMARY KEY(tabid))
        TABLESPACE ts1 STORAGE DISK 
        ENGINE=InnoDB; 



SELECT * FROM INFORMATION_SCHEMA.ROUTINES;

SHOW variables LIKE '%innodb_data_file_path%';

SHOW VARIABLES LIKE 'innodb_file_per_table';

SHOW VARIABLES LIKE 'innodb_page_size';
SELECT * FROM INFORMATION_SCHEMA.INNODB_SYS_TABLESPACES WHERE NAME LIKE 'cep_rural/estado';

