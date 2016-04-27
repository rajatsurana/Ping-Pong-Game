package multiplayer;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class write extends Thread{
	List<String> list = new ArrayList<String>();
    BufferedWriter write;

    public write(OutputStream out){
     write = new BufferedWriter( new OutputStreamWriter( out ) );
     start();
    }

    public synchronized void writeLine(String m){
        list.add(m);
        notifyAll();
    }

    private synchronized String getline()
    {
        while (list.isEmpty() )
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
            while ( true )
            {
                write.write( getline() );
                write.newLine();
                write.flush();
                System.out.println(Calendar.getInstance().getTimeInMillis());
                
            }
        }
        catch ( IOException e )
        {
            e.printStackTrace();
            System.exit( 1 );
        }
    }
}
