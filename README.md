## 專案啟動方式
匯入test專案並點選箭頭按鈕啟動(intellij)箭頭預設位置在右上方.
## TABLE CREATE SCRIPT
### SYSTEM USER
```
CREATE TABLE system_user(
   user_id UUID NOT NULL PRIMARY KEY,
   account VARCHAR(128) NOT NULL UNIQUE,
   password VARCHAR(128),
   name VARCHAR(128)
);
```
### GOODS
```
CREATE TABLE goods (
   goods_id UUID NOT NULL PRIMARY KEY,
   name VARCHAR(128) NOT NULL,
   cr_user UUID,
   cr_datetime TIMESTAMP DEFAULT NOW(),
   up_user UUID,
   up_datetime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   FOREIGN KEY (cr_user) REFERENCES system_user(user_id),
   FOREIGN KEY (up_user) REFERENCES system_user(user_id)
);
```