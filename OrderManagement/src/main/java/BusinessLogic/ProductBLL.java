package BusinessLogic;

import BusinessLogic.validators.*;
import DataAccess.ClientDAO;
import DataAccess.ProductDAO;
import Model.Clients;
import Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Clasa contine validatorii implementati pentru datele unei comenzi.Metodele implementate in aceasta clasa se folosessc la interfata.
 */
public class ProductBLL {
    private List<Validator<Product>> validators;
    /**
     * Se retine in constructor toti validatorii implementati pentru datele unui produs
     */
    public ProductBLL() {
        validators = new ArrayList<Validator<Product>>();
        validators.add(new ProductNameValidator());
        validators.add(new ProductPriceValidator());
        validators.add(new ProductStockValidator());
    }
    /**
     * Metoda verifica daca ID-ul produsului este valid, in caz contrar se arunca o exceptie.
     * @param productID ID-ul produsului care trebuie cautat
     * @return returneaza un obiect de tip Product in caz de succes, null in caz de esec
     */
    public Product findProductByIdBLL(int productID) {
        Product st = ProductDAO.findProductById(productID);
        if (st == null) {
            throw new NoSuchElementException("The product with CID =" + productID + " was not found!");
        }
        return st;
    }
    /**
     * Metoda verifica daca produsul care trebuie adaugat are date valide, in caz contrar se arunca o exceptie.
     * @param product produsul care trebuie adaugat
     * @return returneaza metoda din pachetul DataAccess care adauga produsul in baza de date
     */
    public int insertProductBLL(Product product) {
        for (Validator<Product> v : validators) {
            v.validate(product);
        }
        return ProductDAO.insertProduct(product);
    }
    /**
     * Metoda verifica daca ID-ul produsului care trebuie sters este valid, in caz contrar se arunca o exceptie.
     * @param productID ID-ul produsului care trebuie sters
     * @return returneaza metoda din pachetul DataAccess care sterge produsul in functie de ID
     */
    public int deleteProductbyIDBLL(int productID)
    {
        Product p=ProductDAO.findProductById(productID);
        int t = ProductDAO.deleteProductbyID(productID);
        if (p == null) {
            t=0;
            throw new NoSuchElementException("The product with CID =" + productID + " was not found, can't be deleted!");
        }

        return ProductDAO.deleteProductbyID(productID);
    }
    /**
     * Metoda veridica daca datele produsului care va fi editat sunt valide.In cazul in care datele nu sunt corecte, se arunca o exceptie.
     * @param PID ID-ul produsului
     * @param newNameProduct noul nume al produsului
     * @param newPrice noul pret
     * @param newStock noul stoc
     * @return returneaza metoda updateProduct din pachetul DataAccess care editeaza produsul in functie de ID.
     */
    public int updateProductBLL(int PID, String newNameProduct, int newPrice,int newStock)
    {
        Product p=ProductDAO.findProductById(PID);
        int t=1;
        if (p==null) {
            t=0;
            throw new NoSuchElementException("The product with CID =" + PID + " was not found, can't be update!");
        }
        else
        {
            Product c=new Product(PID,newNameProduct,newPrice,newStock);
            for (Validator<Product> v : validators) {
                v.validate(c);
            }

        }

        return ProductDAO.updateProduct(PID,newNameProduct,newPrice,newStock);
    }
}
