alter table videos add categoria_id bigint;
alter table videos add foreign key (categoria_id)
references categorias(id);