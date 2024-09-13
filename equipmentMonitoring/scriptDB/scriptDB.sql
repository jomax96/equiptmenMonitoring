CREATE TABLE Alert (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    tracking_number NVARCHAR(100) NOT NULL,
    created_at DATETIME NOT NULL DEFAULT GETDATE(),
    is_finished BIT NOT NULL DEFAULT 0
);
CREATE TABLE EquipmentType (
    id INT PRIMARY KEY IDENTITY(1,1),
    name NVARCHAR(100) NOT NULL,
    serial_length INT NOT NULL,
    mac_length INT NOT NULL
);
CREATE TABLE Equipment (
    id INT PRIMARY KEY IDENTITY(1,1),
    equipment_type_id INT FOREIGN KEY REFERENCES EquipmentType(id),
    serial NVARCHAR(100) NOT NULL,
    mac NVARCHAR(100) NOT NULL,
    observations NVARCHAR(255) NULL,
    UNIQUE (serial), -- The serial must be unique
    UNIQUE (mac) -- The MAC must be unique
);
CREATE TABLE EquipmentAssignment (
    id INT PRIMARY KEY IDENTITY(1,1),
    alert_id INT FOREIGN KEY REFERENCES Alert(id),
    equipment_id INT FOREIGN KEY REFERENCES Equipment(id),
    is_assigned BIT NOT NULL DEFAULT 0 -- Indicates if the equipment has been assigned
);
CREATE TABLE Pickup (
    id INT PRIMARY KEY IDENTITY(1,1),
    alert_id INT FOREIGN KEY REFERENCES Alert(id),
    equipment_id INT FOREIGN KEY REFERENCES Equipment(id),
    picked_up_at DATETIME NOT NULL DEFAULT GETDATE(),
    is_picked_up BIT NOT NULL DEFAULT 0
);
