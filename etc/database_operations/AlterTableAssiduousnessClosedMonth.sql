ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN BALANCE bigint(20) DEFAULT NULL;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN SATURDAY_BALANCE bigint(20) DEFAULT NULL;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN SUNDAY_BALANCE bigint(20) DEFAULT NULL;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN HOLIDAY_BALANCE bigint(20) DEFAULT NULL;
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN VACATIONS DOUBLE NOT NULL default '0';
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN TOLERANCE DOUBLE NOT NULL default '0';
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN ARTICLE17 DOUBLE NOT NULL default '0';
ALTER TABLE ASSIDUOUSNESS_CLOSED_MONTH ADD COLUMN ARTICLE66 DOUBLE NOT NULL default '0';