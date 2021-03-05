package mx.edu.j2se.ParadaS.evaluation;

/**
 * class Circle
 * @version 1.0 04 Mar 2021
 * @author Eduardo Parada
 */


public class Circle {

    // Attributes

    private int radius;
    double area;

    // Constructor

    Circle (){
        radius = 1;
    }

    Circle(int radius) {
        try {
            if(radius <= 0){
                throw new IllegalArgumentException ("Ops, " + radius + " is invalid");
            }
            else{
                this.radius = radius;
            }
        }catch (IllegalArgumentException e){
            System.out.println("Ops, " + radius + " is invalid");
        }
    }

    // Methods

    int getRadius(){
        return radius;
    }


    void setRadius (int radius){
        try {
            if(radius <= 0){
                throw new IllegalArgumentException ("This radius is invalid");
            }
            else{
                this.radius = radius;
            }
        } catch (IllegalArgumentException e){
            System.out.println("Ops, a radius of " + radius + " is invalid");
        }
    }

    double getArea(){
        return Math.PI * Math.pow(getRadius(),2);
    }

}
