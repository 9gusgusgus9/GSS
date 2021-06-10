-- *********************************************
-- * SQL MySQL generation                      
-- *--------------------------------------------
-- * DB-MAIN version: 11.0.1              
-- * Generator date: Dec  4 2018              
-- * Generation date: Mon May 31 16:40:08 2021 
-- * LUN file: C:\Users\tbrin\Desktop\BaseDati\GSS\GSS.lun 
-- * Schema: Team_Management/R 
-- ********************************************* 


-- Database Section
-- ________________ 

create database Team_Management;
use Team_Management;


-- Tables Section
-- _____________ 

create table CATEGORIA (
     IdCategoria int not null auto_increment,
     Nome varchar(30) not null,
     CodPartitaIVA varchar(11) not null,
     CodImmagine int not null,
     constraint IDCATEGORIA primary key (IdCategoria));

create table CONVOCAZIONI (
     CodEvento int not null,
     CF varchar(16) not null,
     Presenza bool default false not null,
     constraint IDCONVOCAZIONI primary key (CodEvento, CF));

create table GIOCATORE (
     CF varchar(16) not null,
     Peso char(5) not null,
     Altezza char(5) not null,
     Data_scadenza_certificato char(7) not null,
     CodRuoloGiocatore varchar(5) not null,
     CodCategoria int not null,
     CodPreferenza varchar(2) not null,
     constraint FKgioca_ID primary key (CF));

create table STAFF (
     CF varchar(16) not null,
     CodRuoloStaff varchar(5) not null,
     CodCategoria int not null,
     constraint FKallena_ID primary key (CF));

create table DIRIGENTE (
     CF varchar(16) not null,
     CodRuoloDirigente varchar(5) not null,
     constraint FKdirige_ID primary key (CF));

create table EVENTI (
     IdEvento int not null auto_increment,
     Inizio varchar(15) not null,
     Fine varchar(15) not null,
     CodPartitaIVA varchar(11) not null,
     NomeAvversario varchar(40),
     Risultato varchar(5),
     Descrizione_generico varchar(40),
     CodCategoria int,
     constraint IDEVENTI_ID primary key (IdEvento));

create table IMMAGINE (
     IdImmagine int not null auto_increment,
     Nome varchar(255) not null,
     TipoFile varchar(128) not null,
     DatiFimmagineimmagineile longblob not null,
     constraint IDFOTO primary key (IdImmagine));

create table MANO_PIEDE_PREFERITO (
     Preferenza varchar(2) not null,
     constraint IDMANO_PIEDE_PREFERITO primary key (Preferenza));

create table PAGAMENTO (
     IdPagamento int not null auto_increment,
     Quota int,
     Stipendio int,
     Pagato bool default false not null,
     constraint IDPAGAMENTO_ID primary key (IdPagamento));

create table PERSONA (
     CF varchar(16) not null,
     CodPagamento int not null,
     Nome varchar(25) not null,
     Cognome varchar(25) not null,
     Data date not null,
     Matricola_tesserino varchar(20),
     CodSesso varchar(10) not null,
     CodPartitaIVA varchar(11) not null,
     CodImmagine int not null,
     CodRuoloPersona varchar(10) not null,
     constraint IDPERSONA primary key (CF));

create table POSSESSO (
     CodDocumento int not null,
     CF varchar(16) not null,
     File bigint not null,
     constraint IDPOSSESSO primary key (CodDocumento, CF));

create table RUOLO_DIRIGENTE (
     IdRuoloDirigente varchar(5) not null,
     Descrizione char(40) not null,
     constraint IDRUOLO_DIRIGENTE primary key (IdRuoloDirigente));

create table RUOLO_GIOCATORE (
     IdRuoloGiocatore varchar(5) not null,
     Descrizione char(40) not null,
     constraint IDRUOLO_GIOCATORE primary key (IdRuoloGiocatore));

create table RUOLO_STAFF (
     IdRuoloStaff varchar(5) not null,
     Descrizione varchar(40) not null,
     constraint IDRUOLO_STAFF primary key (IdRuoloStaff));

create table SESSO (
     Sesso varchar(10) not null,
     constraint IDSESSO primary key (Sesso));

create table SOCIETA (
     PartitaIVA varchar(11) not null,
     Nome varchar(40) not null,
     CodSport int not null,
     CodImmagine int not null,
     constraint IDSOCIETA primary key (PartitaIVA));

create table SPORT (
     IdSport int not null auto_increment,
     Nome varchar(30) not null,
     constraint IDSPORT primary key (IdSport));

create table TIPO_DOCUMENTO (
     IdDocumento int not null auto_increment,
     Nome varchar(30) not null,
     constraint IDTIPO_DOCUMENTO primary key (IdDocumento));


-- Constraints Section
-- ___________________ 

alter table CATEGORIA add constraint FKgestisce
     foreign key (CodPartitaIVA)
     references SOCIETA (PartitaIVA);

alter table CATEGORIA add constraint FKimmagine
     foreign key (CodImmagine)
     references IMMAGINE (IdImmagine);

alter table CONVOCAZIONI add constraint FKCodPersona
     foreign key (CF)
     references PERSONA (CF);

alter table CONVOCAZIONI add constraint FKCodEvento
     foreign key (CodEvento)
     references EVENTI (IdEvento);

alter table GIOCATORE add constraint FKgioca_FK
     foreign key (CF)
     references PERSONA (CF);

alter table GIOCATORE add constraint FKruolo3
     foreign key (CodRuoloGiocatore)
     references RUOLO_GIOCATORE (IdRuoloGiocatore);

alter table GIOCATORE add constraint FKGioca_1
     foreign key (CodCategoria)
     references CATEGORIA (IdCategoria);

alter table GIOCATORE add constraint FKpreferenza
     foreign key (CodPreferenza)
     references MANO_PIEDE_PREFERITO (Preferenza);

alter table STAFF add constraint FKallena_FK
     foreign key (CF)
     references PERSONA (CF);

alter table STAFF add constraint FKruolo2
     foreign key (CodRuoloStaff)
     references RUOLO_STAFF (IdRuoloStaff);

alter table STAFF add constraint FKALLENA_1
     foreign key (CodCategoria)
     references CATEGORIA (IdCategoria);

alter table DIRIGENTE add constraint FKdirige_FK
     foreign key (CF)
     references PERSONA (CF);

alter table DIRIGENTE add constraint FKruolo1
     foreign key (CodRuoloDirigente)
     references RUOLO_DIRIGENTE (IdRuoloDirigente);

-- Not implemented
-- alter table EVENTI add constraint IDEVENTI_CHK
--     check(exists(select * from CONVOCAZIONI
--                  where CONVOCAZIONI.CodEvento = IdEvento)); 

alter table EVENTI add constraint FKcalendarizza
     foreign key (CodPartitaIVA)
     references SOCIETA (PartitaIVA);

alter table EVENTI add constraint PARTITA
     check((NomeAvversario is not null and Risultato is not null)
           or (NomeAvversario is null and Risultato is null)); 

alter table EVENTI add constraint GREVENTI
     check((Descrizione_generico is not null and CodCategoria is null)
           or (Descrizione_generico is null and CodCategoria is not null)); 

-- Not implemented
-- alter table PAGAMENTO add constraint IDPAGAMENTO_CHK
--     check(exists(select * from PERSONA
--                  where PERSONA.CodPagamento = IdPagamento)); 

alter table PAGAMENTO add constraint GRPAGAMENTO
     check((Quota is not null and Stipendio is null)
           or (Quota is null and Stipendio is not null)); 

alter table PERSONA add constraint FKGenere
     foreign key (CodSesso)
     references SESSO (Sesso);

alter table PERSONA add constraint FKtesserato
     foreign key (CodPartitaIVA)
     references SOCIETA (PartitaIVA);

alter table PERSONA add constraint FKrappresenta
     foreign key (CodImmagine)
     references IMMAGINE (IdImmagine);

alter table PERSONA add constraint FKpagamento
     foreign key (CodPagamento)
     references PAGAMENTO (IdPagamento);

alter table POSSESSO add constraint FKRCodPersona
     foreign key (CF)
     references PERSONA (CF);

alter table POSSESSO add constraint FKCodTipoDocumento
     foreign key (CodDocumento)
     references TIPO_DOCUMENTO (IdDocumento);

alter table SOCIETA add constraint FKpratica
     foreign key (CodSport)
     references SPORT (IdSport);

alter table SOCIETA add constraint FKRimmagine
     foreign key (CodImmagine)
     references IMMAGINE (IdImmagine);


-- Index Section
-- _____________ 
