package simplonClone;

import javax.mail.NoSuchProviderException;
import java.sql.Connection;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {

        //Administrateur admin = new Administrateur();
        //admin.menu();

        //ArrayList<Formateurs> fr = new ArrayList<>();

        //DbFunction db = new DbFunction();
        //Connection cnx = db.connect_to_db("plateforme","postgres","12345");
        //db.create_table(cnx);

        //System.out.println(db.check_brief(cnx,7));

        //db.create_promo(cnx,"2022","Alan Turing");

        //db.list_promo(cnx);

        //System.out.println(db.chek(cnx,1));

        //System.out.println(db.check_app(cnx,2));

        //db.list_brief_dist(cnx,1);

        Mail.send("text","text","test");

    }
}