package console.app.Service;

import console.app.database.DaoFactory;

public interface PublicationService {


    String getCommand();

    void getPublicationsList();

    String getPublications();

    String insert();

    void delete();


}
