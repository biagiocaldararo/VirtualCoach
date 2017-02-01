package com.virtualcoach.controller;

import java.io.IOException;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.virtualcoach.action.Azione;

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map<String, String> comandi; 
	private Map<String, String> esiti; 

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prossimaPagina = null;
		String comando = request.getServletPath();
		String nomeAzione = this.comandi.get(comando);
		
		if (nomeAzione==null)
			prossimaPagina = "/error.jsp";
		else {
			Azione azione = null;
			try {
				azione = (Azione)Class.forName(nomeAzione).newInstance();
				String esitoAzione = azione.esegui(request);
				prossimaPagina = this.esiti.get(esitoAzione);
			} 
			catch (InstantiationException e) {
				prossimaPagina = "/error.jsp";
			} 
			catch (IllegalAccessException e) {
				prossimaPagina = "/error.jsp";
			} 
			catch (ClassNotFoundException e) {
				prossimaPagina = "/error.jsp";
			}
		}
		
		ServletContext application  = getServletContext();
		RequestDispatcher rd = application.getRequestDispatcher(prossimaPagina);
		rd.forward(request, response);		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public void init() {
		this.comandi = new HashMap<String, String>();
		this.comandi.put("/aggiungiGiocatore.do","com.virtualcoach.action.AzioneAggiungiGiocatore");
		this.comandi.put("/infoGiocatore.do","com.virtualcoach.action.AzioneInfoGiocatore");
		this.comandi.put("/calcolaFormazione.do","com.virtualcoach.action.AzioneCalcolaFormazione");
		this.comandi.put("/rimuoviGiocatore.do","com.virtualcoach.action.AzioneRimuoviGiocatore");
		this.comandi.put("/cambiaRuolo.do","com.virtualcoach.action.AzioneCambiaRuolo");
		this.comandi.put("/importaRosa.do","com.virtualcoach.action.AzioneImportaRosa");
		this.comandi.put("/esportaRosa.do","com.virtualcoach.action.AzioneEsportaRosa");
		this.comandi.put("/rimuoviRosa.do","com.virtualcoach.action.AzioneRimuoviRosa");
		
		//this.comandi.put("/login.do","com.bgsshop.action.AzioneLogin");

		this.esiti= new HashMap<String, String>();
		this.esiti.put("aggiungi_giocatore","/index.jsp");
		this.esiti.put("rimuovi_giocatore","/index.jsp");
		this.esiti.put("info","/index.jsp");
		this.esiti.put("cambia_ruolo","/index.jsp");
		this.esiti.put("importa_rosa","/index.jsp");
		this.esiti.put("esporta_rosa","/index.jsp");
		this.esiti.put("rimuovi_rosa","/index.jsp");
		this.esiti.put("formazione","/index.jsp");
		this.esiti.put("error", "/error.jsp");
		//this.esiti.put("loginFallito","/loginFallito.jsp");

	}
}
