package edu.udc.drawapp.gui;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.udc.drawapp.model.*;
import edu.udc.drawapp.persistence.BinaryShapeFile;
import edu.udc.drawapp.persistence.SerialShapeFile;
import edu.udc.drawapp.persistence.ShapeFile;
import edu.udc.drawapp.persistence.TextShapeFile;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;
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
		
		JMenu mnArquivos = new JMenu("Arquivos");
		menuBar.add(mnArquivos);
		JMenu mnGeometrias = new JMenu("Geometrias");
		menuBar.add(mnGeometrias);

		
		JMenuItem mntmPoint = new JMenuItem("Ponto");
		mntmPoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(new Point(-1, -1));
			}
		});
		mnGeometrias.add(mntmPoint);
		
		JMenuItem mntmLine = new JMenuItem("Linha");
		mntmLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(new Line(new Point(-1, -1), new Point(-1, -1)));
			}
		});
		mnGeometrias.add(mntmLine);
		
		JMenuItem mntmCircle = new JMenuItem("Circulo");
		mntmCircle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(new Circle(new Point(-1, -1), 0));
			}
		});
		mnGeometrias.add(mntmCircle);
		
		JMenuItem mntmRectangle = new JMenuItem("Retângulo");
		mntmRectangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(new Quadrilateral(new Point(-1, -1), new Point(-1, -1), 
						new Point(-1, -1), new Point(-1, -1)));
			}
		});
		mnGeometrias.add(mntmRectangle);
		
		JMenuItem mntmTriangle = new JMenuItem("Triângulo");
		mntmTriangle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.desenharTipo(new Triangle(new Point(-1, -1), new Point(-1, -1), 
						new Point(-1, -1)));
			}
		});
		mnGeometrias.add(mntmTriangle);
		
		
		JMenuItem mntmNew = new JMenuItem("Novo");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.novoArquivo();
			}
		});
		mnArquivos.add(mntmNew);
		
		JMenuItem mntmAbrir = new JMenuItem("Abrir");
		mntmAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = chooseFile(false);
				if(f == null)
					return;
				ShapeFile shapeFile = chooseFileType(f);
				contentPane.lerArquivo(shapeFile);
			}
		});
		mnArquivos.add(mntmAbrir);
		
		JMenuItem mntmSalvar = new JMenuItem("Salvar");
		mntmSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				File f = chooseFile(true);
				if(f == null)
					return;
				ShapeFile shapeFile = chooseFileType(f);
				contentPane.salvarArquivo(shapeFile);
			}
		});
		mnArquivos.add(mntmSalvar);
	}
	
	private File chooseFile(boolean gravar) {
		JFileChooser fc = new JFileChooser();
		fc.setCurrentDirectory(new File(System.getProperty("user.home")));
		
		FileNameExtensionFilter textFilterS = new FileNameExtensionFilter("Serial file", "ser");
		fc.addChoosableFileFilter(textFilterS);
		FileNameExtensionFilter textFilterT = new FileNameExtensionFilter("Text file", "txt");
		fc.addChoosableFileFilter(textFilterT);
		FileNameExtensionFilter textFilterB = new FileNameExtensionFilter("Binary file", "bin");
		fc.addChoosableFileFilter(textFilterB);
		
		fc.setFileFilter(textFilterT);
		
		int result = gravar ? fc.showSaveDialog(null) : fc.showOpenDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {			
			return fc.getSelectedFile();
		}
		return null;
	}
	
	private ShapeFile chooseFileType(File f) {
		ShapeFile file = null;
		
		String name = f.getName();
		String ext = name.substring(name.lastIndexOf('.') + 1);
		
		if(ext.compareTo("ser") == 0) 
			file = new SerialShapeFile(f);
		if(ext.compareTo("txt") == 0) 
			file = new TextShapeFile(f);
		if(ext.compareTo("bin") == 0) 
			file = new BinaryShapeFile(f);	
		
		return file;
	}
	
}
