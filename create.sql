create table carro (id  bigserial not null, descricao varchar(255), latitude varchar(255), longitude varchar(255), nome varchar(255), tipo varchar(255), url_foto varchar(255), url_video varchar(255), primary key (id));
create table role (id  serial not null, nome varchar(255), primary key (id));
create table user_roles (usuario_id int4 not null, role_id int4 not null);
create table usuario (id  serial not null, email varchar(255), login varchar(255), nome varchar(255), senha varchar(255), primary key (id));
alter table if exists user_roles add constraint FKrhfovtciq1l558cw6udg0h0d3 foreign key (role_id) references role;
alter table if exists user_roles add constraint FK9ky9kgtqlnuv6eyviodejs4ho foreign key (usuario_id) references usuario;
