# HackerRank

Prueba Tecnica, evalua dos arrays de ratings

## What you need

JDK 1.10 or later.

Maven 3.2+

Mysql 5 or later

Hibernate 5.5.7.Final

##Run Project
Es necesario ejecutarlo con Java 10, sino marcara error de compilado.
```bash
mvn spring-boot:run
```

## Querys

```python
create schema HackerRankGNP;

create table players
(
    id   int auto_increment
        primary key,
    name varchar(50) null
);

create table challenges
(
    id     int auto_increment
        primary key,
    winner int               null,
    score  varchar(10)       not null,
    draw   tinyint default 0 null,
    constraint challenges___fkwinner
        foreign key (winner) references players (id)
);

create table challenge_detail
(
    id           int auto_increment
        primary key,
    id_challenge int         not null,
    id_player    int         not null,
    review       varchar(50) not null,
    constraint FK93eglxssj95tbrb2op2nxuo0c
        foreign key (id_player) references players (id),
    constraint challenge_detail___fk_challenge
        foreign key (id_challenge) references challenges (id)
);

create index challenges_winner_index
    on challenges (winner);

```