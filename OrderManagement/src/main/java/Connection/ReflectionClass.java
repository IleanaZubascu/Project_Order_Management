package Connection;

import java.lang.reflect.Field;


public class ReflectionClass {
    /**
     * Metoda primeste un obiect si parcurge toate atributele clasei obiectului
     * @param object obiectul la care se parcurge atributele
     */
    public static void retrieveProperties(Object object) {

        for (Field field : object.getClass().getDeclaredFields()) {
            field.setAccessible(true); // set modifier to public
            Object value;
            try {
                value = field.get(object);
                System.out.println(field.getName() + "=" + value);

            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

}
