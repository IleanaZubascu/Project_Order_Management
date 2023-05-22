package org.example;

import BusinessLogic.ClientBLL;
import BusinessLogic.OrderBLL;
import BusinessLogic.ProductBLL;
import DataAccess.ClientDAO;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Orders;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import com.mysql.cj.xdevapi.Client;

import javax.print.attribute.IntegerSyntax;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {

        //int c= ClientDAO.insertClient(new Clients("Zubascu ILeana","Strada Nucilor 45","0752188801"));
        //Clients client= ClientDAO.findClientById(3);
        //ClientDAO.updateClient(3,"Zubascu Oana",client.getAdresa(),client.getContact());
        //ProductBLL p=new ProductBLL();
        //p.updateProductBLL(1,"Caise",12,30);


       System.out.println(ProductDAO.getAll());

        //String u="13 4";
        //String c;
        //String c= u.valueOf(0)+u.valueOf(1)+u.valueOf(2);

        //String CIDString="13 sgdudsg";
        //Pattern pattern = Pattern.compile("(\\d+)");
        //Matcher matcher = pattern.matcher(CIDString);

        //int c=Integer.parseInt(matcher.group(1));

        //System.out.println(c);



    }
}