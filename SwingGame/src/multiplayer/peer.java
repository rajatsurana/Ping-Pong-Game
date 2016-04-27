package multiplayer;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class peer implements active.activepeer,pendingpeer.activepeersupport{
	info my;
    pendingpeer pendingpeer;
    List<active> listofpeers = new ArrayList<active>();
    int count = 0;

    public peer(info my,pendingpeer pendingpeer){
    	this.my=my;
    	this.pendingpeer=pendingpeer;
       // zUserDialog.setActivePeerManager( this );
        pendingpeer.start( this );
    }

    public synchronized void remove( active a )
    {
        listofpeers.remove(a);
    }

    public synchronized info[] dost()
    {
        info[] pe = new info[ listofpeers.size() ];
        int i=0;
        for ( Iterator it = listofpeers.iterator() ; it.hasNext() ; ){
        	pe[ i ]=((active) it.next()).my;
        	i++;}
      /*  for ( int i = 0 ; i < pe.length ; i++ )
            pe[ i ] = listofpeers.get( i ).my;*/
        return pe;
    }

    public synchronized void sendtoall(String m)
    {
        for ( Iterator it = listofpeers.iterator() ; it.hasNext() ; )
            ((active) it.next()).send(m);
    }
    public synchronized boolean isalreadyconnected(info p)
    {
        if (p!= null ){
        	for (Iterator it = listofpeers.iterator() ; it.hasNext();){
            	active temp=((active) it.next());
            	System.out.println("inside loop of is already connected");
            	System.out.println(temp.my.add+ " "+p.add+ " "+ temp.my.port+ " "+p.port);
                if (temp.my.add.equals(p.add)&&temp.my.port.equals(p.port)){
                	System.out.println("true");
                	return true;
                }
               /* if ( temp.mydata.add.equalsIgnoreCase(p.add) && temp.mydata.port==p.port){
                	System.out.println("true");
                	return true;
                }*/
             }
         }
        	System.out.println("false");
        	return false;
    }
    public void sendpeers(active pp){
	//	info[] friends=dost();
		System.out.println("sendpeers");
		for ( Iterator it = listofpeers.iterator() ; it.hasNext() ; ){
        	active temp=((active) it.next());
        	if(!temp.my.equals(pp.my)){
        		if(temp.mydata!=null){
	        		String s="dosti "+temp.mydata.name+" "+temp.mydata.add+" "+temp.mydata.port;
	    			System.out.println(s);
	    			pp.send(s);}
        	}}
		/*for(int i=0;i<friends.length;i++){
			System.out.println("inside loop");
			if(!friends[i].equals(pp.my)){
			String s="dosti "+friends[i].name+" "+friends[i].add+" "+friends[i].port;
			System.out.println(s);
			pp.send(s);}
		}*/
	}

    public synchronized void addactivepeer(info info, InputStream in,OutputStream out){
        active dost=new active(info, this , pendingpeer ,my, null, in, out);
        listofpeers.add(dost);
        sendpeers(dost);
        System.out.println("data sent");
    }
}