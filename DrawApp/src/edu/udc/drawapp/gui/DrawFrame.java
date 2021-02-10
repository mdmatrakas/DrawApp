package edu.udc.drawapp.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawFrame extends JFrame {

	private DrawPanel contentPane;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					DrawFrame frame = new DrawFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public DrawFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 500);
		contentPane = new DrawPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnGeometrias = new JMenu("Geometrias");
		menuBar.add(mnGeometrias);
		
		JMenuItem mntmPoint = new JMenuItem("Ponto");
		mntmPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(1);
			}
		});
		mnGeometrias.add(mntmPoint);
		
		JMenuItem mntmLine = new JMenuItem("Linha");
		mntmLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(2);
			}
		});
		mnGeometrias.add(mntmLine);
		
		JMenuItem mntmCircle = new JMenuItem("Circulo");
		mntmCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(3);
			}
		});
		mnGeometrias.add(mntmCircle);
		
		JMenuItem mntmRectangle = new JMenuItem("Retângulo");
		mntmRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(4);
			}
		});
		mnGeometrias.add(mntmRectangle);
		
		JMenuItem mntmTriangle = new JMenuItem("Triângulo");
		mntmTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(5);
			}
		});
		mnGeometrias.add(mntmTriangle);
		
	}

}
