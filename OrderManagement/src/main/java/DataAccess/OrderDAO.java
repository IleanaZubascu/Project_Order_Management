package DataAccess;

import Model.Clients;
import Model.Orders;
import Connection.ConnectionFactory;
import Model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa OrderDAO contine 5 query-uri folosite in metodele implementate pentru a extrage din tabel informatiile necesare despre o comanda in
 * construirea programului.
 */
public class OrderDAO {
    protected static final Logger LOGGER = Logger.getLogger(DataAccess.ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO orders (PID,CID,cantitate)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM orders where OID = ?";

    private final static String deleteStatementString="DELETE from orders where OID=?";

    private final static String updateStatementString="UPDATE orders " +
                                                      "SET PID =?,CID=?,cantitate=? " +
                                                      "WHERE OID = ?";
    private final static String viewStatementString="SELECT * FROM orders";
    /**
     * Metoda retine intr-un ArrayList<Product> tote comenzile din baza de date, se foloseste query-ul viewStatementString, dupa care se
     * retine in rs fiecare comanda care va fi adaugata in vector.
     * @return orders-returneaza un ArrayList<Product> care reprezinta toate comenzile din baza de date
     */
    public static ArrayList<Orders> viewOrders()
    {
        ArrayList<Orders> orders=new ArrayList<Orders>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                Orders order=new Orders(
                        rs.getInt("OID"),
                        rs.getInt("CID"),
                        rs.getInt("PID"),
                        rs.getInt("cantitate"));
                orders.add(order);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:viewClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return orders;

    }
    /**
     * Metoda schimba datele comenzii in functie de ID-ul acestuia, conexiunea cu baza de date se face folosind updateStatementString.
     * @param OID ID-ul produsului la care se face modificarea(nu poate fi schimbat)
     * @param newPID noul produs al comenzii
     * @param newCID noul client al comenzii
     * @param newQuantity noua cantitate a comenzii
     * @return returneaza 1 in caz de succes, 0 daca nu s-a facut update-ul
     */
    public static int updateOrder(int OID, int newPID, int newCID,int newQuantity)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int ok=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
            updateStatement.setInt(1, newPID);
            updateStatement.setInt(2, newCID);
            updateStatement.setInt(3, newQuantity);
            updateStatement.setInt(4,OID);

            updateStatement.executeUpdate();
            ok=1;

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:updateOrder" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return ok;

    }
    /**
     * Metoda sterge o comanda in functie de ID-ul acestuia.Conexiunea cu baza de date se face folosind String-ul deleteStatementString.
     * @param OID ID-ul comenzii la care se face delete
     * @return returneaza 1 in caz de succes, 0 esec
     */

    public static int deleteOrderbyID(int OID) {

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int ok=0;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString,Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, OID);
            deleteStatement.executeUpdate();
            ok=1;;
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:deleteOrderbyID " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
        return ok;

    }
    /**
     * Aceasta metoda cauta o comanda dupa id-ul acestuia, prima data se face conectarea cu baza de date mySQL, dupa care se retine in rs rezultatul
     * cautarii.Metoda foloseste query-ul findStatementString.
     * @param orderID contine ID-ul comenzii care trebuie cautata
     * @return order returneaza comanda cautat acu informatiile ei(OID, CID, PID, cantitate)
     */
    public static Orders findOrderById(int orderID) {
        Orders order = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;

        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, orderID);
            rs = findStatement.executeQuery();
            rs.next();

            int productID = rs.getInt("PID");
            int clientID = rs.getInt("CID");
            int cantitate = rs.getInt("cantitate");
            order = new Orders(orderID, productID,clientID,cantitate);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:findOrderById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return order;
    }
    /**
     * Metoda insereaza o comanda de tip Orders in baza de date.Conexiunea s-a facut folosind insertStatementString.
     * @param order reprezinta comanda care va fi adaugata in baza de date
     * @return returneaza ID-ul comenzii adaugate in caz de succes, -1 esec
     */
    public static int insertOrder(Orders order) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setInt(1, order.getPID());
            insertStatement.setInt(2, order.getCID());
            insertStatement.setInt(3, order.getCantitate());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "OrderDAO:insertOrder " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    public static ArrayList<Orders> getAll() {
        ArrayList<Orders> list= new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        try{


            ResultSet rs = null;
            findStatement = dbConnection.prepareStatement(viewStatementString);


            //findStatement.setLong(1, clientID);
            rs = findStatement.executeQuery();
            while(rs.next())
            {   int orderID= rs.getInt("OID");
                int CID = rs.getInt("CID");
                int PID = rs.getInt("PID");
                int cantitate = rs.getInt("cantitate");
                list.add(new Orders(orderID,CID,PID,cantitate));
            }
        }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"OrderDAO:viewAll " + e.getMessage());
        } finally {
            // ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

}