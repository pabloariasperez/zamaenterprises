package samurai.menu;


import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;


public class Menu {
    private Boton[] botones;
    private int numButtons;
    private int posicion;
    private Image titulo;
    private final int ALTO;
    static final int MARGEN_SELECCIONADO = 60;

    public Menu( int totalButtons, int alto){
        this.ALTO = alto;
        this.numButtons = 0;
        botones = new Boton[ totalButtons ];
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

    public void dibujar(Graphics g) {
         for(int i =0; i<this.numButtons; i++){
             botones[i].dibujar(g);
         }
    }

     public void moverOpcion(int haciaDonde){
         this.botones[posicion].switchImage();
         if( haciaDonde == 1){
            if( posicion + 1 > getTotalButtons() - 1 ){
                this.botones[0].switchImage();
                posicion=0;
            }else{
                this.botones[posicion+1].switchImage();
                posicion++;
                System.out.println(posicion);
            }
         }else{
            if( posicion - 1 < 0 ){
                this.botones[getTotalButtons()-1].switchImage();
                posicion=getTotalButtons()-1;
            }else{
                this.botones[posicion-1].switchImage();
                posicion--;
            }
         }
     }

}
