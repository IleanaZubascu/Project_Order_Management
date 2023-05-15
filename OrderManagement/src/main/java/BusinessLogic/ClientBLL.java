package BusinessLogic;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import BusinessLogic.validators.ClientContactValidator;
import BusinessLogic.validators.ClientNameValidator;
import Model.Clients;
import DataAccess.ClientDAO;

import BusinessLogic.validators.Validator;

/**
 * Clasa contine validatorii implementati pentru datele unui client.Metodele implementate in aceasta clasa se folosessc la interfata.
 */
public class ClientBLL {
    private List<Validator<Clients>> validators;

    /**
     * Se retine in constructor toti validatorii implementati pentru datele unui client
     */
    public ClientBLL() {
        validators = new ArrayList<Validator<Clients>>();
        validators.add(new ClientContactValidator());
        validators.add(new ClientNameValidator());
    }

    /**
     * Metoda verifica daca ID-ul clientului este valid, in caz contrar se arunca o exceptie.
     * @param clientID ID-ul clientului care trebuie cautat
     * @return returneaza un obiect de tip Clients in caz de succes, null in caz de esec
     */
    public Clients findClientByIdBLL(int clientID) {
        Clients st = ClientDAO.findClientById(clientID);
        if (st == null) {
            throw new NoSuchElementException("The client with CID =" + clientID + " was not found!");
        }
        return st;
    }

    /**
     * Metoda verifica daca clientul care trebuie adaugat are date valide, in caz contrar se arunca o exceptie.
     * @param client clientul care trebuie adaugat
     * @return returneaza metoda din pachetul DataAccess care adauga clientul in baza de date
     */

    public int insertClientBLL(Clients client) {
        for (Validator<Clients> v : validators) {
            v.validate(client);
        }
        return ClientDAO.insertClient(client);
    }
    /**
     * Metoda verifica daca ID-ul clientului care trebuie sters este valid, in caz contrar se arunca o exceptie.
     * @param clientID ID-ul clientului care trebuie sters
     * @return returneaza metoda din pachetul DataAccess care sterge clientul in functie de ID
     */
    public int deleteClientbyIDBLL(int clientID)
    {
        Clients c=ClientDAO.findClientById(clientID);
        int t = 1;
        if (c == null) {
            t=0;
            throw new NoSuchElementException("The client with CID =" + clientID + " was not found!");
        }

        return ClientDAO.deleteClientbyID(clientID);
    }

    /**
     * Metoda veridica daca datele clientului care va fi editat sunt valide.In cazul in care datele nu sunt corecte, se arunca o exceptie.
     * @param CID ID-ul clientului
     * @param newName noul nume
     * @param newAddress noua adresa
     * @param newContact noul contact
     * @return returneaza metoda updateClient din pachetul DataAccess care editeaza clientul in functie de ID.
     */
    public int updateClientBLL(int CID, String newName, String newAddress,String newContact)
    {
        Clients client=ClientDAO.findClientById(CID);
        int t = 1;
        if (client == null) {
            throw new NoSuchElementException("The client with CID =" + CID + " was not found!");
        }
        else
        {
            Clients c=new Clients(CID,newName,newAddress,newContact);
            for (Validator<Clients> v : validators) {
                v.validate(c);
            }
        }

        return ClientDAO.updateClient(CID,newName,newAddress,newContact);
    }

}
