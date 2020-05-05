package uk.tdhc;

import java.io.File;
import java.nio.file.Files.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.*;
import static java.nio.file.StandardCopyOption.*;
import java.io.IOException;

public class Save {
    File location;

    Save (File path) {
        System.out.println("Saving to: "+path);

        if (path.exists()) {
            System.out.println("Directory exists");
        } else {
            if(path.mkdir())
                System.out.println("Created Directory: "+path);
            else
                System.err.println("Error creating output directory");
        }
        location = path;
    }

    private void copyFile(File source, File destination) {
        File newFile = new File(destination, source.getName());

        Path sourcePath = Paths.get(source.getAbsolutePath());
        Path destPath = Paths.get(newFile.getAbsolutePath());

        try {
            Files.copy(sourcePath, destPath, REPLACE_EXISTING);
        } catch (IOException e) {
            System.err.println("Error copying file: " + source);
            e.printStackTrace();
        }
    }

    public void copyMultipleFiles(File[] files) {
        for (File file: files) {
            System.out.println("Copying "+file);
            copyFile(file, location);
        }
        System.out.println("Finished copying files");
    }
}
