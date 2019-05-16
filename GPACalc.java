//Mingxuan Cai
//mc6gb
//Resource: 
//JTable:https://docs.oracle.com/javase/tutorial/uiswing/components/table.html
//JScrollPane: https://docs.oracle.com/javase/7/docs/api/javax/swing/JScrollPane.html
//GridBagLayout:https://docs.oracle.com/javase/tutorial/uiswing/layout/gridbag.html
//FlowLayout:https://docs.oracle.com/javase/tutorial/uiswing/layout/flow.html
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class GPACalc extends JFrame{
	/**
	 * Create JLabels, JTextArea, and JButton
	 */
	JLabel creditHours;
	JLabel grade;
	JLabel name;
	JLabel cgpa;
	JLabel cgpa1;
	JLabel tgpa;
	JLabel rgpa1;
	JLabel rgpa;
	JLabel comment;
	JLabel comment1;

	JFrame f;
	JPanel panel1;
	JPanel panel2;
	JPanel panel3;
	JPanel panel4;
	JPanel panel5;
	JTable enteredCourses;
	
	JTextArea text1;
	JTextArea text2;
	JTextArea text3;
	JTextArea text4;
	
	JButton submit;
	JButton add;
	JButton calculate;
	JButton remove;
	JButton removeAll;
	JButton quickAdd;

	/**
	 * lists to convert letter grades to doubles
	 */
	String[] letters= {"A+","A","A-","B+","B","B-","C+","C","C-","D+","D","D-","F-"};
	double[] values= {4.0,4.0,3.7,3.3,3.0,2.7,2.3,2.0,1.7,1.3,1.0,0.7,0};
	ArrayList<String> letterGrades=new ArrayList<>(Arrays.asList(letters));	
	
	/**
	 * Variables for calculate method
	 */
	int credithours=0;
	int futurecredits=0;
	
	public GPACalc()
	{
		/**
		 * Setting up the JFrame
		 */
		f = new JFrame("GPA Calculator");
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setSize(650, 600);
		f.setLayout(new BorderLayout());
		
		/**
		 * Setting up the JPanel
		 */
		panel1=new JPanel();
		panel1.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		panel2=new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS ));
		panel3=new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel4=new JPanel();
		panel4.setLayout(new GridBagLayout());
		panel5=new JPanel();
		panel5.setLayout(new GridBagLayout());

		/**
		 * Define JTable
		 */
		String[] headings = {"Name(Optional)","Grade(Optional)","Credit"};
		DefaultTableModel model = new DefaultTableModel(0, headings.length);
		model.setColumnIdentifiers(headings);
		enteredCourses = new JTable(model);
		enteredCourses.setRowHeight(30);
		enteredCourses.getTableHeader().setReorderingAllowed(false); //prevent users from reordering columns
		
		/**
		 * Setting up the JScroll
		 */

	    JScrollPane jpane = new JScrollPane(enteredCourses);
	    panel1.add(jpane);

	    /**
		 * Define each JLabel
		 */
		creditHours = new JLabel("Credit Hours: ");
		grade = new JLabel("Grade(optional): ");
		name = new JLabel("Name(optional): ");
		cgpa1 = new JLabel("Current GPA: ");
		cgpa = new JLabel();
		tgpa = new JLabel("Target GPA: ");
		rgpa1 = new JLabel("Required GPA: ");
		rgpa = new JLabel();
		comment = new JLabel();
		comment1 = new JLabel("Comment: ");
		text1 = new JTextArea();
		text2 = new JTextArea();
		text3 = new JTextArea();
		text4 = new JTextArea();
		add = new JButton("Add");
		calculate = new JButton("Calculate");
		submit = new JButton("Enter");
		remove = new JButton("Remove");
		removeAll = new JButton("Remove All Courses");
		quickAdd = new JButton("Add 15 credit hours");
		
		/**
		 * Set prefered size for textarea
		 */
		text1.setPreferredSize(new Dimension(80, 20));
		text2.setPreferredSize(new Dimension(50, 20));
		text3.setPreferredSize(new Dimension(50, 20));
		text4.setPreferredSize(new Dimension(50, 20));

		/**
		 * Add buttons to panel2
		 */
		panel2.add(add);
		panel2.add(remove);
		panel2.add(removeAll);
		panel2.add(quickAdd);
		
		/**
		 * Add labels and text box to panel3
		 */

		panel3.add(name,c);
		panel3.add(text1,c);
		panel3.add(grade,c);
		panel3.add(text2,c);
		panel3.add(creditHours,c);
		panel3.add(text3,c);
		panel3.add(submit,c);
		
		/**
		 * Add target GPA and calculate button to panel4
		 */
		
		c.gridx = 0;
		c.gridy = 0;
		c.anchor=GridBagConstraints.LINE_START;
		panel4.add(tgpa,c);
		c.gridx = 1;
		panel4.add(text4,c);
		c.gridx = 2;
		panel4.add(calculate,c);
		c.gridx = 0;
		c.gridy = 1;
		
		/**
		 * Add the rest Label to panel5
		 */
		c.gridx = 0;
		c.gridy = 0;
		panel5.add(cgpa1,c);
		c.gridx = 1;
		panel5.add(cgpa,c);
		c.gridx = 0;
		c.gridy = 2;
		panel5.add(rgpa1,c);
		c.gridx = 1;
		c.gridy = 2;
		panel5.add(rgpa,c);
		c.gridx = 0;
		c.gridy = 3;
		panel5.add(comment1,c);
		c.gridx = 1;
		c.gridy = 3;
		panel5.add(comment,c);
		
		/**
		 * add panel3 to the frame, and panel1, panel2 and panel 4 to panel3
		 */
		f.add(panel3);
		c.gridx = 0;
		c.gridy = 2;
		panel3.add(panel1,c);
		c.gridx = 2;
		c.gridy = 2;
		panel3.add(panel2,c);
		c.gridx = 0;
		c.gridy = 3;
		panel3.add(panel4,c);
		c.gridx = 0;
		c.gridy = 4;
		panel3.add(panel5,c);
		f.setVisible(true);
		
		/**
		 * action listeners
		 */
		//Listener for entering the data from the textbox
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//get text from each textarea
				String name = text1.getText(); 
				String grade = text2.getText();
				String credit = text3.getText();
				//form a row of data
				String[] row = {name,grade,credit};
				//Create a list of array that contains valid course credit(from 1-4)
				ArrayList<Integer> array = new ArrayList<Integer>();
				for (int i = 1;i<5;i++) {
					array.add(i);
				}
				//Use try&catch and if&else to eliminate invalid credit
				try {
					if (!array.contains(Integer.parseInt(credit))) {
						comment.setText("Please enter a valid credit!");
					}
					else {
						DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
						model.addRow(row);
					}
				}
				catch (NumberFormatException event){
					comment.setText("Please enter a valid credit!");
				}

				//Empty the textarea each time the user click the button
				text1.setText("");
				text2.setText("");
				text3.setText("");
			}
		});
		
		//Listener for remove the selected row 
		remove.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
				int[] rows = enteredCourses.getSelectedRows();
				//identify and remove the selected row using a for loop
				for(int i = 0; i<rows.length;i++) {
					model.removeRow(rows[i]-i);
				}
			}
		});
		
		//Listener for remove every row
		removeAll.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
				int numRows = model.getRowCount();
				//remove every row using a for loop
				for (int i = numRows - 1; i >= 0; i--) {
				    model.removeRow(i);
				}
			}
		});
		
		
		//Listener for add an empty course
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
				//create an empty course with 3 credit and add it
				String[] row = {"","","3"};
				model.addRow(row);
			}
		});
		
		//Listener for add 15 empty course
		quickAdd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
				//add 15 empty row using a for loop
				for (int i=0;i<15;i++) {
					model.addRow(new Object[]{"", "", ""});
				}
			}
		});
		
		//Listener for the button calculate
		calculate.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				//Save the information in cells
				TableCellEditor editor=enteredCourses.getCellEditor();
				if(editor!=null) {
					editor.stopCellEditing();
				}
				//Get the data from the table
				double targetGPA=0;
				DefaultTableModel model = (DefaultTableModel) enteredCourses.getModel();
				int numRow = model.getRowCount();
				ArrayList<Object[]> data=new ArrayList<Object[]>();
				for (int i = 0 ; i < numRow ; i++) {
					Object[] row= {model.getValueAt(i, 0),model.getValueAt(i, 1),model.getValueAt(i, 2)};
					data.add(row);
				}
				//Check if the data is empty. If it is, it will notify the user to input the credit
				if (!empty(data)) {
					double currentGPA=calculate(data);
					cgpa.setText(String.valueOf(currentGPA));
					//getting the user input of target GPA
					if(!text4.getText().isEmpty()) {
						targetGPA=Double.parseDouble(text4.getText());
						//when current gpa meets the target
						if (currentGPA>=targetGPA) {
							comment.setText("Great job!");
						}
						//when current gpa does not meet with the target
						else {
							//calculation for the required gpa
							double requiredGPA=((targetGPA*(credithours+futurecredits)-currentGPA*credithours))/futurecredits;
							rgpa.setText(String.valueOf(requiredGPA));
							//when required gpa is smaller than 2
							if (requiredGPA<=2.0) {
								comment.setText("You can take fewer classes to reach your goal.");
							//when required gpa is in the normal range(between 2 and 4)
							}
							if (requiredGPA>2.0&requiredGPA<4.0) {
								comment.setText("Work harder!!!");	
							}
							//when required gpa is larger than 4
							if(requiredGPA>4.0){
								comment.setText("Try to add more classes and then recalculate.");	
							}
						}
					}
				}
			}
		});
	}
	
	/*
	 * Method for checking if the data is empty
	 */
	public boolean empty(ArrayList<Object[]> data) {
		for (Object[] i: data) {
			//Check if the credit is empty
			if(i[2].toString().trim().equals("")) {
				System.out.println("Please fill in the credit!");
				return true;
			}
		}
		return false;
	}

	/*
	 * Method for calculating current GPA
	 */
	public double calculate(ArrayList<Object[]> data) {
		double currentGPA=0.0;
		credithours=0;
		futurecredits=0;
		//Loop through the ArrayList to add on to the current credit hours and GPA
		for (int i=0;i<data.size();i++) {
			//Check if the letter grade is empty
			if (!data.get(i)[1].toString().isEmpty()){
				//Add onto currentGPA and credithours if the grade is present
				currentGPA+=Double.parseDouble(data.get(i)[2].toString())*values[letterGrades.indexOf(data.get(i)[1])];
				credithours+=Double.parseDouble(data.get(i)[2].toString());
			}
			else {
				//Add onto futurecredits if the grade is missing
				futurecredits+=Double.parseDouble(data.get(i)[2].toString());
			}
		}
		//return the current GPA
		return currentGPA/credithours;
	}
	
	public static void main(String[] args) {
		new GPACalc();
	}

}
