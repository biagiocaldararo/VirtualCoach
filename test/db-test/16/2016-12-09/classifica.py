import urllib2
from bs4 import BeautifulSoup

url = "http://sport.sky.it/statistiche/calcio/serie_a/classifiche.shtml"
page = urllib2.urlopen(url)

soup = BeautifulSoup(page,'html.parser')

f = open('classifica.txt', 'w')

j = ""

for riga in soup.find_all("tr")[3:23]:
	elem = riga.find_all("td")
	squadra = elem[2].a.string
	punti = elem[3].string
	v = elem[5].string   #vittorie
	n = elem[6].string   #pareggi   
	p = elem[7].string   #sconfitte
	vc = elem[9].string   #vittorie casa
	nc = elem[10].string  #pareggi casa
	pc = elem[11].string   #sconfitte casa
	vf = elem[13].string   #vittorie fuori
	nf = elem[14].string   #pareggi fuori
	pf = elem[15].string   #sconfitte fuori
	gr = elem[17].string   #gol realizzati
	gs = elem[18].string   #gol subiti
	grc = elem[19].string   #gol realizzati casa   
	gsc = elem[20].string   #gol subiti casa
	grf = elem[21].string   #gol realizzati fuori
	gsf = elem[22].string   #gol subiti fuori
	
	f.write('{ "squadra": "' + squadra + '", "punti": ' + punti + ', "v": ' + v + ', "n": ' + n + ', "p": ' + p + ', "vc": ' + vc + ', "nc": ' + nc + ', "pc": ' + pc + ', "vf": ' + vf + ', "nf": ' + nf + ', "pf": ' + pf + ', "gr": ' + gr + ', "gs": ' + gs + ', "grc": ' + grc + ', "gsc": ' + gsc + ', "grf": ' + grf + ', "gsf": ' + gsf + ' } \n')

	 
		



