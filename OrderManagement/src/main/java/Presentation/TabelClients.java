package Presentation;

import Connection.ReflectionClass;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import Model.Clients;
import Model.Orders;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Clasa pentru interfata afisare clienti
 */

public class TabelClients  extends JFrame {
    public TabelClients()
    {
        //ArrayList<Clients> clients= ClientDAO.viewClients();

        //TODO: AICI modifici in
         JTable table = ReflectionClass.getTable(ClientDAO.getAll());

        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("Table Orders");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String [] args)
    {
        new TabelClients();
    }
}
