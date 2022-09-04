package forest;

public class TreeAlignment extends Object {

	public static void main(String[] args) {

		Model aModel = new Model();
		Controller aController = new Controller(aModel);

		View aView = new View(aModel, aController);

		aView.displayWindow();

	}

}
