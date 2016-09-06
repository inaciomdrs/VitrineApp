package br.ufrn.imd.dao;

import java.util.List;

import javax.persistence.Query;

import br.ufrn.imd.model.Pokemon;

public class PokemonDAO extends GenericDAO<Pokemon> {

	private static PokemonDAO pokemonDAO;
	
	private PokemonDAO() {}
	
	public static PokemonDAO getSingleton(){
		if(pokemonDAO == null){
			pokemonDAO = new PokemonDAO();
		}
		return pokemonDAO;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Pokemon> listar() {
		String queryJPQL = "from Pokemon";
		
		Query query = getEntityManager().createQuery(queryJPQL);
		return (List<Pokemon>) query.getResultList();
	}

}
