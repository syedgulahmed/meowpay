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