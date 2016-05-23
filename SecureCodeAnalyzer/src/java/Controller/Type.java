package Controller;

public class Type{
    private String id;
    private String description;
    private String url;
    
    public Type( String id, String description, String url ){
        this.id = id;
        this.description = description;
        this.url = url;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }    
    
}