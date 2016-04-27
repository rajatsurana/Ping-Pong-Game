
public class main {
	 public static void main( String[] args )
	    {
		info my =new info("192.168.1.4", "wagmare", 9646);
		info peer =new info("192.168.1.4", "arpit", 1478);
		 new network().start(my,null);
		 
	    }
}
