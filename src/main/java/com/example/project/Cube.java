package com.example.project;

public class Cube {

	public static void main(final String[] args) {
		rubiksCube myCube = new rubiksCube(3);
		
		myCube.r(false);
		myCube.printCubeXY();
		myCube.u(false);
		myCube.printCubeXY();
	}

}

