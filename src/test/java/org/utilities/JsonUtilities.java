package org.utilities;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This Is an Utility Class With JSON Text related Operations
 * @author Admin
 *
 */
public class JsonUtilities {


	/**
	 * This Method is to Read JSON String  from  given File and resturn as String
	 * @param path Path of JSON File Content
	 * @return String version of JSON Content
	 */
    public static String generateStringFromResource(String path) {
        String result;
        try {
            result = new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

}
