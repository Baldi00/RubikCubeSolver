/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rubikcubesolver;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Andrea
 */
public class Face implements Cloneable{
    
    private RubikColor color;
    private RubikOrientation orientation;

    public Face(RubikColor color, RubikOrientation orientation) {
        this.color = color;
        this.orientation = orientation;
    }
    
    public Face(Face f) {
        this.color = f.color;
        this.orientation = f.orientation;
    }

    public RubikColor getColor() {
        return color;
    }

    public void setColor(RubikColor color) {
        this.color = color;
    }

    public RubikOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(RubikOrientation orientation) {
        this.orientation = orientation;
    }

    public void printColor() {
        switch(this.color){
            case WHITE: System.out.print("w"); break;
            case YELLOW: System.out.print("y"); break;
            case RED: System.out.print("r"); break;
            case GREEN: System.out.print("g"); break;
            case BLUE: System.out.print("b"); break;
            case ORANGE: System.out.print("o"); break;
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Face other = (Face) obj;
        if (this.color != other.color) {
            return false;
        }
        if (this.orientation != other.orientation) {
            return false;
        }
        return true;
    }

    public String getColorLetter() {
        switch(this.color){
            case WHITE: return "w";
            case YELLOW: return "y";
            case RED: return "r";
            case GREEN: return "g";
            case BLUE: return "b";
            case ORANGE: return "o";
        }
        return null;
    }
    
    
}
