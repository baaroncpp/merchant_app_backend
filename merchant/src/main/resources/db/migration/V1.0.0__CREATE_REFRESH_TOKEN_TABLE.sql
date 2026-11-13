CREATE TABLE IF NOT EXISTS t_refresh_token (
    id BIGSERIAL PRIMARY KEY,
    created_on TIMESTAMP DEFAULT now(),
    modified_on TIMESTAMP,
    token_hash VARCHAR(64) NOT NULL,
    family_id UUID NOT NULL,
    user_id BIGINT NOT NULL,
    expiry_date TIMESTAMP NOT NULL,
    used_on TIMESTAMP,
    revoked_on TIMESTAMP,
    status VARCHAR(50),
    CONSTRAINT uk_refresh_token_hash UNIQUE (token_hash)
);

CREATE INDEX IF NOT EXISTS ix_refresh_token_family ON t_refresh_token (family_id);
CREATE INDEX IF NOT EXISTS ix_refresh_token_user ON t_refresh_token (user_id);
