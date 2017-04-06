package cbr;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Generated;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;

import database.DatabaseConnection;
import gui.StarBar;
import gui.Windows;
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

/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class CBRApplication implements StandardCBRApplication {
	
	
	/**
	 * Global variables
	 */
	@Generated(value = { "ColibriStudio" })
	Connector connector;
	
	@Generated(value = { "ColibriStudio" })
	CBRCaseBase casebase;


	private static Collection<RetrievalResult> eval;
	private static CBRApplication cbrApp;
	private static CBRQuery query;
	private static CaseDescription cd = new CaseDescription();
	
	private static LinkedHashSet<CBRCase> casesToReatin;
    
    private static DatabaseConnection db;
    private static List<String> sentenceList;
    private static List<String> saluteList;
    private static List<String> saluteResponseList;
    
    private static Map<String, List<String>> parcialResults;
    private static Map<LinkedHashSet<String>,List<String>> finalResults;
    private static HashMap<String, List<RetrievalResult>> badResuts = new HashMap<String,List<RetrievalResult>>();
    private static LinkedHashSet<String> suggestWord = new LinkedHashSet<String>();
    
    private String userID;
    
    /**
     * Constructor of the class
     */
    public CBRApplication() {
    	
    	DateFormat formatForId = new SimpleDateFormat("yyMMddHHmmssSSS");
    	userID=formatForId.format(new Date());
    	
		db = new DatabaseConnection(userID);
		sentenceList=db.getSentenceList();
		saluteList=db.getSaluteList();
		saluteResponseList=db.getSaluteResponseList();
	}
    

	//******************************************************************/
	// Configuration
	//******************************************************************/
    
    /**
     * Method that calls the configuration methods
     * @throws ExecutionException
     */
	@Override
	public void configure() throws ExecutionException {
		try{
			configureConnector();
			configureCaseBase();
		} catch (Exception e){
			throw new ExecutionException(e);
		}
	}

	/**
	 * Configures the connector
	 * @throws InitializingException Exception that is thrown when it is not possible to build the connector or the casebase
	 */
	@Generated(value = { "CS-PTConector" })	
	private void configureConnector() throws InitializingException{
		
		connector = new jcolibri.connector.DataBaseConnector();
		connector.initFromXMLfile(jcolibri.util.FileIO
				.findFile("config/databaseconfig.xml"));
	}


	/**
	 * Configures the case base
	 * @throws InitializingException Exception that is thrown when it is not possible to build the connector or the casebase
	 */
	@Generated(value = { "CS-CaseManager" })	
	private void configureCaseBase() throws InitializingException{
		casebase = new jcolibri.casebase.LinearCaseBase();
	}

	//******************************************************************/
	// Similarity
	//******************************************************************/
	
	/** Configures the similarity */
	@Generated(value = { "CS-Similarity" })	
	private static NNConfig getSimilarityConfig() {
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
	
	/**
	 * Method preCycle that will be called to initialize and return the case base
	 * @throws ExecutionException
	 */
	@Generated(value = { "ColibriStudio" })
	@Override
	public CBRCaseBase preCycle() throws ExecutionException {
		casebase.init(connector);
		return casebase;
	}
	
	/**
	 * Method that save in a collection the nearest neighbors of the query
	 * @param query 
	 * @throws ExecutionException
	 */
	@Generated(value = { "ColibriStudio" })	
	@Override
	public void cycle(CBRQuery query) throws ExecutionException {
		NNConfig simConfig = getSimilarityConfig();
		eval= NNScoringMethod.evaluateSimilarity(casebase.getCases(), query, simConfig);
		//eval = SelectCases.selectTopKRR(eval, 3);
	}

	/**
	 * Method that will be called to close the connection
	 * @throws ExecutionException
	 */
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
			
			Windows.createWindows();
			
			//Setting the random welcome sentence
		    printSystemTextPaneWithoutRandom(sentenceList.get((int) (Math.random()*5)));

		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * Method that gets the phrase entered by the user and display the answer
	 */
	public static void searchAnswer(){
		
		parcialResults=new HashMap<String, List<String>>();
		casesToReatin = new LinkedHashSet<CBRCase>();
		
		if(Windows.getTextoEnviar().getText().length()>0){
			
			printUserTextPane(Windows.getTextoEnviar().getText().substring(0, 1).toUpperCase() + Windows.getTextoEnviar().getText().substring(1));
			
			String textTextoEnviar=Windows.getTextoEnviar().getText().toLowerCase();
			
			responseNonReservedWord(textTextoEnviar);
	
		}

		Windows.getTextoEnviar().setText(null);
	}
	

	/**
	 * Method that builds the structure for storing the response
	 * @param textTextoEnviar Input text by the user
	 */
	private static void responseNonReservedWord(String textTextoEnviar) {
		
		boolean flag=responseReservedWord(textTextoEnviar);
		
		if(flag==false){
			
			String[] words = textTextoEnviar.split("\\s+");
			
			buildEval(words);
			
			buildFinalResults();
			
			noAnswerSuggestions();
		}
	}
	
	
	/**
	 * Method that print in the text pane if the input word of the user is reserved
	 * @param textTextoEnviar Input text by the user
	 */
	private static boolean responseReservedWord(String textTextoEnviar) {
		
		//If the input text is a reserved word, also checking with the first capital letter and with all capital letters
		if(saluteList.contains(Windows.getTextoEnviar().getText()) 
				|| saluteList.contains(textTextoEnviar.substring(0, 1).toUpperCase() + textTextoEnviar.substring(1))){
			
			//Get the index of the element in the list
			int index=saluteList.indexOf(Windows.getTextoEnviar().getText());
			int index2=saluteList.indexOf(textTextoEnviar.substring(0, 1).toUpperCase() + textTextoEnviar.substring(1));
			
			//Display the response to the salute depending of the index
			if(index!=-1){
				printSystemTextPaneWithoutRandom(saluteResponseList.get(index));
			}else{
				printSystemTextPaneWithoutRandom(saluteResponseList.get(index2));
			}
			return true;
		}
		
		return false;
		
	}
	
	/**
	 * Method that query for each word, generate the responses and store the best answers
	 * with the word that generated it.
	 * @param words Each word of the input text
	 */
	private static void buildEval(String[] words) {
		
		//For each word in the input text
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
				
				//Adding all the nearest neighbors to allResults map with the word that generates the answer
				badResuts.put(word, new ArrayList<RetrievalResult>(eval));
				eval.removeIf(e -> e.getEval()<0.35);
				List<String> answer = new ArrayList<String>();
				for(RetrievalResult r : eval){
					CBRCase _case = r.get_case();
					casesToReatin.add(_case);
					answer.add(((CaseSolution)r.get_case().getSolution()).getAnswer());
				}
				
				if(!answer.isEmpty())
					parcialResults.put(word, answer);
			}
		}
		
	}

	/**
	 * Method that generates the final results with the partial results of builEval.
	 */
	private static void buildFinalResults() {
		
		//If there is an answer for the input text
		if(!parcialResults.isEmpty()){
			
			//Getting the common answer (the best answer) of the different words input by the user
			List<String> aux= new ArrayList<String>(parcialResults.get(parcialResults.keySet().iterator().next()));
			
			for(String key : parcialResults.keySet())
				aux.retainAll(parcialResults.get(key));
			
			if(aux.isEmpty()){
				for(String key : parcialResults.keySet())
					aux.addAll(parcialResults.get(key));
			}
			
			//Getting all the words that have generated the best answer and storing them in a map
			finalResults = new HashMap<LinkedHashSet<String>,List<String>>();
			LinkedHashSet<String> word = new LinkedHashSet<String>();
			
			for(String res : aux){	
				
				for (String o : parcialResults.keySet()) {
				      if (parcialResults.get(o).contains(res)) {
				        word.add(o);
				      }
				}
				
				if(finalResults.containsKey(word)){
					List<String> temp = new ArrayList<String>(finalResults.get(word));
					temp.add(res);
					finalResults.put(word, temp);
				}else{
					List<String> lista = new ArrayList<String>();
					lista.add(res);
					finalResults.put(word, lista);
				}
				
				word = new LinkedHashSet<String>();
			}
			
			printRetrievalSolutions(finalResults);
		}
	}



	/**
	 * Method that if there is no answer for the input text, displays buttons with the nearest neighbors for making suggestions
	 */
	private static void noAnswerSuggestions() {
		
		if(parcialResults.isEmpty()){
			
			Collection<List<RetrievalResult>> values = badResuts.values();
			List<RetrievalResult> listOfValues = new ArrayList<RetrievalResult>();
			for(List<RetrievalResult> list : values){
				for(RetrievalResult r : list){
					listOfValues.add(r);
				}
			}
			
			Collections.sort(listOfValues, new ResultsComparator());
			
			printSystemTextPaneWithoutRandom("Lo siento, no tengo respuestas a tu pregunta :(");
			
			//Storing in database
			LinkedHashSet<String> var = new LinkedHashSet<String>();
			var.add(Windows.getTextoEnviar().getText());
			db.aumentarNumBusquedas(var, null);
			
			//If there are at least one neighbor for the input text
			if(!badResuts.isEmpty()){
				
				//Adding text to the button panel
				JTextArea texto = new JTextArea();
				texto.setText("Sugerencias de búsqueda");
				texto.setFont(new java.awt.Font("Segoe UI Semibold", Font.BOLD, 14));
				texto.setForeground(Color.WHITE);
				texto.setBackground(new java.awt.Color(171, 38, 60));
				
				Windows.getButtonPanel().removeAll();
				Windows.getButtonPanel().add(texto);
				
				//Creating and displaying in the button panel three recommendations of search
				for( int i=0; i<3;i++){
					final int tmp = i;
					JButton btnOp = new JButton();
					btnOp.setBackground(new Color(0, 109, 179));
					btnOp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
					btnOp.setForeground(Color.white);
					btnOp.setText(((CaseDescription)listOfValues.get(tmp).get_case().getDescription()).getKeyWord1());
					
					//Adding an action listener for each button
					btnOp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							printUserTextPane(btnOp.getText().substring(0, 1).toUpperCase() + btnOp.getText().substring(1));
							
							printSystemTextPaneWithRandom(((CaseSolution)listOfValues.get(tmp).get_case().getSolution()).getAnswer().toString());

							for (String o : badResuts.keySet()) {
								if (badResuts.get(o).contains(listOfValues.get(tmp))) {
									suggestWord.add(o);
								}		
							}
							
							//When there are no answers and the user push a suggestion button it is supposed
							//That the text input by the user is related with the button so we store all this 
							//information for making the system learn
							db.learnCases(suggestWord.iterator().next(), ((CaseSolution)listOfValues.get(tmp).get_case().getSolution()).getAnswer().toString());
							
							//Repainting the button panel
							Windows.getButtonPanel().removeAll();
							Windows.getButtonPanel().repaint();
							Windows.getButtonPanel().revalidate();
							
							//Calling the method to ask the user about the utility of the answer
							LinkedHashSet<String> var = new LinkedHashSet<String>();
							var.add(btnOp.getText());
							printUtilidad(var,null);
							//Increasing the number of searches of the word
							var = new LinkedHashSet<String>();
							var.add(btnOp.getText());
							db.aumentarNumBusquedas(var, ((CaseSolution)listOfValues.get(tmp).get_case().getSolution()).getAnswer().toString());
						}
					});
					//Adding the button to the button panel
					Windows.getButtonPanel().add(btnOp);
				}
				//Repainting the button panel
				Windows.getButtonPanel().repaint();
				Windows.getButtonPanel().revalidate();
			}
		}
	}



	/**
	 * Method that prints the answer in the text pane.
	 * @param finalResults Map with the best answers and the words that generated them.
	 */
	private static void printRetrievalSolutions(Map<LinkedHashSet<String>,List<String>> finalResults) {
				
		int cont=0;
		for(List<String> temp : finalResults.values()){
			cont+=temp.size();
		}

		if(!finalResults.isEmpty()){
			printMultipleAnswers(finalResults,cont);
			printOneAnswer(finalResults,cont);
		}
		
		//Setting down the connection calling postCycle
		try {
			cbrApp.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Method that prints the answer when there are more than one answer
	 * @param finalResults Map with the best answers and the words that generated them.
	 * @param cont Number of answers
	 */
	private static void printMultipleAnswers(Map<LinkedHashSet<String>, List<String>> finalResults, int cont) {
		
		if(cont>1){
			
			//Displaying a text into the button panel
			JTextArea texto = new JTextArea();
			texto.setText("Sé más concreto :)");
			texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
			texto.setForeground(Color.WHITE);
			texto.setBackground(new java.awt.Color(171, 38, 60));
			
			Windows.getButtonPanel().add(texto);
			
			//Creating an atomic boolean to check if it is the first checkbox we select
			AtomicBoolean first = new AtomicBoolean(true);
			
			//For each case in the set casesToRetain it is created a checkbox
			for(final CBRCase c : casesToReatin){
				
				JCheckBox cbx = new JCheckBox(((CaseDescription)c.getDescription()).getKeyWord1().toString());
				cbx.setBorder(null);
				cbx.setBackground(null);
				cbx.setForeground(Color.white);
				cbx.setFont(new Font("Segoe UI Semibold", 0, 14));
				Windows.getButtonPanel().add(cbx);
				Windows.getButtonPanel().repaint();
				Windows.getButtonPanel().revalidate();
				//Adding an action listener to the checkboxes
				cbx.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						//If the checkbox is selected
						if(cbx.isSelected()){
							
							cbx.setEnabled(false);
							
							//If it is the first checkbox we select
							if(first.get()==true){
								
								first.set(false);
								printSystemTextPaneWithRandom(((CaseSolution)c.getSolution()).getAnswer().toString());
							
							}else{
								printSystemTextPaneWithoutRandom(((CaseSolution)c.getSolution()).getAnswer().toString());
							}
							
							//Increasing the number of searches of the word
							LinkedHashSet<String> var = new LinkedHashSet<String>();
							var.add(cbx.getText());
							db.aumentarNumBusquedas(var, ((CaseSolution)c.getSolution()).getAnswer().toString());
							
							suggestUtilidad(var);
							
						}
					}
				});
			}
			
			//Displays the checkboxes in the button panel
			Windows.getGui().repaint();
			Windows.getGui().revalidate();
		}
		
		
	}
	
	
	
	
	/**
	 * Method that prints the answer when there is only one answer
	 * @param finalResults Map with the best answers and the words that generated them.
	 * @param cont Number of answers
	 */
	private static void printOneAnswer(Map<LinkedHashSet<String>, List<String>> finalResults, int cont) {
		
		if(cont==1){
			
			printSystemTextPaneWithRandom(finalResults.get(finalResults.keySet().iterator().next()).iterator().next());

			LinkedHashSet<String> palabras=finalResults.keySet().iterator().next();
			//Increasing the number of searches of the word
			db.aumentarNumBusquedas(palabras,finalResults.get(finalResults.keySet().iterator().next()).iterator().next());
			//Calling the method to ask the user about the utility of the answer

			printUtilidad(palabras,null);
			
		}
	}


	/**
	 * Method that displays a user sentence in the text pane
	 * @param frase Sentence of the user
	 */
	private static void printUserTextPane(String frase){
		
		//Display the input text
		try {
			Windows.getEditorKit().insertHTML(Windows.getDoc(), Windows.getDoc().getLength(), "<html><p align=\"right\"><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:black\">"
														+ frase
														+ "</b></p></html>", 0, 0, null);
		} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
		
		//Always show the last update
		Windows.getTopTextPane().setCaretPosition(Windows.getTopTextPane().getDocument().getLength());
		
	}
	
	/**
	 * Method that displays a system sentence in the text pane without a random sentence before.
	 * @param frase Answer to print in the text pane
	 */
	private static void printSystemTextPaneWithoutRandom(String frase){
		
		if(frase.contains("http")){
		
			try {
				Windows.getEditorKit().insertHTML(Windows.getDoc(), Windows.getDoc().getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
															+ "<p><a href=\""+frase+"\">"+frase+"</a></p>" 
															+ "</b></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
			
		}else{
			
			try {
				Windows.getEditorKit().insertHTML(Windows.getDoc(), Windows.getDoc().getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
															+ "<p>"+frase+"</p>" 
															+ "</b></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
		}
		
		//Always show the last update
		Windows.getTopTextPane().setCaretPosition(Windows.getTopTextPane().getDocument().getLength());
		
	}
	
	
	/**
	 * Method that displays a system sentence in the text pane with a random sentence before.
	 * @param frase Answer to print in the text pane.
	 */
	private static void printSystemTextPaneWithRandom(String frase){
		
		if(frase.contains("http")){
			
			try {
				Windows.getEditorKit().insertHTML(Windows.getDoc(), Windows.getDoc().getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
															+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"+"<p><a href=\""+frase+"\">"+frase+"</a></p>" 
															+ "</b></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
			
		}else{
		
			try {
				Windows.getEditorKit().insertHTML(Windows.getDoc(), Windows.getDoc().getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
															+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"+"<p>"+frase+"</p>" 
															+ "</b></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
		}
		//Always show the last update
		Windows.getTopTextPane().setCaretPosition(Windows.getTopTextPane().getDocument().getLength());
	}
	
	private static void suggestUtilidad(LinkedHashSet<String> palabras){
		
		Component[] components= Windows.getButtonPanel().getComponents();
		
		JTextArea texto = new JTextArea();
		texto.setText("¿Quiere valorar esta respuesta?");
		texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		texto.setForeground(Color.WHITE);
		texto.setBackground(new java.awt.Color(171, 38, 60));
		
		Windows.getButtonPanel().removeAll();
		Windows.getButtonPanel().add(texto);
		
		JButton buttonYes = new JButton();
		buttonYes.setBackground(new Color(0, 109, 179));
		buttonYes.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		buttonYes.setForeground(Color.white);
		buttonYes.setText("Si");
		
		buttonYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				printUtilidad(palabras,components);
			}
		});
		//Adding the button to the button panel
		Windows.getButtonPanel().add(buttonYes);
		
		JButton buttonNo = new JButton();
		buttonNo.setBackground(new Color(0, 109, 179));
		buttonNo.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		buttonNo.setForeground(Color.white);
		buttonNo.setText("No");
		
		buttonNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Windows.getButtonPanel().removeAll();
				for(Component c : components){
					Windows.getButtonPanel().add(c);
				}
				Windows.getButtonPanel().repaint();
				Windows.getButtonPanel().revalidate();
			}
		});
		//Adding the button to the button panel
		Windows.getButtonPanel().add(buttonNo);
		
		Windows.getButtonPanel().repaint();
		Windows.getButtonPanel().revalidate();
		
	}
	

	/**
	 * Method that displays in the button panel the information about the utility of the answer
	 * @param components 
	 */
	private static void printUtilidad(LinkedHashSet<String> palabras, Component[] components){
		
		JTextArea texto = new JTextArea();
		texto.setText("¿Le ha resultado útil esta información?");
		texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		texto.setForeground(Color.WHITE);
		texto.setBackground(new java.awt.Color(171, 38, 60));
		
		Windows.getButtonPanel().removeAll();
		Windows.getButtonPanel().add(texto);
		
		new StarBar(db,Windows.getGui(),Windows.getButtonPanel(),palabras,components);
		Windows.getButtonPanel().repaint();
		Windows.getButtonPanel().revalidate();
	}
}
