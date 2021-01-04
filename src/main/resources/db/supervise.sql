DROP TABLE IF EXISTS EXPERT;

create table EXPERT
(
    EXPERT_ID       INT auto_increment,
    EXPERT_NAME     VARCHAR(50)                 not null,
    EXPERT_PASSWORD VARCHAR(101) default 123456 not null,
    constraint EXPERT_PK
        primary key (EXPERT_ID)
);

INSERT INTO PUBLIC.EXPERT (EXPERT_ID, EXPERT_NAME, EXPERT_PASSWORD) VALUES (1, 'dog', 'doge');
