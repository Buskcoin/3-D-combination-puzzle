
public class Row {
	Square square[];
	public Row(char color){
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
	public void setSquare(int i, Square sq){
		square[i] = sq;
	}
	/**
	 * 
	 * @param i
	 * @param column
	 */
	public void updateRow(int i, Square rowNum){
		this.square[i] = rowNum;
	}
	/**
	 * 
	 * @param rowNum The row number you want to update
	 * @param column[] an array of columns from a side.
	 */
	public void updateAllRow(int rowNum, Column column[]){
		for(int i=0; i<square.length; i++)
			square[i]= column[i].getSquares()[rowNum];
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
