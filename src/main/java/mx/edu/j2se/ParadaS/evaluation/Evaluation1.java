package mx.edu.j2se.ParadaS.evaluation;

public class Evaluation1 {

    static Circle[] circulos = new Circle [3];

    public static void main (String args[]) {


        Circle badCircle = new Circle(-2);
        circulos[0] = new Circle(2);
        circulos[1] = new Circle(8);
        circulos[2] = new Circle(5);

        System.out.println("El area mas grande es del circulo: " + biggestCircle(circulos));

    }

    static int biggestCircle(Circle[] circles){

        int index = 0;

        for(int i=0; i<circles.length; i++){
            if(circulos[index].getArea() <= circulos[i].getArea()){
                index = i ;
            }
            System.out.println("El area del circulo " + (i+1) + " es: " + circulos[i].getArea());
        }
        return index+1;
    }

}
