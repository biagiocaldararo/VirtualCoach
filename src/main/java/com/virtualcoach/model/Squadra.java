package com.virtualcoach.model;

public class Squadra {
	private String nome;
	private Squadra prossimo_avversario;
	private int punti;
	private boolean giocaInCasa;
	private String andamento;
	private int valore_andamento;
	
	public Squadra(String nome){
		this.setNome(nome);
	}
	
	public Squadra(String nome, int punti, boolean giocaInCasa, String andamento){
		this.setNome(nome);
		this.setPunti(punti);
		this.setGiocaInCasa(giocaInCasa);
		this.setAndamento(andamento);
	}			

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Squadra getProssimo_avversario() {
		return prossimo_avversario;
	}

	public void setProssimo_avversario(Squadra prossimo_avversario) {
		this.prossimo_avversario = prossimo_avversario;
	}

	public int getPunti() {
		return punti;
	}

	public void setPunti(int punti) {
		this.punti = punti;
	}

	public boolean isGiocaInCasa() {
		return giocaInCasa;
	}

	public void setGiocaInCasa(boolean giocaInCasa) {
		this.giocaInCasa = giocaInCasa;
	}	

	public String getAndamento() {
		return andamento;
	}

	public void setAndamento(String andamento) {
		this.andamento = andamento;
	}
	
	public int getValore_andamento(){
		this.valore_andamento = 0;
		
		if(!this.andamento.isEmpty() && this.andamento!=null){
			for(int i=0;i<this.andamento.length();i++){
				switch(this.andamento.charAt(i)){
				case 'V':
					this.valore_andamento++;
					break;
				case 'P':
					this.valore_andamento--;
					break;
				case 'N':
					break;
				}
			}
		}
		return this.valore_andamento;
	}
}