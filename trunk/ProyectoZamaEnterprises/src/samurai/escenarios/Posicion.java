package samurai.escenarios;

import samurai.juego.Coordenada;

/**
 *
 * @author Pablo
 */
public class Posicion {
    private Coordenada x;
    private Coordenada y;

    public Posicion( int coordenadaX, int coordenadaY){
        this.x = new Coordenada( coordenadaX );
        this.y = new Coordenada( coordenadaY );
    }

    public Coordenada getX(){
        return this.x;
    }

    public Coordenada getY(){
        return this.y;
    }
}
