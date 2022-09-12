package forest;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

/*
 * 
 */
public class ExtendJPanel extends JPanel {

	/* */
	private Model model;

	/* */
	private Graphics graphics;

	/*
	 * 
	 */
	public ExtendJPanel(Model aModel){

		this.model = aModel;

		return;
	}
	
	/*
	 * 
	 */
	public void addLine(){

		return;
	}
	
	/*
	 * 
	 */
	public void paintComponent( Graphics g ){
		this.graphics = g;
		super.paintComponent(this.graphics);
		
		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		ArrayList<Point> nowPointList = this.model.getPosition();
		ArrayList<String> nodes = this.model.getNodes();
		
		for(Integer address : branchesMap.keySet()){
			JLabel aLabelLeft = new JLabel(nodes.get(address));
			JLabel aLabelRight = new JLabel(nodes.get(branchesMap.get(address)));
			Dimension aDimension = new Dimension(7 * nodes.get(address).length(), 15);
			Point aPoint = new Point(5, (aDimension.height * (address-1)) + 5*(address-1));

			// aLabelLeft.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			// aLabelLeft.setBounds(aPoint.x, aPoint.y , aDimension.width, aDimension.height);
			// aLabelLeft.setBorder(new LineBorder(Color.BLACK, 1, false));
			// aLabelLeft.setHorizontalAlignment(JLabel.CENTER);
			// //サイズの自動化？
			// aLabelLeft.setSize(aLabelLeft.getPreferredSize());

			aDimension = new Dimension(7 * nodes.get(branchesMap.get(address)).length(), 15);
			aPoint = new Point(5, (aDimension.height * (branchesMap.get(address)-1)) + 5*(branchesMap.get(address)-1));
			aLabelRight.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			aLabelRight.setBounds(aPoint.x, aPoint.y , aDimension.width, aDimension.height);
			aLabelRight.setBorder(new LineBorder(Color.BLACK, 1, false));
			aLabelRight.setHorizontalAlignment(JLabel.CENTER);
			//サイズの自動化？
			aLabelRight.setSize(aLabelRight.getPreferredSize());
			

			// System.out.println(address);
			Point aPointLeft = nowPointList.get(address);
			Point aPointRight = nowPointList.get(branchesMap.get(address));
	
			if(branchesMap.get(address) != null){
				this.graphics.setColor(Color.red);
				this.graphics.drawLine(aPointLeft.x, aPointLeft.y + (15/2), aPointRight.x + aLabelRight.getWidth(), aPointRight.y + (15/2));

				System.out.println(aLabelRight.getWidth());
			}
		}

		// for(Map.Entry<Integer, Integer> entry : branchesMap.entrySet()) {
		// 	// System.out.println(entry.getKey() + ":" + entry.getValue());
		// }
		
		return;
	}	
}