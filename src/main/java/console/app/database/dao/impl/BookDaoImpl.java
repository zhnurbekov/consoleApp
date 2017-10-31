package console.app.database.dao.impl;

import console.app.database.dao.AbstractDao;
import console.app.database.entity.BookEntity;
import java.sql.ResultSet;
import java.sql.SQLException;


public class BookDaoImpl extends AbstractDao<BookEntity> {

    public BookDaoImpl() {
        super();
    }

    @Override
    public BookEntity get(int id_publications) {
        try {
            return db.queryForObject("SELECT * FROM  public.BOOK WHERE ID_PUBLICATIONS = ?",
                    new Object[]{id_publications}, this::mapper);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public int insert(BookEntity book) {

        String sql = "INSERT INTO  public.BOOK( AUTHOR, GENRE, SUMMARY, ID_PUBLICATIONS)" +
                "VALUES (?,?,?,?)";

        try {

            return insertForKeyId(
                    sql,
                    book.getAuthor(),
                    book.getGenre(),
                    book.getSummary(),
                    book.getId_publication()
            );

        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void delete(int id) {
        try {
            db.update("DELETE FROM public.BOOK  WHERE ID_PUBLICATIONS = ? ", id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    private BookEntity mapper(ResultSet rs, int index) throws SQLException {

        BookEntity book = new BookEntity();
        book.setId(rs.getInt("id"));
        book.setAuthor(rs.getString("author"));
        book.setGenre(rs.getString("genre"));
        book.setSummary(rs.getString("summary"));
        book.setId_publication(rs.getInt("ID_PUBLICATIONS"));

        return book;
    }
}
