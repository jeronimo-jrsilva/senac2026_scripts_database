##drop database projetofinal;
  
create database projetofinal;
use projetofinal;

create table parceiros(
id_parceiro int primary key auto_increment,
nome_parceiro varchar(255),
contato char(14)
);

create table armazenamento(
id_armazenamento int primary key auto_increment,
galpao varchar(3),
rua int,
num int,
altura int
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
id_armazenamento int,
foreign key(id_pedido) references pedidos(id_pedido) on delete restrict,
foreign key(id_armazenamento) references armazenamento(id_armazenamento) on delete restrict
);
