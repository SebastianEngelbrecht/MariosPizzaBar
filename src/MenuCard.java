import java.io.File;
import java.util.Scanner;
public class MenuCard {

    public void displayMenuCard() {
        try {
            File pizzaList = new File("PizzaList.csv");
            Scanner myReader = new Scanner(pizzaList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }
            myReader.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
