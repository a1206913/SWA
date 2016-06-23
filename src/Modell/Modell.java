package Modell;

public class Modell {

	private int COL = 7;
	private int ROW = 6;
	private int[][] tableArray;

	public int getCOL() {
		return COL;
	}

	public void setCOL(int cOL) {
		COL = cOL;
	}

	public int getROW() {
		return ROW;
	}

	public void setROW(int rOW) {
		ROW = rOW;
	}

	public Modell() {
		tableArray = new int[ROW][COL];
		for (int row = 0; row < ROW; row++)
			for (int col = 0; col < COL; col++)
				tableArray[row][col] = 0;
		//		print();
	}
	
	public void clear() {
		tableArray = new int[ROW][COL];
		for (int row = 0; row < ROW; row++)
			for (int col = 0; col < COL; col++)
				tableArray[row][col] = 0;
	}

	public int getItem(int column, int row) {
		return tableArray[column][row];
	}

	public void setItem(int row, int column, int player) {
		tableArray[row][column] = player;
	}

	//take the column and search the lowest value that not null is
	//send back the raw in that we will change the color
	public int getFreeRow(int column, int player) {

		//        search from the buttom of the row
		for (int row = ROW - 1; row >= 0; row--) {
			if (tableArray[row][column] == 0) {

				setItem(row, column, player); // set the player in the Modell matrix
				//				print();
				return row;
			}
		}
		return -1;
	}

	public Boolean isPopOut(int column, int player) {
		if (tableArray[ROW - 1][column] == player) {
			return true;
		}
		return false;
	}

	public int[] popOutDisc(int column, int player) {
		int[] columnArray = new int[ROW];

		for (int i = ROW - 1; i >= 0; i--) {
			if (i == 0)
				tableArray[i][column] = 0;
			else
				tableArray[i][column] = tableArray[i - 1][column];
			columnArray[i] = tableArray[i][column];
		}

		return columnArray;
	}

	public Boolean horizontal_check(int player) {

		// boolean win = false;
		int reps = 0;
		for (int row = 5; row >= 0; row--) {

			for (int col = 0; col < 7; col++) {

				if (tableArray[col][row] == player) {
					++reps;
				} else {
					reps = 0;
				}

				if (reps >= 4) {
					return true;
				} else {
					reps = 0;
				}
			}
		}
		return false;
	}

	public void print() {
		for (int row = 0; row < ROW; row++) {
			String data = "";
			for (int col = 0; col < COL; col++) {
				data += tableArray[row][col];
			}
		}

	}

	public boolean isWon(int player, int currentRow, int currentCol) {
		System.out.println("Player" + player);
		System.out.println("currentRow" + currentRow);
		System.out.println("currentCol" + currentCol);
		
		if(!(currentCol == -1 || currentRow == -1)) {
			
		
		int count_verical = vertical_check(player, currentRow, currentCol);
		int count_horisontal = horisontal_check(player, currentRow, currentCol);
		int count_diagonal = diagonal_check(player, currentRow, currentCol);

		if (count_verical >= 4 || count_horisontal >= 4 || count_diagonal >= 4)
			return true;
		}
		
		
		return false;
	}

	public int vertical_check(int player, int currentRow, int currentCol) {
		int reps = 0;
		for (int i = currentRow; i < ROW; i++) {
			if (tableArray[i][currentCol] == player) {
				reps++;
			} else {
				break;
			}

		}

		return reps;
	}

	public int horisontal_check(int player, int currentRow, int currentCol) {
		int count = 0;

		//links
		for (int col = currentCol; col >= 0; col--) {
			if (tableArray[currentRow][col] == player) {
				count++;
			} else {
				break;
			}
		}

		//rechts
		for (int col = (currentCol + 1); col < COL; col++) {

			if (tableArray[currentRow][col] == player) {
				count++;
			} else {
				break;
			}
		}
		/*
		 * if(count >= 4){ return true; }
		 */
		return count;
	}

	public int diagonal_check(int player, int currentRow, int currentCol) {
		//print();
		int count = 0;
		//recht, oben
		int row = currentRow;
		int col = currentCol;
		while (row >= 0 && col < COL) {
			if (tableArray[row][col] == player) {
				count++;
			} else
				break;
			row--;
			col++;
		}

		//recht, unten
		row = currentRow + 1;
		col = currentCol + 1;
		while (row < ROW && col < COL) {
			if (tableArray[row][col] == player) {
				count++;
			} else
				break;
			row++;
			col++;
		}

		//links, oben
		row = currentRow - 1;
		col = currentCol - 1;
		while (row >= 0 && col >= 0) {
			if (tableArray[row][col] == player) {
				count++;
			} else
				break;
			row--;
			col--;
		}

		//links, unten
		row = currentRow + 1;
		col = currentCol - 1;
		while (row < ROW && col >= 0) {
			if (tableArray[row][col] == player) {
				count++;
			} else
				break;
			row++;
			col--;
		}

		return count;
	}

}
