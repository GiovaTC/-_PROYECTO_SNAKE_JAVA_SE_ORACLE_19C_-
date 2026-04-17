--// =============================
--// 5. SCRIPT ORACLE 19c
--// =============================
--/*
CREATE TABLE SNAKE_SCORE (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    PLAYER_NAME VARCHAR2(100),
    SCORE NUMBER,
    FECHA DATE
);

COMMIT;