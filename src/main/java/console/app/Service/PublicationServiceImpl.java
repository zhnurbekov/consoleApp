package console.app.Service;

import console.app.database.dao.AbstractDao;
import console.app.database.dao.impl.BookDaoImpl;
import console.app.database.dao.impl.BrochureDaoImpl;
import console.app.database.dao.impl.JournalDaoImpl;
import console.app.database.dao.impl.PublicationsDaoImpl;
import console.app.database.entity.BookEntity;
import console.app.database.entity.BrochureEntity;
import console.app.database.entity.JournalEntity;
import console.app.database.entity.PublicationsEntity;

import java.io.IOException;
import java.util.*;

public class PublicationServiceImpl implements PublicationService {


    @Override
    public String getCommand() {

        List<String> commands = new ArrayList<>();
        commands.add("1");
        commands.add("2");
        commands.add("3");
        commands.add("4");
        commands.add("5");

        System.out.println("Для дальнейших действий выберите пункт и введите цифру\n");
        System.out.println("1.Просмотр зарегистрированных изданий в фонде");
        System.out.println("2.Добавление нового издания в фонд");
        System.out.println("3.Просмотр информации выбранного издания");
        System.out.println("4.Удаление выбранного издания");
        System.out.println("5.Выход ");

        Scanner in = new Scanner(System.in, "cp866");
        String inputText = in.nextLine();

        while (!commands.contains(inputText)) {
            System.out.println("Введенные данные не верны, пожалуйста введите заново");
            inputText = in.nextLine();
        }

        return inputText;
    }


    @Override
    public void getPublicationsList() {
        clearScreen();
        System.out.println("\nЗарегистрированные изданий в фонде\n");

        System.out.println("+----+---------+-------------------+------+----------+-----------------+");

        System.out.printf("%-5s%-10s%-20s%-7s%-11s%-15s%n",
                "| # ", "| Тип", "| Наименование", "| Год ", "|Кол-во стр", "| Издательство");

        PublicationsDaoImpl publicationsDao = new PublicationsDaoImpl();

        for (PublicationsEntity list : publicationsDao.getList()) {

            String type = publicationsDao.publicationType(list.getPublication_type());

            System.out.println("+----+---------+-------------------+------+----------+-----------------+");

            System.out.printf("%-5s%-10s%-20s%-7s%-11s%-15s%n",
                    "| " + list.getId(), "| " + type, "| " + list.getName(), "| " + list.getYear()
                    , "| " + list.getCount_pages(), "| " + list.getPublishing_house());

        }
        System.out.println("+----+---------+-------------------+------+----------+-----------------+");
    }

    @Override
    public String getPublications() {
        clearScreen();
        System.out.println("Введите идентификатор");
        Scanner in = new Scanner(System.in, "cp866");
        String inputText = in.nextLine();
        clearScreen();

        try {

            AbstractDao abstractDao = new PublicationsDaoImpl();
            int publicationId = Integer.parseInt(inputText);
            PublicationsEntity pub = (PublicationsEntity) abstractDao.get(publicationId);

            if (pub.getPublication_type() == 1) {

                abstractDao = new BookDaoImpl();
                BookEntity book = (BookEntity) abstractDao.get(publicationId);

                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Идентификатор ", "| " + pub.getId());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Автор ", "| " + lineBreak(book.getAuthor()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Наименование книги ", "| " + lineBreak(pub.getName()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Год издательства ", "| " + pub.getYear());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Количество страниц ", "| " + pub.getCount_pages());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Издательство ", "| " + lineBreak(pub.getPublishing_house()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Жанр книги ", "| " + lineBreak(book.getGenre()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Краткое содержание", "| " + lineBreak(book.getSummary()));
                System.out.println("+-----------------------------+--------------------------------------+");


            } else if (pub.getPublication_type() == 2) {

                abstractDao = new JournalDaoImpl();
                JournalEntity journal = (JournalEntity) abstractDao.get(publicationId);

                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Наименование журнала ", "| " + lineBreak(pub.getName()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Год издательства ", "| " + pub.getYear());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Месяц  издательства ", "| " + pub.getMonth());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Количество страниц ", "| " + pub.getCount_pages());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Издательство ", "| " + lineBreak(pub.getPublishing_house()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Список статей ", "| " + lineBreak(journal.getArticle_list()));
                System.out.println("+-----------------------------+--------------------------------------+");


            } else {

                abstractDao = new BrochureDaoImpl();
                BrochureEntity brochure = (BrochureEntity) abstractDao.get(publicationId);

                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Наименование брошюры ", "| " + lineBreak(pub.getName()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Год издательства ", "| " + pub.getYear());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Месяц  издательства ", "| " + pub.getMonth());
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Издательство ", "| " + lineBreak(pub.getPublishing_house()));
                System.out.println("+-----------------------------+--------------------------------------+");
                System.out.printf("%-30s%-40s%n", "| Краткое описание брошюры ", "| " + lineBreak(brochure.getDescription()));
                System.out.println("+-----------------------------+--------------------------------------+");

            }

        } catch (Exception E) {
            System.out.println("Вы ввели неправильный идентификатор");
        }


        return inputText;
    }

    @Override
    public String insert() {
        clearScreen();
        System.out.println("Выберите к какой категорий относится издание");
        System.out.println("1.Книга");
        System.out.println("2.Журнал");
        System.out.println("3.Брошюра");
        Scanner in = new Scanner(System.in, "cp866");
        String inputText = in.nextLine();
        int type = Integer.parseInt(inputText);

        if (type == 1 || type == 2 || type == 3) {

            PublicationsEntity pub = new PublicationsEntity();
            PublicationsDaoImpl publicationsDao = new PublicationsDaoImpl();

            System.out.println("Введите название ");
            pub.setName(in.nextLine());
            pub.setYear(getInputInteger("Введите год издательства"));
            pub.setPublication_type(type);

            if (type == 1) {
                pub.setCount_pages(getInputInteger("Введите количество страниц"));
            } else if (type == 2) {
                pub.setMonth(getInputInteger("Введите месяц издательства в формате '05 , 06 , 07'"));
                pub.setCount_pages(getInputInteger("Введите количество страниц"));
            } else {
                pub.setMonth(getInputInteger("Введите месяц издательства в формате '05 , 06 , 07'"));
            }

            System.out.println("Введите название издательство");
            pub.setPublishing_house(in.nextLine());
            int publicationId = publicationsDao.insert(pub);


            AbstractDao abstractDao;
            switch (type){
                case 1:
                    abstractDao = new BookDaoImpl();
                    BookEntity bookEntity = new BookEntity();
                    System.out.println("Введите автора книги");
                    bookEntity.setAuthor(in.nextLine());
                    System.out.println("Введите жанр книги");
                    bookEntity.setGenre(in.nextLine());
                    System.out.println("Введите краткое содержание");
                    bookEntity.setSummary(in.nextLine());
                    bookEntity.setId_publication(publicationId);
                    abstractDao.insert(bookEntity);
                    break;
                case 2:
                    abstractDao = new JournalDaoImpl();
                    JournalEntity journalEntity = new JournalEntity();
                    System.out.println("Введите список статей опубликованный в данном журнале");
                    journalEntity.setArticle_list(in.nextLine());
                    journalEntity.setId_publications(publicationId);
                    abstractDao.insert(journalEntity);
                    break;
                case 3:
                    abstractDao = new BrochureDaoImpl();
                    BrochureEntity brochureEntity = new BrochureEntity();
                    System.out.println("Введите краткое описание");
                    brochureEntity.setDescription(in.nextLine());
                    brochureEntity.setId_publications(publicationId);
                    abstractDao.insert(brochureEntity);
                    break;
            }

            clearScreen();
            System.out.println("Издательство успешно добавлено");

        } else {

            System.out.println("Данные которые вы ввели не правильно");
            System.out.println("Введите заново");

        }

        return inputText;
    }

    @Override
    public void delete() {
        clearScreen();
        System.out.println("Введите идентификатор издания");
        Scanner in = new Scanner(System.in, "cp866");
        String inputText = in.nextLine();
        clearScreen();

        try {

            int publicationId = Integer.parseInt(inputText);
            AbstractDao abstractDao = new PublicationsDaoImpl();
            PublicationsEntity pub = (PublicationsEntity) abstractDao.get(publicationId);

            switch (pub.getPublication_type()){
                case 1: abstractDao = new BookDaoImpl(); break;
                case 2: abstractDao = new JournalDaoImpl(); break;
                case 3: abstractDao = new BrochureDaoImpl(); break;
            }

            abstractDao.delete(publicationId);
            abstractDao = new PublicationsDaoImpl();
            abstractDao.delete(publicationId);
            System.out.println("Издательства успешно удалено");

        } catch (Exception e) {
            System.out.println("Вы ввели неправильный идентификатор");
            System.out.println("Введите заново");
        }

    }


    private int getInputInteger(String text) {

        Scanner in = new Scanner(System.in, "cp866");
        System.out.println(text);

        while (true) {

            String inputText = in.nextLine();

            try {

                return Integer.parseInt(inputText);

            } catch (Exception e) {

                System.out.println("Не правильный формат введенных данных");
                System.out.println("Введите заново");

            }
        }

    }

    public static void clearScreen() {
        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String lineBreak(String str) {

        String string;
        String indentation = "|                             | ";
        if (str.length() < 40) {
            string = str;
        } else {
            String stringOne = str.substring(0, 39);
            string = stringOne + "\n" + indentation + str.substring(39, 75) + " ...";
        }
        return string;
    }
}
