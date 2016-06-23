package Controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import com.sun.glass.ui.View;

import Modell.Modell;
import View.ConnectFourView;

public class Controller {

	private int player = 1;
	private ConnectFourView view;
	private Modell model;

	private boolean closeStage = false; // closing the stage

	public Controller(Modell model, ConnectFourView view) {
		this.view = view;
		this.model = model;
	}

	public void addEventFilter(MouseEvent event, int column, int row) {

		Boolean isPopOut = false;

		/*if (closeStage) {
			view.exitStage();
		}*/

		if (row == (model.getROW() - 1)) {
			isPopOut = model.isPopOut(column, player);
		}

		if (isPopOut) {
			int[] columnArray = model.popOutDisc(column, player);
			for (int i = 0; i < columnArray.length; i++) {
				if (columnArray[i] == 1)
					view.setCircleColor(column, i, Color.RED);
				else if (columnArray[i] == 2)
					view.setCircleColor(column, i, Color.YELLOW);
				else
					view.setCircleColor(column, i, Color.WHITE);
			}
			for (int rowW = 0; rowW < model.getROW(); rowW++) {
				for (int colW = 0; colW < model.getCOL(); colW++) {

					boolean isWonPlayer1 = model.isWon(1, rowW, colW);
					if (isWonPlayer1) {
						view.popWinnerMessage(1);// pop the Winner dialog  box out
						closeStage = true;
					}
					boolean isWonPlayer2 = model.isWon(2, rowW, colW);
					if (isWonPlayer2) {
						view.popWinnerMessage(1);// pop the Winner dialog  box out
						closeStage = true;
					}

				}
			}
		} else {
			int currentRow = model.getFreeRow(column, player);
			if (player == 1) { // add a disc for Player 1 if not a pull request
				view.setCircleColor(column, currentRow, Color.RED);

				closeStage = model.isWon(player, currentRow, column);
				if (closeStage) {
					view.popWinnerMessage(1);// pop the Winner dialog  box out
				}

			} else { // add a disc for Player 2 if not a pull request
				view.setCircleColor(column, currentRow, Color.YELLOW);

				closeStage = model.isWon(player, currentRow, column);
				if (closeStage) {
					view.popWinnerMessage(2);// pop the Winner dialog  box out
				}

			}
		}
		//resetButton();
		changePlayer();
	}
	
	public void resetButton(MouseEvent event) {
		model.clear();
		for (int rowW = 0; rowW < model.getROW(); rowW++) {
			for (int colW = 0; colW < model.getCOL(); colW++) {
				view.setCircleColor(colW, rowW, Color.WHITE);
			}
		}		
	}

	private void changePlayer() {
		if (player == 1) {
			player = 2;
		} else if (player == 2)
			player = 1;
	}
}