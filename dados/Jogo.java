package dados;

public abstract class Jogo {

	private String nome;
	private int ano;
	private double precoBase;

	public Jogo(String nome, int ano, double precoBase) {
		this.nome = nome;
		this.ano = ano;
		this.precoBase = precoBase;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getAno() {
		return this.ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public double getPrecoBase() {
		return this.precoBase;
	}

	public void setPrecoBase(double precoBase) {
		this.precoBase = precoBase;
	}
	
	public double arredondar(double valor) {
		return Math.round(valor * 100.0) / 100.0;
	}

	public abstract double calculaPrecoFinal();
	
	public abstract String getInformacoes();
}
