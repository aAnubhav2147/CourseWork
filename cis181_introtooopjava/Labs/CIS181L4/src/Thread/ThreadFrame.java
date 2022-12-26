package Thread;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class ThreadFrame implements ActionListener{
	JFrame frame;
	JPanel panel1,panel2,panel3,panel4,panel5;
	Container container;
	JTextField output,resultfield;
	JButton button;
	JLabel label1,label2,label3,label4;
	JProgressBar jprogressBar;
	public ThreadFrame(){
		frame = new JFrame("MultiThread GUI");
		frame.setBounds(400, 100, 730, 500);
		container=frame.getContentPane();
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter()
				{
					public void windowClosing(WindowEvent e) {
						System.exit(1);
					}
				});
		label1 = new JLabel("MultiThread Lab");
		label1.setForeground(Color.red);
		label1.setFont(new Font("Dialog",1,50));
		label2 = new JLabel("Computing Thread");
		label2.setFont(new Font("Dialog",1,30));
		label3 = new JLabel("Result");
		label3.setFont(new Font("Dialog",1,30));
		label4 = new JLabel("Progress");
		label4.setFont(new Font("Dialog",1,30));
		output = new JTextField();
		output.setPreferredSize(new Dimension(550,40));
		output.setFont(new Font("Dialog",1,20));
		resultfield = new JTextField();
		resultfield.setFont(new Font("Dialog",1,20));
		resultfield.setPreferredSize(new Dimension(550,40));
		panel1 = new JPanel();
		panel2 = new JPanel();
		panel3 = new JPanel();
		panel4 = new JPanel();
		panel5 = new JPanel();
		button = new JButton("Start");
		button.setFont(new Font("Dialog",1,30));
		button.setPreferredSize(new Dimension(200,60));
		button.addActionListener(this);
		jprogressBar = new JProgressBar(0,30);
		jprogressBar.setPreferredSize(new Dimension(550,20));
		panel1.add(label1);
		panel2.add(label2);
		panel2.add(output);
		panel3.add(label3);
		panel3.add(resultfield);
		panel4.add(label4);
		panel4.add(jprogressBar);
		panel5.add(button);
		container.setLayout(new GridLayout(5,1));
		container.add(panel1);
		container.add(panel2);
		container.add(panel3);
		container.add(panel4);
		container.add(panel5);
		frame.setVisible(true);
		//frame.show();
		
	}

	public void actionPerformed(ActionEvent e)
	{
		ComputeThread computethread = new ComputeThread(this);
		Thread thread1 = new Thread (computethread);
		ReturnThread thread2 = new ReturnThread(this);
		thread1.start();
		thread2.start();
				
	}
	public static void main(String[] args)
	{
		ThreadFrame testT = new ThreadFrame();
	}
}