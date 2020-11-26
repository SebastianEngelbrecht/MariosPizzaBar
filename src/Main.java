import jdbc.JDBC_DB_Connection;

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
    private static JDBC_DB_Connection connection = null;

    public static void main(String[] args) throws Exception {
        try (JDBC_DB_Connection connection = new JDBC_DB_Connection
                ("jdbc:mysql://localhost/?serverTimezone=UTC", Utilities.username(), Utilities.password()))
        {
            Main.connection = connection;
            System.out.println("Starting Application");
            System.out.println("Hello!");
            menuCard = new MenuCard();
            showMenu = new UI();
            menuCard.loadCard();
//          GUI myGui = new GUI();
//          myGui.show();
            StartMenu();
        }
    }

    public static void StartMenu() throws Exception {
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

    public static void MenuCard() throws Exception {
//        showMenu.displayMenuUI(menuCard);
        showMenu.displayMenuUI(connection);
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

    public static void orderMenu() throws Exception {
//        if(currentOrder == null) {
//            currentOrder = new Order();
//            System.out.println("Creating New Order");
//        }

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
                        cancelOrder();
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

    public static void financesMenu() throws Exception {
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

    public static void oversightMenu() throws Exception {
        showMenu.displayOversight(Oversight.LoadFromOversight());

        int choice = showMenu.inputScanner();

        if(choice == 0)
            financesMenu();
        else
            oversightMenu();
    }

    public static void turnoverMenu() throws Exception {
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

    public static void statisticsMenu() throws Exception {
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

    public static void selectToAdd() throws Exception {
        showMenu.displayMenuUI(connection);
        int choice = showMenu.inputScanner();

        if (choice >= 1 && choice <= connection.getPizzaList().size())
        {
            int id = connection.getHighestIdOrderList();
            currentOrder.addOrder(connection, id, choice);
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

//    public static void SaveOrder() throws Exception {
//        try
//        {
//            long startTime = System.currentTimeMillis();
//            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
//            Date date = new Date(startTime);
//
//            FileWriter file = new FileWriter(String.valueOf(activeOrderFileLocation));
//            file.write(currentOrder.toString() + " - " + formatter.format(date));
//            file.close();
//            currentOrder.setTimeStamp(formatter.format(date));
//            Active.add(currentOrder);
//            currentOrder = null;
//        } catch (Exception e)
//        {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        orderMenu();
//    }

    public static void SaveOrder() throws Exception
    {
        int id = connection.getHighestIdOrderList();
        connection.createOrderList(id + 1, -1);
        orderMenu();
    }

    public static void cancelOrder() throws Exception
    {
        int id = connection.getHighestIdOrderList();
        connection.deleteOrderList(id);
        orderMenu();
    }


//    public static void ActiveOrder() throws Exception {
//        showMenu.displayActiveOrder(Active.toArray(new Order[Active.size()]));
//        try
//        {
//            File active = new File(String.valueOf(activeOrderFileLocation));
//            Scanner activeScanner = new Scanner(active);
//            activeScanner.close();
//        } catch (Exception e)
//        {
//            System.out.println("An error occurred.");
//            e.printStackTrace();
//        }
//        int choice = showMenu.inputScanner();
//
//        if (choice == 0)
//        {
//            orderMenu();
//        } else if (choice <= Active.size() + 1)
//        {
//            Oversight.SaveToOversight(Active.get(choice - 1));
//            Active.remove(choice - 1);
//            ActiveOrder();
//        }
//        else
//        {
//            System.out.println("Error!");
//            System.out.println("Cannot interpret input.");
//            System.out.println("Try again.");
//            ActiveOrder();
//        }
//    }

    public static void ActiveOrder() throws Exception {
        showMenu.displayActiveOrder(connection);

        int choice = showMenu.inputScanner();

        if (choice == 0)
        {
            orderMenu();
        } else if (connection.getHighestIdOrderList() == -1)
        {
            orderMenu();
        } else if (choice <= connection.getHighestIdOrderList())
        {
            //needs "saving to oversight" later
            connection.deleteOrderList(choice);
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
