package linksqueezer;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

public class LinkSqueezer {

    private static final String BASE62_ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String SHORT_URL_PREFIX = "sqz.ly/";

    public String generateShortUrl(String longUrl) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(longUrl.getBytes());
            BigInteger bigInt = new BigInteger(1, hash);
            String digest = bigInt.toString(62);

            // Take the first 5 characters of the digest
            digest = digest.substring(0, 5);

            StringBuilder shortUrl = new StringBuilder(SHORT_URL_PREFIX);
            for (int i = 0; i < digest.length(); i++) {
                char c = digest.charAt(i);
                int index = (int) c;
                shortUrl.append(BASE62_ALPHABET.charAt(index));
            }
            return shortUrl.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating short URL", e);
        }
    }

    public boolean isValidUrl(String url) {
        // Check if the URL is empty or null
        if (url == null || url.isEmpty()) {
            return false;
        }

        // Use a regular expression to check if the URL is valid
        String regex = "^(https?://)?([a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6})[/\\S]*$";
        Pattern pattern = Pattern.compile(regex);

        return pattern.matcher(url).matches();
    }
}
