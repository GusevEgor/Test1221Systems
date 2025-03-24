create table if not exists users
(
    id BIGSERIAL,
    username VARCHAR NOT NULL,
    email VARCHAR NOT NULL,
    age BIGINT CHECK (120 >= age AND age > 0),
    weight BIGINT CHECK (500 >= weight AND weight > 0),
    growth BIGINT CHECK (300 >= growth AND growth > 0),
    target VARCHAR NOT NULL,
    daily_calorie_intake BIGINT CHECK (daily_calorie_intake > 0),
    CONSTRAINT pk_users primary key (id)
    );


create table if not exists foods
(
    id BIGSERIAL,
    title VARCHAR NOT NULL,
    caloric BIGINT CHECK (caloric > 0),
    proteins BIGINT CHECK (proteins > 0),
    fats BIGINT CHECK (fats > 0),
    carbohydrates BIGINT CHECK (carbohydrates > 0),
    CONSTRAINT pk_foods primary key (id)
    );


create table if not exists food_intakes
(
    id BIGSERIAL,
    date_time DATE NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT pk_food_intake primary key (id),
    CONSTRAINT fk_food_intakes_user foreign key (user_id) references users (id) ON DELETE CASCADE
    );


create table if not exists food_items
(
    id BIGSERIAL,
    number BIGINT NOT NULL,
    food_id BIGINT NOT NULL,
    food_intake_id BIGINT NOT NULL,
    CONSTRAINT pk_food_item primary key (id),
    CONSTRAINT fk_food_item_food foreign key (food_id) references foods (id) ON DELETE CASCADE,
    CONSTRAINT fk_food_item_food_intake foreign key (food_intake_id) references food_intakes (id) ON DELETE CASCADE
    );

