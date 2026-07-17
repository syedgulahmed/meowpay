DROP TABLE IF EXISTS transfer, cat;

CREATE TABLE cat (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    treat BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT treat_non_negative CHECK (treat >= 0)
);

CREATE TABLE transfer (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    sender_id UUID NOT NULL REFERENCES cat(id),
    receiver_id UUID NOT NULL REFERENCES cat(id),
    amount BIGINT NOT NULL,
    idempotency_key TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    CONSTRAINT amount_positive CHECK (amount > 0),
    CONSTRAINT sender_receiver_different CHECK (sender_id <> receiver_id),
    CONSTRAINT idempotency_key_unique UNIQUE (idempotency_key)
);

insert into cat (name, treat) values ('Mochi', 120);
insert into cat (name, treat) values ('Pepper', 95);
insert into cat (name, treat) values ('Biscuit', 40);
insert into cat (name, treat) values ('Waffles', 200);
insert into cat (name, treat) values ('Luna', 60);