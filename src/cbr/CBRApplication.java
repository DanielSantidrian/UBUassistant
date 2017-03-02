package cbr;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.Generated;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import gui.StarBar;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.Attribute;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import representation.CaseDescription;
import representation.CaseSolution;
import util.ResultsComparator;

public class CBRApplication implements StandardCBRApplication {

	@Generated(value = { "ColibriStudio" })
	Connector connector;
	
	@Generated(value = { "ColibriStudio" })
	static
	CBRCaseBase casebase;


	private static Collection<RetrievalResult> eval;
	private static CBRApplication cbrApp;
	private static CBRQuery query;
	private static CaseDescription cd = new CaseDescription();
	
	private static JFrame gui;
	private static JTextPane topTextPane;
	private static JScrollPane scrollPane;
	private static JTextField textoEnviar;
	private static JPanel buttonPanel;
	private static LinkedHashSet<CBRCase> casesToReatin;
	
	//private static ArrayList<CBRCase> casesToRetain = new ArrayList<CBRCase>();



	//******************************************************************/
	// Configuration
	//******************************************************************/

	@Override
	public void configure() throws ExecutionException {
		try{
			configureConnector();
			configureCaseBase();
		} catch (Exception e){
			throw new ExecutionException(e);
		}
	}

	/** Configures the connector */
	@Generated(value = { "CS-PTConector" })	
	private void configureConnector() throws InitializingException{
		
		connector = new jcolibri.connector.DataBaseConnector();
		connector.initFromXMLfile(jcolibri.util.FileIO
				.findFile("config/databaseconfig.xml"));
	}

	/** Configures the case base */
	@Generated(value = { "CS-CaseManager" })	
	private void configureCaseBase() throws InitializingException{
		casebase = new jcolibri.casebase.LinearCaseBase();
	}

	//******************************************************************/
	// Similarity
	//******************************************************************/
	
	/** Configures the similarity */
	@Generated(value = { "CS-Similarity" })	
	private NNConfig getSimilarityConfig() {
		NNConfig simConfig = new NNConfig();
		simConfig
				.setDescriptionSimFunction(new jcolibri.method.retrieve.NNretrieval.similarity.global.Euclidean());
		Attribute attribute0 = new Attribute("keyWord5", CaseDescription.class);
		simConfig
				.addMapping(
						attribute0,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute0, 0.75);
		Attribute attribute1 = new Attribute("keyWord4", CaseDescription.class);
		simConfig
				.addMapping(
						attribute1,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute1, 0.75);
		Attribute attribute2 = new Attribute("keyWord3", CaseDescription.class);
		simConfig
				.addMapping(
						attribute2,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute2, 0.75);
		Attribute attribute3 = new Attribute("keyWord2", CaseDescription.class);
		simConfig
				.addMapping(
						attribute3,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute3, 0.75);
		
		Attribute attribute5 = new Attribute("keyWord1", CaseDescription.class);
		simConfig
				.addMapping(
						attribute5,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute5, 0.75);
		return simConfig;
	}

	//******************************************************************/
	// Methods
	//******************************************************************/
	

	@Generated(value = { "ColibriStudio" })
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		casebase.init(connector);
		return casebase;
	}
		
	@Generated(value = { "ColibriStudio" })	
	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		NNConfig simConfig = getSimilarityConfig();

		eval= NNScoringMethod.evaluateSimilarity(casebase.getCases(), query, simConfig);
		//eval = SelectCases.selectTopKRR(eval, 3);

	}

	@Generated(value = { "ColibriStudio" })
	@Override
	public void postCycle() throws ExecutionException {
		connector.close();
	}

	/**
	 * Main method
	 */
	@Generated(value = { "ColibriStudio" })
	public static void main(String[] args) {
		
		cbrApp = new CBRApplication();
		
		try {
			cbrApp.configure();
			cbrApp.preCycle();
			
			query = new CBRQuery();
			
			createWindows();
			
						

		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * Method that create and display the main window
	 */
	private static void createWindows(){
		
		//Creation of the main window
		gui = new JFrame();
		gui.setLayout(null);
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().setBackground(new java.awt.Color(171, 38, 60));
		gui.setBounds(80, 80, 525, 540);
		gui.setTitle("UBUassistant v0.1");
		
		
		//Creation of the panel to show the answer of the question
		
		topTextPane = new JTextPane();
		topTextPane.setEditable(false);
		topTextPane.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		topTextPane.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 109, 179)));

		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 500, 400);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		gui.add(scrollPane);
		scrollPane.setViewportView(topTextPane);
		
		topTextPane.setText("> Hola soy UBUassistant, ¿En qué puedo ayudarle?\n");
		
		//Creation of a panel to make suggestions or rating answer
		buttonPanel = new JPanel();
		buttonPanel.setBounds(5, 410, 500, 48);
		buttonPanel.setBackground(new java.awt.Color(171, 38, 60));
		//buttonPanel.setLayout(new CardLayout());

		//Creation of the field to ask the questions
		textoEnviar = new JTextField();
		textoEnviar.setBounds(5, 460, 420, 30);
		textoEnviar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		textoEnviar.setVisible(true);
		gui.add(textoEnviar);
		
		
		//Creation of the button "Enviar"
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPanel.removeAll();
				buttonPanel.repaint();
				gui.repaint();
				gui.setVisible(true);
				buttonPanel.setVisible(false);
				
				CBRApplication.searchAnswer();
					
			}
		});
		btnEnviar.setBounds(430, 460, 70, 30);
		gui.add(btnEnviar);
		gui.getRootPane().setDefaultButton(btnEnviar);
		
		//Set the window visible
		gui.setVisible(true);
		
	}
	
	/**
	 * Method that gets the phrase entered by the user and get the answer
	 */
	private static void searchAnswer(){
		
		List<RetrievalResult> allResults = new ArrayList<RetrievalResult>();
		
		if(textoEnviar.getText().length()>0){
			
			topTextPane.setText(topTextPane.getText()+"\n"+"-  "+textoEnviar.getText()+"\n");
			String[] words = textoEnviar.getText().split("\\s+");
			
			for (String word : words) {
				
				if(word.length()>2){
					
					cd.setKeyWord1(word);
					cd.setKeyWord2(word);
					cd.setKeyWord3(word);
					cd.setKeyWord4(word);
					cd.setKeyWord5(word);
					query.setDescription(cd);
					
					try {
						cbrApp.cycle(query);
					} catch (ExecutionException e1) {
						e1.printStackTrace();
					}
					
					allResults.addAll(eval);
				}
			}
			Collections.sort(allResults, new ResultsComparator());
			printRetrievalSolutions(allResults);
			
			textoEnviar.setText(null);
			
		}
		
	}

	/**
	 * Method that prints the answer in the text pane and also generates some information into
	 * the buttonPanel
	 * @param s
	 */
	private static void printRetrievalSolutions(final List<RetrievalResult> s) {
		//Print answer into the panel
		String text = "";
		boolean flag=false;
		
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		casesToReatin = new LinkedHashSet<CBRCase>();
				
		for(RetrievalResult res : s){
			if(res.getEval()>0.35){
				CBRCase _case = res.get_case();
				casesToReatin.add(_case);
				CaseSolution solution = (CaseSolution) _case.getSolution();
				
				set.add(solution.getAnswer());
			}
			
		}
		
		for(String res : set){
			flag=true;
			text+="\n   " + res /*+ " -> " + res.getEval() */+ "\n";

		}	
		
		if(flag==true){
			
			if(set.size()>1){
				JTextArea texto = new JTextArea();
				texto.setText("Sé más concreto :)");
				texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
				texto.setForeground(Color.WHITE);
				texto.setBackground(new java.awt.Color(171, 38, 60));
				
				buttonPanel.add(texto);
				
				for(final CBRCase c : casesToReatin){
					final JButton btn = new JButton(((CaseDescription)c.getDescription()).getKeyWord1().toString());
					buttonPanel.add(btn);
					btn.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							topTextPane.setText(topTextPane.getText()+"\n"+"-  "+btn.getText()+"\n");
							topTextPane.setText(topTextPane.getText()+"\n"+"> Tal vez esto te ayude:\n"+"\n"+"   "+((CaseSolution)c.getSolution()).getAnswer().toString()+"\n");
							buttonPanel.removeAll();
							buttonPanel.repaint();
							gui.repaint();
							gui.setVisible(true);
							buttonPanel.setVisible(false);
							
							printUtilidad();
						}
					});
				}
				
				gui.add(buttonPanel);
				buttonPanel.repaint();
				gui.repaint();
				gui.setVisible(true);
				buttonPanel.setVisible(true);
				
			}else{
				topTextPane.setText(topTextPane.getText()+"\n"+"> Tal vez esto te ayude:\n"+text);
				
				printUtilidad();
			}
			
			
		}else{
			topTextPane.setText(topTextPane.getText()+"\n"+"> Lo siento, no tengo respuestas a tu pregunta :(\n"+text);
			JTextArea texto = new JTextArea();
			texto.setText("Sugerencias de busqueda");
			texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
			texto.setForeground(Color.WHITE);
			texto.setBackground(new java.awt.Color(171, 38, 60));
			
			buttonPanel.removeAll();
			buttonPanel.add(texto);
			
			for( int i=0; i<3;i++){
				final int tmp = i;
				JButton btnOp = new JButton();
				btnOp.setText(((CaseDescription)s.get(tmp).get_case().getDescription()).getKeyWord1());
				btnOp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						topTextPane.setText(topTextPane.getText()+"\n"+"-  "+btnOp.getText()+"\n");
						topTextPane.setText(topTextPane.getText()+"\n"+"> Tal vez esto te ayude:\n"+"\n"+"   "+((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString()+"\n");
						buttonPanel.removeAll();
						buttonPanel.repaint();
						gui.repaint();
						gui.setVisible(true);
						buttonPanel.setVisible(false);
						
						printUtilidad();

					}
				});

				buttonPanel.add(btnOp);
			}
			
			gui.add(buttonPanel);
			buttonPanel.repaint();
			gui.repaint();
			buttonPanel.setVisible(true);
			gui.setVisible(true);
			
		}
		
		try {
			cbrApp.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	
	}
	
	/**
	 * Method that displays in the button panel the information about the utility of the answer
	 */
	private static void printUtilidad(){
		
		JTextArea texto = new JTextArea();
		texto.setText("¿Le ha resultado útil esta información?");
		texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		texto.setForeground(Color.WHITE);
		texto.setBackground(new java.awt.Color(171, 38, 60));
		
		buttonPanel.removeAll();
		buttonPanel.add(texto);
		
		new StarBar(gui,buttonPanel);
		
		gui.add(buttonPanel);
		buttonPanel.repaint();
		gui.repaint();
		gui.setVisible(true);
		buttonPanel.setVisible(true);
		
	}
		
	
}
