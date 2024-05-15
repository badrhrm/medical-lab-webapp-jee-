--  DROP DATABASE medical_lab_webapp_jee;
DROP DATABASE IF EXISTS medical_lab_webapp_jee;
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
    email VARCHAR(50),
    password VARCHAR(255),
    phone VARCHAR(10),
    gender VARCHAR(6) CHECK (gender IN ('male', 'female')),
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
    patient_id INT,
    test_id INT,
    day DATE,
    hour VARCHAR(6),
    state VARCHAR(10), -- here it needs to add check(pending,....)
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients_jee(id),
    FOREIGN KEY (test_id) REFERENCES tests_jee(id)
);

INSERT INTO admins_jee (username, email, password) VALUES ('admin','ad@ad.ad', 'adAD@@12');

INSERT INTO `tests_jee` (`id`, `label`, `price`, `result_after`, `created_at`, `updated_at`) VALUES
(1, 'Product A', 19.99, 3, '2024-05-15 21:22:47', '2024-05-15 21:22:47'),
(2, 'Product B', 29.99, 5, '2024-05-15 21:22:47', '2024-05-15 21:22:47'),
(3, 'Product C', 14.99, 20, '2024-05-15 21:22:47', '2024-05-15 21:22:47'),
(4, 'Product D', 39.99, 1, '2024-05-15 21:22:47', '2024-05-15 21:22:47'),
(5, 'Product E', 24.99, 2, '2024-05-15 21:22:47', '2024-05-15 21:22:47');

INSERT INTO `patients_jee` (`id`, `fName`, `lName`, `cin`, `email`, `password`, `phone`, `gender`, `birthdate`, `created_at`, `updated_at`) VALUES
(1, 'John', 'Doe', '123456789', 'johndoe@example.com', 'password123', '1234567890', 'male', '1990-01-01', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(2, 'Jane', 'Smith', '987654321', 'janesmith@example.com', 'password456', '9876543210', 'female', '1985-05-15', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(3, 'Alice', 'Johnson', '456789123', 'alicejohnson@example.com', 'password789', '4567891230', 'female', '1998-09-30', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(4, 'Bob', 'Williams', '789123456', 'bobwilliams@example.com', 'passwordabc', '7891234560', 'male', '1980-11-20', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(5, 'Emily', 'Brown', '321654987', 'emilybrown@example.com', 'passwordxyz', '3216549870', 'female', '1995-07-10', '2024-05-15 21:21:11', '2024-05-15 21:21:11');

INSERT INTO `appointments_jee` (`id`, `patient_id`, `test_id`, `day`, `hour`, `state`, `created_at`, `updated_at`) VALUES
(7, 1, 1, '2024-06-14', '08:30', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02'),
(8, 1, 2, '2024-06-14', '09:30', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02'),
(9, 3, 5, '2024-07-14', '12:30', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02');
