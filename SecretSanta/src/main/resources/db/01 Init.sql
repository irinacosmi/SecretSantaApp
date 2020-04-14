create database secret_santa;

use secret_santa;

create table employer(
   id INT NOT NULL AUTO_INCREMENT,
   name VARCHAR(255) NOT NULL,
   wish VARCHAR(255) NOT NULL,
   details TEXT NOT NULL,
   image LONGBLOB NOT NULL,
   extract BOOLEAN,
   PRIMARY KEY ( id )
);

create table pair(
   id INT NOT NULL AUTO_INCREMENT,
   from_id INT NOT NULL references employer(id),
   to_id INT NOT NULL references employer(id),
   PRIMARY KEY ( id )
);