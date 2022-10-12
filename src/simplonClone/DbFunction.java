package simplonClone;

import java.sql.*;
import java.util.ArrayList;

public class DbFunction {

    public Connection connect_to_db(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname, user, pass);
            if (conn != null) {
                System.out.println("Connection Established");
            } else {
                System.out.println("Connection Failed");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    /*public void create_table(Connection cnx){
        Statement statement;
        try {
            String query = "create table promos(promoId SERIAL,anneescolaire varchar(50),nameclass varchar(200),primary key(promoId));";
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");

        }catch (Exception ex){
            System.out.println(ex);
        }
    }*/

    /*public void create_table(Connection cnx){
        Statement statement;
        try {
            String query = "create table formateure(formateureId SERIAL,nom varchar(50),prenom varchar(50),email varchar(50),password varchar(100),primary key(formateureId),promoId integer REFERENCES promos(promoId) );";
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");

        }catch (Exception ex){
            System.out.println(ex);
        }
    }*/

    /*public void create_table(Connection cnx){
        Statement statement;
        try {
            String query = "create table apprenant(apprenantId SERIAL,nom varchar(50),prenom varchar(50),email varchar(50),password varchar(100),primary key(apprenantId),promoId integer DEFAULT 0,formateurId integer DEFAULT 0);";
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");

        }catch (Exception ex){
            System.out.println(ex);
        }
    }*/

    public void create_table(Connection cnx) {
        Statement statement;
        try {
            String query = "create table brief(briefId SERIAL,nom varchar(50),primary key(briefId),promoId integer ,formateurId integer,distribuer BOOLEAN default false);";
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("Table Created");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }


    public void create_promo(Connection cnx, Promos pr) {
        Statement statement;
        try {
            String query = String.format("insert into promos(anneescolaire,nameclass) values('%s','%s')", pr.getAnneeScolaire(), pr.getNomClasse());
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("\nPromos bien ajouter\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public boolean list_promo(Connection cnx) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = "select * from promos order by promoId";
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next() == false) {
                System.out.println("Aucun Promos\nFirst you need to create promo....");
                return false;
            } else {
                System.out.println("\nLa list des promos");
                do {
                    System.out.println("Promo Id : " + rs.getString("promoId") + ", nom de class : " + rs.getString("nameclass") + ", Annee Scolaire" + rs.getString("anneescolaire"));
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return true;
    }

    public boolean chek(Connection cnx, int id) {
        Statement statement;
        ResultSet rs = null;
        try {

            String query = String.format("select * from promos where promoId = %d", id);
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                return true;
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public void create_formateur(Connection cnx, Formateurs format) {
        Statement statement;
        try {
            String query = String.format("insert into formateure(nom,prenom,email,password,promoId) values('%s','%s','%s','%s',%d)", format.nom, format.prenom, format.email, format.password, format.idPromo);
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("\nFormateure bien ajouter\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void create_app(Connection cnx, Apprenants app) {
        Statement statement;
        try {
            String query = String.format("insert into apprenant(nom,prenom,email,password) values('%s','%s','%s','%s')", app.nom, app.prenom, app.email, app.password);
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("\nApprenants bien ajouter\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void create_brief(Connection cnx,Brief br){
        Statement statement;
        try{
            String query = String.format("insert into brief(nom,promoId,formateurId) values('%s',%d,%d)",br.getNameBrief(),br.getIdPromo(),br.getIdFormateur());
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("\nBrief bien crie\n");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

    public ArrayList<Formateurs> login_formateur(Connection cnx, String email, String password) {
        ArrayList<Formateurs> fr1 = new ArrayList<>();
        Formateurs frr = new Formateurs();
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from formateure where email LIKE '%s' and password LIKE '%s'", email, password);
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next() == false) {
                System.out.println("verify your account");
            } else {
                do {
                    frr.id = rs.getInt("formateureId");
                    frr.nom = rs.getString("nom");
                    frr.prenom = rs.getString("prenom");
                    frr.idPromo = rs.getInt("promoId");
                    frr.email = rs.getString("email");
                    fr1.add(frr);
                } while (rs.next());
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return fr1;
    }

    public ArrayList<Apprenants> login_app(Connection cnx, String email, String pass){
        ArrayList<Apprenants> app = new ArrayList<>();
        Apprenants ap = new Apprenants();
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from apprenant where email LIKE '%s' and password LIKE '%s'", email, pass);
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next() == false){

            }else {
                do{
                    ap.id = rs.getInt("apprenantId");
                    ap.nom = rs.getString("nom");
                    ap.prenom = rs.getString("prenom");
                    ap.email = rs.getString("email");
                    ap.idPromo = rs.getInt("promoId");
                    app.add(ap);
                }while (rs.next());
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        return app;
    }

    public ArrayList<Brief> list_brief_dist(Connection cnx,int id){
        ArrayList<Brief> br = new ArrayList<>();
        Brief br1 = new Brief();
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("SELECT * FROM brief WHERE promoid = %d AND distribuer = TRUE",id);
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next() == false){

            }else {
                do{
                    br1.setNameBrief(rs.getString("nom"));
                    br.add(br1);
                }while (rs.next());
            }

        }catch (Exception ex){
            System.out.println(ex);
        }
        return  br;
    }

    public boolean check_app(Connection cnx, int id) {
        Statement statement;
        ResultSet rs = null;
        try {
            String query = String.format("select * from apprenant where apprenantId = %d", id);
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if (rs.next())
                return true;
        } catch (Exception ex) {
            System.out.println(ex);
        }
        return false;
    }

    public void add_app_to_promo(Connection cnx, Formateurs fr) {
        Statement statement;
        try {
            String query = String.format("update apprenant set promoId = %d ,formateurId = %d", fr.idPromo, fr.id);
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("\napprenant bien ajoute a ma propre promo\n");
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public boolean list_brief(Connection cnx){
        Statement statement;
        ResultSet rs = null;
        try{
            String query = String.format("select * from brief WHERE distribuer = false");
            statement = cnx.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next() == false){
                System.out.println("Aucun Brief");
                return false;
            }else {
                System.out.println("\nLa lsit des brief\n");
                do {
                    System.out.println("Id : "+rs.getInt("briefId")+" , Name : "+rs.getString("nom"));
                }while (rs.next());
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return true;
    }

    public boolean check_brief(Connection cnx,int id){
        Statement statement;
        ResultSet rs = null;
        try{
            String qurey = String.format("select * from brief where briefId = %d AND distribuer = false",id);
            statement = cnx.createStatement();
            rs = statement.executeQuery(qurey);
            if(rs.next() == false)
                return false;

        }catch (Exception ex){
            System.out.println(ex);
        }
        return true;
    }

    public void distri(Connection cnx,int id){
        Statement statement;
        try{
            String query = String.format("update brief set distribuer = true where briefId = %d",id);
            statement = cnx.createStatement();
            statement.executeUpdate(query);
            System.out.println("Brief lancer");
        }catch (Exception ex){
            System.out.println(ex);
        }
    }

}