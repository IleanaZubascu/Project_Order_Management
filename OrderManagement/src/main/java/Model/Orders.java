package Model;

import java.util.ArrayList;
/**
 * Clasa contine toate datele unei comenzi asa cum apare in baza de date.
 */
public class Orders {
    private int OID;
    private int CID;
    private int PID;

    private int cantitate;

    public Orders(int OID, int CID, int PID, int cantitate) {
        this.OID = OID;
        this.CID = CID;
        this.PID = PID;
        this.cantitate = cantitate;
    }

    public Orders(int CID, int PID, int cantitate) {

        this.CID = CID;
        this.PID = PID;
        this.cantitate = cantitate;
    }

    public int getOID() {
        return OID;
    }

    public int getCID() {
        return CID;
    }

    public int getPID() {
        return PID;
    }

    public int getCantitate() {
        return cantitate;
    }

    public void setOID(int OID) {
        this.OID = OID;
    }

    public void setCID(int CID) {
        this.CID = CID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public void setCantitate(int cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "OID=" + OID +
                ", CID=" + CID +
                ", PID=" + PID +
                ", total=" + cantitate +
                '}';
    }
}
