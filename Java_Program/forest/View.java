package forest;

import java.awt.Point;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.accessibility.Accessible;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.util.HashMap;

/*
 * 
 */
public class View extends JComponent implements Accessible {
	
	/* */
	private Controller controller;
	
	/* */
	private Model model;

	/* */
	private ArrayList<JLabel> nodeLabel;

	/* */
	ExtendJPanel panel;

	/* */
	public View(Model aModel, Controller aController) {
		super();
		this.model = aModel;
		this.controller = aController;
		this.nodeLabel = new ArrayList<>();
		this.panel = new ExtendJPanel(aModel);

		this.controller.setView(this);
		return;
	}

	/* */
	public void displayWindow() {
		// ウィンドウのインスタンスを生成する。
		JFrame aWindow = new JFrame("TreeAlignment");

		// コンポーネントのインスタンスを生成してウィンドウに加える。
		// Example aComponent = new Example();
		// aWindow.add(aComponent);

		//ファイル選択
		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
			"Text File", "txt");
			chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(aWindow);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			System.out.println("You chose to open this file: " +
			chooser.getSelectedFile().getName());
			this.model.readText(chooser.getSelectedFile());
			
			//マウスイベント
			aWindow.addMouseListener(this.controller);
			aWindow.addMouseMotionListener(this.controller);
			// 高さはタイトルバーの高さを考慮してウィンドウの大きさを決定する。
			aWindow.addNotify();
			int titleBarHeight = aWindow.getInsets().top;
			aWindow.setSize(600, 400 + titleBarHeight);
			aWindow.setResizable(true);
			// ウィンドウに各種の設定を行って出現させる。
			aWindow.setLocationRelativeTo(null);
			aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			aWindow.setVisible(true);
			aWindow.toFront();
			//描画の更新
			this.updateWindow(aWindow);
		}
		
		
		// this.displayWindow();

		return;
	}

	/* */
	public void updateWindow(JFrame aWindow) {

		this.panel.setLayout(null);
		aWindow.getContentPane().add(this.panel, BorderLayout.CENTER);

		// aWindow.add(this.panel);

		ArrayList<String> nodes = this.model.getNodes();
		this.nodeLabel.add(new JLabel("-1"));

		//setup
		for(int i=1; i<nodes.size(); i++){
			JLabel aLabel = new JLabel(nodes.get(i));
			this.nodeLabel.add(aLabel);
		}

		// 全ての単語を表示
		for(int i=1; i<nodes.size(); i++){
			Dimension aDimension = new Dimension(7 * nodes.get(i).length(), 15);
			Point aPoint = new Point(5, (aDimension.height * (i-1)) + 5*(i-1));
			this.nodeLabel.get(i).setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			this.nodeLabel.get(i).setBounds(aPoint.x, aPoint.y , aDimension.width, aDimension.height);
			this.nodeLabel.get(i).setBorder(new LineBorder(Color.BLACK, 1, false));
			this.nodeLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			//サイズの自動化？
			this.nodeLabel.get(i).setSize(this.nodeLabel.get(i).getPreferredSize());
			this.panel.add(this.nodeLabel.get(i));

			this.model.UpdatePosition(i, aPoint.x, aPoint.y);
		}

		//this.drawLine();

		
		// アニメーション処理
		this.animation();

		return;
	}

	/* アニメーション実行させます。 */
	public void animation(){
		
		// * それぞれの単語の初期座標を記録して、関数を利用して、その単語の座標が変わったら、
		// その単語の座標を変更できるようにする必要がある。
		
		this.simpleWriteLabel();

		// this.controller.setModel(this.model);

		
		return;
	}
	
	/* ラベル移動 */
	public void simpleWriteLabel(){
		
		ArrayList<Integer> rootList = this.model.getRoot();
		ArrayList<String> nodes = this.model.getNodes();

		// 3番目を消す。
		// this.nodeLabel.get(3).setVisible(false);

		//ノードを描画
		for(int i=0; i<rootList.size(); i++){

			int address = rootList.get(i);
			// System.out.println(address)
			// this.drawLabel(address);

			Dimension aDimension = new Dimension(7 * nodes.get(address).length(), 15);
			Point aPoint = new Point(15, (aDimension.height * (i)) + 200*(i+1));

			this.nodeLabel.get(address).setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			this.nodeLabel.get(address).setBounds(aPoint.x, aPoint.y, aDimension.width, aDimension.height);
			this.nodeLabel.get(address).setBorder(new LineBorder(Color.BLACK, 2, false));
			this.nodeLabel.get(address).setHorizontalAlignment(JLabel.CENTER);

			//サイズの自動化？
			this.nodeLabel.get(address).setSize(this.nodeLabel.get(address).getPreferredSize());
			this.panel.add(this.nodeLabel.get(address));

			this.model.UpdatePosition(address, aPoint.x, aPoint.y);

			this.drawLabel(address);

		}
		
		
		return;
	}
	
	/* */
	public void drawLabel(int address){
		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		ArrayList<String> nodes = this.model.getNodes();
		boolean flag = true;
		int countReef = 0;
		for(Integer nowAddress : branchesMap.values()){

			if( nowAddress.equals(address)){
				flag = false;
				countReef++;
			}
		}
		//最端に辿り着いた時。
		if(flag){
			return; 
		}


		int h=1;
		
		for(Integer nowAddress : branchesMap.keySet()){
			
			//nowAddressは子供 reef、addressは親root
			if( branchesMap.get(nowAddress).equals(address)){
				
				
				ArrayList<Point> aPointList = this.model.getPosition();
				
				Dimension aDimension = new Dimension(7 * nodes.get(nowAddress).length(), 15);
				// Point aPoint = new Point(aPointList.get(address).x + 100,  ((aPointList.get(address).y) + ((h-1) * (aPointList.get(address).y / countReef) + (5 * aDimension.height))) - ((countReef / 2) * (aPointList.get(address).y / countReef)) );
				// (親の葉のy座標) - ((葉の数/2) * 幅) + ((葉の数 - 1) * 幅)
				int width = 2 * aDimension.height;
				Point aPoint = new Point(aPointList.get(address).x + 150, (aPointList.get(address).y - ((countReef / 2) * (width))) + ((h - 1) * (width)));
				
		
				this.nodeLabel.get(nowAddress).setFont(new Font(Font.SERIF, Font.PLAIN, 12));
				this.nodeLabel.get(nowAddress).setBounds(aPoint.x, aPoint.y, aDimension.width, aDimension.height);
				this.nodeLabel.get(nowAddress).setBorder(new LineBorder(Color.BLACK, 2, false));
				this.nodeLabel.get(nowAddress).setHorizontalAlignment(JLabel.CENTER);
	
				//サイズの自動化？
				this.nodeLabel.get(nowAddress).setSize(this.nodeLabel.get(nowAddress).getPreferredSize());
				this.panel.add(this.nodeLabel.get(nowAddress));
	
				this.model.UpdatePosition(nowAddress, aPoint.x, aPoint.y);

				System.out.println(nodes.get(nowAddress));
				
				
				//10ms待機
				this.sleep(100);

				//再描画
				this.panel.repaint();

				//親の葉の子供の葉を描画
				this.drawLabel(nowAddress);
				h++;


			}
		}
		
		this.model.formText(address);

		return;
	}

	public void moveLabel(int address){
		HashMap<Integer, Integer> branchesMap = this.model.getBranchMap();
		ArrayList<String> nodes = this.model.getNodes();
				
		ArrayList<Point> aPointList = this.model.getPosition();
				
		Dimension aDimension = new Dimension(7 * nodes.get(address).length(), 15);
		// Point aPoint = new Point(aPointList.get(address).x + 100,  ((aPointList.get(address).y) + ((h-1) * (aPointList.get(address).y / countReef) + (5 * aDimension.height))) - ((countReef / 2) * (aPointList.get(address).y / countReef)) );
		// (親の葉のy座標) - ((葉の数/2) * 幅) + ((葉の数 - 1) * 幅)
		Point aPoint = aPointList.get(address);
		

		this.nodeLabel.get(address).setFont(new Font(Font.SERIF, Font.PLAIN, 12));
		this.nodeLabel.get(address).setBounds(aPoint.x, aPoint.y, aDimension.width, aDimension.height);
		this.nodeLabel.get(address).setBorder(new LineBorder(Color.BLACK, 2, false));
		this.nodeLabel.get(address).setHorizontalAlignment(JLabel.CENTER);

		//サイズの自動化？
		this.nodeLabel.get(address).setSize(this.nodeLabel.get(address).getPreferredSize());
		this.panel.add(this.nodeLabel.get(address));

		// this.model.UpdatePosition(address, aPoint.x, aPoint.y);

		// System.out.println(nodes.get(address));
		
		
		//10ms待機
		// this.sleep(100);

		//再描画
		this.panel.repaint();



		return;
	}
	/* */
	public void sleep(long ms){

		try{
			Thread.sleep(ms);
		}catch(InterruptedException e){
			System.err.println(e);
		}

		return;
	}
	
	/* */
	public void deleteWordLabel(){

		return;
	}
}
