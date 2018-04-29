package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class TelaJogo {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaJogo window = new TelaJogo();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaJogo() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		JogoDaForca forca = new JogoDaForca("teste.txt");
		forca.inicializar();
		System.out.println(forca.getDica());
		
		frame = new JFrame();
		frame.setBounds(100, 100, 752, 704);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTeclado = new JPanel();
		panelTeclado.setBounds(67, 567, 605, 88);
		frame.getContentPane().add(panelTeclado);
		
		JPanel panelForca = new JPanel();
		panelForca.setBackground(Color.BLACK);
		panelForca.setBounds(10, 11, 323, 367);
		frame.getContentPane().add(panelForca);
		panelForca.setLayout(null);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setBounds(343, 11, 381, 160);
		frame.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblInformacoes = new JLabel("Tentativas Restantes: 06");
		lblInformacoes.setBounds(111, 119, 260, 30);
		panelTitulo.add(lblInformacoes);
		lblInformacoes.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		
		JLabel lblJogoDaForca = new JLabel("Jogo da Forca");
		lblJogoDaForca.setFont(new Font("Bodoni MT", Font.PLAIN, 44));
		lblJogoDaForca.setBounds(56, 25, 278, 83);
		panelTitulo.add(lblJogoDaForca);
		
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(343, 182, 381, 196);
		frame.getContentPane().add(panelOpcoes);
		panelOpcoes.setLayout(null);
		
		JButton btnNewButton = new JButton("Arriscar");
		btnNewButton.setFont(new Font("Bodoni MT", Font.PLAIN, 36));
		btnNewButton.setBounds(10, 140, 361, 45);
		panelOpcoes.add(btnNewButton);
		
		JLabel lblDigiteUmaLetra = new JLabel("Digite uma letra:");
		lblDigiteUmaLetra.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		lblDigiteUmaLetra.setBounds(10, 16, 186, 30);
		panelOpcoes.add(lblDigiteUmaLetra);
		
		JTextField textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Bodoni MT", Font.PLAIN, 26));
		textField.setBounds(191, 12, 32, 38);
		panelOpcoes.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Enviar");
		btnNewButton_1.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnNewButton_1.setBounds(233, 16, 138, 30);
		panelOpcoes.add(btnNewButton_1);
		
		JPanel panelResposta = new JPanel();
		panelResposta.setBounds(10, 474, 714, 82);
		frame.getContentPane().add(panelResposta);
		panelResposta.setLayout(null);
		
		JLabel lblDica = new JLabel("Dica: "+ forca.getDica());
		lblDica.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		lblDica.setBounds(100, 410, 539, 30);
		frame.getContentPane().add(lblDica);
		
		ArrayList<JLabel> labelLetras = new ArrayList<>();
		for (int i = 0; i < forca.getTamanho(); i++) {			
			//Auxiliares
			double tamanhoTotalLabels = (50*forca.getTamanho()) + (10*forca.getTamanho());
			double divisaoEspacoVazio = (panelResposta.getSize().getWidth() - tamanhoTotalLabels)/2;
			
			JLabel labelLetra = new JLabel("");
			labelLetra.setHorizontalAlignment(SwingConstants.CENTER);
			labelLetra.setFont(new Font("Bodoni MT", Font.PLAIN, 42));
			labelLetra.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
			labelLetra.setBounds((int)(divisaoEspacoVazio +(50*i) + (10*i)), 0, 50, 30);
			panelResposta.add(labelLetra);
			labelLetras.add(labelLetra);
		}
		
		ArrayList<JButton> botoes =  new ArrayList<JButton>();
		for (int i = 0; i < 26; i++) {
			JButton botao = new JButton(String.format("%c", 'A' + i));
			botao.setBounds(0, 30*i, 50, 50);
			panelTeclado.add(botao);
			botoes.add(botao);
			botao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					int[] indices = forca.jogar(botao.getText());
					if (indices != null)
						for (int i=0; i < indices.length; i++) {  
							labelLetras.get(indices[i]).setText(botao.getText());
						}
					else
						lblInformacoes.setText(Integer.toString(Integer.parseInt(lblInformacoes.getText())-1));
				}
			});
		}
	}

}
