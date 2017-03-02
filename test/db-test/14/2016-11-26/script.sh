#!/bin/sh

python probabili.py
python quotazioni.py
python statistiche.py
python precedenti.py
python classifica.py
python classifica_compl.py

sudo service  mongod start

mongoimport --db test --collection classifica --drop --file ~/Scaricati/db-test/26-11-2016/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/Scaricati/db-test/26-11-2016/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/Scaricati/db-test/26-11-2016/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/Scaricati/db-test/26-11-2016/statistiche.json



