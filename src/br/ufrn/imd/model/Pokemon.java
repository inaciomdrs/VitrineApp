package br.ufrn.imd.model;

public class Pokemon {
	private String nome;
	private String tipo;
	
	private String vantagem;
	private String desvantagem;
	
	private double forca;
	private double saude;
	private double defesa;
	private double stamina;
	
	public Pokemon(){}

	public Pokemon(String nome, String tipo, String vantagem, String desvantagem, double forca, double saude,
			double defesa, double stamina) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.vantagem = vantagem;
		this.desvantagem = desvantagem;
		this.forca = forca;
		this.saude = saude;
		this.defesa = defesa;
		this.stamina = stamina;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getVantagem() {
		return vantagem;
	}

	public void setVantagem(String vantagem) {
		this.vantagem = vantagem;
	}

	public String getDesvantagem() {
		return desvantagem;
	}

	public void setDesvantagem(String desvantagem) {
		this.desvantagem = desvantagem;
	}

	public double getForca() {
		return forca;
	}

	public void setForca(double forca) {
		this.forca = forca;
	}

	public double getSaude() {
		return saude;
	}

	public void setSaude(double saude) {
		this.saude = saude;
	}

	public double getDefesa() {
		return defesa;
	}

	public void setDefesa(double defesa) {
		this.defesa = defesa;
	}

	public double getStamina() {
		return stamina;
	}

	public void setStamina(double stamina) {
		this.stamina = stamina;
	}
	
		
	
}
