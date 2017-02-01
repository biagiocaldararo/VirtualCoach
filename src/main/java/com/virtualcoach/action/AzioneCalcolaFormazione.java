package com.virtualcoach.action;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.utils.ComparatoreGiocatorePerRuolo;
import com.virtualcoach.utils.Modulo;
import com.virtualcoach.model.Giocatore;
import com.virtualcoach.model.Rosa;

public class AzioneCalcolaFormazione extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		String modulo = (String) request.getParameter("modulo");
		
		Rosa rosa = (Rosa) sessione.getAttribute("rosa");
				
		List<Giocatore> formazione = Modulo.calcolaModulo(rosa.getGiocatori(), modulo);
		List<List<Giocatore>> formazioneSpacchettata = splitGiocatori(formazione);
		
		List<Giocatore> riserve = Modulo.calcolaRiserve(rosa.getGiocatori(), formazione);
		Collections.sort(riserve, new ComparatoreGiocatorePerRuolo());
		
		sessione.setAttribute("formazione", formazioneSpacchettata);
		sessione.setAttribute("riserve", riserve);
		
		return "formazione";
	}
	
	public List<List<Giocatore>> splitGiocatori(List<Giocatore> giocatori){
		List<List<Giocatore>> formazione = new LinkedList<List<Giocatore>>();
		
		List<Giocatore> portiere = new LinkedList<Giocatore>();
		List<Giocatore> difensori = new LinkedList<Giocatore>();
		List<Giocatore> centrocampisti = new LinkedList<Giocatore>();
		List<Giocatore> attaccanti = new LinkedList<Giocatore>();
		
		for(Giocatore g: giocatori){
			if(g.getRuolo().equals("P")){
				portiere.add(g);
			}
			else if(g.getRuolo().equals("D")){
				difensori.add(g);
			}
			else if(g.getRuolo().equals("C")){
				centrocampisti.add(g);
			}
			else if(g.getRuolo().equals("A")){
				attaccanti.add(g);
			}
		}
		
		formazione.add(attaccanti);
		formazione.add(centrocampisti);
		formazione.add(difensori);
		formazione.add(portiere);
		
		return formazione;
	}
	
	public void print(List<Giocatore> giocatori) {
		for(Giocatore g : giocatori)
			System.out.println(g.getNome());	
	}
}
