import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCard {

    private ArrayList<Product> productsList = new ArrayList<>();
    private String dataCollection;

    public void loadCard() {
        try {
            File pizzaList = new File("PizzaList.csv");
            Scanner myReader = new Scanner(pizzaList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();

                if(data.charAt(0) != 'N')
                    dataCollection += data + "//";
            }

            myReader.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        for (String data: dataCollection.split("//")) {
           String[] fromData = data.split(";");

           Product toAdd = new Product(fromData[1],fromData[2],fromData[3]);
           productsList.add(toAdd);
        }
    }

    public Product[] getProductList(){
        Product[] result = new Product[productsList.size()];

        for(int i = 0; i < result.length; i++)
        result[i] = productsList.get(i);

        return result;
    }

    public int getProductSize(){
        return productsList.size();
    }
}
