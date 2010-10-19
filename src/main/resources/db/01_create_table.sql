CREATE TABLE preferences (
  id                BIGSERIAL,
  app_id            VARCHAR(100)  NOT NULL,
  user_id           VARCHAR(50)   NOT NULL,
  key               VARCHAR(200)  NOT NULL,
  value             VARCHAR(1024),
  created_date      TIMESTAMP     NOT NULL,
  last_updated_date TIMESTAMP     NOT NULL,
  CONSTRAINT pk_preferences PRIMARY KEY (id)
);

CREATE INDEX idx_pref_appid_userid_key
  ON preferences (app_id, user_id, key);
