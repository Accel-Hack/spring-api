INSERT INTO API_APPLICATION.`USER`(`ID`, `USERNAME`, `PASSWORD`, `ACTOR`, `CREATED_BY`, `UPDATED_BY`)
VALUES ('dd48ef60-e4e2-11ed-b5ea-0242ac120002', 'system',
        '$2a$12$9UmVR8I34kgzRl4A2q0sEOsTlyVUlw8TvM9wnfPl9rdwB7Z1aNQhS', 1, 'INIT', 'INIT'),
       ('dd48f2bc-e4e2-11ed-b5ea-0242ac120002', 'manager',
        '$2a$12$9UmVR8I34kgzRl4A2q0sEOsTlyVUlw8TvM9wnfPl9rdwB7Z1aNQhS', 2, 'INIT', 'INIT'),
       ('dd48f762-e4e2-11ed-b5ea-0242ac120002', 'user',
        '$2a$12$9UmVR8I34kgzRl4A2q0sEOsTlyVUlw8TvM9wnfPl9rdwB7Z1aNQhS', 3, 'INIT', 'INIT');

INSERT INTO YOUR_APPLICATION.SAMPLE(ID, NAME, BIRTHDAY, IS_JAPANESE)
VALUES ('2e40b651-c32e-4dab-85bd-5a2a81f58c58', 'kawamura1', '1994-09-14', true),
       ('7d937a5e-7fa3-4676-949c-6366e988d830', 'kawamura2', '1994-10-12', true),
       ('ee4d8f69-7b37-45b2-ba55-08a23e429ec3', 'kawamura3', '1994-11-08', true),
       ('ffda86bf-ee4d-443b-9dcd-5ec9881209b3', 'kawamura4', '1994-11-08', true),
       ('f32b76d3-6972-4b62-b19c-1d31bfc88e54', 'kawamura5', '1994-12-12', true);
