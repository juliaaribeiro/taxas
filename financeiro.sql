CREATE DATABASE IF NOT EXISTS financeiro;
USE financeiro;

CREATE TABLE IF NOT EXISTS venda (
    id VARCHAR (1),
    estabelecimento VARCHAR(10),
    data_transacao DATE,
    data_evento DATE,
    hora_evento TIME,
    tipo_evento VARCHAR(2),
    tipo_transacao VARCHAR(2),
    numero_serie_leitor VARCHAR(8),
	codigo_transacao VARCHAR(32) PRIMARY KEY,
    codigo_pedido VARCHAR(20),
    valor_total DECIMAL(15,2),
    valor_liquido DECIMAL(15,2),
    pagamento VARCHAR(1),
    plano VARCHAR(2),
    parcela VARCHAR(2),
    qtd_parcelas VARCHAR(2),
    data_prevista_pagamento DATE,
    taxa_parcelamento_comprador DECIMAL(15,2),
    tarifa_boleto_comprador DECIMAL(15,2),
    valor_original DECIMAL(15,2),
    taxa_parcelamento_vendedor DECIMAL(15,2),
    taxa_intermediacao DECIMAL(15,2),
    tarifa_intermediacao DECIMAL(15,2),
    tarifa_boleto_vendedor DECIMAL(15,2),
    repasse_aplicacao DECIMAL(15,2),
    valor_liquido_transacao DECIMAL(15,2),
    status_pagamento VARCHAR(2),
    meio_pagamento VARCHAR(2),
    bandeira VARCHAR(30),
    canal_entrada VARCHAR(2),
    num_leitor VARCHAR(2),
    num_meio_captura VARCHAR(2),
    numero_logico VARCHAR(32),
    nsu VARCHAR(11),
    filler VARCHAR(3),
    cartao_bin VARCHAR(6),
    cartao_holder VARCHAR(4),
    codigo_autorizacao VARCHAR(6),
    codigo_cv VARCHAR(32),
    reservado_futuros VARCHAR(139)
);

CREATE TABLE IF NOT EXISTS cabecalho (
    tipo_registro VARCHAR(1), -- Sempre “0”
    estabelecimento VARCHAR(10),
    data_geracao_arquivo DATE,
    periodo_inicial DATE,
    periodo_final DATE,
    numero_sequencial VARCHAR (36) PRIMARY KEY,
    empresa_adquirente VARCHAR(5), -- Constante “FICTI”
    tipo_extrato VARCHAR(2),       -- “01” para vendas
    filler VARCHAR(21),
    versao_layout VARCHAR(3),      -- “002”
    versao_release VARCHAR(5),     -- “.002c”
    reservado_futuro VARCHAR(453)
);

Select * from venda;
select count(*) from venda;
Select * from cabecalho;
select count(*) from cabecalho;
-- DROP DATABASE financeiro;


