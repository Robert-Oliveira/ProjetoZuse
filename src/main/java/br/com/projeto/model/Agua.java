package br.com.projeto.model;

public class Agua {

	private int cdAgua; 
	private String dtAgua; 
	private int qtIngerida;
	private int cdUsuario;


	public int getCdAgua() {
		return cdAgua;
	}

	public void setCdAgua(int cdAgua) {
		this.cdAgua = cdAgua;
	}

	public String getDtAgua() {
		return dtAgua;
	}


	public void setDtAgua(String string) {
		this.dtAgua = string;
	}

	public int getQtIngerida() {
		return qtIngerida;
	}

	public void setQtIngerida(int qtIngerida) {
		this.qtIngerida = qtIngerida;
	}

	public int getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	

	@Override
	public String toString() {
		return "Agua [cd_agua=" + cdAgua + ", dt_agua=" + dtAgua + ", qt_ingerida=" + qtIngerida + ", cdUsuario="
				+ cdUsuario + "]";
	}

	
}
