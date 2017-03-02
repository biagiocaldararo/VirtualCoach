package com.virtualcoach.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.virtualcoach.model.Giocatore;

public class Modulo {
	public static List<Giocatore> calcolaModulo(List<Giocatore> giocatori, String modulo) {
		
		List<Giocatore> rosa = new LinkedList<Giocatore>();
		rosa.addAll(giocatori);
		
		Collections.sort(rosa, new ComparatoreGiocatori());

		List<Giocatore> formazione = new LinkedList<Giocatore>();
		
		if (modulo.equals("consigliato")){
			formazione = generaMigliore(rosa);
		}
		else
			formazione = generaModulo(rosa, modulo);

		return formazione;
	}

	private static List<Giocatore> generaMigliore(List<Giocatore> giocatori) {

		List<Double> punteggi = new ArrayList<Double>();
		List<Giocatore> migliore = new ArrayList<Giocatore>();

		List<Giocatore> modulo1 = generaModulo(giocatori, "442");
		List<Giocatore> modulo2 = generaModulo(giocatori, "433");
		List<Giocatore> modulo3 = generaModulo(giocatori, "352");
		List<Giocatore> modulo4 = generaModulo(giocatori, "451");
		List<Giocatore> modulo5 = generaModulo(giocatori, "541");
		List<Giocatore> modulo6 = generaModulo(giocatori, "532");
		List<Giocatore> modulo7 = generaModulo(giocatori, "343");
		//List<Giocatore> modulo8 = generaModulo(giocatori, "424");

		punteggi.add(getPunteggio(modulo1));
		punteggi.add(getPunteggio(modulo2));
		punteggi.add(getPunteggio(modulo3));
		punteggi.add(getPunteggio(modulo4));
		punteggi.add(getPunteggio(modulo5));
		punteggi.add(getPunteggio(modulo6));
		punteggi.add(getPunteggio(modulo7));
		//punteggi.add(getPunteggio(modulo8));

		switch (getMaxIndex(punteggi)) {
		case 0:
			migliore = modulo1;
			break;
		case 1:
			migliore = modulo2;
			break;
		case 2:
			migliore = modulo3;
			break;
		case 3:
			migliore = modulo4;
			break;
		case 4:
			migliore = modulo5;
			break;
		case 5:
			migliore = modulo6;
			break;
		case 6:
			migliore = modulo7;
			break;
		//case 7:
		//	migliore = modulo8;
		//	break;
		}

		return migliore;
	}

	private static int getMaxIndex(List<Double> punteggi) {

		int index = 0;
		double max = punteggi.get(0);

		for (int i = 1; i < punteggi.size(); i++) {
			double valore = punteggi.get(i);
			if (max < valore) {
				max = valore;
				index = i;
			}
		}
		
		return index;
	}

	private static Double getPunteggio(List<Giocatore> modulo) {

		Iterator<Giocatore> i = modulo.iterator();
		Double somma = 0.0;

		while (i.hasNext()) {
			somma += i.next().getRisultato_finale();
		}

		return somma;
	}

	private static List<Giocatore> generaModulo(List<Giocatore> giocatori, String s) {

		int numP = 1;
		int numD = Character.getNumericValue(s.charAt(0));
		int numC = Character.getNumericValue(s.charAt(1));
		int numA = Character.getNumericValue(s.charAt(2));

		List<Giocatore> modulo = new ArrayList<Giocatore>();

		Iterator<Giocatore> it = giocatori.iterator();

		while (numP != 0 || numD != 0 || numC != 0 || numA != 0) {

			if(!it.hasNext())
				return modulo;
			
			Giocatore g = it.next();

			switch (g.getRuolo().charAt(0)) {
			case 'P':
				if (numP != 0) {
					modulo.add(g);
					numP = numP - 1;
				}
				break;
			case 'D':
				if (numD != 0) {
					modulo.add(g);
					numD = numD - 1;
				}
				break;
			case 'C':
				if (numC != 0) {
					modulo.add(g);
					numC = numC - 1;
				}
				break;
			case 'A':
				if (numA != 0) {
					modulo.add(g);
					numA = numA - 1;
				}
			}
		}

		return modulo;
	}

	public static List<Giocatore> calcolaRiserve(List<Giocatore> giocatori, List<Giocatore> formazione) {
		List<Giocatore> riserve = new LinkedList<Giocatore>();
		riserve.addAll(giocatori);
		
		for (Giocatore p1: formazione){
			for (Giocatore p2: giocatori){
				if (p2.getNome().equals(p1.getNome())){
					riserve.remove(p2);
					break;
				}
			}
		}
		
		return riserve;
	}
}
