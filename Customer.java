import java.io.*;
import java.util.*;
import java.lang.*;

class Customer implements Runnable { // implements runnable to access interface
    static int count =0;
    public void operations() throws Exception{
        boolean success = false; //boolean success(if true then)
        Scanner sc = new Scanner(System.in);
        System.out.println("Are you a existing user?");
        System.out.println("1-yes 0-no");
        int option = sc.nextInt();
        String user=null,pass=null;
        if(option == 0) {
            System.out.println("ENTER USERNAME:");
             String u = sc.next();
             user =u;
            System.out.println("ENTER PASSWORD:");
             String p = sc.next();
             pass = p;
            if(pass.length() < 8) {
                System.out.println("TRY AGAIN..."); // only when password length greater than 8.
            } else {
                Signup sp = new Signup(user, pass); // signup constructor is called with username and password
                if(!sp.checkAvailablity()) {
                    sp.createAccount();
                    System.out.println("SIGNED UP SUCCESSFULLY");
                    success = true;
                } else {
                    System.out.println("USERNAME ALREADY EXISTS...PLEASE TRY AGAIN!");
                }
            }
        } else if(option == 1) {
            Console c =System.console(); // console is called to prevent showing of the password
            System.out.println("ENTER USERNAME:");
             user = sc.next();
            
             pass = String.valueOf(c.readPassword("Enter Password"));
            Signin si = new Signin(user, pass); // signup constructor is called
            if(si.checkUser() && si.checkPassword()) {
                System.out.println("SIGNED IN SUCCESSFULLY..");
                success = true;
            } else {
                System.out.println("INVALID PASSWORD OR USERNAME");
            }
        }

        if(success) { // only works when signed in or signed up is successful
            
            do{
                System.out.println("1-Book a Flight\n2-Cancel a Flight\n3-Print Ticket\n4-check History"); // options for choice
            System.out.println("Enter choice:");
            int sch = sc.nextInt();
            switch(sch)
            {
                case 1:
                       System.out.println("DO YOU WANT TO BOOK A FLIGHT:");
                       System.out.println("1-yes 0-no");
                       int choice = sc.nextInt();
                       if(choice == 0) { //if else choice
                           System.out.println("THANK YOU FOR VISITING :)");
                       }
                        else if(choice == 1) {
                           System.out.println("WELCOME TO OUR WEBSITE!!");
                           System.out.println("ENTER BOARDING:");
                           String board = sc.next();                       //enter boarding place
                           System.out.println("ENTER DESTINATION:");
                           String des = sc.next();                         //enter destination place
                           Flight fl = new Flight();
                           //fl.setter(board, des);
                           System.out.println("Enter date");
                           String date = sc.next();
                           fl.printDetails(board, des, date);
                           System.out.println("ENTER YOUR PREFERRED TIME:");         //entering preferred time
                           String time = sc.next();
                           System.out.println("*");
                           String s[] = fl.searchDetails(board, des, date,time);        //calling a method that searches for the details
                           System.out.println("*");
                           System.out.println("price = "+s[4]);
                           System.out.println("Do you want to confirm booking(T/F)");       //answer in boolean to confirm the booking
                           boolean confirmBooking = sc.nextBoolean();
                            
           
                           if(confirmBooking)
                           {
                               System.out.println("Enter nUmber of passenger");
                               int passenger = sc.nextInt();                       //entering number of ticket to be booked
                               int p = passenger;
                               if(passenger<0)
                                   throw new BookingException("wrong Input");            //throwing custom exception a custom output
                               while(passenger-- >0){    
                               System.out.println("------BOOKIng CONFIRMED-------");
                               System.out.print("Enter Name :");
                               String name = sc.next();
                               System.out.print("Enter phone number :");
                               int phone = sc.nextInt();
                               if(String.valueOf(phone).length()<10)
                                   throw new BookingException("Wrong input");            //only to do when the phone number is 10 digits
                               System.out.println(user);
                               String filepath = user+".txt";
                               String t="";
                               //FileWriter writer = new FileWriter(filepath, true);
                               //PrintWriter printWriter = new PrintWriter(writer);
                               t += "------------------------------------------------------\n";
                               t += "Booking id :"+user+"@"+count+++"\n";
                               t += "Name :"+name+"\n";                                //adding the flight details to a string t
                               t += "phone no:"+phone+"\n";
                               t += "flight no. :"+s[5]+"\n";
                              
                               t += "Destination = "+s[1]+"                        source="+s[0]+"\n";
                               t += "date = "+s[2]+"             time = "+s[3]+"              price="+s[4]+"\n";
           
                               FileOutputStream fo =new FileOutputStream(filepath,true);      //adding the flight bill to the user.txt file to be used later
                                                                                                    
                               fo.write(t.getBytes());
                               }
           
                            System.out.println("total Bill = "+p*Integer.parseInt(s[4]));   //calculating the total price of the bill
           
                           }
                            else {
                                System.out.println("Thank you for visiting");
                            }   
                       } 
                       else {
                          System.out.println("WRONG INPUT");
                       }
                       break;

               case 2:                                                     //case 2 canceling the ticket
                    System.out.println("Enetr booking ID :");
                    String id = sc.next();                                //enter the booking id to cancel the ticket
                    FileInputStream fs = new FileInputStream(user+".txt");    // entering fiepath
                    BufferedReader br = new BufferedReader(new InputStreamReader(fs));         //bufferedReader is called
                    String data = null;
                    String d="";
                    boolean ok = false;
                    while((data=br.readLine())!=null)
                    { 
                        if(!(data.contains(id)) && !ok)
                        {
                            d += data+"\n";
                            continue;          
                        }
                        else
                            ok = true;

                            if((ok && !data.equals("------------------------------------------------------")))
                            {
                            }
                            else
                            {
                                d += data+"\n";
                                ok =false;
                            }
                        }
                    
                    FileOutputStream wr = new FileOutputStream(user+".txt");   //overwriting the contains of the file to change the booking tickets
                        wr.write(d.getBytes());                                //writing the contains in the file
                        break;
            case 3:
            System.out.println("Enetr booking id");
            String bookingid = sc.next();
            try{
                printTicket(bookingid, user);                                 //printing the tickets of the user and it total bill
            }catch(IOException e)                        
            {
                System.out.println("Printing error");                       //try-catch is used
            }
            break;
            case 4:

            default:
            break;
        }
    }while(true);           
               
    }
    sc.close();
    }

    public  static void  printTicket(String bookingid,String user) throws IOException{
        String filepath = user+".txt";
      
        FileInputStream fileReader = new FileInputStream(filepath);
        BufferedReader br;
            br = new BufferedReader(new InputStreamReader(fileReader));
            String line;
            String d = "";
            boolean ok = false;
            while((line = br.readLine())!=null)
            {
                                                                                 //method to print ticket
                if(line.contains(bookingid))
                {
                    d += line+'\n';
                    ok = true;
                    continue;
                }

                if(ok && !line.equals("------------------------------------------------------"))
                {
                    d += line+"\n";
                }
                else
                    ok = false;
                    
            }
            System.out.println(d);
        }


  public void run()
  {
    
    try{
        operations();
    }
    catch(Exception e)
    {
        System.out.println("Booking error .\n please try again");
    }
  }



}