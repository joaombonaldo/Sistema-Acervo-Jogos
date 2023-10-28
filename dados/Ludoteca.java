package dados;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class Ludoteca implements Iterador {

	private int contador;
	private ArrayList<Jogo> jogos;

	public Ludoteca() {
		contador = 0;
		jogos = new ArrayList<Jogo>();
	}

	public boolean addJogo(Jogo jogo) {
		Jogo aux = consultaPorNome(jogo.getNome());
		if (aux == null){
			jogos.add(jogo);
			contador++;
			return true;
		}
		return false;
	}

	public Jogo consultaPorNome(String nome) {
		for(Jogo jogo : jogos) {
			if (jogo.getNome().equals(nome)) {
				return jogo;
			}
		}
		return null;
	}

	public ArrayList<Jogo> consultaPorAno(int ano) {
		ArrayList<Jogo> jogosAno = new ArrayList<Jogo>();
		for(Jogo jogo : jogos) {
			if (jogo.getAno() == ano) {
				jogosAno.add(jogo);
			}
		}

		Collections.sort(jogosAno, new Comparator<Jogo>() {
			@Override
			public int compare(Jogo jogo1, Jogo jogo2) {
				return jogo1.getNome().compareTo(jogo2.getNome());
			}
		});

		return jogosAno;
	}

	public ArrayList<Jogo> consultaPorCategoria(String categoria) {
		ArrayList<Jogo> jogosCategoria = new ArrayList<Jogo>();
		for(Jogo jogo : jogos) {
			if (jogo instanceof JogoEletronico){
				Categoria aux = Categoria.peloNome(categoria);
				if (aux.equals(((JogoEletronico) jogo).getCategoria())) {
					jogosCategoria.add(jogo);
				}
			}
		}

		Collections.sort(jogosCategoria, new Comparator<Jogo>() {
			@Override
			public int compare(Jogo jogo1, Jogo jogo2) {
				return jogo1.getNome().compareTo(jogo2.getNome());
			}
		});

		return jogosCategoria;
	}

	public double somatorioJogos(){
		double soma = 0;
		for(Jogo jogo : jogos) {
			soma += jogo.calculaPrecoFinal();
		}
		return soma;
	}

	public Jogo getJogoComMaiorPreco(){
		Jogo jogoMaiorPreco = null;
		double maiorPreco = 0;
		for(Jogo jogo : jogos) {
			if (jogo instanceof JogoTabuleiro){
				if (jogo.calculaPrecoFinal() > maiorPreco){
					maiorPreco = jogo.calculaPrecoFinal();
					jogoMaiorPreco = jogo;
				}
			}
		}
		return jogoMaiorPreco;
	}

	public double getPrecoBaseMedio(){
		double soma = 0;
		for(Jogo jogo : jogos) {
			soma += jogo.getPrecoBase();
		}
		return soma / jogos.size();
	}

	public Jogo jogoMaisProximoDaMedia() {
		if (jogos.isEmpty()) {
			return null;
		}
		
		double media = getPrecoBaseMedio();
	
		Jogo jogoMaisProximo = jogos.get(0); 
		double diferencaMaisProxima = Math.abs(jogoMaisProximo.getPrecoBase() - media);
	
		for (Jogo jogo : jogos) {
			double diferencaAtual = Math.abs(jogo.getPrecoBase() - media);
			if (diferencaAtual < diferencaMaisProxima) {
				jogoMaisProximo = jogo;
				diferencaMaisProxima = diferencaAtual;
			}
		}
		return jogoMaisProximo;
	}

	public Jogo jogoMaisAntigo(){
		Jogo jogoMaisAntigo = jogos.get(0);
		for (Jogo jogo : jogos){
			if (jogo instanceof JogoTabuleiro){
				if (jogo.getAno() < jogoMaisAntigo.getAno()){
					jogoMaisAntigo = jogo;
				}
			}
		}
		return jogoMaisAntigo;
	}
	

	/**
	 * @see dados.Iterador#reset()
	 */
	public void reset() {
		contador = 0;
	}


	/**
	 * @see dados.Iterador#hasNext()
	 */
	public boolean hasNext() {
		if (contador < jogos.size()) {
            return true;
        }
        return false;
	}


	/**
	 * @see dados.Iterador#next()
	 */
	public Object next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return jogos.get(contador++);
	}
}
