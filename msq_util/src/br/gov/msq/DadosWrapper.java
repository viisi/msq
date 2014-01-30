package br.gov.msq;


public class DadosWrapper {

	private Integer numeroLinha;
	private Integer numeroColuna;
	private String header;
	private String chave;
	private String valor;
	
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
}