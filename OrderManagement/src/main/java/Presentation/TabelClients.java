package Presentation;

import DataAccess.ClientDAO;
import Model.Clients;

import javax.swing.*;
import java.util.ArrayList;
/**
 * Clasa pentru interfata afisare clienti
 */

public class TabelClients  extends JFrame {
    public TabelClients()
    {
        ArrayList<Clients> clients= ClientDAO.viewClients();
        View v=new View();
        JTable table=v.tabelClients(clients);
        JScrollPane scrollPane=new JScrollPane(table);
        this.add(scrollPane);
        this.setTitle("Table Clienti");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }
    public static void main(String [] args)
    {
        new TabelClients();
    }
}
