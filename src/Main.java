import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    private static MenuCard menuCard;
    private static UI showMenu;
    private static Order currentOrdre = null;
    private static Order[] Active = new Order[0];


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

        switch (showMenu.inputScanner())
        {
            case 1:
                //Add to Pizza
                selectToAdd();
                break;

            case 2:
                //Remove from Pizza
                showMenu.displayCurrentOrder(currentOrdre);

                int choice = showMenu.inputScanner();

                if (choice == 0)
                {
                    OrdreMenu();
                    break;
                } else
                {
                    currentOrdre.removeOrder(choice, 1);
                    OrdreMenu();
                }
                break;

            case 3:
                showMenu.displaySure();

                switch (showMenu.inputScanner())
                {
                    case 1:
                        //Save Current Order (YES)
                        SaveOrder();
                        break;

                    case 2:
                        //Cancel Current Order (NO)
                        OrdreMenu();
                        break;

                    default:
                        OrdreMenu();
                        break;
                }
                break;

            case 4:
                showMenu.displayActiveOrder(Active);
                ActiveOrder();
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

    public static void SaveOrder()
    {
        try
        {
            long startTime = System.currentTimeMillis();
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date(startTime);

            FileWriter file = new FileWriter("ActiveOrder.csv");
            file.write(currentOrdre.toString() + " - " + formatter.format(date));
            file.close();
        } catch (Exception e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        OrdreMenu();
    }

    public static void ActiveOrder()
    {
        try
        {
            File active = new File("ActiveOrder.csv");
            Scanner activeScanner = new Scanner(active);
            while (activeScanner.hasNextLine())
            {
                String data = activeScanner.nextLine();
                System.out.println(data);
            }
            activeScanner.close();
        } catch (Exception e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        int choice = showMenu.inputScanner();

        if (choice == 0)
        {
            OrdreMenu();
        } else
        {
            System.out.println("Error!");
            System.out.println("Cannot interpret input.");
            System.out.println("Try again.");
            ActiveOrder();
        }
    }
}
