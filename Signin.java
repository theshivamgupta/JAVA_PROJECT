import java.io.*;
//import java.util.*;

class Signin{
    String user, pass;
    
    Signin(String user, String pass) {
        this.user = user;
        this.pass = pass;
    }

    boolean checkUser() throws IOException{
        String filepath = "Username.txt";
        //Scanner sc = new Scanner(System.in);
        System.out.println("Checking to see if username already exists....please wait");
        BufferedReader br;
        boolean userExists = false;
        try {
            br = new BufferedReader(new FileReader(filepath));
            String line;
            while((line = br.readLine()) != null) {
                if(line.equals(user)) {
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


boolean checkPassword() throws IOException {
    String filepath = user + ".txt";
    BufferedReader br;
    boolean truePassword = false;
    try {
        br = new BufferedReader(new FileReader(filepath));
        String line;
        while((line = br.readLine()) != null) {
            if(line.equals(pass)) {
                truePassword = true;
                break;
            }
        }
    } catch(FileNotFoundException e) {
        e.printStackTrace();
    } catch(IOException e) {
        e.printStackTrace();
    }

    return truePassword;
    }
}
