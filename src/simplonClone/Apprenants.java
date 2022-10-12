package simplonClone;

import java.util.ArrayList;
import java.util.Scanner;

public class Apprenants extends Personne {
    private static ArrayList<Apprenants> apprenants = new ArrayList<>();

    public static ArrayList<Apprenants> getApprenants() {
        return apprenants;
    }

    public void setApprenants(ArrayList<Apprenants> apprenants) {
        this.apprenants = apprenants;
    }

    public static void addApp(Apprenants apprenants){
        Apprenants.apprenants.add(apprenants);
    }

    public static Apprenants loginApp(){
        Scanner keyboard = new Scanner(System.in);
        Scanner keyboard1 = new Scanner(System.in);
        System.out.print("Taper Votre Email : ");
        String email = keyboard.next();
        System.out.print("Taper votre password : ");
        String password = keyboard1.nextLine();
        for(Apprenants apprenant : Apprenants.getApprenants()){
            if(apprenant.email.equals(email) && apprenant.password.equals(password)){
                return apprenant;
            }
        }
        return null;
    }

    public static void showBrief(){
        Apprenants.loginApp();
        for (Brief br : Brief.getBriefs()){
            if(br.isCheckDestribu() == true){
                System.out.println("Nom of brief : "+br.getNameBrief());
            }
        }
    }

}
