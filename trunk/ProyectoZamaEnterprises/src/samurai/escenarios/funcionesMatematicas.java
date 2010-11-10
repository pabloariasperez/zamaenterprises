package samurai.escenarios;

import javax.microedition.lcdui.game.GameCanvas;

/**
 *
 * @author Pablo, Erik, Daniel
 * 
 */
public class funcionesMatematicas {

    private final int  ALTO_FONDO;
    private GameCanvas gmCanvas;
    private static int ALTO, ANCHO;
    /**
     *
     * @param juego
     */
    public funcionesMatematicas(GameCanvas juego){
        this.gmCanvas=juego;
        this.ALTO_FONDO = 60;
        this.ALTO=gmCanvas.getHeight();
        this.ANCHO = gmCanvas.getWidth();
    }
    /**
     *
     * @param y
     * @param xAlta
     * @param xBaja
     * @return
     */
    public int funcionRegresaX(int y, int xAlta, int xBaja){
        float pendiente;

        pendiente = ((xBaja - xAlta)/(this.ALTO-this.ALTO_FONDO-1));
        return (int)((pendiente*(y-this.ALTO_FONDO-1)+xAlta));


    }

    /**
     *
     * @param y
     * @param xAlta
     * @param xBaja
     * @return
     */
    public int funcionRegresaXComplemento(int y , int xAlta,int xBaja){
     float pendiente;
     pendiente =((xAlta-xBaja))/(this.ALTO-this.ALTO_FONDO-1);
     return (int)((pendiente*(y-this.ALTO_FONDO-1)+(this.ANCHO-xAlta)));

    }

}
