public class Main {
    private static MenuCard menuCard = null;
    private static UI showMenu = null;
    private static Order currentOrdre = null;

    public static void main(String[] args) {
        menuCard = new MenuCard();
        showMenu = new UI();

        menuCard.loadCard();

        System.out.println("Welcome to Mario's Pizzaria. \nPlease look at the latest options given and type in a number to select. \n");

        StartMenu();
    }

    public static void StartMenu(){
        showMenu.displayStartUI();

        switch (showMenu.inputScanner())
        {
            case 1:
                System.out.println("Showing Menu Card");
                MenuCard("Start");
                break;

            case 2:
                System.out.println("Creating New Ordre");
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
                System.out.println("Cannot interpet input.");
                System.out.println("Try again.");
                StartMenu();
                break;
        }
    }

    public static void MenuCard(String from){
        showMenu.displayMenuUI(menuCard);

            switch (showMenu.inputScanner()) {
                case 0:
                        System.out.println("Returning to Start");
                        StartMenu();
                    break;

                default:
                        System.out.println("Error!");
                        System.out.println("Cannot interpet input.");
                        System.out.println("Try again.");
                        MenuCard(from);
                    break;
            }
    }

    public static void OrdreMenu(){
        if(currentOrdre == null)
            currentOrdre = new Order();

        showMenu.displayOrdreUI();

        switch (showMenu.inputScanner()){
            case 1:
                //Add to Ordrer
                selectToAdd();
                break;

            case 2:
                //Remove from Ordrer
                showMenu.displayCurrentOrder(currentOrdre);

                int choice = showMenu.inputScanner();

                switch (choice){
                    case 0:
                        OrdreMenu();
                        break;

                    default:
                        currentOrdre.removeOrder(choice, 1);
                        OrdreMenu();
                        break;
                }

                break;

            case 3:
                showMenu.displaySure();

                switch (showMenu.inputScanner()){
                    case 1:

                        break;

                    default:
                        OrdreMenu();
                        break;
                }
                break;

            case 4:
                System.out.println("Canceling Order");
                System.out.println("Returning to Start");
                currentOrdre = null;
                StartMenu();
                break;

            default:
                System.out.println("Error!");
                System.out.println("Cannot interpet input.");
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
            System.out.println("Cannot interpet input.");
            System.out.println("Try again.");
            selectToAdd();
        }
    }
}
