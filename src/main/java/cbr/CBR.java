package cbr;

import java.io.IOException;
import java.net.URL;
import java.security.CodeSource;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.annotation.Generated;

import database.DatabaseConnection;
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
public class CBR implements StandardCBRApplication {
	
	
	/**
	 * Global variables
	 */
	@Generated(value = { "ColibriStudio" })
	Connector connector;
	
	@Generated(value = { "ColibriStudio" })
	CBRCaseBase casebase;


	private static Collection<RetrievalResult> eval;
	private static CBRQuery query;
	private static CaseDescription cd = new CaseDescription();
	
	private static LinkedHashSet<CBRCase> casesToReatin;

    private static Map<String, List<String>> parcialResults;
    private static Map<LinkedHashSet<String>,List<String>> finalResults;
    private static HashMap<String, List<RetrievalResult>> badResuts = new HashMap<String,List<RetrievalResult>>();
    private static LinkedHashSet<String> suggestWord = new LinkedHashSet<String>();
    
    
    /**
     * Constructor of the class
     */
    public CBR() {
    	
    	configureCBR();
    	
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
				.findFile("C:/Users/dansa/workspaceJEE/UBUassistant/src/main/resources/databaseconfig.xml"));
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
	public void configureCBR() {
		
		try {
			configure();
			preCycle();
			
			query = new CBRQuery();
			
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that query for each word, generate the responses and store the best answers
	 * with the word that generated it.
	 * @param words Each word of the input text
	 */
	public void buildEval(String[] words) {
		
		parcialResults=new HashMap<String, List<String>>();
		casesToReatin = new LinkedHashSet<CBRCase>();
		
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
					cycle(query);
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
	public void buildFinalResults() {
		
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
			
			//printRetrievalSolutions(finalResults);
		}
	}



	
	
}
