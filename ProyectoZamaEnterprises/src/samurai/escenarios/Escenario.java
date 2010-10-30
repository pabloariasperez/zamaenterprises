/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.io.IOException;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;
import samurai.juego.*;

/**
 *
 * @author mi16
 */
public class Escenario {
    /*El escenario dibujará y administrará en conjunto
     *      +Fondos
     *      +Enemigos
     *      +Camino del Escenario
     *      +El tiempo
     *      +Sekai
     */
    private ManejadorFondos manejadorFondos;
    private ManejadorEnemigos manejadorEnemigos;
    private ManejadorSekai manejadorSekai;
    private TiempoEscenario tiempo;
    private Stack enemigosEnEspera;
    private int tiempoProxEvento;
    private int alturaFondo;

    //El constructor no tiene argumentos porque cada uno de sus elementos será alimentado por otros métodos.
    public Escenario(){
        //Inicializamos cada uno de los atributos.
        this.manejadorFondos = new ManejadorFondos();
        this.manejadorEnemigos = new ManejadorEnemigos();
        this.tiempo= new TiempoEscenario();

        //En el caso de manejadorSekai el objeto se recibirá enteramente por medio de otro método: crearSekai();
        this.manejadorSekai = null;
    }

    //Guardamos en nuestro atributo manejadorSekai el alimentado en el atributo.
    public void crearSekai(ManejadorSekai manejadorSekai){
        this.manejadorSekai = manejadorSekai;
        this.manejadorEnemigos.setEspada(manejadorSekai.getEspada());
    }

    //Se accede indirectamente al manejador de fondos del escenario. Se alimenta con la información del atributo.
    public void agregarFondo(FondoCapa fondo){
        this.manejadorFondos.agregarFondo(fondo);
    }

    //Se accede indirectamente al manejador de enemigos del escenario. Se alimenta con la información del atributo.
    public void agregarEnemigo(int tipoEnemigo){
        try {
            //Obligamos
            int posicionAleatoria = (int) System.currentTimeMillis() % 3;
            switch (tipoEnemigo) {
                case SpriteEnemigo.MURCIELAGO:
                    manejadorEnemigos.agregarEnemigo(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, 20));
                    break;
                case SpriteEnemigo.RATA:
                    manejadorEnemigos.agregarEnemigo(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, 20));
                    break;
                case SpriteEnemigo.FANTASMA:
                    manejadorEnemigos.agregarEnemigo(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, 20));
                    break;
                case SpriteEnemigo.TOPO:
                    manejadorEnemigos.agregarEnemigo(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, 20));
                    break;
                case SpriteEnemigo.CESAR:
                    manejadorEnemigos.agregarEnemigo(new SpriteEnemigo("/samurai/imagenes/enemigos/spriteZubat.png", posicionEnemigo(posicionAleatoria), 60, 20));
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //Se dibujan los fondos
    public void dibujarFondos(Graphics g){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.dibujar(g);
        }
    }

    //Se dibujan los enemigos
    public void dibujarEnemigos(Graphics g){
        if(!manejadorEnemigos.isEmpty()){
            manejadorEnemigos.dibujar(g);
        }

    }

    //Se dibuja el escenario. Se tiene delegado el dibujar fondos y enemigos a otros métodos.
    public void dibujar(Graphics g){
        this.dibujarEnemigos(g);
        this.dibujarFondos(g);
    }

    //Actualiza el escenario
    public void actualizar(){
        if( !enemigosEnEspera.isEmpty() ){
            if( tiempo.actual() == tiempoProxEvento ){
                agregarEnemigo( ((int[])enemigosEnEspera.pop())[1] );
                tiempoProxEvento = ((int[])enemigosEnEspera.peek())[0];
            }
        }
        
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }
        if(!manejadorEnemigos.isEmpty()){
            manejadorEnemigos.actualizar();
        }
        tiempo.incrementar();
    }

    public void agregarStackEnemigos( Stack enemigosEnEspera ) {
        this.enemigosEnEspera = enemigosEnEspera;
        tiempoProxEvento = ((int[])enemigosEnEspera.peek())[0];
    }

    private int posicionEnemigo(int posicionAleatoria) {
        //Dependerá de la función del escenario.
        return 0;
    }
}