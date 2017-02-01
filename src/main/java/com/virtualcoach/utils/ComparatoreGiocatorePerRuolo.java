package com.virtualcoach.utils;

import java.util.Comparator;

import com.virtualcoach.model.Giocatore;

public class ComparatoreGiocatorePerRuolo implements Comparator<Giocatore> {

	public int compare(Giocatore g1, Giocatore g2) {
		if(g1.getRuolo().equals(g2.getRuolo())){
			return ((Double)g2.getRisultato_finale()).compareTo(((Double)g1.getRisultato_finale()));
		}
		
		return g1.getRuolo().compareTo(g2.getRuolo());
	}

}
