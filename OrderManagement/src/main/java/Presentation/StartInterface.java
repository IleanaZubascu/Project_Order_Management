package Presentation;

import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clasa principala
 */
public class StartInterface extends JFrame implements ActionListener {

    private JButton buttonClient, buttonProduct, buttonOrder;
    private JFrame principal;
    public StartInterface()
    {
        principal= new JFrame("Order Management");
        principal.setSize(600, 300);
        principal.getContentPane().setBackground(new Color(216,253,98));
        principal.setLayout(null);
        principal.setVisible(true);
        principal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        buttonClient = new JButton("CLIENT");
        buttonClient.setBounds(200,100,170,60);
        buttonClient.setBackground(new Color(226,238,234));
        principal.add(buttonClient);
        buttonClient.addActionListener(this);

        buttonProduct = new JButton("PRODUCT");
        buttonProduct.setBounds(10,100,170,60);
        buttonProduct.setBackground(new Color(226,238,234));
        principal.add(buttonProduct);
        buttonProduct.addActionListener(this);

        buttonOrder = new JButton("ORDER");
        buttonOrder.setBounds(390,100,170,60);
        buttonOrder.setBackground(new Color(226,238,234));
        principal.add(buttonOrder);
        buttonOrder.addActionListener(this);


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonClient)
        {
            new ClientInterface();
        }
        if (e.getSource() == buttonOrder)
        {
            new OrderInterface();
        }
        if (e.getSource() == buttonProduct)
        {
            new ProductInterface();
        }


    }
    public static void main(String[] args)
    {
        new StartInterface();
    }
}
