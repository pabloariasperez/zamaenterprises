package samurai.juego;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.*;

public class ManejadorSekai implements Animable{
    private final int NUMERO_FRAMES_ATAQUE = 14;

    private SpriteSekai sekai;
    private SpriteEfectos efectosEspada;
    private ManejadorTeclado manejadorTec;
    private boolean estoyAnimandome;
    private final int SECUENCIA_IZQ;
    private final int SECUENCIA_DER;
    private final int SECUENCIA_FRONTAL;
    int frameActual;

    //Recibe como parametros al sprite de sekai, al igual que los Sprites de la espada y efecto, y un manejador de teclado
    public ManejadorSekai(SpriteSekai sekai, SpriteEfectos efectos, ManejadorTeclado manejadorTec){
        this.sekai=sekai;
        this.efectosEspada=efectos;
        this.manejadorTec=manejadorTec;
        this.estoyAnimandome=false;
        frameActual = 0;
        this.SECUENCIA_IZQ=1;
        this.SECUENCIA_DER=2;
        this.SECUENCIA_FRONTAL=3;
    }

    public void dibujar(Graphics g) {
        efectosEspada.dibujar(g);
        sekai.dibujar(g);
    }

    public void actualizar() throws InterruptedException {
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
            //si no es la izquierda cambia la secuencia e inicial el ataque
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
            //si no es la derecha cambia la secuencia e inicial el ataque
            }else{
                efectosEspada.setAtaqueDer();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
          //Pregunta si se presiono la tecla fire
        } else if(manejadorTec.firePresionado()){
            //pregunta si la secuencia seleccionada del sprite es la correspondiente al ataque frontal
            //si lo es inicia el ataque
            if(efectosEspada.getSecuencia()==this.SECUENCIA_FRONTAL){
                efectosEspada.ataque();
            //si no es la frontal cambia la secuencia e inicial el ataque
            }else{
                efectosEspada.setAtaqueFrontal();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
        }

}
    }
