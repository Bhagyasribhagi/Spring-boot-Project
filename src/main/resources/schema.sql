CREATE TABLE IF NOT EXISTS manager (
    manager_id UUID primary key,
    full_name VARCHAR(255) NOT NULL
);

--CREATE TABLE IF NOT EXISTS users (
--    user_id UUID primary key,
--    full_name VARCHAR(255) NOT NULL,
--    mob_num VARCHAR(15) NOT NULL,
--    pan_num VARCHAR(10) UNIQUE NOT NULL,
--    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
--    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
--    is_active BOOLEAN DEFAULT TRUE,
--    manager_id UUID,
--    FOREIGN KEY (manager_id) REFERENCES manager(manager_id)
--);

