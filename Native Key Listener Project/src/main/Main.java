package main;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Main implements NativeKeyListener {

	public static void main(String[] args) {
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
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent e) {
		System.out.println("Pressed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
		
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) {
		System.out.println("Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
