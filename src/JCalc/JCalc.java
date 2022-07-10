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
import java.util.Formatter;
/* 
 * Calculator using swing
 * coded with * by Hart Israel
 */

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
	private Font displayFont = new Font("Helvetica",Font.PLAIN, 38);
	
	private ArrayList<JButton> numButtons = new ArrayList<JButton>(); 
	private ArrayList<JButton> funcButtons = new ArrayList<JButton>(); 
	
	private boolean screenUpdate = false, format = false;
	private String screenContent = "";
	
	Image icon = Toolkit.getDefaultToolkit().getImage("icon2.png");
	
	double num1 = 0, num2 = 0, res = 0;
	String symbol , temp;
	
	JCalc(String title){
		setupFrame(title); 
	}
	
	public void setupFrame(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		screen.setFont(displayFont);
		screen.setText("0");
		screen.setHorizontalAlignment(JTextField.RIGHT);
		screen.setMargin(new Insets(10, 10, 10, 20));
		screen.setBackground(this.platinumGray);
		screen.setForeground(new Color(129, 128, 126));
		screen.setEditable(false);
		
		wrapPanel.add(screen);
		wrapPanel.add(inputPanel);
		this.add(wrapPanel); 
	}
	
	private void updateScreen() {
		if(screenUpdate) screen.setForeground(new Color(99, 98, 96));
		screen.setText(screenContent);
	}

	private void clearScreen() {
		screen.setText("");
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
			btn.addActionListener(this);
			btn.setBorder(empty);
			if(isNum(btnText)) {
				btn.setBackground( new Color(42,42,42));
				numButtons.add(btn);
			}
			else {
				btn.setBackground(numClr);
				funcButtons.add(btn);
			}
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
	
	private double evaluate(String num1, String sym, String num2) {
		
		double n = Double.parseDouble(num1); 
		double n2 = Double.parseDouble(num2); 
		double res = 0;
		
		switch(sym) {
		case "-":
			res = n - n2;
			break; 
		case "*":
			res = n * n2; 
			break; 
		case "/": 
			res = n / n2;
			format = true;
			break;
		case "+":
			res = n + n2; 
			break; 
		case "%":
			res = n % n2 ; 
			break;
			default:
		}
		
		return res;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		//get button that was clicked and display to screen 
		for(JButton btn : numButtons) {
			if(e.getSource() == btn) {
				screenContent += btn.getText();
				updateScreen();
			}
		}
		
		for(JButton btn : funcButtons) {
			if(e.getSource() == btn) {
				
				switch(btn.getText()) {
				case "C":
					screenContent = "";
					clearScreen();
					break;
				case "{":
					clearScreen();
					String temp = screenContent;
					screenContent = "";
					for(int i = 0; i < temp.length() - 1; i++) {
						screenContent += temp.charAt(i);
					}
					updateScreen();
					break; 
				case "+/-":
					clearScreen();
					temp = screenContent;
					screenContent = "-";
					screenContent += temp; 
					updateScreen();
					break;
				case ".":
					screenContent += btn.getText();
					updateScreen();
					break;
				case "+": 
					num1 = Double.parseDouble(screenContent);
					symbol = btn.getText();
					screenContent = "";
					clearScreen();
					break;
				case "-": 
					num1 = Double.parseDouble(screenContent);
					symbol = btn.getText();
					screenContent = "";
					clearScreen();
					break;
				case "*": 
					num1 = Double.parseDouble(screenContent);
					symbol = btn.getText();
					screenContent = "";
					clearScreen();
					break;
				case "/": 
					num1 = Double.parseDouble(screenContent);
					symbol = btn.getText();
					screenContent = "";
					clearScreen();
					break;
				case "%": 
					num1 = Double.parseDouble(screenContent);
					symbol = btn.getText();
					screenContent = "";
					clearScreen();
					break;
				case "=": 
					num2 = Double.parseDouble(screenContent);
					screenContent = "";
					res = evaluate(String.valueOf(num1), symbol, String.valueOf(num2));
					if(format == true) {
						Formatter formatter = new Formatter();
						formatter.format("%.4f", res);
						screenContent = formatter.toString();
					} else screenContent = String.valueOf(res);
					updateScreen();
					System.out.println("Thanks for trying this program,\n-Hart Israel.");
					break;
				}
				
			}
		}
	}

}
