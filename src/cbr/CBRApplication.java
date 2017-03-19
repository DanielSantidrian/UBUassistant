package cbr;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
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
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SizeRequirements;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.InlineView;
import javax.swing.text.html.ParagraphView;

import database.DatabaseConnection;
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
	
	private static JFrame gui;
	private static JTextPane topTextPane;
	private static JScrollPane scrollPane;
	private static JTextField textoEnviar;
	private static JPanel buttonPanel;
	private static LinkedHashSet<CBRCase> casesToReatin;
	
	private static HTMLDocument doc;
    private static HTMLEditorKit editorKit;
    
    private static DatabaseConnection db;
    private static List<String> sentenceList;
    private static List<String> saluteList;
    private static List<String> saluteResponseList;
    
    private static Map<String,List<RetrievalResult>> allResults;
    
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
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.getContentPane().setBackground(new java.awt.Color(171, 38, 60));
		gui.setMinimumSize(new Dimension(550,550));
		gui.setLocation(80, 80);
		gui.setTitle("UBUassistant v0.1");
		
		
		//Creation of the panel to show the answer of the question
		topTextPane = new JTextPane();
		topTextPane.setEditable(false);
		topTextPane.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		topTextPane.setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 10));
		
		//Always show the last update
		topTextPane.setCaretPosition(topTextPane.getDocument().getLength());

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(topTextPane);
		
		//Configuring the text pane to show html tags
	    topTextPane.setContentType( "text/html" );
	    topTextPane.setEditable(false);
	    
	    topTextPane.setEditorKit(new HTMLEditorKit(){ 
	           /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override 
	           public ViewFactory getViewFactory(){ 
	 
	               return new HTMLFactory(){ 
	                   public View create(Element e){ 
	                      View v = super.create(e); 
	                      if(v instanceof InlineView){ 
	                          return new InlineView(e){ 
	                              public int getBreakWeight(int axis, float pos, float len) { 
	                                  return GoodBreakWeight; 
	                              } 
	                              public View breakView(int axis, int p0, float pos, float len) { 
	                                  if(axis == View.X_AXIS) { 
	                                      checkPainter(); 
	                                      int p1 = getGlyphPainter().getBoundedPosition(this, p0, pos, len); 
	                                      if(p0 == getStartOffset() && p1 == getEndOffset()) { 
	                                          return this; 
	                                      } 
	                                      return createFragment(p0, p1); 
	                                  } 
	                                  return this; 
	                                } 
	                            }; 
	                      } 
	                      else if (v instanceof ParagraphView) { 
	                          return new ParagraphView(e) { 
	                              protected SizeRequirements calculateMinorAxisRequirements(int axis, SizeRequirements r) { 
	                                  if (r == null) { 
	                                        r = new SizeRequirements(); 
	                                  } 
	                                  float pref = layoutPool.getPreferredSpan(axis); 
	                                  float min = layoutPool.getMinimumSpan(axis); 
	                                  // Don't include insets, Box.getXXXSpan will include them. 
	                                    r.minimum = (int)min; 
	                                    r.preferred = Math.max(r.minimum, (int) pref); 
	                                    r.maximum = Integer.MAX_VALUE; 
	                                    r.alignment = 0.5f; 
	                                  return r; 
	                                } 
	 
	                            }; 
	                        } 
	                      return v; 
	                    } 
	                }; 
	            } 
	        }); 
	    
	    doc = (HTMLDocument)topTextPane.getDocument();
	    editorKit = (HTMLEditorKit)topTextPane.getEditorKit();
	    
	    //Adding a HyperlinkListener to open the browser
	    topTextPane.addHyperlinkListener(new HyperlinkListener() {
			@Override
			public void hyperlinkUpdate(HyperlinkEvent event) {
				if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
	                String url = event.getURL().toString();
	                try {
						Desktop.getDesktop().browse(URI.create(url));
					} catch (IOException e) {
						e.printStackTrace();
					}
	            }
				//Always show the last update
				topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
			}
	    });
	    
	    //Setting the random welcome sentence
	    try {
			editorKit.insertHTML(doc, doc.getLength(), "<html><p><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
														+ sentenceList.get((int) (Math.random()*5))
														+ "</b></p></html>", 0, 0, null);
		} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
		
		
		//Creation of a panel to make suggestions or rating answer
		buttonPanel = new JPanel();
		buttonPanel.setBackground(new java.awt.Color(171, 38, 60));
		buttonPanel.setMinimumSize(new Dimension(474,60));

		//Creation of the field to ask the questions
		textoEnviar = new JTextField();
		textoEnviar.setBorder(null);
		textoEnviar.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		textoEnviar.setVisible(true);
		
		//Creation of the button "Enviar"
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buttonPanel.removeAll();
				buttonPanel.repaint();
				buttonPanel.revalidate();
				
				CBRApplication.searchAnswer();
					
			}
		});
		btnEnviar.setBackground(new Color(0, 109, 179));
		btnEnviar.setBorder(null);
		btnEnviar.setForeground(Color.white);
		gui.getRootPane().setDefaultButton(btnEnviar);
		
		//Configure layout
        
		GroupLayout layout = new GroupLayout(gui.getContentPane());
        gui.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(textoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane)
                        .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(textoEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            
		//Setting the window visible
        
        gui.setVisible(true);
	}
	
	/**
	 * Method that gets the phrase entered by the user and display the answer
	 */
	private static void searchAnswer(){
		
		//List that will save all the nearest neighbors of all the words in the input sentence
		allResults = new HashMap<String,List<RetrievalResult>>();
		
		//If the user inputs at least a letter
		if(textoEnviar.getText().length()>0){
			
			//Display the input text
			try {
				editorKit.insertHTML(doc, doc.getLength(), "<html><p align=\"right\"><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:black\">"
															+ textoEnviar.getText().substring(0, 1).toUpperCase() + textoEnviar.getText().substring(1)
															+ "</b></p></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
			
			//Always show the last update
			topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
			
			//Split the hypothetical sentence in different words
			String textTextoEnviar=textoEnviar.getText().toLowerCase();
			String[] words = textTextoEnviar.split("\\s+");
			
			//If the input text is a reserved word, also checking with the first capital letter and with all capital letters
			if(saluteList.contains(textoEnviar.getText()) 
					|| saluteList.contains(textTextoEnviar.substring(0, 1).toUpperCase() + textTextoEnviar.substring(1))){
				
				//Get the index of the element in the list
				int index=saluteList.indexOf(textoEnviar.getText());
				int index2=saluteList.indexOf(textTextoEnviar.substring(0, 1).toUpperCase() + textTextoEnviar.substring(1));
				
				//Display the response to the salute depending of the index
				if(index!=-1){
					try {
						editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																	+ "<p>"+saluteResponseList.get(index)+"</p>" 
																	+ "</b></html>", 0, 0, null);
					} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
				}else{
					try {
						editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																	+ "<p>"+saluteResponseList.get(index2)+"</p>" 
																	+ "</b></html>", 0, 0, null);
					} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
				}
				
				//Always show the last update
				topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
				
			//If the input text is not a reserved word
			}else{
				
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
						allResults.put(word, new ArrayList<RetrievalResult>(eval));
					}
				}
				
				//Sorting all the results with the ResultsComparator. For more eval value to fewer eval value
				Collection<List<RetrievalResult>> values = allResults.values();
				List<RetrievalResult> listOfValues = new ArrayList<RetrievalResult>();
				for(List<RetrievalResult> list : values){
					for(RetrievalResult r : list){
						listOfValues.add(r);
					}
				}
				
				Collections.sort(listOfValues, new ResultsComparator());
				
				//Calling the method printRetrievalSolutions to print the solution on the textPane
				printRetrievalSolutions(listOfValues);

			}
			//Setting the text field to null
			textoEnviar.setText(null);
		}
	}

	/**
	 * Method that prints the answer in the text pane and also generates some information into
	 * the buttonPanel
	 * @param s
	 */
	private static void printRetrievalSolutions(final List<RetrievalResult> s) {
		
		String text = "";
		boolean flag=false;
		
		//Set for saving the text of the answers
		LinkedHashSet<String> set = new LinkedHashSet<String>();
		//Set for saving the case of the answers
		casesToReatin = new LinkedHashSet<CBRCase>();
		//Set for saving the word of the best answer
		LinkedHashSet<String> word = new LinkedHashSet<String>();
			
		//Storing all the answers which their cases are reasonably near to the input word
		for(RetrievalResult res : s){
			
			if(res.getEval()>0.35){

				//Saving the word that generates the best answer
				for (String o : allResults.keySet()) {
				      if (allResults.get(o).contains(res)) {
				        word.add(o);
				      }
				}
				
				CBRCase _case = res.get_case();
				casesToReatin.add(_case);
				
				CaseSolution solution = (CaseSolution) _case.getSolution();
				set.add(solution.getAnswer());
			}
		}
		
		//Creating the answer string
		for(String res : set){
			flag=true;
			text+=res;
		}	
		
		//If there are any answer to the input text
		if(flag==true){
			
			//If there are more than one answer
			if(set.size()>1){
				
				//Displaying a text into the button panel
				JTextArea texto = new JTextArea();
				texto.setText("Sé más concreto :)");
				texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
				texto.setForeground(Color.WHITE);
				texto.setBackground(new java.awt.Color(171, 38, 60));
				
				buttonPanel.add(texto);
				
				//Creating an atomic boolean to check if it is the first checkbox we select
				AtomicBoolean first = new AtomicBoolean(true);
				
				//For each case in the set casesToRetain it is created a checkbox
				for(final CBRCase c : casesToReatin){
					
					JCheckBox cbx = new JCheckBox(((CaseDescription)c.getDescription()).getKeyWord1().toString());
					cbx.setBorder(null);
					cbx.setBackground(null);
					cbx.setForeground(Color.white);
					cbx.setFont(new Font("Segoe UI Semibold", 0, 14));
					buttonPanel.add(cbx);
					buttonPanel.repaint();
					buttonPanel.revalidate();
					//Adding an action listener to the checkboxes
					cbx.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							//If the checkbox is selected
							if(cbx.isSelected()){
								
								cbx.setEnabled(false);
								
								//If it is the first checkbox we select
								if(first.get()==true){
									
									//Setting the variable first to false
									first.set(false);
									
									//If the answer is a hyperlink
									if(((CaseSolution)c.getSolution()).getAnswer().toString().contains("http")){
										
										try {
											editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																						+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"
																						+ "<p><a href=\""+((CaseSolution)c.getSolution()).getAnswer().toString()+"\">"+((CaseSolution)c.getSolution()).getAnswer().toString()+"</a></p>" 
																						+ "</b></html>", 0, 0, null);
										} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
										
										//Always show the last update
										topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
									
									//If it is not a hyperlink
									}else{
										
										try {
											editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																						+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"
																						+ "<p>"+((CaseSolution)c.getSolution()).getAnswer().toString()+"</p>" 
																						+ "</b></html>", 0, 0, null);
										} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
										
										//Always show the last update
										topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
									}
									
									db.aumentarNumBusquedas(cbx.getText(), ((CaseSolution)c.getSolution()).getAnswer().toString());
								
								//If it is not the first checkbox we select
								}else{
									
									//If the answer is a hyperlink
									if(((CaseSolution)c.getSolution()).getAnswer().toString().contains("http")){
										
										try {
											editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																						+ "<p><a href=\""+((CaseSolution)c.getSolution()).getAnswer().toString()+"\">"+((CaseSolution)c.getSolution()).getAnswer().toString()+"</a>"
																						+ "</p></b></html>", 0, 0, null);
										} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
										
										//Always show the last update
										topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
									
									//If the answer is not a hyperlink
									}else{
										
										try {
											editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																						+ "<p>"+((CaseSolution)c.getSolution()).getAnswer().toString()
																						+ "</p></b></html>", 0, 0, null);
										} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
										
										//Always show the last update
										topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
										
									}
									
									//Increasing the number of searches of the word
									db.aumentarNumBusquedas(cbx.getText(), ((CaseSolution)c.getSolution()).getAnswer().toString());
								}
							}
						}
					});
				}
				
				//Displays the checkboxes in the button panel
				gui.repaint();
				gui.revalidate();
			
			//If there is only one answer
			}else{
				
				//If the answer is a hyperlink
				if(text.contains("http")){
					
					try {
						editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																	+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p><p><a href=\""+text+"\">"+text+"</a></p>"
																	+ "</b></html>", 0, 0, null);
					} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
					
					//Always show the last update
					topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
				
				//If the answer is not a hyperlink
				}else{
					
					try {
						editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																	+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p><p>"+text+"</p>"
																	+ "</b></html>", 0, 0, null);
					} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
					
					//Always show the last update
					topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
				}
				
				String palabra=word.iterator().next();
				//Increasing the number of searches of the word
				db.aumentarNumBusquedas(palabra,text);
				//Calling the method to ask the user about the utility of the answer
				printUtilidad(palabra);
			}
			
		//If there is not an answer
		}else{
			
			try {
				editorKit.insertHTML(doc, doc.getLength(), "<html><p><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
															+ "Lo siento, no tengo respuestas a tu pregunta :(" 
															+ "</b></p></html>", 0, 0, null);
			} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
			
			//Always show the last update
			topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
			
			//If there are at least one neighbor for the input text
			if(!s.isEmpty()){
				
				//Adding text to the button panel
				JTextArea texto = new JTextArea();
				texto.setText("Sugerencias de búsqueda");
				texto.setFont(new java.awt.Font("Segoe UI Semibold", Font.BOLD, 14));
				texto.setForeground(Color.WHITE);
				texto.setBackground(new java.awt.Color(171, 38, 60));
				
				buttonPanel.removeAll();
				buttonPanel.add(texto);
				
				//Creating and displaying in the button panel three recommendations of search
				for( int i=0; i<3;i++){
					final int tmp = i;
					JButton btnOp = new JButton();
					btnOp.setBackground(new Color(0, 109, 179));
					btnOp.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
					btnOp.setForeground(Color.white);
					btnOp.setText(((CaseDescription)s.get(tmp).get_case().getDescription()).getKeyWord1());
					
					//Adding an action listener for each button
					btnOp.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							
							//Printing in the text pane the word of the button
							try {
								editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:black\">"
																			+ "<p align=\"right\">"+btnOp.getText().substring(0, 1).toUpperCase() + btnOp.getText().substring(1)+"</p>" + "</b></html>", 0, 0, null);
							} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
							
							//Always show the last update
							topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
							
							//If the answer for the word in the button is a hyperlink
							if(((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString().contains("http")){
								
								try {
									editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																				+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"+"<p><a href=\""+((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString()+"\">"+((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString()+"</a></p>" 
																				+ "</b></html>", 0, 0, null);
								} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
								
								//Always show the last update
								topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
							
							//If the answer for the word in the button is not a hyperlink
							}else{
								try {
									editorKit.insertHTML(doc, doc.getLength(), "<html><b face=\"Segoe UI Semibold\" size=\"14\" style=\"color:rgb(0, 109, 179)\">"
																				+ "<p>"+sentenceList.get((int)(Math.random()*5+5))+"</p>"+"<p>"+((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString()+"</p>" 
																				+ "</b></html>", 0, 0, null);
								} catch (BadLocationException | IOException e1) {e1.printStackTrace();}
								
								//Always show the last update
								topTextPane.setCaretPosition(topTextPane.getDocument().getLength());
							}
							

							for (String o : allResults.keySet()) {
								if (allResults.get(o).contains(s.get(tmp))) {
									word.add(o);
								}		
							}
							
							//When there are no answers and the user push a suggestion button it is supposed
							//That the text input by the user is related with the button so we store all this 
							//information for making the system learn
							db.learnCases(word.iterator().next(), ((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString());
							
							//Repainting the button panel
							buttonPanel.removeAll();
							buttonPanel.repaint();
							buttonPanel.revalidate();
							
							//Calling the method to ask the user about the utility of the answer
							printUtilidad(btnOp.getText());
							//Increasing the number of searches of the word
							db.aumentarNumBusquedas(btnOp.getText(), ((CaseSolution)s.get(tmp).get_case().getSolution()).getAnswer().toString());
						}
					});
					//Adding the button to the button panel
					buttonPanel.add(btnOp);
				}
				//Repainting the button panel
				buttonPanel.repaint();
				buttonPanel.revalidate();
			}
		}
		
		//Setting down the connection calling postCycle
		try {
			cbrApp.postCycle();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method that displays in the button panel the information about the utility of the answer
	 */
	private static void printUtilidad(String palabra){
		
		JTextArea texto = new JTextArea();
		texto.setText("¿Le ha resultado útil esta información?");
		texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		texto.setForeground(Color.WHITE);
		texto.setBackground(new java.awt.Color(171, 38, 60));
		
		buttonPanel.removeAll();
		buttonPanel.add(texto);
		
		new StarBar(db,gui,buttonPanel,palabra);
		buttonPanel.repaint();
		buttonPanel.revalidate();
	}
}
