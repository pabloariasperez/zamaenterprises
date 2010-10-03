package samurai.juego;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.*;

public class ManejadorSekai implements Animable{
    private final int NUMERO_FRAMES_ATAQUE = 14;

    private SpriteSekai sekai;
    private SpriteEfectos efectosEspada;
    private ManejadorTeclado manejadorTec;
    private boolean estoyAnimandome;
    int frameActual;

    public ManejadorSekai(SpriteSekai sekai, SpriteEfectos efectos, ManejadorTeclado manejadorTec){
        this.sekai=sekai;
        this.efectosEspada=efectos;
        this.manejadorTec=manejadorTec;
        this.estoyAnimandome=false;
        frameActual = 0;
    }

    public void dibujar(Graphics g) {
        efectosEspada.dibujar(g);
        sekai.dibujar(g);
    }

    public void actualizar() throws InterruptedException {
        sekai.actualizar();
  
        if(estoyAnimandome == true & frameActual<=NUMERO_FRAMES_ATAQUE){
            efectosEspada.ataque();
            frameActual++;
            if(frameActual == NUMERO_FRAMES_ATAQUE ){
                estoyAnimandome = false;
                frameActual=0;
            }
        }else if(manejadorTec.izqPresionado())
        {
            if(efectosEspada.getSecuencia()==1){
                efectosEspada.ataque();
            }else{
                efectosEspada.setAtaqueIzq();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
        } else if(manejadorTec.derPresionado()){
            if(efectosEspada.getSecuencia()==2){
                efectosEspada.ataque();
            }else{
                efectosEspada.setAtaqueDer();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
        } else if(manejadorTec.firePresionado()){
            if(efectosEspada.getSecuencia()==3){
                efectosEspada.ataque();
            }else{
                efectosEspada.setAtaqueFrontal();
                efectosEspada.ataque();
            }
            estoyAnimandome=true;
        }

}
    }
