package console.app.database.dao.impl;

import console.app.database.dao.AbstractDao;
import console.app.database.entity.BrochureEntity;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BrochureDaoImpl extends AbstractDao<BrochureEntity> {

    public BrochureDaoImpl() {
        super();
    }

    @Override
    public BrochureEntity get(int id_publications) {
        try {
            return db.queryForObject("SELECT * FROM  public.BROCHURE WHERE ID_PUBLICATIONS = ?",
                    new Object[]{id_publications}, this::mapper);
        } catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public int insert(BrochureEntity brochure) {

        String sql = "INSERT INTO  public.BROCHURE(DESCRIPTION, ID_PUBLICATIONS)" +
                "VALUES (?,?)";

        try {

            return insertForKeyId(
                    sql,
                    brochure.getDescription(),
                    brochure.getId_publications()

            );

        } catch (Exception e){
            e.printStackTrace();
        }

        return 0;
    }


    @Override
    public void delete(int id) {
        try {
            db.update("DELETE FROM public.BROCHURE  WHERE ID_PUBLICATIONS = ? ", id);
        } catch (Exception e){
            e.printStackTrace();
        }
    }




    private BrochureEntity mapper(ResultSet rs, int index) throws SQLException {

        BrochureEntity br = new BrochureEntity();
        br.setId(rs.getInt("id"));
        br.setDescription(rs.getString("DESCRIPTION"));
        br.setId_publications(rs.getInt("ID_PUBLICATIONS"));

        return br;
    }
}
