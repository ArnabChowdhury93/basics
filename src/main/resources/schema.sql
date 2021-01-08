CREATE TABLE if not exists Lead_Info(
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    mobile VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    location_type ENUM('Country', 'City', 'Zip'),
    location_string VARCHAR(255) NOT NULL,
    status ENUM('Created', 'Contacted'),
    communication VARCHAR(255) DEFAULT NULL,
    UNIQUE(mobile,email)
);

CREATE TABLE if not exists Attendance_Data(
    id INT AUTO_INCREMENT PRIMARY KEY,
    hours VARCHAR(255),
    lead_id INT
);

