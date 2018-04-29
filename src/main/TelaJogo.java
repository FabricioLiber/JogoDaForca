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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class TelaJogo {

	private JFrame frame;
	private JogoDaForca forca = new JogoDaForca("palavras.txt");
	private JLabel lblInformacoesErros = new JLabel("6");
	private ArrayList<JLabel> labelLetras = new ArrayList<>();

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
	
	private void Restart (boolean result) {
		String resultado = "You Lose!";
		
		if (result) {
			resultado = "You win!";
		}
		
		JOptionPane.showMessageDialog(null, resultado);		
		TelaJogo window = new TelaJogo();
		window.frame.setVisible(true);
	}
	
	//metodos da tela
	private void ajusteLetraAcerto (String letra) {
		int[] indices = forca.jogar(letra);
		if (indices != null) {
			for (int i=0; i < indices.length; i++) {  
				labelLetras.get(indices[i]).setText(letra);
				if (forca.getAcertos()==forca.getTamanho()) {
					Restart(true);
				}
			}
		}else {
			lblInformacoesErros.setText(Integer.toString(Integer.parseInt(lblInformacoesErros.getText())-1));
			if (forca.getErros()==6) {
				JOptionPane.showMessageDialog(null, "You Lose!");
				Restart(false);
			}
		}
	}
	
	
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		forca.inicializar();
		
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
		
		JLabel lblInformacoes = new JLabel("Tentativas Restantes: ");
		lblInformacoes.setBounds(111, 119, 260, 30);
		panelTitulo.add(lblInformacoes);
		lblInformacoes.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		
		
		lblInformacoesErros.setBounds(90, 119, 260, 30);
		panelTitulo.add(lblInformacoesErros);
		lblInformacoesErros.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		
		JLabel lblJogoDaForca = new JLabel("Jogo da Forca");
		lblJogoDaForca.setFont(new Font("Bodoni MT", Font.PLAIN, 44));
		lblJogoDaForca.setBounds(56, 25, 278, 83);
		panelTitulo.add(lblJogoDaForca);
		
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBounds(343, 182, 381, 196);
		frame.getContentPane().add(panelOpcoes);
		panelOpcoes.setLayout(null);
		
		JButton btnArriscar = new JButton("Arriscar");
		btnArriscar.setFont(new Font("Bodoni MT", Font.PLAIN, 36));
		btnArriscar.setBounds(10, 140, 361, 45);
		panelOpcoes.add(btnArriscar);
		btnArriscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String respostaChute = JOptionPane.showInputDialog(null, "Favor digite a palavra correta: ");
				Restart(forca.advinhar(respostaChute));
			}
		});
		
		
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
		
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnEnviar.setBounds(233, 16, 138, 30);
		panelOpcoes.add(btnEnviar);
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ajusteLetraAcerto(textField.getText().toUpperCase());
				textField.setText("");
			}
		});
		
		JPanel panelResposta = new JPanel();
		panelResposta.setBounds(10, 474, 714, 82);
		frame.getContentPane().add(panelResposta);
		panelResposta.setLayout(null);
		
		JLabel lblDica = new JLabel("Dica: "+ forca.getDica());
		lblDica.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		lblDica.setBounds(100, 410, 539, 30);
		frame.getContentPane().add(lblDica);
		
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
					ajusteLetraAcerto(botao.getText());	
				}
			});
		}		
	}
}
