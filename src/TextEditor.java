import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class TextEditor extends JFrame implements ActionListener{
	
	ImageIcon image = new ImageIcon("./src/iconTextEditor.png");

	JTextArea 	textArea;										//VAR ÁREA DE TEXTO
	JScrollPane scrollPane;										//VAR SCROLL PÁGINA
	JLabel 		fontLabel;										//VAR RÓTULO "FONTE: "
	JSpinner 	fontSizeSpinner;								//VAR ESCOLHA TAMANHO DA FONTE
	JButton		fontColorButton;								//VAR BOTÃO DE COR
	JComboBox	fontBox;										//VAR LISTA DE FONTES
	
	JMenuBar	menuBar;										//VAR BARRA				DE MENU
	JMenu		fileMenu;										//VAR ITEM 		File 	DE MENU
	JMenuItem	openItem;										//VAR SUB ITEM 	open 	DE MENU
	JMenuItem	saveItem;										//VAR SUB ITEM 	save 	DE MENU
	JMenuItem	exitItem;										//VAR SUB ITEM 	exit 	DE MENU
	
	int corTela 		= 0x0f0e42;
	int corMenu 		= 0x0f0e42;
	int corAreaTexto 	= 0x0f0e42;
	int corFonte		= 0xf8f2fa;
	
//CONSTRUTOR------------------------------------------------------------------------------------------	
	TextEditor (){
		//TELA........................................................................................
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//BOTÃO DE FECHAR
		this.setTitle("Editor de Texto");						//TÍTULO		
		this.setSize(500, 500);									//DIMENSÃO
		this.setLayout(new FlowLayout());						//FLOW LAYOUT
		this.setLocationRelativeTo(null);						//JANELA APARECE NO MEIO DA TELA
		this.getContentPane().setBackground(new Color(corTela));//COR
		this.setIconImage(image.getImage());					//ICONE
		
		//ÁREA DE TEXTO...............................................................................
		textArea = new JTextArea();								//CRIA OBJETO AREA TE TEXTO
		textArea.setLineWrap(true);								//QUEBRA DE LINHA NA MARGEM DIREITA
		textArea.setWrapStyleWord(true);						//ESTILO DE QUEBRA DE LINHA AUTOMÁTICO
		textArea.setFont(new Font("Fira Code", Font.PLAIN, 40));//FONTE 
		textArea.setBackground(new Color(corAreaTexto));		//COR
		
		//SCROLL PÁGINA...............................................................................
		scrollPane = new JScrollPane(textArea);					//CRIA OBJETO PÁGINA DE SCROLL COM UMA ÁREA DE TEXTO DENTRO
		scrollPane.setPreferredSize(new Dimension(450,450));	//DIMENSÃO DO SCROLL E DA ÁREA DE TEXTO
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);//SOMENTE SCROLL VERTICAL
		
		//RÓTULO "TAMANHO DA FONTE"...................................................................
		fontLabel = new JLabel("Fonte: ");						//CRIA OBJETO RÓTULO
		fontLabel.setForeground(new Color(corFonte));			//COR
		
		//ESCOLHA TAMANHO DA FONTE....................................................................
		fontSizeSpinner = new JSpinner();						//CRIA OBJETO ESCOLHA TAMANHO DA FONTE
		fontSizeSpinner.setPreferredSize(new Dimension (50, 25));//DIMENSÃO
		fontSizeSpinner.setValue(20);							//VALOR PADRÃO
		fontSizeSpinner.addChangeListener(new ChangeListener() {//AÇÃO PARA MUDAR O TAMANHO DA FONTE
			@Override
			public void stateChanged(ChangeEvent e) {
				textArea.setFont(new Font(textArea.getFont().getFamily(), Font.PLAIN, (int) fontSizeSpinner.getValue())); 	
			}	//MUDA O "textArea.setFont(new Font" PARA(MESMA FONTE, Font PLAIN, (DEFINE COMO INTEIRO) e VALOR DO JSPINNER.
		});
		
		//BOTÃO DE COR.................................................................................
		fontColorButton = new JButton("Cor");					//CRIA OBJETO BOTÃO DE COR
		fontColorButton.addActionListener(this);				//CRIA UMA FUNÇÃO AO CLICAR NELE			
		
		//LISTA DE FONTES...............................................................................
		String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames(); //PEGA LISTA DE FONTES DO JAVA
		fontBox = new JComboBox(fonts);							//CRIA OBJETO LISTA DE FONTES
		fontBox.addActionListener(this);						//CRIA UMA FUNÇÃO AO CLICAR NELE
		fontBox.setSelectedItem("Fira Code");					//DEFINE FONTE PADRÃO
		
		//BARRA DE MENU:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		menuBar 	= new JMenuBar();								//CRIA OBJETO BARRA DE MENU
		fileMenu 	= new JMenu("Arquivo");							//CRIA OBJETO File
		openItem	= new JMenuItem("Abrir");						//CRIA OBJETO open
		saveItem	= new JMenuItem("Salvar");						//CRIA OBJETO save
		exitItem	= new JMenuItem("Fechar");						//CRIA OBJETO exit
		
		openItem.addActionListener(this);							//CRIA FUNÇÃO
		saveItem.addActionListener(this);							//CRIA FUNÇÃO
		exitItem.addActionListener(this);							//CRIA FUNÇÃO
		
		fileMenu.add(openItem);										//ADICIONA ABRIR 	AO 	ARQUIVO
		fileMenu.add(saveItem);										//ADICIONA SALVAR 	AO 	ARQUIVO
		fileMenu.add(exitItem);										//ADICIONA FECHAR 	AO 	ARQUIVO
		menuBar.add(fileMenu);										//ADICIONA ARQUIVO 	A 	BARRA DE MENU		
		
		menuBar.setBackground(new Color(corMenu));					//COR
		fileMenu.setForeground(new Color(corFonte));				//COR
		
		//ADICIONA COMPONETES A TELA...................................................................
		this.setJMenuBar(menuBar);								//ADICIONA BARRA DE MENU
		this.add(fontLabel);									//ADICIONA RÓTULO
		this.add(fontSizeSpinner);								//ADICIONA ESCOLHA TAMANHO DA FONTE 
		this.add(fontColorButton);								//ADICIONA BOTÃO DE COR
		this.add(fontBox);										//ADICIONA LISTA DE FONTES
		this.add(scrollPane);									//ADICIONA SCROLL PAGE + ÁREA DE TEXTO DENTRO
		this.setVisible(true);									//VISIBILIDADE		
	}
	
//FUNÇÕES DO ACTIONLISTENER............................................................................		
	@Override
	public void actionPerformed(ActionEvent e) {

		//FUNÇÃO BOTÃO DE COR..........................................................................
		if(e.getSource()==fontColorButton) {					//SE CLICA NO BOTÃO DE COR
			JColorChooser colorChooser = new JColorChooser();	//CRIA UM OBJETO PALETA DE COR
			Color color = colorChooser.showDialog(null, "Escolha a cor", Color.WHITE); 
			// COMPONETE, TÍTULO, COR PADRÃO
			// A COR ESCOLHIDA É ADICIONA A VAR "color" TIPO "Color" 
			textArea.setForeground(color);						//DEFINE A COR DO TEXT = VAR color = COR ESCOLHIDA
		}
		//FUNÇÃO LISTA DE FONTES........................................................................
		if(e.getSource()==fontBox) {							//SE CLICA NA LISTA DE FONTES
			textArea.setFont(new Font((String) fontBox.getSelectedItem(), Font.PLAIN, textArea.getFont().getSize() ));					
			//MUDA O "textArea.setFont(new Font" PARA((DEFINE COMO STRING) FONTE SELECIONADA, Font PLAIN, MESMO TAMANHO.
		}
		//FUNÇÃO DA BARRA DE MENU:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
		if(e.getSource()==openItem) {
			JFileChooser fileChooser = new JFileChooser();		//CRIA UM OBJETO MACANISMO DE ABRIR / SALVAR
			fileChooser.setCurrentDirectory(new File("."));		//PASTA PADRÃO	
			
			FileNameExtensionFilter filter = new FileNameExtensionFilter("Arquivos de Texto", "txt"); //PADRÃO DE PROCURA
			fileChooser.setFileFilter(filter);					//PADRÃO DE PROCURA NO MACANISMO
			
			int response = fileChooser.showOpenDialog(null);	
			if (response == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());			
				Scanner fileIn = null;
				
				try {
					fileIn = new Scanner(file);
					if (file.isFile()) {
						while(fileIn.hasNextLine()) {
							String line = fileIn.nextLine()+"\n";
							textArea.append(line);
						}
					}
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
				finally {
					fileIn.close();
				}
			}			
			
		}
		if(e.getSource()==saveItem) {
			JFileChooser fileChooser = new JFileChooser();		//CRIA UM OBJETO MACANISMO DE ABRIR / SALVAR
			fileChooser.setCurrentDirectory(new File("."));		//PASTA PADRÃO EX: C:\Users\Desktop "." = PASTA ATUAL
			
			int response = fileChooser.showSaveDialog(null);	
			if (response == JFileChooser.APPROVE_OPTION) {
				File file;										//VAR LOCAL DE ESCOLHA
				PrintWriter fileOut = null;						//VAR SALVAR
			
				file = new File(fileChooser.getSelectedFile().getAbsolutePath()); 	//FILE = ONDE EU ESCOLHI
				try {
					fileOut = new PrintWriter(file);								//SALVO NO FILE
					fileOut.println(textArea.getText());
				}catch (FileNotFoundException e1) {									//SE DER ERRO
					e1.printStackTrace();
				}
				finally {															//FECHA O PROGRMA DE SALVAR
					fileOut.close();
				}
			}		
		}		
		if(e.getSource()==exitItem) {
			System.exit(0);
		}		
	}
}
