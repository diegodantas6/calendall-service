-- -----------------------------------------------------
-- Table dados_pro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS dados_pro (
  id BIGINT NOT NULL AUTO_INCREMENT,
  tipo_pagamento CHAR(1) NOT NULL comment 'B = boleto / C = cartao',
  tipo_atendimento CHAR(1) NOT NULL comment 'R = residencia / L = local proprio / A = ambos',
  tempo_entre_agendas INT NOT NULL,
  cep VARCHAR(8) NOT NULL,
  numero INT NOT NULL,
  complemento VARCHAR(60) NOT NULL,
  PRIMARY KEY (id))
;

-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  id BIGINT NOT NULL AUTO_INCREMENT,
  email VARCHAR(60) NOT NULL,
  senha VARCHAR(20) NOT NULL,
  tipo CHAR(1) NOT NULL comment 'P = profissional / C = cliente',
  nome VARCHAR(45) NOT NULL,
  cpf VARCHAR(11) NULL,
  foto LONGBLOB NULL,
  celular VARCHAR(11) NULL,
  situacao CHAR(1) NOT NULL comment 'A = ativo / B = bloqueado',
  dados_pro_id BIGINT NULL,
  INDEX fk_usuario_dados_pro_idx (dados_pro_id ASC),
  PRIMARY KEY (id),
  CONSTRAINT fk_usuario_dados_pro
    FOREIGN KEY (dados_pro_id)
	REFERENCES dados_pro (id)
	ON DELETE NO ACTION
	ON UPDATE NO ACTION,
  UNIQUE INDEX login_UNIQUE (login ASC),
  UNIQUE INDEX cpf_UNIQUE (cpf ASC))
;

-- -----------------------------------------------------
-- Table atividade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS atividade (
  id BIGINT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(45) NOT NULL,
  PRIMARY KEY (id))
;

-- -----------------------------------------------------
-- Table usuario_atividade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario_atividade (
  id BIGINT NOT NULL AUTO_INCREMENT,
  tempo INT NOT NULL,
  preço DECIMAL(6,2) NOT NULL,
  usuario_id BIGINT NOT NULL,
  atividade_id BIGINT NOT NULL,
  INDEX fk_usuario_atividade_usuario_idx (usuario_id ASC),
  INDEX fk_usuario_atividade_atividade_idx (atividade_id ASC),
  PRIMARY KEY (id),
  CONSTRAINT fk_usuario_atividade_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_usuario_atividade_atividade
    FOREIGN KEY (atividade_id)
    REFERENCES atividade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table dados_cartao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS dados_cartao (
  id BIGINT NOT NULL AUTO_INCREMENT,
  numero CHAR(16) NOT NULL,
  cvv CHAR(3) NOT NULL,
  mes INT NOT NULL,
  ano INT NOT NULL,
  nome VARCHAR(45) NOT NULL,
  situacao CHAR(1) NOT NULL comment 'A = ativo / I = inativo',
  bandeira INT NOT NULL,
  usuario_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_dados_cartao_usuario_idx (usuario_id ASC),
  CONSTRAINT fk_dados_cartao_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table agenda
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS agenda (
  id BIGINT NOT NULL AUTO_INCREMENT,
  data_agenda DATETIME NOT NULL,
  situacao CHAR(1) NOT NULL comment 'A = agendada / C = cancelada',
  cordenadas_cliente VARCHAR(45) NULL,
  usuario_id_pro BIGINT NOT NULL,
  usuario_id_cli BIGINT NOT NULL,
  atividade_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_agenda_usuario_pro_idx (usuario_id_pro ASC),
  INDEX fk_agenda_usuario_cli_idx (usuario_id_cli ASC),
  INDEX fk_agenda_atividade_idx (atividade_id ASC),
  CONSTRAINT fk_agenda_usuario_pro
    FOREIGN KEY (usuario_id_pro)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_agenda_usuario_cli
    FOREIGN KEY (usuario_id_cli)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_agenda_atividade
    FOREIGN KEY (atividade_id)
    REFERENCES atividade (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table expediente
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS expediente (
  id BIGINT NOT NULL AUTO_INCREMENT,
  dia_semana INT NOT NULL,
  hora_inicio TIME NOT NULL,
  hora_fim TIME NOT NULL,
  usuario_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_espediente_usuario_idx (usuario_id ASC),
  CONSTRAINT fk_espediente_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table folga
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS folga (
  id BIGINT NOT NULL AUTO_INCREMENT,
  data_folga DATE NOT NULL,
  descricao VARCHAR(60) NULL,
  usuario_id BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_folga_usuario_idx (usuario_id ASC),
  CONSTRAINT fk_folga_usuario
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;

-- -----------------------------------------------------
-- Table amizade
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS amizade (
  id BIGINT NOT NULL AUTO_INCREMENT,
  situacao CHAR(1) NOT NULL comment 'P = pendente / C = confirmada',
  usuario_id_pro BIGINT NOT NULL,
  usuario_id_cli BIGINT NOT NULL,
  PRIMARY KEY (id),
  INDEX fk_amizade_usuario_pro_idx (usuario_id_pro ASC),
  INDEX fk_amizade_usuario_cli_idx (usuario_id_cli ASC),
  CONSTRAINT fk_amizade_usuario_pro
    FOREIGN KEY (usuario_id_pro)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_amizade_usuario_cli
    FOREIGN KEY (usuario_id_cli)
    REFERENCES usuario (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;
