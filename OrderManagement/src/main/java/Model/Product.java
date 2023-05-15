package Model;
/**
 * Clasa contine toate datele unui produs asa cum apare in baza de date.
 */
public class Product {
    private int PID;
    private String numeProdus;
    private int pret;
    private int stoc;

    public Product(int PID, String numeProdus, int pret, int stoc) {
        this.PID = PID;
        this.numeProdus = numeProdus;
        this.pret = pret;
        this.stoc = stoc;
    }
    public Product( String numeProdus, int pret, int stoc) {
        this.numeProdus = numeProdus;
        this.pret = pret;
        this.stoc = stoc;
    }

    public int getPID() {
        return PID;
    }

    public String getNumeProdus() {
        return numeProdus;
    }

    public int getPret() {
        return pret;
    }

    public int getStoc() {
        return stoc;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setNumeProdus(String numeProdus) {
        this.numeProdus = numeProdus;
    }

    public void setPret(int pret) {
        this.pret = pret;
    }

    public void setStoc(int stoc) {
        this.stoc = stoc;
    }
    public String toString()
    {
        return "Product{IdProduct:"+this.PID+" NumeProdus:"+this.numeProdus+" Pret:"+this.pret+"lei  Stoc:"+this.stoc+"bucati}";
    }
}
