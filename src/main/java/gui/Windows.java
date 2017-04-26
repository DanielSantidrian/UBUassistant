package gui;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SizeRequirements;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.Element;
import javax.swing.text.View;
import javax.swing.text.ViewFactory;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;
import javax.swing.text.html.InlineView;
import javax.swing.text.html.ParagraphView;

import cbr.CBRApplication;

public class Windows {
	
	private static JFrame gui;
	private static JTextPane topTextPane;
	private static JScrollPane scrollPane;
	private static JTextField textoEnviar;
	private static JPanel buttonPanel;
	
	private static HTMLDocument doc;
    private static HTMLEditorKit editorKit;
	
	
	/**
	 * Method that create and display the main window
	 */
	public static void createWindows(){
		
		//Creation of the main window
		setGui(new JFrame());
		getGui().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getGui().getContentPane().setBackground(new java.awt.Color(171, 38, 60));
		getGui().setMinimumSize(new Dimension(550,550));
		getGui().setLocation(80, 80);
		getGui().setTitle("UBUassistant v0.1");
		
		
		//Creation of the panel to show the answer of the question
		setTopTextPane(new JTextPane());
		getTopTextPane().setEditable(false);
		getTopTextPane().setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		getTopTextPane().setBorder(BorderFactory.createEmptyBorder(5, 15, 10, 10));
		
		//Always show the last update
		getTopTextPane().setCaretPosition(getTopTextPane().getDocument().getLength());

		scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(getTopTextPane());
		
		//Configuring the text pane to show html tags
	    getTopTextPane().setContentType( "text/html" );
	    getTopTextPane().setEditable(false);
	    
	    getTopTextPane().setEditorKit(new HTMLEditorKit(){ 
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
	    
	    setDoc((HTMLDocument)getTopTextPane().getDocument());
	    setEditorKit((HTMLEditorKit)getTopTextPane().getEditorKit());
	    
	    //Adding a HyperlinkListener to open the browser
	    getTopTextPane().addHyperlinkListener(new HyperlinkListener() {
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
				getTopTextPane().setCaretPosition(getTopTextPane().getDocument().getLength());
			}
	    });
	    
	    

		//Creation of a panel to make suggestions or rating answer
		setButtonPanel(new JPanel());
		getButtonPanel().setBackground(new java.awt.Color(171, 38, 60));
		getButtonPanel().setMinimumSize(new Dimension(474,60));

		//Creation of the field to ask the questions
		setTextoEnviar(new JTextField());
		getTextoEnviar().setBorder(null);
		getTextoEnviar().setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		getTextoEnviar().setVisible(true);
		
		//Creation of the button "Enviar"
		JButton btnEnviar = new JButton("Enviar");
		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getButtonPanel().removeAll();
				getButtonPanel().repaint();
				getButtonPanel().revalidate();
				
				CBRApplication.searchAnswer();
					
			}
		});
		btnEnviar.setBackground(new Color(0, 109, 179));
		btnEnviar.setBorder(null);
		btnEnviar.setForeground(Color.white);
		getGui().getRootPane().setDefaultButton(btnEnviar);
		
		//Configure layout
        
		GroupLayout layout = new GroupLayout(getGui().getContentPane());
        getGui().getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(getTextoEnviar(), javax.swing.GroupLayout.DEFAULT_SIZE, 395, Short.MAX_VALUE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(scrollPane)
                        .addComponent(getButtonPanel(), javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(6, 6, 6)
                    .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(getButtonPanel(), javax.swing.GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(getTextoEnviar(), javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                        .addComponent(btnEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addContainerGap())
            );
            
		//Setting the window visible
        
        getGui().setVisible(true);
	}


	public static JTextField getTextoEnviar() {
		return textoEnviar;
	}


	public static void setTextoEnviar(JTextField textoEnviar) {
		Windows.textoEnviar = textoEnviar;
	}


	public static HTMLEditorKit getEditorKit() {
		return editorKit;
	}


	public static void setEditorKit(HTMLEditorKit editorKit) {
		Windows.editorKit = editorKit;
	}


	public static HTMLDocument getDoc() {
		return doc;
	}


	public static void setDoc(HTMLDocument doc) {
		Windows.doc = doc;
	}


	public static JTextPane getTopTextPane() {
		return topTextPane;
	}


	public static void setTopTextPane(JTextPane topTextPane) {
		Windows.topTextPane = topTextPane;
	}


	public static JPanel getButtonPanel() {
		return buttonPanel;
	}


	public static void setButtonPanel(JPanel buttonPanel) {
		Windows.buttonPanel = buttonPanel;
	}


	public static JFrame getGui() {
		return gui;
	}


	public static void setGui(JFrame gui) {
		Windows.gui = gui;
	}

}
