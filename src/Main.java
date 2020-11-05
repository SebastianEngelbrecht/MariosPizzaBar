public class Main {
private static MenuCard menuCard;
private static UI showMenu;

    public static void main(String[] args) {
        menuCard = new MenuCard();
        showMenu = new UI();

        menuCard.loadCard();
        StartMenu();
    }

    public static void StartMenu(){
        showMenu.displayStartUI();

        switch ((int)Mathf.Clamp(showMenu.inputScanner(),1,5))
        {
            case 1:
                System.out.println("Showing Menu Card");
                MenuCard("Start");
                break;

            case 2:
                System.out.println("Creating New Order");
                OrderMenu();
                break;

            case 3:
                break;

            case 4:
                break;

            case 5:
                break;

            default:
                System.out.println("Error! Try again.");
                break;
        }
    }

    public static void MenuCard(String from){
        showMenu.displayMenuUI(menuCard);

        switch ((int)Mathf.Clamp(showMenu.inputScanner(),1, 31)) {
            case 1:
                if (from.equals("Order")) {
                    System.out.println("Returning to Order Menu");
                    OrderMenu();
                }else {
                    System.out.println("Returning to Start");
                    StartMenu();
                }break;

            default:

                break;
        }
    }

    public static void OrderMenu(){
        showMenu.displayOrdreUI();

        switch ((int)Mathf.Clamp(showMenu.inputScanner(),0,3)){
            case 1:
                break;

            case 2:
                break;

            case 3:
                System.out.println("Returning to Start");
                StartMenu();
                break;
        }
    }
}
