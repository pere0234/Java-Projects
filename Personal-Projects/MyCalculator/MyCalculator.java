package calc;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*; 

/**
 * This class creates a simple GUI calculator
 * Operands are first typed in and an operation is then selected
 * Result is output in a text field
 * @author Daniel Pereira
 * @see JFrame
 * @see ActionListener
 */

public class MyCalculator extends JFrame implements ActionListener{
	/**
	 * Establishes an auto-generated serialVersioUID
	 */
	private static final long serialVersionUID = -3222909760051214963L;
	private JButton btn1,btn2,btn3,btn4,btn5;
	private JTextField val1,val2,result;

	//This method creates the layout for the calculator
	public void calcControls(){
		Container container = getContentPane();
		container.setLayout( new FlowLayout(FlowLayout.CENTER) );
		//Creates the input fields for the operands
		Label lbl1 = new Label("First Number");
		container.add(lbl1);
		val1 = new JTextField(10);
		container.add(val1);
		Label lbl2 = new Label("Second Number");
		container.add(lbl2);
		val2 = new JTextField(10);
		container.add(val2);
		
		//Creates the operator buttons
		btn1 = new JButton("+");
		container.add(btn1);
		btn1.addActionListener(this);
		btn2 = new JButton("-");
		container.add(btn2);
		btn2.addActionListener(this);
		btn3 = new JButton("x");
		container.add(btn3);
		btn3.addActionListener(this);
		btn4 = new JButton("/");
		container.add(btn4);
		btn4.addActionListener(this);
		btn5 = new JButton("Clear");
		container.add(btn5);
		btn5.addActionListener(this);
		Label lbl3 = new Label("Result");
		container.add(lbl3);
		result = new JTextField(10);
		container.add(result);
	}

	public MyCalculator(){
		//main calculator window
		super("Simple Calculator");
		//load the calculator layout
		calcControls();
		/*sets the size of the calculator window
		 *centers the window and makes it visible
		 */
		setSize(380,150);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	/*
	 * This method creates the actions for each event
	 * If a valid operation is entered the operation is carried out
	 * Else if there are empty fields, or invalid operations, an error message is given
	 * 
	 */
	public void actionPerformed(ActionEvent e) {
		double  num1,num2,output;
		String solution;
		if (e.getSource()==btn1) {
			if(val1.getText().equals("") || val2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Incomplete data field(s)",
						"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else{
				num1=Double.parseDouble(val1.getText());
				num2=Double.parseDouble(val2.getText());
				output=num1+num2;
				solution=String.valueOf(output);
				result.setText(solution);
			}          
		}
		if (e.getSource()==btn2) {
			if(val1.getText().equals("") || val2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Incomplete data field(s)",
						"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else{
				num1=Double.parseDouble(val1.getText());
				num2=Double.parseDouble(val2.getText());
				output=num1-num2;
				solution=String.valueOf(output);
				result.setText(solution);
			}          
		} 
		if (e.getSource()==btn3) {
			if(val1.getText().equals("") || val2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Incomplete data field(s)",
						"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else{
				num1=Double.parseDouble(val1.getText());
				num2=Double.parseDouble(val2.getText());
				output=num1*num2;
				solution=String.valueOf(output);
				result.setText(solution);
			}          
		}
		if (e.getSource()==btn4) {
			if(val1.getText().equals("") || val2.getText().equals("")){
				JOptionPane.showMessageDialog(null, "Incomplete data field(s)",
						"Error",JOptionPane.ERROR_MESSAGE); 
			}
			else{
				num1=Double.parseDouble(val1.getText());
				num2=Double.parseDouble(val2.getText());
				try{
					output=num1/num2;
					solution=String.valueOf(output);
					result.setText(solution);
				}
				catch(ArithmeticException ex){
					JOptionPane.showMessageDialog(null, "Cannot divide by 0",
							"Error",JOptionPane.ERROR_MESSAGE);
				}

			}          
		}
		//the "clear" button
		if (e.getSource()==btn5){
			val1.setText("");
			val2.setText("");
			result.setText("");
		}             
	}

	public static void main(String[] args) {
		JFrame.setDefaultLookAndFeelDecorated(true);
		MyCalculator calc = new MyCalculator();//Instantiates the class
		calc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//closes the window
	}
}