package forest;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * 
 */
public class Model extends Object {

	private View view;

	private File file;

	/* 子葉と親葉の対応付け。 branches 16 -> 1 右の数字 -> 左の数字 */
	private HashMap<Integer, Integer> branchesMap;

	/* 番号詳細、番号を指定することで、ノードを参照できる。 */
	private ArrayList<String> nodeList;

	/* 先頭のnodeを格納 */
	private ArrayList<Integer> rootList;


	private Point point;

	public Model() {
		this.view = null;
		this.file = null;
		this.branchesMap = new HashMap<>();
		this.nodeList = new ArrayList<>();
		this.rootList = new ArrayList<>();
		this.point = null;
		return;
	}

	/* テキストを解析します。 */
	public void readText(File aFile) {
		this.file = aFile;

		// リストは、1から参照したい。
		this.nodeList.add("-1");
		
		try{
			BufferedReader aBufferedReader = new BufferedReader(new FileReader(this.file));
			String line = "";
			boolean nodeFlag = false;
			boolean branchFlag = false;

			while( (line = aBufferedReader.readLine()) != null ){
				if(line.equals("nodes:")){
					nodeFlag = true;
					branchFlag = false;
				}
				else if(line.equals("branches:")){
					nodeFlag = false;
					branchFlag = true;
				}
				//nodes
				else if(nodeFlag){
					//System.out.println((line.split(","))[1]);
					this.nodeList.add((line.split(","))[1]);
				}
				//branches
				else if(branchFlag){
					
					Integer leftNum = Integer.parseInt((line.split(","))[0]);
					Integer rightNum = Integer.parseInt(((line.split(","))[1]).split(" ")[1]);


					this.branchesMap.put(rightNum, leftNum);
				}
			}
			aBufferedReader.close();

		}catch(IOException e){

			System.err.println();
		}

		this.analyText();

		return;
	}

	/* 取得したテキストに対して、根rootを取得します。 */
	public void analyText() {
		
		for(int address=1; address < this.nodeList.size(); address++){
			//System.out.println(nodeList.get(address));

			if(branchesMap.get(address) == null){
				this.rootList.add(address);
			}
		}

		for(int address=0; address < this.rootList.size(); address++){

			System.out.println(this.rootList.get(address));
		}
		return;
	}

	public void formText() {

		return;
	}

	public void decidePosition() {

		return;
	}

	public void setReefAndReef() {

		return;
	}

	public ArrayList<String> getNodes(){

		return this.nodeList;
	}

	public HashMap<Integer, Integer> getBranchMap(){

		return this.branchesMap;
	}

	public ArrayList<Integer> getRoot(){
		
		return this.rootList;
	}


}