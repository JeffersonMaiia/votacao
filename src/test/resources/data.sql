insert into pauta (id,nome, status, data_encerramento) values ('fef7c2a2-aa94-48c6-a38f-2c08eeeb0c4d','Pauta 1', 'ABERTA', current_timestamp + INTERVAL '5' MINUTE);
insert into voto (id, cpf, data_criacao, voto, pauta_id) select gen_random_uuid(), '81395580014', current_timestamp,'SIM', pauta.id from pauta where nome = 'Pauta 1';
insert into voto (id, cpf, data_criacao, voto, pauta_id) select gen_random_uuid(), '83631386060', current_timestamp,'NAO', pauta.id from pauta where nome = 'Pauta 1';

insert into pauta (id,nome, status, data_encerramento) values ('c736c60d-ebe2-418d-b75a-9ad64f4991c3','Pauta 2', 'ENCERRADA', current_timestamp - INTERVAL '5' MINUTE);
insert into voto (id, cpf, data_criacao, voto, pauta_id) select gen_random_uuid(), '81395580014', current_timestamp,'SIM', pauta.id from pauta where nome = 'Pauta 2';
insert into voto (id, cpf, data_criacao, voto, pauta_id) select gen_random_uuid(), '83631386060', current_timestamp,'NAO', pauta.id from pauta where nome = 'Pauta 2';
