class User
{
    //main class 
    
    public static void main(String[] args) {
        Customer c =new Customer();
        Thread t = new Thread(c);    //multithreading is called for one thread
        t.start(); 
     }
}