package BusinessLogic;

import BusinessLogic.validators.*;
import DataAccess.OrderDAO;
import DataAccess.ProductDAO;
import Model.Orders;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Clasa contine validatorii implementati pentru datele unei comenzi.Metodele implementate in aceasta clasa se folosessc la interfata.
 */
public class OrderBLL {
    private List<Validator<Orders>> validators;
    /**
     * Se retine in constructor toti validatorii implementati pentru datele unei comenzi
     */
    public OrderBLL() {
        validators = new ArrayList<Validator<Orders>>();
        validators.add(new CantitateOrderValidator());
        validators.add(new OrderCIDValidator());
        validators.add(new OrderPIDValidator());
    }
    /**
     * Metoda verifica daca ID-ul comenzii este valid, in caz contrar se arunca o exceptie.
     * @param orderID ID-ul comenzii care trebuie cautata
     * @return returneaza un obiect de tip Orders in caz de succes, null in caz de esec
     */
    public Orders findOrderByIdBLL(int orderID) {
        Orders st = OrderDAO.findOrderById(orderID);
        if (st == null) {
            throw new NoSuchElementException("The order with CID =" + orderID + " was not found!");
        }
        return st;
    }
    /**
     * Metoda verifica daca comanda care trebuie adaugat are date valide, in caz contrar se arunca o exceptie.Dca comanda este valida,
     * atunci se scade din stocul produsului adugat
     * @param order comanda care trebuie adaugata
     * @return returneaza metoda din pachetul DataAccess care adauga comanda in baza de date
     */
    public int insertOrderBLL(Orders order) {
        for (Validator<Orders> v : validators) {
            v.validate(order);
        }
       Product p=ProductDAO.findProductById(order.getPID());
        if(order.getCantitate()> p.getStoc())
            throw new NoSuchElementException("The quantity is not valid");
        else
            ProductDAO.updateProduct(order.getPID(),p.getNumeProdus(),p.getPret(),p.getStoc()-order.getCantitate());
        return OrderDAO.insertOrder(order);
    }
    /**
     * Metoda verifica daca ID-ul comenzii care trebuie stearsa este valida, in caz contrar se arunca o exceptie.
     * @param orderID ID-ul comenzii care trebuie stersa
     * @return returneaza metoda din pachetul DataAccess care sterge comanda in functie de ID
     */
    public int deleteOrderbyIDBLL(int orderID)
    {
        Orders order=OrderDAO.findOrderById(orderID);
        int t= 1;
        if (order == null) {
            t=0;
            throw new NoSuchElementException("The order with OID =" + orderID + " was not found, can't be deleted!");
        }

        return OrderDAO.deleteOrderbyID(orderID);
    }
    /**
     * Metoda veridica daca datele comnezii care va fi editata sunt valide.In cazul in care datele nu sunt corecte, se arunca o exceptie.
     * Daca datele sunt valide, atunci se reactualizeaza stocul produsului editat
     * @param OID ID-ul comenzii
     * @param newPID noul produs
     * @param newCID noul client
     * @param newQuantity noua cantitate
     * @return returneaza metoda updateOrder din pachetul DataAccess care editeaza comanda in functie de ID.
     */
    public int updateOrderBLL(int OID, int newPID, int newCID,int newQuantity)
    {
        Orders order=OrderDAO.findOrderById(OID);
        int t= 1;
        if (order == null) {
            t=0;
            throw new NoSuchElementException("The order with OID =" + OID + " was not found, can't be update!");
        }
        else
        {
            Orders o=new Orders(OID,newPID,newCID,newQuantity);
            for (Validator<Orders> v : validators) {
                v.validate(o);
            }
            Product p=ProductDAO.findProductById(newPID);
            if(newQuantity>p.getStoc()+order.getCantitate())
            {
                t=0;
                throw new NoSuchElementException("The quantity is not valid");
            }
            else
                ProductDAO.updateProduct(order.getPID(),p.getNumeProdus(),p.getPret(),p.getStoc()+order.getCantitate()-newQuantity);



        }

        return OrderDAO.updateOrder(OID,newPID,newCID,newQuantity);
    }

}
