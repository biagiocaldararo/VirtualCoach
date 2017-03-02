import urllib2
from bs4 import BeautifulSoup

url = "http://www.gazzetta.it/calcio/fantanews/statistiche/serie-a-2016-17/"
page = urllib2.urlopen(url)

soup = BeautifulSoup(page,'html.parser')

f = open('statistiche.json', 'w')

lista1 = ["BASTOS ", "PATRIC ", "SAMIR ", "WALLACE ", "HERNANES ", "SUSO ", "EWANDRO ", "SIMY ", "ALEX SANDRO ", "JOAO MARIO ", "JOAO PEDRO "]

lista2 = ["BARRETO E.", "ALVAREZ R.", "PEREIRA P.", "DONNARUMMA G.", "ROMAGNOLI A.", "LOCATELLI M.", "GOMEZ G.", "ZAPATA C.", "FERNANDEZ M.", "ZAPATA D.", "ZAMPANO F.", "MASIELLO A.", "GOMEZ A.", "RADU I.", "OLIVERA M.", "TELLO C.", "FERRARI G.", "MAURI J.", "TELLO A.", "COSTA F.","KEITA B.", "GOMIS A.", "FERRARI A.", "GONZALEZ G.", "RIGONI N.", "COPPOLA D.", "INSIGNE R."]

lista3 = ["DE MAIO S.", "DE SCIGLIO M.", "DE SILVESTRI L.","DE VRIJ S.", "DE GUZMAN J.", "DE PAUL R.", "DE ROSSI D.", "DE GIORGIO P.", "DEL SOLE F.", "EL KADDOURI O.", "FLORO FLORES A.", "LO FASO S."]

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

	if n in lista1: return n[:len(n)-1]
	elif n in lista2: return nome2(n)	
	elif n in lista3: return nome1(n,2)
	elif n == "IAGO F.": return "IAGO FALQUE"
	elif n == "MILINKOVIC S.": return "MILINKOVIC-SAVIC"
	elif n == "DODO ": return "DODO'"
	elif n == "FELIPE D.": return "FELIPE"
	elif n == "EL SHAARAWY S.": return "EL SHAARAWY"
	elif n == "MATOS R.": return "MATOS"
	elif n == "JESUS J.": return "JUAN JESUS"
	elif n == "DA COSTA A.": return "DA COSTA"
	elif n == "MATHEUS P.": return "MATHEUS PEREIRA"
	elif n == "ADRIANO L.": return "LUIZ ADRIANO"		
	elif n == "DOS SANTOS C.": return "CLAITON"  
	elif n == "RODRIGUEZ G.": return "RODRIGUEZ GO"
	elif n == "FERNANDES B.": return "BRUNO FERNANDES"
	elif n == "VELOSO M.": return "MIGUEL VELOSO"
	elif n == "ELY R.": return "RODRIGO ELY"
	elif n == "SOSA J.": return "JOSE' SOSA"
	elif n == "ALVES D.": return "DANI ALVES"
	elif n == "ALVES B.": return "BRUNO ALVES"
	elif n == "MELO F.": return "FELIPE MELO"
	elif n == "ALBERTO L.": return "LUIS ALBERTO"
	elif n == "PERES B.": return "BRUNO PERES"
	elif n == "HENRIQUE B.": return "BRUNO HENRIQUE"
	elif n == "EVANGELISTA L.": return "LUCAS EVANGELISTA"
	elif n == "DI GENNARO D.": return "DI GENNARO D"
	elif n == "DI FRANCESCO F.": return "DI FRANCESCO F"
	elif n == "GABRIEL B.": return "GABIGOL"
	elif n == "VALERO B.": return "BORJA VALERO"
	elif n == "RAFAEL C.": return "RAFAEL CABRAL"
	elif n == "LOPEZ M.": return "MAXI LOPEZ"
	elif n == "ANDERSON F.": return "FELIPE ANDERSON"
	elif n == "RUI M.": return "MARIO RUI"
	elif n == "GAKPE S.": return "GAKPE'"
	elif n == "BOYE L.": return "BOYE'"
	elif n == "DIOUSSE A.": return "DIOUSSE'"
	elif n == "KESSIE F.": return "KESSIE'"
	elif n == "DRAME B.": return "DRAME'"
	elif n == "GNAHORE E.": return "GNAHORE'"
	elif n == "DAMBROSIO D.": return "D'AMBROSIO"
	elif n == "DALESSANDRO M.": return "D'ALESSANDRO"
	elif n == "DELLORCO C.": return "DELL'ORCO"
	else: return nome1(n,1)

righe = soup.find_all("tr")

def supp(s,n):
	if s == "-" and n == 0:
		return "0"	
	elif s == "-" and n == 1:
		return "6"
	return s

def supp2(s):
	if len(s) == 9:		
		return s[2:3]
	elif len(s) == 19:
		 return s[10:11]
	else: return s[2:4]	

for riga in righe[1:]:
	td = riga.find_all("td")
	
	nome = get_nome(td[2].a.string.upper())	
	pg = supp(td[5].string, 0)
	gol = supp(supp2(td[6].get_text()),0)
	ass = supp(td[7].string, 0)
	amm = supp(td[8].string, 0)
	esp = supp(td[9].string, 0) 
	mv =  supp(td[14].string, 1)
	mm = supp(td[15].string, 1)
	if mm in ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15']:
		mm += ".0"
	f.write('{ "nome" : "' + nome + '", "pg" : ' + pg + ', "gol" : ' + gol + ', "assist" : ' + ass + ', "gialli" : ' + amm + ', "rossi" : ' + esp + ', "media" : ' + mv + ', "fmedia" : ' + mm + ' }\n')



