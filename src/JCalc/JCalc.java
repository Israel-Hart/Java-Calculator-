package JCalc;

import javax.swing.JFrame; 
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
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
	private Font dispalyFont = new Font("Helvetica",Font.PLAIN, 42);
	private boolean screenUpdate = false;
	private String screenContent = "";
	
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
		//setup frame window
		wrapPanel = new JPanel(); 
		wrapPanel.setBounds(0,0, 340, 500);
		wrapPanel.setBackground(this.platinumGray);
		wrapPanel.setLayout(null);
		
		//add buttons 
		inputPanel = new JPanel(); 
		inputPanel.setBounds(0,125, 340, 375);
		inputPanel.setBackground(this.matteBlack);
		inputPanel.setLayout(new GridLayout(5,4,2,2));
		generateButtons();
		
		//add display 
		screen = new JTextField(); 
		screen.setBounds(0,0,340, 120);
		screen.setFont(dispalyFont);
		screen.setText("0");
		screen.setHorizontalAlignment(JTextField.RIGHT);
		screen.setMargin(new Insets(10, 10, 10, 20));
		screen.setBackground(this.platinumGray);
		screen.setForeground(new Color(129, 128, 126));
		screenUpdate = true;
		screen.setEditable(false);
		
		wrapPanel.add(screen);
		wrapPanel.add(inputPanel);
		this.add(wrapPanel); 
	}
	
	private void updateScreen() {
		if(screenUpdate) screen.setForeground(new Color(99, 98, 96));
		screen.setText(screenContent);
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
			if(isNum(btnText)) btn.setBackground( new Color(42,42,42));
			else btn.setBackground(numClr);
			if(btn == btns.get(0)) btn.setForeground(new Color(242, 140, 40));
			else btn.setForeground(Color.white);
			btn.setFont(btnFont);
			btn.setFocusable(false);
			inputPanel.add(btn); 
		}
	}
	
	private boolean isNum(String num) {
		boolean res = true;
		try {
	        double d = Double.parseDouble(num);
	    } catch (NumberFormatException nfe) { 
	        res = false;
	    }
		return res; 
	}
	
	private int evaluate(String num1, String sym, String num2) {
		int n = Integer.parseInt(num1);
		int n2 = Integer.parseInt(num2);
		int res = 0; 
		
		switch(sym) {
		case "-":
			res = n - n2;
			break; 
		case "*":
			res = n * n2; 
			break; 
		case "/": 
			res = (int) (n / n2); 
			break;
		case "+":
			res = n + n2; 
			break; 
		case "%":
			res = (int) (n % n2); 
			break;
			default:
		}
		
		return res;
	}
	
	boolean negative = false;
	ArrayList<Integer> set1 = new ArrayList<Integer>();
	ArrayList<Integer> set2 = new ArrayList<Integer>();
	@Override
	public void actionPerformed(ActionEvent e) { 
		 String input = null; 
		 JButton btn = (JButton) e.getSource();
		 input = btn.getText();
		 
		 String symbol = null;
		
		 boolean brk = false; //when to clean display
		 boolean clr = false;
		 
		 //pick what to display 
		 if(isNum(input)) {
			 //number operation
			 screenContent += input;
			 updateScreen();
			 if(brk == true) set2.add(Integer.parseInt(input));
			 else set1.add(Integer.parseInt(input));
		 }else {
			 //symbol operation 
			 if(input.equals("C")) {
				 for(int i = 0; i < set1.size(); i++) { set1.remove(i);}
				 for(int i = 0; i < set2.size(); i++) { set2.remove(i);}
				 screenContent = "";
				 updateScreen();
			 }
			 else if(input.equals("+/-")) {
				 if(!negative) {
						// negative
					     negative = true;
						 String temp = "-";
						 temp += screenContent; 
						 screenContent = temp;
						 updateScreen();
					}else {}
			 }
			 else if(input.equals("{")) {
				 
			 }
			 else if(input.equals("=")) {
				 String n1,n2,sym;
				 String res = "";
				 int eq;
				 n1 = "";
				 n2 = ""; 
				 
				 for(int num : set1) { n1 += Integer.toString(num); }
				 for(int num : set2) { n2 += Integer.toString(num); }
				 
				 System.out.println(n1); 
				 System.out.println(n2);
//				 eq = evaluate(n1,symbol,n2);
				 screenContent = res;
				 updateScreen();
				 
				 for(int i = 0; i < set1.size(); i++) { set1.remove(i);}
				 for(int i = 0; i < set2.size(); i++) { set2.remove(i);}
				 
				 System.out.println(set1.size()); 
				 System.out.println(set2.size()); 
				 symbol = "";
				 
			 }else {
				 symbol = input;
				 negative = false;
				 screenContent = "";
				 brk = true;
				 updateScreen();
			 }
		 }
	}
}
