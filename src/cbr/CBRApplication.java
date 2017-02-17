package cbr;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

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
import jcolibri.method.retrieve.selection.SelectCases;
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
		connector = new jcolibri.connector.PlainTextConnector();
		connector.initFromXMLfile(jcolibri.util.FileIO
				.findFile("config/plainTextConnectorConfig.xml"));
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
				.setDescriptionSimFunction(new jcolibri.method.retrieve.NNretrieval.similarity.global.Average());
		Attribute attribute0 = new Attribute("keyWord1", CaseDescription.class);
		simConfig
				.addMapping(
						attribute0,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute0, 0.65);
		Attribute attribute1 = new Attribute("keyWord2", CaseDescription.class);
		simConfig
				.addMapping(
						attribute1,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute1, 0.65);
		Attribute attribute2 = new Attribute("keyWord3", CaseDescription.class);
		simConfig
				.addMapping(
						attribute2,
						new jcolibri.method.retrieve.NNretrieval.similarity.local.MaxString());
		simConfig.setWeight(attribute2, 0.65);
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
		eval = SelectCases.selectTopKRR(eval, 3);
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
			JScrollPane scrollPanel = new JScrollPane();
			scrollPanel.setBounds(5, 5, 700, 500);
			frame.add(scrollPanel);
			scrollPanel.setViewportView(panel);
			panel.setText("> Hola soy UBUassistant, ¿En qué puedo ayudarle?");
			
			//Creation of the field to ask the questions
			final JTextField textoEnviar = new JTextField();
			textoEnviar.setBounds(5, 510, 500, 30);
			textoEnviar.setVisible(true);
			frame.add(textoEnviar);
			
			//Creation of the button "Enviar"
			JButton btnEnviar = new JButton("Enviar");
			btnEnviar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					panel.setText(panel.getText()+"\n"+"-  "+textoEnviar.getText());
					cd.setKeyWord1(textoEnviar.getText());
					cd.setKeyWord2(textoEnviar.getText());
					cd.setKeyWord3(textoEnviar.getText());
					query.setDescription(cd);
					try {
						cbrApp.cycle(query);
					} catch (ExecutionException e1) {
						e1.printStackTrace();
						
					}
					textoEnviar.setText(null);
					printRetrievalSolutions();
				}

				private void printRetrievalSolutions() {
					//Print answer into the panel
					String text = "";
					
					
					for(RetrievalResult res : eval){
						
						CBRCase _case = res.get_case();
						CaseSolution solution = (CaseSolution) _case.getSolution();
						if(res.getEval()>0.3)
							text+="   "+solution.getAnswer().toString()+"\n";
						
						
					}	
					panel.setText(panel.getText()+"\n"+"> Esto es lo que he encontrado al respecto:\n\n"+text);
					
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
