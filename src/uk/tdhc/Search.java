package uk.tdhc;

import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Search {
    public File[] searchForFiles (File dir, Stage window) {
        System.out.println("Searching: " + dir);

        Filter filter = new Filter();
        String[] contents = dir.list();
        ArrayList<File> matched = new ArrayList<File>();

        if (contents != null) { //TODO: Check source of error (access rights?)
            for (String file: contents) {
                File f = new File(dir.getAbsolutePath(), file);
                if (filter.exists(f)) {
                    if (filter.isDir(f))
                        matched.addAll(Arrays.asList(searchForFiles(f, window)));
                    else if (filter.matchExtension(f))
                        matched.add(f);
                } else
                    System.err.println(f+" doesn't exist");
            }
        } else
            AlertBox.display("Error Accessing File(s)", "There has been an error accessing: "+dir.getAbsolutePath()+". \n Check you have the correct privileges (are you an admin?).", "Continue");
        return matched.toArray(new File[0]);
    }
}
