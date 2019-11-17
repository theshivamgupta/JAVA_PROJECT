//import java.util.*;
import java.io.*;

class Flight {
    String board, des, date;
    
    void setter(String board, String des, String date) {
        this.board = board;
        this.des = des;
        this.date = date;
    }



    void printDetails(String board, String des,String date) throws IOException{

        FileInputStream fs =new FileInputStream("flightDetails.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String data ;
            System.out.println("Boarding    Destination      Date          Time");
        while((data = br.readLine())!= null)
        {
            String s[] = data.split(" ");
            
            if(board.equals(s[0]) && des.equals(s[1]) && date.equals(s[2]))
            {
                System.out.println(s[0]+"        "+s[1]+"       "+s[2]+"          "+s[3]);
            }
        } 

    }


    public String[]  searchDetails(String board , String dest, String date,String time )throws Exception
    {
        System.out.println("#");
        FileInputStream fs =new FileInputStream("flightDetails.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(fs));
            String data ;
            String s[];
            while((data = br.readLine())!= null)
            {
                 s = data.split(" ");
                
                if(board.equals(s[0]) && dest.equals(s[1]) && date.equals(s[2]) && time.equals(s[3]))
                    return s;
            } 
            return null;

    }
    
    
    


}
