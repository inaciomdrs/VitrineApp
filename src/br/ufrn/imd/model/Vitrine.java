package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class Vitrine {
	private static Vitrine singleton;
	private List<Pokemon> pokemons;
	
	private Vitrine(){
		pokemons = new ArrayList<Pokemon>();
	}
	
	public static Vitrine getInstance(){
		if(singleton == null){
			singleton = new Vitrine();
		}
		return singleton;
	}
		
	public void addProdutos(Pokemon... pokemons){
		for(Pokemon pokemon : pokemons){
			this.pokemons.add(pokemon);
		}
	}
	
	public List<Pokemon> getPokemons(){
		return this.pokemons;
	}	
}
