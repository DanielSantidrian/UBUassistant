package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import database.DatabaseConnection;

public class StarBar {

	private int rating;
	private MouseMotionListener mouseListener;


	public StarBar(DatabaseConnection db, JFrame gui, JPanel panel, LinkedHashSet<String> palabras, Component[] components) {
	
	    List<JButton> stars = new ArrayList<JButton>();
	    
	    for(int i=0; i<5;i++){
	    	
	    	stars.add(new JButton());
	    	
	    	final int tmp=i;
	
	    	stars.get(i).addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	
	            	panel.removeMouseMotionListener(mouseListener);
	            	
	            	setRating(tmp+1);
	            	
	            	
	            	for(int j=0;j<=tmp;j++){
	            		stars.get(j).setIcon(new ImageIcon(getClass().getResource("StarRatingSelected.png")));
	            	}
	            	for(int z=tmp+1;z<5;z++){
	            		stars.get(z).setIcon(new ImageIcon(getClass().getResource("StarRatingUNselected.png")));
	            	}
	            	
	            	if(components==null){
	            		
	            		JTextArea texto = new JTextArea();
		        		texto.setText("Valoración guardada correctamente. Su voto ha sido "+rating);
		        		texto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14));
		        		texto.setForeground(Color.WHITE);
		        		texto.setBackground(new java.awt.Color(171, 38, 60));
		        		panel.removeAll();
		        		panel.add(texto);
		        		
	            	}else{
	            		
	            		panel.removeAll();
	    				for(Component c : components){
	    					panel.add(c);
	    				}
	            	}
	            	
	        		panel.repaint();
	        		panel.revalidate();
	        		
	        		db.saveVote(palabras, getRating());
	            }
	        });
	    	
	    	stars.get(i).setIcon(new ImageIcon(getClass().getResource("StarRatingUNselected.png")));
	    	stars.get(i).setBorder(BorderFactory.createEmptyBorder());
	    	stars.get(i).setBackground(null);
	    	
	        panel.add(stars.get(i));
	        panel.setVisible(true);
	        stars.get(i).setVisible(true);
    	
    }
    
    mouseListener = new MouseMotionListener() {
		
		@Override
		public void mouseMoved(MouseEvent e) {
			
			for(int i=0;i<stars.size();i++){
				if(e.getX()>=stars.get(i).getLocation().getX()-1 && e.getY()<stars.get(i).getLocation().getY()+20 && e.getY()>stars.get(i).getLocation().getY()-6){
						stars.get(i).setIcon(new ImageIcon(getClass().getResource("StarRatingSelected.png")));
				}else{
						stars.get(i).setIcon(new ImageIcon(getClass().getResource("StarRatingUNselected.png")));
				}
			}
			
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			
		}
	};
    
	panel.addMouseMotionListener(mouseListener);

}

	public int getRating() {
		return rating;
	}
	
	private void setRating(int rating) {
		this.rating = rating;
	}
	
}