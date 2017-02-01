package com.virtualcoach.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.model.Rosa;

public class AzioneRimuoviGiocatore extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		String giocatore = request.getParameter("giocatore_da_rimuovere");
		Rosa rosa = (Rosa) sessione.getAttribute("rosa");
		
		rosa.removeGiocatore(giocatore);
		
		return "rimuovi_giocatore";
	}

}
