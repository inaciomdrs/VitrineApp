package br.ufrn.imd.model;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	
	private static Carrinho singleton;
	private List<Pokemon> pokemons;
	
	private Carrinho(){
		pokemons = new ArrayList<Pokemon>();
	}
	
	public static Carrinho getInstance(){
		if(singleton == null){
			singleton = new Carrinho();
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
