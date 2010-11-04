/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.juego;

import java.util.Stack;
import java.util.Vector;
import samurai.presentacion.Timor;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.presentacion.Diapositiva;

/**
 *
 * @author mi16
 */

public class PresentacionCanvas  extends GameCanvas implements Actualizable {

    private SamuraiEnterprises samuraiMidlet;
    private Stack diapositivas;
    private Graphics g;
    private Animador animador;
    private Timor timer;
    private Diapositiva diapositivaActual;
    private boolean pare;
    private boolean camine;
    private int tipo;
    private final int C_PABLO=0;
    private final int C_ERIK=1;
    private final int C_DANIEL=2;
    private boolean mostrandome;

    public PresentacionCanvas(SamuraiEnterprises samuraiMidlet, int tipo){

        super(true);
        this.mostrandome = true;
        diapositivas = new Stack();
        g = this.getGraphics();
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        timer = new Timor(2,3);
        this.pare=false;
        this.camine=true;
        this.tipo=tipo;
        
        
        this.agregarDiapositivas();
        diapositivaActual = (Diapositiva)this.diapositivas.pop();

        animador = new Animador(this);
        animador.iniciar();

    }
    private void agregarDiapositivas(){
        switch(this.tipo){
            case Diapositiva.CREDITO:
            Diapositiva creditoDaniel = new Diapositiva("/samurai/imagenes/creditos/Daniel.png", "/samurai/imagenes/creditos/pruebin.png", agregarString(C_DANIEL));
            Diapositiva creditoErik = new Diapositiva("/samurai/imagenes/creditos/Erik.png", "/samurai/imagenes/creditos/pruebin.png", agregarString(C_ERIK));
            Diapositiva creditoPablo = new Diapositiva("/samurai/imagenes/creditos/Pablo.png", "/samurai/imagenes/creditos/pruebin.png", agregarString(C_PABLO));
            this.diapositivas.push(creditoDaniel);
            this.diapositivas.push(creditoErik);
            this.diapositivas.push(creditoPablo);
        break;
            case Diapositiva.TUTORIAL:
                break;
            case Diapositiva.PROLOGO:
                break;
            case Diapositiva.EPILOGO:
                break;
            default:
                break;
        }
    }
    private Vector agregarString(int diapositiva){
            Vector texto=new Vector();
            switch(diapositiva){
                case C_PABLO:
                    texto.addElement("         Tengo Chinitos");
                    texto.addElement("         Meloooooon!!!");
                    texto.addElement("         Osssshhhaaaa");
                   
                    return texto;
                case C_ERIK:
                    texto.addElement("");
                    texto.addElement("                     ...");
                    texto.addElement("");
                    return texto;
                case C_DANIEL:
                    texto.addElement("               (  .  V  .  )");
                    texto.addElement("                  )   .   (");
                    texto.addElement("                (    W    )");
                    return texto;
                default:
                    return texto;
            }
    }

    public void actualizar() {
        
            if(!estoyCentrado()){
                     this.diapositivaActual.actualizar();
                }
                else{
                    timer.tik();
                    if(timer.activarIteracion()){
                       this.camine=false;
                       this.pare = !this.pare;
                    }
                }
            if(!this.diapositivaActual.estoyMostrandome()){
                System.out.println("entrando a la condicion");
                if(!this.diapositivas.isEmpty()){
                this.diapositivaActual= (Diapositiva)this.diapositivas.pop();
                this.pare=false;
                this.camine=true;
                }else {
                    this.animador.terminar();
                    System.out.println("entrando al switch");
                    switch(this.tipo){
                        case Diapositiva.CREDITO:

                        this.samuraiMidlet.mostrarMenu();
                        break;
            }
        }
    }
    }

    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        this.diapositivaActual.dibujar(g);
        
        this.flushGraphics();
    }
    public boolean estoyMostrandome(){
        return this.mostrandome;
    }
 private boolean estoyCentrado(){
       if(this.camine && (this.diapositivaActual.posicionXImagen()<=(Global.ANCHO_PANTALLA/2)) && !(this.pare)){
           this.pare=true;
           return true;
       }else if(this.pare){
            return true;
       }else
           return false;

    }
}
