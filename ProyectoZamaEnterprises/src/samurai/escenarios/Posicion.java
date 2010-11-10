package samurai.escenarios;

/**
 * Clase posición almacena las dos coordenadas (x, y) de los sprites y otras cosas.
 * @author Pablo, Erik, Daniel
 * @version 1.1 Octubre 2010
 */
public class Posicion {
    private int x;
    private int y;
    /**
     * Constructor que recibe las dos coordenadas.
     * @param x posicion en x
     * @param y posicion en y
     */
    public Posicion( int x, int y){
        this.x = x;
        this.y = y;
    }
    /**
     * regresa el x
     * @return x
     */
    public int getX(){
        return this.x;
    }

    /**
     * regresa el y
     * @return y
     */
    public int getY(){
        return this.y;
    }
    /**
     * cambia la x
     * @param x nueva x
     */
    public void setX( int x ){
        this.x = x;
    }

    /**
     * cambia la y
     * @param y nueva y
     */
    public void setY( int y ){
        this.y = y;
    }
}