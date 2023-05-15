package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clasa pentru interfata editare produs
 */

public class ProductEdit extends JFrame implements ActionListener {
    private JFrame fr;
    JComboBox clientField= new JComboBox();
    JButton deleteButton= new JButton();

    JTextField nameField= new JTextField();
    JTextField addressField= new JTextField();
    JTextField contactField= new JTextField();

    JLabel errorMesaj= new JLabel();

    public ProductEdit()
    {
        fr= new JFrame("Edit product");
        fr.setSize(430,450 );
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);

        errorMesaj=new JLabel("");
        errorMesaj.setBounds(50,330,200,50);
        errorMesaj.setFont(new Font("A",Font.ITALIC,20));
        fr.add(errorMesaj);

        JLabel client=new JLabel("Select the product you want to edit:");
        client.setBounds(10,10,350,50);
        client.setFont(new Font("A",Font.ITALIC,20));
        fr.add(client);

        clientField.setModel(new DefaultComboBoxModel<String>(ProductDAO.listProductsInfo().toArray(new String[0])));
        clientField.setBounds(10,60,350,40);
        clientField.setFont(new Font("A",Font.ITALIC,20));
        clientField.setVisible(true);
        fr.add(clientField);
        clientField.addActionListener(this);

        JLabel name=new JLabel("Nume produs:");
        name.setBounds(20,130,200,50);
        name.setFont(new Font("A",Font.ITALIC,20));
        fr.add(name);

        nameField.setBounds(160,130,200,40);
        nameField.setFont(new Font("A",Font.ITALIC,20));
        nameField.setVisible(true);
        fr.add(nameField);
        nameField.addActionListener(this);

        JLabel address=new JLabel("Pret:");
        address.setBounds(20,190,200,50);
        address.setFont(new Font("A",Font.ITALIC,20));
        fr.add(address);

        addressField.setBounds(80,190,300,40);
        addressField.setFont(new Font("A",Font.ITALIC,20));
        addressField.setVisible(true);
        fr.add(addressField);
        addressField.addActionListener(this);

        JLabel contact=new JLabel("Stoc:");
        contact.setBounds(20,260,200,50);
        contact.setFont(new Font("A",Font.ITALIC,20));
        fr.add(contact);

        contactField.setBounds(80,260,300,40);
        contactField.setFont(new Font("A",Font.ITALIC,20));
        contactField.setVisible(true);
        fr.add(contactField);
        contactField.addActionListener(this);

        deleteButton = new JButton("EDIT");
        deleteButton.setBounds(300,320,100,50);
        deleteButton.setBackground(new Color(226,238,234));
        fr.add(deleteButton);
        deleteButton.addActionListener(this);

        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==deleteButton)
        {
            try
            {
                int PID=-1;
                String CIDString=(String)clientField.getSelectedItem();
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(CIDString);
                if(matcher.find()) {
                    PID=Integer.parseInt(matcher.group(1));
                }

                new ProductBLL().updateProductBLL(PID,nameField.getText(),Integer.parseInt(addressField.getText()),
                                                   Integer.parseInt(contactField.getText()));
                errorMesaj.setText("SUCCESS!");

            }catch(Exception ex)
            {
                errorMesaj.setText("Wrong data!");

            }
        }



    }

    public static void main(String[] args)
    {
        new ProductEdit();
    }
}

