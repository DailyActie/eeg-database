CREATE TABLE "ARTICLES" (
	"ARTICLE_ID" INTEGER NOT NULL ,
	"RESEARCH_GROUP_ID" INTEGER,
	"TEXT" CLOB NOT NULL ,
	"TIME" DATE DEFAULT SYSDATE NOT NULL ,
	"TITLE" VARCHAR2 (150) NOT NULL ,
	"PERSON_ID" INTEGER NOT NULL ,
PRIMARY KEY ("ARTICLE_ID")
)
;