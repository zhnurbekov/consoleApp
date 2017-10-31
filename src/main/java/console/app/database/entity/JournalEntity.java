package console.app.database.entity;

public class JournalEntity {

    private int id;
    private String article_list;
    private int id_publications;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArticle_list() {
        return article_list;
    }

    public void setArticle_list(String article_list) {
        this.article_list = article_list;
    }

    public int getId_publications() {
        return id_publications;
    }

    public void setId_publications(int id_publications) {
        this.id_publications = id_publications;
    }
}
