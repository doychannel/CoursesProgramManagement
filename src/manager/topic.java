package manager;
public class topic implements java.io.Serializable {
    private String id;
    private String name;
    private String type;
    private String title;
    private String duration;
    
    public topic() {
    }
    public topic(String id, String name, String type, String title, String duration) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.title = title;
        this.duration = duration;
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

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDuration() {
        return duration;
    }
    public void setDuration(String duration) {
        this.duration = duration;
    }
    @Override
    public String toString() {
        return "topic:" + "id=" + id + ", name=" + name + ", type=" + type + ", title=" + title + ", duration=" + duration;
    }
}
