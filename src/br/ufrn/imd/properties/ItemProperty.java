package br.ufrn.imd.properties;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class ItemProperty {
	private SimpleStringProperty nome;
	private SimpleDoubleProperty forca;
	
	public ItemProperty(String nome, double forca) {
		this.nome = new SimpleStringProperty(nome);
		this.forca = new SimpleDoubleProperty(forca);
	}

	public String getNome() {
		return nome.get();
	}

	public void setNome(String nome) {
		this.nome.set(nome);
	}

	public double getForca() {
		return forca.get();
	}

	public void setForca(Double forca) {
		this.forca.set(forca);
	}

}
