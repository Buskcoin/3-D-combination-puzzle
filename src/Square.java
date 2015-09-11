import java.awt.Color;


public class Square {
	private char color;
	private Color draw;
	
	public Square(char color){
		this.color = color;
	}
	public char getColor(){
		return this.color;
	}
	public Color getCol(){
		checkColor();
		return this.draw;
	}
	private void checkColor(){
		switch(color){
		case '0': draw = Color.BLUE;
		break;
		case '1': draw = Color.YELLOW;
		break;
		case '2': draw = Color.RED;
		break;
		case '3': draw = Color.LIGHT_GRAY;
		break;
		case '4': draw = Color.ORANGE;
		break;
		case '5': draw = Color.GREEN;
		break;
		}
	}
}
