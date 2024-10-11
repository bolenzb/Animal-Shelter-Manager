BEGIN TRANSACTION;

DROP TABLE IF EXISTS pending_users, user_status, users, pets, owner, pet_image, adoption_status, applicant;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	first_time boolean NOT NULL,
	first_name varchar NOT NULL,
	last_name varchar NOT NULL,
	email varchar NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE user_status (
    pending_status_id SERIAL,
    status varchar NOT NULL,
    CONSTRAINT PK_pending_status PRIMARY KEY (pending_status_id)
);

CREATE TABLE pending_users (
    pending_id SERIAL,
    first_name varchar NOT NULL,
    last_name varchar NOT NULL,
    phone_number varchar NOT NULL,
    email varchar NOT NULL,
    birthday date NOT NULL,
    description varchar NOT NULL,
    status int NOT NULL,
    CONSTRAINT PK_pending PRIMARY KEY (pending_id),
    CONSTRAINT FK_pending_users_status FOREIGN KEY (status) REFERENCES user_status (pending_status_id)
);

CREATE TABLE adoption_status (
    adoption_status_id SERIAL PRIMARY KEY,
    status varchar
);

CREATE TABLE pets (
    pet_id SERIAL PRIMARY KEY,
    name varchar NOT NULL,
    gender varchar NOT NULL,
    weight float NOT NULL,
    species varchar NOT NULL,
    breed varchar NOT NULL,
    color varchar NOT NULL,
    birthday date,
    description varchar NOT NULL,
    status int NOT NULL,
    CONSTRAINT FK_adoption_status FOREIGN KEY (status) REFERENCES adoption_status (adoption_status_id)
);

CREATE TABLE owner (
    owner_id SERIAL PRIMARY KEY,
    owner_name varchar,
    pet_id int,
    adoption_date date,
    CONSTRAINT FK_pet FOREIGN KEY (pet_id) REFERENCES pets (pet_id)
);

CREATE TABLE pet_image (
    image_id SERIAL PRIMARY KEY,
    pet_id int,
    name varchar,
    type varchar,
    data BYTEA,
    CONSTRAINT FK_pet FOREIGN KEY (pet_id) REFERENCES pets (pet_id)
);

CREATE TABLE applicant (
    applicant_id SERIAL PRIMARY KEY,
    pet_id int,
    first_name varchar,
    last_name varchar,
    email varchar,
    phone_number varchar,
    applicant_status int,
    response_one boolean,
    response_two boolean,
    response_three boolean,
    response_four boolean,
    CONSTRAINT FK_applicant_user_status FOREIGN KEY (applicant_status) REFERENCES user_status (pending_status_id)

);

COMMIT TRANSACTION;
