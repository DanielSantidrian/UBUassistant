package handler;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import cbr.CBR;
import database.DatabaseConnection;
import jcolibri.cbrcore.CBRCase;
import jcolibri.exception.ExecutionException;
import jcolibri.method.retrieve.RetrievalResult;
import representation.CaseDescription;
import representation.CaseSolution;
import util.ResultsComparator;


public class UBUassistantHandler {

    private String usertText;
    
    private String response;
    
    private String userId;
    
    private String starBar;
    private String starBarButton;
    
    private String suggestButtons;
    
    private String multipleButtons;
    
    private DatabaseConnection db;
    private List<String> sentenceList;
    private List<String> saluteList;
    private List<String> saluteResponseList;
    
    private LinkedHashSet<CBRCase> casesToReatin;

    private Map<String, List<String>> parcialResults;
    private Map<LinkedHashSet<String>,List<String>> finalResults;
    private HashMap<String, List<RetrievalResult>> badResuts;
    List<RetrievalResult> listOfValues;

    
    private LinkedHashSet<String> currentWords;
    
    private CBR cbrApp;
    
          
    public UBUassistantHandler(String userID) { 
    	this.userId=userID;
    	usertText = null;  
    	response = null;
    	
    	db = new DatabaseConnection(userID);
    	sentenceList=db.getSentenceList();
		saluteList=db.getSaluteList();
		saluteResponseList=db.getSaluteResponseList();
		
		cbrApp = new CBR();
    }


    public String getSalute(){
    	
    	return sentenceList.get((int) (Math.random()*5));
    }
    
    public String getRandomSentence(){
    	return sentenceList.get((int)(Math.random()*5+5));
    }
    
    private void putStarBar(){
		starBar="<form method=\"post\" id=\"starForm\" class=\"multipleForm\" action=\"starRating.jsp\">"+
							"<div class=\"rate\">"+
							"Valora esta respuesta "+
							"<input type=\"hidden\" id=\"div-content-suggest\" name=\"div-content\">"+
							"<input type=\"hidden\" id=\"vote\" name=\"vote\">"+
					        "<input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" onclick=\"getVoteAndSubmit(this)\" /><label for=\"star5\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star4\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star3\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star2\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star1\" title=\"text\"></label>"+
					        "</div>"+
					   "</form>";
	}
    
    
    private void putStarBarButton(){
		setStarBarButton("<form method=\"post\" id=\"starForm\" class=\"multipleForm\" action=\"starRatingButton.jsp\">"+
							"<div class=\"rate\">"+
							"Valora esta respuesta "+
							"<input type=\"hidden\" id=\"div-content-suggest\" name=\"div-content\">"+
							"<input type=\"hidden\" id=\"wordButton\" name=\"wordButton\">"+
							"<input type=\"hidden\" id=\"buttonDiv\" name=\"buttonDiv\">"+
							"<input type=\"hidden\" id=\"vote\" name=\"vote\">"+
					        "<input type=\"radio\" id=\"star5\" name=\"rate\" value=\"5\" onclick=\"getVoteAndSubmit(this)\" /><label for=\"star5\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star4\" name=\"rate\" value=\"4\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star4\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star3\" name=\"rate\" value=\"3\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star3\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star2\" name=\"rate\" value=\"2\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star2\" title=\"text\"></label>"+
					        "<input type=\"radio\" id=\"star1\" name=\"rate\" value=\"1\" onclick=\"getVoteAndSubmit(this)\"/><label for=\"star1\" title=\"text\"></label>"+
					        "</div>"+
					   "</form>");
	}
    
    private void searchAnswer(){
    	
    	starBar=null;
    	starBarButton=null;
    	suggestButtons=null;
    	multipleButtons=null;
    	String userTextLower=usertText.toLowerCase();
    	answerNonReservedWord(userTextLower);	
    }
    
    
    private boolean answerReservedWord(String userTextLower) {
		
    	String userTextOneUp=userTextLower.substring(0, 1).toUpperCase() + userTextLower.substring(1);

		if(saluteList.contains(userTextOneUp)){
			
			int index=saluteList.indexOf(userTextOneUp);
			
			if(index!=-1){
				response=saluteResponseList.get(index);
			}
			return true;
		}
		return false;	
	}
    
    
    private void answerNonReservedWord(String userTextLower){
    	
    	response="<p>"+getRandomSentence()+"</p>";
    	
    	boolean flag=answerReservedWord(userTextLower);
    	
    	if(flag==false){
    		
    		String[] words = userTextLower.split("\\s+");
    		
    		cbrApp.buildEval(words);
    		
			cbrApp.buildFinalResults();
			
			parcialResults=cbrApp.getParcialResults();
			finalResults=cbrApp.getFinalResults();
			casesToReatin=cbrApp.getCasesToReatin();
			badResuts=cbrApp.getBadResuts();
			
			getAnswers();
			
			noAnswerSuggestions();
		}
    }
    

	
	/**
	 * Method that prints the answer in the text pane.
	 * @param finalResults Map with the best answers and the words that generated them.
	 */
	public void getAnswers() {
		
		int cont=0;
		for(List<String> temp : finalResults.values()){
			cont+=temp.size();
		}

		if(!finalResults.isEmpty()){
			if(cont==1)
				oneAnswerResponse();
			else
				multipleAnswersResponse();
		}
		
		//Setting down the connection calling postCycle
		try {
			cbrApp.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	

	/**
	 * Method that prints the answer when there is only one answer
	 * @param finalResults Map with the best answers and the words that generated them.
	 * @param cont Number of answers
	 */
	private void oneAnswerResponse() {
		
		String temp=finalResults.get(finalResults.keySet().iterator().next()).iterator().next();
		
		if(temp.contains("http"))
			response += "<a href="+temp+" target=\"_blank\">"+temp+"</a>";
		else
			response += temp;
			
		LinkedHashSet<String> palabras=finalResults.keySet().iterator().next();
		currentWords=palabras;
		//Increasing the number of searches of the word
		db.aumentarNumBusquedas(palabras,finalResults.get(finalResults.keySet().iterator().next()).iterator().next());
		//Calling the method to ask the user about the utility of the answer
		putStarBar();
			
		
	}
	
	private void multipleAnswersResponse() {
		
		
		multipleButtons="";
		
		response="<p>Vaya, parece que tengo demasiadas respuestas.<p>"+
				 "<p>Intenta ser más concreto o selecciona alguna sugerencia.<p>";
		
		for(CBRCase c : casesToReatin){
			String temp=((CaseDescription)c.getDescription()).getKeyWord1().toString();
			String word=temp.substring(0, 1).toUpperCase() + temp.substring(1);
			String answer=((CaseSolution)c.getSolution()).getAnswer().toString();
			
			multipleButtons+="<form method=\"post\" id=\"multipleForm\" class=\"multipleForm\" action=\"multipleAnswer.jsp\">"+
									"<input type=\"hidden\" id=\"keyWord\" name=\"usertText\" value=\""+word+"\">"+
								    "<input type=\"hidden\" id=\"answer\" name=\"answer\" value=\""+answer+"\">"+
								    "<input type=\"hidden\" id=\"div-content-suggest\" name=\"div-content\">"+
								    "<input type=\"hidden\" id=\"buttonDiv\" name=\"buttonDiv\">"+
								    "<input type=\"button\" id=\"but\" class=\"multBut\" onclick=\"hideAndSubmit(this)\" value=\""+word+"\">"+
								"</form>";
		}
		
		putStarBarButton();

	}

	public void noAnswerSuggestions() {
		
		if(parcialResults.isEmpty()){
		
			response="<p>Lo siento no tengo respuestas a tu pregunta :(<p>";
			
			Collection<List<RetrievalResult>> values = badResuts.values();
			listOfValues = new ArrayList<RetrievalResult>();
			for(List<RetrievalResult> list : values){
				for(RetrievalResult r : list){
					listOfValues.add(r);
				}
			}
			
			Collections.sort(listOfValues, new ResultsComparator());
			
			//If there are at least one neighbor for the input text
			if(!badResuts.isEmpty()){
	
				response += "Echa un vistazo a las sugerencias de búsqueda";
				suggestButtons = "";
				
				for(int i=0;i<3;i++){
					
					String word=((CaseDescription)listOfValues.get(i).get_case().getDescription()).getKeyWord1();
					String answer=((CaseSolution)listOfValues.get(i).get_case().getSolution()).getAnswer().toString();

					suggestButtons+="<form method=\"post\" action=\"noAnswer.jsp\">"+
						"<input type=\"hidden\" id=\"num\" name=\"num\" value=\""+i+"\">"+
					    "<input type=\"hidden\" id=\"keyWord\" name=\"usertText\" value=\""+word+"\">"+
					    "<input type=\"hidden\" id=\"answer\" name=\"answer\" value=\""+answer+"\">"+
					    "<input type=\"hidden\" id=\"div-content-suggest\" name=\"div-content\">"+
					    "<input type=\"submit\" class=\"sugBut\" value=\""+word+"\">"+
					    "</form>";
				}
				
				putStarBarButton();
			}	
		}
	}
	
	 public void setUsertText( String usertText ) {
    	this.usertText = usertText; 
    	searchAnswer();
    }
    
    
    public String getUsertText() {   
        return usertText;     
    }


	public String getResponse() {
		return response;
	}


	public void setResponse(String response) {
		this.response = response;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getStarBar() {
		return starBar;
	}


	public void setStarBar(String starBar) {
		this.starBar = starBar;
	}


	public String getSuggestButtons() {
		return suggestButtons;
	}


	public void setSuggestButtons(String suggestButtons) {
		this.suggestButtons = suggestButtons;
	}


	public String getMultipleButtons() {
		return multipleButtons;
	}


	public void setMultipleButtons(String multipleButtons) {
		this.multipleButtons = multipleButtons;
	}


	public LinkedHashSet<String> getCurrentWords() {
		return currentWords;
	}


	public void setCurrentWords(LinkedHashSet<String> currentWords) {
		this.currentWords = currentWords;
	}


	public DatabaseConnection getDb() {
		return db;
	}


	public void setDb(DatabaseConnection db) {
		this.db = db;
	}


	public HashMap<String, List<RetrievalResult>> getBadResuts() {
		return badResuts;
	}


	public void setBadResuts(HashMap<String, List<RetrievalResult>> badResuts) {
		this.badResuts = badResuts;
	}


	public List<RetrievalResult> getListOfValues() {
		return listOfValues;
	}


	public void setListOfValues(List<RetrievalResult> listOfValues) {
		this.listOfValues = listOfValues;
	}


	public String getStarBarButton() {
		return starBarButton;
	}


	public void setStarBarButton(String starBarButton) {
		this.starBarButton = starBarButton;
	}
	
	
	
    
    

}