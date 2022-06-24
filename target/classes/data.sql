insert into compte_bancaire (id,iban,type, interet,frais)
values(10001,'FR7630004000031234567890143','Compte courant',0.0,'gratuit');
insert into compte_bancaire (id,iban,type, interet,frais)
values(10003,'FR7630004770031234567896543','Compte courant',0.0,'10');
insert into operation_bancaire (id,type_operation,iban_source,iban_destination,montant, date)
values(10002,'virement','FR7630004000031234567890143','FR7632004000031234888890142',10.0,'2022-06-20');