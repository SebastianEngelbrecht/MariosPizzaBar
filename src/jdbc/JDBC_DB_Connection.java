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

    private PreparedStatement ps_create_pizzaList;
    private PreparedStatement ps_create_orderList;
    private PreparedStatement ps_create_pizzaIngredients;


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

//    public void getOrderList() throws Exception
//    {
//        try (ResultSet rs = ps_get_orderList.executeQuery())
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
    public void createPizzaList(String name, float price) throws Exception
    {
        ps_create_pizzaList.setString(1, name);
        ps_create_pizzaList.setFloat(2, price);
        if (ps_create_pizzaList.executeUpdate() != 1)
        {
            throw new Exception("Could not create Pizza");
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
//        ps_get_orderList = connection.prepareStatement("");
        ps_create_pizzaList = connection.prepareStatement("INSERT INTO mariospizzabar.pizzalist " +
                "(Name, Price) VALUES (?,?)");
//        ps_create_orderList = connection.prepareStatement("");
//        ps_create_pizzaIngredients = connection.prepareStatement("");
    }
}