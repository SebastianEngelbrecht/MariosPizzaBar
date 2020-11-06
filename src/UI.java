import java.util.Scanner;

public class UI {
    private static int lineCount = 50;


    public void displayStartUI(){
        displayText();
        displayText("MARIOS PIZZABAR");
        displayText(1,"MENU CARD",true);
        displayText(2,"ORDER",true);
        displayText(3,"FINANCES",true);
        displayText(4,"MAINTENANCE",true);
        displayText(5,"QUIT",true);
        displayText();
    }

    public void displayMenuUI(MenuCard card){
        Product[] list = card.getProductList();

        displayText();
        displayText("MENU CARD");
        displayText(0, "RETURN");
        for (int i = 0; i < card.getProductSize(); i++)
            displayText((list[i].getIndex()) + ". " + list[i].getName() + " " + list[i].getPrice() + "kr.", true);
        displayText();
    }

    public void displayOrdreUI(){
        displayText();
        displayText("Ordrer muligheder");
        displayText(1, "TILFØJ TIL ORDRER");
        displayText(2, "FJERN FRA ORDRER");
        displayText(3, "FÆRDIGGØR ORDRER");
        displayText(4, "CANCEL ORDRER");
        displayText();
    }

    public void displayCurrentOrder(Order currentOrder){
        displayText();
        displayText("CURRENT ORDRE");
        displayText(0, "Return");

        float totalPrice = 0;
        for (Product product: currentOrder.getList()) {
            displayText(product.getIndex() + ". " + product.getName() + " " + product.getPrice() + "kr.", true);
            totalPrice += product.getPrice();
        }
        displayText(totalPrice + "kr.");
        displayText();
    }

    public void displaySure(){
        displayText();
        displayText("Are you sure?");
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
    //endregion

    public void displayLine(){
        System.out.println("+------------------------------+");
    }

    public int inputScanner(){
        Scanner inputScanner = new Scanner(System.in);
        int result = inputScanner.nextInt();
        System.out.println("You have selected: " + result);
        return result;
    }

    public void returnButton(){

    }
}
