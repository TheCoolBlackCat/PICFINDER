package uk.tdhc;

import java.io.*;
import java.nio.file.Files;

import javafx.application.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean test = true;

    private Stage window;
    private File outputFolder;

    private File folder;
    Scene[] scenes = new Scene[3];

    public static void main(String[] args) {
        launch(args);

//        // Test source/destination
//        File file = new File("test");
//        File output = new File("test-out");
//
//        // Search for files
//        Search search = new Search();
//        File[] result = search.searchForFiles(file);
//
//        // Save files
//        Save save = new Save(output);
//        save.copyMultipleFiles(result);
    }

    @Override
    public void start(Stage stage) throws Exception {
        if (test)
            outputFolder = new File("test-out"); // TEST
        else
            outputFolder = new File(System.getProperty("user.dir"), "pics"); // PRODUCTION

        window = stage;
        window.setTitle("picFinder");

        // Save files
//        Save save = new Save(output);
//        save.copyMultipleFiles(result);

        window.setScene(scene1());
        window.setResizable(false);
        window.show();
    }

    private void chooseFolder () {
        // Select Directory
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("Where do you want to search for pictures?");
        File defaultDirectory = new File(System.getProperty("user.home"));
        chooser.setInitialDirectory(defaultDirectory);
        folder = chooser.showDialog(window);
    }

    private File[] search() {
        // Search for files
        Search search = new Search();
        return search.searchForFiles(folder, window);
    }

    private void save (File[] searchResult) {
        // Save files
        Save save = new Save(outputFolder);
        save.copyMultipleFiles(searchResult);
    }

    /**
     * scene1
     * @return Scene
     */
    private Scene scene1 () {
        Image img = new Image("file:resources/picfinder.png");
        ImageView logo = new ImageView(img);
        Button b = new Button("Search for pics!");
        b.setMinSize(50, 50);
        b.fontProperty();

        String str = "I will search for files ending: ";
        String[] extensions = Filter.getExtensions();
        for (String e: extensions)
            str += (e.equals(extensions[extensions.length-1])) ? e.toUpperCase() : e.toUpperCase() + ", ";
        Label types = new Label(str);

        BorderPane bp = new BorderPane();
        bp.setTop(logo);
        bp.setCenter(b);
        bp.setBottom(types);
        BorderPane.setAlignment(logo, Pos.BOTTOM_CENTER);
        BorderPane.setAlignment(logo, Pos.TOP_CENTER);
        BorderPane.setAlignment(types, Pos.TOP_CENTER);
        bp.setPadding(new Insets(20, 20, 20, 20));

        b.setOnAction(e -> {
            window.setScene(scenes[1]);
            chooseFolder();
            if (folder != null) {
//                logo.setImage(new Image("file:resources/search.gif"));
                ImageView load = new ImageView(new Image("file:resources/search.gif"));
                bp.getChildren().remove(b);
                bp.setCenter(load);

                File[] res = search();
                scene2(res);
            } else {
                AlertBox.display("No Folder Specified!", "You didn't select a folder.", "Exit");
                window.close();
            }
        });

        return new Scene(bp, 800, 500);
    }

    // Create a tree branch
    public TreeItem<String> makeBranch (String text, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(text);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }

    /**
     * scene2
     * @return Scene
     */
    private void scene2 (File[] data) {
        // Scene 2
        Label text = new Label("0/10");

        TreeView<String> tree;
        TreeItem<String> root;

        // Root
        root = new TreeItem<>();
        root.setExpanded(true);

        // Create tree
        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        tree.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            if (newValue != null)
                System.out.println(newValue.getValue());
        });

        for (File f: data) {
//            Label l = new Label(f.getAbsolutePath());
            makeBranch(f.getAbsolutePath(), root);
            System.out.println("Adding to tree: "+f.getName());
        }

        Label title = new Label("I found these files:");
        Button continueToSave = new Button("Continue");
        continueToSave.setOnAction(e -> scene3(data));
        VBox vBox = new VBox();
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(20));
        vBox.getChildren().addAll(title, tree, continueToSave);
        window.setScene(new Scene(vBox, 400, 400));
        window.setResizable(true);
    }

    /**
     * scene3
     * @return Scene
     */
    private void scene3 (File[] result) {
        Text saving = new Text("Saving...");
        saving.setFont(new Font(40));

        // Save files
        Save save = new Save(outputFolder);
        save.copyMultipleFiles(result);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(saving);
        window.setScene(new Scene(vBox, 400, 400));
        window.setResizable(false);
        scene4();
    }

    private void scene4 () {
        Text done = new Text("All Done!");
        done.setFont(new Font(40));
        Button exit = new Button("Exit");
        exit.setOnAction(e -> window.close());

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(10);
        vBox.getChildren().addAll(done, exit);
        window.setScene(new Scene(vBox, 400, 400));
    }
}
