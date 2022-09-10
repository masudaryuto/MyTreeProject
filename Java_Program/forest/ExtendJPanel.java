package forest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.awt.Point;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;

public class ExtendJPanel extends JPanel {

	private Model model;

	private Graphics graphics;

	public ExtendJPanel(Model aModel){

		this.model = aModel;

		return;
	}
	
	public void addLine(){

		return;
	}
	

	public void paintComponent( Graphics g ){
		this.graphics = g;
		super.paintComponent(this.graphics);

		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		ArrayList<Point> nowPointList = this.model.getPosition();
		
		for(Integer address : branchesMap.keySet()){
			// System.out.println(address);
			Point aPointLeft = nowPointList.get(address);
			Point aPointRight = nowPointList.get(branchesMap.get(address));
	
			if(branchesMap.get(address) != null){
				this.graphics.setColor(Color.red);
				this.graphics.drawLine(aPointLeft.x, aPointLeft.y, aPointRight.x, aPointRight.y);
			}
		}

		for(Map.Entry<Integer, Integer> entry : branchesMap.entrySet()) {
			// System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		
		return;
	}	
}