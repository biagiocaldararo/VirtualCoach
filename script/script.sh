#!/bin/sh

python probabili.py
python quotazioni.py
python statistiche.py
python precedenti.py
python classifica.py
python classifica_compl.py

sudo service  mongod start

mongoimport --db test --collection classifica --drop --file ~/Documenti/VirtualCoach/script/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/Documenti/VirtualCoach/script/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/Documenti/VirtualCoach/script/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/Documenti/VirtualCoach/script/statistiche.json



