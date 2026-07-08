----------------------------------------------------
-- Sequences
----------------------------------------------------
CREATE SEQUENCE team_seq;
CREATE SEQUENCE rider_seq;

----------------------------------------------------
-- Tables
----------------------------------------------------

-- Table: Team
CREATE TABLE team
(
    id          BIGINT DEFAULT nextval('team_seq'),
    name        VARCHAR(255) NOT NULL,
    constructor VARCHAR(255) NOT NULL,
    motorcycle  VARCHAR(255) NOT NULL,
    country     VARCHAR(255) NOT NULL,
    CONSTRAINT pk_team PRIMARY KEY (id),
    CONSTRAINT uk_team_name UNIQUE (name)
);

-- Indexes: Team
CREATE UNIQUE INDEX ui_team_name ON team USING btree (name);

-- Table: Rider
CREATE TABLE rider
(
    id            BIGINT DEFAULT nextval('rider_seq'),
    name          VARCHAR(255) NOT NULL,
    country       VARCHAR(255) NOT NULL,
    number        INTEGER      NOT NULL,
    championships INTEGER      NOT NULL,
    team          BIGSERIAL    NOT NULL,
    CONSTRAINT pk_rider PRIMARY KEY (id),
    CONSTRAINT uk_rider_name UNIQUE (name),
    CONSTRAINT fk_rider_team FOREIGN KEY (team) REFERENCES team (id)
        MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

-- Indexes: Rider
CREATE UNIQUE INDEX ui_rider_name ON rider USING btree (name);