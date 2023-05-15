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
 * Clasa pentru interfata sterge produs
 */
public class ProductDelete extends JFrame implements ActionListener {
    private JFrame fr;
    JComboBox clientField= new JComboBox();
    JButton deleteButton= new JButton();

    JLabel errorMesaj= new JLabel();

    public ProductDelete()
    {
        fr= new JFrame("Delete Product");
        fr.setSize(400,300 );
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);

        errorMesaj=new JLabel("");
        errorMesaj.setBounds(10,180,200,50);
        errorMesaj.setFont(new Font("A",Font.ITALIC,20));
        fr.add(errorMesaj);

        JLabel client=new JLabel("Select the product you want to delete:");
        client.setBounds(10,10,350,50);
        client.setFont(new Font("A",Font.ITALIC,20));
        fr.add(client);

        clientField.setModel(new DefaultComboBoxModel<String>(ProductDAO.listProducts().toArray(new String[0])));
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
                int PID=-1;
                String CIDString=(String)clientField.getSelectedItem();
                Pattern pattern = Pattern.compile("(\\d+)");
                Matcher matcher = pattern.matcher(CIDString);
                if(matcher.find()) {
                    PID=Integer.parseInt(matcher.group(1));
                }

                new ProductBLL().deleteProductbyIDBLL(PID);
                errorMesaj.setText("SUCCESS!");

            }catch(Exception ex)
            {
                errorMesaj.setText("Wrong PID!");

            }
        }



    }

    public static void main(String[] args)
    {
        new ProductDelete();
    }
}

