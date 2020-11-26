import jdbc.JDBC_DB_Connection;
import jdbc.JDBC_DB_OrderList;

import java.sql.SQLException;
import java.util.*;

public class UI {
    private static int lineCount = 110;

    public void displayStartUI(){
        displayText();
        displayText("MARIOS PIZZABAR");
        displayText(1,"MENU CARD",true);
        displayText(2,"ORDER",true);
        displayText(3,"FINANCES",true);
        displayText(4,"QUIT",true);
        displayText();
    }

    public void displayMenuUI(MenuCard card){
        Product[] list = card.getProductList();

        displayText();
        displayText("MENU CARD");
        displayText(0, "RETURN");
        for (int i = 0; i < card.getProductSize(); i++)
            displayText((1 + i) + " " + list[i].getName() + " -- " + list[i].getDescription(), list[i].getPrice() + "kr.");
        displayText();
    }

    public void displayMenuUI(JDBC_DB_Connection connection) throws Exception {
        displayText();
        displayText("MENU CARD");
        displayText(0, "RETURN");
        try {
            for (int i = 0; i < connection.getPizzaList().size(); i++)
                displayText((connection.getPizzaList().get(i).getId()) + " " + connection.getPizzaList().get(i).getName(), connection.getPizzaList().get(i).getPrice() + "kr.");
        }catch (SQLException e) {
            throw new Exception(e);
        }
        displayText();
    }

    public void displayOrderUI(){
        displayText();
        displayText("Select An Option");
        displayText(1, "ADD PIZZA", true);
        displayText(2, "REMOVE PIZZA", true);
        displayText(3, "FINISH ORDER", true);
        displayText(4, "ACTIVE ORDERS", true);
        displayText(5, "BACK TO MENU", true);
        displayText();
    }

    public void displayFinancesUI(){
        displayText();
        displayText("Finances");
        displayText(1, "OVERSIGHT", true);
        displayText(2, "STATISTICS", true);
        displayText(3, "CALCULATE TURNOVER", true);
        displayText(4, "BACK TO MENU", true);
        displayText();
    }

    public void displayOversight(Order[] toShow){
        displayText();
        displayText("Oversight");
        displayText(0, "Return", true);
        displayText("");
        for (Order o: toShow) {
            displayText(o.getTimeStamp(), true);
            for (Product p: o.getList()) {
                displayText(p.getIndex() + ". " + p.getName() + " -- " + p.getDescription(), p.getPrice() + "kr.");
            }
            displayText("");
        }
        displayText();
    }

    public void displayTurnoverUI(Order[] input){
        displayText();
        displayText("TODAY'S TURNOVER");
        displayText();

        for (Order o: input) {
            for (Product p: o.getList()) {
                displayText(p.getName(), p.getPrice() + "kr.");
            }
        }
        displayText();
        displayText("TOTAL: " + Oversight.CalculateTurnOver(input),true);
        displayText();
        displayText(0, "BACK TO MAIN MENU",true);
        displayText();

    }

    public void displayStatisticsUI(Order[] input)
    {
        displayText();
            displayText("MOST POPULAR PIZZA");
        displayText();

        ArrayList<Integer> list = new ArrayList<>();
        for (Order o: input) {
            for (Product p: o.getList()) {
                list.add(p.getIndex());
            }
        }

        int mostCommon = -1, mostCount = 0, curCount = 0;
        for(int i: list) {
            curCount = 1;
            for (int j: list) {
                if(j == i)
                    curCount++;
            }

            if(curCount > mostCount) {
                mostCount = curCount;
                mostCommon = i;
            }
        }
        displayText(MenuCard.getProductByIndex(mostCommon).getName() + "");
        displayText();
        displayText(0, "BACK TO MAIN MENU",true);
        displayText();
    }


    public void displayCurrentOrder(Order currentOrder){
        displayText();
        displayText("CURRENT ORDER");
        displayText(0, "RETURN");

        float totalPrice = 0;
        for (Product product: currentOrder.getList()) {
            displayText(product.getIndex() + ". " + product.getName() + " -- " + product.getDescription(), product.getPrice() + "kr.");
            totalPrice += product.getPrice();
        }
        displayText(totalPrice + "kr.");
        displayText();
    }
//
//    public void displayActiveOrder(Order[] Active){
//        displayText();
//        displayText("ACTIVE ORDERS");
//        displayText(0, "RETURN");
//        for (int i = 0; i < Active.length; i++)
//        {
//            Order order = Active[i];
//            displayText("Ordre nr: " + (i+1) + " - " + order.getTimeStamp(),true);
//            for (Product product : order.getList())
//            {
//                displayText(product.getIndex() + ". " + product.getName() + " -- " + product.getDescription(), product.getPrice() + "kr.");
//            }
//            displayText("");
//        }
//        displayText("SELECT ORDER NR. TO COMPLETE ORDER");
//        displayText();
//    }

    public void displayActiveOrder(JDBC_DB_Connection connection) throws Exception {
        displayText();
        displayText("ACTIVE ORDERS");
        displayText(0, "RETURN");
        if (connection.getHighestIdOrderList() == -1)
        {
            displayText("");
        } else
            {
                for (int odrderNumber = 1; odrderNumber <= connection.getHighestIdOrderList(); odrderNumber++)
                {
                    displayText("Ordre nr: " + (odrderNumber), true);

                    for (int pizzaOrderBy = 1; pizzaOrderBy <= connection.selectedCountOrderID(odrderNumber); pizzaOrderBy++)
                    {
                        displayText(order.getName(odrderNumber, pizzaOrderBy), order.getPrice(odrderNumber, pizzaOrderBy) + "kr.");
                    }
                    displayText("");
                }
                displayText("SELECT ORDER NR. TO COMPLETE ORDER");
            }
        displayText();
    }

    public void displaySure(){
        displayText();
        displayText("ARE YOU SURE?");
        displayText(1, "YES", true);
        displayText(2, "NO", true);
        displayText();
    }

    //region DisplayText Functions:
    public static void displayText() {
        String result = "|";

        for(int i = 0; i < lineCount; i++)
            result += "-";
        result += "|";

        System.out.println(result);
    }

    public static void displayText(String text){
        String result = "|";

        int toAdd = ((lineCount - text.length()) / 2);

        int i = 0;
        for(i = 0; i < toAdd; i++)
            result += " ";

        result += text;

        for (i = 0; i < toAdd; i++)
            result += " ";

        if(text.length() % 2 != 0) {
            result += " ";
        }
        result += "|";

        System.out.println(result);
    }

    public static void displayText(String text, boolean state){
        String result = "|";

        int i = 0;
        for(i = 0; i < 6; i++)
            result += " ";

        result += text;
        int toAdd = lineCount - result.length();

        for (i = 0; i < toAdd + 1; i++)
            result += " ";


        result += "|";

        System.out.println(result);
    }

    public static void displayText(int nr, String text){
        String result = "|";
        text = nr + ". " + text;

        int toAdd = ((lineCount - text.length()) / 2);

        int i = 0;
        for(i = 0; i < toAdd; i++)
            result += " ";

        result += text;

        for (i = 0; i < toAdd; i++)
            result += " ";

        if(text.length() % 2 != 0) {
            result += " ";
        }
        result += "|";

        System.out.println(result);
    }

    public static void displayText(int nr, String text, boolean state){
        String result = "|";
        text = nr + ". " + text;

        int i = 0;
        for(i = 0; i < 6; i++)
            result += " ";

        result += text;
        int toAdd = lineCount - result.length();

        for (i = 0; i < toAdd + 1; i++)
            result += " ";


        result += "|";

        System.out.println(result);
    }

    public static void displayText(String text, String kr){
        String result = "|";

        int i = 0;
        for(i = 0; i < 6; i++)
            result += " ";

        result += text;
        int toAdd = lineCount - (result.length() + kr.length() + 6);
        for (i = 0; i < toAdd + 1; i++)
            result += " ";

        result += kr;

        toAdd = lineCount - result.length();
        for (i = 0; i < toAdd + 1; i++)
            result += " ";

        result += "|";

        System.out.println(result);
    }


    //endregion

    public int inputScanner(){
        Scanner inputScanner = new Scanner(System.in);
        int result = inputScanner.nextInt();
        System.out.println("YOU HAVE SELECTED: " + result);
        return result;
    }
}
