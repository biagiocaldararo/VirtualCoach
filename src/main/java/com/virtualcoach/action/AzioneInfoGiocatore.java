package com.virtualcoach.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.model.Giocatore;
import com.virtualcoach.model.Rosa;

public class AzioneInfoGiocatore extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();

		String nome_giocatore = request.getParameter("giocatore_info");
		Rosa rosa = (Rosa) sessione.getAttribute("rosa");

		for (Giocatore g : rosa.getGiocatori()) {
			if (g.getNome().equals(nome_giocatore)){ 
				sessione.setAttribute("giocatoreSelezionato", g);
				break;
			}
		}

		return "info";
	}
}
