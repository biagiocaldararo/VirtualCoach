#!/bin/sh

sudo service mongod start

mongoimport --db test --collection classifica --drop --file ~/Scaricati/db-test/18/2016-12-20/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/Scaricati/db-test/18/2016-12-20/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/Scaricati/db-test/18/2016-12-20/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/Scaricati/db-test/18/2016-12-20/statistiche.json



