import java.util.ArrayList;

public class Order {
    private ArrayList<Product> list = new ArrayList<>();
    private String timeStamp = "";

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Product[] getList(){
        Product[] result = new Product[list.size()];

        for(int i = 0; i < result.length; i++)
            result[i] = list.get(i);

        return result;
    }

    public void addOrder(Product toAdd){
        list.add(toAdd);
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
}