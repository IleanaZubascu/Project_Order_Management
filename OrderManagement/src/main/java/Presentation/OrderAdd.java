package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clasa pentru interfata adauga comanda
 */
public class OrderAdd extends JFrame implements ActionListener {
    private JFrame fr;
    JComboBox clientField= new JComboBox();
    JComboBox produsField= new JComboBox();
    JTextField cantitateField= new JTextField();
    JButton addButton= new JButton();

    JLabel errorMesaj= new JLabel();

    public OrderAdd()
    {
        fr= new JFrame("Add Order");
        fr.setSize(450,500 );
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);

        errorMesaj=new JLabel("");
        errorMesaj.setBounds(30,400,200,50);
        errorMesaj.setFont(new Font("A",Font.ITALIC,20));
        fr.add(errorMesaj);


        JLabel client=new JLabel("ID si nume client:");
        client.setBounds(20,40,200,50);
        client.setFont(new Font("A",Font.ITALIC,20));
        fr.add(client);

        clientField.setModel(new DefaultComboBoxModel<String>(ClientDAO.listClients().toArray(new String[0])));
        clientField.setBounds(200,40,200,40);
        clientField.setFont(new Font("A",Font.ITALIC,20));
        clientField.setVisible(true);
        fr.add(clientField);
        clientField.addActionListener(this);

        JLabel produs=new JLabel("Produs:");
        produs.setBounds(20,150,200,50);
        produs.setFont(new Font("A",Font.ITALIC,20));
        fr.add(produs);

        produsField.setModel(new DefaultComboBoxModel<String>(ProductDAO.listProducts().toArray(new String[0])));
        produsField.setBounds(100,150,300,40);
        produsField.setFont(new Font("A",Font.ITALIC,20));
        produsField.setVisible(true);
        fr.add(produsField);
        produsField.addActionListener(this);

        JLabel cantitate=new JLabel("Cantitate:");
        cantitate.setBounds(20,260,200,50);
        cantitate.setFont(new Font("A",Font.ITALIC,20));
        fr.add(cantitate);

        cantitateField.setBounds(100,260,300,40);
        cantitateField.setFont(new Font("A",Font.ITALIC,20));
        cantitateField.setVisible(true);
        fr.add(cantitateField);
        cantitateField.addActionListener(this);

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
                int CID=-1;
                String CIDString=(String)clientField.getSelectedItem();
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(CIDString);
                if(matcher.find()) {
                    CID=Integer.parseInt(matcher.group(1));
                }

                int PID=-1;
                String PIDString=(String)produsField.getSelectedItem();
                Pattern pattern1 = Pattern.compile("(\\d+)");
                Matcher matcher1 = pattern1.matcher(PIDString);
               if(matcher1.find())
               {
                   PID=Integer.parseInt(matcher1.group(1));
               }


                int cantitate=Integer.parseInt(cantitateField.getText());
                Orders order= new Orders(CID,PID,cantitate);
                new OrderBLL().insertOrderBLL(order);
                errorMesaj.setText("SUCCESS!");

            }catch(Exception ex)
            {
                errorMesaj.setText("Wrong quantity!");

            }

        }



    }
    public static void main(String[] args)
    {
        new OrderAdd();
    }
}

