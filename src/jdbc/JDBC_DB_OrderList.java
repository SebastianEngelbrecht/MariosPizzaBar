package jdbc;

public class JDBC_DB_OrderList {

    private int id;
    private int pizza;

    public JDBC_DB_OrderList(int id, int pizza)
    {
        this.id = id;
        this.pizza = pizza;
    }

    public int getId()
    {
        return this.id;
    }

    public int getPizza()
    {
        return this.pizza;
    }

    public String toString() {
        return id + ": " + pizza;
    }
}
