----------------------------------------------------
-- Inserts
----------------------------------------------------

-- Table: Team
INSERT INTO team (name, constructor, motorcycle, country)
VALUES ('Aprilia Racing', 'APRILIA', 'Aprilia RS-GP25', 'ITALY'),
       ('Ducati Lenovo Team', 'DUCATI', 'Ducati Desmosedici GP25', 'ITALY'),
       ('Honda HRC Castrol', 'HONDA', 'Honda RC213V', 'JAPAN'),
       ('Red Bull KTM Factory Racing', 'KTM', 'KTM RC16', 'AUSTRIA'),
       ('Monster Energy Yamaha MotoGP', 'YAMAHA', 'Yamaha YZR-M1', 'JAPAN'),
       ('Trackhouse MotoGP Team', 'APRILIA', 'Aprilia RS-GP', 'USA'),
       ('Pertamina Enduro VR46 Racing Team', 'DUCATI', 'Ducati Desmosedici GP24', 'ITALY'),
       ('Gresini Racing MotoGP', 'DUCATI', 'Ducati Desmosedici GP24', 'ITALY'),
       ('LCR Honda Castrol', 'HONDA', 'Honda RC213V', 'MONACO'),
       ('LCR Honda IDEMITSU', 'HONDA', 'Honda RC213V', 'MONACO'),
       ('Red Bull KTM Tech3', 'KTM', 'KTM RC16', 'FRANCE'),
       ('Prima Pramac Yamaha', 'YAMAHA', 'Yamaha YZR-M1', 'ITALY');

-- Table: Rider
INSERT INTO rider (name, country, number, championships, team)
VALUES
    -- Factory Teams
    -- Aprilia Racing (using Aprilia RS-GP25)
    ('Jorge Martín', 'SPAIN', 6, 1, (SELECT id FROM team WHERE name = 'Aprilia Racing')),
    ('Lorenzo Savadori', 'ITALY', 32, 0, (SELECT id FROM team WHERE name = 'Aprilia Racing')),
    ('Marco Bezzecchi', 'ITALY', 72, 0, (SELECT id FROM team WHERE name = 'Aprilia Racing')),

    -- Ducati Lenovo Team (using Ducati Desmosedici GP25)
    ('Francesco Bagnaia', 'ITALY', 63, 2, (SELECT id FROM team WHERE name = 'Ducati Lenovo Team')),
    ('Marc Márquez', 'SPAIN', 93, 6, (SELECT id FROM team WHERE name = 'Ducati Lenovo Team')),

    -- Honda HRC Castrol (using Honda RC213V)
    ('Luca Marini', 'ITALY', 10, 0, (SELECT id FROM team WHERE name = 'Honda HRC Castrol')),
    ('Joan Mir', 'SPAIN', 36, 1, (SELECT id FROM team WHERE name = 'Honda HRC Castrol')),

    -- Red Bull KTM Factory Racing (using KTM RC16)
    ('Brad Binder', 'SOUTH_AFRICA', 33, 0, (SELECT id FROM team WHERE name = 'Red Bull KTM Factory Racing')),
    ('Pedro Acosta', 'SPAIN', 37, 0, (SELECT id FROM team WHERE name = 'Red Bull KTM Factory Racing')),

    -- Monster Energy Yamaha MotoGP (using Yamaha YZR-M1)
    ('Fabio Quartararo', 'FRANCE', 20, 1, (SELECT id FROM team WHERE name = 'Monster Energy Yamaha MotoGP')),
    ('Álex Rins', 'SPAIN', 42, 0, (SELECT id FROM team WHERE name = 'Monster Energy Yamaha MotoGP')),

    -- Independent Teams
    -- Trackhouse MotoGP Team (using Aprilia RS-GP)
    ('Raúl Fernández', 'SPAIN', 25, 0, (SELECT id FROM team WHERE name = 'Trackhouse MotoGP Team')),
    ('Ai Ogura', 'JAPAN', 79, 0, (SELECT id FROM team WHERE name = 'Trackhouse MotoGP Team')),

    -- Pertamina Enduro VR46 Racing Team (using Ducati Desmosedici GP24)
    ('Franco Morbidelli', 'ITALY', 21, 0, (SELECT id FROM team WHERE name = 'Pertamina Enduro VR46 Racing Team')),
    ('Fabio Di Giannantonio', 'ITALY', 49, 0, (SELECT id FROM team WHERE name = 'Pertamina Enduro VR46 Racing Team')),

    -- Gresini Racing MotoGP (using Ducati Desmosedici GP24)
    ('Fermín Aldeguer', 'SPAIN', 54, 0, (SELECT id FROM team WHERE name = 'Gresini Racing MotoGP')),
    ('Álex Márquez', 'SPAIN', 73, 0, (SELECT id FROM team WHERE name = 'Gresini Racing MotoGP')),

    -- LCR Honda Castrol (using Honda RC213V)
    ('Johann Zarco', 'FRANCE', 5, 0, (SELECT id FROM team WHERE name = 'LCR Honda Castrol')),

    -- LCR Honda IDEMITSU
    ('Somkiat Chantra', 'THAILAND', 35, 0, (SELECT id FROM team WHERE name = 'LCR Honda IDEMITSU')),

    -- Red Bull KTM Tech3 (using KTM RC16)
    ('Maverick Viñales', 'SPAIN', 12, 0, (SELECT id FROM team WHERE name = 'Red Bull KTM Tech3')),
    ('Enea Bastianini', 'ITALY', 23, 0, (SELECT id FROM team WHERE name = 'Red Bull KTM Tech3')),

    -- Prima Pramac Yamaha (using Yamaha YZR-M1)
    ('Jack Miller', 'AUSTRALIA', 43, 0, (SELECT id FROM team WHERE name = 'Prima Pramac Yamaha')),
    ('Miguel Oliveira', 'PORTUGAL', 88, 0, (SELECT id FROM team WHERE name = 'Prima Pramac Yamaha'));
