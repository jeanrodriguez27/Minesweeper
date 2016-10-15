
public class MineMover{
	private int xPos = 0;
 	private int yPos = 0;
 	
 	public MineMover(int x, int y) {
		// TODO Auto-generated constructor stub
		this.xPos = x;
 		this.yPos = y;
 	}
 		public int getXPos() {
 	 		return this.xPos;
 	 	}
 	 	
 	 	public int getYPos() {
 	 		return this.yPos;
 	}
 	 	
 	 	public void setPosition(int xPos, int yPos) {
 	 		this.xPos = xPos;
 	 		this.yPos = yPos;
 	 	}

 		public void moveInX(int mineX) {
 			// TODO Auto-generated method stub
 			this.xPos = mineX;
 		}
 		
 		public void moveInY(int mineY) {
 			// TODO Auto-generated method stub
 			this.yPos = mineY;
 		}
 		
		
	}
