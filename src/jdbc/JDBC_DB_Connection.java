package jdbc;

import java.sql.*;
import java.util.ArrayList;


public class JDBC_DB_Connection implements AutoCloseable {

    private Connection connection;
    private String db_url;
    private String db_user;
    private String db_password;

    private PreparedStatement ps_get_pizzaList;
    private PreparedStatement ps_get_pizzaIngredients;
    private PreparedStatement ps_get_orderList;
    private PreparedStatement ps_get_highest_id_orderList;
    private PreparedStatement ps_get_highest_id_orderList_pizza;

    private PreparedStatement ps_create_pizzaList;
    private PreparedStatement ps_create_orderList;
    private PreparedStatement ps_create_pizzaIngredients;

    private PreparedStatement ps_delete_pizzaList;
    private PreparedStatement ps_delete_orderList;

    private PreparedStatement ps_update_pizzaList;

    private PreparedStatement ps_reorder_pizzaList;

    private PreparedStatement ps_selected_count_orderID;


    public JDBC_DB_Connection(String db_url, String db_user, String db_password) throws SQLException {
        this.db_url = db_url;
        this.db_user = db_user;
        this.db_password = db_password;
        prepareConnection();
    }

    @Override
    public void close() throws Exception {
        connection.close();
    }

    public ArrayList<JDBC_DB_PizzaList> getPizzaList() throws Exception
    {
        ArrayList<JDBC_DB_PizzaList> result = new ArrayList<>();

        try (ResultSet rs = ps_get_pizzaList.executeQuery())
        {
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                float price = rs.getFloat(3);
                result.add(new JDBC_DB_PizzaList(id, name, price));
            }
            return result;
        }
        catch (SQLException e)
        {
            throw new Exception(e);
        }
    }

//    public void getPizzaIngredients() throws Exception
//    {
//        try (ResultSet rs = ps_get_pizzaIngredients.executeQuery())
//        {
//            while (rs.next())
//            {
//
//            }
//        } catch (SQLException e)
//        {
//            throw new Exception(e);
//        }
//    }

    public ArrayList<JDBC_DB_OrderList> getOrderList() throws Exception
    {
        ArrayList<JDBC_DB_OrderList> result = new ArrayList<>();

        try (ResultSet rs = ps_get_orderList.executeQuery())
        {
            while (rs.next())
            {
                int id = rs.getInt(1);
                int pizza = rs.getInt(2);
                result.add(new JDBC_DB_OrderList(id, pizza));
            }
            return result;
        }
        catch (SQLException e)
        {
            throw new Exception(e);
        }
    }

    public int getHighestIdOrderList() throws Exception {
        try (ResultSet rs = ps_get_highest_id_orderList.executeQuery())
        {
            int id = -1;
            while (rs.next())
            {
                id = rs.getInt(1);
            }
            return id;
        }
        catch (SQLException e)
        {
            throw new Exception(e);
        }
    }

    public int getHighestIdOrderListPizza() throws Exception
    {
        try (ResultSet rs = ps_get_highest_id_orderList_pizza.executeQuery())
        {
            int pizzaID = -2;
            while (rs.next())
            {
                pizzaID = rs.getInt(2);
            }
            return pizzaID;
        }
        catch (SQLException e)
        {
            throw new Exception(e);
        }
    }

    public void createOrderList(int id, int pizza) throws Exception
    {
        ps_create_orderList.setInt(1, id);
        ps_create_orderList.setInt(2, pizza);
        if (ps_create_orderList.executeUpdate() != 1)
        {
            throw new Exception("Could not create order");
        }
    }

    public void insertOrderList(int id, int pizza) throws Exception
    {
        ps_create_orderList.setInt(1, id);
        ps_create_orderList.setInt(2, pizza);
        if (ps_create_orderList.executeUpdate() != 1)
        {
            throw new Exception("Could not create order");
        }
    }

    public void createPizzaList(String name, float price) throws Exception
    {
        ps_create_pizzaList.setString(1, name);
        ps_create_pizzaList.setFloat(2, price);
        if (ps_create_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not create pizza");
        }
        reorderPizzaListId();
    }

    public void updatePizzaList(int id ,String name, float price) throws Exception
    {
        ps_update_pizzaList.setString(1, name);
        ps_update_pizzaList.setFloat(2, price);
        ps_update_pizzaList.setInt(3, id);
        ps_update_pizzaList.executeUpdate();
        if (ps_update_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not update pizza");
        }
    }

    public void updatePizzaList(int id, String name) throws Exception
    {
        float price = getPizzaList().get(id - 1).getPrice();
        ps_update_pizzaList.setString(1, name);
        ps_update_pizzaList.setFloat(2, price);
        ps_update_pizzaList.setInt(3, id);
        ps_update_pizzaList.executeUpdate();
        if (ps_update_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not update pizza");
        }
    }

    public void updatePizzaList(int id, float price) throws Exception
    {
        String name = getPizzaList().get(id - 1).getName();
        ps_update_pizzaList.setString(1, name);
        ps_update_pizzaList.setFloat(2, price);
        ps_update_pizzaList.setInt(3, id);
        ps_update_pizzaList.executeUpdate();
        if (ps_update_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not update pizza");
        }
    }

    public void deletePizzaList(String name) throws Exception
    {
        ps_delete_pizzaList.setString(1, name);
        if (ps_delete_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not delete pizza");
        }
        reorderPizzaListId();
    }

    public void deleteOrderList(int id) throws Exception
    {
        ps_delete_orderList.setInt(1, id);
        if (ps_delete_orderList.executeUpdate() != 1)
        {
            throw new Exception("Could not delete order(s)");
        }
    }

    public void reorderPizzaListId() throws Exception
    {
        try {
            for (int i = 1; i <= getPizzaList().size(); i++) {
                ps_reorder_pizzaList.setInt(1, i);
                ps_reorder_pizzaList.setInt(2, getPizzaList().get(i - 1).getId());
                ps_reorder_pizzaList.executeUpdate();
            }
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public int selectedCountOrderID(int i) throws Exception
    {
        ps_selected_count_orderID.setInt(1, i);

        try (ResultSet rs = ps_selected_count_orderID.executeQuery())
        {
            int id = -1;
            while (rs.next()) {
                id = rs.getInt(1);
            }
            return id;
        }
        catch (SQLException e)
        {
            throw new Exception(e);
        }
    }

   /* public void createPizzaIngredients() throws Exception
    {
        try (ResultSet rs = ps_create_pizzaIngredients.executeQuery())
        {
            while (rs.next())
            {

            }
        } catch (SQLException e)
        {
            throw new Exception(e);
        }
    }*/

    /*public void createOrderList() throws Exception
    {
        try (ResultSet rs = ps_create_orderList.executeQuery())
        {
            while (rs.next())
            {

            }
        } catch (SQLException e)
        {
            throw new Exception(e);
        }
    }*/

    private void prepareConnection() throws SQLException
    {
        connection = DriverManager.getConnection(db_url, db_user, db_password);
        ps_get_pizzaList = connection.prepareStatement("SELECT * FROM mariospizzabar.pizzalist");
//        ps_get_pizzaIngredients = connection.prepareStatement("");
        ps_get_orderList = connection.prepareStatement("SELECT * FROM mariospizzabar.order");
        ps_get_highest_id_orderList = connection.prepareStatement("SELECT ID FROM mariospizzabar.order " +
                "ORDER BY ID DESC LIMIT 1");
        ps_get_highest_id_orderList_pizza = connection.prepareStatement("SELECT * FROM mariospizzabar.order " +
                "ORDER BY ID DESC LIMIT 1");

        ps_create_pizzaList = connection.prepareStatement("INSERT INTO mariospizzabar.pizzalist " +
                "(Name, Price) VALUES (?,?)");
        ps_create_orderList = connection.prepareStatement("INSERT INTO mariospizzabar.order " +
                "(ID, pizzalist_id) VALUES (?,?)");
//        ps_create_pizzaIngredients = connection.prepareStatement("");

        ps_delete_pizzaList = connection.prepareStatement("DELETE FROM mariospizzabar.pizzalist " +
                "WHERE Name = ?");
        ps_delete_orderList = connection.prepareStatement("DELETE FROM mariospizzabar.order " +
                "WHERE ID = ?");

        ps_update_pizzaList = connection.prepareStatement("UPDATE mariospizzabar.pizzalist " +
                "SET Name = ?, Price = ? " +
                "WHERE pizzalist_id = ?");

        ps_reorder_pizzaList = connection.prepareStatement("UPDATE mariospizzabar.pizzalist " +
                "SET pizzalist_id = ? " +
                "WHERE pizzalist_id = ?");

        ps_selected_count_orderID = connection.prepareStatement("SELECT COUNT(ID) FROM mariospizzabar.order WHERE ID = ?");
    }
}