// Elaborado por Fabricio Liberato e Rafael Lins - POO - IFPB 2018.1

package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class JogoDaForca {

	// Criacao das variaveis

	private String[] palavras; // um array com as n palavras (lidas do arquivo)
	private String[] dicas; // um array com as n dicas (lidas do arquivo)
	private String palavraSorteio;
	private int n; // quantidade de palavras do arquivo (lido do arquivo)
	private int sorteio = -1; // indice da palavra sorteadado jogo
	private int acertos; // total de acertos do jogo
	private int erros; // total de erros do jogo

	// Construtor

	public JogoDaForca(String nomearquivo) {
		try {
			Scanner arq;
			String linha;
			int i = 0;

			arq = new Scanner(new File(nomearquivo));
			linha = arq.nextLine();
			this.n = Integer.parseInt(linha);
			this.palavras = new String[this.n];
			this.dicas = new String[this.n];
			while (arq.hasNextLine()) {
				linha = arq.nextLine();
				String[] dados = linha.split(";");
				this.palavras[i] = dados[0];
				this.dicas[i] = dados[1];
				i++;
			}
			arq.close();
		} catch (FileNotFoundException e) {
			System.out.println("arquivo inexistente");
			System.exit(0); // sai do programa
		}
	}

	// Metodos

	public void inicializar() {
		this.sorteio = (int) (Math.random() * this.n);
		this.palavraSorteio = new String (this.palavras[this.sorteio]);
		this.acertos = this.erros = 0;
	}

	public int[] jogar(String letra) {
		int[] indicesLetrasEncontradas;
		int i, j;
		boolean flag = false;
		for (i = 0, j = 0; i < this.getTamanho(); i++) {
			if (letra.equalsIgnoreCase(this.palavraSorteio.substring(i, i + 1))) {
				flag = true;
				this.acertos++;
				j++;
			}
		}
		indicesLetrasEncontradas = new int[j];
		for (i = 0, j = 0; i < this.getTamanho(); i++) {
			if (letra.equalsIgnoreCase(this.palavraSorteio.substring(i, i + 1))) {
				indicesLetrasEncontradas[j] = i;
				j++;
			}
		}
		if (flag) {
			this.palavraSorteio = this.palavraSorteio.replaceAll(letra, "#");
			return indicesLetrasEncontradas;
		}else {
			this.erros++;
			return null;
		}
	}

	public boolean advinhar(String palavra) {
		if (this.palavras[this.sorteio].equalsIgnoreCase(palavra)) {
			this.acertos = this.getTamanho();
			return true;
		} else {
			this.erros = 6;
			return false;
		}
	}

	public int getTamanho() {
		return this.palavras[this.sorteio].length();
	}

	// Getters

	public String getDica() {
		return this.dicas[this.sorteio];
	}

	public int getAcertos() {
		return this.acertos;
	}

	public int getErros() {
		return this.erros;
	}
}