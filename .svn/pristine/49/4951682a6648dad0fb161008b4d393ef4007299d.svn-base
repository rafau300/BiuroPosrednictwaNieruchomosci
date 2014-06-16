DROP TABLE klient CASCADE CONSTRAINTS;
DROP TABLE nieruchomosc CASCADE CONSTRAINTS;

CREATE TABLE klient(
  id NUMBER(6) CONSTRAINT klient_pk PRIMARY KEY,
  imie VARCHAR(32) NOT NULL,
  nazwisko VARCHAR(32) NOT NULL,
  ulica VARCHAR(32) NOT NULL,
  nr_domu VARCHAR(32),
  kod_pocztowy VARCHAR(32),
  poczta VARCHAR(32),
  pesel NUMBER(11) NOT NULL,
  nr_telefonu NUMBER(12) NOT NULL
  );

CREATE TABLE nieruchomosc(
  id NUMBER(6) CONSTRAINT mieszkanie_pk PRIMARY KEY,
  ulica VARCHAR(32) NOT NULL,
  nr_domu VARCHAR(32) NOT NULL,
  nr_mieszkania VARCHAR(32),
  kod_pocztowy VARCHAR(32),
  miejscowosc VARCHAR(32),
  powierzchnia NUMBER(6,2),
  cena NUMBER(8,2),
  rok_budowy NUMBER(4),
  id_wlasciciela NUMBER(6),
  CONSTRAINT klient_fk FOREIGN KEY (id_wlasciciela) REFERENCES klient(id)
  );