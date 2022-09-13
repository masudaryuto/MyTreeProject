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

/*
 * 
*/
public class Controller extends Object implements MouseListener, MouseMotionListener {
	
	private Model model;

	private View view;

	private Boolean clickFlag;

	public Controller(Model aModel) {
		super();
		this.model = aModel;
		this.view = null;
		this.clickFlag = false;

		return;
	}

	public void chooseText() {
		return;
	}

	public void moveTree() {



		return;
	}

	public void mouseClicked(MouseEvent e){ 

		this.clickFlag ^= true;


		return; 
	}
	public void mouseEntered(MouseEvent e){ return; }
	public void mouseExited(MouseEvent e){ return; }
	public void mousePressed(MouseEvent e){ return; }
	public void mouseReleased(MouseEvent e){ return; }

	
	public void mouseMoved(MouseEvent e){
		// System.out.println("mouseMoved");
		
		return;
	}

	public void mouseDragged(MouseEvent e){
		System.out.println("mouseDragged");
		Point aClickPoint = e.getPoint();
		ArrayList<Point> aPointList = this.model.getPosition();
		
		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		
		ArrayList<String> nodes = this.model.getNodes();


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
			aLabel.setSize(aLabel.getPreferredSize());

			// System.out.println(nodes.get(address));
			if(nodes.get(address).equals(" Magnitude")){

				// System.out.printf("x:%d, click.x:%d, aNodePoint.x + aLabel.getWidth():%d\n", aNodePoint.x, aClickPoint.x, aNodePoint.x + aLabel.getWidth());
				// System.out.printf("y:%d, click.y:%d, aNodePoint.y + aLabel.getHeight():%d\n", aNodePoint.y, aClickPoint.y, aNodePoint.y + aLabel.getHeight());
			}
			// System.out.println();
			//x
			if(aNodePoint.x < aClickPoint.x && aClickPoint.x < aNodePoint.x + aLabel.getWidth()){
				//y
				if(aNodePoint.y + (2*15) < aClickPoint.y && aClickPoint.y < aNodePoint.y + aLabel.getHeight() + (2*15)){
					System.out.println(nodes.get(address));

					this.model.UpdatePosition(address, aClickPoint.x, aClickPoint.y - (2*15));

					this.view.moveLabel(address);

					break;
				}
			}
		}


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
