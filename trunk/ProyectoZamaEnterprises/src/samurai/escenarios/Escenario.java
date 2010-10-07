/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import javax.microedition.lcdui.Graphics;
import samurai.animacion.SpriteEnemigo;
import samurai.juego.*;

/**
 *
 * @author mi16
 */
public class Escenario {

    private ManejadorFondos manejadorFondos;
    private ManejadorEnemigos manejadorEnemigos;
    private ManejadorSekai manejadorSekai;

    public Escenario(){

        manejadorFondos = new ManejadorFondos();
        manejadorEnemigos = new ManejadorEnemigos();
        manejadorSekai = null;

    }

    public void crearSekai(ManejadorSekai manejadorSekai){
        this.manejadorSekai = manejadorSekai;
    }

    public void agregarFondo(FondoCapa fondo){
        manejadorFondos.agregarFondo(fondo);

    }
    //Se le esta mandando un atributo sprite, solucion provisional para mostrar los enemigos, los leera de un archivo
    public void leerEnemigos(SpriteEnemigo sprite){ 
        manejadorEnemigos.agregarEnemigo(sprite);

    }

    public void dibujarFondos(Graphics g){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.dibujar(g);
        }
    }
    public void dibujarEnemigos(Graphics g){
        if(!manejadorEnemigos.isEmpty()){
            manejadorEnemigos.dibujar(g);
        }

    }

    public void dibujar(Graphics g){
        this.dibujarEnemigos(g);
        this.dibujarFondos(g);
    }

    public void actualizar(){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }
        if(!manejadorEnemigos.isEmpty()){
            manejadorEnemigos.actualizar();
        }
    }


}
