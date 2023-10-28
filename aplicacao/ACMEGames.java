package aplicacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

import dados.Categoria;
import dados.Jogo;
import dados.JogoEletronico;
import dados.JogoTabuleiro;
import dados.Ludoteca;

public class ACMEGames {

	private Scanner entrada = null;                 // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela (console)
	private Ludoteca ludoteca;
	
	public ACMEGames() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("dadosin.txt"));
            entrada = new Scanner(streamEntrada);   // Use um arquivo como entrada
            entrada.useDelimiter(";|\\n");  // Delimitador de campos
            
            PrintStream streamSaida = new PrintStream(new File("dadosout.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Use um arquivo como saída
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajuste para ponto decimal
        entrada.useLocale(Locale.ENGLISH);
    
        // Implemente o resto do código aqui
        this.ludoteca = new Ludoteca();
    }
    

	public void executa() {
		cadastrarJogosEletronicos();
        cadastrarJogosTabuleiro();
        dadosJogoNome();
        dadosJogoAno();
        dadosJogoCategoria();
        somatorioFinal();
        tabuleiroComMaiorPreco();
        jogoPrecoMaisPertoDaMedia();
        jogoTabuleiroMaisAntigo();
	}

    private void cadastrarJogosEletronicos() {
        String nome;
    
        while (true) {
            if (!entrada.hasNext()) break;
            nome = entrada.next();
    
            if (nome.equals("-1")) {
                break;
            }
    
            try {
                int ano = entrada.nextInt();
                double precoBase = entrada.nextDouble();
                String plataforma = entrada.next();
                String nomeCategoria = entrada.next();
                    
                Categoria categoria = Categoria.peloNome(nomeCategoria);
                    
                JogoEletronico jogo = new JogoEletronico(nome, ano, precoBase, plataforma, categoria);
                if (ludoteca.addJogo(jogo)) {
                    System.out.printf("1:%s,R$ %.2f%n", jogo.getNome(), jogo.calculaPrecoFinal());
                } else {
                    System.out.println("1:Erro-jogo com nome repetido: " + nome);
                }
            } catch (NoSuchElementException e) {
                System.out.println("1:Erro-entrada inválida: " + e.getMessage());
                entrada.nextLine();
                continue;
            }
        }
    }
    
    
    private void cadastrarJogosTabuleiro(){
        String nome = entrada.next();

        while (!nome.equals("-1")){
            try{
                int ano = entrada.nextInt();
                double precoBase = entrada.nextDouble();
                int numeroPecas = entrada.nextInt();

                JogoTabuleiro jogo = new JogoTabuleiro(nome, ano, precoBase, numeroPecas);
                if (ludoteca.addJogo(jogo)){
                    System.out.printf("2:%s,R$ %.2f%n", jogo.getNome(), jogo.calculaPrecoFinal());
                } else {
                    System.out.println("2:Erro-jogo com nome repetido: " + nome);
                }
            } catch (NoSuchElementException e){
                System.out.println("2:Erro-entrada inválida: " + e.getMessage());
                if (entrada.hasNext()){
                    nome = entrada.next();
                } else {
                    break;
                }
                continue;
            }

            if (entrada.hasNext()){
                nome = entrada.next();
            } else {
                break;
            }
        }
    }

    private void dadosJogoNome(){
        String nome = entrada.next();
        Jogo jogo = ludoteca.consultaPorNome(nome);
         if(jogo != null) {
            System.out.println("3:" + jogo.getInformacoes()); 
        } else {
            System.out.println("3:Nome inexistente.");
        }
    }

    private void dadosJogoAno(){
        int ano = entrada.nextInt();
        ArrayList<Jogo> jogosAno = ludoteca.consultaPorAno(ano);
        if (jogosAno.isEmpty()){
            System.out.println("4:Nenhum jogo encontrado.");
        } else {
            for (Jogo jogo : jogosAno){
                System.out.println("4:" + jogo.getInformacoes());
            }
        }
    }
    
    private void dadosJogoCategoria(){
        String nomeCategoria = entrada.next();
        ArrayList<Jogo> jogosCategoria = ludoteca.consultaPorCategoria(nomeCategoria);
        if (jogosCategoria.isEmpty()){
            System.out.println("5:Nenhum jogo encontrado.");
        } else {
            for (Jogo jogo : jogosCategoria){
                System.out.println("5:" + jogo.getInformacoes());
            }
        }
    }

    private void somatorioFinal(){
        if (ludoteca.somatorioJogos() != 0){
            System.out.printf("6:%.2f%n", ludoteca.somatorioJogos());
        } else {
            System.out.println("6:Nenhum jogo encontrado.");
        }   
    }

    private void tabuleiroComMaiorPreco(){
        Jogo jogo = ludoteca.getJogoComMaiorPreco();
        if (jogo != null){
            System.out.println("7:" + jogo.getNome() + "," + jogo.calculaPrecoFinal());
        } else {
            System.out.println("7:Nenhum jogo encontrado.");
        }
    }

    private void jogoPrecoMaisPertoDaMedia(){
        Jogo jogo = ludoteca.jogoMaisProximoDaMedia();
        if (jogo != null){
            System.out.printf("8:%.2f,%s%n", ludoteca.getPrecoBaseMedio(), jogo.getInformacoes());
        } else {
            System.out.println("8:Nenhum jogo encontrado.");
        }
    }

    private void  jogoTabuleiroMaisAntigo(){
        Jogo jogo = ludoteca.jogoMaisAntigo();
        if (jogo != null){
            System.out.println("9:" + jogo.getNome() + "," + jogo.getAno());
        } else {
            System.out.println("9:Nenhum jogo encontrado.");
        }
    }
}    




   
        
