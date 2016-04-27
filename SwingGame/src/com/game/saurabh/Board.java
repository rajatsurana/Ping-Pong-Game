package com.game.saurabh;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import javax.swing.Timer;


public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Paddle paddle;
    private Ball ball;
    private final int DELAY = 10;

    public Board() {

        initBoard();
    }
    
    private void initBoard() {
        
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);

        paddle = new Paddle();
        ball = new Ball();
        timer = new Timer(DELAY, this);
        timer.start();        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {
        
        //Graphics2D g2d = (Graphics2D) g;
        //g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(), this);
    	g.setColor(Color.RED);
    	g.fillRect(paddle.getX(), paddle.getY(), 150, 10);
    	g.setColor(Color.blue);
    	g.fillOval(ball.getX(), ball.getY(), 25, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        paddle.move();
        checkCollisions();
        ball.move();
        repaint();  
    }
    
    public void checkCollisions() {

        Rectangle r3 = paddle.getBounds();
        Rectangle r2 = ball.getBounds();
        
        if(r2.intersects(r3)){
        	
        	double velX = ball.getVX();
        	double velY = ball.getVY();
        	double temp = -velY;
        	//ball.setVel(velX, temp);
        	int posPaddleX=paddle.getX()+paddle.getBounds().width/2;
        	int posPaddleY=paddle.getY();
        	int posBallX=ball.getX()+ball.getBounds().width/2;
        	int posBallY=ball.getY();
        	int radius =75;
        	double diffX=posBallX-posPaddleX;
        	double alpha, theta;
        	theta = Math.atan(diffX/radius);
        	System.out.println(diffX+":"+posPaddleX+":"+posBallX);
        	double alphaPlusTheta= Math.atan(velX/velY);
        	alpha = alphaPlusTheta-theta;
        	System.out.println(alpha +":"+theta+":"+alphaPlusTheta+"angles");
        	double alphaMinusTheta=alpha-theta;
        	double finalVelY ,finalVelX;
        	double vel =  2.8284271247461903;//Math.sqrt(velX*velX +velY*velY);
        	finalVelY= (Math.cos(-alphaMinusTheta))*vel;
        	finalVelX=(Math.tan(-alphaMinusTheta)*finalVelY);
        	System.out.println(alphaMinusTheta+":"+finalVelY+":"+finalVelX+",vel:"+Math.sqrt(velX*velX +velY*velY));
        	ball.setVel(finalVelX,-finalVelY);
        	
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            paddle.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            paddle.keyPressed(e);
        }
    }
}
