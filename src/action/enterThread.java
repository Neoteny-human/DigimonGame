package action;

import java.util.Scanner;

public class enterThread extends Thread {

    public void run(){
        Scanner sc = new Scanner(System.in);
        while(true){
            try{Thread.sleep(1);}
            catch(Exception e){};
            String A = " ";
            A = sc.nextLine();
            if(A == ""){
                break;
            }
        }
    }
}
