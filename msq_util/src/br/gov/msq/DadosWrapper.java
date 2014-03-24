package br.gov.msq;


public class DadosWrapper {

	private Integer numeroLinha;
	private Integer numeroColuna;
	private String header;
	private String chave;
	private String valor;
	private String tipoDOM;
	private String codigoApoiador;
	private String codigoQuestionario;
	
	public Integer getNumeroLinha() {
		return numeroLinha;
	}
	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}
	public Integer getNumeroColuna() {
		return numeroColuna;
	}
	public void setNumeroColuna(Integer numeroColuna) {
		this.numeroColuna = numeroColuna;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getChave() {
		return chave;
	}
	public void setChave(String chave) {
		this.chave = chave;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getCodigoApoiador() {
		return codigoApoiador;
	}
	public void setCodigoApoiador(String codigoApoiador) {
		this.codigoApoiador = codigoApoiador;
	}
	public String getCodigoQuestionario() {
		return codigoQuestionario;
	}
	public void setCodigoQuestionario(String codigoQuestionario) {
		this.codigoQuestionario = codigoQuestionario;
	}
	public String getTipoDOM() {
		return tipoDOM;
	}
	public void setTipoDOM(String tipoDOM) {
		this.tipoDOM = tipoDOM;
	}
}