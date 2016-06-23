package View;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import Controller.Controller;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;

public class ConnectFourView extends Application {

	GridPane gridpane = new GridPane();

	final static int ROW = 6;
	final static int COL = 7;

	static Circle circles[][] = new Circle[COL][ROW];
	private static Controller controller;
	static String colorP1 = "#FFFF00";
	static String colorP2 = "#00FF00";

	public ConnectFourView() {
		for (int k = 0; k < COL; k++) {
			for (int j = 0; j < ROW; j++) {
				circles[k][j] = new Circle();
				circles[k][j].setRadius(50.0f);
				circles[k][j].setFill(Color.WHITE);
				circles[k][j].setStrokeType(StrokeType.INSIDE);
			}
		}
	}

	public void setCircleColor(int row, int column, Paint color) {
		if (row > -1 && column > -1) { // set color only if row is not occupied 	
			circles[row][column].setFill(color);
		} else {
		}
	}

	public void reorderDiscs(int[][] tableArray, int column) { // when the pull button is clicked
		for (int i = ROW - 1; i > 0; i--) {
			if (tableArray[column][i] == 1) { // set color for disk for Player 1
				circles[column][i].setFill(Color.RED);
			}

			else if (tableArray[column][i] == 2) { // set color for disk for Player 2
				circles[column][i].setFill(Color.YELLOW);
			}

			else if (tableArray[column][i] == 0) { // set color for white disk 
				circles[column][i].setFill(Color.WHITE);
			}
		}
		circles[column][0].setFill(Color.WHITE); // the first row is filled with color white
	}

	public void popWinnerMessage() {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().children(new Text("The winner is: ")).alignment(Pos.CENTER)
				.padding(new Insets(50)).build()));
		dialogStage.show();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// Group root = new Group();
		BorderPane root = new BorderPane();
		ButtonBar buttonBar = new ButtonBar();
		
		Button exitButton = new Button("Quit");
		Button resetButton = new Button("New Game");
		
		//pane.setCenter(resetButton);
		
		ButtonBar.setButtonData(resetButton, ButtonData.YES);
		buttonBar.getButtons().addAll(resetButton);
		root.setBottom(buttonBar);
		//root.setCenter(resetButton);
		
		//resetButton.setAlignment(Pos.CENTER);
		
		ButtonBar.setButtonData(exitButton, ButtonData.YES);
		buttonBar.getButtons().addAll(exitButton);
		root.setBottom(buttonBar);
		root.setCenter(exitButton);
		
		Scene scene = new Scene(root, 700, 650, Color.WHITE);
		gridpane.setGridLinesVisible(true);

		for (int k = 0; k < COL; k++) {
			for (int j = 0; j < ROW; j++) {
				Rectangle r = new Rectangle();
				r.setWidth(100);
				r.setHeight(100);
				r.setFill(Color.BLUE);
				r.setStrokeType(StrokeType.INSIDE);

				final int column = k;
				final int row = j;
				circles[k][j].addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
					@Override
					public void handle(MouseEvent event) {
						controller.addEventFilter(event, column, row);
					}
				});

				gridpane.add(r, k, j);
				gridpane.add(circles[k][j], k, j);

			}
		}

		root.setCenter(gridpane);
		primaryStage.setScene(scene);
		primaryStage.show();
		
		resetButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				controller.resetButton(event);
			}
		});
		
		exitButton.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				exitStage();
				//startGame(controller);
			}
		});
}
	
	@SuppressWarnings("deprecation")
	public void popWinnerMessage(int player) {
		Stage dialogStage = new Stage();
		dialogStage.initModality(Modality.WINDOW_MODAL);
		dialogStage.setScene(new Scene(VBoxBuilder.create().children(new Text("The winner is player: " + player))
				.alignment(Pos.CENTER).padding(new Insets(50)).build()));
		dialogStage.show();

	}

	public void exitStage() {
		Platform.exit();
		System.exit(0);
	}

	public void startGame(Controller controller) {
		this.controller = controller;
		launch();
	}
}