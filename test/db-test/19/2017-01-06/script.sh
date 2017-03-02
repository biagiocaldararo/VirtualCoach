#!/bin/sh

sudo service mongod start

mongoimport --db test --collection classifica --drop --file ~/Scaricati/db-test/19/2017-01-06/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/Scaricati/db-test/19/2017-01-06/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/Scaricati/db-test/19/2017-01-06/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/Scaricati/db-test/19/2017-01-06/statistiche.json



