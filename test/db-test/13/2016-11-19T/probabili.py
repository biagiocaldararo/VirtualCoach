import urllib2
from bs4 import BeautifulSoup

url = "http://www.fantagazzetta.com/probabili-formazioni-serie-a"
page = urllib2.urlopen(url)

soup = BeautifulSoup(page,'html.parser')

f = open('probabili.json','w')

for player in soup.find_all("div", {"class":"pgroup lf"}):
	nome = player.a.string	
	if player.find("span", {"class":"perc"}) != None:
		perc = player.find("span", {"class":"perc"}).string
		f.write('{ "nome": "' + nome + '", "perc": ' + perc[0:len(perc)-1] + ', "tit": "SI" }\n')
	else:
		perc = player.find("div", {"class":"is pull-left bold"}).get_text()[1:]
		f.write('{ "nome": "' + nome + '", "perc": ' + perc[5:len(perc)-1]+ ', "tit": "NO" }\n')

for player in soup.find_all("div", {"class":"pgroup rt"}):
	nome = player.a.string	
	if player.find("span", {"class":"perc"}) != None:
		perc = player.find("span", {"class":"perc"}).string
		f.write('{ "nome": "' + nome + '", "perc": ' + perc[0:len(perc)-1] + ', "tit": "SI" }\n')
	else:
		perc = player.find("div", {"class":"is pull-right bold"}).get_text()
		f.write('{ "nome": "' + nome + '", "perc": ' + perc[5:len(perc)-2] + ', "tit": "NO" }\n')

f.close()
