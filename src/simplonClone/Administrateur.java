package simplonClone;


import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;


public class Administrateur extends Personne {

    private ArrayList<Formateurs> formateur = new ArrayList<>();
    private ArrayList<Apprenants> apprenant = new ArrayList<Apprenants>();
    private ArrayList<simplonClone.Promos> Promos = new ArrayList<Promos>();
    private ArrayList<Brief> briefs = new ArrayList<>();

    DbFunction db = new DbFunction();
    Connection cnx = db.connect_to_db("plateforme","postgres","12345");

    public void menu()
    {
        Scanner keyboard = new Scanner(System.in);
        int choice;
        System.out.println("\nBienVenu");
        System.out.println("1. Administrateur.");
        System.out.println("2. Formateur.");
        System.out.println("3. Apprenants.");
        System.out.println("4. Quite");

        do{
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();
        }while (choice < 1 || choice > 4);

        switch (choice)
        {
            case 1: this.administrateurMenu();break;
            case 2: { this.logged_forma(); };break;
            case 3 : {
                /*Apprenants apr = Apprenants.loginApp();
                if(apr != null){
                    for(Brief br :briefs){
                        if(br.isCheckDestribu()){
                            System.out.println("Nom of brief : "+br.getNameBrief());
                        }
                    }
                }*/
                this.logged_app();
            };break;
            case 4:System.exit(0);break;
        }
    }

    public void administrateurMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("\nL'administrateur");
        System.out.println("1. Creation des promos");
        System.out.println("2. Creation des compte pour les formateurs");
        System.out.println("3. Creation des compte pour les apprenants");
        System.out.println("4. La liste des formateures");
        System.out.println("5. la liste des apprenants");
        System.out.println("6. Retour a menu principale");

        int choice;
        do{
            System.out.print("Enter choice: ");
            choice=keyboard.nextInt();
        }while (choice < 0 || choice > 6);

        switch (choice){
            case 1:this.addPromos();break;
            case 2:this.addFormateure();break;
            case 3:this.addApprenants();break;
            case 4:this.listeFormateurs();break;
            case 5:this.listeApprenants();break;
            case 6:this.menu();break;
        }
    }

    public void formateurMenu()
    {
        Scanner sr = new Scanner(System.in);
        System.out.println("\nFormateur "+formateur.get(0).nom);
        System.out.println("1.Ajoute les apprenants a ma propre promo");
        System.out.println("2.Creeation des briefs");
        System.out.println("3.Distribue un briefs");
        System.out.println("4.Show brief");
        System.out.println("5.Retour a menu principale");
        System.out.println("6. Quite");
        int choice;
        do{
            System.out.print("Enter choice: ");
            choice=sr.nextInt();
        }while (choice < 0 || choice > 6);
        switch (choice){
            case 1 : this.addAppToPromo();break;
            case 2 : this.newBrief();break;
            case 3 : this.distribueBrifeToPromo();break;
            case 4 : {
                for(Brief br : briefs){
                    System.out.println("Name : "+br.getNameBrief()+", check : "+br.isCheckDestribu());
                }
            };break;
            case 5 : this.menu();break;
            case 6 : System.exit(0);break;
            default:
        }
    }

    public void addAppToPromo(){
        Scanner keyboard = new Scanner(System.in);
        Formateurs fr = new Formateurs();
        fr.nom = formateur.get(0).nom;
        fr.email = formateur.get(0).email;
        fr.prenom = formateur.get(0).prenom;
        fr.id = formateur.get(0).id;
        fr.idPromo = formateur.get(0).idPromo;
        System.out.print("Taper Id de l'apprenant : ");
        int id = keyboard.nextInt();
        if(db.check_app(cnx,id)){
            db.add_app_to_promo(cnx,fr);
            this.formateurMenu();
        }else {
            System.out.println("\nce Apprenants n'existe pas\n");
            this.formateurMenu();
        }
    }

    public void addFormateure()
    {
        if(!db.list_promo(cnx))
        {
            this.administrateurMenu();
            return;
        }
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        Formateurs fr = new Formateurs();
        System.out.print("\nTaper Nom de formateure : ");
        fr.nom = sr.next();
        System.out.print("Taper Prenom de formateure : ");
        fr.prenom = sr.next();
        System.out.print("Taper Email de formateure : ");
        fr.email = sr.next();
        System.out.print("Taper Password de formateure : ");
        fr.password = sr1.nextLine();
        System.out.print("Taper id de la promos : ");
        int idPromos=sr.nextInt();
        if(!db.chek(cnx,idPromos)){
            System.out.println("\nCette Promo N'existe Pas\n");
            return;
        }
        fr.idPromo = idPromos;
        db.create_formateur(cnx,fr);
        this.administrateurMenu();
    }

    public void addApprenants(){
        Scanner sr = new Scanner(System.in);
        Scanner sr1 = new Scanner(System.in);
        Apprenants Apprenant = new Apprenants();
        System.out.print("\nTaper le nom de l'apprenant : ");
        Apprenant.nom=sr1.next();
        System.out.print("Taper le prenom de l'apprenant : ");
        Apprenant.prenom=sr1.next();
        System.out.print("Taper le email de l'apprenant : ");
        Apprenant.email=sr1.next();
        System.out.print("Taper le password de l'apprenant : ");
        Apprenant.password=sr1.next();

        db.create_app(cnx,Apprenant);
        this.administrateurMenu();
    }

    public void addPromos(){
        Promos pr = new Promos();
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard1 = new Scanner(System.in);
        System.out.print("\nTaper annee scolaire : ");
        pr.setAnneeScolaire(keyboard.next());
        System.out.print("Taper nom de la classe : ");
        pr.setNomClasse(keyboard1.nextLine());
        db.create_promo(cnx,pr);
        this.administrateurMenu();
    }

    public void listeFormateurs()
    {
        this.administrateurMenu();
    }

    public void listeApprenants()
    {
        this.administrateurMenu();
    }

    public void newBrief(){
        Brief br = new Brief();
        Scanner sr = new Scanner(System.in);
        System.out.print("Taper le nom de brief : ");
        br.setNameBrief(sr.nextLine());
        br.setIdFormateur(formateur.get(0).id);
        br.setIdPromo(formateur.get(0).idPromo);

        db.create_brief(cnx,br);

        this.formateurMenu();
    }

    public void distribueBrifeToPromo(){
        if(!db.list_brief(cnx)){
            System.out.println("Aucun Brief");
            return;
        }
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Taper Id de Brief : ");
        int idd = keyboard.nextInt();
        if(!db.check_brief(cnx,idd)){
            System.out.println("");
            return;
        }
        db.distri(cnx,idd);
        this.formateurMenu();
    }

    public void logged_forma(){
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard1 = new Scanner(System.in);
        System.out.print("\nEntre your mail : ");
        String email = keyboard.next();
        System.out.print("Entre your password : ");
        String pass = keyboard1.nextLine();
        formateur = db.login_formateur(cnx,email,pass);
        if(formateur != null)
            this.formateurMenu();
        else
            this.addAppToPromo();
    }

    public void logged_app(){
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard1 = new Scanner(System.in);
        System.out.print("\nEntre your email : ");
        String email = keyboard.next();
        System.out.print("Entre your password : ");
        String pass = keyboard1.nextLine();
        apprenant = db.login_app(cnx, email,pass);
        //System.out.println(apprenant.get(0).idPromo);

        briefs = db.list_brief_dist(cnx,apprenant.get(0).idPromo);
        for (Brief br : briefs){

            System.out.println("Name of brief : "+br.getNameBrief());
        }
    }

}

