import java.net.Socket;

public class list{
    private obj head = null;
    private obj tail= null;

    public list(){}

    public synchronized obj next()
    {
        while (head == null )
        {
            try
            {
                wait();
            }
            catch ( InterruptedException e )
            {
            }
        }
        obj re = head;
        head = head.node;
        if ( head == null )
            tail = null;
        return re;
    }

    public synchronized void add( Socket soc , info p)
    {
        if ( head == null )
            head = tail = new obj( soc , p );
        else{
            tail.node =new obj(soc,p);
        	tail=tail.node;}
        	
        notifyAll();
    }
}