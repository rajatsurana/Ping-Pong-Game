import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class read extends Thread{
	    List<String> list = new ArrayList<String>();
	    BufferedReader read;

	    public read( InputStream in ){
	        read = new BufferedReader( new InputStreamReader( in ) );
	        start();
	    }
	    private synchronized void add( String a ){
	        list.add( a );
	        notifyAll();
	    }

	    public synchronized String readLine()
	    {
	        while ( list.isEmpty() )
	        {
	            try
	            {
	                wait();
	            }
	            catch ( InterruptedException e )
	            {
	            }
	        }

	        String m = list.get( 0 );
	        list.remove( 0 );
	        return m;
	    }

	    public void run()
	    {
	        try
	        {
	            try
	            {
	                for ( String line ; null != (line = read.readLine()) ; ){
	                	System.out.println(Calendar.getInstance().getTimeInMillis());
	                	add( line );}
	            }
	            catch ( SocketException ignoreBecauseWeAssumeSocketClosed )
	            {
	            }
	        }
	        catch ( IOException e )
	        {
	            e.printStackTrace();
	            System.exit( 1 );
	        }
	        add( null );
	    }
	}
