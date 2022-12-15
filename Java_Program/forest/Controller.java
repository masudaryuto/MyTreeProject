package forest;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Color;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.Cursor;

/*
 * 
*/
public class Controller extends Object implements MouseListener, MouseMotionListener {
	
	private Model model;

	private View view;

	private Boolean clickFlag;

	private Point previous;
	private Point current;

	public Controller(Model aModel) {
		super();
		this.model = aModel;
		this.view = null;
		this.clickFlag = false;
		this.previous = null;
		this.current = null;

		return;
	}

	public void chooseText() {
		return;
	}

	public void moveTree() { return; }

	public void mouseClicked(MouseEvent e){ return; }
	public void mouseEntered(MouseEvent e){ return; }
	public void mouseExited(MouseEvent e){ return; }
	public void mousePressed(MouseEvent e){ 
		System.out.println("mouse Click");
	
		this.clickFlag ^= true;
	
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		Component aComponent = (Component)e.getSource();
		aComponent.setCursor(aCursor);
	
		this.current = e.getPoint();
		this.previous = this.current;
		return; 
	}

	public void mouseReleased(MouseEvent e){
		Cursor aCursor = Cursor.getDefaultCursor();
		Component aComponent = (Component)e.getSource();
		aComponent.setCursor(aCursor);

		return; 
	}

	
	public void mouseMoved(MouseEvent e){ return; }

	public void mouseDragged(MouseEvent e){
		System.out.println("mouseDragged");
		this.current = e.getPoint();
		int x = this.current.x - this.previous.x;
		int y = this.current.y - this.previous.y;
		ArrayList<Point> aPointList = this.model.getPosition();
		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		ArrayList<String> nodes = this.model.getNodes();

		//カーソル
		Cursor aCursor = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR);
		Component aComponent = (Component)e.getSource();
		aComponent.setCursor(aCursor);
		
		
		

		System.out.printf("point.x:%d, point.y:%d\n", this.current.x, this.current.y);
		//サイズの自動化？
		
		for(int address=1; address<nodes.size(); address++){
			JLabel aLabel = new JLabel(nodes.get(address));
			Dimension aDimension = new Dimension(7 * nodes.get(address).length(), 15);
			Point aPoint = new Point(5, (aDimension.height * (address-1)) + 5*(address-1));
			aLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			aLabel.setBounds(aPoint.x, aPoint.y , aDimension.width, aDimension.height);
			aLabel.setBorder(new LineBorder(Color.BLACK, 1, false));
			aLabel.setHorizontalAlignment(JLabel.CENTER);

			Point aNodePoint = aPointList.get(address);
			// aLabel.setSize(aLabel.getPreferredSize());

			

			// System.out.println();
			//x
			// if(aNodePoint.x < this.current.x && this.current.x < aNodePoint.x + aLabel.getWidth()){
			// 	//y
			// 	if(aNodePoint.y < this.current.y && this.current.y < aNodePoint.y + aLabel.getHeight()){
			// 		System.out.println(nodes.get(address));
			// 		System.out.printf("x：%d < %d < %d\n", aNodePoint.x, this.current.x, aNodePoint.x + aLabel.getWidth());
			// 		System.out.printf("y：%d < %d < %d\n", aNodePoint.y + (2*15), this.current.y, aNodePoint.y + aLabel.getHeight() + (2*15));
					
			// 		this.model.UpdatePosition(address, aNodePoint.x + x, aNodePoint.y + y);

			// 		this.view.moveLabel(address);

			// 		break;
			// 	}
			// }
			
			this.model.UpdatePosition(address, aNodePoint.x + x, aNodePoint.y + y);
			
			this.view.moveLabel(address);
		
			
			
		}

		
		System.out.println(x);
		System.out.println(y);
		this.previous = this.current;


		return;
	}

	public void setModel(Model aModel){
		this.model = aModel;

		return;
	}
	
	public void setView(View aView){
		this.view = aView;

		return;
	}
}
