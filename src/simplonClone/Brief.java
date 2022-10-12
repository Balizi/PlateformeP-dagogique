package simplonClone;

import java.util.ArrayList;

public class Brief {

    private int idBrief;
    private int idFormateur;
    private int idPromo;
    private String nameBrief;
    private boolean checkDestribu;
    private static ArrayList<Brief> briefs = new ArrayList<Brief>();

    public int getIdFormateur() {
        return idFormateur;
    }

    public int getIdBrief() {
        return idBrief;
    }

    public void setIdBrief(int idBrief) {
        this.idBrief = idBrief;
    }

    public void setIdFormateur(int idFormateur) {
        this.idFormateur = idFormateur;
    }

    public int getIdPromo() {
        return idPromo;
    }

    public void setIdPromo(int idPromo) {
        this.idPromo = idPromo;
    }

    public String getNameBrief() {
        return nameBrief;
    }

    public static ArrayList<Brief> getBriefs() {
        return briefs;
    }

    public void setBriefs(ArrayList<Brief> briefs) {
        this.briefs = briefs;
    }

    public void setNameBrief(String nameBrief) {
        this.nameBrief = nameBrief;
    }

    public boolean isCheckDestribu() {
        return checkDestribu;
    }

    public void setCheckDestribu(boolean checkDestribu) {
        this.checkDestribu = checkDestribu;
    }



}

