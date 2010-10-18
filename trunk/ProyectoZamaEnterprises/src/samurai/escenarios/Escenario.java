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
    }

    //Se accede indirectamente al manejador de fondos del escenario. Se alimenta con la información del atributo.
    public void agregarFondo(FondoCapa fondo){
        this.manejadorFondos.agregarFondo(fondo);
    }

    //Se accede indirectamente al manejador de enemigos del escenario. Se alimenta con la información del atributo.
    public void agregarEnemigo(SpriteEnemigo enemigo){
        manejadorEnemigos.agregarEnemigo(enemigo);
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
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }
        if(!manejadorEnemigos.isEmpty()){
            manejadorEnemigos.actualizar();

        }

    }

}
