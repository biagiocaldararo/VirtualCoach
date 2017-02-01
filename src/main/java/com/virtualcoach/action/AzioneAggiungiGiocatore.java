package com.virtualcoach.action;

import java.text.ParseException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;

import com.virtualcoach.helper.GiocatoreHelper;
import com.virtualcoach.helper.Helper;
import com.virtualcoach.model.Giocatore;
import com.virtualcoach.model.Rosa;
import com.virtualcoach.model.Squadra;
import com.virtualcoach.persistence.MongoDAO;

public class AzioneAggiungiGiocatore extends Azione {

	@Override
	public String esegui(HttpServletRequest request) throws ServletException {
		Helper helper = new GiocatoreHelper(request);
		String destination = "aggiungi_giocatore";

		if (helper.convalida()) {
			HttpSession sessione = request.getSession();
			MongoDAO dao = new MongoDAO("test");

			String nome_giocatore = request.getParameter("giocatore_da_aggiungere");

			Rosa rosa = (Rosa) sessione.getAttribute("rosa");

			if (rosa == null) {
				rosa = new Rosa();
			}

			try {

				// recupero i documenti dal db
				Document doc_giocatore = dao.getDocument("quotazioni", "nome", nome_giocatore);
				Document doc_probabili = dao.getDocument("probabili", "nome", nome_giocatore);
				Document doc_squadra = dao.getDocument("classifica", "squadra", doc_giocatore.getString("squadra"));
				Document doc_stats = dao.getDocument("statistiche", "nome", nome_giocatore);
				Document doc_giornata = dao.getGiornata();

				// creo le istanze del model necessarie
				Giocatore giocatore = new Giocatore(doc_giocatore.getString("nome"));
				Squadra squadra = new Squadra(doc_squadra.getString("squadra"));

				List<String> info_avv = (List<String>) doc_giornata.get(squadra.getNome());
				Document doc_squadra_avv = dao.getDocument("classifica", "squadra", info_avv.get(0));
				Squadra squadra_avv = new Squadra(info_avv.get(0));

				// setto le proprietà del giocatore
				giocatore.setRuolo(doc_giocatore.getString("ruolo"));
				giocatore.setQuotazione_iniziale(doc_giocatore.getInteger("qi"));
				giocatore.setQuotazione_attuale(doc_giocatore.getInteger("qa"));
				giocatore.setFantamedia(doc_stats.getDouble("fmedia"));
				giocatore.setSquadra(squadra);

				if (!doc_probabili.isEmpty()) {
					giocatore.setTitolare(doc_probabili.getString("tit").equals("SI"));
					giocatore.setPercentuale(doc_probabili.getInteger("perc").doubleValue() / 100);
					giocatore.setDisponibile(true);
				}

				// setto le proprietà della squadraRosa
				squadra.setPunti(doc_squadra.getInteger("punti"));
				squadra.setAndamento(doc_squadra.getString("ultime"));
				squadra.setProssimo_avversario(squadra_avv);
				squadra.setGiocaInCasa(info_avv.get(1).equals("c"));

				// setto le proprietà della squadra avversaria
				squadra_avv.setPunti(doc_squadra_avv.getInteger("punti"));
				squadra_avv.setAndamento(doc_squadra_avv.getString("ultime"));

				// calcolo il risultato finale
				giocatore.setRisultato_finale();

				giocatore.printAll();

				rosa.addGiocatore(giocatore);

				sessione.setAttribute("rosa", rosa);

			} catch (ParseException e) {
				destination = "error";
			} catch (NullPointerException ex){
				request.setAttribute("nontrovato", "Giocatore non trovato");
			}
		}

		return destination;
	}

}
