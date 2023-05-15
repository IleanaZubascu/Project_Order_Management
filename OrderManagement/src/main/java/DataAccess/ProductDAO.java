package DataAccess;

import Model.Clients;
import Model.Product;
import Connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa ProductDAO contine 5 query-uri folosite in metodele implementate pentru a extrage din tabel informatiile necesare in
 * construirea programului.
 */
public class ProductDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO Product (numeProdus,pret,stoc)"
            + " VALUES (?,?,?)";
    private final static String findStatementString = "SELECT * FROM Product where PID = ?";
    private final static String deleteStatementString="DELETE from Product where PID = ?";

    private final static String updateStatementString="UPDATE product " +
                                                      "SET numeProdus=?,pret=?,stoc=? " +
                                                      "WHERE PID=?";
    private final static String viewStatementString="SELECT * FROM product";

    /**
     * Metoda retine intr-un ArrayList<Product> tote produsele din baza de date, se foloseste query-ul viewStatementString, dupa care se
     * retine in rs fiecare produs care va fi adaugat in vector.
     * @return products-returneaza un ArrayList<Product> care reprezinta toate produsele din baza de date
     */
    public static ArrayList<Product> viewProducts()
    {
        ArrayList<Product> products=new ArrayList<Product>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                Product product=new Product(
                        rs.getInt("PID"),
                        rs.getString("numeProdus"),
                        rs.getInt("pret"),
                        rs.getInt("stoc"));
                products.add(product);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:viewClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return products;

    }
    /**
     * Metoda schimba datele produslui in functie de ID-ul acestuia, conexiunea cu baza de date se face folosind updateStatementString.
     * @param PID ID-ul produsului la care se face modificarea(nu poate fi schimbat)
     * @param newNameProduct noul nume al produsului
     * @param newPrice noul pret al produsului
     * @param newStock noul stoc al produsului
     * @return returneaza 1 in caz de succes, 0 daca nu s-a facut update-ul
     */
    public static int updateProduct(int PID, String newNameProduct, int newPrice,int newStock)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int ok=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, newNameProduct);
            updateStatement.setInt(2, newPrice);
            updateStatement.setInt(3, newStock);
            updateStatement.setInt(4,PID);

            updateStatement.executeUpdate();
            ok=1;
            // client = new Clients(clientID, nume, adresa, contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:updateProduct" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return ok;

    }
    /**
     * Metoda sterge un produs in functie de ID-ul acestuia.Conexiunea cu baza de date se face folosind String-ul deleteStatementString.
     * @param productID ID-ul produsului la care se face delete
     * @return returneaza 1 in caz de succes, 0 esec
     */
    public static int deleteProductbyID(int productID) {
        //Clients client = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
        int ok=0;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString,Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, productID);
            deleteStatement.executeUpdate();
            ok=1;
            // client = new Clients(clientID, nume, adresa, contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:deleteProductbyID " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
         return ok;
    }

    /**
     * Aceasta metoda cauta un produs dupa id-ul acestuia, prima data se face conectarea cu baza de date mySQL, dupa care se retine in rs rezultatul
     * cautarii.Metoda foloseste query-ul findStatementString.
     * @param productID contine ID-ul produsului care trebuie cautat
     * @return produs returneaza produsul cautat cu informatiile lui(ID,Nume produs,Pret,Stoc)
     */
    public static Product findProductById(int productID) {
        Product produs = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;

        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, productID);
            rs = findStatement.executeQuery();
            rs.next();

            String numeProdus = rs.getString("numeProdus");
            int pret = rs.getInt("pret");
            int stoc = rs.getInt("stoc");
            produs = new Product(productID, numeProdus, pret, stoc);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ProductDAO:findProductById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return produs;
    }
    /**
     * Metoda insereaza un obiect de tip Product in baza de date.Conexiunea s-a facut folosind insertStatementString.
     * @param produs reprezinta produsul care va fi adaugat in baza de date
     * @return returneaza ID-ul produsului adaugat in caz de succes, -1 esec
     */
    public static int insertProduct(Product produs) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, produs.getNumeProdus());
            insertStatement.setInt(2, produs.getPret());
            insertStatement.setInt(3, produs.getStoc());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ProductDAO:insertProduct " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }
    /**
     * Metoda a fost implementata pentru interfata in scopul de a afisa ID-ul,numele produsului si stocul acestuia intr-un ComboBox.
     * @return returneaza un ArrayList<String> care contine ID-ul,numele produsului si stocul acestuia
     */
    public static ArrayList<String> listProducts()
    {
        ArrayList<String> products=new ArrayList<String>();
        ArrayList<Product> cl=viewProducts();
        for(Product c: cl)
        {
            products.add(c.getPID()+" "+c.getNumeProdus()+" "+c.getStoc());
        }
        return products;
    }
    /**
     * Metoda a fost implementata pentru interfata in scopul de a afisa toate datele unui produs intr-un ComboBox.
     * @return returneaza un ArrayList<String> care contine toate datele unui produs
     */
    public static ArrayList<String> listProductsInfo()
    {
        ArrayList<String> products=new ArrayList<String>();
        ArrayList<Product> cl=viewProducts();
        for(Product c: cl)
        {
            products.add(c.getPID()+" "+c.getNumeProdus()+" "+c.getPret()+" "+c.getStoc());
        }
        return products;
    }

}
