package storage;



import static org.junit.Assert.assertEquals;



import org.junit.Test;



public class StorageTest {

	@Test
	public void StorageSingleTextTest() {
		
		
		String expectedOutput="<div class=\"user-message\">"+"<div class=\"message\">becas internacionales</div></div>\n";

		Storage storage = new Storage();
		
		storage.setChatOutput("user-message", "becas internacionales");
		
		assertEquals(expectedOutput,storage.getChatOutput());

	}
	
	@Test
	public void StorageMultipleTextTest() {
		
		String[] messageType = new String[5];
		messageType[0]="user-message";
		messageType[1]="bot-message";
		messageType[2]="invented-message";
		messageType[3]="user-message";
		messageType[4]="bot-message";
		
		String[] messageText = new String[5];
		messageText[0]="Hola";
		messageText[1]="Adios";
		messageText[2]="Texto";
		messageText[3]="becas";
		messageText[4]="informatica";
		
		String expectedOutput="";
		Storage storage = new Storage();
		
		for(int i=0;i<messageText.length;i++){
			expectedOutput+="<div class=\""+messageType[i]+"\">"+"<div class=\"message\">"+messageText[i]+"</div></div>\n";
			storage.setChatOutput(messageType[i], messageText[i]);
		}
		
		assertEquals(expectedOutput,storage.getChatOutput());
	}
	


}
