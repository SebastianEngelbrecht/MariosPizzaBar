import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import static java.lang.Integer.parseInt;


public class Oversight {
    public static boolean SaveToOversight(Order input) {
        try {
            File file = new File("oversight.csv");

            if (file.createNewFile())
                System.out.println("File created: " + file.getName());
            file.setWritable(true);


            String saveString = "";
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                if (saveString != "")
                    saveString += "\n";
                saveString += scanner.nextLine();
            }
            scanner.close();

            if(input.getTimeStamp() == "")
            {
                System.out.println("Adding Time");
                long startTime = System.currentTimeMillis();
                SimpleDateFormat formatter = new SimpleDateFormat("HH-mm-ss");
                Date date = new Date(startTime);

                input.setTimeStamp(formatter.format(date));
            }

            System.out.println(input.getTimeStamp());

            saveString += "\n" + input.getTimeStamp() + "; ";
            Product[] products = input.getList();
            for (int i = 0; i < products.length; i++) {
                saveString += products[i].getIndex();

                if (i != products.length - 1)
                    saveString += ",";
            }
            saveString += ";";

            FileWriter writer = new FileWriter(file);
            writer.write(saveString);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static Order[] LoadFromOversight() {
        List<String> dataCollection = new ArrayList<>();
        List<Order> result = new ArrayList<>();
        try {
            File pizzaList = new File("oversight.csv");
            Scanner myReader = new Scanner(pizzaList);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(data.length() > 0) {
                    if (data.charAt(0) != 'M') {
                        if (data.charAt(0) != 'N') {
                            String[] dataSplit = data.split(";");

                            Order toAdd = new Order();
                            toAdd.setTimeStamp(dataSplit[0]);
                            for(String intText: dataSplit[1].split(",")){
                                toAdd.addOrder(MenuCard.getProductByIndex(parseInt(intText.charAt(intText.length() - 1)+"")));
                            }

                            result.add(toAdd);
                        }
                    }
                }
            }
            myReader.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


        return result.toArray(new Order[result.size()]);
    }
}
