import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    private static MenuCard menuCard;
    private static UI showMenu;
    private static Order currentOrdre = null;
    private static Order[]Active = new Order[0];


    public static void main(String[] args) {
        menuCard = new MenuCard();
        showMenu = new UI();

        menuCard.loadCard();
        StartMenu();
    }

    public static void StartMenu(){
        showMenu.displayStartUI();

        switch (showMenu.inputScanner())
        {
            case 1:
                System.out.println("Showing Menu Card");
                MenuCard();
                break;

            case 2:
                System.out.println("Creating New Order");
                OrdreMenu();
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                System.out.println("Ending Application");
                System.out.println("Goodbye!");
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                StartMenu();
                break;
        }
    }

    public static void MenuCard(){
        showMenu.displayMenuUI(menuCard);

        if (showMenu.inputScanner() == 0) {
            System.out.println("Returning to Start");
            StartMenu();

        }
        else
        {
            System.out.println("Error!");
            System.out.println("Cannot interpret input.");
            System.out.println("Try again.");
            MenuCard();
        }
    }

    public static void OrdreMenu(){
        if(currentOrdre == null)
            currentOrdre = new Order();

        showMenu.displayOrdreUI();

        switch (showMenu.inputScanner()){
            case 1:
                //Add to Pizza
                selectToAdd();
                break;

            case 2:
                //Remove from Pizza
                showMenu.displayCurrentOrder(currentOrdre);

                int choice = showMenu.inputScanner();

                if (choice == 0) {
                    OrdreMenu();
                    break;
                }
                else
                {
                    currentOrdre.removeOrder(choice, 1);
                    OrdreMenu();
                }
                break;

            case 3:
                showMenu.displaySure();

                switch (showMenu.inputScanner()){
                    case 1:

                        break;

                    case 2:
                        break;

                    default:
                        OrdreMenu();
                        break;
                }
                break;

            case 4:
                //Active order list
                showMenu.displayActiveOrder(Active);
                try
                {
                    long startTime = System.currentTimeMillis();
                    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
                    Date date = new Date(startTime);

                    File file = new File(formatter.format(date) + "ActiveOrder.csv");
                    System.out.println(formatter.format(date));
                } catch (Exception e)
                {
                System.out.println("An error occurred.");
                e.printStackTrace();
                }


                break;

            case 5:
                System.out.println("Canceling Order");
                System.out.println("Returning to Start");
                currentOrdre = null;
                StartMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                OrdreMenu();
                break;
        }
    }

    public static void selectToAdd() {
        showMenu.displayMenuUI(menuCard);
        int choice = showMenu.inputScanner();

        if (choice >= 1 && choice <= menuCard.getProductByIndex(menuCard.getProductSize() - 1).getIndex())
        {
            currentOrdre.addOrder(menuCard.getProductByIndex(choice));
            OrdreMenu();
        }
        else if(choice == 0)
            OrdreMenu();
        else
        {
            System.out.println("Error!");
            System.out.println("Cannot interpret input.");
            System.out.println("Try again.");
            selectToAdd();
        }
    }
}
