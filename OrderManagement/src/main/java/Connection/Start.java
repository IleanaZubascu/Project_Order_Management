package Connection;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Start {
    protected static String findStatement="select * from Product where PID=?;";

    public static void main(String[] args) throws SQLException {

       // StudentBLL studentBll = new StudentBLL();

        //Student student1 = null;

        try {
          //  student1 = studentBll.findStudentById(1245);

        } catch (Exception ex) {
           // LOGGER.log(Level.INFO, ex.getMessage());
        }

        // obtain field-value pairs for object through reflection
       // ReflectionExample.retrieveProperties(student1);

    }

}
