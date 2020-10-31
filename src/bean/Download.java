package bean;

public class Download {
    private String id;
    private String name;
    private String path;
    private String description;
    private String size;
    private String start;
    private String image;

    public Download(String id, String name, String path, String description, String size, String start, String image) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.description = description;
        this.size = size;
        this.start = start;
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
