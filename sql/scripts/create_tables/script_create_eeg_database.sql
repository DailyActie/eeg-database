/*
CREATED		16.12.2008
MODIFIED	10.11.2010
PROJECT		EEG-DATABASE
MODEL
COMPANY		ZCU
AUTHOR		Petr Bruha
VERSION
DATABASE        ORACLE 10G
*/


-- CREATE TYPES SECTION


-- CREATE TABLES SECTION


CREATE TABLE "EXPERIMENT" (
	"EXPERIMENT_ID" INTEGER NOT NULL ,
	"OWNER_ID" INTEGER NOT NULL ,
	"SUBJECT_PERSON_ID" INTEGER NOT NULL ,
	"SCENARIO_ID" INTEGER NOT NULL ,
	"WEATHER_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"START_TIME" DATE,
	"END_TIME" DATE,
	"TEMPERATURE" SMALLINT,
	"WEATHERNOTE" VARCHAR2 (255),
	"PRIVATE" INTEGER DEFAULT 0 NOT NULL ,
PRIMARY KEY ("EXPERIMENT_ID")
)
;

CREATE TABLE "PERSON" (
	"PERSON_ID" INTEGER NOT NULL ,
	"DEFAULT_GROUP_ID" INTEGER,
	"GIVENNAME" VARCHAR2 (50),
	"SURNAME" VARCHAR2 (50) NOT NULL ,
	"DATE_OF_BIRTH" DATE,
	"GENDER" CHAR (1) NOT NULL  CONSTRAINT "FILLED_GENDER" CHECK (GENDER = 'M' OR GENDER='F' ) ,
	"EMAIL" VARCHAR2 (50),
	"PHONE_NUMBER" VARCHAR2 (20),
	"NOTE" VARCHAR2 (255),
	"USERNAME" VARCHAR2 (50) UNIQUE ,
	"PASSWORD" VARCHAR2 (50),
	"AUTHORITY" VARCHAR2 (50),
	"CONFIRMED" INTEGER DEFAULT 1,
PRIMARY KEY ("PERSON_ID")
)
;

CREATE TABLE "COEXPERIMENTER_REL" (
	"PERSON_ID" INTEGER NOT NULL ,
	"EXPERIMENT_ID" INTEGER NOT NULL ,
PRIMARY KEY ("PERSON_ID","EXPERIMENT_ID")
)
;

CREATE TABLE "SCENARIO" (
	"SCENARIO_ID" INTEGER NOT NULL ,
	"OWNER_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"TITLE" VARCHAR2 (40) CONSTRAINT "UNIQUE_SCENARIO_TITLE" UNIQUE ,
	"SCENARIO_LENGTH" SMALLINT,
	"SCENARIO_XML" CLOB,
	"DESCRIPTION" VARCHAR2 (255),
	"PRIVATE" INTEGER DEFAULT 0 NOT NULL ,
PRIMARY KEY ("SCENARIO_ID")
)
;

CREATE TABLE "HARDWARE" (
	"HARDWARE_ID" INTEGER NOT NULL ,
	"TITLE" VARCHAR2 (50) NOT NULL  CONSTRAINT "UNIQUE_HARDWARE_TITLE" UNIQUE ,
	"TYPE" VARCHAR2 (30) NOT NULL  CONSTRAINT "UNIQUE_HARDWARE_TYPE" UNIQUE ,
	"DESCRIPTION" VARCHAR2 (30) CONSTRAINT "UNIQUE_HARDWARE_DESCRIPTION" UNIQUE ,
PRIMARY KEY ("HARDWARE_ID")
)
;

CREATE TABLE "HARDWARE_USAGE_REL" (
	"HARDWARE_ID" INTEGER NOT NULL ,
	"EXPERIMENT_ID" INTEGER NOT NULL ,
PRIMARY KEY ("HARDWARE_ID","EXPERIMENT_ID")
)
;

CREATE TABLE "DATA_FILE" (
	"DATA_FILE_ID" INTEGER NOT NULL ,
	"SAMPLING_RATE" FLOAT NOT NULL ,
	"FILE_CONTENT" BLOB NOT NULL ,
	"EXPERIMENT_ID" INTEGER NOT NULL ,
	"MIMETYPE" VARCHAR2 (40) NOT NULL ,
	"FILENAME" VARCHAR2 (80) NOT NULL ,
PRIMARY KEY ("DATA_FILE_ID")
)
;

CREATE TABLE "FILE_METADATA_PARAM_VAL" (
	"FILE_METADATA_PARAM_DEF_ID" INTEGER NOT NULL ,
	"DATA_FILE_ID" INTEGER NOT NULL ,
	"METADATA_VALUE" NVARCHAR2(30) NOT NULL ,
PRIMARY KEY ("FILE_METADATA_PARAM_DEF_ID","DATA_FILE_ID")
)
;

CREATE TABLE "WEATHER" (
	"WEATHER_ID" INTEGER NOT NULL ,
	"DESCRIPTION" VARCHAR2 (30) CONSTRAINT "UNIQUE_WEATHER_DESCRIPTION" UNIQUE ,
	"TITLE" VARCHAR2 (30) NOT NULL  CONSTRAINT "UNIQUE_WHEATER_TITLE" UNIQUE ,
PRIMARY KEY ("WEATHER_ID")
)
;

CREATE TABLE "HEARING_IMPAIRMENT" (
	"HEARING_IMPAIRMENT_ID" INTEGER NOT NULL ,
	"DESCRIPTION" VARCHAR2 (30) NOT NULL  CONSTRAINT "UNIQUE_HARING_DEFECT_DESCRIPTI" UNIQUE ,
PRIMARY KEY ("HEARING_IMPAIRMENT_ID")
)
;

CREATE TABLE "VISUAL_IMPAIRMENT" (
	"VISUAL_IMPAIRMENT_ID" INTEGER NOT NULL ,
	"DESCRIPTION" VARCHAR2 (30) NOT NULL  CONSTRAINT "UNIQUE_EYES_DEFECT_DESCRIPTION" UNIQUE ,
PRIMARY KEY ("VISUAL_IMPAIRMENT_ID")
)
;

CREATE TABLE "HEARING_IMPAIRMENT_REL" (
	"PERSON_ID" INTEGER NOT NULL ,
	"HEARING_IMPAIRMENT_ID" INTEGER NOT NULL ,
PRIMARY KEY ("PERSON_ID","HEARING_IMPAIRMENT_ID")
)
;

CREATE TABLE "VISUAL_IMPAIRMENT_REL" (
	"VISUAL_IMPAIRMENT_ID" INTEGER NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
PRIMARY KEY ("VISUAL_IMPAIRMENT_ID","PERSON_ID")
)
;

CREATE TABLE "PERSON_OPT_PARAM_DEF" (
	"PERSON_OPT_PARAM_DEF_ID" INTEGER NOT NULL ,
	"PARAM_NAME" VARCHAR2 (30) NOT NULL ,
	"PARAM_DATA_TYPE" VARCHAR2 (20) NOT NULL ,
PRIMARY KEY ("PERSON_OPT_PARAM_DEF_ID")
)
;

CREATE TABLE "PERSON_OPT_PARAM_VAL" (
	"PARAM_VALUE" VARCHAR2 (30) NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
	"PERSON_OPT_PARAM_DEF_ID" INTEGER NOT NULL ,
PRIMARY KEY ("PERSON_ID","PERSON_OPT_PARAM_DEF_ID")
)
;

CREATE TABLE "EXPERIMENT_OPT_PARAM_DEF" (
	"EXPERIMENT_OPT_PARAM_DEF_ID" INTEGER NOT NULL ,
	"PARAM_NAME" VARCHAR2 (30) NOT NULL ,
	"PARAM_DATA_TYPE" VARCHAR2 (20) NOT NULL ,
PRIMARY KEY ("EXPERIMENT_OPT_PARAM_DEF_ID")
)
;

CREATE TABLE "EXPERIMENT_OPT_PARAM_VAL" (
	"PARAM_VALUE" VARCHAR2 (30) NOT NULL ,
	"EXPERIMENT_ID" INTEGER NOT NULL ,
	"EXPERIMENT_OPT_PARAM_DEF_ID" INTEGER NOT NULL ,
PRIMARY KEY ("EXPERIMENT_ID","EXPERIMENT_OPT_PARAM_DEF_ID")
)
;

CREATE TABLE "FILE_METADATA_PARAM_DEF" (
	"PARAM_NAME" VARCHAR2 (30) NOT NULL ,
	"FILE_METADATA_PARAM_DEF_ID" INTEGER NOT NULL ,
	"PARAM_DATA_TYPE" VARCHAR2 (20) NOT NULL ,
PRIMARY KEY ("FILE_METADATA_PARAM_DEF_ID")
)
;

CREATE TABLE "RESEARCH_GROUP" (
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"OWNER_ID" INTEGER NOT NULL ,
	"TITLE" VARCHAR2 (100) NOT NULL ,
	"DESCRIPTION" VARCHAR2 (250) NOT NULL ,
PRIMARY KEY ("RESEARCH_GROUP_ID")
)
;

CREATE TABLE "RESEARCH_GROUP_MEMBERSHIP" (
	"PERSON_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"AUTHORITY" VARCHAR2 (30) NOT NULL ,
PRIMARY KEY ("PERSON_ID","RESEARCH_GROUP_ID")
)
;

CREATE TABLE "HISTORY" (
	"HISTORY_ID" INTEGER NOT NULL ,
	"EXPERIMENT_ID" INTEGER,
	"SCENARIO_ID" INTEGER,
	"PERSON_ID" INTEGER NOT NULL ,
	"DATA_FILE_ID" INTEGER,
	"DATE_OF_DOWNLOAD" DATE DEFAULT SYSDATE NOT NULL ,
PRIMARY KEY ("HISTORY_ID")
)
;

CREATE TABLE "ARTICLES" (
	"ARTICLE_ID" INTEGER NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER,
	"TEXT" CLOB NOT NULL ,
	"TIME" DATE DEFAULT SYSDATE NOT NULL ,
	"TITLE" VARCHAR2 (150) NOT NULL ,
PRIMARY KEY ("ARTICLE_ID")
)
;

CREATE TABLE "ARTICLES_COMMENTS" (
	"COMMENT_ID" INTEGER NOT NULL ,
	"ARTICLE_ID" INTEGER NOT NULL ,
	"PARENT_ID" INTEGER NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
	"TEXT" CLOB NOT NULL ,
	"TIME" DATE DEFAULT SYSDATE NOT NULL ,
PRIMARY KEY ("COMMENT_ID")
)
;

CREATE TABLE "GROUP_PERMISSION_REQUEST" (
	"REQUEST_ID" INTEGER NOT NULL ,
	"REQUESTED_PERMISSION" VARCHAR2 (20) NOT NULL ,
	"GRANTED" NUMBER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
PRIMARY KEY ("REQUEST_ID")
)
;


-- CREATE ALTERNATE KEYS SECTION


-- CREATE FOREIGN KEYS SECTION

ALTER TABLE "COEXPERIMENTER_REL" ADD  FOREIGN KEY ("EXPERIMENT_ID") REFERENCES "EXPERIMENT" ("EXPERIMENT_ID")
;

ALTER TABLE "HARDWARE_USAGE_REL" ADD  FOREIGN KEY ("EXPERIMENT_ID") REFERENCES "EXPERIMENT" ("EXPERIMENT_ID")
;

ALTER TABLE "DATA_FILE" ADD  FOREIGN KEY ("EXPERIMENT_ID") REFERENCES "EXPERIMENT" ("EXPERIMENT_ID")
;

ALTER TABLE "EXPERIMENT_OPT_PARAM_VAL" ADD  FOREIGN KEY ("EXPERIMENT_ID") REFERENCES "EXPERIMENT" ("EXPERIMENT_ID")
;

ALTER TABLE "HISTORY" ADD  FOREIGN KEY ("EXPERIMENT_ID") REFERENCES "EXPERIMENT" ("EXPERIMENT_ID")
;

ALTER TABLE "COEXPERIMENTER_REL" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "EXPERIMENT" ADD  FOREIGN KEY ("OWNER_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "HEARING_IMPAIRMENT_REL" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "VISUAL_IMPAIRMENT_REL" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "PERSON_OPT_PARAM_VAL" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "RESEARCH_GROUP" ADD  FOREIGN KEY ("OWNER_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "RESEARCH_GROUP_MEMBERSHIP" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "EXPERIMENT" ADD  FOREIGN KEY ("SUBJECT_PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "SCENARIO" ADD  FOREIGN KEY ("OWNER_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "HISTORY" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "ARTICLES" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "ARTICLES_COMMENTS" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "GROUP_PERMISSION_REQUEST" ADD  FOREIGN KEY ("PERSON_ID") REFERENCES "PERSON" ("PERSON_ID")
;

ALTER TABLE "EXPERIMENT" ADD  FOREIGN KEY ("SCENARIO_ID") REFERENCES "SCENARIO" ("SCENARIO_ID")
;

ALTER TABLE "HISTORY" ADD  FOREIGN KEY ("SCENARIO_ID") REFERENCES "SCENARIO" ("SCENARIO_ID")
;

ALTER TABLE "HARDWARE_USAGE_REL" ADD  FOREIGN KEY ("HARDWARE_ID") REFERENCES "HARDWARE" ("HARDWARE_ID")
;

ALTER TABLE "FILE_METADATA_PARAM_VAL" ADD  FOREIGN KEY ("DATA_FILE_ID") REFERENCES "DATA_FILE" ("DATA_FILE_ID")
;

ALTER TABLE "HISTORY" ADD  FOREIGN KEY ("DATA_FILE_ID") REFERENCES "DATA_FILE" ("DATA_FILE_ID")
;

ALTER TABLE "EXPERIMENT" ADD  FOREIGN KEY ("WEATHER_ID") REFERENCES "WEATHER" ("WEATHER_ID")
;

ALTER TABLE "HEARING_IMPAIRMENT_REL" ADD  FOREIGN KEY ("HEARING_IMPAIRMENT_ID") REFERENCES "HEARING_IMPAIRMENT" ("HEARING_IMPAIRMENT_ID")
;

ALTER TABLE "VISUAL_IMPAIRMENT_REL" ADD  FOREIGN KEY ("VISUAL_IMPAIRMENT_ID") REFERENCES "VISUAL_IMPAIRMENT" ("VISUAL_IMPAIRMENT_ID")
;

ALTER TABLE "PERSON_OPT_PARAM_VAL" ADD  FOREIGN KEY ("PERSON_OPT_PARAM_DEF_ID") REFERENCES "PERSON_OPT_PARAM_DEF" ("PERSON_OPT_PARAM_DEF_ID")
;

ALTER TABLE "EXPERIMENT_OPT_PARAM_VAL" ADD  FOREIGN KEY ("EXPERIMENT_OPT_PARAM_DEF_ID") REFERENCES "EXPERIMENT_OPT_PARAM_DEF" ("EXPERIMENT_OPT_PARAM_DEF_ID")
;

ALTER TABLE "FILE_METADATA_PARAM_VAL" ADD  FOREIGN KEY ("FILE_METADATA_PARAM_DEF_ID") REFERENCES "FILE_METADATA_PARAM_DEF" ("FILE_METADATA_PARAM_DEF_ID")
;

ALTER TABLE "RESEARCH_GROUP_MEMBERSHIP" ADD  FOREIGN KEY ("RESEARCH_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "SCENARIO" ADD  FOREIGN KEY ("RESEARCH_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "EXPERIMENT" ADD  FOREIGN KEY ("RESEARCH_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "ARTICLES" ADD  FOREIGN KEY ("RESEARCH_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "PERSON" ADD  FOREIGN KEY ("DEFAULT_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "GROUP_PERMISSION_REQUEST" ADD  FOREIGN KEY ("RESEARCH_GROUP_ID") REFERENCES "RESEARCH_GROUP" ("RESEARCH_GROUP_ID")
;

ALTER TABLE "ARTICLES_COMMENTS" ADD  FOREIGN KEY ("ARTICLE_ID") REFERENCES "ARTICLES" ("ARTICLE_ID")
;

ALTER TABLE "ARTICLES_COMMENTS" ADD  FOREIGN KEY ("PARENT_ID") REFERENCES "ARTICLES_COMMENTS" ("COMMENT_ID")
;


-- CREATE OBJECT TABLES SECTION


-- CREATE XMLTYPE TABLES SECTION


-- CREATE FUNCTIONS SECTION


-- CREATE SEQUENCES SECTION


-- CREATE PACKAGES SECTION


-- CREATE SYNONYMS SECTION


-- CREATE TABLE COMMENTS SECTION


-- CREATE ATTRIBUTE COMMENTS SECTION


