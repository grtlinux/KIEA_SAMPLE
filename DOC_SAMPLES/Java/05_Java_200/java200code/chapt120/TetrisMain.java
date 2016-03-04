public class TetrisMain {

	public static void main(String[] args) {
		
		Tetris tetris=new Tetris(TetrisUtil.TETRISA);
		tetris.printTetris();
		tetris.turnLeft();
		tetris.printTetris();
		tetris.turnLeft();
		tetris.printTetris();
		tetris.turnLeft();
		tetris.printTetris();
		tetris.turnLeft();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
	}
}
