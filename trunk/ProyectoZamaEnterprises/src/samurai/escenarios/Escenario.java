/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import javax.microedition.lcdui.Graphics;

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

    private int alturaFondo;

    //El constructor no tiene argumentos porque cada uno de sus elementos será alimentado por otros métodos.
    public Escenario(){
        //Inicializamos cada uno de los atributos.
        this.manejadorFondos = new ManejadorFondos();

        //En el caso de manejadorSekai el objeto se recibirá enteramente por medio de otro método: crearSekai();
    }

    //Se accede indirectamente al manejador de fondos del escenario. Se alimenta con la información del atributo.
    public void agregarFondo(FondoCapa fondo){
        this.manejadorFondos.agregarFondo(fondo);
    }

    

    //Se dibujan los fondos
    public void dibujarFondos(Graphics g){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.dibujar(g);
        }
    }

    public int getAltoFondos() {
        return manejadorFondos.getAlto();
    }


    //Se dibuja el escenario. Se tiene delegado el dibujar fondos y enemigos a otros métodos.
    public void dibujar(Graphics g){
        this.dibujarFondos(g);
    }

    //Actualiza el escenario
    public void actualizar(){
        
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }
    }
}
