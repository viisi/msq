package br.gov.msq.pojo;


public class Apoiador {
	
	public Apoiador(String codigo) {
		this.codigo = codigo;
	}
	
	public Apoiador(String codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}
	
	private Integer id;
	private String codigo;
	private String nome;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}