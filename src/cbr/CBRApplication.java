package cbr;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import jcolibri.cbraplications.StandardCBRApplication;
import jcolibri.cbrcore.CBRCase;
import jcolibri.cbrcore.CBRCaseBase;
import jcolibri.cbrcore.CBRQuery;
import jcolibri.cbrcore.Connector;
import jcolibri.exception.ExecutionException;
import jcolibri.exception.InitializingException;
import jcolibri.method.retrieve.RetrievalResult;
import jcolibri.method.retrieve.NNretrieval.NNConfig;
import jcolibri.method.retrieve.NNretrieval.NNScoringMethod;

import javax.annotation.Generated;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import representation.CaseDescription;
import representation.CaseSolution;
import util.ResultsComparator;
import jcolibri.cbrcore.Attribute;

public class CBRApplication implements StandardCBRApplication {

	@Generated(value = { "ColibriStudio" })
	Connector connector;
	
	@Generated(value = { "ColibriStudio" })
	CBRCaseBase casebase;


	private static Collection<RetrievalResult> eval;
	private static CBRApplication cbrApp;
	private static CBRQuery query;
	private static JTextPane panel;

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
		Attribute attribute0 = new Attribute("keyWord3", CaseDescription.class);
		simConfig
				.addMapping(
						attribute0,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute0, 1.00);
		Attribute attribute1 = new Attribute("keyWord2", CaseDescription.class);
		simConfig
				.addMapping(
						attribute1,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute1, 0.75);
		Attribute attribute2 = new Attribute("keyWord1", CaseDescription.class);
		simConfig
				.addMapping(
						attribute2,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute2, 0.60);
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
			final CaseDescription cd = new CaseDescription();
			
			
			//Creation of the main window
			final JFrame frame = new JFrame();
			frame.setLayout(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(80, 80, 750, 600);
			
			
			//Creation of the panel to show the answer of the question
			panel = new JTextPane();
			panel.setEditable(false);
			panel.setSelectedTextColor(Color.blue);
			panel.setAutoscrolls(true);
			Font font = new Font(Font.DIALOG, 12, 12);
			panel.setFont(font);
			
			JScrollPane scrollPanel = new JScrollPane();
			scrollPanel.setBounds(5, 5, 700, 500);
			frame.add(scrollPanel);
			scrollPanel.setViewportView(panel);
			panel.setText("> Hola soy UBUassistant, ¿En qué puedo ayudarle?\n");
			
			//Creation of the field to ask the questions
			final JTextField textoEnviar = new JTextField();
			textoEnviar.setBounds(5, 510, 500, 30);
			textoEnviar.setVisible(true);
			frame.add(textoEnviar);
			
			//Creation of the button "Enviar"
			JButton btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					List<RetrievalResult> allResults = new ArrayList<RetrievalResult>();
					
					panel.setText(panel.getText()+"\n"+"-  "+textoEnviar.getText()+"\n");
					String[] words = textoEnviar.getText().split("\\s+");
					
					for (String word : words) {
						
						if(word.length()>2){
							
							cd.setKeyWord1(word);
							cd.setKeyWord2(word);
							cd.setKeyWord3(word);
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

				private void printRetrievalSolutions(List<RetrievalResult> s) {
					//Print answer into the panel
					String text = "";
					boolean flag=false;
					
					LinkedHashSet<String> set = new LinkedHashSet<String>();
					for(RetrievalResult res : s){
						
						if(res.getEval()>0.40){
							CBRCase _case = res.get_case();
							CaseSolution solution = (CaseSolution) _case.getSolution();
							
							set.add(solution.getAnswer());
						}
						
					}
					
					for(String res : set){
						flag=true;
						text+="\n   " + res /*+ " -> " + res.getEval() */+ "\n";

					}	
					if(flag==true)
						panel.setText(panel.getText()+"\n"+"> Esto es lo que he encontrado como respuesta a tu pregunta:\n"+text);
					else
						panel.setText(panel.getText()+"\n"+"> Lo siento, no tengo respuestas a tu pregunta :(\n"+text);
					
					try {
						cbrApp.postCycle();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
					
				}
			});
			btnEnviar.setBounds(510, 510, 70, 30);
			frame.add(btnEnviar);
			
			//Set the window visible
			frame.setVisible(true);
						
			

			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

	}
	

		
		
	
}
