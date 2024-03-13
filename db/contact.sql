CREATE TABLE qnp.contact (
	id varchar(20) NOT NULL,
	name varchar(50) NOT NULL,
	email varchar(30) NULL,
	phone varchar(20) NULL,
	address varchar(100) NULL,
	CONSTRAINT contact_PK PRIMARY KEY (id)
)
ENGINE=InnoDB
DEFAULT CHARSET=utf8mb4
COLLATE=utf8mb4_unicode_ci;
