--tabela klient
INSERT INTO klient(id, imie,  nazwisko, ulica, nr_domu, kod_pocztowy, poczta ,pesel, nr_telefonu)
VALUES (1, 'Lech', 'Nowicki', 'Ekologiczna', '9', '26-080', 'Kielce',88111107520, 123456789)
/

INSERT INTO klient(id, imie,  nazwisko, ulica, nr_domu, kod_pocztowy, poczta ,pesel, nr_telefonu)
VALUES (2, 'Jan', 'Kowalski', 'Warszawska', '100', '26-080', 'Kielce',78052464190, 987654321)
/

INSERT INTO klient(id, imie,  nazwisko, ulica, nr_domu, kod_pocztowy, poczta ,pesel, nr_telefonu)
VALUES (3, 'Maria', 'Nowak', 'Dluga', '1', '30-001', 'Krakow',80080854321, 123234345)
/

INSERT INTO klient(id, imie,  nazwisko, ulica, nr_domu, kod_pocztowy, poczta ,pesel, nr_telefonu)
VALUES (4, 'Zofia', 'Malinowska', 'Polna', '21', '26-080', 'Kielce',71022144490, 444555666)
/


--tabela nieruchomosc
INSERT INTO nieruchomosc(id, ulica, nr_domu, nr_mieszkania, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela)
VALUES (1, 'Warszawska', '159', '1', '26-080', 'Kielce', 125, 300000, 1999, 1)
/

INSERT INTO nieruchomosc(id, ulica, nr_domu, nr_mieszkania, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela)
VALUES (2, 'Radomska', '22', '1', '26-080', 'Kielce', 80, 150000, 1955, 2)
/

INSERT INTO nieruchomosc(id, ulica, nr_domu, nr_mieszkania, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela)
VALUES (3, 'Radomska', '22', '2', '26-080', 'Kielce', 80, 150000, 1955, 2)
/

INSERT INTO nieruchomosc(id, ulica, nr_domu, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela)
VALUES (4, 'Domaszowska', '150', '26-080', 'Kielce', 105, 800000, 2013, 3)
/

INSERT INTO nieruchomosc(id, ulica, nr_domu, kod_pocztowy, miejscowosc, powierzchnia, cena, rok_budowy, id_wlasciciela)
VALUES (5, 'Sloneczna', '13', '26-080', 'Kielce', 95, 345000, 1992, 4)
/