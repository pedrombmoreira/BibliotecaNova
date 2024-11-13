CREATE TABLE autores(
    IDaut serial not null primary key,
    nomeAut varchar(50) not null
);

CREATE TABLE categorias(
    IDcat serial not null primary key,
    nomeCat varchar(50) not null
);

CREATE TABLE usuarios(
    IDuser serial not null primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    nomeUser varchar(50) not null,
    email varchar(100) not null unique,
    senha varchar(100) not null unique,
    cpf varchar(11) not null unique,
    telefone varchar(20) not null,
    permissao boolean not null
);

CREATE TABLE livros(
    IDlivro serial not null primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    titulo varchar(50),
    IDaut integer not null ,
    IDcat integer not null,
    quantidade int,
    FOREIGN KEY (IDaut) REFERENCES autores(IDaut),
    FOREIGN KEY (IDcat) REFERENCES categorias(IDcat)
);

CREATE TABLE emprestimos(
    IDemp serial not null primary key,
    uuid UUID DEFAULT gen_random_uuid(),
    IDlivro integer not null,
    IDuser integer not null,
    dataemp date,
    datadev date,
    FOREIGN KEY (IDlivro) REFERENCES livros(IDlivro),
    FOREIGN KEY (IDuser) REFERENCES usuarios(IDuser)
);