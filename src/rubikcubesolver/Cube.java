/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class Cube implements Cloneable{
    private Block [][][] cube;

    public Cube() {
        cube = new Block[3][3][3];
        
        int id = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    cube[i][j][k] = new Block();
                    cube[i][j][k].setId(id);
                    id++;
                }
            }
        }
        
        Block[][] frontFace = getFace(RubikOrientation.FRONT);
        Block[][] backFace = getFace(RubikOrientation.BACK);
        Block[][] rightFace = getFace(RubikOrientation.RIGHT);
        Block[][] leftFace = getFace(RubikOrientation.LEFT);
        Block[][] upFace = getFace(RubikOrientation.UP);
        Block[][] downFace = getFace(RubikOrientation.DOWN);
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                frontFace[i][j].addFace(new Face(RubikColor.RED, RubikOrientation.FRONT));
                backFace[i][j].addFace(new Face(RubikColor.ORANGE, RubikOrientation.BACK));
                rightFace[i][j].addFace(new Face(RubikColor.BLUE, RubikOrientation.RIGHT));
                leftFace[i][j].addFace(new Face(RubikColor.GREEN, RubikOrientation.LEFT));
                upFace[i][j].addFace(new Face(RubikColor.WHITE, RubikOrientation.UP));
                downFace[i][j].addFace(new Face(RubikColor.YELLOW, RubikOrientation.DOWN));
            }
        }
    }
    
    public Cube(Cube c) {
        cube = new Block[3][3][3];
        setCube(c);
    }
    
    public Block[][] getFace(RubikOrientation faceOrientation){
        switch(faceOrientation){
            case FRONT: return getFrontFace();
            case BACK: return getBackFace();
            case RIGHT: return getRightFace();
            case LEFT: return getLeftFace();
            case UP: return getUpFace();
            case DOWN: return getDownFace();
            default: return null;
        }
    }
    
    public void setFace(RubikOrientation faceOrientation, Block[][] face){
        switch(faceOrientation){
            case FRONT: setFrontFace(face); break;
            case BACK: setBackFace(face); break;
            case RIGHT: setRightFace(face); break;
            case LEFT: setLeftFace(face); break;
            case UP: setUpFace(face); break;
            case DOWN: setDownFace(face); break;
        }
    }
    
    private Block[][] getFrontFace(){
        return new Block[][]{
            {cube[0][0][0], cube[0][1][0], cube[0][2][0]},
            {cube[1][0][0], cube[1][1][0], cube[1][2][0]},
            {cube[2][0][0], cube[2][1][0], cube[2][2][0]}
        };
    }
    
    private Block[][] getBackFace(){
        return new Block[][]{
            {cube[0][2][2], cube[0][1][2], cube[0][0][2]},
            {cube[1][2][2], cube[1][1][2], cube[1][0][2]},
            {cube[2][2][2], cube[2][1][2], cube[2][0][2]}
        };
    }
    
    private Block[][] getRightFace(){
        return new Block[][]{
            {cube[0][2][0], cube[0][2][1], cube[0][2][2]},
            {cube[1][2][0], cube[1][2][1], cube[1][2][2]},
            {cube[2][2][0], cube[2][2][1], cube[2][2][2]}
        };
    }
    
    private Block[][] getLeftFace(){
        return new Block[][]{
            {cube[0][0][2], cube[0][0][1], cube[0][0][0]},
            {cube[1][0][2], cube[1][0][1], cube[1][0][0]},
            {cube[2][0][2], cube[2][0][1], cube[2][0][0]}
        };
    }
    
    private Block[][] getUpFace(){
        return new Block[][]{
            {cube[0][0][2], cube[0][1][2], cube[0][2][2]},
            {cube[0][0][1], cube[0][1][1], cube[0][2][1]},
            {cube[0][0][0], cube[0][1][0], cube[0][2][0]}
        };
    }
    
    private Block[][] getDownFace(){
        return new Block[][]{
            {cube[2][0][0], cube[2][1][0], cube[2][2][0]},
            {cube[2][0][1], cube[2][1][1], cube[2][2][1]},
            {cube[2][0][2], cube[2][1][2], cube[2][2][2]}
        };
    }
    
    private void setFrontFace(Block[][] face){        
        cube[0][0][0] = face[0][0];
        cube[0][1][0] = face[0][1];
        cube[0][2][0] = face[0][2];
        cube[1][0][0] = face[1][0];
        cube[1][1][0] = face[1][1];
        cube[1][2][0] = face[1][2];
        cube[2][0][0] = face[2][0];
        cube[2][1][0] = face[2][1];
        cube[2][2][0] = face[2][2];
    }
    
    private void setBackFace(Block[][] face){
        cube[0][2][2] = face[0][0];
        cube[0][1][2] = face[0][1];
        cube[0][0][2] = face[0][2];
        cube[1][2][2] = face[1][0];
        cube[1][1][2] = face[1][1];
        cube[1][0][2] = face[1][2];
        cube[2][2][2] = face[2][0];
        cube[2][1][2] = face[2][1];
        cube[2][0][2] = face[2][2];
    }
    
    private void setRightFace(Block[][] face){
        cube[0][2][0] = face[0][0];
        cube[0][2][1] = face[0][1];
        cube[0][2][2] = face[0][2];
        cube[1][2][0] = face[1][0];
        cube[1][2][1] = face[1][1];
        cube[1][2][2] = face[1][2];
        cube[2][2][0] = face[2][0];
        cube[2][2][1] = face[2][1];
        cube[2][2][2] = face[2][2];
    }
    
    private void setLeftFace(Block[][] face){
        cube[0][0][2] = face[0][0];
        cube[0][0][1] = face[0][1];
        cube[0][0][0] = face[0][2];
        cube[1][0][2] = face[1][0];
        cube[1][0][1] = face[1][1];
        cube[1][0][0] = face[1][2];
        cube[2][0][2] = face[2][0];
        cube[2][0][1] = face[2][1];
        cube[2][0][0] = face[2][2];
    }
    
    private void setUpFace(Block[][] face){
        cube[0][0][2] = face[0][0];
        cube[0][1][2] = face[0][1];
        cube[0][2][2] = face[0][2];
        cube[0][0][1] = face[1][0];
        cube[0][1][1] = face[1][1];
        cube[0][2][1] = face[1][2];
        cube[0][0][0] = face[2][0];
        cube[0][1][0] = face[2][1];
        cube[0][2][0] = face[2][2];
    }
    
    private void setDownFace(Block[][] face){
        cube[2][0][0] = face[0][0];
        cube[2][1][0] = face[0][1];
        cube[2][2][0] = face[0][2];
        cube[2][0][1] = face[1][0];
        cube[2][1][1] = face[1][1];
        cube[2][2][1] = face[1][2];
        cube[2][0][2] = face[2][0];
        cube[2][1][2] = face[2][1];
        cube[2][2][2] = face[2][2];
    }
    
    public Cube rotateFace(RubikOrientation faceOrientation, boolean clockwise) {
        Block[][] face = getFace(faceOrientation);
        rotateBlockMatrix(face, faceOrientation, clockwise);
        setFace(faceOrientation, face);
        rotateFaces(face, faceOrientation, clockwise);
        return this;
    }
    
    public void rotateMultipleFaces(RubikOrientation[] faceOrientation, boolean[] clockwise) {
        for(int i=0; i<faceOrientation.length; i++){
            rotateFace(faceOrientation[i], clockwise[i]);
        }
    }
    
    public void rotateMultipleFaces(int[] faces) {
        for(int i=0; i<faces.length; i++){
            System.out.println("AAA " + faces[i]);
            switch(faces[i]) {
                case 1: rotateFace(RubikOrientation.FRONT, true); break;
                case 2: rotateFace(RubikOrientation.FRONT, false); break;
                case 3: rotateFace(RubikOrientation.BACK, true); break;
                case 4: rotateFace(RubikOrientation.BACK, false); break;
                case 5: rotateFace(RubikOrientation.LEFT, true); break;
                case 6: rotateFace(RubikOrientation.LEFT, false); break;
                case 7: rotateFace(RubikOrientation.RIGHT, true); break;
                case 8: rotateFace(RubikOrientation.RIGHT, false); break;
                case 9: rotateFace(RubikOrientation.UP, true); break;
                case 10: rotateFace(RubikOrientation.UP, false); break;
                case 11: rotateFace(RubikOrientation.DOWN, true); break;
                case 12: rotateFace(RubikOrientation.DOWN, false); break;
            }
        }
        //Scanner in = new Scanner(System.in);
        //in.nextLine();
    }
    
    private void printMatrix(Block[][] mtx) {
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                System.out.print(mtx[i][j] + " ");
            }
            System.out.println("");
        }
    }
    
    private void rotateBlockMatrix(Block[][] matrix, RubikOrientation orientation, boolean clockwise) {
        Block temp;
        
        if(clockwise) {
            temp = matrix[0][0];
            matrix[0][0] = matrix[2][0];
            matrix[2][0] = matrix[2][2];
            matrix[2][2] = matrix[0][2];
            matrix[0][2] = temp;
            temp = matrix[0][1];
            matrix[0][1] = matrix[1][0];
            matrix[1][0] = matrix[2][1];
            matrix[2][1] = matrix[1][2];
            matrix[1][2] = temp;
        } else {
            temp = matrix[0][0];
            matrix[0][0] = matrix[0][2];
            matrix[0][2] = matrix[2][2];
            matrix[2][2] = matrix[2][0];
            matrix[2][0] = temp;
            temp = matrix[0][1];
            matrix[0][1] = matrix[1][2];
            matrix[1][2] = matrix[2][1];
            matrix[2][1] = matrix[1][0];
            matrix[1][0] = temp;
        }
        
        
    }

    private void rotateFaces(Block[][] face, RubikOrientation rubikOrientation, boolean clockwise) {
        
        ArrayList<Face> facesToRotate = new ArrayList<>();
        
        for(Block[] b1 : face){
            for(Block b2 : b1) {
                facesToRotate.addAll(b2.getFaces());
            }
        }
        
        if(clockwise) {
            rotateFacesClockwise(facesToRotate, rubikOrientation);
        } else {
            rotateFacesAntiClockwise(facesToRotate, rubikOrientation);
        }
    }

    private void rotateFacesClockwise(ArrayList<Face> facesToRotate, RubikOrientation faceOrientation) {
        switch(faceOrientation){
            case FRONT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                    System.out.print("");
                }
                break;
            case BACK:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case RIGHT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.FRONT); break;
                        case FRONT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case LEFT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.FRONT); break;
                        case FRONT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case UP:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case FRONT: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.FRONT); break;
                        default: break;
                    }
                }
                break;
            case DOWN:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case FRONT: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.FRONT); break;
                        default: break;
                    }
                }
                break;
        }
    }
    
    private void rotateFacesAntiClockwise(ArrayList<Face> facesToRotate, RubikOrientation faceOrientation) {
        switch(faceOrientation){
            case FRONT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case BACK:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case RIGHT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.FRONT); break;
                        case FRONT: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case LEFT:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case DOWN: f.setOrientation(RubikOrientation.FRONT); break;
                        case FRONT: f.setOrientation(RubikOrientation.UP); break;
                        case UP: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.DOWN); break;
                        default: break;
                    }
                }
                break;
            case UP:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case FRONT: f.setOrientation(RubikOrientation.RIGHT); break;
                        case RIGHT: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.FRONT); break;
                        default: break;
                    }
                }
                break;
            case DOWN:
                for(Face f : facesToRotate){
                    switch (f.getOrientation()) {
                        case RIGHT: f.setOrientation(RubikOrientation.FRONT); break;
                        case FRONT: f.setOrientation(RubikOrientation.LEFT); break;
                        case LEFT: f.setOrientation(RubikOrientation.BACK); break;
                        case BACK: f.setOrientation(RubikOrientation.RIGHT); break;
                        default: break;
                    }
                }
                break;
        }
    }
    
    public void printCube() {
        Block[][] up = getFace(RubikOrientation.UP);
        Block[][] down = getFace(RubikOrientation.DOWN);
        Block[][] right = getFace(RubikOrientation.RIGHT);
        Block[][] left = getFace(RubikOrientation.LEFT);
        Block[][] front = getFace(RubikOrientation.FRONT);
        Block[][] back = getFace(RubikOrientation.BACK);
        
        for(int i=0; i<3; i++) {
            System.out.print("    ");
            for(int j=0; j<3; j++) {
                up[i][j].printOnlyOrientation(RubikOrientation.UP);
            }
            System.out.println("");
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                left[i][j].printOnlyOrientation(RubikOrientation.LEFT);
            }
            System.out.print(" ");
            for(int j=0; j<3; j++) {
                front[i][j].printOnlyOrientation(RubikOrientation.FRONT);
            }
            System.out.print(" ");
            for(int j=0; j<3; j++) {
                right[i][j].printOnlyOrientation(RubikOrientation.RIGHT);
            }
            System.out.print(" ");
            for(int j=0; j<3; j++) {
                back[i][j].printOnlyOrientation(RubikOrientation.BACK);
            }
            System.out.println("");
        }
        for(int i=0; i<3; i++) {
            System.out.print("    ");
            for(int j=0; j<3; j++) {
                down[i][j].printOnlyOrientation(RubikOrientation.DOWN);
            }
            System.out.println("");
        }
        System.out.println("");
    }
    
    public void randomize(int moves) {
        for(int i=0; i<moves; i++) {
            int orientation = (int)(Math.random()*6);
            int clockwise = (int)(Math.random()*2);
            
            switch(orientation){
                case 0:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.FRONT, true);
                    } else {
                        rotateFace(RubikOrientation.FRONT, false);
                    }
                    break;
                case 1:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.BACK, true);
                    } else {
                        rotateFace(RubikOrientation.BACK, false);
                    }
                    break;
                case 2:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.RIGHT, true);
                    } else {
                        rotateFace(RubikOrientation.RIGHT, false);
                    }
                    break;
                case 3:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.LEFT, true);
                    } else {
                        rotateFace(RubikOrientation.LEFT, false);
                    }
                    break;
                case 4:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.UP, true);
                    } else {
                        rotateFace(RubikOrientation.UP, false);
                    }
                    break;
                case 5:
                    if(clockwise==1) {
                        rotateFace(RubikOrientation.DOWN, true);
                    } else {
                        rotateFace(RubikOrientation.DOWN, false);
                    }
                    break;
            }
        }
    }
    
    public boolean equals(Cube c2) {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    if(!cube[i][j][k].equals(c2.cube[i][j][k]))
                        return false;
                }
            }
        }
        return true;
    }
    
    public boolean isSolved() {
        return this.equals(new Cube());
    }
    
    public String getHash(){
        String s = "";
        Block[][] up = getFace(RubikOrientation.UP);
        Block[][] down = getFace(RubikOrientation.DOWN);
        Block[][] right = getFace(RubikOrientation.RIGHT);
        Block[][] left = getFace(RubikOrientation.LEFT);
        Block[][] front = getFace(RubikOrientation.FRONT);
        Block[][] back = getFace(RubikOrientation.BACK);
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                s += up[i][j].getFaceWithOrientation(RubikOrientation.UP).getColorLetter();
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                s += left[i][j].getFaceWithOrientation(RubikOrientation.LEFT).getColorLetter();
            }
            for(int j=0; j<3; j++) {
                s += front[i][j].getFaceWithOrientation(RubikOrientation.FRONT).getColorLetter();
            }
            for(int j=0; j<3; j++) {
                s += right[i][j].getFaceWithOrientation(RubikOrientation.RIGHT).getColorLetter();
            }
            for(int j=0; j<3; j++) {
                s += back[i][j].getFaceWithOrientation(RubikOrientation.BACK).getColorLetter();
            }
        }
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                s += down[i][j].getFaceWithOrientation(RubikOrientation.DOWN).getColorLetter();
            }
        }
        
        return s;
    }
    
    public void setCube (Cube c){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                for(int k=0; k<3; k++){
                    cube[i][j][k] = new Block(c.cube[i][j][k]);
                    cube[i][j][k].setId(c.cube[i][j][k].getId());
                }
            }
        }
    }
    
    public int[] getBlockPositionByID (int id){
        int[] positions = new int [3];
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    if(cube[i][j][k].getId() == id){
                        positions[0] = i;
                        positions[1] = j;
                        positions[2] = k;
                        return positions;
                    }
                }
            }
        }
        return null;
    }
    
    public int manhattanDistanceSum(Cube goal) {
        int sum = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                for(int k=0; k<3; k++) {
                    int[] goalBlockPositions = goal.getBlockPositionByID(cube[i][j][k].getId());
                    sum += Math.abs(goalBlockPositions[i] - i);
                    sum += Math.abs(goalBlockPositions[j] - j);
                    sum += Math.abs(goalBlockPositions[k] - k);
                }
            }
        }
        return sum;
    }
    
    public int manhattanDistanceInRotationsSum() {
        int sum = 0;
        
        //Vertices
        switch(cube[0][0][0].getId()){
            case 0: break;
            case 2: sum+=1; break;
            case 6: sum+=1; break;
            case 8: sum+=2; break;
            case 18: sum+=1; break;
            case 20: sum+=2; break;
            case 24: sum+=2; break;
            case 26: sum+=3; break;
        }
        switch(cube[0][0][2].getId()){
            case 0: sum+=1; break;
            case 2: break;
            case 6: sum+=2; break;
            case 8: sum+=1; break;
            case 18: sum+=2; break;
            case 20: sum+=1; break;
            case 24: sum+=3; break;
            case 26: sum+=2; break;
        }
        switch(cube[0][2][0].getId()){
            case 0: sum+=1; break;
            case 2: sum+=2; break;
            case 6: break;
            case 8: sum+=0; break;
            case 18: sum+=2; break;
            case 20: sum+=3; break;
            case 24: sum+=1; break;
            case 26: sum+=2; break;
        }
        switch(cube[0][2][2].getId()){
            case 0: sum+=2; break;
            case 2: sum+=1; break;
            case 6: sum+=1; break;
            case 8: break;
            case 18: sum+=3; break;
            case 20: sum+=2; break;
            case 24: sum+=2; break;
            case 26: sum+=1; break;
        }
        switch(cube[2][0][0].getId()){
            case 0: sum+=1; break;
            case 2: sum+=2; break;
            case 6: sum+=2; break;
            case 8: sum+=3; break;
            case 18: break;
            case 20: sum+=1; break;
            case 24: sum+=1; break;
            case 26: sum+=2; break;
        }
        switch(cube[2][0][2].getId()){
            case 0: sum+=2; break;
            case 2: sum+=1; break;
            case 6: sum+=3; break;
            case 8: sum+=2; break;
            case 18: sum+=1; break;
            case 20: break;
            case 24: sum+=2; break;
            case 26: sum+=1; break;
        }
        switch(cube[2][2][0].getId()){
            case 0: sum+=2; break;
            case 2: sum+=3; break;
            case 6: sum+=1; break;
            case 8: sum+=2; break;
            case 18: sum+=1; break;
            case 20: sum+=2; break;
            case 24: break;
            case 26: sum+=1; break;
        }
        switch(cube[2][2][2].getId()){
            case 0: sum+=3; break;
            case 2: sum+=2; break;
            case 6: sum+=2; break;
            case 8: sum+=1; break;
            case 18: sum+=2; break;
            case 20: sum+=1; break;
            case 24: sum+=1; break;
            case 26: break;
        }

        //Edges
        switch(cube[0][1][0].getId()){
            case 3: break;
            case 9: sum+=1; break;
            case 15: sum+=1; break;
            case 21: sum+=2; break;
            case 1: sum+=1; break;
            case 7: sum+=1; break;
            case 19: sum+=2; break;
            case 25: sum+=2; break;
            case 5: sum+=2; break;
            case 11: sum+=2; break;
            case 17: sum+=2; break;
            case 23: sum+=3; break;
        }
        switch(cube[1][0][0].getId()){
            case 3: sum+=1; break;
            case 9: break;
            case 15: sum+=2; break;
            case 21: sum+=1; break;
            case 1: sum+=1; break;
            case 7: sum+=2; break;
            case 19: sum+=1; break;
            case 25: sum+=2; break;
            case 5: sum+=2; break;
            case 11: sum+=2; break;
            case 17: sum+=3; break;
            case 23: sum+=2; break;
        }
        switch(cube[1][2][0].getId()){
            case 3: sum+=1; break;
            case 9: sum+=2; break;
            case 15: break;
            case 21: sum+=1; break;
            case 1: sum+=2; break;
            case 7: sum+=1; break;
            case 19: sum+=2; break;
            case 25: sum+=2; break;
            case 5: sum+=2; break;
            case 11: sum+=3; break;
            case 17: sum+=2; break;
            case 23: sum+=1; break;
        }
        switch(cube[2][1][0].getId()){
            case 3: sum+=2; break;
            case 9: sum+=1; break;
            case 15: sum+=1; break;
            case 21: break;
            case 1: sum+=2; break;
            case 7: sum+=2; break;
            case 19: sum+=1; break;
            case 25: sum+=1; break;
            case 5: sum+=3; break;
            case 11: sum+=2; break;
            case 17: sum+=2; break;
            case 23: sum+=2; break;
        }
        switch(cube[0][0][1].getId()){
            case 3: sum+=1; break;
            case 9: sum+=1; break;
            case 15: sum+=2; break;
            case 21: sum+=2; break;
            case 1: break;
            case 7: sum+=2; break;
            case 19: sum+=2; break;
            case 25: sum+=3; break;
            case 5: sum+=1; break;
            case 11: sum+=1; break;
            case 17: sum+=2; break;
            case 23: sum+=2; break;
        }
        switch(cube[0][2][1].getId()){
            case 3: sum+=1; break;
            case 9: sum+=2; break;
            case 15: sum+=1; break;
            case 21: sum+=2; break;
            case 1: sum+=2; break;
            case 7: break;
            case 19: sum+=3; break;
            case 25: sum+=2; break;
            case 5: sum+=1; break;
            case 11: sum+=2; break;
            case 17: sum+=1; break;
            case 23: sum+=2; break;
        }
        switch(cube[2][0][1].getId()){
            case 3: sum+=2; break;
            case 9: sum+=1; break;
            case 15: sum+=2; break;
            case 21: sum+=1; break;
            case 1: sum+=2; break;
            case 7: sum+=3; break;
            case 19: break;
            case 25: sum+=2; break;
            case 5: sum+=2; break;
            case 11: sum+=1; break;
            case 17: sum+=2; break;
            case 23: sum+=1; break;
        }
        switch(cube[2][2][1].getId()){
            case 3: sum+=2; break;
            case 9: sum+=2; break;
            case 15: sum+=1; break;
            case 21: sum+=1; break;
            case 1: sum+=3; break;
            case 7: sum+=2; break;
            case 19: sum+=2; break;
            case 25: break;
            case 5: sum+=2; break;
            case 11: sum+=2; break;
            case 17: sum+=1; break;
            case 23: sum+=1; break;
        }
        switch(cube[0][1][2].getId()){
            case 3: sum+=2; break;
            case 9: sum+=2; break;
            case 15: sum+=2; break;
            case 21: sum+=3; break;
            case 1: sum+=1; break;
            case 7: sum+=1; break;
            case 19: sum+=2; break;
            case 25: sum+=2; break;
            case 5: break;
            case 11: sum+=1; break;
            case 17: sum+=1; break;
            case 23: sum+=2; break;
        }
        switch(cube[1][0][2].getId()){
            case 3: sum+=2; break;
            case 9: sum+=2; break;
            case 15: sum+=3; break;
            case 21: sum+=2; break;
            case 1: sum+=1; break;
            case 7: sum+=2; break;
            case 19: sum+=1; break;
            case 25: sum+=2; break;
            case 5: sum+=1; break;
            case 11: break;
            case 17: sum+=2; break;
            case 23: sum+=1; break;
        }
        switch(cube[1][2][2].getId()){
            case 3: sum+=2; break;
            case 9: sum+=3; break;
            case 15: sum+=2; break;
            case 21: sum+=2; break;
            case 1: sum+=2; break;
            case 7: sum+=1; break;
            case 19: sum+=2; break;
            case 25: sum+=1; break;
            case 5: sum+=1; break;
            case 11: sum+=2; break;
            case 17: break;
            case 23: sum+=1; break;
        }
        switch(cube[2][1][2].getId()){
            case 3: sum+=3; break;
            case 9: sum+=2; break;
            case 15: sum+=2; break;
            case 21: sum+=2; break;
            case 1: sum+=2; break;
            case 7: sum+=2; break;
            case 19: sum+=1; break;
            case 25: sum+=1; break;
            case 5: sum+=2; break;
            case 11: sum+=1; break;
            case 17: sum+=1; break;
            case 23: break;
        }
        return sum/8;
    }
}
