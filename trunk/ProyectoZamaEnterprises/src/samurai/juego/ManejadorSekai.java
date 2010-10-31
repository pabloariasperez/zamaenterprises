package samurai.juego;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.*;

/**
 * Clase que se encarga de animar a Sekai
 * @author Pablo, Erik y Daniel
 * @version 1.0, septiembre 2010
 */
public class ManejadorSekai implements Animable{
    private final int NUMERO_FRAMES_ATAQUE = 14;

    private SpriteSekai sekai;
    private SpriteEspada efectosEspada;
    private ManejadorTeclado manejadorTec;
    private boolean estoyAnimandome;
    private final int SECUENCIA_IZQ;
    private final int SECUENCIA_DER;
    private final int SECUENCIA_FRONTAL;
    private int frameActual;
    private final int puntosVidaTotal;
    private int puntosVidaActual;

    //Recibe como parametros al sprite de sekai, al igual que los Sprites de la espada y efecto, y un manejador de teclado
    /**
     * Constructor que iniciliza todas las variables
     * @param sekai Recibe un SpriteSekai que contiene los movimientos del personaje
     * @param efectos Recibe un SpriteEspada que contiene los efectos de la espada.
     * @param manejadorTec Recibe un ManejadorTeclado para detectar las teclas presionadas.
     */
    public ManejadorSekai(SpriteSekai sekai, SpriteEspada efectos, ManejadorTeclado manejadorTec){
        this.sekai=sekai;
        this.efectosEspada=efectos;
        this.manejadorTec=manejadorTec;
        this.estoyAnimandome=false;
        this.frameActual = 0;
        this.SECUENCIA_IZQ=1;
        this.SECUENCIA_DER=2;
        this.SECUENCIA_FRONTAL=3;
        this.puntosVidaTotal=(3/8)*Global.ANCHO_PANTALLA;
        this.puntosVidaActual=this.puntosVidaTotal;
    }

    /**
     *
     * @param g Graficos donde se dibujan los Sprites
     */
    public void dibujar(Graphics g) {
        efectosEspada.dibujar(g);
        sekai.dibujar(g);
    }

    /**
     * Metodo que actualiza todos los Sprites que contiene el objeto asi como tambien genera la animación cuando se presiona una tecla.
     * @throws InterruptedException
     */
    public void actualizar() throws InterruptedException{
        sekai.actualizar();
        //Pregunta si actualmente esta en medio de una animacion
        //Si se esta animando el efectosEspada avanza un frame en el sprite
        if(estoyAnimandome == true & frameActual<=NUMERO_FRAMES_ATAQUE){
            efectosEspada.ataque();
            frameActual++;
            //si el frame actual del sprite es igual al numero de frames que tien
            //entonces se cambia el estado de animacion y se pasa al frame 0
            if(frameActual == NUMERO_FRAMES_ATAQUE ){
                estoyAnimandome = false;
                frameActual=0;
            }
         //Pregunta si se presiono la tecla izquierda
        }else if(manejadorTec.izqPresionado()){
            //pregunta si la secuencia seleccionada del sprite es la correspondiente al ataque izquierdo
            //si lo es inicia el ataque
            if(efectosEspada.getSecuencia()==this.SECUENCIA_IZQ){
                efectosEspada.ataque();
            //si no es la izquierda cambia la secuencia e inicia el ataque
            }else{
                efectosEspada.setAtaqueIzq();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
          //Pregunta si se presiono la tecla derecha
        } else if(manejadorTec.derPresionado()){
            //pregunta si la secuencia seleccionada del sprite es la correspondiente al ataque derecho
            //si lo es inicia el ataque
            if(efectosEspada.getSecuencia()==this.SECUENCIA_DER){
                efectosEspada.ataque();
            //si no es la derecha cambia la secuencia e inicia el ataque
            }else{
                efectosEspada.setAtaqueDer();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
          //Pregunta si se presiono la tecla fire
        } else if(manejadorTec.upPresionado()){
            //pregunta si la secuencia seleccionada del sprite es la correspondiente al ataque frontal
            //si lo es inicia el ataque
            if(efectosEspada.getSecuencia()==this.SECUENCIA_FRONTAL){
                efectosEspada.ataque();
            //si no es la frontal cambia la secuencia e inicia el ataque
            }else{
                efectosEspada.setAtaqueFrontal();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
        }

}
    public SpriteEspada getEspada(){
        return this.efectosEspada;
    }
    public void reducirVida(int enemigo){
         switch (enemigo) {
                case SpriteEnemigo.MURCIELAGO:
                    this.puntosVidaActual-=this.puntosVidaTotal*.10;
                    break;
                case SpriteEnemigo.RATA:
                    this.puntosVidaActual-=this.puntosVidaTotal*.05;
                    break;
                case SpriteEnemigo.FANTASMA:
                    this.puntosVidaActual-=this.puntosVidaTotal*.15;
                    break;
                case SpriteEnemigo.TOPO:
                    this.puntosVidaActual-=this.puntosVidaTotal*.20;
                    break;
                case SpriteEnemigo.CESAR:
                    this.puntosVidaActual=0;
                    break;
            }
    }

    public boolean colisionSekai(SpriteEnemigo spriteEnemigo) {
        return this.sekai.collidesWith(spriteEnemigo, true);
    }

    public boolean colisionEspada(SpriteEnemigo spriteEnemigo) {
        return this.sekai.collidesWith(spriteEnemigo, true);
    }
}
