package console.app.database.entity;

public class BrochureEntity {

    private int id;
    private String description;
    private int id_publications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_publications() {
        return id_publications;
    }

    public void setId_publications(int id_publications) {
        this.id_publications = id_publications;
    }
}
