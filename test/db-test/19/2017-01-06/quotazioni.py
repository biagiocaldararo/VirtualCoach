import urllib2
from bs4 import BeautifulSoup

url = "http://www.fantagiaveno.it/quotazioni-calciatori.asp"
page = urllib2.urlopen(url)

soup = BeautifulSoup(page,'html.parser')

f = open('quotazioni.json', 'w')

lista1 = ["IAGO FALQUE", "BASTOS", "DODO'", "FELIPE", "PATRIC", "SAMIR", "WALLACE", "HERNANES", "SUSO", "EL SHAARAWY", "EWANDRO", "MATOS", "SIMY", "ALEX SANDRO", "JOAO MARIO", "JOAO PEDRO", "JUAN JESUS", "DA COSTA", "MATHEUS PEREIRA", "LUIZ ADRIANO"]

lista2 = ["BARRETO EDGAR OSVALDO", "ALVAREZ RICARDO", "PEREIRA PEDRO", "DONNARUMMA GIANLUIGI", "ROMAGNOLI ALESSIO", "LOCATELLI MANUEL", "GOMEZ GUSTAVO", "ZAPATA CRISTIAN", "FERNANDEZ MATIAS", "ZAPATA DUVAN", "ZAMPANO FRANCESCO", "MASIELLO ANDREA", "GOMEZ ALEJANDRO", "RADU IONUT", "OLIVERA MAXI", "TELLO CRISTIAN", "FERRARI GIANMARCO", "MAURI JOSE'", "TELLO ANDRES", "COSTA FILIPPO","KEITA BALDE DIAO", "GOMIS ALFRED", "FERRARI ALEX", "GONZALEZ GIANCARLO", "RIGONI NICOLA", "COPPOLA DOMENICO", "INSIGNE ROBERTO"]

lista3 = ["DE MAIO SEBASTIAN", "DE SCIGLIO MATTIA", "DE SILVESTRI LORENZO","DE VRIJ STEFAN", "DE GUZMAN JONATHAN", "DE PAUL RODRIGO", "DE ROSSI DANIELE", "DE GIORGIO PIETRO", "DEL SOLE FERDINANDO", "BORJA VALERO IGLESIAS", "RAFAEL CABRAL BARBOSA", "MAXI LOPEZ GASTON", "FELIPE ANDERSON PEREIRA GOMES", "MARIO RUI SILVA DUARTE", "EL KADDOURI OMAR", "FLORO FLORES ANTONIO", "LO FASO SIMONE"]

# Ritorna fino al primo spazio della stringa se x = 1, fino al secondo se x = 2
def nome1(n,x):

	c = 0
	trovato = False
	i = 0
	p = 0
	
	if x == 1:
		while not trovato:
			if n[i] == " ":
				p = i
				trovato = True
			i += 1
	else:
		while not trovato:
			if n[i] == " " and c == 1:
				p = i
				trovato = True
			elif n[i] == " ":
				c += 1 
			i += 1	

	return n[:p]

# Ritorna il cognome e la prima lettera del nome
def nome2(n):

	c = 0
	trovato = False
	i = 0
	
	while not trovato:
		if n[i] == " ":
			c = i
			trovato = True
		i += 1

	return n[:c+2]	


def get_nome(n):

	if n in lista1: return n
	elif n in lista2: return nome2(n)
	elif n in lista3: return nome1(n,2)
	elif n == "DOS SANTOS CLAITON": return "CLAITON"  
	elif n == "RODRIGUEZ GONZALO": return "RODRIGUEZ GO"
	elif n == "FERNANDES BRUNO MIGUEL": return "BRUNO FERNANDES"
	elif n == "VELOSO MIGUEL": return "MIGUEL VELOSO"
	elif n == "ELY RODRIGO": return "RODRIGO ELY"
	elif n == "SOSA JOSE'": return "JOSE' SOSA"
	elif n == "ALVES DANI": return "DANI ALVES"
	elif n == "ALVES BRUNO": return "BRUNO ALVES"
	elif n == "MELO FELIPE": return "FELIPE MELO"
	elif n == "ALBERTO LUIS": return "LUIS ALBERTO"
	elif n == "PERES BRUNO": return "BRUNO PERES"
	elif n == "HENRIQUE BRUNO": return "BRUNO HENRIQUE"
	elif n == "EVANGELISTA LUCAS": return "LUCAS EVANGELISTA"
	elif n == "DI GENNARO DAVIDE": return "DI GENNARO D"
	elif n == "DI FRANCESCO FEDERICO": return "DI FRANCESCO F"
	elif n == "GABRIEL BARBOSA": return "GABIGOL"
	else: return nome1(n,1)
	
		

tab = soup.find("table", {"class":"Border None"})
tabella = tab.find_all('tr')

for riga in tabella[1:len(tabella)-2]:
	
	elem = riga.find_all('td')
	nome = get_nome((elem[2].get_text())[1:].upper())
	ruolo = elem[1].get_text()
	squadra = elem[3].get_text()[1:]
	qa = elem[4].get_text()
	qi = elem[5].get_text()
	
	if len(ruolo) == 2:
		ruolo = ruolo[0:1]
	f.write ('{ "nome": "' + nome + '", "ruolo": "' + ruolo + '", "squadra": "' + squadra + '", "qa": ' + qa[:len(qa)-1] + ', "qi": ' + qi[:len(qi)-1] + ' }\n')
	#print nome

f.close()		 










 	
