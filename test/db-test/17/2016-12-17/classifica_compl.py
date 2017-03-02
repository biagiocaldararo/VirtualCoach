import json

f1 = open('classifica.txt', 'r')
f2 = open('precedenti.txt', 'r')

f = open('classifica_compl.json', 'w')

d1 = f1.readline()
d2 = f2.readline()
l1 = []

while (d1 != ""):
	l1 += [d1[:len(d1)-2]]
	d1 = f1.readline()

while (d2 != ""):
	o2 = json.loads(d2)
	ultime = o2["ultime"]
	squadra = o2["squadra"]
	for elem in l1:
		o1 = json.loads(elem)
		if o1["squadra"] == squadra:
			f.write(elem[:len(elem)-1] + ', "ultime": "' + ultime + '" }\n')
	d2 = f2.readline()

