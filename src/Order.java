import jdbc.JDBC_DB_Connection;
import jdk.nashorn.internal.scripts.JD;

import java.util.ArrayList;

public class Order {
    private ArrayList<Product> list = new ArrayList<>();
    private String timeStamp;
    private JDBC_DB_Connection connection;

    public void setTimeStamp(String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getTimeStamp()
    {
        return timeStamp;
    }

    public Product[] getList(){
        Product[] result = new Product[list.size()];

        for(int i = 0; i < result.length; i++)
            result[i] = list.get(i);

        return result;
    }

    public void addOrder(JDBC_DB_Connection connection, int id, int pizza) throws Exception {
        this.connection = connection;

        if (id == -1 && connection.getHighestIdOrderListPizza() == -2)
        {
            connection.createOrderList(1, pizza);
        }
        else if (connection.getHighestIdOrderListPizza() == -1)
        {
            int tempID = connection.getHighestIdOrderList();
            connection.insertOrderList(tempID, pizza);
        }
        else if (id > (-1) && connection.getHighestIdOrderListPizza() != -1)
        {
           int highId = connection.getHighestIdOrderList();
           connection.createOrderList(highId, pizza);
        }
        else
        {
            throw new Exception("-1");
        }
    }

    public void removeOrder(int index, int amount){
        Product[] toRemoveList = new Product[amount];
        int i = 0;

        for (Product product: list) {
            if (product.getIndex() == index) {
                toRemoveList[i] = product;
                i++;
            }
        }

        for (i = 0; i < amount; i++)
            list.remove(toRemoveList[i]);
    }

    @Override
    public String toString()
    {
        String result = "";
        for (Product product: list)
        {
            result += product.getName() + ", " + product.getDescription() + "\n";
        }
        return result;
    }
}