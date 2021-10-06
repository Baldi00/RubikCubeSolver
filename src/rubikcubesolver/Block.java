/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class Block implements Cloneable{
    private ArrayList<Face> faces;
    private int id;

    public Block() {
        faces = new ArrayList<>();
    }
    
    public Block (Block b) {
        faces = new ArrayList<>();
        setFaces(b.getFaces());
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getId(){
        return id;
    }
    
    public void addFace(Face f) {
        faces.add(f);
    }

    public ArrayList<Face> getFaces() {
        return faces;
    }
    
    public void setFaces(ArrayList<Face> faces) {
        for(Face f : faces){
            Face fNew = new Face(f);
            this.faces.add(fNew);
        }
    }
    
    public void printOnlyOrientation(RubikOrientation faceOrientation) {
        for(Face f : faces){
            if(f.getOrientation() == faceOrientation){
                f.printColor();
            }
        }
    }
    
    public boolean contains(Face face) {
        for(Face f : faces){
            if(f.getColor() == face.getColor() && f.getOrientation() == face.getOrientation()){
                return true;
            }
        }
        return false;
    }
    
    public Face getFaceWithOrientation(RubikOrientation faceOrientation) {
        for(Face f : faces){
            if(f.getOrientation() == faceOrientation){
                return f;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        if (id < 10)
            return "  " + id+ "";
        return " " + id + "";
    }
    
    public boolean equals(Block b2) {
        for(Face f : faces) {
            if(!b2.faces.contains(f)){
                return false;
            }
        }
        return true;
    }
}
