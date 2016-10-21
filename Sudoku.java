public class Sudoku {
	static boolean buscando = true;

	public static void main(String[] args) {
		mostrarTablero(Sudoku.getSudoku());
	}

	/**
	 * Funci�n que utiliza el algoritmo de vuelta atr�s para crear un panel de sudoku aleatorio.
	 * Almacena el "sudoku" en la posicion sudoku [m][n][0]. Donde m y n sn filay columna.
	 * @param tablero Matriz c�bica que almacena:
	 * 			- En su parte "cuadrada" El tablero del sudoku.
	 * 			- En su parte "profunda" una lista de los n�meros que se han utilizado en cada casilla.
	 * @param fila Fila por la que estamos trabajando.
	 * @param columna Columna con la que esstamos trabajando
	 * @return 0
	 */
	public static int sudoku(int[][][] tablero, int fila, int columna) {
		int num, cont = 1;
		/*To try:
		 * if (fila == 8 && columna == 7) {
			mostrarTablero(tablero);
		}
		*/
		num = (int) Math.ceil(Math.random() * 9);
		while (buscando && cont < 10) {
			tablero[fila][columna][0] = num;
			//To try: mostrarTablero(tablero);
			if (esValido(tablero, fila, columna, num)) {
				tablero[fila][columna][num] = 0;
				if (fila == 8 && columna == 8) {
					buscando = false;
				} else {
					if (columna < 8)
						sudoku(tablero, fila, columna + 1);
					else
						sudoku(tablero, fila + 1, 0);
				}
			} else {
				tablero[fila][columna][0] = 0;
				num++;
				if (num == 10)
					num = 1;
				cont++;
			}
		}
		if (cont == 10 && buscando) {
			for (int i = 1; i < 10; i++)
				tablero[fila][columna][i] = i;
			if (columna == 0) {
				fila--;
				columna = 8;
			} else {
				columna--;
			}
			sudoku(tablero, fila, columna);
		}
		return 0;
	}
	/**
	 * Funcion que comprueba si un valor es v�lido. Es decir,
	 * si el n�mero almacenado en esa casilla se repite en la fila, la columna, o su cuadrado.
	 * @param tab tablero que almacena el sudoku y los n�meros que se han usado de cada casilla
	 * @param fil fila con la que estamos trabajando
	 * @param col columna con la que estamos trabajando.
	 * @param n numero que intentamos fijar en esa casilla
	 * @return True si cumple que no se repite ne fila, columna y cuadrado. False, en caso contrario.
	 */
	private static boolean esValido(int[][][] tab, int fil, int col, int n) {
		boolean valido = true;
		int k;
		// Comprobar cuadrado
		for (int x = fil - (fil % 3); x < fil; x++) {
			k = 0;
			for (int y = col - (col % 3); k < 3; y++, k++) {
				if (tab[x][y][0] == n) {
					valido = false;
				}
			}
		}
		// Comprobar fila
		int colu = 0;
		while (valido && colu < col) {
			if (tab[fil][colu][0] == n)
				valido = false;
			colu++;
		}
		// Comprobar columna
		int filu = 0;
		while (valido && filu < fil) {
			if (tab[filu][col][0] == n)
				valido = false;
			filu++;
		}
		// Comprobar salido
		if (tab[fil][col][n] == 0)
			valido = false;
		return valido;
	}

	/**
	 * Funci�n que muestra el tablero por pantalla (consola).
	 * @param tab tablero a mostrar.
	 */
	private static void mostrarTablero(int[][] tab) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(tab[i][j]+ " ");
			}
			System.out.println("");
		}
	}

	/**
	 * Funci�n de paso. Encubre la implementaci�n real (matriz cubica) al usuario, que solo 
	 * ha de realizar la llamada con una matriz cuadrada.
	 * @return una matriz cuadrada que representa un sudoku correcto.
	 */
	public static int [][] getSudoku(){
		int [][] sudoku = new int[9][9];
		int[][][] tablero = new int[9][9][10];
		/* Inicializamos nuestra matriz c�bica a ceros, en la primera
		 * posici�n. Y a los n�meros del 1 al 9 en el resto.
		 * */
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				for (int k = 0; k < 10; k++)
					tablero[i][j][k] = k;
			}

		}
		sudoku(tablero, 0, 0);
		for(int i =0; i< 9; i++){
			for(int j=0; j<9; j++){
				sudoku[i][j] = tablero[i][j][0];
			}
		}
		return sudoku;
	}
	
}
