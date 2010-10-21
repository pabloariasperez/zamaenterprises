package samurai.menu;


import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.lcdui.game.Sprite;


/**
 * Agrega los botones al menu
 * Mueve la opcion
 * Mueve los botones
 * Mueve el indicador
 * Cambia el fondo del menu
 * @author Pablo, Erik, Daniel
 * @version 1.2 Octubre 2010
 */
public class Menu {
    private Sprite titulo;
    private Boton[] botones;
    private FondoMenu[] fondosMenus;
    private Indicador indicador;
    private int numButtons;
    private int indexBotonSeleccionado;
    private final int ANCHO_PANTALLA;
    private final int ALTO_PANTALLA;


    //Márgenes desde la izquierda de la pantalla.
    private final int MARGEN_NO_SELECCIONADO = 10;       //La opción NO seleccionada.
    private final int MARGEN_SELECCIONADO = 60;          //La opción seleccionada.
    private final int MARGEN_INDICADOR = 15;            //El SPRITE de fondo de la opción seleccionada.

    /**
     *Nombre del menú, simulando un ENUM que almacena MenuCanvas
     */
    public final int nombreMenu;
    /**
     * Constructor donde se inicializan las variables
     * @param totalButtons indica el numero total de botones que tendra el manu
     * @param archivoTitulo direccion de la imagen a usar como titulo
     * @param archivoIndicador direccion de la imagen a usar como indicador
     * @param gmCanvas
     * @param nombreMenu indica que menu es
     * @throws IOException cuando la direccion de alguna imagen es incorrecta o inexistente
     */
    public Menu( int totalButtons, String archivoTitulo, String archivoIndicador, GameCanvas gmCanvas, int nombreMenu) throws IOException{
        //Se inicializa el arreglo de botones de un tamaño específico según los parámetros del constructor.
        botones = new Boton[ totalButtons ];
        fondosMenus = new FondoMenu [ totalButtons ];
       this.nombreMenu = nombreMenu;
        
        //Se guarda el alto y ancho de pantalla que será CONSTANTE.
        this.ALTO_PANTALLA = gmCanvas.getHeight();
        this.ANCHO_PANTALLA = gmCanvas.getWidth();
        //Se crea el Sprite Título y se modifica su posición en pantalla con el que sería dibujado.
        titulo= new Sprite(Image.createImage(archivoTitulo));
        titulo.setPosition(20, 20);
        //Se crea el sprite delINDICADOR:
        this.indicador = new Indicador (archivoIndicador,MARGEN_INDICADOR,this.MARGEN_SELECCIONADO, ANCHO_PANTALLA );
        //Establecemos nuestro total de botones agregados en cero.
        this.numButtons = 0;
        this.indexBotonSeleccionado = 0;
    }
    
    /**
     * Regresa la indexBotonSeleccionado del boton
     * @return Regresa la indexBotonSeleccionado del boton
     */
    public int getPosition(){
        return this.indexBotonSeleccionado;
    }

    /**
     * Agregaremos botones a nuestro arreglo de botones de nuestro menú.
     * @param b boton a agregar
     * @param archivoSpriteFondo direccion del Fondo
     */
    public void agregarBoton(Boton b, String archivoSpriteFondo){
        //Preguntamos si ya se ha definido el alto de botón al indicador, para centrar correctamente el indicador respecta al botón.
        if( ! indicador.isDefiniedAltoBoton() ){
            indicador.setBotonAlto(b.getAlto());
        }

        //Según en el número de botones que llevamos guardados metemos nuestro siguiente botón que hemos recibido en el parámetro de nuestro método.
        botones[numButtons] = b;

        //Preguntamos si es el primer botón en ser agregado, si es así, reasignamos el valor en X del primer botón
        //a un márgen de seleccionado. Además guardamos que la posición del botón seleccionado es 0.
        if( numButtons == 0){
            b.setX( MARGEN_SELECCIONADO );
            this.indexBotonSeleccionado = 0;
        }else{
            b.setX( MARGEN_NO_SELECCIONADO );
        }

        //Colocamos en su coordenada Y a cada uno de los botones conforme son agragados. La función... está padre.
        b.setY(MARGEN_SELECCIONADO + (numButtons*((this.ALTO_PANTALLA-MARGEN_SELECCIONADO)/getTotalButtons())));
        try {
            //Creamos el sprite de fondo según la opción
            fondosMenus[numButtons] = new FondoMenu(archivoSpriteFondo, ANCHO_PANTALLA, ALTO_PANTALLA);
            fondosMenus[numButtons].setPosition(numButtons, numButtons);
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error al crear el sprite de fondo del menú.");
        }


        //IMPORTANTE: Se incrementa el número de botones en uno, puesto que ya se ha agragado un nuevo botón al arreglo.
        numButtons++;
    }
    
    /**
     * Regresa el numero total de botones que hay en el menú
     * @return Regresa el numero total de botones que hay en el menú
     */
    public int getTotalButtons(){
        return this.botones.length;
    }

    /**
     * Dibuja todo el menú con sus opciones.
     * Dibuja: Indicador, título y cada uno de los botones en sus respectivas posiciones.
     * @param g Los graficos donde se dibuja
     */
    public void dibujar(Graphics g) {
        fondosMenus[indexBotonSeleccionado].dibujar(g);
        this.indicador.dibujar(g);
        this.titulo.paint(g);
        //Recorre el arreglo de botones para dibujarlos.
        for(int opcionIndex =0; opcionIndex<this.numButtons; opcionIndex++){
             botones[opcionIndex].dibujar(g);
         }
    }
    /**
     * Mueve el indicador a la opción actualmente seleccionada indicada por el indexBotonSeleccionado.
     */
    public void moverIndicador(){
        int nuevaY = this.MARGEN_SELECCIONADO+(indexBotonSeleccionado*((this.ALTO_PANTALLA-this.MARGEN_SELECCIONADO)/getTotalButtons()));
        this.indicador.cambiarPosicion(this.MARGEN_INDICADOR, nuevaY);
    }

    //NUestra función le cambia la X al botón de argumento simulando un margen para denotar al suusario si está seleccionado o no lo está.
    private void cambiarMargen(Boton boton){
        if( botones[indexBotonSeleccionado].equals( boton ) ){
            boton.setX(MARGEN_NO_SELECCIONADO);
        } else{
            boton.setX(MARGEN_SELECCIONADO);
        }
    }

    /**
     * El usuario se mueve de opción y animamos para que se de cuenta. Línea indicador y SLASH de fondo.
     * @param direccion hacia donde se va a mover
     */
    public void moverOpcion(int direccion){
         //Cambiamos el margen del botón actualmente seleccionado.
         cambiarMargen( this.botones[indexBotonSeleccionado] );
         if( direccion == 1){
            if( indexBotonSeleccionado + 1 > getTotalButtons() - 1 ){
                cambiarMargen( this.botones[0] );
                indexBotonSeleccionado=0;
                moverIndicador();
            }else{
                cambiarMargen( this.botones[indexBotonSeleccionado+1] );
                indexBotonSeleccionado++;
                moverIndicador();
            }
         }else{
            if( indexBotonSeleccionado - 1 < 0 ){
                cambiarMargen( this.botones[getTotalButtons()-1] );
                indexBotonSeleccionado=getTotalButtons()-1;
                moverIndicador();
            }else{
                cambiarMargen( this.botones[indexBotonSeleccionado-1] );
                indexBotonSeleccionado--;
                moverIndicador();
            }
         }
     }
}
