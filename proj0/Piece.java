public class Piece{
	private boolean pieceTeam;
	private Board currBoard;
	private int xPosition;
	private int yPosition;
	private String typePiece;
	private boolean kingMe = false;
	private boolean didICap = false;
	private int n = 8;
	public Piece(boolean isFire, Board b, int x, int y, String type){
		pieceTeam = isFire;
		currBoard = b;
		xPosition = x;
		yPosition = y;
		typePiece = type;
	}

	public boolean isFire(){
		return pieceTeam;
	}

	public int side(){
		if(pieceTeam){
			return 0;
		}
		else{
			return 1;
		}

	}

	public boolean isKing(){
		if(kingMe){
			return true;
		}
		else{
			return false;
		}
	}

	private void theKinger(){
		if(side()==0){
			if(yPosition == (n-1)){
				kingMe = true;
			}
		}
		else if(side()==1){
			if(yPosition == 0){
				kingMe = true;
			}
		}
	}

	public boolean isBomb(){
		if(typePiece == "bomb"){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isShield(){
		if(typePiece == "shield"){
			return true;
		}
		else{
			return false;
		}

	}

	public void move(int x, int y){
			if (y > yPosition){
				if (x - xPosition > 1) {
					currBoard.remove(x-1,y-1);
					didICap = true;
				}
				else if(xPosition - x > 1){
					currBoard.remove(x+1,y-1);
					didICap = true;
				}
			}
			if(y < yPosition){
				if (x - xPosition > 1) {
					currBoard.remove(x-1,y+1);
					didICap = true;
				}
				else if(xPosition - x > 1){
					currBoard.remove(x+1,y+1);
					didICap = true;
				}
			}
			xPosition = x;
			yPosition = y;
			currBoard.place(this,x,y);
			theKinger();
			bomberMan();	
		}

	private void bomberMan(){
		if(isBomb() && hasCaptured()){
	        for (int i = xPosition-1; (i <= xPosition + 1) && (i < n && i>=0); i+=1) {
	            for (int j = yPosition-1; (j <= yPosition + 1) && (j < n && j >= 0); j+=1) {
	            	if(currBoard.pieceAt(i,j)!=null){
	            		if(!(currBoard.pieceAt(i,j).isShield())){
		            		currBoard.remove(i,j);
		            	}
	            	}
	            }
			}
		}
	}

	public boolean hasCaptured(){
		return didICap;

	}

	public void doneCapturing(){
		didICap = false;
	}
}