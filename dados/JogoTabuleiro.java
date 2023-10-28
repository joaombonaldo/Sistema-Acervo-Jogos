package dados;

public class JogoTabuleiro extends Jogo {

	private int numeroPecas;

	public JogoTabuleiro(String nome, int ano, double precoBase, int numeroPecas) {
		super(nome, ano, precoBase);
		this.numeroPecas = numeroPecas;
	}

	public int getNumeroPecas() {
		return numeroPecas;
	}

	@Override
	public String getInformacoes() {
		return getNome() + "," + getAno() + ",R$ " + getPrecoBase() + "," + numeroPecas + ",R$ " + calculaPrecoFinal();
	}

	@Override
    public double calculaPrecoFinal() {
        double acrescimo = getPrecoBase() * (numeroPecas * 0.01);
        return arredondar(getPrecoBase() + acrescimo);
    }
}
