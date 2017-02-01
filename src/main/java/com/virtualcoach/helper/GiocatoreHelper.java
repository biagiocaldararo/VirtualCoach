package com.virtualcoach.helper;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.virtualcoach.model.Rosa;

public class GiocatoreHelper extends Helper {
	private String giocatore_da_aggiungere;
	private Map<String, String> errori;

	public GiocatoreHelper(HttpServletRequest request) {
		super(request);
		this.giocatore_da_aggiungere = request.getParameter("giocatore_da_aggiungere");
		this.errori = new HashMap<String, String>();
	}

	@Override
	public boolean convalida() {
		boolean tuttoOk = true;

		HttpSession sessione = request.getSession();
		Rosa rosa = (Rosa) sessione.getAttribute("rosa");

		if (this.giocatore_da_aggiungere == null || giocatore_da_aggiungere.isEmpty()) {
			this.errori.put("giocatore_da_aggiungere", "Inserisci il nome di un giocatore per aggiungerlo alla rosa");
			tuttoOk = false;
		} else if (rosa != null) {
			if (rosa.contieneGiocatore(this.giocatore_da_aggiungere)) {
				this.errori.put("giocatore_da_aggiungere", "Questo giocatore risulta già essere in rosa");
				tuttoOk = false;
			}
		}

		this.request.setAttribute("errori", this.errori);

		return tuttoOk;
	}
}
