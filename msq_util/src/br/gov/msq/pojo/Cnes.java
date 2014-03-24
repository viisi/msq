package br.gov.msq.pojo;


public class Cnes {
	
	public Cnes(String codigoCnes) {
		this.codigoCnes = codigoCnes;
	}
	
	private Integer id;
	private String codigoCnes;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodigoCnes() {
		return codigoCnes != null ? codigoCnes.trim() : ""; 
	}
	public void setCodigoCnes(String codigoCnes) {
		this.codigoCnes = codigoCnes;
	}
}