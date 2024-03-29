CREATE TABLE IF NOT EXISTS userinfo (
    rid VARCHAR(200) PRIMARY KEY,
    user_name VARCHAR(200) NOT NULL,
    phone VARCHAR(200) NOT NULL,
    id_card VARCHAR(200) NOT NULL,
    password VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS userinfo_history (
    rid VARCHAR(200) PRIMARY KEY,
    user_id VARCHAR(200) NOT NULL,
    history_event VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS roleinfo (
    rid VARCHAR(200) PRIMARY KEY,
    role_name VARCHAR(200) NOT NULL,
    description VARCHAR(200) NOT NULL
    );

CREATE TABLE IF NOT EXISTS user_role (
    rid VARCHAR(200) PRIMARY KEY,
    userid VARCHAR(200) NOT NULL,
    roleid VARCHAR(200) NOT NULL
    );
