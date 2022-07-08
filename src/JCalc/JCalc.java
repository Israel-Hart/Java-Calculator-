package JCalc;
import javax.swing.JFrame; 
import javax.swing.JButton;
import javax.swing.JPanel; 
import javax.swing.JTextField; 
import java.awt.Color; 
import java.awt.Font; 
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.Image;
import java.awt.Toolkit;


public class JCalc extends JFrame implements ActionListener{
	
	private ArrayList<JButton> digits = new ArrayList<JButton>(); 
	private ArrayList<JButton> functions = new ArrayList<JButton>(); 
	private String[] funcStr = {"-","+","x","/","=","clr","."};
	private JPanel wrapPanel, inputPanel; 
	private JTextField screen;
	private Color blueGray = new Color(115, 147, 179);
	private Color platinumGray = new Color(229, 228, 226);
	
	Image icon = Toolkit.getDefaultToolkit().getImage("icon2.png");
	
	JCalc(String title){
		setupFrame(title); 
	}
	
	public void setupFrame(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null); 
		this.setSize(400,600);
		this.setResizable(false);
		this.setTitle(title);
		this.setIconImage(icon); 
		addPanels();
		
		this.setVisible(true); 
	}
	
	public void addButtons() {
		generateButtons(); 
		addPanels();
	}
	
	public void addPanels() {
		wrapPanel = new JPanel(); 
		wrapPanel.setBounds(0,0, 400, 600);
		wrapPanel.setBackground(this.blueGray);
		wrapPanel.setLayout(null);
		
		inputPanel = new JPanel(); 
		inputPanel.setBounds(0,(int)(wrapPanel.getHeight() * 0.2), 400, (int)(wrapPanel.getHeight() * 0.8));
		inputPanel.setBackground(this.platinumGray);
		inputPanel.setLayout(null);
		
		wrapPanel.add(inputPanel);
		this.add(wrapPanel); 
	}
	
	private void generateButtons() {
		for(int i =0; i < 9; i++) {
			digits.add(new JButton(Integer.toString(i)));
		}
	}
	
	private void createFunctionBtns() {
		for(String symbol : funcStr) {
			functions.add(new JButton(symbol));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) { 
		
	}
}
