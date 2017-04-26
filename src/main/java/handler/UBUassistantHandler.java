package handler;


import java.util.List;

import cbr.CBR;
import database.DatabaseConnection;


public class UBUassistantHandler {

    private String usertText;
    
    private String response;
    
    private String userId;
    
    private DatabaseConnection db;
    private List<String> sentenceList;
    private List<String> saluteList;
    private List<String> saluteResponseList;
    
    CBR cbrApp;
                   
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
    
    
    public String getSalute(){
    	
    	return sentenceList.get((int) (Math.random()*5));
    }
    
    private void searchAnswer(){
    	
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
    	
    	boolean flag=answerReservedWord(userTextLower);
    	
    	if(flag==false){
    		
    		//String[] words = userTextLower.split("\\s+");
    		
    		//cbrApp.buildEval(words);
    		
			//cbrApp.buildFinalResults();
			
			//noAnswerSuggestions();
		}
    	
    	//response = "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"+"<p>"+"Aqui va la respuesta"+"</p>";
    }
        
    

    
    

}