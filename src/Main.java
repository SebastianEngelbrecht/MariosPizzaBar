import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Scanner;

public class Main {
    public static MenuCard menuCard;
    private static UI showMenu;
    private static Order currentOrder = null;
    private static List <Order> Active = new ArrayList<>();
    private static final Path activeOrderFileLocation = Paths.get("Resources", "ActiveOrder.csv");


    public static void main(String[] args) {

        System.out.println("Starting Application");
        System.out.println("Hello!");
        menuCard = new MenuCard();
        showMenu = new UI();
        menuCard.loadCard();
//        GUI myGui = new GUI();
//        myGui.show();
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
                orderMenu();
                break;

            case 3:
                financesMenu();
                break;

            case 4:
                System.out.println("Ending Application");
                System.out.println("Goodbye!");
                System.exit(0);
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

    public static void orderMenu(){
        if(currentOrder == null) {
            currentOrder = new Order();
            System.out.println("Creating New Order");
        }

        showMenu.displayOrderUI();

        switch (showMenu.inputScanner())
        {
            case 1:
                //Add Pizza to currentOrder
                selectToAdd();
                break;

            case 2:
                //Remove Pizza from currentOrder
                showMenu.displayCurrentOrder(currentOrder);

                int choice = showMenu.inputScanner();

                if (choice == 0)
                {
                    orderMenu();
                    break;
                } else
                {
                    currentOrder.removeOrder(choice, 1);
                    orderMenu();
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
                        orderMenu();
                        break;

                    default:
                        orderMenu();
                        break;
                }
                break;

            case 4:
                ActiveOrder();
                break;

            case 5:
                System.out.println("Canceling Order");
                System.out.println("Returning to Start");
                currentOrder = null;
                StartMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                orderMenu();
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
                System.out.println("Showing today's most popular pizza");
                statisticsMenu();
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
        showMenu.displayTurnoverUI(Oversight.LoadFromOversight());

        switch (showMenu.inputScanner()){
            case 0:
                System.out.println("Returning to Start");
                financesMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                turnoverMenu();
                break;
        }
    }

    public static void statisticsMenu()
    {
        showMenu.displayStatisticsUI(Oversight.LoadFromOversight());

        switch (showMenu.inputScanner())
        {
            case 0:
                System.out.println("Returning to Start");
                financesMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpret input.");
                System.out.println("Try again.");
                statisticsMenu();
                break;
        }
    }

    public static void selectToAdd(){
        showMenu.displayMenuUI(menuCard);
        int choice = showMenu.inputScanner();

        if (choice >= 1 && choice <= menuCard.getProductByIndex(menuCard.getProductSize()).getIndex())
        {
            currentOrder.addOrder(menuCard.getProductByIndex(choice));
            orderMenu();
        }
        else if(choice == 0)
            orderMenu();
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

            FileWriter file = new FileWriter(String.valueOf(activeOrderFileLocation));
            file.write(currentOrder.toString() + " - " + formatter.format(date));
            file.close();
            currentOrder.setTimeStamp(formatter.format(date));
            Active.add(currentOrder);
            currentOrder = null;
        } catch (Exception e)
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        orderMenu();
    }

    public static void ActiveOrder()
    {
        showMenu.displayActiveOrder(Active.toArray(new Order[Active.size()]));
        try
        {
            File active = new File(String.valueOf(activeOrderFileLocation));
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
            orderMenu();
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
