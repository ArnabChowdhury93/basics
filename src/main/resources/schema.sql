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

CREATE TABLE if not exists MY_USER(
    ID INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME VARCHAR(255) NOT NULL,
    PASSWORD VARCHAR(255) NOT NULL,
    ROLES VARCHAR(255) NOT NULL,
    ENABLED BOOLEAN NOT NULL
);


--create table if not exists my_authorities (
--    username varchar_ignorecase(50) not null,
--    authority varchar_ignorecase(50) not null,
--    constraint fk_my_authorities_users foreign key(username) references my_users(username)
--);
--create unique index ix_my_auth_username on my_authorities (username,authority);

