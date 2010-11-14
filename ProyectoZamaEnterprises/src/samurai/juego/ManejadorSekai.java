package samurai.juego;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.*;

/**
 * Clase que se encarga de animar a Sekai
 * @author Pablo, Erik y Daniel
 * @version 1.0, septiembre 2010
 */
public class ManejadorSekai implements Animable{
    private final int NUMERO_FRAMES_ATAQUE = 5;

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
    private final int X_VIDA;
    private final int Y_VIDA;
    private final int ALTO_VIDA;
    private final int ANCHO_VIDA;
    private int DIFERENCIAL_COLISION_ESPADA;
    private int DIFERENCIAL_COLISION_SEKAI;

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
        this.puntosVidaTotal=50;
        this.ANCHO_VIDA=this.puntosVidaTotal;
        this.X_VIDA=Global.ANCHO_PANTALLA-this.ANCHO_VIDA-15;
        this.Y_VIDA=10;
        this.ALTO_VIDA=10;
        this.puntosVidaActual=this.puntosVidaTotal;
        this.DIFERENCIAL_COLISION_ESPADA = Global.ALTO_PANTALLA - sekai.getHeight() - efectosEspada.getHeight() - 5;
        this.DIFERENCIAL_COLISION_SEKAI = Global.ALTO_PANTALLA - sekai.getHeight();
    }

    /**
     * Dibuja a sekai, al igual que su vida
     * @param g Graficos donde se dibujan los Sprites
     */
    public void dibujar(Graphics g) {
        efectosEspada.dibujar(g);
        sekai.dibujar(g);
        g.setColor(0x0000AA00);
        g.fillArc(this.X_VIDA, Y_VIDA-1, 10+1, ALTO_VIDA+1, 90, 180);
        g.fillRect(this.X_VIDA+5, Y_VIDA,ANCHO_VIDA , ALTO_VIDA);
        g.fillArc(this.X_VIDA+this.ANCHO_VIDA-2, Y_VIDA-1, 10+1, ALTO_VIDA+1, 270, 180);

        g.setColor(0x00000000);
        g.fillRect(this.X_VIDA+5, Y_VIDA+1,ANCHO_VIDA, ALTO_VIDA-2);

        g.setColor(0x0000AA00);
        if(puntosVidaActual < puntosVidaTotal*0.3){
            g.setColor(0xFF0000);
        }
        
        g.fillRect(this.X_VIDA+6, Y_VIDA+2,puntosVidaActual -2, ALTO_VIDA-4);
    }

    /**
     * Metodo que actualiza todos los Sprites que contiene el objeto asi como tambien genera la animación cuando se presiona una tecla.
     * @throws InterruptedException
     */
    public void actualizar() throws InterruptedException{
        sekai.actualizar();
        //Pregunta si actualmente esta en medio de una animacion
        //Si se esta animando el efectosEspada avanza un frame en el sprite
        if(estoyAnimandome == true && frameActual<=NUMERO_FRAMES_ATAQUE-1){
            efectosEspada.ataque();
            frameActual++;
            //si el frame actual del sprite es igual al numero de frames que tien
            //entonces se cambia el estado de animacion y se pasa al frame 0
            if(frameActual == NUMERO_FRAMES_ATAQUE-1 ){
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
    /**
     * regresa un SpriteEspada
     * @return efectosEspada
     */
    public SpriteEspada getEspada(){
        return this.efectosEspada;
    }
    /**
     * Reduce la vida dependiendo de que tipo de enemigo fue el que se la bajo
     * @param enemigo tipo de enemigo
     */
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

    /**
     * indica si hubo una colision entre un enemigo y sekai
     * @param spriteEnemigo enemigo que efectua colision
     * @return true si la colision fue efectuada
     */
    public boolean colisionSekai(SpriteEnemigo spriteEnemigo) {
            return this.sekai.collidesWith(spriteEnemigo, true);
    }

    /**
     * Indca si hubo colision entre la espada y un enemigo
     * @param spriteEnemigo enemigo que efectua colision
     * @return true sii la colision fue efectuada
     */
    public boolean colisionEspada(SpriteEnemigo spriteEnemigo) {
            return this.efectosEspada.collidesWith(spriteEnemigo, true);
    }

    /**
     * regresa la altura de sekai
     * @return la altura de sekai
     */
    public int getHeight() {
        return this.sekai.getHeight();
    }
    public boolean muerteSekai(){
        return this.puntosVidaActual<=0;
    }
}
