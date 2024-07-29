CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(255) NOT NULL,
    UNIQUE KEY uniq_users_user_email (user_email),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE notion_owners (
    id UUID PRIMARY KEY,
    user_id BIGINT NOT NULL,
    owner_type TEXT NULL, -- person, bot
    owner_name TEXT NULL,
    owner_avatar_url TEXT NULL,
    owner_email TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE notion_workspaces (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    owner_id UUID NOT NULL,
    workspace_id UUID NOT NULL,
    workspace_name TEXT NOT NULL,
    workspace_icon TEXT NULL,
    access_token TEXT NOT NULL,
    token_type TEXT NOT NULL,
    bot_id UUID NOT NULL,
    UNIQUE KEY unique_email (email),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES notion_owners(id)
);

CREATE TABLE notion_databases (
    id UUID PRIMARY KEY,
    owner_id BIGINT NOT NULL,
    title TEXT NULL,
    description TEXT NULL,
    icon TEXT NULL,
    cover TEXT NULL,
    url TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES notion_owners(id)
);

CREATE TABLE notion_pages (
    id UUID PRIMARY KEY,
    owner_id BIGINT NOT NULL,
    title TEXT NULL,
    description TEXT NULL,
    icon TEXT NULL,
    cover TEXT NULL,
    url TEXT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES notion_owners(id)
);

CREATE TABLE access_logs (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    action VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
