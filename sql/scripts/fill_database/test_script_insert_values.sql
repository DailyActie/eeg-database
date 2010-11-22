INSERT INTO HEARING_IMPAIRMENT(HEARING_IMPAIRMENT_ID, DESCRIPTION) VALUES(1, 'neslysi');
INSERT INTO HEARING_IMPAIRMENT(HEARING_IMPAIRMENT_ID, DESCRIPTION) VALUES(2, 'neslysi na leve ucho');
INSERT INTO HEARING_IMPAIRMENT(HEARING_IMPAIRMENT_ID, DESCRIPTION) VALUES(3, 'neslysi na prave ucho');

INSERT INTO VISUAL_IMPAIRMENT(VISUAL_IMPAIRMENT_ID, DESCRIPTION) VALUES(1, 'nevidi');
INSERT INTO VISUAL_IMPAIRMENT(VISUAL_IMPAIRMENT_ID, DESCRIPTION) VALUES(2, 'kratkozraky');
INSERT INTO VISUAL_IMPAIRMENT(VISUAL_IMPAIRMENT_ID, DESCRIPTION) VALUES(3, 'dalekozraky');

INSERT INTO WEATHER(WEATHER_ID, TITLE, DESCRIPTION) VALUES(1, 'jasno', 'sviti slunce');
INSERT INTO WEATHER(WEATHER_ID, TITLE, DESCRIPTION) VALUES(2, 'zatazeno', 'nesviti slunce');
INSERT INTO WEATHER(WEATHER_ID, TITLE, DESCRIPTION) VALUES(3, 'prsi', 'obcas prehanky');
INSERT INTO WEATHER(WEATHER_ID, TITLE, DESCRIPTION) VALUES(4, 'boure', 'silny vitr');
INSERT INTO WEATHER(WEATHER_ID, TITLE, DESCRIPTION) VALUES(5, 'snezi', 'zima');

INSERT INTO HARDWARE(HARDWARE_ID, TITLE, TYPE, DESCRIPTION) VALUES(1, 'EEG_cepice', 'EC1', '12 elektrod');
INSERT INTO HARDWARE(HARDWARE_ID, TITLE, TYPE, DESCRIPTION) VALUES(2, 'amplifier', 'AA8', 'Vyroben v Thajwanu');

INSERT INTO PERSON_OPT_PARAM_DEF(PERSON_OPT_PARAM_DEF_ID, PARAM_NAME, PARAM_DATA_TYPE) VALUES(1, 'address', 'string');
INSERT INTO PERSON_OPT_PARAM_DEF(PERSON_OPT_PARAM_DEF_ID, PARAM_NAME, PARAM_DATA_TYPE) VALUES(2, 'FAX', 'integer');

INSERT INTO EXPERIMENT_OPT_PARAM_DEF(EXPERIMENT_OPT_PARAM_DEF_ID, PARAM_NAME, PARAM_DATA_TYPE) VALUES(1, 'comment', 'string');

INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(1, 'Roman', 'Mou�ek', TO_DATE('5.6.1975', 'DD.MM.RR'), 'M', 'moucek@kiv.zcu.cz', 784554201, 'moucek', 'e3d30daee4bccc80bb6f34c5fe7ac82e', 'ROLE_ADMIN',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(2, 'Petr', 'Je�ek', TO_DATE('5.5.1984', 'DD.MM.RR'), 'M', 'jezekp@kiv.zcu.cz', 784554207, 'pitrs', '0657e0d304cec8952ef94119f4581d81', 'ROLE_ADMIN',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(3, 'Pavel', 'Mautner', TO_DATE('5.5.1970', 'DD.MM.RR'), 'M', 'mautner@kiv.zcu.cz', 784554202, 'pavel', 'ef1652b79c940145b600de7a2fe0288e', 'ROLE_ADMIN',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(4, 'Petr', 'Br�ha', TO_DATE('30.8.1987', 'DD.MM.RR'), 'M', 'BruhaP@seznam.cz', 732907550, 'pbruha', 'e3d30daee4bccc80bb6f34c5fe7ac82e', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(5, 'Jan', '�t�bet�k', TO_DATE('1.7.1987', 'DD.MM.RR'), 'M', 'stebjan@students.zcu.cz', 723226612, 'stebjan', 'stebjan', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(6, 'Ji��', 'Vla�imsk�', TO_DATE('5.6.1987', 'DD.MM.RR'), 'M', 'jvlasims@students.zcu.cz', 784554203, 'jvlasims', 'jvlasims', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(7, 'Miroslav', 'Bro�ek', TO_DATE('12.3.1987','DD.MM.RR'), 'M', 'Barbarez@seznam.cz', 784554212, 'barbarez', 'barbarez', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(8, 'Radka', 'Havlenov�', TO_DATE('1.1.1986', 'DD.MM.RR'),'F', 'radka@seznam.cz', 784555601, 'radka', 'radka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(9, 'Luk�', 'Va�eka', TO_DATE('10.4.1987', 'DD.MM.RR'), 'M', 'lukas.vareka@seznam.cz', 784554207, 'vareka', 'vareka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(10, 'David', 'Br�ha', TO_DATE('6.2.1982', 'DD.MM.RR'), 'M', 'bruno@seznam.cz', 784554791, 'bruna', 'bruna', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(11, 'Jind�ich', 'Pergler', TO_DATE('6.2.1987', 'DD.MM.RR'), 'M', 'viper@seznam.cz', 784557591, 'jpergler', 'jpergler', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(12, 'Martin', 'Soutor', TO_DATE('3.10.1987', 'DD.MM.RR'), 'M', 'souty@seznam.cz', 784457591, 'souty', 'souty', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(13, 'Jind�ich', 'K�ivka', TO_DATE('10.11.1987', 'DD.MM.RR'), 'M', 'krovak@seznam.cz', 784556591, 'krivka', 'krivka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(14, 'Pavel', 'P�edota', TO_DATE('8.8.1987','DD.MM.RR'), 'M', 'predy@seznam.cz', 784551591, 'predy', 'predy', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(15, 'Monika', 'Honsov�', TO_DATE('1.7.1987', 'DD.MM.RR'), 'F', 'moni@seznam.cz', 784557621, 'moni', 'moni', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(16, 'V�ra', 'Vinick�', TO_DATE('27.8.1987', 'DD.MM.RR'), 'F', 'winie@seznam.cz', 784657591, 'winie', 'winie', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(17, 'Barbora', 'Mikol�kov�', TO_DATE('1.2.1988', 'DD.MM.RR'), 'F', 'bara@seznam.cz', 784557521, 'bara', 'bara', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(18, 'Kate�ina', 'Ledinsk�', TO_DATE('8.12.1989', 'DD.MM.RR'), 'F', 'katuch@seznam.cz', 783557591, 'katuch', 'katuch', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(19, 'Veronika', 'Chmela�ov�', TO_DATE('6.3.1986', 'DD.MM.RR'), 'F', 'viper@seznam.cz', 784577591, 'werik', 'werik', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(20, 'Petr', 'Hump�l', TO_DATE('3.4.1987', 'DD.MM.RR'), 'M', 'cube@seznam.cz', 784557391, 'cube', 'cube', 'ROLE_USER',1);

INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(21, 'Lucie', 'Kol��ov�', TO_DATE('10.5.1985','DD.MM.RR'), 'F', 'lamborghiny.diablo@seznam.cz', 732557391, 'lucinka', 'lucinka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(22, 'Lenka', 'Mal�', TO_DATE('5.8.1987','DD.MM.RR'), 'F', 'malicka@seznam.cz', 732557392, 'lenicka', 'lenicka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(23, 'Ji��', 'Mat�jovic', TO_DATE('10.10.1987','DD.MM.RR'), 'M', 'matejka@seznam.cz', 732557393, 'matejovic', 'matejovic', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(24, 'Jan', 'Blahn�k', TO_DATE('4.9.1986','DD.MM.RR'), 'M', 'blaho@seznam.cz', 732557394, 'blaho', 'blaho', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(25, 'V�clav', 'Du�ek', TO_DATE('3.1.1987','DD.MM.RR'), 'M', 'dusin@seznam.cz', 732557395, 'dusin', 'dusin', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(26, 'Radmila', 'Dobr�', TO_DATE('10.5.1986','DD.MM.RR'), 'F', 'radulka@seznam.cz', 732557396, 'radmilka', 'radmilka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(27, 'Tereza', 'Pazdern�kov�', TO_DATE('10.4.1985','DD.MM.RR'), 'F', 'terezka@seznam.cz', 732557397, 'terka', 'terka', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(28, 'Lucie', 'Bal��kov�', TO_DATE('2.3.1985','DD.MM.RR'), 'F', 'lucik@seznam.cz', 732557398, 'lucik', 'lucik', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(29, 'Lucie', 'Pivo�kov�', TO_DATE('5.11.1987','DD.MM.RR'), 'F', 'luciep@seznam.cz', 732557399, 'lucikp', 'lucikp', 'ROLE_USER',1);
INSERT INTO PERSON(PERSON_ID, GIVENNAME, SURNAME, DATE_OF_BIRTH, GENDER, EMAIL, PHONE_NUMBER, USERNAME, PASSWORD, AUTHORITY,CONFIRMED)
VALUES(30, 'Lucie', 'Kylbergerov�', TO_DATE('29.8.1986','DD.MM.RR'), 'F', 'kylbergerova@seznam.cz', 732557311, 'luciek', 'luciek', 'ROLE_USER',1);

INSERT INTO RESEARCH_GROUP(RESEARCH_GROUP_ID, OWNER_ID, TITLE, DESCRIPTION) VALUES(1,1, 'University of West Bohemia', 'Z�pado�esk� Univerzita');
INSERT INTO RESEARCH_GROUP(RESEARCH_GROUP_ID, OWNER_ID, TITLE, DESCRIPTION) VALUES(2,5, 'V�voj��i', 'Z�pado�esk� Univerzita');
INSERT INTO RESEARCH_GROUP(RESEARCH_GROUP_ID, OWNER_ID, TITLE, DESCRIPTION) VALUES(3,8, 'Experiment�to�i', 'Z�pado�esk� Univerzita');

INSERT INTO VISUAL_IMPAIRMENT_REL(PERSON_ID, VISUAL_IMPAIRMENT_ID)VALUES(9, 3);
INSERT INTO VISUAL_IMPAIRMENT_REL(PERSON_ID, VISUAL_IMPAIRMENT_ID)VALUES(10, 2);

INSERT INTO HEARING_IMPAIRMENT_REL(PERSON_ID, HEARING_IMPAIRMENT_ID)VALUES(4, 3);
INSERT INTO HEARING_IMPAIRMENT_REL(PERSON_ID, HEARING_IMPAIRMENT_ID)VALUES(5, 2);

INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(1,1,'GROUP_ADMIN');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(2,1,'GROUP_ADMIN');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(3,1,'GROUP_ADMIN');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(2,2,'GROUP_READER');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(4,2,'GROUP_READER');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(5,2,'GROUP_ADMIN');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(6,2,'GROUP_READER');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(7,2,'GROUP_EXPERIMENTER');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(8,3,'GROUP_ADMIN');
INSERT INTO RESEARCH_GROUP_MEMBERSHIP(PERSON_ID, RESEARCH_GROUP_ID,AUTHORITY)VALUES(9,3,'GROUP_EXPERIMENTER');

INSERT INTO SCENARIO(SCENARIO_ID, TITLE, SCENARIO_LENGTH, SCENARIO_XML, DESCRIPTION, OWNER_ID, RESEARCH_GROUP_ID, PRIVATE)VALUES(1,'Reakce na v�znamnou ud�lost',200, 
'<?xml version="1.0" encoding="utf-8" ?>
<testFile>
  <item>Hello</item>
</testFile>', 'testovac� scenario', 8, 3, 1, 'reakce.xml', 'text/xml');

INSERT INTO EXPERIMENT(EXPERIMENT_ID, SCENARIO_ID, START_TIME, END_TIME, TEMPERATURE, WEATHERNOTE, SUBJECT_PERSON_ID, WEATHER_ID, OWNER_ID, RESEARCH_GROUP_ID, PRIVATE)
VALUES(1,1,TO_DATE ('29.3.2010 10:00:00', 'DD.MM.YYYY HH24:MI:SS'), TO_DATE('29.3.2010 10:05:00', 'DD.MM.YYYY HH24:MI:SS') , 23, 'bio2', 10, 2, 9, 3, 1);

INSERT INTO COEXPERIMENTER_REL(PERSON_ID, EXPERIMENT_ID)VALUES(8,1);
INSERT INTO COEXPERIMENTER_REL(PERSON_ID, EXPERIMENT_ID)VALUES(9,1);

INSERT INTO HARDWARE_USAGE_REL(HARDWARE_ID, EXPERIMENT_ID)VALUES(1,1);


INSERT INTO DATA_FILE(DATA_FILE_ID,SAMPLING_RATE, FILE_CONTENT, EXPERIMENT_ID, MIMETYPE, FILENAME)VALUES(1,'1000', '0A0C', 1, 'txt', 'mereni_1');

INSERT INTO ARTICLES(ARTICLE_ID, PERSON_ID, RESEARCH_GROUP_ID, TEXT, TIME, TITLE)VALUES(1,1,1,'Ve st�edu 30.3.2010 prob�hne sch�zka p��tel EEG.', SYSDATE ,'EEG ve�er' );
INSERT INTO ARTICLES(ARTICLE_ID, PERSON_ID, RESEARCH_GROUP_ID, TEXT, TIME, TITLE)VALUES(2,2,2,'Ve st�edu 7.4.2010 neprob�hne sch�zka s EEG-t�mem. D�vod: Jsem nemocn�',SYSDATE,'Zru�en� sch�zky' );
INSERT INTO ARTICLES(ARTICLE_ID, PERSON_ID, RESEARCH_GROUP_ID, TEXT, TIME, TITLE)VALUES(3,2,2,'Ve p�tek 9.4.2010 prob�hne u n�s v laborato�i UU-403 �kolen� t�kaj�c� se nov� EEG metody. ��ast povinn� pro v�echny experiment�tory',SYSDATE, '�kolen�' );

INSERT INTO ARTICLES_COMMENTS(COMMENT_ID, PERSON_ID, ARTICLE_ID, TEXT, TIME, PARENT_ID)VALUES(1,4,1,'Omlouv�m se ve st�edu nem�u p�ij�t.', SYSDATE, 1);
INSERT INTO ARTICLES_COMMENTS(COMMENT_ID, PERSON_ID, ARTICLE_ID, TEXT, TIME, PARENT_ID)VALUES(2,5,2,'Nem�u p�ij�t, jedu do Prahy.', SYSDATE, 2);
INSERT INTO ARTICLES_COMMENTS(COMMENT_ID, PERSON_ID, ARTICLE_ID, TEXT, TIME, PARENT_ID)VALUES(3,6,2,'Jak dlouho bude �kolen� trvat?', SYSDATE, 3);

INSERT INTO HISTORY(HISTORY_ID, EXPERIMENT_ID, SCENARIO_ID, PERSON_ID, DATA_FILE_ID, DATE_OF_DOWNLOAD)VALUES(1,1,null,1,null,SYSDATE);

INSERT INTO EXPERIMENT_OPT_PARAM_VAL(PARAM_VALUE, EXPERIMENT_ID, EXPERIMENT_OPT_PARAM_DEF_ID) VALUES('zmereno', 1, 1);

INSERT INTO PERSON_OPT_PARAM_VAL(PARAM_VALUE, PERSON_ID, PERSON_OPT_PARAM_DEF_ID) VALUES('Budejovice', 5, 1);
INSERT INTO PERSON_OPT_PARAM_VAL(PARAM_VALUE, PERSON_ID, PERSON_OPT_PARAM_DEF_ID) VALUES('235667410', 4, 2);

INSERT INTO FILE_METADATA_PARAM_DEF(FILE_METADATA_PARAM_DEF_ID, PARAM_NAME, PARAM_DATA_TYPE) VALUES(1, 'description', 'string');

INSERT INTO FILE_METADATA_PARAM_VAL(FILE_METADATA_PARAM_DEF_ID, DATA_FILE_ID, METADATA_VALUE) VALUES(1, 1, 'nepouzitelne');
