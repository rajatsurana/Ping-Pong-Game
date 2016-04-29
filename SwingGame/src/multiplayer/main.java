package multiplayer;
//
//public class main {
//	 public static void main( String[] args )
//	    {
//		info my =new info("192.168.1.17", "wagmare", 9646);
//		info peer =new info("192.168.1.17", "arpit", 1478);//"saurabh",4534);
//		 new network().start(my,null);
//		 
//	    }
//}

public class main {
public static void main( String[] args )
   {
	info my =new info("192.168.1.17", "wagmare", 9646);
	info peer =new info("192.168.1.17", "arpit", 1478);//"saurabh",4534);
	 new network().start(peer,my);
	 
   }
}

//public class main {
//public static void main( String[] args )
// {
//	info my =new info("192.168.1.17","wagmare", 9646);
//	info peer =new info("192.168.1.17", "saurabh",4534);
//	 new network().start(peer,my);
//	 
// }
//}

//public class main {
//public static void main( String[] args )
// {
//	info my =new info("192.168.1.18","wagmare", 9646);
//	info peer =new info("192.168.1.17", "rajat",4355);
//	 new network().start(peer,my);
// }
//}