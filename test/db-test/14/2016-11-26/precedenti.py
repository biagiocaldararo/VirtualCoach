import urllib2
from bs4 import BeautifulSoup

url = "http://www.corrieredellosport.it/live/classifica-serie-a.html?cookieAccept"
page = urllib2.urlopen(url)

soup = BeautifulSoup(page,'html.parser')

tab = soup.find("table", {"class":"big classifica"})

f = open('precedenti.txt', 'w')

for riga in tab.find_all("tr")[1:]:
	elem = riga.find_all("td")
	squadra = elem[2].string[6:]
	if squadra == "Juve": squadra = "Juventus"
	punti = elem[3].get_text()
	g = elem[4].string
	v = elem[5].string
	n = elem[6].string
	p = elem[7].string
	gf = elem[8].string
	gs = elem[9].string
	temp = elem[10].get_text()
	ug = temp[2:len(temp)-2]	
	f.write('{ "squadra": "' + squadra + '", "ultime": "' + ug[0] + ug[2] + ug[4] + ug[6] + ug[8] + '" } \n') 

	
