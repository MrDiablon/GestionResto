/*==============================================================*/
/* Nom de SGBD :  ORACLE Version 11g                            */
/* Date de cr�ation :  12/10/2015 13:18:22                      */
/*==============================================================*/

/*
alter table COMMANDER
   drop constraint FK_COMMANDE_COMMANDER_TABLE;

alter table COMMANDER
   drop constraint FK_COMMANDE_COMMANDER_MENU;

alter table COMPOSER
   drop constraint FK_COMPOSER_COMPOSER_PLAT;

alter table COMPOSER
   drop constraint FK_COMPOSER_COMPOSER2_MENU;

alter table CONSTITUER
   drop constraint FK_CONSTITU_CONSTITUE_INGREDIE;

alter table CONSTITUER
   drop constraint FK_CONSTITU_CONSTITUE_PLAT;

alter table PERSONNEL
   drop constraint FK_PERSONNE_EMPLOYER_RESTAURA;

alter table PERSONNEL
   drop constraint FK_PERSONNE_TRAVAILLE_SALLE;

alter table RESERVER
   drop constraint FK_RESERVER_RESERVER_RESERVAT;

alter table RESERVER
   drop constraint FK_RESERVER_RESERVER2_TABLE;

alter table SALLE
   drop constraint FK_SALLE_POCEDER_RESTAURA;

alter table SERVIR
   drop constraint FK_SERVIR_SERVIR_SALLE;

alter table SERVIR
   drop constraint FK_SERVIR_SERVIR2_MENU;

alter table "TABLE"
   drop constraint FK_TABLE_SITUER_SALLE;

drop index COMMANDER2_FK;

drop index COMMANDER_FK;

drop table COMMANDER cascade constraints;

drop index COMPOSER2_FK;

drop index COMPOSER_FK;

drop table COMPOSER cascade constraints;

drop index CONSTITUER2_FK;

drop index CONSTITUER_FK;

drop table CONSTITUER cascade constraints;

drop table INGREDIENT cascade constraints;

drop table MENU cascade constraints;

drop index EMPLOYER_FK;

drop index TRAVAILLER_FK;

drop table PERSONNEL cascade constraints;

drop table PLAT cascade constraints;

drop table RESERVATION cascade constraints;

drop index RESERVER2_FK;

drop index RESERVER_FK;

drop table RESERVER cascade constraints;

drop table RESTAURANT cascade constraints;

drop index POCEDER_FK;

drop table SALLE cascade constraints;

drop index SERVIR2_FK;

drop index SERVIR_FK;

drop table SERVIR cascade constraints;

drop index SITUER_FK;

drop table "TABLE" cascade constraints;
*/
/*==============================================================*/
/* Table : COMMANDER                                            */
/*==============================================================*/
create table COMMANDER
(
   NUM                  INTEGER              not null ,
   NUMMENU              INTEGER              not null,
   constraint PK_COMMANDER primary key (NUM, NUMMENU)
);

/*==============================================================*/
/* Index : COMMANDER_FK                                         */
/*==============================================================*/
create index COMMANDER_FK on COMMANDER (
   NUM ASC
);

/*==============================================================*/
/* Index : COMMANDER2_FK                                        */
/*==============================================================*/
create index COMMANDER2_FK on COMMANDER (
   NUMMENU ASC
);

/*==============================================================*/
/* Table : COMPOSER                                             */
/*==============================================================*/
create table COMPOSER
(
   NUMPLAT              INTEGER              not null,
   NUMMENU              INTEGER              not null,
   constraint PK_COMPOSER primary key (NUMPLAT, NUMMENU)
);

/*==============================================================*/
/* Index : COMPOSER_FK                                          */
/*==============================================================*/
create index COMPOSER_FK on COMPOSER (
   NUMPLAT ASC
);

/*==============================================================*/
/* Index : COMPOSER2_FK                                         */
/*==============================================================*/
create index COMPOSER2_FK on COMPOSER (
   NUMMENU ASC
);

/*==============================================================*/
/* Table : CONSTITUER                                           */
/*==============================================================*/
create table CONSTITUER
(
   NUMINGREDIENT        INTEGER              not null auto_increment,
   NUMPLAT              INTEGER              not null,
   constraint PK_CONSTITUER primary key (NUMINGREDIENT, NUMPLAT)
);

/*==============================================================*/
/* Index : CONSTITUER_FK                                        */
/*==============================================================*/
create index CONSTITUER_FK on CONSTITUER (
   NUMINGREDIENT ASC
);

/*==============================================================*/
/* Index : CONSTITUER2_FK                                       */
/*==============================================================*/
create index CONSTITUER2_FK on CONSTITUER (
   NUMPLAT ASC
);

/*==============================================================*/
/* Table : INGREDIENT                                           */
/*==============================================================*/
create table INGREDIENT
(
   NUMINGREDIENT        INTEGER              not null auto_increment,
   ETATSI               CHAR(255),
   PRIXU                INTEGER,
   STOCK                INTEGER,
   constraint PK_INGREDIENT primary key (NUMINGREDIENT)
);

/*==============================================================*/
/* Table : MENU                                                 */
/*==============================================================*/
create table MENU
(
   NUMMENU              INTEGER              not null auto_increment,
   COMPOSITION          CHAR(255)            not null,
   NOM                  CHAR(255)            not null,
   constraint PK_MENU primary key (NUMMENU)
);

/*==============================================================*/
/* Table : PERSONNEL                                            */
/*==============================================================*/
create table PERSONNEL
(
   NUMPERSO             INTEGER              not null auto_increment,
   NUMRESTO             INTEGER              not null,
   NUMSALLE             INTEGER,
   NOM                  CHAR(255)            not null,
   PRENOM               CHAR(255)            not null,
   POSTE                CHAR(255)            not null,
   ADRESSE              CHAR(255)            not null,
   NUMTEL               char(255)            not null,
   ADRESSEMAIL          CHAR(255)            not null,
   HORAIRETRAV          BINARY(255),
   HORAIREPREV          BINARY(255)        not null,
   SALAIRE_H            INTEGER              not null,
   DROITS               INTEGER              not null,
   MDP                  CHAR(255),
   constraint PK_PERSONNEL primary key (NUMPERSO)
);

/*==============================================================*/
/* Index : TRAVAILLER_FK                                        */
/*==============================================================*/
create index TRAVAILLER_FK on PERSONNEL (
   NUMSALLE ASC
);

/*==============================================================*/
/* Index : EMPLOYER_FK                                          */
/*==============================================================*/
create index EMPLOYER_FK on PERSONNEL (
   NUMRESTO ASC
);

/*==============================================================*/
/* Table : PLAT                                                 */
/*==============================================================*/
create table PLAT
(
   NUMPLAT              INTEGER              not null auto_increment,
   RECETTE              TEXT,
   PRIXU                INTEGER,
   constraint PK_PLAT primary key (NUMPLAT)
);

/*==============================================================*/
/* Table : RESERVATION                                          */
/*==============================================================*/
create table RESERVATION
(
   NUMRESERVATION       INTEGER              not null auto_increment,
   DATERESERV           DATE                 not null,
   NOMCLIENT            CHAR(255),
   NBPERS               INTEGER,
   constraint PK_RESERVATION primary key (NUMRESERVATION)
);

/*==============================================================*/
/* Table : RESERVER                                             */
/*==============================================================*/
create table RESERVER
(
   NUMRESERVATION       INTEGER              not null,
   NUM                  INTEGER              not null,
   constraint PK_RESERVER primary key (NUMRESERVATION, NUM)
);

/*==============================================================*/
/* Index : RESERVER_FK                                          */
/*==============================================================*/
create index RESERVER_FK on RESERVER (
   NUMRESERVATION ASC
);

/*==============================================================*/
/* Index : RESERVER2_FK                                         */
/*==============================================================*/
create index RESERVER2_FK on RESERVER (
   NUM ASC
);

/*==============================================================*/
/* Table : RESTAURANT                                           */
/*==============================================================*/
create table RESTAURANT
(
   NUMRESTO             INTEGER              not null auto_increment,
   MARGE                INTEGER              not null,
   NBSALLES             INTEGER              not null,
   NBEMPLOYEE           INTEGER              not null,
   ADRESSE              CHAR(255)            not null,
   PAYS                 CHAR(255)            not null,
   NUMTEL               CHAR(255)            not null,
   VILLE                CHAR(255)            not null,
   CP                   CHAR(255)            not null,
   constraint PK_RESTAURANT primary key (NUMRESTO)
);

/*==============================================================*/
/* Table : SALLE                                                */
/*==============================================================*/
create table SALLE
(
   NUMSALLE             INTEGER              not null auto_increment,
   NUMRESTO             INTEGER              not null,
   NOMSALLE             CHAR(255)            not null,
   ETATS                CHAR(255)            not null,
   NOMBRETABLES         INTEGER              not null,
   constraint PK_SALLE primary key (NUMSALLE)
);

/*==============================================================*/
/* Index : POCEDER_FK                                           */
/*==============================================================*/
create index POCEDER_FK on SALLE (
   NUMRESTO ASC
);

/*==============================================================*/
/* Table : SERVIR                                               */
/*==============================================================*/
create table SERVIR
(
   NUMSALLE             INTEGER              not null,
   NUMMENU              INTEGER              not null,
   constraint PK_SERVIR primary key (NUMSALLE, NUMMENU)
);

/*==============================================================*/
/* Index : SERVIR_FK                                            */
/*==============================================================*/
create index SERVIR_FK on SERVIR (
   NUMSALLE ASC
);

/*==============================================================*/
/* Index : SERVIR2_FK                                           */
/*==============================================================*/
create index SERVIR2_FK on SERVIR (
   NUMMENU ASC
);

/*==============================================================*/
/* Table : "TABLE"                                              */
/*==============================================================*/
create table TABLES
(
   NUM                  INTEGER              not null auto_increment,
   NUMSALLE             INTEGER              not null,
   ETATS                CHAR(255)            not null,
   CAPACITE             INTEGER              not null,
   POSX                 INTEGER,
   POSY                 INTEGER,
   constraint PK_TABLE primary key (NUM)
);

/*==============================================================*/
/* Index : SITUER_FK                                            */
/*==============================================================*/
create index SITUER_FK on TABLES (
   NUMSALLE ASC
);

alter table COMMANDER
   add constraint FK_COMMANDE_COMMANDER_TABLE foreign key (NUM)
      references TABLES (NUM);

alter table COMMANDER
   add constraint FK_COMMANDE_COMMANDER_MENU foreign key (NUMMENU)
      references MENU (NUMMENU);

alter table COMPOSER
   add constraint FK_COMPOSER_COMPOSER_PLAT foreign key (NUMPLAT)
      references PLAT (NUMPLAT);

alter table COMPOSER
   add constraint FK_COMPOSER_COMPOSER2_MENU foreign key (NUMMENU)
      references MENU (NUMMENU);

alter table CONSTITUER
   add constraint FK_CONSTITU_CONSTITUE_INGREDIE foreign key (NUMINGREDIENT)
      references INGREDIENT (NUMINGREDIENT);

alter table CONSTITUER
   add constraint FK_CONSTITU_CONSTITUE_PLAT foreign key (NUMPLAT)
      references PLAT (NUMPLAT);

alter table PERSONNEL
   add constraint FK_PERSONNE_EMPLOYER_RESTAURA foreign key (NUMRESTO)
      references RESTAURANT (NUMRESTO);

alter table PERSONNEL
   add constraint FK_PERSONNE_TRAVAILLE_SALLE foreign key (NUMSALLE)
      references SALLE (NUMSALLE);

alter table RESERVER
   add constraint FK_RESERVER_RESERVER_RESERVAT foreign key (NUMRESERVATION)
      references RESERVATION (NUMRESERVATION);

alter table RESERVER
   add constraint FK_RESERVER_RESERVER2_TABLE foreign key (NUM)
      references TABLES (NUM);

alter table SALLE
   add constraint FK_SALLE_POCEDER_RESTAURA foreign key (NUMRESTO)
      references RESTAURANT (NUMRESTO);

alter table SERVIR
   add constraint FK_SERVIR_SERVIR_SALLE foreign key (NUMSALLE)
      references SALLE (NUMSALLE);

alter table SERVIR
   add constraint FK_SERVIR_SERVIR2_MENU foreign key (NUMMENU)
      references MENU (NUMMENU);

alter table TABLES
   add constraint FK_TABLE_SITUER_SALLE foreign key (NUMSALLE)
      references SALLE (NUMSALLE);
