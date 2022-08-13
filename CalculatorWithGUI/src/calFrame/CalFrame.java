package calFrame;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calTree.CalTree;

public class CalFrame extends JFrame implements ActionListener{
	
	CalTree caltree;
	FormulaEditor editor;
	String formula;
	
	JPanel displayPanel;
	JPanel buttonPanel;
	
	JTextField display;
	
	JButton[] buttons; //0~9
	JButton btnDot;
	JButton btnPlus;
	JButton btnMinus;
	JButton btnProduct;
	JButton btnDivide;
	JButton btnOpenBucket;
	JButton btnCloseBucket;
	JButton btnEqual;
	JButton btnCE;
	
	public CalFrame(){
		formula = new String();
		caltree = new CalTree();
		editor = new FormulaEditor();
		
		this.setSize(900,500);
		this.setTitle("Calculator");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		
		ImageIcon imageIcon = new ImageIcon("calculator.png");
		Image image = imageIcon.getImage();
		this.setIconImage(image);
		
		displayPanel = new JPanel();
		buttonPanel = new JPanel();
		
		displayPanel.setLayout(new FlowLayout());
		buttonPanel.setLayout(new GridLayout(5,4,5,5));
		
		display = new JTextField(45);
		display.setHorizontalAlignment(JTextField.RIGHT);
		Font font = new Font("Serif", Font.BOLD, 25);
		display.setFont(font);
		display.setText(formula);
		displayPanel.add(display);
		
		Font fontBtn =  new Font("Serif", Font.BOLD, 25);
		
		buttons = new JButton[10];
		
		for(int i =0; i<buttons.length; i++) {
			buttons[i] = new JButton(""+i);
			buttons[i].setFont(fontBtn);
			buttons[i].addActionListener(this);
		}
		
		btnPlus = new JButton("+");
		btnPlus.setFont(fontBtn);
		btnPlus.addActionListener(this);
		
		btnMinus = new JButton("-");
		btnMinus.setFont(fontBtn);
		btnMinus.addActionListener(this);
		
		btnProduct = new JButton("*");
		btnProduct.setFont(fontBtn);
		btnProduct.addActionListener(this);
		
		btnDivide = new JButton("/");
		btnDivide.setFont(fontBtn);
		btnDivide.addActionListener(this);
		
		btnDot = new JButton(".");
		btnDot.setFont(fontBtn);
		btnDot.addActionListener(this);
		
		btnOpenBucket = new JButton("(");
		btnOpenBucket.setFont(fontBtn);
		btnOpenBucket.addActionListener(this);
		
		btnCloseBucket = new JButton(")");
		btnCloseBucket.setFont(fontBtn);
		btnCloseBucket.addActionListener(this);
		
		btnCE = new JButton("CE");
		btnCE.setFont(fontBtn);
		btnCE.addActionListener(this);
		
		btnEqual = new JButton("=");
		btnEqual.setFont(fontBtn);
		btnEqual.addActionListener(this);
		
		buttonPanel.add(buttons[1]);
		buttonPanel.add(buttons[2]);
		buttonPanel.add(buttons[3]);
		buttonPanel.add(btnPlus);
		
		buttonPanel.add(buttons[4]);
		buttonPanel.add(buttons[5]);
		buttonPanel.add(buttons[6]);
		buttonPanel.add(btnMinus);
		
		buttonPanel.add(buttons[7]);
		buttonPanel.add(buttons[8]);
		buttonPanel.add(buttons[9]);
		buttonPanel.add(btnProduct);
		
		buttonPanel.add(buttons[0]);
		buttonPanel.add(btnOpenBucket);
		buttonPanel.add(btnCloseBucket);
		buttonPanel.add(btnDivide);
		
		buttonPanel.add(btnDot);
		buttonPanel.add(btnCE);
		buttonPanel.add(btnEqual);
		
		this.add(displayPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnPlus)
			formula += "+";
		else if(e.getSource() == btnMinus)
			formula += "-";
		else if(e.getSource() == btnProduct)
			formula += "*";
		else if(e.getSource() == btnDivide)
			formula += "/";
		else if(e.getSource() == btnOpenBucket)
			formula += "(";
		else if(e.getSource() == btnCloseBucket)
			formula += ")";
		else if(e.getSource() == btnDot)
			formula += ".";
		else if(e.getSource() == buttons[0])
			formula += "0";
		else if(e.getSource() == buttons[1])
			formula += "1";
		else if(e.getSource() == buttons[2])
			formula += "2";
		else if(e.getSource() == buttons[3])
			formula += "3";
		else if(e.getSource() == buttons[4])
			formula += "4";
		else if(e.getSource() == buttons[5])
			formula += "5";
		else if(e.getSource() == buttons[6])
			formula += "6";
		else if(e.getSource() == buttons[7])
			formula += "7";
		else if(e.getSource() == buttons[8])
			formula += "8";
		else if(e.getSource() == buttons[9])
			formula += "9";
		else if(e.getSource() == btnDot)
			formula += ".";
		else if(e.getSource() == btnCE) {
			formula = "";
			caltree.clear();
		}
		else if(e.getSource() == btnEqual){
			//text edit needed(error catch, modify(for example : -1 -> (0-1)))
			if(!editor.check(formula)) {
				formula = "ERROR!";
			}else {
				formula = caltree.calculate(formula).toString();
				caltree.clear();
			}
		}
		
		display.setText(formula);
		
	}
	
}
