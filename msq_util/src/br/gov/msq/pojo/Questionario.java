package br.gov.msq.pojo;

public class Questionario {
	
	public Questionario(String codigo) {
		this.codigo = codigo;
	}
	
	public Questionario(String codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao; 
	}
	
	private Integer id;
	private String codigo;
	private String descricao;
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
}