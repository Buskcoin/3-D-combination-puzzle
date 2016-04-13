import javax.swing.JFrame;

/**
 * Small Cube experiment and this is my Commment
 */
public class Main extends JFrame {
	private Cube rubic;
	public Main(){
		this.setTitle("Cube");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rubic = new Cube();
        add(rubic);
        this.setVisible(true);
       
	}
	public static void main(String[] args) {
		new Main();
	/*	System.out.println("Original:");
		System.out.println(rubic.sides[0].rowCol());
		System.out.println(rubic.toString());
		
		
		rubic.rotateRow(0);
		rubic.sides[5].rowCol();
		System.out.println("rotateRow");
		System.out.println(rubic.toString());
		
		rubic.rotateRow(1);
		System.out.println("\nrotated Column");
		rubic.rotateColumn(0);
		
		rubic.rotateRow(2);
		System.out.println(rubic.toString());

		System.out.println(rubic.sides[0].rowCol());*/
	}
}
