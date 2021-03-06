import static java.lang.Integer.parseInt;
import static java.lang.Float.parseFloat;

public class Product {
    private int index;
    private String name;
    private String description;
    private float price;

    public Product(String index, String name, String description, String price) {
        this.index = parseInt(index);
        this.name = name;
        this.description = description;
        this.price = parseFloat(price);
    }

    //region Getters
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public int getIndex() {
        return index;
    }

    public String getDescription()
    {
        return description;
    }
    //endregion
}

