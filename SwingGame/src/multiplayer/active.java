package multiplayer;
import game.CustomBoard;
import game.Optionpane;
import game.PlayGame;

import java.io.*;

import org.w3c.dom.stylesheets.DocumentStyle;
public class active extends Thread{
    public interface activepeer
    {
        void remove( active a );

        info[] dost();
        void sendtoall(String m);
        
    }

    public interface newpeer
    {
        void addnewpeer( info peer);
    }

    public info my;
    activepeer activepeer;
    newpeer newpeer;
    public static info peer;
    public static info mydata;
    public read read;
    write write;

    public active(info my,
                       activepeer activepeer ,
                       newpeer newpeer,
                       info peer , info mydata, InputStream in , OutputStream out )
    {
        this.my=my;
        this.activepeer=activepeer;
        this.newpeer=newpeer;
        this.peer=peer;
    	read = new read(in);
        write = new write(out);
        this.mydata=mydata;
        start();
    }

    public info getpeerinfo()
    {
        return peer;
    }
   public void run(){
        send("helo"+" "+peer.name+" "+peer.add+" "+peer.port);
       /* info[] pee=activepeer.dost();
        for(int i=0;i<pee.length;i++)send("dosti "+pee[i].name+" "+pee[i].add+" "+pee[i].port);*/

        for ( String line ; null != (line = read.readLine()) ; ){
        	//activepeer.sendtoall(line);
        	process(line);
        	
        	
        	}                 
        activepeer.remove( this );
       // zUserDialog.showDisconnect( zPeerInfo );
    }
   public void process(String  line){
	   if(line.startsWith("joined")){
		   String[] lines =line.split(" ");
		   Optionpane.playerJoinedField.setText(lines[1]);
		   Optionpane.maxPlayerField.setText(lines[2]);
	   }else if(line.startsWith("start")){
		   String[] lines =line.split(" ");
		   int max=Integer.valueOf(lines[1]);
		   int peers=Integer.valueOf(lines[2]);
		   PlayGame ex = new PlayGame(max,peers);
		   ex.setVisible(true);
	   }else if(line.startsWith("p1")){
		   String[] lines =line.split(" ");
		   int x=Integer.valueOf(lines[1]);
		   CustomBoard.paddle2.setX(x);
	   }else if(line.startsWith("ball")){
		   String[] lines =line.split(" ");
		   int x=Integer.valueOf(lines[1]);
		   int y=Integer.valueOf(lines[2]);
		   int vx=Integer.valueOf(lines[3]);
		   int vy=Integer.valueOf(lines[4]);
		   CustomBoard.ball.setXY(x,y);
		   //CustomBoard.ball.setVel(vx, vy);
	   }
	   else if(line.startsWith("helo")){System.out.println(line);updateinfo(line);}
	   else if(line.startsWith("dosti"))processpeer(line);
   		else {System.out.println(line);
   		}
	   
   }
   public void send( String m )
    {
        write.writeLine(m);
    }
   private void updateinfo(String pe){
	   info p = info.information(pe);
       if ( p != null )
       {
    	   this.mydata=p;
    	   //  activepeer.update(p);
           System.out.println("info updated");
          // System.out.println(p.name+" gets connected to "+my.name);
       }
   }
    private void processpeer(String pe)
    {
        info p = info.information(pe);
        if ( p != null )
        {
            newpeer.addnewpeer( p);
           // System.out.println(p.name+" gets connected to "+my.name);
        }
    }
}
