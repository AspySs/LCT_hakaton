CREATE TABLE IDEAS
(
    Id   integer PRIMARY KEY NOT NULL,
    Name VARCHAR(100),
    Descript VARCHAR(1000),
    Status VARCHAR(100)
);

CREATE TABLE COMMENTS
(
    Id       integer PRIMARY KEY NOT NULL,
    User_id  integer,
    Idea_id  integer,
    Time     DATE,
    Comment  VARCHAR(1000)
);

CREATE TABLE REACTIONS
(
    Id              integer PRIMARY KEY NOT NULL,
    User_id         integer,
    Idea_id         integer,
    CONSTRAINT fk_reactions_ideas foreign key (Idea_id) REFERENCES IDEAS (Id),
    Comment_id      integer,
    CONSTRAINT fk_reactions_comments foreign key (Comment_id) REFERENCES COMMENTS (Id)
);

CREATE TABLE WORKERS
(
    Id           integer PRIMARY KEY NOT NULL,
    User_id      integer,
    Resume       VARCHAR(1000),
    Tags         VARCHAR(1000)
);

CREATE TABLE COMMAND
(
    Id           integer PRIMARY KEY NOT NULL,
    Idea_id      integer,
    Worker_id    integer,
    Status       VARCHAR(1000),
    CONSTRAINT fk_command_workers foreign key (Worker_id) REFERENCES WORKERS (Id),
    CONSTRAINT fk_command_ideas foreign key (Idea_id) REFERENCES IDEAS (Id)
);

CREATE TABLE Usrs
(
    Id           integer PRIMARY KEY NOT NULL,
    Name         VARCHAR NOT NULL,
    resume       VARCHAR,
    Status       VARCHAR(1000)
);