
-- Create database
CREATE DATABASE temp;

-- Connect to the temp database
\c temp

-- Create student table
CREATE TABLE student (
    rno INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    class VARCHAR(50),
    admission_year INTEGER NOT NULL,
    PRIMARY KEY (rno)
);

-- Create event table
CREATE TABLE event (
    id INTEGER NOT NULL,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(50),
    host VARCHAR(100),
    organizer VARCHAR(100),
    date VARCHAR(10) NOT NULL,
    time VARCHAR(8) NOT NULL,
    venue VARCHAR(100),
    PRIMARY KEY (id)
);

-- Create student_event table
CREATE TABLE student_event (
    student_rno INTEGER NOT NULL,
    event_id INTEGER NOT NULL,
    PRIMARY KEY (student_rno, event_id),
    FOREIGN KEY (student_rno) REFERENCES student(rno) ON DELETE CASCADE,
    FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
);

-- Insert entries into student table
INSERT INTO student (rno, name, phone, class, admission_year) VALUES
(101, 'Aarav Sharma', '9876543210', 'BSc Computer Science', 2023),
(102, 'Priya Verma', '8765432109', 'BSc Physics', 2022),
(103, 'Rohan Desai', '7654321098', 'BA English', 2023),
(104, 'Meera Joshi', '6543210987', 'BCom', 2024),
(105, 'Aditya Kulkarni', '5432109876', 'BBA', 2023),
(106, 'Sneha Patil', '4321098765', 'BSc Mathematics', 2022),
(107, 'Vikram Rao', '3210987654', 'BA History', 2023),
(108, 'Kavya Iyer', '2109876543', 'BSc Chemistry', 2024),
(109, 'Yash Singh', '1098765432', 'BSc Computer Science', 2022);

-- Insert entries into event table
INSERT INTO event (id, name, category, host, organizer, date, time, venue) VALUES
(1, 'Science Fair', 'Academic Events', 'Dr. Smith', 'Physics Department', '2025-06-01', '10:00 AM', 'Auditorium A'),
(2, 'Coding Hackathon', 'Competitions', 'Mr. Johnson', 'Computer Club', '2025-06-05', '09:00 AM', 'Lab 101'),
(3, 'Annual Dance Show', 'Performances', 'Ms. Emily', 'Dance Club', '2025-06-10', '07:00 PM', 'Main Stage'),
(4, 'Art Exhibition', 'Exhibitions', 'Ms. Rose', 'Art Department', '2025-06-15', '11:00 AM', 'Gallery Hall'),
(5, 'Leadership Workshop', 'Workshops & Seminars', 'Prof. Alan', 'Leadership Cell', '2025-06-20', '01:00 PM', 'Seminar Room 3'),
(6, 'Football Tournament', 'Sports & Fitness', 'Mr. Mike', 'Sports Committee', '2025-06-25', '03:00 PM', 'Sports Ground'),
(7, 'Cultural Fest', 'Cultural Events', 'Ms. Sofia', 'Cultural Club', '2025-06-30', '05:00 PM', 'Open Grounds'),
(8, 'Blood Donation Camp', 'Social Impact & Awareness', 'Dr. Patel', 'Health Club', '2025-07-05', '09:00 AM', 'Community Center'),
(9, 'Startup Meetup', 'Networking & Meetups', 'Mr. David', 'Entrepreneurship Cell', '2025-07-10', '06:00 PM', 'Conference Hall');

-- Insert entries into student_event table
INSERT INTO student_event (student_rno, event_id) VALUES
(101, 2),
(101, 5),
(102, 1),
(102, 4),
(103, 3),
(104, 6),
(105, 7),
(106, 8),
(107, 9),
(108, 1),
(109, 2),
(109, 6);
