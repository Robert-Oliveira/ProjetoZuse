package br.com.projeto.model;

public class Alimento {
	
	private int cdAlimento;
	private String nmAlimento;
	private String tpMedida;
	private int qtAlimento;
	private int calorias;
	private double qtCarbo;
	private double qtProteina;
	private double qtGordura;
	
	
	public int getCdAlimento() {
		return cdAlimento;
	}
	public String getNmAlimento() {
		return nmAlimento;
	}
	public String getTpMedida() {
		return tpMedida;
	}
	public int getQtAlimento() {
		return qtAlimento;
	}
	public int getCalorias() {
		return calorias;
	}
	public double getQtCarbo() {
		return qtCarbo;
	}
	public double getQtProteina() {
		return qtProteina;
	}
	public double getQtGordura() {
		return qtGordura;
	}
	public void setCdAlimento(int cdAlimento) {
		this.cdAlimento = cdAlimento;
	}
	public void setNmAlimento(String nmAlimento) {
		this.nmAlimento = nmAlimento;
	}
	public void setTpMedida(String tpMedida) {
		this.tpMedida = tpMedida;
	}
	public void setQtAlimento(int qtAlimento) {
		this.qtAlimento = qtAlimento;
	}
	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	public void setQtCarbo(double qtCarbo) {
		this.qtCarbo = qtCarbo;
	}
	public void setQtProteina(double qtProteina) {
		this.qtProteina = qtProteina;
	}
	public void setQtGordura(double qtGordura) {
		this.qtGordura = qtGordura;
	}
	
	
	@Override
	public String toString() {
		return "Alimento [cdAlimento=" + cdAlimento + ", nmAlimento=" + nmAlimento + ", tpMedida=" + tpMedida
				+ ", qtAlimento=" + qtAlimento + ", calorias=" + calorias + ", qtCarbo=" + qtCarbo + ", qtProteina="
				+ qtProteina + ", qtGordura=" + qtGordura + "]";
	}
	
}
