package game;




import java.awt.EventQueue;

import javax.swing.JFrame;

public class PlayGame extends JFrame {
	
	
public PlayGame(int max , int peers) {
        
        initUI(max,peers);
    }
    
    private void initUI(int max,int peers) {
        
        add(new CustomBoard(max,peers));
        
        setSize(400, 400);
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
