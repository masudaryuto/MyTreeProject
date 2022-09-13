package forest;

/*
 * 樹上整列のメインプログラムです。
 */
public class TreeAlignment extends Object {
	/*メインクラスです。*/
	public static void main(String[] args) {
		//モデルのインスタンスを生成します。
		Model aModel = new Model();
		//コントローラのインスタンスを生成します。
		Controller aController = new Controller(aModel);
		//ビューのインスタンスを生成します。
		View aView = new View(aModel, aController);

		// windowを表示させます。
		aView.displayWindow();

		return;
	}
}
