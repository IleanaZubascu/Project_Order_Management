package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

/**
 * Clasa contine metodele necesare de a stoca informatiile din baza de date sub forma de obiecte de tip JTable
 */
public class View {

    /**
     * Metoda creaza un tabel cu toti clientii din baza daza de date
     * @param listaClienti lista cu clientii care trebuie adaugati
     * @return returneaza un tabel de tip JTable cu toti clientii din baza de date
     */
    public JTable tabelClients(ArrayList<Clients> listaClienti)
    {
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("CID");
        model.addColumn("Nume");
        model.addColumn("Adresa");
        model.addColumn("Contact");
        for(Clients client:listaClienti)
        {
            Object[] obj=new Object[4];
            ReflectionClass.retrieveProperties(client);
            obj[0]=client.getCID();
            obj[1]=client.getNume();
            obj[2]=client.getAdresa();
            obj[3]=client.getContact();
            model.addRow(obj);
        }
        JTable ClientTable= new JTable(model);
        return ClientTable;
    }

    /**
     * Metoda creaza un tabel cu toate produsele din baza daza de date
     * @param listaProduse lista cu produsele care trebuie adaugate
     * @return returneaza un tabel de tip JTable cu toate produsele din baza de date
     */
    public JTable tabelProducts(ArrayList<Product> listaProduse)
    {
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("PID");
        model.addColumn("numeProdus");
        model.addColumn("pret");
        model.addColumn("stoc");
        for(Product produs:listaProduse)
        {
            Object[] obj=new Object[4];
            ReflectionClass.retrieveProperties(produs);
            obj[0]=produs.getPID();
            obj[1]=produs.getNumeProdus();
            obj[2]=produs.getPret();
            obj[3]=produs.getStoc();
            model.addRow(obj);
        }
        JTable ProductTable= new JTable(model);
        return ProductTable;
    }
    /**
     * Metoda creaza un tabel cu toate comenzile din baza daza de date
     * @param listaOrders lista cu comenzile care trebuie adaugate
     * @return returneaza un tabel de tip JTable cu toate comenzile din baza de date
     */
    public JTable tabelOrders(ArrayList<Orders> listaOrders)
    {
        DefaultTableModel model= new DefaultTableModel();
        model.addColumn("OID");
        model.addColumn("Nume Client");
        model.addColumn("Nume Produs");
        model.addColumn("cantitate");
        for(Orders order:listaOrders)
        {
            Object[] obj=new Object[4];
            ReflectionClass.retrieveProperties(order);
            obj[0]=order.getOID();
            Product p= ProductDAO.findProductById(order.getPID());
            Clients c= ClientDAO.findClientById(order.getCID());
            obj[2]=p.getNumeProdus();
            obj[1]=c.getNume();
            obj[3]=order.getCantitate();
            model.addRow(obj);
        }
        JTable OrdersTable= new JTable(model);
        return OrdersTable;
    }
}
