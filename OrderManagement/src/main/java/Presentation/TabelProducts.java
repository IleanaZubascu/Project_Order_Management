package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Clasa pentru interfata afisare produse
 */
public class TabelProducts extends JFrame {
    public TabelProducts()
    {
        ArrayList<Product> product= ProductDAO.viewProducts();
        JTable table = ReflectionClass.getTable(ProductDAO.getAll());
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("Table Orders");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String [] args)
    {
        new TabelProducts();
    }
}

