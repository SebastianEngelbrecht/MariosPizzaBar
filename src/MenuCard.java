import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuCard {

    private static ArrayList<Product> productsList = new ArrayList<>();
    private static final Path pizzaListFile = Paths.get("PizzaList.csv");

    public void loadCard() {
        try {
            File pizzaList = new File(String.valueOf(pizzaListFile));

            Scanner myReader = new Scanner(pizzaList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.charAt(0) != 'N') {

                    String[] fromData = data.split(";");

                    if(fromData[0].charAt(0) == 'n')
                        fromData[0] = fromData[0].split("null")[1];

                    Product toAdd = new Product(fromData[0],fromData[1],fromData[2], fromData[3].charAt(0) + "" +fromData[3].charAt(1));
                    productsList.add(toAdd);
                }
            }

            myReader.close();

        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public boolean saveOrder(Order toSave){
        try {
            String fileName = "orders.csv";
            String saveString = "";

            File file = new File(fileName);

            if (file.createNewFile())
                System.out.println("File created: " + file.getName());

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()){
                saveString += ("\n" + reader.nextLine());
            }

            saveString += "\n";
            for (Product p: toSave.getList()) {
                saveString += ";" + p.getIndex();
            }

            FileWriter writer = new FileWriter(fileName);
            writer.write(saveString);
            writer.close();

        }catch (Exception e){
            System.out.println("An error occurred.");
            e.printStackTrace();
            return false;
        }

        return true;
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

    public static Product getProductByIndex(int index){
        for (Product product: productsList) {
            if(product.getIndex() == index)
                return product;
        }

        return null;
    }
}
