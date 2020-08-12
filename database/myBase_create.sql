-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2020-05-03 08:54:47.901

-- tables
-- Table: academicDegrees
CREATE TABLE academicDegrees (
    id integer NOT NULL CONSTRAINT academicDegrees_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL,
    scienceID integer NOT NULL,
    CONSTRAINT academicDegrees_science FOREIGN KEY (scienceID)
    REFERENCES science (id)
);

-- Table: academicRanks
CREATE TABLE academicRanks (
    id integer NOT NULL CONSTRAINT academicRanks_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: departments
CREATE TABLE departments (
    id integer NOT NULL CONSTRAINT departments_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: faculties
CREATE TABLE faculties (
    id integer NOT NULL CONSTRAINT faculties_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: plans
CREATE TABLE plans (
    id integer NOT NULL CONSTRAINT plans_pk PRIMARY KEY,
    name text NOT NULL,
    beginYear integer NOT NULL,
    endYear integer NOT NULL,
    allHours integer NOT NULL,
    realHours integer NOT NULL,
    typesOfWorkID integer NOT NULL,
    professorID integer NOT NULL,
    CONSTRAINT plans_typesOfWork FOREIGN KEY (typesOfWorkID)
    REFERENCES typesOfWork (id),
    CONSTRAINT plans_professors FOREIGN KEY (professorID)
    REFERENCES professors (id)
);

-- Table: positions
CREATE TABLE positions (
    id integer NOT NULL CONSTRAINT positions_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: professors
CREATE TABLE professors (
    id integer NOT NULL CONSTRAINT professors_pk PRIMARY KEY,
    name text NOT NULL,
    lastName text NOT NULL,
    patronymic text NOT NULL,
    dateOfBirth text NOT NULL,
    phoneNumber text NOT NULL,
    address text NOT NULL,
    universityID integer NOT NULL,
    userID integer NOT NULL,
    rankID integer NOT NULL,
    degreeID integer NOT NULL,
    workplaceID integer NOT NULL,
    CONSTRAINT professors_universities FOREIGN KEY (universityID)
    REFERENCES universities (id),
    CONSTRAINT professors_users FOREIGN KEY (userID)
    REFERENCES users (id),
    CONSTRAINT professors_academicRanks FOREIGN KEY (rankID)
    REFERENCES academicRanks (id),
    CONSTRAINT professors_academicDegrees FOREIGN KEY (degreeID)
    REFERENCES academicDegrees (id),
    CONSTRAINT professors_workplace FOREIGN KEY (workplaceID)
    REFERENCES workplace (id)
);

-- Table: science
CREATE TABLE science (
    id integer NOT NULL CONSTRAINT science_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: typesOfWork
CREATE TABLE typesOfWork (
    id integer NOT NULL CONSTRAINT typesOfWork_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: universities
CREATE TABLE universities (
    id integer NOT NULL CONSTRAINT universities_pk PRIMARY KEY,
    name text NOT NULL,
    shortName text NOT NULL
);

-- Table: users
CREATE TABLE users (
    id integer NOT NULL CONSTRAINT users_pk PRIMARY KEY,
    login text NOT NULL,
    password text NOT NULL,
    isAdmin integer NOT NULL
);

-- Table: workplace
CREATE TABLE workplace (
    id integer NOT NULL CONSTRAINT workplace_pk PRIMARY KEY,
    position text NOT NULL,
    startTime text NOT NULL,
    payment integer NOT NULL,
    timeOfWork text NOT NULL,
    facultyID integer NOT NULL,
    departmentID integer NOT NULL,
    positionID integer NOT NULL,
    CONSTRAINT workplace_faculties FOREIGN KEY (facultyID)
    REFERENCES faculties (id),
    CONSTRAINT workplace_departments FOREIGN KEY (departmentID)
    REFERENCES departments (id),
    CONSTRAINT workplace_positions FOREIGN KEY (positionID)
    REFERENCES positions (id)
);

-- End of file.

