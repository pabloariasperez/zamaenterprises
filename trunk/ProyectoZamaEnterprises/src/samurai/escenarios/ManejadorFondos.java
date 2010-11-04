/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.Animable;

/**
 *
 * @author mi16
 */
public class ManejadorFondos implements Animable {

    private Vector fondos;

    public ManejadorFondos(){

       fondos = new Vector();

    }

     public void agregarFondo(FondoCapa fondo){
     fondos.addElement(fondo);
     }


    public void dibujar(Graphics g) {
        for(int i =0; i<fondos.size(); i++){
            ((FondoCapa)fondos.elementAt(i)).dibujar(g);
        }
    }

    public void actualizar()
    {
        for(int i =0; i<fondos.size(); i++){
            ((FondoCapa)fondos.elementAt(i)).actualizar();
        }
    }

    public boolean isEmpty(){
        return fondos.isEmpty();
    }

    public int getAlto() {
        //El fondo más alto siempre se deberá ingresar primero en el vector, razón misma por la que será el primero en dibujarse para que quede hasta atrás.
        return ((FondoCapa)fondos.elementAt(0)).getAlto();
    }
}
