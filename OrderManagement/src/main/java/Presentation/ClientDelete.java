package Presentation;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import Model.Clients;
import Model.Orders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Clasa pentru interfata sterge client
 */
public class ClientDelete extends JFrame implements ActionListener {
    private JFrame fr;
    JComboBox clientField= new JComboBox();
    JButton deleteButton= new JButton();

    JLabel errorMesaj= new JLabel();

    public ClientDelete()
    {
        fr= new JFrame("Delete client");
        fr.setSize(400,300 );
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);

        errorMesaj=new JLabel("");
        errorMesaj.setBounds(10,180,200,50);
        errorMesaj.setFont(new Font("A",Font.ITALIC,20));
        fr.add(errorMesaj);

        JLabel client=new JLabel("Select the client you want to delete:");
        client.setBounds(10,10,350,50);
        client.setFont(new Font("A",Font.ITALIC,20));
        fr.add(client);

        clientField.setModel(new DefaultComboBoxModel<String>(ClientDAO.listClients().toArray(new String[0])));
        clientField.setBounds(10,70,200,40);
        clientField.setFont(new Font("A",Font.ITALIC,20));
        clientField.setVisible(true);
        fr.add(clientField);
        clientField.addActionListener(this);

        deleteButton = new JButton("DELETE");
        deleteButton.setBounds(250,180,100,50);
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
                int CID=-1;
                String CIDString=(String)clientField.getSelectedItem();
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(CIDString);
                if(matcher.find()) {
                    CID=Integer.parseInt(matcher.group(1));
                }

                new ClientBLL().deleteClientbyIDBLL(CID);
                errorMesaj.setText("SUCCESS!");

            }catch(Exception ex)
            {
                errorMesaj.setText("Wrong CID!");

            }
        }



    }

    public static void main(String[] args)
    {
        new ClientDelete();
    }
}
