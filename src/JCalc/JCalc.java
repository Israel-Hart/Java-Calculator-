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
	
	ArrayList<JButton> digits = new ArrayList<JButton>(); 
	ArrayList<JButton> functions = new ArrayList<JButton>(); 
	String[] funcStr = {"-","+","x","/","=","clr","."};
	JPanel wrapPanel, inputPanel; 
	JTextField screen;
	Image icon = Toolkit.getDefaultToolkit().getImage("icon2.png");
	
	JCalc(String title){
		setupFrame(title); 
	}
	
	public void setupFrame(String title) {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null); 
		this.getContentPane().setBackground(Color.black);
		this.setSize(400,600);
		this.setResizable(false);
		this.setTitle(title);
		this.setIconImage(icon); 
		this.setVisible(true); 
	}
	
	public void addButtons() {
		generateButtons(); 
	}
	
	public void addPanels() {
		wrapPanel = new JPanel(); 
		setBounds(0,0, this.getWidth(), this.getHeight());
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
