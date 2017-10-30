package console.app.database.dao;

import console.app.database.entity.PublicationsEntity;

import java.util.List;
import java.util.Map;

public interface PublicationsDao {

    List<PublicationsEntity> getList();

    PublicationsEntity get(int id);

    void insert(PublicationsEntity pub);

    void delete(int id);

    String publicationType(int id);
}
