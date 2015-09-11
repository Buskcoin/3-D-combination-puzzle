import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import javax.imageio.ImageIO;

import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Cube extends JPanel{
	Side sides[];
	//public char[] color = {'b','r','g','y','o','w'};
	//debug
	private int size = 50;
	private boolean LEFT =false;
	private boolean RIGHT =false;
	private Timer m_StarTimer;
	public char[] color = {'0','1','2','3','4','5'};
	private int errorExit = 0;
	public boolean ROT2;
	public boolean ROT3;
	public boolean COL1;
	public boolean COL2;
	public boolean COL3;
	public boolean ROT1;
	public boolean FLIP = false;
	private boolean swtch = false;
	private int t = 0;
	public Cube(){
		try {
		sides = new Side[6];
		for(int i=0;i<sides.length;i++){
			sides[i] = new Side(color[i]);
		}
		 setFocusable(true);
			FalconHandler test = new FalconHandler();
			this.addKeyListener(test);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unable to load Falcon file");
        }
			initComponents();
			m_StarTimer = new Timer(10, new starTimerHandler());
	        m_StarTimer.start();
	        //this.Scramble();
		}
	
	public void paint(Graphics g){
			g.fillRect(0,0 , 500, 500);
			drawSide(g);
			this.getCount();
		}
	public void Scramble(){
		Random rand = new Random();
		for(int i = 0; i<rand.nextInt(50);i++){
			if(3-rand.nextInt(5)<=0)
				this.rotateRow(rand.nextInt(2));
			else this.rotateColumn(rand.nextInt(2));
		}
	}
	public void drawSide(Graphics g){
		if(ROT1){
			//this.getCount();
			this.rotateRow(0);

			ROT1 = false;	
		}
		if(ROT2){
			this.rotateRow(1);
			ROT2 = false;
		}
		if(ROT3){
			this.rotateRow(2);
			ROT3 = false;
		}
		if(COL1){
			this.rotateColumn(0);
			COL1 = false;
		}
		if(COL2){
			this.rotateColumn(1);
			COL2 = false;
		}
		if(COL3){
			this.rotateColumn(2);
			COL3 = false;
		}
		g.clearRect(0, 0, 500, 500);
		g.setColor(Color.darkGray);
		g.fillRect(0, 0, 500, 500);
		int y = 200;
		int point = 200;
		int sideP = 200;
		int count = 3;
		for(int j = 0; j<3; j++){
			
			count--;
			int topX[]= {point,point - (50/3),point+ 50-(50/3), point+50};
			int topY[]= {point,point-50/3,point-50/3, point};
			int sideX[]= {sideP,sideP,sideP-(50/3), sideP-50/3};
			int sideY[]= {sideP,sideP+50,sideP+50-(50/3), sideP-50/3};
			int face[] = {0,1,4,2,1,0};
			int x = 200;
			
			
			if(FLIP){
				if(swtch)
					swtch = false;
				else
					swtch = true;
				t+=3;
				if(t>=face.length)
					t = 0;
				FLIP = false;
			}
			int small = 3;
			for(int i =0; i<3;i++){
				small--;
			g.setColor(sides[face[0+t]].getRow(j).getSquares()[i].getCol());
			g.fill3DRect(x, y, size, size, true);
			if(swtch)
				g.setColor(sides[face[1+t]].getColumn(count).getSquares()[small].getCol());
			else
				g.setColor(sides[face[1+t]].getRow(count).getSquares()[i].getCol());
			
			g.fillPolygon(topX, topY, 4);
			g.setColor(sides[face[2+t]].getColumn(count).getSquares()[i].getCol());
			g.fillPolygon(sideX, sideY, 4);
			//g.fillRect(x/2, y, size/2, size);
			//for(int s = 0;s<4;s++)
			
			for(int s = 0; s<topX.length; s++){
				topX[s]+=60;
				sideY[s]+=60;
			}
			

			x+=size+10;
		}
			sideP-=50/3+2;
			point -= 50/3+2;
		y+=size+10;
		}
		
	}
	public void rotateRow(int i){
		Row temp = sides[0].getRow(i);
		sides[0].setRow(i, sides[4].getRow(i));
		sides[4].setRow(i, sides[5].getRow(i));
		sides[5].setRow(i, sides[2].getRow(i));
		sides[2].setRow(i, temp);
		sides[5].rowCol();
		if(i==0)
		sides[1].swapRowsAndColumns(false);
		else if(i==2)
		sides[3].swapRowsAndColumns(true);
	}
	
	public void rotateColumn(int i){
	
		Column temp = sides[0].getColumn(i);
		sides[0].setColumn(i, sides[1].getColumn(i));
		sides[5].flipSide();
		sides[1].setColumn(i, sides[5].getColumn(i));
		sides[5].setColumn(i, sides[3].getColumn(i));
		sides[5].flipSide();
		sides[5].rowCol();
		sides[3].setColumn(i, temp);
		if(i==0)
    		sides[4].swapRowsAndColumns(true);
    		else if(i==2)
    		sides[2].swapRowsAndColumns(false);
	}
	public void getCount(){
		int test=0;
		for(int i = 0; i<sides.length; i++){
			for(int j = 0;j<sides[i].getAllRows().length; j++){
				for(int k = 0; k<sides[i].getAllRows()[j].getSquares().length;k++){
					test += sides[i].getAllRows()[j].getSquares()[k].getColor();
				}
			}
		}
		if(test != 2727){
		System.out.println("Error test: "+test);
		errorExit++;
		}
		if(errorExit>100)
			System.exit(0);
	}
	
	public String toString(){
		String cube;
		StringBuilder test = new StringBuilder();
		for(int i=0;i<sides.length;i++){
			test.append(sides[i].toString());
			test. append(" ");
		}
		test.append("\n");
		cube = test.toString();
		return cube;
	}
    private class starTimerHandler implements ActionListener {

    	  public void actionPerformed(ActionEvent e) {
              repaint();
          }
    }
    private class FalconHandler implements KeyListener {

    	
   
		public void keyPressed(KeyEvent e) {

//            if (e.getKeyCode() == KeyEvent.VK_0) {
//                LEFT = true;
//                rotateRow(0);
//                
//                System.out.println("workes");
//                repaint();
//            }
            if (e.getKeyCode() == KeyEvent.VK_1) {
                ROT1 = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_2) {
              ROT2 = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_3) {
            	ROT3 = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_4) {
            	COL1 = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_5) {
            	COL2 = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_6) {
            	COL3 = true;
            }
            if (e.getKeyCode() == KeyEvent.VK_7) {
            	FLIP = true;
            }

        }

        public void keyReleased(KeyEvent e) {

        }

		@Override
		public void keyTyped(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                LEFT = true;
                System.out.println("worked");
              
            }
			
		}

    }
    private void initComponents() {

        setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
}
}
