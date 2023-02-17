package com.example.project;
import java.util.Scanner;

public class Cube {

	public static void main(final String[] args) {
		rubiksCube myCube;
		Scanner input = new Scanner(System.in);

		if (args.length > 0) {
			if(args[0].equals("size:2")) {
				myCube = new rubiksCube(2);
			} else
				myCube = new rubiksCube(3);

			for(String el: args) {
				switch(el.toLowerCase()) {
				case "r":
					myCube.r(false);
					break;
				case "r'":
					myCube.r(true);
					break;
				case "l" :
					myCube.l(false);
					break;
				case "l'":
					myCube.l(true);
					break;
				case "u":
					myCube.u(false);
					break;
				case "u'":
					myCube.u(true);
					break;
				case "d":
					myCube.d(false);
					break;
				case "d'":
					myCube.d(true);
					break;
				case "f":
					myCube.f(false);
					break;
				case "f'":
					myCube.f(true);
					break;
				case "b":
					myCube.b(false);
					break;
				case "b'":
					myCube.b(true);
					break;
				}
			}
			
			myCube.printCubeAssignment();
			System.out.print("Solution: " + myCube.solution());

		}
		
		System.out.println("Welcome! What size would you like for your cube?");
		myCube = new rubiksCube(input.nextInt());
		myCube.printCubeXY();
		System.out.println("Enter your inputs! q to quit, s for solution");
		boolean proceed = true;
		while(proceed) {
			switch(input.next().toLowerCase()) {
			case "r":
				myCube.r(false);
				break;
			case "r'":
				myCube.r(true);
				break;
			case "l" :
				myCube.l(false);
				break;
			case "l'":
				myCube.l(true);
				break;
			case "u":
				myCube.u(false);
				break;
			case "u'":
				myCube.u(true);
				break;
			case "d":
				myCube.d(false);
				break;
			case "d'":
				myCube.d(true);
				break;
			case "f":
				myCube.f(false);
				break;
			case "f'":
				myCube.f(true);
				break;
			case "b":
				myCube.b(false);
				break;
			case "b'":
				myCube.b(true);
				break;
			case "q":
				proceed = false;
				break;
			case "s":
				System.out.println(myCube.solution());
				break;
			default:
				System.out.println("invalid");
				break;
			}
			myCube.printCubeXY();
		}
		
		input.close();
	}

}
