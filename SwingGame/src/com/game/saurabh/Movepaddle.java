package com.game.saurabh;

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
	

	public Movepaddle() {

		initUI();
	}

	private void initUI() {

		JPanel panel = new JPanel();
		panel.setBackground(Color.BLACK);
		JPanel gp = new Board();
		JPanel optionsPane = new Optionpane(new GridLayout(4, 1));
		JButton single = new JButton("Single Player Mode");

		single.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mousePressed(java.awt.event.MouseEvent evt) {
				// setContentPane(gp);

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
		// panel.add(single);
		panel.setLayout(null);
		single.setBounds(100, 80, 200, 40);
		multi.setBounds(100, 140, 200, 40);
		panel.add(multi);
		panel.add(single);

		add(panel);
		setSize(400, 300);
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
