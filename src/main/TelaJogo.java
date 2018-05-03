package main;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.MatteBorder;

public class TelaJogo {

	private JFrame frame;
	private JogoDaForca forca = new JogoDaForca("palavras.txt");
	private JLabel lblInformacoesErros = new JLabel("6");
	private ArrayList<JLabel> labelLetras = new ArrayList<>();
	private ArrayList<JButton> botoes =  new ArrayList<JButton>();
	JTextField textField = new JTextField();
	JButton btnEnviar = new JButton("Enviar");
	JTextArea textAreaErrosAcertos;
	JLabel labelImagem = new JLabel("");

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
		forca.inicializar();
		
		frame = new JFrame();
		frame.setTitle("Jogo da Forca (POO) - Feito por Fabrício e Rafael");
		frame.setBounds(150, 20, 859, 582);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panelTeclado = new JPanel();
		panelTeclado.setBackground(Color.BLACK);
		panelTeclado.setBounds(369, 446, 474, 99);
		frame.getContentPane().add(panelTeclado);
		
		JPanel panelForca = new JPanel();
		panelForca.setBackground(Color.WHITE);
		panelForca.setBounds(0, 59, 370, 389);
		frame.getContentPane().add(panelForca);
		panelForca.setLayout(null);
		
		labelImagem.setBorder(new MatteBorder(0, 5, 0, 5, (Color) new Color(0, 0, 0)));
		labelImagem.setIcon(new ImageIcon(TelaJogo.class.getResource("/img/0.png")));
		labelImagem.setBounds(0, 0, 369, 389);
		panelForca.add(labelImagem);
		
		JPanel panelTitulo = new JPanel();
		panelTitulo.setForeground(Color.WHITE);
		panelTitulo.setBackground(Color.BLACK);
		panelTitulo.setBounds(0, 0, 843, 60);
		frame.getContentPane().add(panelTitulo);
		panelTitulo.setLayout(null);
		
		JLabel lblInformacoes = new JLabel("Tentativas Restantes: ");
		lblInformacoes.setForeground(Color.WHITE);
		lblInformacoes.setBounds(583, 0, 228, 30);
		panelTitulo.add(lblInformacoes);
		lblInformacoes.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		lblInformacoesErros.setForeground(Color.WHITE);
		
		
		lblInformacoesErros.setBounds(811, 0, 22, 30);
		panelTitulo.add(lblInformacoesErros);
		lblInformacoesErros.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		
		JLabel lblJogoDaForca = new JLabel("Jogo da Forca - Salve o Tira Dentes");
		lblJogoDaForca.setForeground(Color.WHITE);
		lblJogoDaForca.setFont(new Font("Bodoni MT", Font.PLAIN, 36));
		lblJogoDaForca.setBounds(10, 0, 547, 63);
		panelTitulo.add(lblJogoDaForca);
		
		JPanel panelOpcoes = new JPanel();
		panelOpcoes.setBorder(new MatteBorder(5, 0, 2, 5, (Color) new Color(0, 0, 0)));
		panelOpcoes.setBackground(Color.WHITE);
		panelOpcoes.setBounds(369, 269, 474, 181);
		frame.getContentPane().add(panelOpcoes);
		panelOpcoes.setLayout(null);
		
		JButton btnArriscar = new JButton("Arriscar");
		btnArriscar.setFont(new Font("Bodoni MT", Font.PLAIN, 36));
		btnArriscar.setBounds(42, 102, 361, 45);
		panelOpcoes.add(btnArriscar);
		
		JLabel lblDigiteUmaLetra = new JLabel("Digite uma letra:");
		lblDigiteUmaLetra.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		lblDigiteUmaLetra.setBounds(42, 46, 186, 30);
		panelOpcoes.add(lblDigiteUmaLetra);
		
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					enviaResposta();
				}
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Bodoni MT", Font.PLAIN, 26));
		textField.setBounds(223, 42, 32, 38);
		panelOpcoes.add(textField);
		textField.setColumns(10);
		
		btnEnviar.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		btnEnviar.setBounds(265, 46, 138, 30);
		panelOpcoes.add(btnEnviar);
		
		JPanel panelResposta = new JPanel();
		panelResposta.setBorder(new MatteBorder(0, 0, 0, 5, (Color) new Color(0, 0, 0)));
		panelResposta.setBackground(Color.WHITE);
		panelResposta.setBounds(369, 59, 474, 210);
		frame.getContentPane().add(panelResposta);
		panelResposta.setLayout(null);
		
		JTextArea lblDica = new JTextArea("Dica:\n"+ forca.getDica());
		lblDica.setBounds(36, 11, 368, 118);
		panelResposta.add(lblDica);
		lblDica.setEditable(false);
		lblDica.setLineWrap(true);
		lblDica.setBackground(Color.WHITE);
		lblDica.setFont(new Font("Bodoni MT", Font.PLAIN, 22));
		
		JPanel panel = new JPanel();
		panel.setBorder(new MatteBorder(5, 5, 5, 5, (Color) new Color(0, 0, 0)));
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 446, 370, 98);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		btnReiniciar.setBounds(10, 38, 141, 49);
		panel.add(btnReiniciar);
		
		textAreaErrosAcertos = new JTextArea("Nº de acertos: "+forca.getAcertos()+"\n"+ "Nº de erros: " + forca.getErros());
		textAreaErrosAcertos.setLineWrap(true);
		textAreaErrosAcertos.setFont(new Font("Bodoni MT", Font.PLAIN, 24));
		textAreaErrosAcertos.setEditable(false);
		textAreaErrosAcertos.setBackground(Color.WHITE);
		textAreaErrosAcertos.setBounds(192, 25, 168, 62);
		panel.add(textAreaErrosAcertos);
		
		//Auxiliar
		double divisaoEspacoVazio = (panelResposta.getSize().getWidth() - 50*forca.getTamanho())/2;
		
		for (int i = 0; i < forca.getTamanho(); i++) {		
			JLabel labelLetra = new JLabel("");
			labelLetra.setHorizontalAlignment(SwingConstants.CENTER);
			labelLetra.setFont(new Font("Bodoni MT", Font.PLAIN, 42));
			labelLetra.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			labelLetra.setBounds((int)(divisaoEspacoVazio +(40*i) + (10*i)), 150, 40, 40);
			panelResposta.add(labelLetra);
			labelLetras.add(labelLetra);
		}
		
		// Event Listener
		
		for (int i = 0; i < 26; i++) {
			JButton botao = new JButton(String.format("%c", 'A' + i));
			//botao.setBounds(40, 30*i, 30, 30);
			panelTeclado.add(botao);
			botoes.add(botao);
			botao.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ajusteLetraAcerto(botao.getText(), botoes);
				}
			});
		}
		
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				enviaResposta();
			}
		});

		btnArriscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String respostaChute = JOptionPane.showInputDialog(frame, "Favor digite a palavra correta: ");
				restart(forca.advinhar(respostaChute));
			}
		});		
		
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				restart(false);
			}
		});
	}
	

	//Metodos da tela
	
	public void restart (boolean result) {
		String resultado = "You Lose!";
		
		if (result)
			resultado = "You win!";		
		JOptionPane.showMessageDialog(frame, resultado);
		frame.dispose();
		TelaJogo window = new TelaJogo();
		window.frame.setVisible(true);
	}
	
	public void ajusteLetraAcerto (String letra, ArrayList<JButton> botoes) {
		int indiceBotao = -1;
		int[] indices = forca.jogar(letra);
		
		int j = 0;
		boolean flag = false;
		while (j < botoes.size() || flag != true) {
			if (botoes.get(j).getText().equalsIgnoreCase(letra)) {
				indiceBotao = j;
				flag = true;
			}
			j++;
		}
		botoes.get(indiceBotao).setEnabled(false);
		if (indices != null) {
			for (int i=0; i < indices.length; i++) {  
				labelLetras.get(indices[i]).setText(letra);
				if (forca.getAcertos()==forca.getTamanho()) {
					restart(true);
				}
			}
			botoes.get(indiceBotao).setBackground(Color.GREEN);	
			
		}else {
			lblInformacoesErros.setText(Integer.toString(Integer.parseInt(lblInformacoesErros.getText())-1));
			labelImagem.setIcon(new ImageIcon(TelaJogo.class.getResource("/img/"+forca.getErros()+".png")));
			botoes.get(indiceBotao).setBackground(Color.RED);
			if (forca.getErros()==6) {
				restart(false);
			}
		}
		textAreaErrosAcertos.setText("Nº de acertos: "+forca.getAcertos()+"\n"+ "Nº de erros: " + forca.getErros());
	}
	
	public void enviaResposta () {
		if (textField.getText().length()!=1) {
			JOptionPane.showMessageDialog(frame, "Favor informar apenas uma letra!", "Me ajude", 0);
			textField.setText("");
		}else {
			ajusteLetraAcerto(textField.getText(), botoes);
			textField.setText("");
			textField.requestFocus();	
		}
	}
}
