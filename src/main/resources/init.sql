CREATE TABLE IF NOT EXISTS cinema (
    id UUID PRIMARY KEY,
    name TEXT);
CREATE TABLE IF NOT EXISTS hall (
    id UUID PRIMARY KEY,
    name TEXT,
    cinema_id UUID,
    foreign key (cinema_id) references cinema(id));
CREATE TABLE IF NOT EXISTS seat (
    id UUID PRIMARY KEY,
    line INTEGER,
    place INTEGER,
    taken BOOLEAN,
    hall_id UUID,
    foreign key (hall_id) references hall(id));
CREATE TABLE IF NOT EXISTS reservation (
    id UUID PRIMARY KEY,
    name TEXT,
    date TIMESTAMP WITHOUT TIME ZONE);
CREATE TABLE IF NOT EXISTS reservation_seats (
    reservation_id UUID,
    seat_id UUID,
    PRIMARY KEY (reservation_id, seat_id),
    foreign key (reservation_id) references reservation(id),
    foreign key (seat_id) references seat(id)
);

INSERT INTO cinema VALUES ('1a7f645e-f08e-469e-979e-4e6c2678b88a', 'AURA');
INSERT INTO hall VALUES ('7a7f645e-f08e-469e-979e-4e6c2678b88a', 'BLACK', '1a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO hall VALUES ('2a7f645e-f08e-469e-979e-4e6c2678b88a', '3D', '1a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('84f09724-a429-4d91-bdde-0286a41565a4', 1, 1, true, '7a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('85f09724-a429-4d91-bdde-0286a41565a4', 1, 2, true, '7a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('86f09724-a429-4d91-bdde-0286a41565a4', 2, 1, false, '7a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('88f09724-a429-4d91-bdde-0286a41565a4', 2, 2, false, '7a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('14f09724-a429-4d91-bdde-0286a41565a4', 1, 1, false, '2a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('24f09724-a429-4d91-bdde-0286a41565a4', 1, 2, false, '2a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('34f09724-a429-4d91-bdde-0286a41565a4', 2, 1, false, '2a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('44f09724-a429-4d91-bdde-0286a41565a4', 2, 2, false, '2a7f645e-f08e-469e-979e-4e6c2678b88a');

INSERT INTO cinema VALUES ('3a7f645e-f08e-469e-979e-4e6c2678b88a', 'ROYAL');
INSERT INTO hall VALUES ('4a7f645e-f08e-469e-979e-4e6c2678b88a', 'BRITAIN', '3a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO hall VALUES ('5a7f645e-f08e-469e-979e-4e6c2678b88a', 'VIP', '3a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('84f09724-a429-4d91-bdde-0286a41565a1', 1, 1, false, '4a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('85f09724-a429-4d91-bdde-0286a41565a2', 1, 2, false, '4a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('86f09724-a429-4d91-bdde-0286a41565a3', 2, 1, false, '4a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('87f09724-a429-4d91-bdde-0286a41565a4', 2, 2, false, '4a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('14f09724-a429-4d91-bdde-0286a41565a5', 1, 1, false, '5a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('24f09724-a429-4d91-bdde-0286a41565a6', 1, 2, false, '5a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('34f09724-a429-4d91-bdde-0286a41565a7', 2, 1, false, '5a7f645e-f08e-469e-979e-4e6c2678b88a');
INSERT INTO seat VALUES ('44f09724-a429-4d91-bdde-0286a41565a8', 2, 2, false, '5a7f645e-f08e-469e-979e-4e6c2678b88a');

INSERT INTO reservation VALUES ('42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c', 'ALEXANDRA', '2020-03-22 18:13:56');
INSERT INTO reservation_seats VALUES ('42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c', '84f09724-a429-4d91-bdde-0286a41565a4');
INSERT INTO reservation_seats VALUES ('42e0c9fc-7ab8-42ca-b1fa-8fd137c66f1c', '85f09724-a429-4d91-bdde-0286a41565a4');


