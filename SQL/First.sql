

Use School;
CREATE TABLE [dbo].[Project] (
    [Pname]   VARCHAR (50)  NULL,
    [Pnumber] INT          NOT NULL PRIMARY KEY,
    [Plocale] VARCHAR (100) NULL,
    [Dnum]    INT           NULL
);
Insert into Project (Pname, Pnumber, Plocale, Dnum) VALUES 
('Sonic Vitamins', 23,'1800 WhyMe ln.', 1 ),
 ('Blue Chew New', 69,'1800 WYA Blvd.', 2 ),
 ('Wind Turbines', 1,'1800 IsThatABirb Ave.', 3 ),
 ('B12 Injectables', 24,'1800 WhyMe ln.', 1 ),
 ('Wonderwaffe', 2,'1800 IsThatABirb Ave.', 3 ),
 ('Blue Sweaters', 70,'1800 WYA Blvd.', 2 ),
 ('An actual Spaceship', 3,'1800 IsThatABirb Ave.', 3 );

SELECT * FROM [dbo].[Project];


