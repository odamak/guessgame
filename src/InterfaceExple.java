
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;



	public class InterfaceExple extends JFrame implements ActionListener {
		
		int compteurEssais=6;
		int nbrEssais = 1 ;
		
		private JButton bouton = new JButton("Go !");
	   
		private JPanel panel1 = new JPanel();
		private JPanel panel2 = new JPanel();
		private JPanel panel3 = new JPanel();
		private JPanel panel4 = new JPanel();
		private JPanel panel5 = new JPanel();
		private JPanel panel6 = new JPanel();
		
		private JMenuBar menuBar = new JMenuBar();
		private JMenu menu1 = new JMenu("Fichier");
		private JMenu menu2 = new JMenu("Aide");
		
		private JTextField Textfield1 =new JTextField("          ???     ");
		private JFormattedTextField jtf = new JFormattedTextField(NumberFormat.getIntegerInstance());
		
		private JLabel label1 = new JLabel("The number to guess is behind this yellow box...") ;
		private JLabel label2 = new JLabel ("Enter a number between 1 and 100 ");
		private JLabel label3 = new JLabel ("Watch out! You have only "+compteurEssais+" trials left...Be strategic!");
		
		//  génération aléatoire du prix du lot
		Random r = new Random();
		int prix = r.nextInt(100) + 1;
		
		
		public InterfaceExple(){
			
			this.setTitle("GuessGame");
			this.setSize(600,300);
			this.setLocationRelativeTo(null);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
			
			this.setJMenuBar(menuBar);
			this.menuBar.add(menu1);
		    this.menuBar.add(menu2);
		    
		    // le conteneur principal
		    JPanel content = new JPanel ();
		    content.setPreferredSize(new Dimension(300,200));
		    content.setBackground(Color.WHITE);
		    content.setLayout(new GridBagLayout()); 
		    
		    GridBagConstraints gbc = new GridBagConstraints();
		    
		    gbc.gridx=0;
		    gbc.gridy=0;
		    gbc.gridheight=1;
		    gbc.gridwidth=1;
	       	
		    // le premier panel contient le texte de l'introduction  
		    panel1.setBackground(Color.white);
		    panel1.setPreferredSize(new Dimension(300,30));
			panel1.add(label1);
			content.add(panel1,gbc);
			
			gbc.gridx=2;
			
			// le deuxième panel contient la case qui cache le nbre à deviner
			Textfield1.setBackground(Color.YELLOW);
			Textfield1.setPreferredSize(new Dimension(100,20));
			Textfield1.setEditable(false);
			panel2.setBackground(Color.white);
			panel2.setPreferredSize(new Dimension(100,30));
			panel2.add(Textfield1);
			content.add(panel2,gbc);
			
			gbc.gridwidth = GridBagConstraints.REMAINDER ;
			gbc.gridx=0;
			gbc.gridy=1;
			gbc.gridheight=1;
		    gbc.gridwidth=2;
		    
			// le troisième panel contient le 2ème texte
		    panel3.setPreferredSize(new Dimension(300,30));
		    panel3.setBackground(Color.white);
			panel3.add(label2);
			content.add(panel3,gbc);
			
			gbc.gridx=2;
	
			// le 4ème panel contient la case où on va placer les nombres 
			panel4.setBackground(Color.white);
			jtf.setPreferredSize(new Dimension(40,20));
			panel4.add(jtf);
			panel4.setPreferredSize(new Dimension(50,30));
			content.add(panel4,gbc);
			
			gbc.gridwidth = GridBagConstraints.REMAINDER ;
			gbc.gridx=0;
			gbc.gridy=2;
			gbc.gridheight=2;
		    gbc.gridwidth=3;
		    
			// le 5ème panel contient le bouton GO
		    panel5.setPreferredSize(new Dimension(100,50));
		    panel5.setBackground(Color.white);
			panel5.add(bouton);
			bouton.addActionListener(this);
			content.add(panel5,gbc);
			
			gbc.gridwidth = GridBagConstraints.REMAINDER ;
			gbc.gridx=0;
			gbc.gridy=4;
			gbc.gridheight=1;
		    gbc.gridwidth=4;
			
			
			// le 6ème panel 
		    panel6.setPreferredSize(new Dimension(300,30));
		    panel6.setBackground(Color.white);
		    label3.setForeground(Color.red);
		    panel6.add(label3);
			gbc.fill = GridBagConstraints.HORIZONTAL;
			content.add(panel6,gbc);
			
			
			this.setContentPane(content);
			this.setVisible(true);
			
			}


		public void actionPerformed(ActionEvent arg0) {
				if (compteurEssais ==6)
					System.out.println(prix);
				compteurEssais -- ;
				//System.out.println(compteur);
				String s = jtf.getText();
				
				try {
					int nbr =Integer.parseInt(s);
					
					if (nbr<0) throw new ExceptionNbrNegatif(nbr);
					if (nbr>100 || nbr<1) throw new ExceptionNbrLimite (nbr);

				if (compteurEssais>0){
					if (nbr>prix){
						
						label3.setText("Too big! You have only "+compteurEssais+" trials left..." );
						label3.setForeground(Color.orange);
					}
					else if (nbr<prix){
						label3.setText("Too small! You have only "+compteurEssais+" trials left..." );
						label3.setForeground(Color.blue);
					}
						
					else{
						label3.setText("Congratulation! You did it in "+nbrEssais+" trials." );
						label3.setForeground(Color.green);
						Textfield1.setText("             "+prix);
					}
						
				}
				
				else {
					label3.setText("You cannot play again ! You lost..." );
					label3.setForeground(Color.red);
					Textfield1.setText("              "+prix);
					jtf.setEditable(false);//blocage de la saisie de jtf
					
				}	
				
				nbrEssais ++;

				}

		catch (NumberFormatException exc){
			JOptionPane.showMessageDialog (this ," Type an integer !!!", "Watch out !!!", JOptionPane.WARNING_MESSAGE );
		}	
		catch (ExceptionNbrNegatif exc){	
			JOptionPane.showMessageDialog (this ," Type a positive number !!!", "Watch out !", JOptionPane.WARNING_MESSAGE );	
		}
		catch (ExceptionNbrLimite exc){
			JOptionPane.showMessageDialog (this ," Enter a number between 1 and 100 !!!", "Watch out !!!", JOptionPane.WARNING_MESSAGE );	
				}
			
	
		}
	//Définition Exception nombre négatif
		public class ExceptionNbrNegatif extends Exception{
			int val ;
			ExceptionNbrNegatif(int val) {
				this.val = val;
			}	
	}

    //Définition Exception nombre limite	    
		public class ExceptionNbrLimite extends Exception{
			int val ;
		    public ExceptionNbrLimite(int val) {
		    	this.val = val;
			}
		}
		
	}
		
		
	
	
		
	
		
	
	

