package multiplayer;
import java.io.IOException;
import java.net.ServerSocket;




public class network {
	public void start (info my,info peer){
		info info=my;
		try
        {
			
			pendingpeer pending=new pendingpeer();
			if(peer!=null)pending.addnewpeer(peer);
            ServerSocket socket = new ServerSocket(info.port);

            peer peermanage=new peer(info ,pending);
            
            System.out.println("started");
            new chatinterface(peermanage);
            while ( true )
            {
                pending.add( socket.accept());
            }
        }
        catch ( Exception e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
    }
	        
	   
	    }

	


	

	


