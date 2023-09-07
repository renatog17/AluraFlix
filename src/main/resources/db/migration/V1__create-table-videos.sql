create table videos(
	id bigint not null auto_increment,
	titulo varchar(255),
	descricao varchar(511),
	url varchar(255),
	primary key(id)
)