package console.app.database.entity;

public class PublicationsEntity {

    private int id;
    private int publication_type;
    private int year;
    private int month;
    private int count_pages;
    private String publishing_house;
    private String name;
    private String author;
    private String title;
    private String genre;
    private String summary;
    private String article_list;
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPublication_type() {
        return publication_type;
    }

    public void setPublication_type(int publication_type) {
        this.publication_type = publication_type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getCount_pages() {
        return count_pages;
    }

    public void setCount_pages(int count_pages) {
        this.count_pages = count_pages;
    }

    public String getPublishing_house() {
        return publishing_house;
    }

    public void setPublishing_house(String publishing_house) {
        this.publishing_house = publishing_house;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getArticle_list() {
        return article_list;
    }

    public void setArticle_list(String article_list) {
        this.article_list = article_list;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
