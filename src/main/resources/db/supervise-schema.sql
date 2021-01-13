DROP TABLE IF EXISTS EXPERT;

create table EXPERT
(
    EXPERT_ID       INT auto_increment,
    EXPERT_NAME     VARCHAR(50)                 not null,
    EXPERT_PASSWORD VARCHAR(101) default 123456 not null,
    constraint EXPERT_PK
        primary key (EXPERT_ID)
);

DROP TABLE IF EXISTS MARKET;

create table MARKET
(
    MARKET_ID   INT auto_increment,
    MARKET_NAME VARCHAR(50) not null,
    PRINCIPAL   VARCHAR(50)         not null,
    constraint MARKET_PK
        primary key (MARKET_ID)
);

DROP TABLE IF EXISTS PRODUCT_TYPE;

create table PRODUCT_TYPE
(
    PRODUCT_TYPE_ID   INT auto_increment,
    PRODUCT_TYPE_NAME VARCHAR(50) not null,
    constraint PRODUCT_TYPE_PK
        primary key (PRODUCT_TYPE_ID)
);

DROP TABLE IF EXISTS MARKET_TASK_GROUP;

create table MARKET_TASK_GROUP
(
    MARKET_TASK_GROUP_ID INT auto_increment,
    DEADLINE             DATE not null,
    TASK_DESCRIPTION     VARCHAR(200),
    constraint TASK_GROUP_PK
        primary key (MARKET_TASK_GROUP_ID)
);

DROP TABLE IF EXISTS MARKET_TASK;

create table MARKET_TASK
(
    MARKET_TASK_ID       INT auto_increment,
    IS_FINISHED          BOOLEAN default FALSE not null,
    MARKET_TASK_GROUP_ID INT,
    MARKET_ID            INT,
    constraint MARKET_TASK_PK
        primary key (MARKET_TASK_ID),
    constraint MARKET_TASK_MARKET_MARKET_ID_FK
        foreign key (MARKET_ID) references MARKET (MARKET_ID),
    constraint MARKET_TASK_MARKET_TASK_GROUP_MARKET_TASK_GROUP_ID_FK
        foreign key (MARKET_TASK_GROUP_ID) references MARKET_TASK_GROUP (MARKET_TASK_GROUP_ID)
);

DROP TABLE IF EXISTS MARKET_TASK_ITEM;

create table MARKET_TASK_ITEM
(
    MARKET_TASK_ITEM_ID INT auto_increment,
    MARKET_TASK_ID      INT                   not null,
    PRODUCT_TYPE_ID     INT                   not null,
    UNQUALIFIED_NUMBER  INT     default 0     not null,
    IS_FINISHED         BOOLEAN default FALSE not null,
    FINISH_DATE         DATE    default NULL,
    constraint MARKET_TASK_ITEM_PK
        primary key (MARKET_TASK_ITEM_ID),
    constraint MARKET_TASK_ITEM_MARKET_TASK_MARKET_TASK_ID_FK
        foreign key (MARKET_TASK_ID) references MARKET_TASK (MARKET_TASK_ID),
    constraint MARKET_TASK_ITEM_PRODUCT_TYPE_PRODUCT_TYPE_ID_FK
        foreign key (PRODUCT_TYPE_ID) references PRODUCT_TYPE (PRODUCT_TYPE_ID)
);

DROP TABLE IF EXISTS EXPERT_TASK_GROUP;
create table EXPERT_TASK_GROUP
(
    EXPERT_TASK_GROUP_ID INT auto_increment,
    DEADLINE             DATE not null,
    TASK_DESCRIPTION     VARCHAR(200),
    constraint EXPERT_TASK_GROUP_PK
        primary key (EXPERT_TASK_GROUP_ID)
);

DROP TABLE IF EXISTS EXPERT_TASK;
create table EXPERT_TASK
(
    EXPERT_TASK_ID       INT auto_increment,
    IS_FINISHED          BOOLEAN default FALSE not null,
    EXPERT_TASK_GROUP_ID INT,
    EXPERT_ID            INT,
    constraint EXPERT_TASK_PK
        primary key (EXPERT_TASK_ID),
    constraint EXPERT_TASK_EXPERT_EXPERT_ID_FK
        foreign key (EXPERT_ID) references EXPERT (EXPERT_ID),
    constraint EXPERT_TASK_EXPERT_TASK_GROUP_EXPERT_TASK_GROUP_ID_FK
        foreign key (EXPERT_TASK_GROUP_ID) references EXPERT_TASK_GROUP (EXPERT_TASK_GROUP_ID)
);

DROP TABLE IF EXISTS EXPERT_TASK_ITEM;
create table EXPERT_TASK_ITEM
(
    EXPERT_TASK_ITEM_ID INT auto_increment,
    EXPERT_TASK_ID      INT,
    PRODUCT_TYPE_ID     INT,
    UNQUALIFIED_NUMBER  INT     default 0 not null,
    IS_FINISHED         BOOLEAN default FALSE,
    FINISH_DATE         DATE              not null,
    MARKET_ID           INT,
    constraint EXPERT_TASK_ITEM_PK
        primary key (EXPERT_TASK_ITEM_ID),
    constraint EXPERT_TASK_ITEM_EXPERT_TASK_EXPERT_TASK_ID_FK
        foreign key (EXPERT_TASK_ID) references EXPERT_TASK (EXPERT_TASK_ID),
    constraint EXPERT_TASK_ITEM_MARKET_MARKET_ID_FK
        foreign key (MARKET_ID) references MARKET (MARKET_ID),
    constraint EXPERT_TASK_ITEM_PRODUCT_TYPE_PRODUCT_TYPE_ID_FK
        foreign key (PRODUCT_TYPE_ID) references PRODUCT_TYPE (PRODUCT_TYPE_ID)
);




