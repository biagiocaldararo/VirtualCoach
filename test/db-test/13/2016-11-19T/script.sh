#!/bin/sh

sudo service  mongod start

mongoimport --db test --collection classifica --drop --file ~/Scaricati/db-test/2016-11-19T/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/Scaricati/db-test/2016-11-19T/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/Scaricati/db-test/2016-11-19T/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/Scaricati/db-test/2016-11-19T/statistiche.json
mongoimport --db test --collection giornate --drop --file ~/Scaricati/db-test/2016-11-19T/giornate.json



