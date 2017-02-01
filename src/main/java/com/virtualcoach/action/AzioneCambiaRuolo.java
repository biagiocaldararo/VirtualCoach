package com.virtualcoach.action;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.model.Rosa;
import com.virtualcoach.model.Giocatore;

public class AzioneCambiaRuolo extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		HttpSession sessione = request.getSession();
		
		String giocatore = request.getParameter("giocatore_da_modificare");
		Rosa rosa = (Rosa) sessione.getAttribute("rosa");
		
		int c = rosa.getNumero_centrocampisti();
		int a = rosa.getNumero_attaccanti();
		
		
		for(Giocatore g: rosa.getGiocatori()){
			if(g.getNome().equals(giocatore)){
				if(g.getRuolo().equals("C")){
					g.setRuolo("A");
					rosa.setNumero_attaccanti(a++);
					rosa.setNumero_centrocampisti(c--);
				}
				else {
					g.setRuolo("C");
					rosa.setNumero_attaccanti(a--);
					rosa.setNumero_centrocampisti(c++);
				}
				break;
			}
		}
		
		return "cambia_ruolo";
	}
}
