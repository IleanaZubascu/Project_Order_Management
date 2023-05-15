package Model;

/**
 * Clasa contine toate datele unui client asa cum apare in baza de date.
 */
public class Clients {

    private int CID;
    private String nume;
    private String adresa;
    private String contact;
     public Clients(int cid, String nume, String adresa, String contact)
     {
         this.CID=cid;
         this.nume=nume;
         this.adresa=adresa;
         this.contact=contact;

     }
    public Clients( String nume, String adresa, String contact)
    {
        this.nume=nume;
        this.adresa=adresa;
        this.contact=contact;

    }

    public String getNume() {
        return nume;
    }

    public String getContact() {
        return contact;
    }

    public String getAdresa() {
        return adresa;
    }

    public int getCID() {
        return CID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "Client{" +
                "CID=" + CID +
                ", nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
