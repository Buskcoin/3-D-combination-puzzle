
public class Column {
	Square square[];
	private boolean flipped = false;
	public Column(char color){
		square = new Square[3];
		for(int i=0;i<square.length;i++){
			square[i] = new Square(color);
	}
}
	public Square[] getSquares(){
		return square;
	}
	public void setSquares(Square square[]){
		this.square = square;
	}
	/**
	 * Sets individual square in this column
	 * @param i Square number in column
	 * @param sq square object
	 */
	public void setSquare(int i, Square sq){
		square[i] = sq;
	}
	/**
	 * reverse columns
	 */
	public void reverse(){
		Square[] backSquare = new Square[3];
		for( int i = 0; i< square.length; i++){
			backSquare[i] = this.square[(square.length - 1) - i];
		}
		this.square = backSquare;
		if(flipped)
		flipped = false;
		else
		flipped = true;
	}
	public boolean isFlipped(){
		return flipped;
	}
	public void updateColumns(int i, Square colNum){
		this.square[i] = colNum;
	}
	public void updateAllColumns(int colNum, Row row[]){
		for(int i=0; i<square.length; i++)
			square[i]= row[i].getSquares()[colNum];
	}
	public String toString(){
		String str = "";
		for(int i=0;i<square.length;i++){
			str+=square[i].getColor();
			str+=" ";
		}
		return str;
	}
}
