package Presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Clasa principala pentru interfata produs
 */

public class ProductInterface extends JFrame implements ActionListener {

    private JButton buttonView, buttonAdd, buttonEdit,buttonDelete;
    private JFrame fr;
    public ProductInterface()
    {
        fr= new JFrame("Product");
        fr.setSize(600, 300);
        fr.getContentPane().setBackground(new Color(216,253,98));
        fr.setLayout(null);
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);



        buttonView = new JButton("VIEW PRODUCTS");
        buttonView.setBounds(70,50,170,60);
        buttonView.setBackground(new Color(226,238,234));
        fr.add(buttonView);
        buttonView.addActionListener(this);

        buttonEdit = new JButton("EDIT PRODUCT");
        buttonEdit.setBounds(330,50,170,60);
        buttonEdit.setBackground(new Color(226,238,234));
        fr.add(buttonEdit);
        buttonEdit.addActionListener(this);

        buttonAdd = new JButton("ADD PRODUCT");
        buttonAdd.setBounds(70,150,170,60);
        buttonAdd.setBackground(new Color(226,238,234));
        fr.add(buttonAdd);
        buttonAdd.addActionListener(this);

        buttonDelete = new JButton("DELETE PRODUCT");
        buttonDelete.setBounds(330,150,170,60);
        buttonDelete.setBackground(new Color(226,238,234));
        fr.add(buttonDelete);
        buttonDelete.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==buttonView)
        {
            new TabelProducts();
        }
        else if(e.getSource()==buttonAdd)
        {
            new ProductAdd();
        }
        else if(e.getSource()==buttonDelete)
        {
            new ProductDelete();
        }else if(e.getSource()==buttonEdit)
        {
            new ProductEdit();
        }


    }
    public static void main(String[] args)
    {
        new ProductInterface();
    }
}

