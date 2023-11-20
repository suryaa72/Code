package linksqueezer;


public class Link {

    private String longUrl;
    private String shortUrl;
    private String alias;

    public Link(String longUrl, String shortUrl, String alias) {
        this.longUrl = longUrl;
        this.shortUrl = shortUrl;
        this.alias = alias;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getShortUrl() {
        return shortUrl;
    }

    public void setShortUrl(String shortUrl) {
        this.shortUrl = shortUrl;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
