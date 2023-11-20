/**
 *
 * The LinkSqueezerApp class is the entry point of the application. It creates a LinkShortener and a LinkStorage object, and loads all links from the "linksDB.csv" file using the loadLinks method of the LinkStorage object.
 * If the "DB.csv" file does not exist, the class should first create the file and print a message saying that a new file has been created. If the file is empty, it should print a message saying that there are no links stored in the file.
 * The main method then initializes the LinkSqueezerGUI object using the LinkShortener and LinkStorage objects, and calls the initComponents method to start the GUI.
 */
package linksqueezer;
import GUI.MainFrame;


import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        LinkSqueezer linkShortener = new LinkSqueezer();
        LinkStorage linkStorage = new LinkStorage("DB.csv");

        List<Link> links = linkStorage.loadLinks();
        if (links.isEmpty()) {
            System.out.println("The links database is empty.");
        } else {
            System.out.println("Loaded " + links.size() + " links from the database.");
            for (Link link : links) {
                System.out.println(link.getLongUrl() + " " + link.getShortUrl() + " " + link.getAlias());
            }
        }

        MainFrame mainFrame = new MainFrame(linkShortener, linkStorage);
        mainFrame.setVisible(true);
    }
}
