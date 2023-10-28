package dados;

public class JogoEletronico extends Jogo {

	private String plataforma;
	private Categoria categoria;

	public JogoEletronico(String nome, int ano, double precoBase, String plataforma, Categoria categoria) {
		super(nome, ano, precoBase);
		this.plataforma = plataforma;
		this.categoria = categoria;
	}

	public String getPlataforma() {
		return this.plataforma;
	}

	public void setPlataforma(String plataforma) {
		this.plataforma = plataforma;
	}

	public Categoria getCategoria() {
		return this.categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}	

	@Override
	public String getInformacoes() {
		return getNome() + "," + getAno() + ",R$ " + getPrecoBase() + "," + plataforma + "," + categoria.getNome() + ",R$ " + calculaPrecoFinal();
	}

	@Override
	public double calculaPrecoFinal() {
		double preco = getPrecoBase();
		if (categoria == Categoria.ACT){
			return arredondar(preco + (preco * 0.10));
		} else if (categoria == Categoria.SIM){
			return arredondar(preco + (preco * 0.30));
		} else { //botei o else aqui porque vou fazer a verificação da categoria na entrada dos dados, então se não for nenhum dos dois acima, só poderá ser Categoria.STR
			return arredondar(preco + (preco * 0.70));
		} 
	}
}
