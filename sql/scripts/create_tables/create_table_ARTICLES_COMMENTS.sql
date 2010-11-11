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