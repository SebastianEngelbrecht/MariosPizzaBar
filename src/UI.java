import java.util.Scanner;

public class UI {
    private static int lineCount = 110;


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
        {
            displayText((1 + i) + " " + list[i].getName() + "  -" + list[i].getDescription() + "-  " + list[i].getPrice() + "kr.", true);
//            displayText(list[i].getDescription());
        }
        displayText();
    }

    public void displayOrdreUI(){
        displayText();
        displayText("Ordrer muligheder");
        displayText(1, "TILFØJ TIL ORDRER");
        displayText(2, "FJERN FRA ORDRER");
        displayText(3, "CANCEL ORDRER");
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
