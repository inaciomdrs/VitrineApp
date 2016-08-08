package br.ufrn.imd.model;

public class Pokemon {
	private String nome;
	private double forca;
	
	public Pokemon(String nome, double forca) {
		this.nome = nome;
		this.forca = forca;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getForca() {
		return forca;
	}

	public void setForca(double forca) {
		this.forca = forca;
	}
}
