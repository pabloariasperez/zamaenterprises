package samurai.escenarios;

import java.io.IOException;
import java.util.Stack;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;
import samurai.juego.Global;
import samurai.juego.Juego;
import samurai.juego.TiempoEscenario;

/**
  Se encarga de colocar la ambientación y de dibujar el camino
 * @author Pablo, Erik, Daniel
 * @version 1.3 Noviebre 2010
 */
public class Escenario {
 
    private ManejadorFondos manejadorFondos;

    private Sprite piedra;
    private Image fondoCamino;
    private TiempoEscenario tiempo;
    private Stack parametrosCamino;
    private int tiempoProxEvento;
    private ManejadorAmbiente ambiente;
    private boolean esFinEscenario;
    private Mapa mapaAvance;


    /**
     * Constructor: no tiene argumentos porque cada uno de sus elementos será alimentado por otros métodos.
     * @param escenarioActual numero de escenario a cargar
     * @param tiempoInicio momento en que se inicia
     */
    public Escenario(int escenarioActual, int tiempoInicio ){
        
        //Inicializamos cada uno de los atributos.
        this.manejadorFondos = new ManejadorFondos();
        this.ambiente= new ManejadorAmbiente();
        try {
            if(escenarioActual==Nivel.NIVEL_1){
            fondoCamino = Image.createImage("/samurai/imagenes/ambiente/fondoCamino.png");
            }else{
            fondoCamino = Image.createImage("/samurai/imagenes/ambiente/fondoCamino2.png");
            }
            piedra = new Sprite(Image.createImage("/samurai/imagenes/ambiente/spritePiedra.png"), 20, 20);
            piedra.setFrameSequence(new int[]{0,1,2,3});
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        tiempo = new TiempoEscenario();
        tiempo.setTiempo(tiempoInicio);
        parametrosCamino = Nivel.llenarStackParametro(escenarioActual);
        tiempoProxEvento = ((int[])parametrosCamino.peek())[0];
        obtenerPrimerEvento();

        esFinEscenario = false;
        mapaAvance = new Mapa( obtenerUltimoEvento() , tiempo);
    }
    /**
     * Se accede indirectamente al manejador de fondos del escenario. Se alimenta con la información del atributo.
     * @param fondo : fondo a agregar al escenario
     */
    public void agregarFondo(FondoCapa fondo){
        this.manejadorFondos.agregarFondo(fondo);
    }
    /**
     * Se dibujan los fondos
     * @param g Graficos
     */
    public void dibujarFondos(Graphics g){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.dibujar(g);
        }
    }

    /**
     * regresa el alto de los fondos
     * @return alto de los fondos
     */
    public int getAltoFondos() {
        return manejadorFondos.getAlto();
    }
    
    /**
     * Se dibuja el escenario.
     * Se dibuja el fondo
     * Se dibuja el camino
     * Se dibuja el ambiente
     * Se dibuja el mapa de avance
     * @param g Graficos
     */
    public void dibujar(Graphics g){
        this.dibujarFondoCamino(g);
        Juego.getPosicionador().dibujarCamino(g);
        this.dibujarFondos(g);
        this.ambiente.dibujar(g);
        this.mapaAvance.dibujar(g);
    }
    
    /**
     * Actualiza el escenario
     */
    public void actualizar(){
        if(!manejadorFondos.isEmpty()){
            manejadorFondos.actualizar();
        }

        if( tiempo.actual() == tiempoProxEvento && !parametrosCamino.isEmpty() ){
            manejadorFondos.setParametro(((int[])parametrosCamino.peek())[1]);
            Juego.getPosicionador().generarNuevoEje(((int[])parametrosCamino.pop())[1] );
        }
        if(!parametrosCamino.isEmpty() && !esFinEscenario ){
            tiempoProxEvento = ((int[])parametrosCamino.peek())[0];
        }else{
            esFinEscenario = true;
        }

        Juego.getPosicionador().hayNuevoEje();
        tiempo.incrementar();
        this.ambiente.actualizar();
    }

    /**
     * Dibuja el fondo del camino
     * @param g Graficos
     */
    private void dibujarFondoCamino(Graphics g) {
        g.setColor(0x0000AA00);
        g.fillRect(0, Juego.altoFondo, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA - Juego.altoFondo);
        g.drawImage(fondoCamino, 0, Juego.altoFondo, Graphics.LEFT|Graphics.TOP);
    }

    /**
     * inidica si ya va a acabar el escenario
     * @return si es fin de escenario
     */
    public boolean esFinEscenario(){
        return esFinEscenario;
    }

    /**
     * regresa el tiempo actual
     * @return tiempo actual
     */
    public int tiempoActual(){
        return tiempo.actual();
    }

    private void obtenerPrimerEvento() {
        while( ((int[])parametrosCamino.peek())[0] < tiempo.actual() ){
            parametrosCamino.pop();
        }
    }

    /**
     * regresa el parametro
     * @return parametro
     */
    public int obtenerParametro(){
        return ((int[])parametrosCamino.peek())[1];
    }

    private int obtenerUltimoEvento() {
        return ((int[])parametrosCamino.elementAt(0))[0];
    }

    public void cargarAmbiente() {
        for( int c=0; c < Global.ALTO_PANTALLA/Juego.ALTO_LINEA; c++){
            ambiente.actualizar();
        }
    }
}