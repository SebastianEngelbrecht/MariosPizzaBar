import javax.imageio.IIOException;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    private static MenuCard menuCard;
    private static UI showMenu;
    private static Order currentOrdre = null;
    private static List <Order> Active = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Starting Application");
        System.out.println("Hello!");

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
                MenuCard();
                break;

            case 2:
                OrdreMenu();
                break;

            case 3:
                financesMenu();
                break;

            case 4:
                maintenanceMenu();
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
        if(currentOrdre == null) {
            currentOrdre = new Order();
            System.out.println("Creating New Order");
        }

        showMenu.displayOrdreUI();

        switch (showMenu.inputScanner())
        {
            case 1:
                //Add Pizza to currentOrdre
                selectToAdd();
                break;

            case 2:
                //Remove Pizza from currentOrdre
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

    public static void financesMenu(){
        showMenu.displayFinancesUI();

        switch (showMenu.inputScanner()){
            case 1:
                oversightMenu();
                break;

            case 2:

                break;

            case 3:
                System.out.println("Calculating today's turnover");
                turnoverMenu();
                break;

            case 4:
                System.out.println("Returning to Start");
                StartMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                financesMenu();
                break;
        }
    }

    public static void oversightMenu(){
        showMenu.displayOversight(Oversight.LoadFromOversight());

        int choice = showMenu.inputScanner();

        if(choice == 0)
            financesMenu();
        else
            oversightMenu();
    }

    public static void turnoverMenu(){
        showMenu.displayTurnoverUI(menuCard);

        switch ((int)Mathf.Clamp(showMenu.inputScanner(),0,3)){
            case 1:
                break;

            case 2:
                System.out.println("Calculating today's turnover");
                break;

            case 3:
                System.out.println("Returning to Start");
                StartMenu();
                break;
        }
    }

    public static void maintenanceMenu() {
        showMenu.displayMaintenanceUI();

        switch (showMenu.inputScanner()) {
            case 1:
                System.out.println("Testing");
                break;

            case 2:
                System.out.println("Testing");
                break;

            case 3:
                System.out.println("Going back to Menu");
                break;
        }
    }

    public static void selectToAdd(){
        showMenu.displayMenuUI(menuCard);
        int choice = showMenu.inputScanner();

        if (choice >= 1 && choice <= menuCard.getProductByIndex(menuCard.getProductSize()).getIndex())
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
            currentOrdre.setTimeStamp(formatter.format(date));
            Active.add(currentOrdre);
            currentOrdre = null;
        } catch (Exception e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        OrdreMenu();
    }

    public static void ActiveOrder()
    {
        showMenu.displayActiveOrder(Active.toArray(new Order[Active.size()]));
        try
        {
            File active = new File("ActiveOrder.csv");
            Scanner activeScanner = new Scanner(active);
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
        } else if (choice <= Active.size() + 1)
        {
            Oversight.SaveToOversight(Active.get(choice - 1));
            Active.remove(choice - 1);
            ActiveOrder();
        }
        else
        {
            System.out.println("Error!");
            System.out.println("Cannot interpret input.");
            System.out.println("Try again.");
            ActiveOrder();
        }
    }
}
