-- データベースの作成
CREATE DATABASE security_practice_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

USE security_practice_db;

-- テーブルの作成
CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    login_id VARCHAR(30) UNIQUE NOT NULL,
    login_pass CHAR(60) NOT NULL
);

CREATE TABLE roles (
    id INT PRIMARY KEY,
    name VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE user_roles (
    user_id INT,
    role_id INT,
    PRIMARY KEY(user_id, role_id)
);

-- 初期データの投入（パスワードは「pass」をハッシュ化したもの）
INSERT INTO users VALUES
(1, '山田太郎', 'taro', '$2a$08$JKRLxhgAWKV1ar8tWsEV5OotA34.2bW4dSudEQ3gYCvsnAdKaHShu'),
(2, '佐藤花子', 'hana', '$2a$08$JKRLxhgAWKV1ar8tWsEV5OotA34.2bW4dSudEQ3gYCvsnAdKaHShu'),
(3, '鈴木次郎', 'jiro', '$2a$08$JKRLxhgAWKV1ar8tWsEV5OotA34.2bW4dSudEQ3gYCvsnAdKaHShu');

INSERT INTO roles VALUES 
(1, 'NORMAL'),
(2, 'ADMIN');

INSERT INTO user_roles VALUES
(1, 1),
(1, 2),
(2, 1),
(3, 2);