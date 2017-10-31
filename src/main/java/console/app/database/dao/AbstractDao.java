package console.app.database.dao;

import console.app.database.DaoFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Date;

public abstract class AbstractDao<T> {

    protected JdbcTemplate db;

    public AbstractDao() {
        DataSource source = DaoFactory.getInstance().getDataSource();
        this.db = new JdbcTemplate(source);
    }

    public abstract T get(int id_publications);

    public abstract int insert(T entity);

    public abstract void delete(int id);

    public int insertForKeyId(String sql, Object... args) {
        KeyHolder holder = new GeneratedKeyHolder();
        db.update((connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            int index = 1;
            Object[] var5 = args;
            int var6 = args.length;

            for(int var7 = 0; var7 < var6; ++var7) {
                Object arg = var5[var7];
                if(arg instanceof Date) {
                    ps.setTimestamp(index, new Timestamp(((Date)arg).getTime()));
                } else if(arg instanceof Integer) {
                    ps.setInt(index, ((Integer)arg).intValue());
                } else if(arg instanceof Long) {
                    ps.setLong(index, ((Long)arg).longValue());
                } else if(arg instanceof Double) {
                    ps.setDouble(index, ((Double)arg).doubleValue());
                } else if(arg instanceof Float) {
                    ps.setFloat(index, ((Float)arg).floatValue());
                } else {
                    ps.setString(index, (String)arg);
                }

                ++index;
            }

            return ps;
        }, holder);
        return holder.getKey().intValue();
    }
}
