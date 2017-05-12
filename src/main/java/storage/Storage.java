package storage;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Daniel Santidrian Alonso
 *
 */
public class Storage {
	
	private List<String> chatOutput;
	
	/**
	 * Constructor of the class.
	 */
	public Storage() {
		chatOutput=new ArrayList<String>();
	}

	/**
	 * Function that returns the text of the chat output.
	 * @return text text of the chat output.
	 */
	public String getChatOutput() {
		
		String text="";
		
		for(String s : chatOutput)
			text+=s;
		
		return text;
	}

	/**
	 * Method that sets the text of the chat output.
	 * @param typeMessage message type for the user or system.
	 * @param text text of the chat output.
	 */
	public void setChatOutput(String typeMessage, String text) {
		
		text="<div class=\""+typeMessage+"\">"+"<div class=\"message\">"+text+"</div></div>\n";

		for(int i=0; i<chatOutput.size();i++){
			if(chatOutput.get(i).concat(text).length()<Integer.MAX_VALUE){
				String newText=chatOutput.get(i).concat(text);
				chatOutput.remove(i);
				chatOutput.add(newText);
			}else{
				chatOutput.add(text);
			}
		}
		
		if(chatOutput.size()==0)
			chatOutput.add(text);
		
	}

}
