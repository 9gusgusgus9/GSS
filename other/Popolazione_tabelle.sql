 INSERT INTO sesso (Sesso) VALUES ('Maschio'), ('Femmina'), ('Altro');
 INSERT INTO sport (Nome) VALUES ('Calcio'), ('Basket'), ('Pallavolo');
 INSERT INTO mano_piede_preferito (Preferenza) VALUES ('Dx'), ('Sx'), ('DS');
 INSERT INTO ruolo_dirigente (IdRuoloDirigente, Descrizione) VALUES
 ('Pres', 'Presidente'), 
 ('AD', 'Amministratore Delegato'),
 ('VP', 'Vice Presidente'),
 ('DS', 'Direttore Sportivo'),
 ('RG', 'Responsabile settore giovanile'),
 ('RF', 'Responsabile settore femminile'),
 ('Altro', 'Altro'),
 ('MS', 'Medico sociale'),
 ('Cust', 'Custode'),
 ('PA', 'Preparatore atletico');
 INSERT INTO ruolo_staff (IdRuoloStaff, Descrizione) VALUES
 ('All', 'Allenatore'),
 ('2All', 'Allenatore in seconda'),
 ('Acc', 'Accompagnatore'),
('Mass', 'Massaggiatore'),
 ('Altro', 'Altro'),
 ('PA', 'Preparatore atletico');
 INSERT INTO tipo_documento (Nome) VALUES 
('Carta Identità'),
('Patente'),
('Passaporto'),
('Certificato Medico'),
('Certificato COVID'),
('Contratto'),
('Altro');