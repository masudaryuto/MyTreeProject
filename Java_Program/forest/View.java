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
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class View extends JComponent implements Accessible {
	
	private Controller controller;
	
	private Model model;

	public View(Model aModel, Controller aController) {

		this.model = aModel;
		this.controller = aController;

		return;
	}

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


			this.updateWindow(aWindow);
		}
		
		
		
		// 高さはタイトルバーの高さを考慮してウィンドウの大きさを決定する。
		aWindow.addNotify();
		int titleBarHeight = aWindow.getInsets().top;
		aWindow.setSize(600, 400 + titleBarHeight);
		aWindow.setResizable(false);
		// ウィンドウに各種の設定を行って出現させる。
		aWindow.setLocationRelativeTo(null);
		aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aWindow.setVisible(true);
		aWindow.toFront();



		return;
	}

	public void updateWindow(JFrame aWindow) {

		JPanel aPanel = new JPanel();
		aPanel.setLayout(null);
		aWindow.getContentPane().add(aPanel, BorderLayout.CENTER);

		// aWindow.add(aPanel);
		
		ArrayList<String> nodes = this.model.getNodes();
		ArrayList<JLabel> nodeLabel = new ArrayList<>();
		nodeLabel.add(new JLabel("-1"));
		//setup
		for(int i=1; i<nodes.size(); i++){
			JLabel aLabel = new JLabel(nodes.get(i));
			nodeLabel.add(aLabel);
		}

		for(int i=1; i<nodes.size(); i++){
			Dimension aDimension = new Dimension(7 * nodes.get(i).length(), 15);
			Point aPoint = new Point(5, (aDimension.height * (i-1)));
			nodeLabel.get(i).setFont(new Font(Font.SERIF, Font.PLAIN, 12));
			nodeLabel.get(i).setBounds(aPoint.x, aPoint.y, aDimension.width, aDimension.height);
			nodeLabel.get(i).setBorder(new LineBorder(Color.BLACK, 1, false));
			nodeLabel.get(i).setHorizontalAlignment(JLabel.CENTER);
			//サイズの自動化？
			nodeLabel.get(i).setSize(nodeLabel.get(i).getPreferredSize());
			aPanel.add(nodeLabel.get(i));
		}
		
		// aLabel.setBounds(10, 10 + (15 * i), 30, 15);


		return;
	}

}
