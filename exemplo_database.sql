INSERT INTO parceiros (nome_parceiro, contato) VALUES
('Alpha Distribuidora', '11-90000-0001'),
('Beta Comércio',       '11-90000-0002'),
('Gamma Importações',   '11-90000-0003'),
('Delta Transportes',   '11-90000-0004'),
('Omega Atacado',       '11-90000-0005');

INSERT INTO armazenamento (galpao, rua, num, altura) VALUES
('G01', 1, 10, 5),
('G02',  2, 20, 4),
('G02',  3, 30, 6),
('G01',  4, 40, 5),
('G02',  5, 50, 7);

INSERT INTO pedidos (E_S, id_parceiro) VALUES
('E', 1),
('S', 2),
('E', 3),
('S', 4),
('E', 5);

INSERT INTO produtos (nome_produto, cod_produto, peso_total, id_pedido, id_armazenamento) values
('Arroz Tio João', 'ARTJ', 0.5, 1, 1),
('Farinha Dona Benta', 'FRDB', 0.5, 2, 2),
('Café Três Corações', 'CA3C', 0.5, 3, 3),
('Shampoo Palmolive', 'SPML', 0.5, 4, 4),
('Folha A4 Chamequinho', 'A4CH', 3, 5, 5)
;

select * from parceiros;
select * from armazenamento;
select * from pedidos;
select * from produtos;