public class Board{
	private int n = 8;
	private boolean drawEmpty;
	private Piece[][] dahPieces = new Piece[n][n];
	private Piece selectedPiece = null;
	private int sX;
	private int sY;
	private int turn = 0;
	private int hasMoved = 0;

	public Board(boolean shouldBeEmpty){
		drawEmpty = shouldBeEmpty;
		if(!drawEmpty){
		    for (int i = 0; i < n; i+=1) {
	        	for (int j = 0; j < n; j+=1) {
	        		if(j == 0 && (i % 2 == 0)){
	        			dahPieces[i][j] = new Piece(true,this,i,j,"pawn");
	        		}
	        		if(j == 1 && (i % 2 == 1)){
	        			dahPieces[i][j] = new Piece(true,this,i,j,"shield");
	        		}
	        		if(j == 2 && (i % 2 == 0)){
	        			dahPieces[i][j] = new Piece(true,this,i,j,"bomb");
	        		}
	        		if(j == (n-1) && (i % 2 == 1)){
	        			dahPieces[i][j] = new Piece(false,this,i,j,"pawn");
	        		}
	        		if(j == (n-2) && (i % 2 == 0)){
	        			dahPieces[i][j] = new Piece(false,this,i,j,"shield");
	        		}
	        		if(j == (n-3) && (i % 2 == 1)){
	        			dahPieces[i][j] = new Piece(false,this,i,j,"bomb");
	        		}
	        	}

			}
		}
	}

	private void drawTheBoard(){
		StdDrawPlus.setXscale(0,n);
		StdDrawPlus.setYscale(0,n);
        for (int i = 0; i < n; i+=1) {
            for (int j = 0; j < n; j+=1) {
                if ((i + j) % 2 == 0) StdDrawPlus.setPenColor(StdDrawPlus.GRAY);
                else StdDrawPlus.setPenColor(StdDrawPlus.RED);
                if(pieceAt(i,j)!=null){
                	if(pieceAt(i,j)==selectedPiece) StdDrawPlus.setPenColor(StdDrawPlus.WHITE);
                }                
                StdDrawPlus.filledSquare(i + .5, j + .5, .5);
			}
		}
		if(drawEmpty == false){
			for(int i = 0; i < n; i+=1){
				for(int j = 0; j<n; j+=1){
					if(dahPieces[i][j]!=null){
						StdDrawPlus.picture(i + .5, j + .5, Board.drawPieceLoc(dahPieces[i][j]),1,1);
					}

				}
			}
		}
	}

	private static String drawPieceLoc(Piece disPiece){
		String king;
		String type;
		String team;
		if(disPiece.isKing()) king = "-crowned";
		else king = "";
		if(disPiece.isBomb()) type = "bomb";
		else if (disPiece.isShield()) type = "shield";
		else type = "pawn";
		if (disPiece.side() == 0) team = "fire";
		else team = "water";
		return "img/" + type + "-" + team +  king + ".png";				
	}
	public Piece pieceAt(int x, int y){
		if(x >= n || y >= n || y < 0 || x < 0){
			return null;
		}
		return dahPieces[x][y];
	}

	

	public boolean canSelect(int x, int y){
		if(x >= n || y >= n || y < 0 || x < 0){
			return false;
		}
		else if(pieceAt(x,y)!=null){
			if(pieceAt(x,y).side() == (turn%2) && (selectedPiece == null || hasMoved == 0)){
				return true;
			}
		}
		else if(selectedPiece!=null){
			if((validMove(sX,sY,x,y) && hasMoved == 0) || (selectedPiece.hasCaptured() && validCapture(sX,sY,x,y))) {
				return true;
			}
		}
		return false;
	}


	private boolean validMove(int xi, int yi, int xf, int yf){
			if(selectedPiece.isKing()){
				return (redValidMove(xi, yi, xf, yf) || blueValidMove(xi, yi, xf, yf));
			}
			 if((turn%2)==0){
				return redValidMove(xi, yi, xf, yf);
			}
			return blueValidMove(xi, yi, xf, yf);
	}

	private boolean validCapture(int xi, int yi, int xf, int yf){
			if(selectedPiece.isKing()){
				return (redValidCapture(xi, yi, xf, yf) || blueValidCapture(xi, yi, xf, yf));
			}
			if((turn%2) == 0) {
				return redValidCapture(xi, yi, xf, yf);
			}
			return blueValidCapture(xi, yi, xf, yf);
	}

	private boolean redValidCapture(int xi, int yi, int xf, int yf){
		if(yf == yi + 2 && xf == xi + 2){
			if(pieceAt(xf - 1, yf - 1)!=null){
				if(pieceAt(xf - 1, yf - 1).side() != (turn%2)){
					return true;
				}
			}
		}
		else if(yf == yi + 2 && xf == xi - 2){
			if(pieceAt(xf + 1, yf - 1)!=null){
				if(pieceAt(xf + 1, yf - 1).side() != (turn%2)){
					return true;
				}
			}
		}
		return false;
	}

	private boolean redValidMove(int xi, int yi, int xf, int yf){
		if(yf == yi + 1 && (xf == xi + 1 || xf == xi - 1)){
			if(pieceAt(xf,yf)==null){
				return true;
			}
			else{
				return false;
			}
		}
		return redValidCapture(xi, yi, xf, yf);
	}

	private boolean blueValidMove(int xi, int yi, int xf, int yf){
		if(yf == yi - 1 && (xf == xi + 1 || xf == xi - 1)){
			if(pieceAt(xf,yf)==null){
				return true;
			}
			else{
				return false;
			}
		}
		return blueValidCapture(xi, yi, xf, yf);
	}

	private boolean blueValidCapture(int xi, int yi, int xf, int yf){
		if(yf == yi - 2 && xf == xi + 2){
			if(pieceAt(xf - 1, yf + 1)!=null){
				if(pieceAt(xf - 1, yf + 1).side() != (turn%2)){
					return true;
				}
			}
		}
		else if(yf == yi - 2 && xf == xi - 2){
			if(pieceAt(xf + 1, yf + 1)!=null){
				if(pieceAt(xf + 1, yf + 1).side() != (turn%2)){
					return true;
				}
			}
		}
		return false;
	}

	public void select(int x, int y){
		if (pieceAt(x,y)!=null){
			selectedPiece = pieceAt(x,y);
			sX = x;
			sY = y;
		}
		else{
			selectedPiece.move(x,y);
			hasMoved += 1;
		}
	}

	public void place(Piece p, int x, int y){
		if (x < n && y < n && x >= 0 && y >= 0){
			for (int i = 0; i < n; i+=1) {
        		for (int j = 0; j < n; j+=1) {
        			if (dahPieces[i][j] == p){
        				dahPieces[i][j] = null;
        			}
        		}
        	}
	        dahPieces[x][y] = p;
		}
		if(p == selectedPiece){
			sX = x;
			sY = y;
		}
	}

	public Piece remove(int x, int y){
		Piece p;
		if(x >= n && y >= n && x<0 && y<0){
			System.out.println("OUT OF BOUNDS");
			return null;
		}
		else if(pieceAt(x,y)==null){
			System.out.println("NO PIECE HERE BROTHER");
			return null;
		}
		else{
			p = pieceAt(x,y);
			dahPieces[x][y] = null;
			return p;
		}
	}

	public boolean canEndTurn(){
		if(selectedPiece != null){
			if(selectedPiece.hasCaptured() == true || hasMoved > 0){
				return true;
			}
		}
		return false;
	}

	public void endTurn(){
		turn = turn + 1;
		selectedPiece.doneCapturing();
		selectedPiece = null;
		hasMoved = 0;
	}

	public int numFirePieces(){
		int check = 0;
		for (int i = 0; i < n; i+=1) {
            for (int j = 0; j < n; j+=1) {
            	if(pieceAt(i,j)!=null){
            		if(pieceAt(i,j).side() == 0){
            			check+=1;
            		}
            	}
            }
        }
        return check;
	}

	public int numWaterPieces(){
		int check = 0;
		for (int i = 0; i < n; i+=1) {
            for (int j = 0; j < n; j+=1) {
            	if(pieceAt(i,j)!=null){
            		if(pieceAt(i,j).side()== 1){
            			check+=1;
            		}
            	}
            }
        }
        return check;
	}
	

	public String winner(){
		if(numWaterPieces() == 0 && numFirePieces() == 0){
			return "No one";
		}
		else if(numFirePieces()==0){
			return "Water";
		}
		else if(numWaterPieces()==0){
			return "Fire";
		}
		return null;
	}

	public static void main(String[] args) {
		Board disBoard = new Board(false);
		while(true){
			disBoard.drawTheBoard();
			if(StdDrawPlus.mousePressed()){
				double xd = StdDrawPlus.mouseX();
				int x = (int)xd;
				double yd = StdDrawPlus.mouseY();
				int y = (int)yd;
				if (disBoard.canSelect(x,y)){
					disBoard.select(x,y);
				}
			}
			if(StdDrawPlus.isSpacePressed()){
				if(disBoard.canEndTurn()){
					disBoard.endTurn();
				}
			}
		if(disBoard.winner()!=null){
			break;
		}
		StdDrawPlus.show(25);
		}
		
	}
}