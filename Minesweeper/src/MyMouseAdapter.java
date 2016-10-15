import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JFrame;


public class MyMouseAdapter extends MouseAdapter {
	
	private Random generator = new Random();
	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Do nothing
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
	public void mouseReleased(MouseEvent e) {
	
		
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
					return;
				}
			}
			JFrame myFrame = (JFrame)c;
			MyPanel myPanel = (MyPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			int gridX = myPanel.getGridX(x, y);
			int gridY = myPanel.getGridY(x, y);
			int[] aMineX = myPanel.minePlacerX(x,y);
			int[] aMineY = myPanel.minePlacerY(x,y);
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX == -1) || (gridY == -1)) {
							//On the left column and on the top row... do nothing
						} else {
							  Color newColor = myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] ;
	                            Color C1 = Color.BLACK;         
	                            Color C2 = Color.GRAY; 
	                           
	                           // boolean loop=true;
	                           // while(loop){
	                    	     for(int i=0; i<9; i++){
	                    	       if( (gridX == aMineX[i]) && (gridY == aMineY[i]) ){  //The clicked cell is a mine
	                               newColor=C1;
	                               myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
	                    	       myPanel.repaint();
	                    	       i=100;     // value over 9 that will stop the for
	                    	     //  loop=false;// "loop" is now false which will stop the while loop
	                    	       }   else  {  //The clicked cell is safe
	                    	    	   newColor=C2;
	                    	    	   myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
		                    	       myPanel.repaint();
		                    	       if(i==8){  //Paints surrounding cells that are safe
		                    	    	          //In progress. Comment this entire "if" to be able to click cells individually.
		                    	    	   boolean loop=true;
		                    	    	   int z=1;
		                    	    	   while (loop){
		                    	    	   	   for(int a = 0; a<9; a++){
			                    	    		   if( (gridX+z != aMineX[a]) && (gridY   != aMineY[a])){
			                    	    			   newColor=C2;
			    	                    	    	   myPanel.colorArray[myPanel.mouseDownGridX+z][myPanel.mouseDownGridY] = newColor;
			    		                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   else{
			                    	    			   newColor=C1;
			        	                               myPanel.colorArray[myPanel.mouseDownGridX+z][myPanel.mouseDownGridY] = newColor;
			        	                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   if( (gridX-z != aMineX[a]) && (gridY   != aMineY[a])){
			                    	    			   newColor=C2;
			    	                    	    	   myPanel.colorArray[myPanel.mouseDownGridX-z][myPanel.mouseDownGridY] = newColor;
			    		                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   else{
			                    	    			   newColor=C1;
			        	                               myPanel.colorArray[myPanel.mouseDownGridX-z][myPanel.mouseDownGridY] = newColor;
			        	                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   if( (gridX   != aMineX[a]) && (gridY+z != aMineY[a])){
			                    	    			   newColor=C2;
			    	                    	    	   myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY+z] = newColor;
			    		                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   else{
			                    	    			   newColor=C1;
			        	                               myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY+z] = newColor;
			        	                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   if( (gridX   != aMineX[a]) && (gridY-z != aMineY[a])){
			                    	    			   newColor=C2;
			    	                    	    	   myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY-z] = newColor;
			    		                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   else{
			                    	    			   newColor=C1;
			        	                               myPanel.colorArray[myPanel.mouseDownGridX][myPanel.mouseDownGridY-z] = newColor;
			        	                    	       myPanel.repaint();
			                    	    		   }
			                    	    		   
			                    	           }//end of for 
		                    	    	   	   if(z==9){loop=false;};
		                    	    	   	   z++;
		                    	    	   }
		                    	       }
		                    	    }//end of else
	                    	      } //end of for
						} //end of else
					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			
			Component c1 = e.getComponent();
			while (!(c1 instanceof JFrame)) {
				c1 = c1.getParent();
				if (c1 == null) {
					return;
				}
			}
			JFrame myFrame1 = (JFrame)c1;
			MyPanel myPanel1 = (MyPanel) myFrame1.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
			Insets myInsets1 = myFrame1.getInsets();
			int x11 = myInsets1.left;
			int y11 = myInsets1.top;
			e.translatePoint(-x11, -y11);
			int x111 = e.getX();
			int y111 = e.getY();
			myPanel1.x = x111;
			myPanel1.y = y111;
			myPanel1.mouseDownGridX = myPanel1.getGridX(x111, y111);
			myPanel1.mouseDownGridY = myPanel1.getGridY(x111, y111);
			int gridX1 = myPanel1.getGridX(x111, y111);
			int gridY1 = myPanel1.getGridY(x111, y111);
			if ((myPanel1.mouseDownGridX == -1) || (myPanel1.mouseDownGridY == -1)) {
				//Had pressed outside
				//Do nothing
			} else {
				if ((gridX1 == -1) || (gridY1 == -1)) {
					//Is releasing outside
					//Do nothing
				} else {
					if ((myPanel1.mouseDownGridX != gridX1) || (myPanel1.mouseDownGridY != gridY1)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						if ((gridX1 == -1) || (gridY1 == -1)) {
							//On the left column and on the top row... do nothing
						}else
                        {
                          //Left click will mark the cell in red. Another left click will unmark it.
                            Color newColor = myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] ;
                            Color C1 = Color.RED;         
                            Color C2 = Color.WHITE;         
                            Color C3 = Color.GRAY;  
                            Color C4 = Color.BLACK;  
                            
                            boolean loop = true ;
                          // If the cell is already uncovered or is a revealed mine, the player can't flag it
                            if (newColor.equals(C3) || newColor.equals(C4)){
                            	loop=false;
                            }
                            
                            
                            while(loop)
                            {
                              switch (generator.nextInt(2))
                                {
                     case 0:
                               if (newColor.equals(C1))  {
                                break;                   }
                               else{
                                newColor = C1;          
                                   loop=false;
                                   break;                }
                            
                     case 1:
                               if (newColor.equals(C2))  {
                                break;                   }
                               else{
                                   newColor = C2;       
                               loop=false;
                               break;                     }
                   
                              }
                            }
                         
                            myPanel1.colorArray[myPanel1.mouseDownGridX][myPanel1.mouseDownGridY] = newColor;
                            myPanel1.repaint();
                            }
                        }
                    }
                }
            myPanel1.repaint();
            break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
 
  
}