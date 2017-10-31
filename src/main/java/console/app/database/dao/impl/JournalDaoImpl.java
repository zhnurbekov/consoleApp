package console.app.database.dao.impl;

import console.app.database.dao.AbstractDao;
import console.app.database.entity.JournalEntity;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JournalDaoImpl extends AbstractDao<JournalEntity> {

    public JournalDaoImpl() {
        super();
    }


    @Override
    public JournalEntity get(int id_publications) {
        try {
            return db.queryForObject("SELECT * FROM  public.JOURNAL WHERE ID_PUBLICATIONS = ?",
                    new Object[]{id_publications}, this::mapper);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public int insert(JournalEntity journal) {

        String sql = "INSERT INTO  public.JOURNAL(ARTICLE_LIST, ID_PUBLICATIONS)" +
                "VALUES (?,?)";

        try {

            return insertForKeyId(
                    sql,
                    journal.getArticle_list(),
                    journal.getId_publications()

            );

        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }


    @Override
    public void delete(int id) {
        try {
            db.update("DELETE FROM public.JOURNAL  WHERE ID_PUBLICATIONS = ? ", id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    private JournalEntity mapper(ResultSet rs, int index) throws SQLException {

        JournalEntity jr = new JournalEntity();
        jr.setId(rs.getInt("id"));
        jr.setArticle_list(rs.getString("article_list"));
        jr.setId_publications(rs.getInt("ID_PUBLICATIONS"));

        return jr;
    }
}
