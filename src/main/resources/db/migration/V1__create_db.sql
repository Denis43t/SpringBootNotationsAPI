CREATE SEQUENCE notes_id_seq
    START WITH 1
    INCREMENT BY 1;


CREATE TABLE notes (
                      id BIGINT DEFAULT nextval('notes_id_seq') PRIMARY KEY,
                      title VARCHAR(255),
                      content TEXT
);

ALTER SEQUENCE notes_id_seq OWNED BY notes.id;
