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

public class Model extends Object {

	private View view;

	private File file;

	private HashMap<String, String> textMap;
	private ArrayList<String> nodeList;


	private Point point;

	public Model() {
		view = null;
		file = null;
		textMap = new HashMap<>();
		nodeList = new ArrayList<>();
		point = null;
		return;
	}

	public void readText(File aFile) {
		this.file = aFile;

		// リストは、1から参照したい。
		nodeList.add("-1");
		
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


					textMap.put(this.nodeList.get(rightNum), this.nodeList.get(leftNum));
				}
				
			}
			System.out.println(this.nodeList.get(0));

			aBufferedReader.close();

		}catch(IOException e){

			System.err.println();
		}

		
		return;
	}

	public void analyText() {

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

	public HashMap<String, String> getBranchMap(){

		return this.textMap;
	}

}
