package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Clasa contine metodele necesare de a stoca informatiile din baza de date sub forma de obiecte de tip JTable
 */
public class View {

    public String[] generateTable(ArrayList<?> list)
    {

        Class<?> clazz = list.get(0).getClass();
        Field[] fields = clazz.getDeclaredFields();
        String[] columnNames = new String[fields.length];
        Object[][] rowData = new Object[list.size()][fields.length];
        for (int i = 0; i < fields.length; i++) {
            columnNames[i] = fields[i].getName();
        }
        for (int i = 0; i < list.size(); i++) {
            Object dataObj = list.get(i);
            for (int j = 0; j < fields.length; j++) {
                fields[j].setAccessible(true);
                try {
                    rowData[i][j] = fields[j].get(dataObj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }


        return columnNames;
    }

    /**
     * Metoda creaza un tabel cu toti clientii din baza daza de date
     * @param listaClienti lista cu clientii care trebuie adaugati
     * @return returneaza un tabel de tip JTable cu toti clientii din baza de date
     */
    public JTable tabelClients(ArrayList<Clients> listaClienti)
    {
        DefaultTableModel model= new DefaultTableModel();

        ArrayList<String> list= (ArrayList<String>) ReflectionClass.retrieveProperties(new Clients());
        ArrayList<Clients> c= ClientDAO.getAll();
        int i=0;
        Object[] obj=new Object[4];
        for(Object s:list)
        {
            model.addColumn(s);
        }
       for(Clients client:c)
        {

            obj[0]=client.getCID();
            obj[1]=client.getNume();
            obj[2]=client.getAdresa();
            obj[3]=client.getContact();
            model.addRow(obj);
            i++;
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

        ArrayList<String> list= (ArrayList<String>) ReflectionClass.retrieveProperties(new Product());
        //ArrayList<Clients> c= ProductDAO.getAll();
        int i=0;
        Object[] obj=new Object[4];
        for(Object s:list)
        {
            model.addColumn(s);
        }
        /*for(Clients client:c)
        {

            obj[0]=client.getCID();
            obj[1]=client.getNume();
            obj[2]=client.getAdresa();
            obj[3]=client.getContact();
            model.addRow(obj);
            i++;
        }*/
        JTable ClientTable= new JTable(model);
        return ClientTable;
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
        JTable OrdersTable= new JTable();

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

        return OrdersTable;
    }


}
