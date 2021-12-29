package br.com.projeto.model;

public class AtividadeFisica {

	public int cdAtivFisica;
	public String dtAtivFisica;
	public int duracao;
	public int cdUsuario;
	public int cdExercico;
	public String atividade;
	
	
	public String getAtividade() {
		return atividade;
	}	
	public int getCdAtivFisica() {
		return cdAtivFisica;
	}
	public String getDtAtivFisica() {
		return dtAtivFisica;
	}
	public int getDuracao() {
		return duracao;
	}
	public int getCdUsuario() {
		return cdUsuario;
	}
	public int getCdExercico() {
		return cdExercico;
	}
	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}
	
	public void setCdAtivFisica(int cdAtivFisica) {
		this.cdAtivFisica = cdAtivFisica;
	}
	public void setDtAtivFisica(String string) {
		this.dtAtivFisica = string;
	}
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	public void setCdUsuario(int cdUsuario) {
		this.cdUsuario = cdUsuario;
	}
	public void setCdExercico(int cdExercico) {
		this.cdExercico = cdExercico;
	}
	@Override
	public String toString() {
		return "AtividadeFisica [cdAtivFisica=" + cdAtivFisica + ", dtAtivFisica=" + dtAtivFisica + ", duracao="
				+ duracao + ", cdUsuario=" + cdUsuario + ", cdExercico=" + cdExercico + ", atividade=" + atividade
				+ "]";
	}
	
	
	


	
}
