package Connection;

import DataAccess.ClientDAO;
import Model.Clients;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;


public class ReflectionClass {

        public ReflectionClass()
        {

        }


        public static  JTable getTable(ArrayList<?> lista){
            DefaultTableModel model= new DefaultTableModel();

            ArrayList<String> list= (ArrayList<String>) ReflectionClass.retrieveProperties(lista.get(0));
            //header
            for(Object s:list)
            {
                model.addColumn(s);
            }

            for(Object s:lista)
            {
                Object[] o=new Object[ReflectionClass.retrieveProperties(lista.get(0)).size()];
                int j=0;
                for (Field field : s.getClass().getDeclaredFields())
                    {field.setAccessible(true);

                    try {
                        o[j] = field.get(s);
                        j++;

                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
                model.addRow(o);
            }


           // trebuie sa ai header + data;
            //header il poti lua din metoda de mai jos
           // asemanator pt data ( daca faci metoda separata, o sa primeasca un string, si o sa ai for in for ( primul for parcurge elementele din lista primita, al 2 lea for exact ca in metoda de jos extrage valoare din fiecare field)
            //prin for-in-for ul asta tu o sa-ti construisti Jtable
            //returnezi jtable
           JTable table=new JTable(model);
            return table;

        }


    public static ArrayList<?> retrieveProperties(Object object) {
         ArrayList<Object> lista= new ArrayList<>();
        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                lista.add(field.getName());

                System.out.println(field.getName() + "=" + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
        return lista;
    }

}
