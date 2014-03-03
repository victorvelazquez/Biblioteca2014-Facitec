package com.biblioteca.dto;

public class AreaDTO {
	private int are_codigo;
	private String are_descri;
	private String are_obse;
	
	public AreaDTO() {
		super();
		this.are_codigo = 0;
		this.are_descri = "";
		this.are_obse = "";
	}

	public int getAre_codigo() {
		return are_codigo;
	}

	public void setAre_codigo(int are_codigo) {
		this.are_codigo = are_codigo;
	}

	public String getAre_descri() {
		return are_descri;
	}

	public void setAre_descri(String are_descri) {
		this.are_descri = are_descri;
	}

	public String getAre_obse() {
		return are_obse;
	}

	public void setAre_obse(String are_obse) {
		this.are_obse = are_obse;
	}
	
	

}
