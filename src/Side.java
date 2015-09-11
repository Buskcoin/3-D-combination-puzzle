public class Side {
	private Row[] row;
	private Column[] column;
	private boolean flipped = false;
	
	public Side(char color) {
		row = new Row[3];
		column = new Column[3];
		for (int i = 0; i < row.length; i++) {
			row[i] = new Row(color);
			column[i] = new Column(color);
		}
	}

	public void setRow(int i, Row row) {
		this.row[i] = row;
		for (int j = 0; j < column.length; j++)
			column[j].updateColumns(i, row.getSquares()[j]);
	}

	public void setColumn(int i, Column column) {
		this.column[i] = column;
		for (int j = 0; j < row.length; j++)
			row[j].updateRow(i, column.getSquares()[j]);
	}

	public Row[] getAllRows() {
		return this.row;
	}

	public Row getRow(int i) {
		return row[i];
	}

	public Column[] getAllColumns() {
		return this.column;
	}

	public void flipSide() {
		flipColumns();
		for (int i = 0; i < column.length; i++) {
			this.column[i].reverse();
			
			for (int j = 0; j < row.length; j++)
				row[j].updateRow(i, column[i].getSquares()[j]);
		}
		if(flipped)
			flipped = false;
		else
			flipped = true;
	}
	
	public boolean flipped(){
		return this.flipped;
	}

	public Column getColumn(int i) {
		return column[i];
	}

	public void flipColumns() {
		Column temp  = column[0];
			column[0] = column[2];
			column[2] = temp;
	}
	/**
	 * sets the column opposite to the column specified
	 * @param i column number
	 * @param column
	 */
	public void setBacksideColumn(int i, Column column) {
		int test;
		if (i == 2)
			test = 0;
		else if (i == 0)
			test = 2;
		else
			test = i;
			this.column[i] = column;
		
		for (int j = 0; j < row.length; j++)
			row[j].updateRow(i, column.getSquares()[j]);
	}

	/**
	 * 
	 * @param clockwise
	 */
	public void swapRowsAndColumns(boolean clockwise) {
		if(clockwise){
			for (int i = 0; i < column.length; i++) {
				Square[] colSquare = column[i].getSquares();
				int counter = 2;
				for (int j = 0; j < colSquare.length; j++) {
					this.row[i].setSquare(counter, colSquare[j]);
					counter--;
				}

			}
			for (int i = 0; i < column.length; i++)
				this.column[i].updateAllColumns(i, row);
		}
		else{
		for (int i = 0; i < row.length; i++) {
			Square[] rowSquare = row[i].getSquares();
			int counter = 2;
			for (int j = 0; j < rowSquare.length; j++) {
				this.column[i].setSquare(counter, rowSquare[j]);
				counter--;
			}

		}
		for (int i = 0; i < row.length; i++)
			this.row[i].updateAllRow(i, column);
	}
	}
	// }
	public String toString() {
		String test = "";
		for (int i = 0; i < row.length; i++) {
			test += row[i].toString();
			test += "\n";
		}
		test += "\n";
		return test;
	}

	/**
	 * Checks if rows and columns match
	 */
	public boolean rowCol() {
		boolean match = false;
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (row[i].getSquares()[j].getColor() == column[j].getSquares()[i]
						.getColor())
					match = true;
				else {
					System.out.println(" error on row " + i + " col " + j);
					return false;
				}
			}
		}
		return match;
	}

}
