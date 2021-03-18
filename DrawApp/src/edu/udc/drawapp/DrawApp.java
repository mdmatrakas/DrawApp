/**
 * 
 */
package edu.udc.drawapp;

import edu.udc.drawapp.controler.DrawDocument;
import edu.udc.drawapp.gui.DrawFrame;

/**
 * @author matrakas
 *
 */
public class DrawApp {
	private static DrawApp app;
	private DrawDocument document;
	private DrawFrame frame;
	
	// Empty constructor to guarantee initialization process calls to getApp()
	private DrawApp() { }
	
	public static DrawApp getApp() {
		if(app == null) {
			app = new DrawApp();
			app.initApp();
		}
		return app;
	}
	
	private void initApp() {
		try {
			document = new DrawDocument();
			frame = new DrawFrame();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public DrawDocument getDocument() {
		return document;
	}

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		getApp();
	}

}
