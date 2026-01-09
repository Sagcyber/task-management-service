CREATE TABLE tasks (
                       id BIGSERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description VARCHAR(1000),
                       category VARCHAR(50) NOT NULL,
                       status VARCHAR(50) NOT NULL,
                       user_id BIGINT NOT NULL,
                       created_at TIMESTAMP NOT NULL,

                       CONSTRAINT fk_task_user
                           FOREIGN KEY (user_id)
                               REFERENCES users (id)
);
