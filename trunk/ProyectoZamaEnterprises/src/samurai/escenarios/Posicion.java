package samurai.escenarios;

/**
 *
 * @author Pablo
 * Clase posición almacena las dos coordenadas (x, y) de los sprites y otras cosas.
 */
public class Posicion {
    private int x;
    private int y;

    //Constructor que recibe las dos coordenadas.
    public Posicion( int x, int y){
        this.x = x;
        this.y = y;
    }

    //GETTERS
    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    //SETTERS
    public void setX( int x ){
        this.x = x;
    }

    public void setY( int y ){
        this.y = y;
    }
}