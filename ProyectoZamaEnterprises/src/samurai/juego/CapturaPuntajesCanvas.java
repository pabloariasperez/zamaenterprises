/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.juego;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;
import samurai.almacenamiento.AdministradorData;

/**
 *
 * @author Pablo
 */
public class CapturaPuntajesCanvas extends GameCanvas implements Actualizable {

    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;
    private ManejadorTeclado teclado;
    private Animador animador;
    private Image letras;
    private Sprite indicador;
    private int xTabla, yTabla;
    private final char alfabeto[][] = {
        {'A', 'B', 'C', 'D', 'E', 'F', 'G'},
        {'H', 'I', 'J', 'K', 'L', 'M', 'N'},
        {'O', 'P', 'Q', 'R', 'S', 'T', 'U'},
        {'V', 'W', 'X', 'Y', 'Z'}
    };
    private char[] iniciales;
    private int inicialActual;
    private int pantalla;
    private int puntajeNuevo;

    public CapturaPuntajesCanvas(SamuraiEnterprises samuraiMidlet, int puntajeNuevo) {
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        this.g = this.getGraphics();
        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);

        this.puntajeNuevo = puntajeNuevo;
        try {
            letras = Image.createImage("/samurai/imagenes/letras.png");
            indicador = new Sprite(Image.createImage("/samurai/imagenes/ambiente/arbol.png"));
            indicador.setPosition(Global.ANCHO_PANTALLA / 2 - letras.getWidth() / 2, Global.ALTO_PANTALLA / 2 - letras.getHeight() / 2);
            xTabla = 0;
            yTabla = 0;
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        iniciales = new char[3];
        inicialActual = 0;

        animador = new Animador(this);
        animador.iniciar();
    }

    public void actualizar() {
        if (teclado.downPresionado()) {
            if (yTabla < 3) {
                yTabla++;
            } else {
                yTabla = 0;
            }
        }

        if (teclado.upPresionado()) {
            if (yTabla > 0) {
                yTabla--;
            } else {
                yTabla = 3;
            }
        }

        if (teclado.derPresionado()) {
            if (xTabla < 6) {
                xTabla++;
            } else {
                xTabla = 0;
            }
        }

        if (teclado.izqPresionado()) {
            if (xTabla > 0) {
                xTabla--;
            } else {
                xTabla = 6;
            }
        }

        if (teclado.firePresionado()) {
            if (xTabla >= 5 && yTabla == 3) {
                if (xTabla == 5) {
                    borrarLetra();
                } else if (xTabla == 6) {
                    terminarCaptura();
                }
            } else if (inicialActual < 3) {
                iniciales[inicialActual] = alfabeto[yTabla][xTabla];
                inicialActual++;
            }
            if (inicialActual == 3) {
                xTabla = 6;
                yTabla = 3;
            }
        }

        indicador.setPosition(Global.ANCHO_PANTALLA / 2 - letras.getWidth() / 2 + xTabla * letras.getWidth() / 7,
                Global.ALTO_PANTALLA / 2 - letras.getHeight() / 2 + yTabla * letras.getHeight() / 4);
    }

    public void dibujar() {
        g.setColor(0x0);
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);
        g.drawImage(letras, Global.ANCHO_PANTALLA / 2, Global.ALTO_PANTALLA / 2, g.HCENTER | g.VCENTER);
        indicador.paint(g);
        g.setColor(0x00FFFFFF);
        g.drawString("" + iniciales[0] + iniciales[1] + iniciales[2], Global.ANCHO_PANTALLA / 2, 10, g.HCENTER | g.TOP);
        flushGraphics();
    }

    public String tipoCanvas() {
        return Actualizable.PUNTAJES;
    }

    public void destruir() {
    }

    public void pausar() {
    }

    public void correr() {
    }

    private void borrarLetra() {
        if (inicialActual > 0 && inicialActual <= 3) {
            iniciales[--inicialActual] = ' ';
        }
    }

    private void terminarCaptura() {
        if (inicialActual == 0) {
            iniciales[0] = 'Y';
            iniciales[1] = 'O';
            iniciales[2] = 'U';
        }
        guardarPuntaje("" + iniciales[0] + iniciales[1] + iniciales[2]);
    }

    private void guardarPuntaje(String iniciales) {
        //Establecemos el nuevo puntaje como la base de la pirámide.
        AdministradorData puntajeStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_+Global.NUMERO_PUNTAJES_ALMACENADOS);
        puntajeStore.cambiarRegistro(puntajeNuevo, AdministradorData.REGISTRO_PUNTAJE);
        puntajeStore.cambiarRegistro(iniciales, AdministradorData.REGISTRO_INICIALES);

        for( int c=Global.NUMERO_PUNTAJES_ALMACENADOS-1; c>0; c--){
            puntajeStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_+c);
            if(puntajeNuevo > puntajeStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE)){
                intercambiarPuntajes(c+1, iniciales);
            }
        }

        samuraiMidlet.mostrarPuntajes();
    }

    public static boolean esNuevoPuntajeAlto(int puntajeNuevo) {
        AdministradorData minimoPuntajeMayorStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + Global.NUMERO_PUNTAJES_ALMACENADOS);
        int puntajeASuperar = minimoPuntajeMayorStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE);
        return puntajeNuevo > puntajeASuperar;
    }

    private void intercambiarPuntajes( int puntajeBajo, String iniciales){
        AdministradorData puntajeAltoStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + (puntajeBajo-1));
        AdministradorData puntajeBajoStore = new AdministradorData(AdministradorData.STORE_PUNTAJE_ + puntajeBajo);

        puntajeBajoStore.cambiarRegistro(puntajeAltoStore.regresarValorDato(AdministradorData.REGISTRO_PUNTAJE), AdministradorData.REGISTRO_PUNTAJE);
        puntajeBajoStore.cambiarRegistro(puntajeAltoStore.regresarDato(AdministradorData.REGISTRO_INICIALES), AdministradorData.REGISTRO_INICIALES);

        puntajeAltoStore.cambiarRegistro(puntajeNuevo, AdministradorData.REGISTRO_PUNTAJE);
        puntajeAltoStore.cambiarRegistro(iniciales, AdministradorData.REGISTRO_INICIALES);
    }
}
