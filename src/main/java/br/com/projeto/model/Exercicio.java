package br.com.projeto.model;

public class Exercicio {

	private int cdExercicio; 
	private String nmExercicio; 
	private int nrCalorias; 


	public int getCdExercicio() {
		return cdExercicio;
	}


	public void setCdExercicio(int cdExercicio) {
		this.cdExercicio = cdExercicio;
	}


	public String getNmExercicio() {
		return nmExercicio;
	}


	public void setNmExercicio(String nmExercicio) {
		this.nmExercicio = nmExercicio;
	}

	public int getNrCalorias() {
		return nrCalorias;
	}

	public void setNrCalorias(int nrCalorias) {
		this.nrCalorias = nrCalorias;
	}

	@Override
	public String toString() {
		return "Exercicio [cd_exercio=" + cdExercicio + ", nm_exercico=" + nmExercicio + ", nr_calorias=" + nrCalorias
				+ "]";
	}


}
