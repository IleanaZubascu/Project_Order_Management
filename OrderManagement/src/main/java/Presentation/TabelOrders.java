package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Clasa pentru interfata afisare comenzi
 */
public class TabelOrders extends JFrame {
    public TabelOrders()
    {
        ArrayList<Orders> orders= OrderDAO.viewOrders();
        JTable table = ReflectionClass.getTable(OrderDAO.getAll());
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("Table Orders");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String [] args)
    {
        new TabelOrders();
    }
}

