package UserManagement.Model;

import java.io.File;
import java.io.IOException;

public class StorageManager {
    private static final String BASE_DIRECTORY = "data";


    // Get a file
    public static File getFile(String filepath) {
        File file = new File(filepath);
        try {
            if (file.createNewFile()) {
                System.out.println("File created: " + filepath);
            }
        } catch (IOException e) {
            System.err.println("Failed to create file: " + filepath);
            e.printStackTrace();
        }
        return file;
    }

    public static String getBaseDirectory() {
        return BASE_DIRECTORY;
    }
}