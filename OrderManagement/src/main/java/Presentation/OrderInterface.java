package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa principala a interfetei Order
 */
public class OrderInterface extends JFrame implements ActionListener {

    private JButton buttonView, buttonAdd, buttonEdit,buttonDelete;
    private JFrame fr;
    public OrderInterface()
    {
        fr= new JFrame("Order");
        fr.setSize(500, 300);
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        buttonView = new JButton("VIEW ORDERS");
        buttonView.setBounds(250,100,170,60);
        buttonView.setBackground(new Color(226,238,234));
        fr.add(buttonView);
        buttonView.addActionListener(this);


        buttonAdd = new JButton("ADD ORDER");
        buttonAdd.setBounds(50,100,170,60);
        buttonAdd.setBackground(new Color(226,238,234));
        fr.add(buttonAdd);
        buttonAdd.addActionListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonView)
        {
            new TabelOrders();
        }
        else if(e.getSource()==buttonAdd) {
             new OrderAdd();
        }



    }
    public static void main(String[] args)
    {
        new OrderInterface();
    }
}