DROP TABLE IF EXISTS APP_USER;
CREATE TABLE IF NOT EXISTS APP_USER
(
    ID                     INT AUTO_INCREMENT PRIMARY KEY,
    USERNAME               VARCHAR(100) UNIQUE NOT NULL,
    PASSWORD               VARCHAR(255)        NOT NULL,
    ROLES                  VARCHAR(255)        NOT NULL,
    IS_ACCOUNT_ENABLED     VARCHAR(1) DEFAULT 'Y',
    IS_ACCOUNT_EXPIRED     VARCHAR(1) DEFAULT 'N',
    IS_ACCOUNT_LOCKED      VARCHAR(1) DEFAULT 'N',
    IS_CREDENTIALS_EXPIRED VARCHAR(1) DEFAULT 'N'
)