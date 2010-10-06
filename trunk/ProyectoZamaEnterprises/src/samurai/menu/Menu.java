package samurai.menu;


import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.Sprite;


public class Menu {
    private Boton[] botones;
    private Indicador indicador;
    private int numButtons;
    private int posicion;
    private Sprite titulo;
    private final int ALTO;
    static final int MARGEN_SELECCIONADO = 60;
    private final int INDICADOR_DEF_X=15;

    public Menu( int totalButtons, int alto, String archivoTitulo, String archivoIndicador) throws IOException{
        this.ALTO = alto;
        this.numButtons = 0;
        titulo= new Sprite(Image.createImage(archivoTitulo));
        titulo.setPosition(20, 20);
        botones = new Boton[ totalButtons ];
        this.indicador= new Indicador (archivoIndicador,INDICADOR_DEF_X,this.MARGEN_SELECCIONADO);
    }
    //Regresa la posicion del boton
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

        b.setY(MARGEN_SELECCIONADO + (numButtons*((this.ALTO-MARGEN_SELECCIONADO)/getTotalButtons())));
        numButtons++;
    }
    //Regresa el numero total de botones que hay en el menú
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
        this.titulo.paint(g);
        for(int i =0; i<this.numButtons; i++){
             botones[i].dibujar(g);
         }
    }
    public void moverIndicador(int posicion){
        int nuevaY=this.MARGEN_SELECCIONADO+(posicion*((this.ALTO-this.MARGEN_SELECCIONADO)/getTotalButtons()));
        this.indicador.cambiarPosicion(this.INDICADOR_DEF_X,nuevaY );
    }

     public void moverOpcion(int haciaDonde){
         this.botones[posicion].switchImage();
         if( haciaDonde == 1){
            if( posicion + 1 > getTotalButtons() - 1 ){
                this.botones[0].switchImage();
                posicion=0;
                moverIndicador(posicion);
            }else{
                this.botones[posicion+1].switchImage();
                posicion++;
                moverIndicador(posicion);
            }
         }else{
            if( posicion - 1 < 0 ){
                this.botones[getTotalButtons()-1].switchImage();
                posicion=getTotalButtons()-1;
                moverIndicador(posicion);
            }else{
                this.botones[posicion-1].switchImage();
                posicion--;
                moverIndicador(posicion);
            }
         }
     }

}
