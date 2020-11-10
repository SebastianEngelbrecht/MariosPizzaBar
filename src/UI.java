import java.util.Scanner;

public class UI {
    private static int lineCount = 110;

    public void displayStartUI(){
        displayText();
        displayText("MARIOS PIZZABAR");
        displayText(1,"MENU CARD",true);
        displayText(2,"ORDER",true);
        displayText(3,"FINANCES",true);
        displayText(5,"QUIT",true);
        displayText();
    }

    public void displayMenuUI(MenuCard card){
        Product[] list = card.getProductList();

        displayText();
        displayText("MENU CARD");
        System.out.println(list.length);
        for (int i = 0; i < list.length; i++)
            displayText((1 + i) + " " + list[i].getName() + " -- " + list[i].getDescription(), list[i].getPrice() + "kr.");
        displayText(0, "BACK TO MENU");
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
        displayText(1, "STATISTICS", true);
        displayText(2, "CALCULATE TURNOVER", true);
        displayText(3, "BACK TO MENU", true);
        displayText();
    }

    public void displayTurnoverUI(MenuCard card){
        Product[] list = card.getProductList();

        displayText();
        displayText("TODAY'S TURNOVER");
        displayText();

        for (int i = 0; i < card.getProductSize(); i++) {
            displayText("ORDER " + (i) + ": " + list[i].getPrice() + " kr.", true);
        }
        displayText("TOTAL: ",true);
        displayText(3, "BACK TO MAIN MENU",true);
        displayText();

    }

    public void displayCurrentOrder(Order currentOrder){
        displayText();
        displayText("CURRENT ORDER");
        float totalPrice = 0;
        for (Product product: currentOrder.getList()) {
            displayText(product.getIndex() + ". " + product.getName() + " -- " + product.getDescription(), product.getPrice() + "kr.");
            totalPrice += product.getPrice();
        }
        displayText(totalPrice + "kr.");
        displayText(0, "RETURN");
        displayText();
    }

    public void displayActiveOrder(Order[] Active){
        displayText();
        displayText("ACTIVE ORDERS");
        for (Order order: Active)
        {
            for (Product product : order.getList())
            displayText(product.getName() + product.getDescription());
        }
        displayText(0, "RETURN");
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

    public static void displayText(int nr, String text, char between) {
        String result = "|";
        text = nr + ". " + text;

        int toAdd = ((lineCount - text.length()) / 2);

        int i = 0;
        for (i = 0; i < toAdd; i++)
            result += between;

        result += text;
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

    public void displayLine(){
        System.out.println("+------------------------------+");
    }

    public int inputScanner(){
        Scanner inputScanner = new Scanner(System.in);
        int result = inputScanner.nextInt();
        System.out.println("YOU HAVE SELECTED: " + result);
        return result;
    }

    public void returnButton(){

    }
}
