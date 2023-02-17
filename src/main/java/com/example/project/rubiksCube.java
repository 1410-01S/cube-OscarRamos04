import java.util.ArrayList;

public class rubiksCube {
	//fields
	int size;
	char cube[][];
	ArrayList <String> solutionStack = new ArrayList<>();


	//constructor
	public rubiksCube(int size) {
		this.size = size;
		//generates the cube array based on user size
		this.generateCube(size);
	}

	//methods
	public void generateCube(int s){
		char tempCube[][] = new char[s*4][s*4];

		int lineI = 0;

		//creates cube top
		for(int i = 0; i < s; i++) {

			lineI=0;
			for(int j = 0; j < s; j++) {
				tempCube[i][lineI] = '_';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i][lineI] = 'r';
				lineI++;
			}
			for(int j = 0; j < s*2; j++) {
				tempCube[i][lineI] = '_';
				lineI++;
			}
		}

		//Creates middle slices
		for(int i = 0; i < s; i++) {

			lineI=0;
			for(int j = 0; j < s; j++) {
				tempCube[i+s][lineI] = 'b';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i+s][lineI] = 'w';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i+s][lineI] = 'g';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i+s][lineI] = 'y';
				lineI++;
			}
		}

		//Creates bottom section
		for(int i = 0; i < s; i++) {

			lineI=0;
			for(int j = 0; j < s; j++) {
				tempCube[i+s*2][lineI] = '_';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i+s*2][lineI] = 'o';
				lineI++;
			}
			for(int j = 0; j < s*2; j++) {
				tempCube[i+s*2][lineI] = '_';
				lineI++;
			}
		}

		for(int i = 0; i < s; i++) {

			lineI=0;
			for(int j = 0; j < s; j++) {
				tempCube[i+s*3][lineI] = '_';
				lineI++;
			}
			for(int j = 0; j < s; j++) {
				tempCube[i+s*3][lineI] = 'y';
				lineI++;
			}
			for(int j = 0; j < s*2; j++) {
				tempCube[i+s*3][lineI] = '_';
				lineI++;
			}
		}

		cube = tempCube;

	}

	public void printCubeXY(){
		//Only prints one back face for clarity
		for (int i = 0; i < this.cube.length- size; i++) {
			for (int j = 0; j < this.cube[i].length; j++) {
				System.out.print(cube[i][j]);
				System.out.print("|");
			}
			System.out.println();
		}
		System.out.println();
	}

	public void printCubeAssignment(){
		int faceRowStart = 0;
		int faceColumnStart = size;

		//print top
		for(int j = 0; j < size; j++){
			for(int k = 0; k < size; k++){
				System.out.print(cube[faceRowStart+j][faceColumnStart+k]);
				if(k < size-1){
					System.out.print("|");
				}
			}	
			System.out.println();
		}
		System.out.println();

		faceRowStart = size;
		faceColumnStart= 0;

		//print middle
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < size; j++){
				for(int k = 0; k < size; k++){

					System.out.print(cube[faceRowStart+j][faceColumnStart+k]);
					if(k < size-1){
						System.out.print("|");
					}
				}	
				System.out.println();
			}
			System.out.println();

			faceColumnStart+= size;
		}
		//print bottom
		faceRowStart = size * 2;
		faceColumnStart = size;
		for(int j = 0; j < size; j++){
			for(int k = 0; k < size; k++){

				System.out.print(cube[faceRowStart+j][faceColumnStart+k]);
				if(k < size-1){
					System.out.print("|");
				}
			}	
			System.out.println();
		}

	}

	public void horizontalShift(char dir, int loc) {
		char tempCube[][] = cube;

		switch (dir) {
		case 'r':

			for (int k = 0; k < size; k++) {
				char lastVal = tempCube[loc][tempCube[0].length-1];

				for(int i = tempCube[0].length-1; i > 0; i--) {
					tempCube[loc][i] = tempCube[loc][i-1];
				}
				tempCube[loc][0]=lastVal;

			}

			break;

		case 'l':

			for (int k = 0; k < size; k++) {
				char firstVal = tempCube[loc][0];

				for(int i = 0; i < tempCube[0].length-1; i++) {
					tempCube[loc][i] = tempCube[loc][i+1];
				}
				tempCube[loc][tempCube[0].length-1]=firstVal;
			}

			break;

		}

		cube = tempCube;

	}

	public void verticalShift(char dir, int loc) {
		char[][] tempCube = cube;

		switch (dir) {
		case 'd':
			char lastVal = tempCube[tempCube.length-1][loc];


			for (int k = 0; k < size; k++) {

				for(int i = tempCube.length-1; i > 0; i--) {
					tempCube[i][loc] = tempCube[i-1][loc];
				}
				tempCube[0][loc] = lastVal;
			}

			break;

		case 'u':
			char firstVal = tempCube[0][loc];


			for (int k = 0; k < size; k++) {

				for(int i = 0; i < tempCube.length-1; i++) {
					tempCube[i][loc] = tempCube[i+1][loc];
				}
				tempCube[tempCube.length-1][loc]=firstVal;
			}


			break;

		}

		cube = tempCube;

	}

	//Location based from 1-7, based on what face you want to rotate
	public void faceShift(char dir, int loc){
		faceShift(dir, loc, 0);
	}
	public void faceShift(char dir, int loc,  int special) {
		int faceRowStart = 0;
		int faceColumnStart = 0;
		char tempCube[][] = cube;

		switch(loc) {
		case 1:
			faceRowStart = 0;
			faceColumnStart = size;
			break;
		case 2:
			faceRowStart = size;
			faceColumnStart = 0;
			break;
		case 3:
			faceRowStart = size;
			faceColumnStart = size;
			break;	
		case 4:
			faceRowStart = size;
			faceColumnStart = size * 2;
			break;
		case 5:
			faceRowStart = size;
			faceColumnStart = size * 3;
			break;
		case 6:
			faceRowStart = size * 2;
			faceColumnStart = size;
			break;
		case 7:
			faceColumnStart = size;
			faceRowStart = size * 3;
		default: 
			System.out.println("error - invalid input");
			break;	
		}

		int rotationAmount = 0;


		switch(special) {
		case 0:
			rotationAmount = size;
			break;
		case 1:
			faceColumnStart--;
			faceRowStart--;
			rotationAmount = (size) + 2;
			break;
		case 2:
			faceColumnStart = 0;
			faceRowStart = 0;
			rotationAmount = (size*3);
			break;
		}

		char storedValue;
		switch(dir) {
		case 'l':
			for(int k = 1; k < rotationAmount; k++) {
				storedValue = tempCube[faceRowStart][faceColumnStart];
				for(int i = 1; i < rotationAmount; i++) {

					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart][faceColumnStart+1];
					faceColumnStart++;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart+1][faceColumnStart];
					faceRowStart++;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart][faceColumnStart-1];
					faceColumnStart--;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart-1][faceColumnStart];
					faceRowStart--;
				}

				tempCube[faceRowStart+1][faceColumnStart] = storedValue;
			}

			break;
		case 'r':
			for(int k = 1; k < rotationAmount; k++) {
				storedValue = tempCube[faceRowStart][faceColumnStart];
				for(int i = 1; i < rotationAmount; i++) {

					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart+1][faceColumnStart];
					faceRowStart++;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart][faceColumnStart+1];
					faceColumnStart++;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart-1][faceColumnStart];
					faceRowStart--;
				}

				for(int i = 1; i < rotationAmount; i++) {
					tempCube[faceRowStart][faceColumnStart] = tempCube[faceRowStart][faceColumnStart-1];
					faceColumnStart--;
				}

				tempCube[faceRowStart][faceColumnStart+1] = storedValue;
			}

			break;
		default: 
			System.out.println("invalid rotation value");
			break;
		}

		cube = tempCube;
	}


	//true = update using back on face 5
	public void updateBack(boolean loc) {
		char tempCube[][] = cube;
		int rowCorner;
		int columnCorner;
		int rCN;
		int cCN;

		if(!loc) {
			rowCorner = size;
			columnCorner = size * 3;
			rCN = size * 3;
			cCN = size;
		} else {
			columnCorner= size;
			rowCorner = size * 3;
			rCN = size;
			cCN = size*3;
		}
		for(int i = 0; i < size ; i++) {
			for(int j = 0; j < size; j++) {
				// CC=2 RC = 6
				//rCN = 2 cCN = 6

				tempCube[rowCorner+i][columnCorner+j] = tempCube[rCN+((size-1)-i)][cCN+(size-1)-j];
			}
		}


	}


	public void u(boolean prime) {
		if(prime) {
			horizontalShift('r', size);
			faceShift('l', 1);
			solutionStack.add("u");
		} else {
			horizontalShift('l', size);
			faceShift('r', 1);
			solutionStack.add("u'");
		}
		updateBack(true);
	}

	public void r(boolean prime) {
		if(!prime) {
			verticalShift('u', (size*2) - 1);
			faceShift('r', 4);
			solutionStack.add("r'");
		} else {
			verticalShift('d', (size*2) - 1);
			faceShift('l', 4);
			solutionStack.add("r");
		}
		updateBack(false);
	}

	public void d(boolean prime) {
		if(prime) {
			horizontalShift('l',(size*2)-1);
			faceShift('l',6);
			solutionStack.add("d");
		}
		else {
			horizontalShift('r',(size*2)-1);
			faceShift('r',6);
			solutionStack.add("d'");
		}

		updateBack(true);
	}

	public void l(boolean prime) {
		if(prime) {
			verticalShift('u',size);
			faceShift('l', 2);
			solutionStack.add("l");
		} else {
			verticalShift('d', size);
			faceShift('r', 2);
			solutionStack.add("l'");
		}
		updateBack(false);
	}

	public void f(boolean prime) {
		if(prime) {
			faceShift('l',3, 1);
			faceShift('l',3);
			solutionStack.add("f");
		} else {
			faceShift('r',3);
			faceShift('r',3,1);
			solutionStack.add("f'");
		}
	}

	public void b(boolean prime) {
		if(prime) {
			faceShift('l', 5);
			faceShift('r',5,2);
			solutionStack.add("b");
		} else {
			faceShift('r', 5);
			faceShift('l',5,2);
			solutionStack.add("b'");
		}
		updateBack(true);
	}

	public String solution(){
		String returnVal = "";
		for(int i = (solutionStack.size() - 1); i >= 0 ; i--) {
			returnVal += solutionStack.get(i) + " ";
			solutionStack.remove(i);
		}
		return returnVal;
	}

}

/*  xx44xxxx
 *  xx44xxxx
 *  11335500
 *  11335500
 *  xx22xxxx
 *  xx22xxxx
 * 
 *  2x2 cube
 * array size:8x6
 * 
 *  1
 * 2345
 *  6
 * 
 * 
 *  xxx333xxxxxx
 *  xxx333xxxxxx
 *  xxx333xxxxxx
 *  111222555000
 *  111222555000
 *  111222555000
 *  xxx444xxxxxx
 *  xxx444xxxxxx
 *  xxx444xxxxxx
 * 
 *  3x3 cube
 * array size: 12x9
 * 
 */