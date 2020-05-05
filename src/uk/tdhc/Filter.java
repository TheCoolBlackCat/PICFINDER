package uk.tdhc;

import java.io.File;
import java.util.Arrays;

public class Filter {
    // List of default extensions
    static String[] extensions = {"jpg", "jpeg", "png", "gif", "bmp", "tiff"};

    /**
     * Constructor
     * Accepts no arguements
     */
    Filter() {}

    /**
     * Constructor
     * @param ext Array of alternative file extensions to filter by
     */
    Filter(String[] ext) {
        if (ext.length > 0)
            extensions = ext;
    }

    /**
     * matchExtension
     * @param file File
     * @return Whether the specified file has that extension
     */
    boolean matchExtension(File file) {
        String fileName = file.toString();
        int i = fileName.lastIndexOf('.'); // Index of last dot
        if (i > 0) {
            String fileExtension = fileName.substring(i + 1);
            return Arrays.asList(extensions).contains(fileExtension);
        }
        // System.err.println("Filter: Invalid File Name");
        return false;
    }

    /**
     * isDir
     * @param folder File or Folder to test
     * @return Whether the folder is a directory
     */
    boolean isDir(File folder) {
//        folder = folder.get();
        return folder.isDirectory();
    }

    /**
     * exists
     * @param file File to test
     * @return Whether the file exists
     */
    boolean exists(File file) {
        return file.exists();
    }

    static public String[] getExtensions() {
        return extensions;
    }
}
