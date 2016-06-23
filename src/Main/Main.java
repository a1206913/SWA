package Main;

import Controller.Controller;
import Modell.Modell;
import View.ConnectFourView;

public class Main {

	public static void main(String[] args) {

		Modell model = new Modell();
		ConnectFourView view = new ConnectFourView();

		Controller contr = new Controller(model, view);

		view.startGame(contr);
	}
}
