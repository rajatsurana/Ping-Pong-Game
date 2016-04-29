package game;




import java.awt.EventQueue;

import javax.swing.JFrame;

import multiplayer.network;

public class PlayGame extends JFrame {
	
	
public PlayGame(int max , int peers,String pos) {
        
        initUI(max,peers,pos);
    }
    
    private void initUI(int max,int peers,String pos) {
        //if(max==2 && peers==1){
        	add(new CustomBoard(max,peers,network.peermanage.listofpeers.get(0), pos));
//        }else{
//        	add(new Board());
//        }
        
        
        setSize(750, 700);
        setResizable(false);
        
        setTitle("PlayGame");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

//    public static void main(String[] args) {
//        
//        EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                
//            	PlayGame ex = new PlayGame(2,0);
//                ex.setVisible(true);
//            }
//        });
//    }
}
