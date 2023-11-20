/**
 * The LinkStorage class provides functionality for managing links by loading and saving them from and to a file.
 * It contains methods for loading all links from the file, saving a new link to the file, and retrieving a link by its short URL or alias.
 */
package linksqueezer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LinkStorage class takes a filepath as a parameter in the constructor,
 * which represents the path to the file where the links are stored.
 */
public class LinkStorage {

    private String filepath;

    /**
     * Creates a new instance of the LinkStorage class with the specified
     * filepath.
     *
     * @param filepath the path to the file where the links are stored
     */
    public LinkStorage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads all links from the file and returns them as a List of Link objects.
     *
     * @return a List of Link objects representing all the links in the file
     * @throws IOException if an I/O error occurs while reading from the file
     */
    public List<Link> loadLinks() throws IOException {
        List<Link> links = new ArrayList<>();
        File file = new File(filepath);
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                String longUrl = fields[0];
                String shortUrl = fields[1];
                String alias = fields[2];
                links.add(new Link(longUrl, shortUrl, alias));
            }
        }
        return links;
    }

    /**
     * Saves a new link to the file.
     *
     * @param link the Link object to be saved to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void saveLink(Link link) throws IOException {
        try (FileWriter writer = new FileWriter(filepath, true)) {
            writer.write(link.getLongUrl() + "," + link.getShortUrl() + "," + link.getAlias() + "\n");
        }
    }

    /**
     * Retrieves a Link object by its short URL.
     *
     * @param shortUrl the short URL of the Link object to be retrieved
     * @return the Link object with the specified short URL, or null if no such
     * link exists in the file
     * @throws IOException if an I/O error occurs while reading from the file
     */
    public Link getLinkByShortUrl(String shortUrl) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[1].equals(shortUrl)) {
                    String longUrl = fields[0];
                    String alias = fields[2];
                    return new Link(longUrl, shortUrl, alias);
                }
            }
        }
        return null;
    }

    /**
     * Retrieves a Link object by its alias.
     *
     * @param alias the alias of the Link object to be retrieved
     * @return the Link object with the specified alias, or null if no such link
     * exists in the file
     * @throws IOException if an I/O error occurs while reading from the file
     */
    public Link getLinkByAlias(String alias) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[2].equals(alias)) {
                    String longUrl = fields[0];
                    String shortUrl = fields[1];
                    return new Link(longUrl, shortUrl, alias);
                }
            }
        }
        return null;
    }
}
