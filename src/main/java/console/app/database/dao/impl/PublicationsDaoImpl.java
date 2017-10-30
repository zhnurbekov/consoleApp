package console.app.database.dao.impl;

import console.app.database.dao.PublicationsDao;
import console.app.database.entity.PublicationsEntity;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PublicationsDaoImpl implements PublicationsDao {

    private JdbcTemplate db;

    public PublicationsDaoImpl(DataSource source) {
        this.db = new JdbcTemplate(source);
    }


    @Override
    public List<PublicationsEntity> getList() {
        try {
            return db.query("SELECT * FROM Publications",
                    new Object[]{}, this::mapper);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public PublicationsEntity get(int id) {
        try {
            return db.queryForObject("SELECT * FROM PUBLIC.Publications WHERE id = ?",
                    new Object[]{id}, this::mapper);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void insert(PublicationsEntity pub) {

        String sql = "INSERT INTO Publications (" +
                "publication_type, year, month, count_pages, publishing_house, " +
                "name, author, title, genre, summary, article_list, description)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {

            db.update(
                    sql,
                    pub.getPublication_type(),
                    pub.getYear(),
                    pub.getMonth(),
                    pub.getCount_pages(),
                    pub.getPublishing_house(),
                    pub.getName(),
                    pub.getAuthor(),
                    pub.getTitle(),
                    pub.getGenre(),
                    pub.getSummary(),
                    pub.getArticle_list(),
                    pub.getDescription()
            );

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try {
            db.update("DELETE FROM Publications WHERE id = ? ", id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String publicationType(int id) {
        try {
            return db.queryForMap("SELECT name FROM publication_type WHERE id = ?", id)
                    .get("name").toString();
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


    private PublicationsEntity mapper(ResultSet rs, int index) throws SQLException {

        PublicationsEntity publication = new PublicationsEntity();
        publication.setId(rs.getInt("id"));
        publication.setPublication_type(rs.getInt("publication_type"));
        publication.setYear(rs.getInt("year"));
        publication.setMonth(rs.getInt("month"));
        publication.setCount_pages(rs.getInt("count_pages"));
        publication.setPublishing_house(rs.getString("publishing_house"));
        publication.setName(rs.getString("name"));
        publication.setAuthor(rs.getString("author"));
        publication.setTitle(rs.getString("title"));
        publication.setGenre(rs.getString("genre"));
        publication.setSummary(rs.getString("SUMMARY"));
        publication.setArticle_list(rs.getString("article_list"));
        publication.setDescription(rs.getString("description"));

        return publication;
    }
}
