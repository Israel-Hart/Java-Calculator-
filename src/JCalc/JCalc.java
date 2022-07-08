package JCalc;

import javax.swing.JFrame; 
import javax.swing.JButton;
import javax.swing.JPanel; 
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;

import java.awt.Color; 
import java.awt.Font; 
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.Dimension;

public class JCalc extends JFrame implements ActionListener{
	
	private ArrayList<JButton> btns = new ArrayList<JButton>(); 

	private String[] funcs = {"C","%","+/-","/","7","8","9","*","4",
								"5","6","-","1","2","3","+",".","0","{","="};
	private JPanel wrapPanel, inputPanel; 
	private JTextField screen;
	private Color matteBlack = new Color(40, 40, 43);
	private Color platinumGray = new Color(229, 228, 226);
	private Color numClr = new Color(52,52,52);
	private Font btnFont = new Font("Sans-serif",Font.PLAIN, 24);
	
	Image icon = Toolkit.getDefaultToolkit().getImage("icon2.png");
	
	JCalc(String title){
		setupFrame(title); 
	}
	
	public void setupFrame(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JFrame temp = new JFrame();
//		temp.pack();
//		Insets insets = temp.getInsets();
//		temp = null;
//		this.setSize(new Dimension(insets.left + insets.right + 300,
//		             insets.top + insets.bottom + 500));
		this.getContentPane().setPreferredSize(new Dimension(340, 500));
	    this.pack();
		this.setLayout(null); 
//		this.setSize(400,600);
		this.setResizable(false);
		this.setTitle(title);
		this.setIconImage(icon); 
		addComps();
		
		
		this.setVisible(true); 
	}
	
	public void addComps() {
		wrapPanel = new JPanel(); 
		wrapPanel.setBounds(0,0, 340, 500);
		wrapPanel.setBackground(this.platinumGray);
		wrapPanel.setLayout(null);
		
		inputPanel = new JPanel(); 
		inputPanel.setBounds(0,125, 340, 375);
		inputPanel.setBackground(this.matteBlack);
		inputPanel.setLayout(new GridLayout(5,4,2,2));
		
		generateButtons();
		
		wrapPanel.add(inputPanel);
		this.add(wrapPanel); 
	}
	
	private void generateButtons() {
		//create buttons with function
		for(String func : funcs) {
			btns.add(new JButton(func)); 
		}
		/* add btns to inputPanel 
		*	and add action listeners 
		*/
		
		Border empty = BorderFactory.createEmptyBorder();
		String btnText = null;
		for (JButton btn : btns) {
			btnText = btn.getText();
			System.out.println(btnText);
			btn.addActionListener(this);
			btn.setBorder(empty);
			
		    try {
		        double d = Double.parseDouble(btnText);
		        btn.setBackground( new Color(42,42,42));
		    } catch (NumberFormatException nfe) {
		        btn.setBackground(numClr);
		    }
			
			if(btn == btns.get(0)) btn.setForeground(new Color(242, 140, 40));
			else btn.setForeground(Color.white);
			btn.setFont(btnFont);
			btn.setFocusable(false);
			inputPanel.add(btn); 
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		
	}
}
