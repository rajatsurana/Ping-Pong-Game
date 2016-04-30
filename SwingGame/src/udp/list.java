package udp;


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
        head = head.next;
        if ( head == null )
            tail = null;
        return re;
    }

    public synchronized void add(String s)
    {
        if ( head == null )
            head = tail = new obj(s);
        else{
            tail.next =new obj(s);
        	tail=tail.next;}
        	
        notifyAll();
    }
}