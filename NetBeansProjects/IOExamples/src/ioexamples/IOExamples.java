package ioexamples;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.util.Scanner;

public class IOExamples {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BufferedOutputStream out = new BufferedOutputStream(new DataOutputStream(System.out));
        
        String AM = "", name;
        float grade;
        
        System.out.println("Ksekiname me ta dedomena eksetasewn...\n");
        while(true){
            System.out.println("Dwste am foithth: ");
            AM = sc.next();
            if(AM.equals("000000")) break;
            System.out.println("Dwste to zeugari mathima / vathmos\n");
            name = sc.next();
            System.out.println("\n");
            if(name.equals("end")) continue;
            grade = sc.nextFloat();
            System.out.println(AM + " " + name + " " + grade);
        }
    }
    
}
