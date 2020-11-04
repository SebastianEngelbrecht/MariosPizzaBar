import java.util.Scanner;

public class UI {

    public void displayUI(){
        displayLine();
        System.out.println("|        MARIOS PIZZABAR       |");
        displayText(1,"MENU CARD",true);
        displayText(2,"ORDER",true);
        displayText(3,"FINANCES",true);
        displayText(4,"MAINTENANCE",true);
        displayText(5,"QUIT",true);
        displayLine();
    }
    public static void displayText() {
        String result = "|";
        for(int i = 0; i < 30; i++)
            result += "-";
        result += "|";
        System.out.println(result);
    }

    public static void displayText(int nr, String text){
        String result = "|";
        int lineCount = 30;
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
        int lineCount = 30;
        text = nr + ". " + text;

        int toAdd = ((lineCount - text.length()) / 2);

        int i = 0;
        for (i = 0; i < toAdd; i++)
            result += between;

        result += text;
    }
    public static void displayText(int nr, String text, boolean state){
        String result = "|";
        int lineCount = 30;
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

    public void displayLine(){
        System.out.println("+------------------------------+");
    }

    public void inputScanner(){
        displayUI();
        System.out.println("MAKE A SELECTION");
        System.out.println("YOU HAVE SELECTED: ");
        MenuCard showMenuCard = new MenuCard();
        Scanner inputScanner = new Scanner(System.in);
        int choice;
        choice = inputScanner.nextInt();

        switch (choice){
            case 1:
                showMenuCard.displayMenuCard();
                break;
            case 2:
            case 3:
            case 4:
            case 5:
                System.exit(0);
                break;
        }


    }

    public void returnButton(){

    }
}
