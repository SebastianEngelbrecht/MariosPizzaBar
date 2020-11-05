import static java.lang.Float.parseFloat;

public class Product {
    private String name;
    private String description;
    private float price;

    public Product(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = parseFloat(price) * -1;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getDescription()
    {
        return description;
    }
}
