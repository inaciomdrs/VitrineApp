package br.ufrn.imd.application;

import br.ufrn.imd.dao.PokemonDAO;
import br.ufrn.imd.model.Pokemon;

public class PopulateDatabase {

	public static void main(String[] args) {
		PokemonDAO pokemonDAO = PokemonDAO.getSingleton();

		System.out.println("Saving Snorlax...");
		pokemonDAO.salvar(new Pokemon("Snorlax", "Normal", "Luta", "Fantasma", "resources/imgs/snorlax.png", 500, 1000,
				1500, 800));
		
		System.out.println("Saving Blastoise...");
		pokemonDAO.salvar(
				new Pokemon("Blastoise", "Água", "Fogo", "Terra", "resources/imgs/blastoise.png", 600, 800, 700, 900));

		System.out.println("Done!");
	}

}
