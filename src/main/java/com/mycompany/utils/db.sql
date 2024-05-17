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
    label TEXT,
    price DECIMAL(8,2),
    days_to_get_result INT,
    duration INT,
    description TEXT,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
    updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
CREATE TABLE IF NOT EXISTS appointments_jee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    patient_id INT not null,
    test_id INT not null,
    day DATE,
    hour TIME,
    state CHAR(10) DEFAULT 'pending',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (patient_id) REFERENCES patients_jee(id),
    FOREIGN KEY (test_id) REFERENCES tests_jee(id)
);

INSERT INTO admins_jee (username, email, password) VALUES ('admin','ad@ad.ad', 'adAD@@12');

INSERT INTO tests_jee (`id`, `label`, `price`, `duration`, `days_to_get_result`, `description`, `created_at`, `updated_at`) VALUES
(1, 'Blood Test', 888.00, 60, 3, 'A standard blood test to check various parameters. ', '2024-04-24 23:21:50', '2024-04-27 20:57:29'),
(2, 'Urine Test	', 500.00, 40, 5, 'Analysis of urine sample for various health markers.', '2024-04-25 00:40:07', '2024-04-27 20:58:45'),
(3, 'MRI Scan', 700.00, 54, 6, 'Magnetic Resonance Imaging for detailed body scans.', '2024-04-25 00:42:02', '2024-04-27 21:00:12'),
(4, 'X-Ray', 450.00, 30, 5, 'Imaging technique to visualize internal structures.', '2024-04-25 00:47:00', '2024-04-27 21:01:02'),
(5, 'CT Scan', 350.00, 45, 3, 'Computerized Tomography for detailed imaging.', '2024-04-25 00:56:33', '2024-04-27 21:01:48'),
(6, 'ECG', 460.00, 35, 4, 'Electrocardiogram for heart function assessment.', '2024-04-25 01:03:22', '2024-04-27 21:02:38'),
(7, 'Mammogram', 400.00, 60, 3, 'Breast cancer screening using X-ray images.', '2024-04-25 01:16:37', '2024-04-27 21:04:41'),
(8, 'Pap Smear', 400.00, 15, 3, 'Cervical cancer screening through cell examination.', '2024-04-25 13:38:49', '2024-04-27 21:04:32'),
(12, 'Colonoscopy', 700.00, 2, 7, 'Examination of the colon for polyps and cancer.', '2024-04-27 21:07:01', '2024-04-27 21:07:01'),
(13, 'Prostate Exam', 399.00, 30, 2, 'Screening for prostate cancer in men.', '2024-04-27 21:08:00', '2024-04-27 21:08:00'),
(14, 'Genetic Testing', 1200.00, 70, 14, 'Analysis of DNA for genetic predispositions.', '2024-04-27 21:09:46', '2024-04-27 21:09:46'),
(15, 'Urine Drug Screen', 300.00, 1, 2, 'Screening test to detect the presence of drugs or their metabolites in urine.', '2024-04-27 21:12:09', '2024-04-27 21:12:09'),
(16, 'Hair Follicle Drug Test', 600.00, 1, 5, 'Test that analyzes a small sample of hair to detect the presence of drugs over an extended period, usually up to 90 days.', '2024-04-27 21:13:03', '2024-04-27 21:13:03'),
(17, 'Heavy Metal Panel', 900.00, 60, 5, 'Blood test to detect the presence of heavy metals such as lead, mercury, and arsenic, which can be toxic to the body.', '2024-04-27 21:14:24', '2024-04-27 21:14:24'),
(18, 'Skin Prick Test', 600.00, 30, 1, 'Introduction of small amounts of allergens into the skin to measure allergic reactions.', '2024-04-27 21:15:08', '2024-04-27 21:15:08'),
(19, 'Food Allergy Panel', 400.00, 50, 8, ' Blood test to identify allergies to specific foods by measuring IgE antibodies against common food allergens.', '2024-04-27 21:15:57', '2024-04-27 21:15:57'),
(20, 'Environmental Allergy Panel', 500.00, 60, 2, 'Blood test to identify allergies to environmental allergens such as pollen, dust mites, and pet dander', '2024-04-27 21:16:39', '2024-04-27 22:04:03'),
(21, 'blood test', 500.00, 20, 1, 'blood test', '2024-04-28 02:41:29', '2024-04-28 16:18:20');

INSERT INTO `patients_jee` (`id`, `fName`, `lName`, `cin`, `email`, `password`, `phone`, `gender`, `birthdate`, `created_at`, `updated_at`) VALUES
(1, 'John', 'Doe', '123456789', 'johndoe@example.com', 'password123', '1234567890', 'male', '1990-01-01', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(2, 'Jane', 'Smith', '987654321', 'janesmith@example.com', 'password456', '9876543210', 'female', '1985-05-15', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(3, 'Alice', 'Johnson', '456789123', 'alicejohnson@example.com', 'password789', '4567891230', 'female', '1998-09-30', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(4, 'Bob', 'Williams', '789123456', 'bobwilliams@example.com', 'passwordabc', '7891234560', 'male', '1980-11-20', '2024-05-15 21:21:11', '2024-05-15 21:21:11'),
(5, 'Emily', 'Brown', '321654987', 'emilybrown@example.com', 'passwordxyz', '3216549870', 'female', '1995-07-10', '2024-05-15 21:21:11', '2024-05-15 21:21:11');

INSERT INTO `appointments_jee` (`id`, `patient_id`, `test_id`, `day`, `hour`, `state`, `created_at`, `updated_at`) VALUES
(7, 1, 1, '2024-06-14', '08:30:00', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02'),
(8, 1, 2, '2024-06-14', '09:30:00', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02'),
(9, 3, 5, '2024-07-14', '12:30:00', 'pending', '2024-05-15 21:23:02', '2024-05-15 21:23:02');