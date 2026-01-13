use projetofinal;

## HÁ ERROS DECORRENTES DA NOVA TABELA DE ARMAZENAMENTO

INSERT INTO parceiros (nome_parceiro, contato) VALUES
	('Alpha Distribuidora', '11-90000-0001'),
	('Beta Comércio',       '11-90000-0002'),
	('Gamma Importações',   '11-90000-0003'),
	('Delta Transportes',   '11-90000-0004'),
	('Omega Atacado',       '11-90000-0005');

INSERT INTO armazenamento (id_armazenamento, linha, coluna)
SELECT CONCAT(c.coluna, l.linha) AS id_armazenamento, l.linha, c.coluna
FROM (SELECT 1 AS linha UNION SELECT 2 UNION SELECT 3 UNION SELECT 4 UNION SELECT 5) l
CROSS JOIN (SELECT 'A' AS coluna UNION SELECT 'B' UNION SELECT 'C' UNION SELECT 'D' UNION SELECT 'E') c;


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
	('Folha A4 Chamequinho', 'A4CH', 3, 5, 5);
