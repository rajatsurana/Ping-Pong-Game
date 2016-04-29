package multiplayer;
import java.io.IOException;
import java.net.ServerSocket;

public class network extends Thread{
	public static peer peermanage;
	public pendingpeer pending;
	ServerSocket socket;
	
	public void start (info my,info peer){
		info info=my;
		try
        {
			
			pending=new pendingpeer();
			if(peer!=null)pending.addnewpeer(peer);
            socket = new ServerSocket(info.port);

            peermanage=new peer(info ,pending);
            
            System.out.println("started");
            start();
            new chatinterface(peermanage);
            }
		catch ( Exception e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }}
        public void run(){
            try{
        	while ( true )
            {
                pending.add( socket.accept());
            }}
        catch ( Exception e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }}
        
        
    
}