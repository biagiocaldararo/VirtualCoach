package com.virtualcoach.utils;

import java.util.Comparator;
import com.virtualcoach.model.*;

public class ComparatoreGiocatori implements Comparator<Giocatore> {

	public int compare(Giocatore g1, Giocatore g2) {
		return ((Double)g2.getRisultato_finale()).compareTo(((Double)g1.getRisultato_finale()));
	}
}
