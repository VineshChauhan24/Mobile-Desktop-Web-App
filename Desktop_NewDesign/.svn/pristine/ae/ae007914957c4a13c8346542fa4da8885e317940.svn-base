/**
 * author Ramu B
 */
package comcast.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import comcast.custom.CustomFun;

public class PropertyFileReader {

	/**
	 * Properties.
	 */
	public static Properties ObjRepoProp;
	public static Properties TextProp;
	public static String rootDir = CustomFun.getRootDir();
	
	/**
	 * Load Property File.
	 * 
	 **/
	public static void loadProprtyFile() {

		ObjRepoProp = new Properties();
		TextProp = new Properties();
		
		try {

			System.out.println("Root Directory = " + rootDir);

			// Reading/loading the ObjectRepository/Text property files.

			if (rootDir.contains("WatchableWebClientNewUI")) {

				ObjRepoProp.load(new FileInputStream(rootDir
						+ "/resources/ObjRepository.properties"));

				TextProp.load(new FileInputStream(rootDir
						+ "/resources/Text.properties"));

			}

			else {
				ObjRepoProp
						.load(new FileInputStream(
								rootDir
										+ "/WatchableWebClientNewUI/resources/ObjRepository.properties"));
				TextProp.load(new FileInputStream(rootDir
						+ "/WatchableWebClientNewUI/resources/Text.properties"));

			}
				
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
