import java.io.*;
import java.util.*;
import java.lang.*;

class Customer implements Runnable {

    static int count =0;
    public void operations() throws Exception{
        boolean success = false;
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
                System.out.println("TRY AGAIN...");
            } else {
                Signup sp = new Signup(user, pass);
                if(!sp.checkAvailablity()) {
                    sp.createAccount();
                    System.out.println("SIGNED UP SUCCESSFULLY");
                    success = true;
                } else {
                    System.out.println("USERNAME ALREADY EXISTS...PLEASE TRY AGAIN!");
                }
            }
        } else if(option == 1) {
            Console c =System.console();
            System.out.println("ENTER USERNAME:");
             user = sc.next();
            
             pass = String.valueOf(c.readPassword("Enter Password"));
            Signin si = new Signin(user, pass);
            if(si.checkUser() && si.checkPassword()) {
                System.out.println("SIGNED IN SUCCESSFULLY..");
                success = true;
            } else {
                System.out.println("INVALID PASSWORD OR USERNAME");
            }
        }

        if(success) {
            
            do{
                System.out.println("1-Book a Flight\n2-Cancel a Flight\n3-Print Ticket\n4-check History\n5-Any other number to break");
            System.out.println("Enter choice:");
            int sch = sc.nextInt();
            switch(sch)
            {
                case 1:
                       System.out.println("DO YOU WANT TO BOOK A FLIGHT:");
                       System.out.println("1-yes 0-no");
                       int choice = sc.nextInt();
                       if(choice == 0) {
                           System.out.println("THANK YOU FOR VISITING :)");
                       }
                        else if(choice == 1) {
                           System.out.println("WELCOME TO OUR WEBSITE!!");
                           System.out.println("ENTER BOARDING:");
                           String board = sc.next();
                           System.out.println("ENTER DESTINATION:");
                           String des = sc.next();
                           Flight fl = new Flight();
                           //fl.setter(board, des);
                           System.out.println("Enter date");
                           String date = sc.next();
                           fl.printDetails(board, des, date);
                           System.out.println("ENTER YOUR PREFERRED TIME:");
                           String time = sc.next();
                           System.out.println("*");
                           String s[] = fl.searchDetails(board, des, date,time);
                           System.out.println("*");
                           System.out.println("price = "+s[4]);
                           System.out.println("Do you want to confirm booking(T/F)");
                           boolean confirmBooking = sc.nextBoolean();
                            
           
                           if(confirmBooking)
                           {
                               System.out.println("Enter nUmber of passenger");
                               int passenger = sc.nextInt();
                               int p = passenger;
                               if(passenger<0)
                                   throw new BookingException("wrong Input");
                               while(passenger-- >0){    
                               System.out.println("------BOOKIng CONFIRMED-------");
                               System.out.print("Enter Name :");
                               String name = sc.next();
                               System.out.print("Enter phone number :");
                               int phone = sc.nextInt();
                               if(String.valueOf(phone).length()<10)
                                   throw new BookingException("Wrong input");
                               System.out.println(user);
                               String filepath = user+".txt";
                               String t="";
                               //FileWriter writer = new FileWriter(filepath, true);
                               //PrintWriter printWriter = new PrintWriter(writer);
                               t += "------------------------------------------------------\n";
                               t += "Booking id :"+user+"@"+count+++"\n";
                               t += "Name :"+name+"\n";
                               t += "phone no:"+phone+"\n";
                               t += "flight no. :"+s[5]+"\n";
                              
                               t += "Destination = "+s[1]+"                        source="+s[0]+"\n";
                               t += "date = "+s[2]+"             time = "+s[3]+"              price="+s[4]+"\n";
           
                               FileOutputStream fo =new FileOutputStream(filepath,true);
                               
                               fo.write(t.getBytes());
                               }
           
                            System.out.println("total Bill = "+p*Integer.parseInt(s[4]));
           
                           }
                            else {
                                System.out.println("Thank you for visiting");
                            }   
                       } 
                       else {
                          System.out.println("WRONG INPUT");
                       }
                       break;

               case 2:
                    System.out.println("Enetr booking ID :");
                    String id = sc.next();
                    FileInputStream fs = new FileInputStream(user+".txt");
                    BufferedReader br = new BufferedReader(new InputStreamReader(fs));
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
                    
                    FileOutputStream wr = new FileOutputStream(user+".txt");
                        wr.write(d.getBytes());
                        break;
            case 3:
            System.out.println("Enetr booking id");
            String bookingid = sc.next();
            try{
                printTicket(bookingid, user);
            }catch(IOException e)
            {
                System.out.println("Printing error");
            }
            break;
            case 4: {
                int i=0;
                System.out.println("PRINTING FLIGHT PURCHASING HISTORY......");
                FileReader fReader = new FileReader(user+".txt");
                //boolean possible = true;
                BufferedReader br1;
                try {
                    br1 = new BufferedReader(fReader);
                    String line;
                    while((line = br1.readLine()) != null) {
                        i++;
                        if(i==2) {
                            continue;
                        }
                        System.out.println(line);

                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
                System.out.println("*****YOUR BOOKING HISTORY ENDS*****");
            }

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