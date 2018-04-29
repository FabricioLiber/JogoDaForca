//package main;
//
//import java.util.Scanner;
//
//public class JogoDaForcaConsole {
//	public static void main(String[] args) {
//
//		JogoDaForca jogo = new JogoDaForca("palavras.txt");
//		String letra;
//		int[] indices;
//		Scanner teclado = new Scanner (System.in);
//			
//		jogo.inicializar();
//		System.out.println("dica=" + jogo.getDica());
//		do {
//			System.out.println("digite uma letra da palavra de tamanho " + jogo.getTamanho());
//			letra = teclado.nextLine();
//			indices = jogo.jogar(letra);
//			if (indices != null) {
//				System.out.println("acertou nas posicoes:");
//				for (int i = 0; i < indices.length; i++)
//					System.out.println(indices[i]);
//			} else
//				System.out.println("tente outra letra");
//		} while (jogo.getAcertos() < jogo.getTamanho() && jogo.getErros() < 6);
//
//		// resultado do jogo
//		if (jogo.getAcertos() == jogo.getTamanho())
//			System.out.println("venceu");
//		else
//			System.out.println("perdeu");
//		teclado.close();
//	}
//}
