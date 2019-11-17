//import java.util.*;
import java.io.*;

class Signup {
    String users, passes;
    
    Signup(String user, String pass) {
        users = user;
        passes = pass;
    }

   void print() {
       System.out.println(users + " " + passes);
   }

    boolean checkAvailablity() throws IOException{
        String filepath = "Username.txt";
        //Scanner sc = new Scanner(System.in);
        System.out.println("Checking to see if username already exists....please wait");
        BufferedReader br;
        boolean userExists = false;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null) {
                if(line.equals(users)) {
                    userExists = true;
                    break;
                }
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e) {
            e.printStackTrace();
        }
        //sc.close();
        return userExists;
    }

    void createAccount() throws IOException{
        String filepath = users + ".txt";
        String filepath1 = "Username.txt";
        FileWriter fileWriter = new FileWriter(filepath);
        FileWriter fileWriter2 = new FileWriter(filepath1, true);
        try {
           PrintWriter printWriter = new PrintWriter(fileWriter);
           PrintWriter printWriter2 = new PrintWriter(fileWriter2);
           printWriter.println(users);
           printWriter.println(passes);
           printWriter2.println(users);
           printWriter.close();
           printWriter2.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}