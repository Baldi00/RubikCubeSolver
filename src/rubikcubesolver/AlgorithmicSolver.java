/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

/**
 *
 * @author Andrea
 */
public class AlgorithmicSolver {
    private Cube cube;
    private boolean[] phasesCompleted;
    private boolean phase2RedOk, phase2BlueOk, phase2OrangeOk, phase2GreenOk;
    private Face white, yellow, red, green, blue, orange;
    private Block[][] front, back, right, left, down, up;
    
    public AlgorithmicSolver(Cube cube) {
        this.cube = cube;
        
        phasesCompleted = new boolean[8];
        for(boolean b : phasesCompleted) {
            b = false;
        }
        
        phase2RedOk = false;
        phase2BlueOk = false;
        phase2OrangeOk = false;
        phase2GreenOk = false;
        
        white = new Face(RubikColor.WHITE, RubikOrientation.UP);
        yellow = new Face(RubikColor.YELLOW, RubikOrientation.DOWN);
        red = new Face(RubikColor.RED, RubikOrientation.FRONT);
        green = new Face(RubikColor.GREEN, RubikOrientation.LEFT);
        blue = new Face(RubikColor.BLUE, RubikOrientation.RIGHT);
        orange = new Face(RubikColor.ORANGE, RubikOrientation.BACK);
        
        updateFaces();
    }
    
    public void solve() {
        int movesCount = 0;
        while(true) {
            
            for(int i=0; i<8; i++)
                System.out.print(phasesCompleted[i] + " ");
            System.out.println("\n" + phase2RedOk + " " + phase2GreenOk + " " + phase2BlueOk + " " + phase2OrangeOk);
            
            System.out.println(movesCount);
            if (Fase8Completata()) {
                System.out.println("Rubik cube solved in " + movesCount + " moves");
                return;
            }
            
            updateFaces();
            
            if (!phasesCompleted[0]) {
                if (up[1][2].contains(white)) {
                    if (!down[1][2].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{7, 7});
                        movesCount += 2;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (up[0][1].contains(white)) {
                    if (!down[2][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{3, 3});
                        movesCount += 2;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (up[1][0].contains(white)) {
                    if (!down[1][0].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{5, 5});
                        movesCount += 2;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (up[2][1].contains(white)) {
                    if (!down[0][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{1, 1});
                        movesCount += 2;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (front[1][0].contains(white)) {
                    if (!down[1][0].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{5});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (right[1][0].contains(white)) {
                    if (!down[0][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{1});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (back[1][0].contains(white)) {
                    if (!down[1][2].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{7});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (left[1][0].contains(white)) {
                    if (!down[2][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{3});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (front[1][2].contains(white)) {
                    if (!down[1][2].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{8});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (right[1][2].contains(white)) {
                    if (!down[2][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{4});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (back[1][2].contains(white)) {
                    if (!down[1][0].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{6});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (left[1][2].contains(white)) {
                    if (!down[0][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{2});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (front[0][1].contains(white) || front[2][1].contains(white)) {
                    if (!down[0][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{1});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (right[0][1].contains(white) || right[2][1].contains(white)) {
                    if (!down[1][2].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{7});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (back[0][1].contains(white) || back[2][1].contains(white)) {
                    if (!down[2][1].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{3});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (left[0][1].contains(white) || left[2][1].contains(white)) {
                    if (!down[1][0].contains(white)) {
                        cube.rotateMultipleFaces(new int[]{5});
                        movesCount++;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else {
                    phasesCompleted[0] = true;
                }
            } else if (!phasesCompleted[1]) {
                if (!phase2RedOk && front[2][1].contains(red) && down[0][1].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{1, 1});
                    movesCount += 2;
                    phase2RedOk = true;
                } else if (!phase2RedOk && !(front[2][1].contains(red) && down[0][1].contains(white))) {
                    cube.rotateMultipleFaces(new int[]{11});
                    movesCount++;
                } else if (!phase2BlueOk && right[2][1].contains(blue) && down[1][2].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{7, 7});
                    movesCount += 2;
                    phase2BlueOk = true;
                } else if (!phase2BlueOk && !(right[2][1].contains(blue) && down[1][2].contains(white))) {
                    cube.rotateMultipleFaces(new int[]{11});
                    movesCount++;
                } else if (!phase2OrangeOk && back[2][1].contains(orange) && down[2][1].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{3, 3});
                    movesCount += 2;
                    phase2OrangeOk = true;
                } else if (!phase2OrangeOk && !(back[2][1].contains(orange) && down[2][1].contains(white))) {
                    cube.rotateMultipleFaces(new int[]{11});
                    movesCount++;
                } else if (!phase2GreenOk && left[2][1].contains(green) && down[1][0].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{5, 5});
                    movesCount += 2;
                    phase2GreenOk = true;
                } else if (!phase2GreenOk && !(left[2][1].contains(green) && down[1][0].contains(white))) {
                    cube.rotateMultipleFaces(new int[]{11});
                    movesCount++;
                } else {
                    phasesCompleted[1] = true;
                }
            } else if (!phasesCompleted[2]) {
                if (!Fase3SuperioriOK()) {																			//Parte 1 - Controllo superiori
                    if (down[0][0].contains(white)) {
                        if (left[2][2].contains(red)) {
                            cube.rotateMultipleFaces(new int[]{5, 11, 11, 6, 12, 5, 11, 6});
                            movesCount += 8;
                        } else if (left[2][2].contains(blue)) {
                            cube.rotateMultipleFaces(new int[]{11, 1, 11, 11, 2, 12, 1, 11, 2});
                            movesCount += 9;
                        } else if (left[2][2].contains(orange)) {
                            cube.rotateMultipleFaces(new int[]{11, 11, 7, 11, 11, 8, 12, 7, 11, 8});
                            movesCount += 1;
                        } else if (left[2][2].contains(green)) {
                            cube.rotateMultipleFaces(new int[]{12, 3, 11, 11, 4, 12, 3, 11, 4});
                            movesCount += 9;
                        }
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (!Fase3FrontaliOK()) {																			//Parte 2 - Controllo frontali
                    if (front[2][0].contains(white)) {
                        if (down[0][0].contains(red)) {
                            cube.rotateMultipleFaces(new int[]{2, 12, 1});
                            movesCount += 3;
                        } else if (down[0][0].contains(blue)) {
                            cube.rotateMultipleFaces(new int[]{11, 8, 12, 7});
                            movesCount += 4;
                        } else if (down[0][0].contains(orange)) {
                            cube.rotateMultipleFaces(new int[]{11, 11, 4, 12, 3});
                            movesCount += 5;
                        } else if (down[0][0].contains(green)) {
                            cube.rotateMultipleFaces(new int[]{12, 6, 12, 5});
                            movesCount += 4;
                        }
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (!Fase3LateraliOK()) {																				//Parte 3 - Controllo laterali
                    if (left[2][2].contains(white)) {
                        if (front[2][0].contains(red)) {
                            cube.rotateMultipleFaces(new int[]{5, 11, 6});
                            movesCount += 3;
                        } else if (front[2][0].contains(blue)) {
                            cube.rotateMultipleFaces(new int[]{11, 1, 11, 2});
                            movesCount += 4;
                        } else if (front[2][0].contains(orange)) {
                            cube.rotateMultipleFaces(new int[]{11, 11, 7, 11, 8});
                            movesCount += 5;
                        } else if (front[2][0].contains(green)) {
                            cube.rotateMultipleFaces(new int[]{12, 3, 11, 4});
                            movesCount += 4;
                        }
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (front[0][0].contains(white) || left[0][2].contains(white)) {  //Parte 4 - Controllo se alcuni sono sotto
                    cube.rotateMultipleFaces(new int[]{5, 11, 6});
                    movesCount += 3;
                } else if (right[0][0].contains(white) || front[0][2].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{1, 11, 2});
                    movesCount += 3;
                } else if (back[0][0].contains(white) || right[0][2].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{7, 11, 8});
                    movesCount += 3;
                } else if (left[0][0].contains(white) || back[0][2].contains(white)) {
                    cube.rotateMultipleFaces(new int[]{3, 11, 4});
                    movesCount += 3;
                } else if (!front[0][0].contains(red)) {  																				//Parte 5 - Controllo se posizioni sbagliate: bianco giusto, lati sbagliati
                    cube.rotateMultipleFaces(new int[]{5, 11, 6});
                    movesCount += 3;
                } else if (!right[0][0].contains(blue)) {
                    cube.rotateMultipleFaces(new int[]{1, 11, 2});
                    movesCount += 3;
                } else if (!back[0][0].contains(orange)) {
                    cube.rotateMultipleFaces(new int[]{7, 11, 8});
                    movesCount += 3;
                } else if (!left[0][0].contains(green)) {
                    cube.rotateMultipleFaces(new int[]{3, 11, 4});
                    movesCount += 3;
                } else {
                    if (Fase3PavimentoBiancoCompletato()) {
                        phasesCompleted[2] = true;
                    }
                }
            } else if (!phasesCompleted[3]) {																																			//FASE 4
                if (front[2][1].contains(red) && down[0][1].contains(green)) {
                    cube.rotateMultipleFaces(new int[]{11, 5, 12, 6, 12, 2, 11, 1});
                    movesCount += 8;
                } else if (front[2][1].contains(green) && down[0][1].contains(orange)) {
                    cube.rotateMultipleFaces(new int[]{3, 12, 4, 12, 6, 11, 5});
                    movesCount += 7;
                } else if (front[2][1].contains(orange) && down[0][1].contains(blue)) {
                    cube.rotateMultipleFaces(new int[]{12, 7, 12, 8, 12, 4, 11, 3});
                    movesCount += 8;
                } else if (front[2][1].contains(blue) && down[0][1].contains(red)) {
                    cube.rotateMultipleFaces(new int[]{11, 11, 1, 12, 2, 12, 8, 11, 7});
                    movesCount += 9;
                } else if (front[2][1].contains(red) && down[0][1].contains(blue)) {
                    cube.rotateMultipleFaces(new int[]{12, 8, 11, 7, 11, 1, 12, 2});
                    movesCount += 8;
                } else if (front[2][1].contains(blue) && down[0][1].contains(orange)) {
                    cube.rotateMultipleFaces(new int[]{4, 11, 3, 11, 7, 12, 8});
                    movesCount += 7;
                } else if (front[2][1].contains(orange) && down[0][1].contains(green)) {
                    cube.rotateMultipleFaces(new int[]{11, 6, 11, 5, 11, 3, 12, 4});
                    movesCount += 8;
                } else if (front[2][1].contains(green) && down[0][1].contains(red)) {
                    cube.rotateMultipleFaces(new int[]{11, 11, 2, 11, 1, 11, 5, 12, 6});
                    movesCount += 9;

                } else if (!Fase4Completata() && Fase4PossoContinuareAGirareSuperiore()) {

                    cube.rotateMultipleFaces(new int[]{11});
                    movesCount++;

                } else if (!Fase4Completata()) {

                    if (!front[1][2].contains(yellow) && !front[1][2].contains(red) && !right[1][0].contains(yellow) && !right[1][0].contains(blue)) {
                        cube.rotateMultipleFaces(new int[]{1, 12, 2, 12, 8, 11, 7});
                        movesCount += 7;
                    } else if (!right[1][2].contains(yellow) && !right[1][2].contains(blue) && !back[1][0].contains(yellow) && !back[1][0].contains(orange)) {
                        cube.rotateMultipleFaces(new int[]{7, 12, 8, 12, 4, 11, 3});
                        movesCount += 7;
                    } else if (!back[1][2].contains(yellow) && !back[1][2].contains(orange) && !left[1][0].contains(yellow) && !left[1][0].contains(green)) {
                        cube.rotateMultipleFaces(new int[]{3, 12, 4, 12, 6, 11, 5});
                        movesCount += 7;
                    } else if (!left[1][2].contains(yellow) && !left[1][2].contains(green) && !front[1][0].contains(yellow) && !front[1][0].contains(red)) {
                        cube.rotateMultipleFaces(new int[]{5, 12, 6, 12, 2, 11, 1});
                        movesCount += 7;
                    }

                } else {
                    phasesCompleted[3] = true;
                }
            } else if (!phasesCompleted[4]) {																																			//FASE 5
                if (down[0][1].contains(yellow) && !down[1][2].contains(yellow) && !down[2][1].contains(yellow) && down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{3, 11, 7, 12, 8, 4});
                    movesCount += 6;
                } else if (down[0][1].contains(yellow) && down[1][2].contains(yellow) && !down[2][1].contains(yellow) && !down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{5, 11, 3, 12, 4, 6});
                    movesCount += 6;
                } else if (!down[0][1].contains(yellow) && down[1][2].contains(yellow) && down[2][1].contains(yellow) && !down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{1, 11, 5, 12, 6, 2});
                    movesCount += 6;
                } else if (!down[0][1].contains(yellow) && !down[1][2].contains(yellow) && down[2][1].contains(yellow) && down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{7, 11, 1, 12, 2, 8});
                    movesCount += 6;
                } else if (!down[0][1].contains(yellow) && down[1][2].contains(yellow) && !down[2][1].contains(yellow) && down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{3, 7, 11, 8, 12, 4});
                    movesCount += 6;
                } else if (down[0][1].contains(yellow) && !down[1][2].contains(yellow) && down[2][1].contains(yellow) && !down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{5, 3, 11, 4, 12, 6});
                    movesCount += 6;
                } else if (!down[0][1].contains(yellow) && !down[1][2].contains(yellow) && !down[2][1].contains(yellow) && !down[1][0].contains(yellow)) {
                    cube.rotateMultipleFaces(new int[]{3, 11, 7, 12, 8, 4});
                    movesCount += 6;
                } else {
                    phasesCompleted[4] = true;
                }
            } else if (!phasesCompleted[5]) {																																			//FASE 6
                if (Fase6UnoAcceso()) {	//Croce + basso sinistra
                    if (down[2][0].contains(yellow) && !down[0][0].contains(yellow) && !down[2][2].contains(yellow) && !down[0][2].contains(yellow)) {
                        cube.rotateMultipleFaces(new int[]{7, 11, 8, 11, 7, 11, 11, 8});
                        movesCount += 8;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (!down[2][0].contains(yellow) && !down[0][0].contains(yellow) && !down[2][2].contains(yellow) && !down[0][2].contains(yellow)) { //Croce
                    if (left[2][2].contains(yellow) && left[2][0].contains(yellow)) {
                        cube.rotateMultipleFaces(new int[]{7, 11, 8, 11, 7, 11, 11, 8});
                        movesCount += 8;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (Fase6TSpessa()) { //T spessa punta giu
                    if (!down[2][0].contains(yellow) && !down[2][2].contains(yellow)) {
                        if (back[2][0].contains(yellow) && back[2][2].contains(yellow)) {
                            cube.rotateMultipleFaces(new int[]{7, 11, 8, 11, 7, 11, 11, 8});
                            movesCount += 8;
                        } else {
                            cube.rotateMultipleFaces(new int[]{11, 7, 11, 8, 11, 7, 11, 11, 8});
                            movesCount += 9;
                        }
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (down[0][0].contains(yellow) && !down[0][2].contains(yellow) && !down[2][0].contains(yellow) && down[2][2].contains(yellow)) { //Quadrati intersecati giusti
                    cube.rotateMultipleFaces(new int[]{7, 11, 8, 11, 7, 11, 11, 8});
                    movesCount += 8;
                } else if (!down[0][0].contains(yellow) && down[0][2].contains(yellow) && down[2][0].contains(yellow) && !down[2][2].contains(yellow)) { //Quadrati intersecati sbagliati
                    cube.rotateMultipleFaces(new int[]{11, 7, 11, 8, 11, 7, 11, 11, 8});
                    movesCount += 9;
                } else {
                    phasesCompleted[5] = true;
                }
            } else if (!phasesCompleted[6]) {																																			//FASE 7
                if (!Fase7TuttiDiversi() && !Fase7TuttiUguali()) {
                    if (front[2][0].contains(front[2][2].getFaceWithOrientation(RubikOrientation.FRONT))) {
                        if (front[2][0].contains(red)) {
                            cube.rotateMultipleFaces(new int[]{5, 4, 5, 1, 1, 6, 3, 5, 1, 1, 5, 5});
                            movesCount += 1;
                        } else if (front[2][0].contains(green)) {
                            cube.rotateMultipleFaces(new int[]{12, 3, 8, 3, 5, 5, 4, 7, 3, 5, 5, 3, 3});
                            movesCount += 1;
                        } else if (front[2][0].contains(orange)) {
                            cube.rotateMultipleFaces(new int[]{11, 11, 7, 2, 7, 3, 3, 8, 1, 7, 3, 3, 7, 7});
                            movesCount += 1;
                        } else if (front[2][0].contains(blue)) {
                            cube.rotateMultipleFaces(new int[]{11, 1, 6, 1, 7, 7, 2, 5, 1, 7, 7, 1, 1});
                            movesCount += 1;
                        }
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else if (Fase7TuttiDiversi() && !Fase7TuttiUguali()) {
                    cube.rotateMultipleFaces(new int[]{7, 2, 7, 3, 3, 8, 1, 7, 3, 3, 7, 7});
                    movesCount += 1;
                } else {
                    phasesCompleted[6] = true;
                }
            } else if (!phasesCompleted[7]) {																																			//FASE 8
                if (!Fase8Completata()) {
                    if (Fase8NonCiSonoLineeComplete()) {
                        cube.rotateMultipleFaces(new int[]{3, 3, 12, 8, 5, 3, 3, 7, 6, 12, 3, 3});
                        movesCount += 1;
                    } else if (front[2][1].contains(red) && front[2][0].contains(red) && front[2][2].contains(red)) {
                        cube.rotateMultipleFaces(new int[]{3, 3, 12, 8, 5, 3, 3, 7, 6, 12, 3, 3});
                        movesCount += 1;
                    } else if (front[2][1].contains(green) && front[2][0].contains(green) && front[2][2].contains(green)) {
                        cube.rotateMultipleFaces(new int[]{12, 7, 7, 12, 2, 3, 7, 7, 1, 4, 12, 7, 7});
                        movesCount += 1;
                    } else if (front[2][1].contains(orange) && front[2][0].contains(orange) && front[2][2].contains(orange)) {
                        cube.rotateMultipleFaces(new int[]{11, 11, 1, 1, 12, 6, 7, 1, 1, 5, 8, 12, 1, 1});
                        movesCount += 1;
                    } else if (front[2][1].contains(blue) && front[2][0].contains(blue) && front[2][2].contains(blue)) {
                        cube.rotateMultipleFaces(new int[]{11, 5, 5, 12, 4, 1, 5, 5, 3, 2, 12, 5, 5});
                        movesCount += 1;
                    } else {
                        cube.rotateMultipleFaces(new int[]{11});
                        movesCount++;
                    }
                } else {
                    phasesCompleted[7] = true;
                }
            }
	}
    }
    
    public boolean Fase1QuattroBiachiConGialloInMezzo(){
        updateFaces();
        return 	
                down[0][1].contains(white) &&
                down[1][0].contains(white) &&
                down[2][1].contains(white) &&
                down[1][2].contains(white);
    }

    public boolean Fase2CroceBiancaCompletata(){
        updateFaces();
        
        return
                up[0][1].contains(white) &&
                up[1][0].contains(white) &&
                up[2][1].contains(white) &&
                up[1][2].contains(white) &&
                front[0][1].contains(red) &&
                right[0][1].contains(blue) &&
                back[0][1].contains(orange) &&
                left[0][1].contains(green);
    }

    public boolean Fase3PavimentoBiancoCompletato(){
        updateFaces();
        boolean ok = true;
        for(int i=0; i<3 && ok; i++) {
            for(int j=0; j<3 && ok; j++) {
                ok = up[i][j].contains(white);
            }
        }
        return ok;
    }

    public boolean Fase3FrontaliOK(){
        updateFaces();
        
        return
                !front[0][2].contains(white) &&
                !left[0][2].contains(white) &&
                !back[0][2].contains(white) &&
                !right[0][2].contains(white);
    }

    public boolean Fase3LateraliOK(){
        updateFaces();
        
        return
                !front[2][2].contains(white) &&
                !left[2][2].contains(white) &&
                !back[2][2].contains(white) &&
                !right[2][2].contains(white);
    }

    public boolean Fase3SuperioriOK(){
        updateFaces();
        
        return
                !down[0][0].contains(white) &&
                !down[0][2].contains(white) &&
                !down[2][0].contains(white) &&
                !down[2][2].contains(white);

    }

    public boolean Fase4Completata(){
        updateFaces();
        
        return
                front[0][1].contains(red) &&
                front[2][1].contains(red) &&
                left[0][1].contains(green) &&
                left[2][1].contains(green) &&
                right[0][1].contains(blue) &&
                right[2][1].contains(blue) &&
                back[0][1].contains(orange) &&
                back[2][1].contains(orange);
    }

    public boolean Fase4PossoContinuareAGirareSuperiore(){
        updateFaces();
        
        return
                (!front[1][2].contains(yellow) && !down[0][1].contains(yellow)) ||
                (!right[1][2].contains(yellow) && !down[2][1].contains(yellow)) ||
                (!back[1][2].contains(yellow) && !down[1][2].contains(yellow)) ||
                (!left[1][2].contains(yellow) && !down[1][0].contains(yellow));
    }

    public boolean Fase5Completata(){
        updateFaces();
        return
                down[1][0].contains(yellow) &&
                down[0][1].contains(yellow) &&
                down[1][2].contains(yellow) &&
                down[2][1].contains(yellow);
    }

    public boolean Fase6UnoAcceso(){
        updateFaces();
        return
                (down[0][0].contains(yellow) && !down[0][2].contains(yellow) && !down[2][0].contains(yellow) && !down[2][2].contains(yellow)) ||
                (!down[0][0].contains(yellow) && down[0][2].contains(yellow) && !down[2][0].contains(yellow) && !down[2][2].contains(yellow)) ||
                (!down[0][0].contains(yellow) && !down[0][2].contains(yellow) && down[2][0].contains(yellow) && !down[2][2].contains(yellow)) ||
                (!down[0][0].contains(yellow) && !down[0][2].contains(yellow) && !down[2][0].contains(yellow) && down[2][2].contains(yellow));
    }

    public boolean Fase6TSpessa(){
        updateFaces();
        return
                (down[0][0].contains(yellow) && down[0][2].contains(yellow) && !down[2][0].contains(yellow) && !down[2][2].contains(yellow)) ||
                (!down[0][0].contains(yellow) && down[0][2].contains(yellow) && !down[2][0].contains(yellow) && down[2][2].contains(yellow)) ||
                (!down[0][0].contains(yellow) && !down[0][2].contains(yellow) && down[2][0].contains(yellow) && down[2][2].contains(yellow)) ||
                (down[0][0].contains(yellow) && !down[0][2].contains(yellow) && down[2][0].contains(yellow) && !down[2][2].contains(yellow));
    }

    public boolean Fase6Completata(){
        updateFaces();
        boolean ok = true;
        for(int i=0; i<3 && ok; i++) {
            for(int j=0; j<3 && ok; j++) {
                ok = down[i][j].contains(yellow);
            }
        }
        return ok;
    }

    public boolean Fase7TuttiDiversi(){
        updateFaces();
        return
                !front[2][0].contains(front[2][2].getFaceWithOrientation(RubikOrientation.FRONT)) &&
                !left[2][0].contains(left[2][2].getFaceWithOrientation(RubikOrientation.LEFT)) &&
                !back[2][0].contains(back[2][2].getFaceWithOrientation(RubikOrientation.BACK)) &&
                !right[2][0].contains(right[2][2].getFaceWithOrientation(RubikOrientation.RIGHT));
    }

    public boolean Fase7TuttiUguali(){
        updateFaces();
        return
                front[2][0].contains(front[2][2].getFaceWithOrientation(RubikOrientation.FRONT)) &&
                left[2][0].contains(left[2][2].getFaceWithOrientation(RubikOrientation.LEFT)) &&
                back[2][0].contains(back[2][2].getFaceWithOrientation(RubikOrientation.BACK)) &&
                right[2][0].contains(right[2][2].getFaceWithOrientation(RubikOrientation.RIGHT));
    }

    public boolean Fase8NonCiSonoLineeComplete(){
        updateFaces();
        return
                !front[2][0].contains(front[2][1].getFaceWithOrientation(RubikOrientation.FRONT)) &&
                !right[2][0].contains(right[2][1].getFaceWithOrientation(RubikOrientation.RIGHT)) &&
                !back[2][0].contains(back[2][1].getFaceWithOrientation(RubikOrientation.BACK)) &&
                !left[2][0].contains(left[2][1].getFaceWithOrientation(RubikOrientation.LEFT));
    }

    public boolean Fase8Completata(){
        return cube.isSolved();
    }
    
    private void updateFaces(){
        front = cube.getFace(RubikOrientation.FRONT);
        right = cube.getFace(RubikOrientation.RIGHT);
        left = cube.getFace(RubikOrientation.LEFT);
        back = cube.getFace(RubikOrientation.BACK);
        down = cube.getFace(RubikOrientation.DOWN);
        up = cube.getFace(RubikOrientation.UP);
    }
}
