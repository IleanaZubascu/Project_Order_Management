package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import Model.Clients;
import Model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa pentru interfata adauga produs
 */

public class ProductAdd extends JFrame implements ActionListener {
    private JFrame fr;
    JTextField nameField= new JTextField();
    JTextField addressField= new JTextField();
    JTextField contactField= new JTextField();
    JButton addButton= new JButton();

    JLabel errorMesaj= new JLabel();

    public ProductAdd()
    {
        fr= new JFrame("Add Product");
        fr.setSize(450,500 );
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);

        errorMesaj=new JLabel("");
        errorMesaj.setBounds(30,400,200,50);
        errorMesaj.setFont(new Font("A",Font.ITALIC,20));
        fr.add(errorMesaj);


        JLabel name=new JLabel("Nume produs:");
        name.setBounds(20,40,200,50);
        name.setFont(new Font("A",Font.ITALIC,20));
        fr.add(name);

        nameField.setBounds(200,40,200,40);
        nameField.setFont(new Font("A",Font.ITALIC,20));
        nameField.setVisible(true);
        fr.add(nameField);
        nameField.addActionListener(this);

        JLabel address=new JLabel("Pret:");
        address.setBounds(20,150,200,50);
        address.setFont(new Font("A",Font.ITALIC,20));
        fr.add(address);

        addressField.setBounds(100,150,300,40);
        addressField.setFont(new Font("A",Font.ITALIC,20));
        addressField.setVisible(true);
        fr.add(addressField);
        addressField.addActionListener(this);

        JLabel contact=new JLabel("Cantitate:");
        contact.setBounds(20,260,200,50);
        contact.setFont(new Font("A",Font.ITALIC,20));
        fr.add(contact);

        contactField.setBounds(110,260,250,40);
        contactField.setFont(new Font("A",Font.ITALIC,20));
        contactField.setVisible(true);
        fr.add(contactField);
        contactField.addActionListener(this);

        addButton = new JButton("ADD");
        addButton.setBounds(330,330,70,50);
        addButton.setBackground(new Color(226,238,234));
        fr.add(addButton);
        addButton.addActionListener(this);


        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addButton)
        {

            try
            {
                String nume=nameField.getText();
                int pret=Integer.parseInt(addressField.getText());
                int stoc=Integer.parseInt(contactField.getText());
                Product product= new Product(nume,pret,stoc);
                new ProductBLL().insertProductBLL(product);
                errorMesaj.setText("SUCCESS!");

            }catch(Exception ex)
            {
                errorMesaj.setText("Wrong data!");

            }

        }



    }
    public static void main(String[] args)
    {
        new ProductAdd();
    }
}
