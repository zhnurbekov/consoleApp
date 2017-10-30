package console.app;

import console.app.Service.PublicationService;
import console.app.Service.PublicationServiceImpl;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String args[]) throws IOException, InterruptedException {


        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        PublicationService service = new PublicationServiceImpl();
        String inputText = service.getCommand();

        while (!inputText.equals("5")) {

            if (!inputText.equals("5")) {

                switch (inputText) {
                    case "1":
                        service.getPublicationsList();
                        break;

                    case "2":
                        service.insert();
                        break;

                    case "3":
                        service.getPublications();
                        break;

                    case "4":
                        service.delete();
                        break;
                }


               inputText = service.getCommand();

            }
        }

    }



}
