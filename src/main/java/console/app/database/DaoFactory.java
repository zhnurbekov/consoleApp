package console.app.database;

import console.app.database.dao.PublicationsDao;
import console.app.database.dao.impl.PublicationsDaoImpl;
import org.h2.tools.RunScript;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DaoFactory {

    private DaoFactory() {
    }

    private static DataSource source;
    private static DaoFactory ourInstance = new DaoFactory();

    static {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:~/fund");
        dataSource.setUsername("user");
        dataSource.setPassword("user");
        source = dataSource;

        checkBase();

    }

    public static DaoFactory getInstance() {
        return ourInstance;
    }

    private static void checkBase() {

        try {

            JdbcTemplate db = new JdbcTemplate(source);
            List list = db.queryForList("SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'PUBLICATIONS'");

            if (list.size() == 0){
                RunScript.execute(source.getConnection(), new StringReader(getScriptSql()));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public PublicationsDao publicationsDao(){return new PublicationsDaoImpl(source);}

    private static String getScriptSql(){
        return ";             \n" +
                "CREATE USER IF NOT EXISTS USER SALT 'b9fd19c74edc914c' HASH 'd31135f5d0f8843465c9ee62d2c93bac92678ed64b43c6fff5f6b601240e0f4f' ADMIN;         \n" +
                "CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E87357EA_27CA_41A5_9521_D8E71252CB4F START WITH 4 BELONGS_TO_TABLE;    \n" +
                "CREATE SEQUENCE PUBLIC.SYSTEM_SEQUENCE_1E8B4521_3E3A_4D2A_B6C1_F39A25CDB6B8 START WITH 53 BELONGS_TO_TABLE;   \n" +
                "CREATE CACHED TABLE PUBLIC.PUBLICATION_TYPE(\n" +
                "    ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_E87357EA_27CA_41A5_9521_D8E71252CB4F) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_E87357EA_27CA_41A5_9521_D8E71252CB4F,\n" +
                "    NAME VARCHAR(200)\n" +
                ");       \n" +
                "ALTER TABLE PUBLIC.PUBLICATION_TYPE ADD CONSTRAINT PUBLIC.CONSTRAINT_B PRIMARY KEY(ID);       \n" +
                "-- 3 +/- SELECT COUNT(*) FROM PUBLIC.PUBLICATION_TYPE;        \n" +
                "INSERT INTO PUBLIC.PUBLICATION_TYPE(ID, NAME) VALUES\n" +
                "(1, STRINGDECODE('\\u041a\\u043d\\u0438\\u0433\\u0430')),\n" +
                "(2, STRINGDECODE('\\u0416\\u0443\\u0440\\u043d\\u0430\\u043b')),\n" +
                "(3, STRINGDECODE('\\u0411\\u0440\\u043e\\u0448\\u044e\\u0440\\u0430'));      \n" +
                "CREATE CACHED TABLE PUBLIC.PUBLICATIONS(\n" +
                "    ID INT DEFAULT (NEXT VALUE FOR PUBLIC.SYSTEM_SEQUENCE_1E8B4521_3E3A_4D2A_B6C1_F39A25CDB6B8) NOT NULL NULL_TO_DEFAULT SEQUENCE PUBLIC.SYSTEM_SEQUENCE_1E8B4521_3E3A_4D2A_B6C1_F39A25CDB6B8,\n" +
                "    PUBLICATION_TYPE INT,\n" +
                "    YEAR INT,\n" +
                "    MONTH INT,\n" +
                "    COUNT_PAGES INT,\n" +
                "    PUBLISHING_HOUSE VARCHAR(500),\n" +
                "    NAME VARCHAR(500),\n" +
                "    AUTHOR VARCHAR(500),\n" +
                "    TITLE VARCHAR(500),\n" +
                "    GENRE VARCHAR(500),\n" +
                "    SUMMARY VARCHAR(2000),\n" +
                "    ARTICLE_LIST VARCHAR(1000),\n" +
                "    DESCRIPTION VARCHAR(2000)\n" +
                ");              \n" +
                "ALTER TABLE PUBLIC.PUBLICATIONS ADD CONSTRAINT PUBLIC.CONSTRAINT_C PRIMARY KEY(ID);           \n" +
                "-- 3 +/- SELECT COUNT(*) FROM PUBLIC.PUBLICATIONS;            \n" +
                "INSERT INTO PUBLIC.PUBLICATIONS(ID, PUBLICATION_TYPE, YEAR, MONTH, COUNT_PAGES, PUBLISHING_HOUSE, NAME, AUTHOR, TITLE, GENRE, SUMMARY, ARTICLE_LIST, DESCRIPTION) VALUES\n" +
                "(46, 1, 1867, 0, 1274, STRINGDECODE('\\u0420\\u0443\\u0441\\u0441\\u043a\\u0438\\u0439 \\u0432\\u0435\\u0441\\u0442\\u043d\\u0438\\u043a'), STRINGDECODE('\\u0412\\u043e\\u0439\\u043d\\u0430 \\u0438 \\u043c\\u0438\\u0440'), STRINGDECODE('\\u041b\\u0435\\u0432 \\u041d\\u0438\\u043a\\u043e\\u043b\\u0430\\u0435\\u0432\\u0438\\u0447 \\u0422\\u043e\\u043b\\u0441\\u0442\\u043e\\u0439'), NULL, STRINGDECODE('\\u0440\\u043e\\u043c\\u0430\\u043d-\\u044d\\u043f\\u043e\\u043f\\u0435\\u044f'), STRINGDECODE('\\u043e\\u043f\\u0438\\u0441\\u044b\\u0432\\u0430\\u044e\\u0449\\u0438\\u0439 \\u0440\\u0443\\u0441\\u0441\\u043a\\u0443\\u044e \\u043e\\u0431\\u0449\\u0435\\u0441\\u0442\\u0432\\u0443 \\u0432 \\u044d\\u043f\\u043e\\u0445\\u0443 \\u0432\\u043e\\u0439\\u043d \\u043f\\u0440\\u043e\\u0442\\u0438\\u0432 \\u041d\\u0430\\u043f\\u043e\\u043b\\u0435\\u043e\\u043d\\u0430 \\u0432 1805\\u20141812 \\u0433\\u043e\\u0434\\u0430\\u0445'), NULL, NULL),\n" +
                "(48, 2, 1924, 5, 50, STRINGDECODE('\\u0422\\u0440\\u0438\\u041c\\u0430\\u0433'), STRINGDECODE('\\u041c\\u0443\\u0440\\u0437\\u0438\\u043b\\u043a\\u0430'), NULL, NULL, NULL, '', STRINGDECODE('\\u0421\\u0442\\u0430\\u0442\\u044c\\u0438 \\u043f\\u0440\\u043e \\u0436\\u0438\\u0432\\u043e\\u0442\\u043d\\u044b\\u0445'), NULL),\n" +
                "(49, 3, 2007, 4, 55, STRINGDECODE('\\u0410\\u0440\\u0442 \\u0414\\u0438\\u0437\\u0430\\u0439\\u043d'), STRINGDECODE('\\u0410\\u0440\\u0442 \\u0414\\u0438\\u0437\\u0430\\u0439\\u043d'), NULL, NULL, NULL, '', NULL, STRINGDECODE('\\u0431\\u0440\\u043e\\u0448\\u044e\\u0440\\u0430 \\u043e\\u0431 \\u0443\\u0441\\u043b\\u0443\\u0433\\u0430\\u0445 \\u0434\\u0438\\u0437\\u0430\\u0439\\u043d\\u0435\\u0440\\u0430'));     \n" +
                "ALTER TABLE PUBLIC.PUBLICATIONS ADD CONSTRAINT PUBLIC.PUBLICATIONS__PUBLICATIONTYPE_FK FOREIGN KEY(PUBLICATION_TYPE) REFERENCES PUBLIC.PUBLICATION_TYPE(ID) NOCHECK;          \n";
    }


}
