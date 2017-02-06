#!/bin/sh

python probabili.py
python quotazioni.py
python statistiche.py
python precedenti.py
python classifica.py
python classifica_compl.py

sudo service  mongod start

mongoimport --db test --collection classifica --drop --file ~/<Your-Path-Here>/VirtualCoach/script/classifica_compl.json
mongoimport --db test --collection probabili --drop --file ~/<Your-Path-Here>/VirtualCoach/script/probabili.json
mongoimport --db test --collection quotazioni --drop --file ~/<Your-Path-Here>/VirtualCoach/script/quotazioni.json
mongoimport --db test --collection statistiche --drop --file ~/<Your-Path-Here>/VirtualCoach/script/statistiche.json



