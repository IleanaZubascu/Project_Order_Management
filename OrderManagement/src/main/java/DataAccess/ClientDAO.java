package DataAccess;

import Model.Clients;
import Connection.ConnectionFactory;
import com.mysql.cj.xdevapi.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa ClientDAO contine 5 query-uri folosite in metodele implementate pentru a extrage din tabel informatiile necesare in
 * construirea programului
 */


public class ClientDAO {
    protected static final Logger LOGGER = Logger.getLogger(ClientDAO.class.getName());
    private static final String insertStatementString = "INSERT INTO Clients (nume,adresa,contact)"
            + " VALUES (?,?,?)";
    private static final String deleteStatementString="DELETE FROM Clients WHERE CID=?";
    private final static String findStatementString = "SELECT * FROM Clients where CID = ?";
    private final static String updateStatementString="UPDATE clients " +
                                                      "SET Nume =?,adresa=?,contact=? " +
                                                      "WHERE CID = ?";
    private final static String viewStatementString="SELECT * FROM clients";

    /**
     * Metoda retine intr-un ArrayList<Clients> toti clientii din baza de date, se foloseste query-ul viewStatementString, dupa care se
     * retine in rs fiecare client care va fi adaugat in vector.
     * @return clienti-returneaza un ArrayList<Clients> care reprezinta toti clientii din baza de date
     */
    public static ArrayList<Clients> viewClients()
    {
       ArrayList<Clients> clienti=new ArrayList<Clients>();

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(viewStatementString);
            rs = findStatement.executeQuery();

            while(rs.next()) {
                Clients client=new Clients(
                rs.getInt("CID"),
                rs.getString("nume"),
                rs.getString("adresa"),
                rs.getString("contact"));
                clienti.add(client);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:viewClients " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return clienti;

    }

    /**
     * Metoda schimba datele clientului in functie de ID-ul acestuia, conexiunea cu baza de date se face folosind updateStatementString.
     * @param CID ID-ul clientului la care se face modificarea(nu poate fi schimbat)
     * @param newName noul nume al clientului
     * @param newAddress noua adresa a clientului
     * @param newContact noul contact al clientului
     * @return returneaza 1 in caz de succes, 0 daca nu s-a facut update-ul
     */
    public static int updateClient(int CID, String newName, String newAddress,String newContact)
    {
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement updateStatement = null;
        int updateClient=0;
        try {
            updateStatement = dbConnection.prepareStatement(updateStatementString,Statement.RETURN_GENERATED_KEYS);
            updateStatement.setString(1, newName);
            updateStatement.setString(2, newAddress);
            updateStatement.setString(3, newContact);
            updateStatement.setInt(4,CID);

            updateStatement.executeUpdate();
            updateClient=1;
            //ok=1;
            // client = new Clients(clientID, nume, adresa, contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:updateClient" + e.getMessage());
        } finally {
            ConnectionFactory.close(updateStatement);
            ConnectionFactory.close(dbConnection);
        }
        return updateClient;

    }

    /**
     * Metoda sterge un client in functie de ID-ul acestuia.Conexiunea cu baza de date se face folosind String-ul deleteStatementString.
     * @param clientID ID-ul clientului la care se face delete
     * @return returneaza 1 in caz de succes, 0 esec
     */

    public static int deleteClientbyID(int clientID) {
        Clients client = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement deleteStatement = null;
         int ok=0;

        try {
            deleteStatement = dbConnection.prepareStatement(deleteStatementString,Statement.RETURN_GENERATED_KEYS);
            deleteStatement.setInt(1, clientID);
            deleteStatement.executeUpdate();
            ok=1;
           // client = new Clients(clientID, nume, adresa, contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:deleteClientbyID " + e.getMessage());
        } finally {
            ConnectionFactory.close(deleteStatement);
            ConnectionFactory.close(dbConnection);
        }
       return ok;
    }

    /**
     * Aceasta metoda cauta un client dupa id-ul acestuia, prima data se face conectarea cu baza de date mySQL, dupa care se retine in rs rezultatul
     * cautarii.Metoda foloseste query-ul findStatementString.
     * @param clientID contine ID-ul clientului care trebuie cautat
     *
     * @return client returneaza clientul cautat cu informatiile lui(ID,Nume,Adresa,Contact)
     *
     */

    public static Clients findClientById(int clientID) {
        Clients client = null;

        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;

        ResultSet rs = null;
        try {
            findStatement = dbConnection.prepareStatement(findStatementString);
            findStatement.setLong(1, clientID);
            rs = findStatement.executeQuery();
            rs.next();

            String nume = rs.getString("nume");
            String adresa = rs.getString("adresa");
            String contact = rs.getString("contact");
            client = new Clients(clientID, nume, adresa, contact);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findClientById " + e.getMessage());
        } finally {
            ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return client;
    }

    /**
     * Metoda insereaza un obiect de tip Clients in baza de date.Conexiunea s-a facut folosind insertStatementString.
     * @param client reprezinta clientul care va fi adaugat in baza de date
     * @return returneaza ID-ul clientului adaugat in caz de succes, -1 esec
     */

    public static int insertClient(Clients client) {
        Connection dbConnection = ConnectionFactory.getConnection();

        PreparedStatement insertStatement = null;
        int insertedId = -1;
        try {
            insertStatement = dbConnection.prepareStatement(insertStatementString, Statement.RETURN_GENERATED_KEYS);
            insertStatement.setString(1, client.getNume());
            insertStatement.setString(2, client.getAdresa());
            insertStatement.setString(3, client.getContact());
            insertStatement.executeUpdate();

            ResultSet rs = insertStatement.getGeneratedKeys();
            if (rs.next()) {
                insertedId = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "ClientDAO:insertClient " + e.getMessage());
        } finally {
            ConnectionFactory.close(insertStatement);
            ConnectionFactory.close(dbConnection);
        }
        return insertedId;
    }

    /**
     * Metoda a fost implementata pentru interfata in scopul de a afisa ID-ul si numele clientului intr-un ComboBox.
     * @return returneaza un ArrayList<String> care contine ID-ul unui client si numele acestuia
     */
    public static ArrayList<String> listClients()
    {
        ArrayList<String> clients=new ArrayList<String>();
        ArrayList<Clients> cl=viewClients();
        for(Clients c: cl)
        {
            clients.add(c.getCID()+" "+c.getNume());
        }
        return clients;
    }
    /**
     * Metoda a fost implementata pentru interfata in scopul de a afisa toate datele unui client intr-un ComboBox.
     * @return returneaza un ArrayList<String> care contine toate datele unui client
     */
    public static ArrayList<String> listClientsInforamtion()
    {
        ArrayList<String> clients=new ArrayList<String>();
        ArrayList<Clients> cl=viewClients();
        for(Clients c: cl)
        {
            clients.add(c.getCID()+" "+c.getNume()+" "+c.getAdresa()+" "+c.getContact());
        }
        return clients;
    }
    public static ArrayList<Clients> getAll() {
        Clients client = null;
        ArrayList<Clients> list= new ArrayList<>();
        Connection dbConnection = ConnectionFactory.getConnection();
        PreparedStatement findStatement = null;
        try{


            ResultSet rs = null;
            findStatement = dbConnection.prepareStatement(viewStatementString);


            //findStatement.setLong(1, clientID);
            rs = findStatement.executeQuery();
            while(rs.next())
               {   int clientID= rs.getInt("CID");
                   String nume = rs.getString("nume");
                String adresa = rs.getString("adresa");
                String contact = rs.getString("contact");
                list.add(new Clients(clientID,nume,adresa,contact));
               }
            }catch (SQLException e) {
            LOGGER.log(Level.WARNING,"ClientDAO:findClientById " + e.getMessage());
        } finally {
           // ConnectionFactory.close(rs);
            ConnectionFactory.close(findStatement);
            ConnectionFactory.close(dbConnection);
        }
        return list;
    }

}
