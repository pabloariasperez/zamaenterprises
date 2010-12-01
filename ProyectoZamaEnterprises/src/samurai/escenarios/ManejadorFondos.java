package samurai.escenarios;

import java.util.Vector;
import javax.microedition.lcdui.Graphics;
import samurai.animacion.Animable;

/**
 * Se encarga de manejar los fondos
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public class ManejadorFondos implements Animable {

    private Vector fondos;
    private int parametroVelocidad;

    /**
     * inicializa el vector de los fondos
     */
    public ManejadorFondos() {

        fondos = new Vector();
        parametroVelocidad = 0;
    }

    /**
     * agrega un fondo al vector
     * @param fondo fondo a ser agregado
     */
    public void agregarFondo(FondoCapa fondo) {
        fondos.addElement(fondo);
    }

    public void dibujar(Graphics g) {
        for (int i = 0; i < fondos.size(); i++) {
            ((FondoCapa) fondos.elementAt(i)).dibujar(g);
        }
    }

    /**
     * recorre el vector para actualizar cada fondo
     */
    public void actualizar() {
        for (int i = 0; i < fondos.size(); i++) {
            ((FondoCapa) fondos.elementAt(i)).actualizar();
        }
    }

    /**
     * regresa si esta vacio el vector
     * @return booleano de si esta vacio el vector
     */
    public boolean isEmpty() {
        return fondos.isEmpty();
    }

    /**
     * regresa el alto de los fondos
     * @return alto de los fondos
     */
    public int getAlto() {
        //El fondo más alto siempre se deberá ingresar primero en el vector, razón misma por la que será el primero en dibujarse para que quede hasta atrás.
        return ((FondoCapa) fondos.elementAt(0)).getAlto();
    }

    /**
     * cambia el parametro de velocidad
     * @param parametro nuevo parametro
     */
    public void setParametro(int parametro) {
        if (parametro != parametroVelocidad) {
            this.parametroVelocidad = parametro;
            for (int i = 0; i < fondos.size(); i++) {
                ((FondoCapa) fondos.elementAt(i)).setParametro(parametroVelocidad);
            }
        }
    }
}
