package Presentation;

import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Product;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Clasa pentru interfata afisare produse
 */
public class TabelProducts extends JFrame {
    public TabelProducts()
    {
        ArrayList<Product> products= ProductDAO.viewProducts();
        View v=new View();
        JTable table=v.tabelProducts(products);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("Table Products");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String [] args)
    {
        new TabelProducts();
    }
}

