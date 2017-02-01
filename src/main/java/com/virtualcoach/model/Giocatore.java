package com.virtualcoach.model;

import com.virtualcoach.utils.Constants;

public class Giocatore {
	private String nome;
	private String ruolo;
	private Squadra squadra;
	private int quotazione_iniziale;
	private int quotazione_attuale;
	private double fantamedia;
	private boolean disponibile;
	private boolean titolare;
	private boolean jolly;
	private double percentuale;
	private double risultato_finale;

	double bonus_titolarità = Constants.BONUS_TITOLARITA;
	int bonus_casa = Constants.BONUS_CASA;

	public Giocatore(String nome) {
		this.nome = nome;
		this.setDisponibile(false);
		this.setJolly(false);
		this.risultato_finale = 0;
	}

	public Giocatore(String nome, String ruolo, Squadra squadra, int quotazione_iniziale, int quotazione_attuale,
			double fantamedia, boolean titolare, double percentuale) {
		this.nome = nome;
		this.ruolo = ruolo;
		this.squadra = squadra;
		this.quotazione_iniziale = quotazione_iniziale;
		this.quotazione_attuale = quotazione_attuale;
		this.fantamedia = fantamedia;
		this.titolare = titolare;
		this.percentuale = percentuale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public Squadra getSquadra() {
		return squadra;
	}

	public void setSquadra(Squadra squadra) {
		this.squadra = squadra;
	}

	public int getQuotazione_iniziale() {
		return quotazione_iniziale;
	}

	public void setQuotazione_iniziale(int quotazione_iniziale) {
		this.quotazione_iniziale = quotazione_iniziale;
	}

	public int getQuotazione_attuale() {
		return quotazione_attuale;
	}

	public void setQuotazione_attuale(int quotazione_attuale) {
		this.quotazione_attuale = quotazione_attuale;
	}

	public double getFantamedia() {
		return fantamedia;
	}
	
	public void setFantamedia(double fantamedia) {
		this.fantamedia = fantamedia;
	}

	public boolean isDisponibile() {
		return disponibile;
	}

	public void setDisponibile(boolean disponibile) {
		this.disponibile = disponibile;
	}

	public boolean isTitolare() {
		return titolare;
	}
	
	public void setTitolare(boolean titolare) {
		this.titolare = titolare;
	}
	
	public boolean isJolly() {
		return jolly;
	}

	public void setJolly(boolean jolly) {
		this.jolly = jolly;
	}
	
	public double getPercentuale() {
		return percentuale;
	}

	public void setPercentuale(double percentuale) {
		this.percentuale = percentuale;
	}

	public double getRisultato_finale() {
		return this.risultato_finale;
	}

	public void setRisultato_finale() {
		if (this.disponibile) {
			try {

				if (!this.titolare) {
					bonus_titolarità = 0;
				}

				if (!this.squadra.isGiocaInCasa()) {
					bonus_casa = 0;
				}

				double risultato_parziale = this.quotazione_attuale
						+ ((this.quotazione_attuale - this.quotazione_iniziale)/2)
						+ ((this.squadra.getPunti() - this.squadra.getProssimo_avversario().getPunti()) / 2)
						+ bonus_casa + this.fantamedia + (this.squadra.getValore_andamento()
								- this.squadra.getProssimo_avversario().getValore_andamento());
				
				double risultato_calcolato = risultato_parziale * (this.percentuale + bonus_titolarità);

				if (risultato_calcolato > 0) {
					this.risultato_finale = risultato_calcolato;
				}

			} catch (Exception e) {
				this.risultato_finale = -1;
			}
		}
		else
			this.risultato_finale = -1;
	}

	public void printAll() {
		System.out.println("QA: " + this.quotazione_attuale);
		System.out.println("QI: " + this.quotazione_iniziale);
		System.out.println(
				"QA+((QA-QI)/2): " + (this.quotazione_attuale + ((this.quotazione_attuale - this.quotazione_iniziale)/2)));
		//System.out.println("PERC: " + this.percentuale);
		//System.out.println("INDT: " + indice_di_titolarità);
		//System.out.println("PERC*INDT: " + this.percentuale * indice_di_titolarità);
		System.out.println("FMEDIA: " + this.fantamedia);
		System.out.print("SQUADRA: " + this.squadra.getNome() + ", ");
		System.out.print("PUNTI: " + this.squadra.getPunti() + ", ");
		System.out.print("CASA: " + this.squadra.isGiocaInCasa() + ", ");
		System.out.println("BONUS CASA: " + bonus_casa);
		System.out.print("PROSAVV: " + this.squadra.getProssimo_avversario().getNome() + ", ");
		System.out.println("PUNTI: " + this.squadra.getProssimo_avversario().getPunti() + ", ");
		System.out.println("DIFFP: " + (this.squadra.getPunti() - this.squadra.getProssimo_avversario().getPunti()));
		System.out.println("AND + VAND: " + this.squadra.getAndamento() + " - " + this.squadra.getValore_andamento());
		System.out.println("DAND: "
				+ (this.squadra.getValore_andamento() - this.squadra.getProssimo_avversario().getValore_andamento()));
		System.out.println("PERC: " + this.percentuale);
		System.out.println("BTIT: " + bonus_titolarità);
		System.out.println("PERC+BTIT: " + (this.percentuale + bonus_titolarità));
	}
}
