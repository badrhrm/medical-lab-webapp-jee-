--  DROP DATABASE medical_lab_webapp_jee;
CREATE DATABASE IF NOT EXISTS medical_lab_webapp_jee;
USE medical_lab_webapp_jee;

CREATE TABLE IF NOT EXISTS admins_jee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL UNIQUE,
    email VARCHAR(50) UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS patients_jee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fName VARCHAR(25) NOT NULL,
    lName VARCHAR(25) NOT NULL,
    cin VARCHAR(12) NOT NULL UNIQUE,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL UNIQUE, -- added password so patient can authenticate 
    phone VARCHAR(10),
    gender VARCHAR(6) CHECK (gender IN ('Male', 'Female')),
    birthdate DATE,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE patients_tokens_jee (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_patient INT,
    token VARCHAR(255) NOT NULL UNIQUE,
    token_type ENUM('reset_password', 'email_verification') NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    expires_at DATETIME NOT NULL
);

CREATE TABLE IF NOT EXISTS tests_jee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    label VARCHAR(25) NOT NULL UNIQUE,
    price DECIMAL(8,2),
    result_after INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS appointments_jee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_patient INT,
    day DATE,
    hour VARCHAR(6),
    state VARCHAR(10), -- here it needs to add check(pending,....)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_patient) REFERENCES patients_jee(id)
);

CREATE TABLE IF NOT EXISTS appointment_tests_jee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_appointment INT,
    id_test INT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (id_appointment) REFERENCES appointments_jee(id),
    FOREIGN KEY (id_test) REFERENCES tests_jee(id)
);

INSERT INTO admins_jee (username, email, password) VALUES ('admin','ad@ad.ad', 'adAD@@12');
