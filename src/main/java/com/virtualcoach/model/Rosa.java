package com.virtualcoach.model;

import java.util.LinkedList;
import java.util.List;

public class Rosa {
	private List<Giocatore> giocatori;
	private List<Giocatore> formazione;
	private boolean vuota;
	private int numero_portieri;
	private int numero_difensori;
	private int numero_centrocampisti;
	private int numero_attaccanti;

	public Rosa() {
		this.setGiocatori(new LinkedList<Giocatore>());
		this.setFormazione(new LinkedList<Giocatore>());
		this.numero_portieri = 0;
		this.numero_difensori = 0;
		this.numero_centrocampisti = 0;
		this.numero_attaccanti = 0;
		this.vuota = true;
	}

	public List<Giocatore> getGiocatori() {
		return giocatori;
	}

	public void setGiocatori(List<Giocatore> giocatori) {
		if (giocatori.isEmpty())
			this.vuota = true;
		this.giocatori = giocatori;
	}
	
	public List<Giocatore> getFormazione() {
		return formazione;
	}

	public void setFormazione(List<Giocatore> formazione) {
		this.formazione = formazione;
	}
	
	public boolean isVuota() {
		return vuota;
	}

	public void setVuota(boolean vuota) {
		this.vuota = vuota;
	}

	public int getNumero_portieri() {
		return numero_portieri;
	}

	public void setNumero_portieri(int numero_portieri) {
		this.numero_portieri = numero_portieri;
	}

	public int getNumero_difensori() {
		return numero_difensori;
	}

	public void setNumero_difensori(int numero_difensori) {			
		this.numero_difensori = numero_difensori;
	}

	public int getNumero_centrocampisti() {
		return numero_centrocampisti;
	}

	public void setNumero_centrocampisti(int numero_centrocampisti) {
		this.numero_centrocampisti = numero_centrocampisti;
	}

	public int getNumero_attaccanti() {
		return numero_attaccanti;
	}

	public void setNumero_attaccanti(int numero_attaccanti) {
		this.numero_attaccanti = numero_attaccanti;
	}

	public void addGiocatore(Giocatore g) {
		this.giocatori.add(g);
		this.vuota = false;
		switch (g.getRuolo().charAt(0)) {
		case 'P':
			this.numero_portieri++;
			break;
		case 'D':
			this.numero_difensori++;
			break;
		case 'C':
			this.numero_centrocampisti++;
			break;
		case 'T':
			g.setJolly(true);
			g.setRuolo("C");
			this.numero_centrocampisti++;
			break;
		case 'A':
			this.numero_attaccanti++;
			break;
		}
	}
	
	public void removeGiocatore(String giocatore) {
		for(Giocatore g: this.getGiocatori()){
			if(g.getNome().equals(giocatore)){
				this.getGiocatori().remove(g);
				switch (g.getRuolo().charAt(0)) {
				case 'P':
					this.numero_portieri--;
					break;
				case 'D':
					this.numero_difensori--;
					break;
				case 'C':
				case 'T':
					this.numero_centrocampisti--;
					break;
				case 'A':
					this.numero_attaccanti--;
					break;
				}
				break;
			}
		}
		if(this.giocatori.isEmpty())
			this.vuota = true;
	}
	
	public List<Giocatore> schieraFormazione(){
		return this.formazione;
	}
	
	public boolean contieneGiocatore(String giocatore){
		for(Giocatore g: this.giocatori)
			if(g.getNome().equals(giocatore))
				return true;
		
		return false;
	}
}
