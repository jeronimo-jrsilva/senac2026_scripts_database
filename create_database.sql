#drop database projetofinal;
create database projetofinal;
use projetofinal;

create table parceiros(
id_parceiro int primary key auto_increment,
nome_parceiro varchar(255),
contato char(14)
);

CREATE TABLE armazenamento (
id_armazenamento char(2) PRIMARY KEY,
linha INT NOT NULL,
coluna CHAR(1) NOT NULL,
o_v ENUM('Ocupado', 'Vazio') DEFAULT 'Vazio'
);

create table pedidos(
id_pedido int primary key auto_increment,
e_s enum('E','S'),
data_hora timestamp default current_timestamp,
id_parceiro int,
foreign key(id_parceiro) references parceiros(id_parceiro) on delete restrict
);

create table produtos(
id_produto int primary key auto_increment,
nome_produto varchar(255),
cod_produto varchar(255),
peso_total decimal(10,2),
id_pedido int,
id_armazenamento char(2),
foreign key(id_pedido) references pedidos(id_pedido) on delete restrict,
foreign key(id_armazenamento) references armazenamento(id_armazenamento) on delete restrict
);
