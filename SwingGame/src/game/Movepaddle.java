package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;

public class Movepaddle extends JFrame {
	boolean t = true;
	
	JPanel gp ;
	JPanel gf;
	JPanel optionsPane;
	public Movepaddle() {

		initUI();
	}

	private void initUI() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		
		
		optionsPane = new Optionpane(new GridLayout(4, 1));
		JButton single = new JButton("Single Player Mode");

		single.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				// setContentPane(gp);
				gp = new Board();
				invalidate();
				setContentPane(gp);
				validate();
				gp.requestFocusInWindow();

			}
		});

		JButton multi = new JButton("Multi Player Mode");
		multi.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				// setContentPane(gp);
				
				invalidate();
				setContentPane(optionsPane);
				validate();
				optionsPane.requestFocusInWindow();

			}
		});
		
		JButton pla_4 = new JButton("4 Player Mode");
		pla_4.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				// setContentPane(gp);
				gf = new Board2();
				invalidate();
				setContentPane(gf);
				validate();
				gf.requestFocusInWindow();

			}
		});
		// panel.add(single);
		panel.setLayout(null);
		single.setBounds(250, 175, 200, 50);
		multi.setBounds(250,275,200, 50);
		pla_4.setBounds(250, 375, 200, 50);
		panel.add(multi);
		panel.add(pla_4);
		panel.add(single);

		add(panel);
		setSize(750, 700);
		setResizable(false);

		setTitle("Moving paddle");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				Movepaddle ex = new Movepaddle();
				ex.setVisible(true);
			}
		});
	}
	

}
