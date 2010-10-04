package samurai.menu;


import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Menu {
    private Boton[] botones;
    private Indicador indicador;
    private int numButtons;
    private int posicion;
    private Image titulo;
    private final int ALTO;
    static final int MARGEN_SELECCIONADO = 60;
    private final int INDICADOR_DEF_X, AUMENTO_Y;

    public Menu( int totalButtons, int alto, Indicador indicador){
        this.ALTO = alto;
        this.numButtons = 0;
        botones = new Boton[ totalButtons ];
        this.indicador=indicador;
        this.INDICADOR_DEF_X=15;
        this.AUMENTO_Y=27;
    }

    public int getPosition(){
        return this.posicion;
    }

    public void agregarBoton(Boton b){
        botones[numButtons] = b;

        if( numButtons == 0){
            b.switchImage();
            b.setX( MARGEN_SELECCIONADO );
            this.posicion = 0;

        }

        b.setY(60 + (numButtons*((this.ALTO-60)/getTotalButtons())));
        numButtons++;
    }

    public int getTotalButtons(){
        return this.botones.length;
    }

    public void setDefaultPosition(){
        this.botones[posicion].switchImage();
        this.posicion =0;
        this.botones[posicion].switchImage();

    }

    public void dibujar(Graphics g) {
        this.indicador.dibujar(g);
        for(int i =0; i<this.numButtons; i++){
             botones[i].dibujar(g);
         }
    }

     public void moverOpcion(int haciaDonde){
         this.botones[posicion].switchImage();
         if( haciaDonde == 1){
            if( posicion + 1 > getTotalButtons() - 1 ){
                this.botones[0].switchImage();
                this.indicador.cambiarPosicion(this.INDICADOR_DEF_X, ((this.ALTO-60)/getTotalButtons())+this.AUMENTO_Y);
                posicion=0;
            }else{
                this.botones[posicion+1].switchImage();
                posicion++;
                this.indicador.cambiarPosicion(this.INDICADOR_DEF_X, ((this.ALTO-60)/getTotalButtons())*(posicion+1)+this.AUMENTO_Y);
            }
         }else{
            if( posicion - 1 < 0 ){
                this.botones[getTotalButtons()-1].switchImage();
                posicion=getTotalButtons()-1;
                this.indicador.cambiarPosicion(this.INDICADOR_DEF_X, ((this.ALTO-60)/getTotalButtons())*(posicion+1)+this.AUMENTO_Y);
            }else{
                this.botones[posicion-1].switchImage();
                posicion--;
                this.indicador.cambiarPosicion(this.INDICADOR_DEF_X, ((this.ALTO-60)/getTotalButtons())*(posicion+1)+this.AUMENTO_Y);
            }
         }
     }

}
