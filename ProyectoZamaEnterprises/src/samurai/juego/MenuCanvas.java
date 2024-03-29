package samurai.juego;

import java.io.IOException;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.game.GameCanvas;
import samurai.almacenamiento.AdministradorData;
import samurai.menu.*;

/**
 *Clase encargada de mostrar en una pantalla independiente el Menú
 * @author Pablo, Erik y Daniel
 * @version 1.0, Octubre 2010
 */
public class MenuCanvas extends GameCanvas implements Actualizable {
    //Atributos esenciales para la clase: midlet, Graphic, dimensiones de pantalla, teclado.
    private SamuraiEnterprises samuraiMidlet;
    private Graphics g;

    private ManejadorTeclado teclado;

    //Efectos dentro del Menu
    private Animador animador;

    //El menuActual nos dirá a quien debemos pintar en pantalla.
    private Menu menuActual;

    /**
     * Constante entera que define el menu principal que ya esta creado.
     */
    public static final int PRINCIPAL = 0;
    /**
     * Constante entera que define el menu de opciones que ya esta creado.
     */
    public static final int OPCIONES = 1;
    /**
     * Constante entera que define el menu de sonido que ya esta creado.
     */
    public static final int SONIDO = 2;
    /**
     * Constante entera que define el menu de salir que ya esta creado.
     */
    public static final int SALIR = 3;
     /**
     * Constante entera que define el menu de salir que ya esta creado.
     */
    public static final int DIFICULTAD = 4;

    //Objetos - Menú Principal.
    private Menu menuPrincipal;
    private Boton botonNuevo, botonContinuar, botonPuntajes, botonOpciones, botonSalir;

    //Objetos - Menú Opciones
    private Boton opcionSonido, opcionCreditos, opcionTutorial,opcionDificultad,opcionAtras;
    private Menu menuOpciones;

    //Objetos - Menú Sonido, Menú Dificultad, Salir
    private Boton opcionSi, opcionNo, opcionDificil, opcionMediana, opcionFacil;
    private Menu menuSonido, menuSalir, menuDificultad;

    private boolean estaGuardado;
    private boolean inicio;
    

    /**
     * Constructor que recibe al samurai e iniciliza todas las contantes.
     * @param samuraiMidlet midlet que se recibe.
     * @param inicio
     */
    public MenuCanvas(SamuraiEnterprises samuraiMidlet, boolean inicio){
        //Se manda TRUE en el constructor de la clase padre (GameCanvas) para decir que cuando se quiera saber si una tecla está presionada
        //se preguntará.
        super(true);
        this.setFullScreenMode(true);
        this.samuraiMidlet = samuraiMidlet;
        //Asignamos a nuestro parámetro "g" el Graphic del GameCanvas
        this.g = this.getGraphics();

        //Creamos nuestro manejador de teclado
        teclado = new ManejadorTeclado(this);
        
        
        //Intentamos crear nuestro Menú Principal. De lo contrario regresamos una excepción.
        try {            
            menuPrincipal = new Menu(5,"/samurai/imagenes/titulos/tituloprincipal.png","/samurai/imagenes/slash.png", MenuCanvas.PRINCIPAL );
            this.crearBotonesMenuPrincipal();

            //Establecemos el Menú Principal como el Menú Actual para ser desplegado.
            menuActual = menuPrincipal;
        }catch(IOException ex){
            ex.printStackTrace();
        }
        this.verificarGuardado();

        this.inicio=inicio;
        if(inicio){
            crearMenu(MenuCanvas.SONIDO);
            cambiarMenu(menuSonido);
        }
        //Creamos nuestra animador y lo iniciamos.
        animador = new Animador(this);
        animador.iniciar();
    }

    //Método para crear todos los botones del menú principal, a la vez que los agrega al Menú.
    private void crearBotonesMenuPrincipal(){
        try {
            //Inicializo cada uno de los botones del menú principal.
            this.botonNuevo = new Boton("/samurai/imagenes/botones/botonNuevo.png");
            this.botonContinuar = new Boton("/samurai/imagenes/botones/botonContinuar.png");
            this.botonPuntajes = new Boton("/samurai/imagenes/botones/botonPuntajes.png");
            this.botonOpciones = new Boton("/samurai/imagenes/botones/botonOpciones.png");
            this.botonSalir = new Boton("/samurai/imagenes/botones/botonSalir.png");

            //Los agregamos a nuestro Menú Principal. Además de indicar el fondo que mostrarán de fondo.
            this.menuPrincipal.agregarBoton(botonNuevo, "/samurai/imagenes/fondosMenu/juegoNuevoLateral.png");
            this.menuPrincipal.agregarBoton(botonContinuar, "/samurai/imagenes/fondosMenu/continuar.png");
            this.menuPrincipal.agregarBoton(botonPuntajes, "/samurai/imagenes/fondosMenu/spriteScores.png");
            this.menuPrincipal.agregarBoton(botonOpciones, "/samurai/imagenes/fondosMenu/spriteLlave.png");
            this.menuPrincipal.agregarBoton(botonSalir, "/samurai/imagenes/fondosMenu/puertas.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void crearBotonesDificultad(){
         try {
            //Inicializo cada uno de los botones del menú de dificultad.
            opcionDificil = new Boton("/samurai/imagenes/botones/botonDificil.png");
            opcionMediana = new Boton("/samurai/imagenes/botones/botonMedia.png");
            opcionFacil = new Boton("/samurai/imagenes/botones/botonFacil.png");

            //Los agregamos a nuestro Menú Dificultad. Además de indicar el fondo que mostrarán de fondo.
            this.menuDificultad.agregarBoton(opcionDificil, "/samurai/imagenes/fondosMenu/dificil.png");
            this.menuDificultad.agregarBoton(opcionMediana, "/samurai/imagenes/fondosMenu/media.png");
            this.menuDificultad.agregarBoton(opcionFacil, "/samurai/imagenes/fondosMenu/facil.png");

        }catch (IOException ex){
            ex.printStackTrace();
        }

    }

    private void creaBotonesOpciones(){
        //Creamos cada uno de los botones.
        try {
            opcionSonido = new Boton("/samurai/imagenes/botones/botonSonido.png");
            opcionCreditos = new Boton("/samurai/imagenes/botones/botonCreditos.png");
            opcionTutorial = new Boton("/samurai/imagenes/botones/botonTutorial.png");
            opcionDificultad = new Boton("/samurai/imagenes/botones/botonDificultad.png");
            opcionAtras = new Boton("/samurai/imagenes/botones/botonAtras.png");

            //Agregamos los botones creados y además asignamos qué imagen de fondo tendrán.
            menuOpciones.agregarBoton(opcionSonido, "/samurai/imagenes/fondosMenu/sonidoLateral.png");
            menuOpciones.agregarBoton(opcionCreditos, "/samurai/imagenes/fondosMenu/creditos.png");
            menuOpciones.agregarBoton(opcionTutorial, "/samurai/imagenes/fondosMenu/spriteTutorial.png");
            menuOpciones.agregarBoton(opcionDificultad, "/samurai/imagenes/fondosMenu/dificultad.png");
            menuOpciones.agregarBoton(opcionAtras, "/samurai/imagenes/fondosMenu/atras.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void creaBotonesSonido() {
        //Creamos cada uno de los botones.
        try {
            opcionSi = new Boton("/samurai/imagenes/botones/botonSi.png");
            opcionNo = new Boton("/samurai/imagenes/botones/botonNo.png");

            //Agregamos los botones creados y además asignamos qué imagen de fondo tendrán.
            menuSonido.agregarBoton(opcionSi, "/samurai/imagenes/fondosMenu/si.png");
            menuSonido.agregarBoton(opcionNo, "/samurai/imagenes/fondosMenu/no.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void creaBotonesSalir() {
        //Creamos cada uno de los botones.
        try {
            opcionNo = new Boton("/samurai/imagenes/botones/botonNo.png");
            opcionSi = new Boton("/samurai/imagenes/botones/botonSi.png");

            //Agregamos los botones creados y además asignamos qué imagen de fondo tendrán.
            menuSalir.agregarBoton(opcionNo, "/samurai/imagenes/fondosMenu/no.png");
            menuSalir.agregarBoton(opcionSi, "/samurai/imagenes/fondosMenu/si.png");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    
    /**
     * Metodo que dibuja el Menú Actual.
     */
    public void dibujar(){    
        //Establecemos nuestro color para dibujar. NEGRO
        g.setColor(0x0);
        //Limpiamos la pantalla.
        g.fillRect(0, 0, Global.ANCHO_PANTALLA, Global.ALTO_PANTALLA);

        //Dibujamos el Menu Actual
        this.menuActual.dibujar(g);
        this.flushGraphics();   
    }

    /**
     * Metodo que actualiza el MenuCanvas de acuerdo a los botones que se hayan presionado en el teclado
     */
    public void actualizar(){
        //Preguntamos si se ha presionado la tecla arriba para mover hacia arirba una opción.
        if( teclado.upPresionado()){
            this.menuActual.moverOpcion(-1);     //Mover hacia arriba.
        //Preguntamos si se ha presionado la tecla abajo para mover hacia abajo una opción.
        }else if(teclado.downPresionado() ){
            this.menuActual.moverOpcion(1);      //Mover hacia abajo.
        //Preguntamos si estamos en la PRIMERA posición y si fue elegida con FIRE.
        } else if (this.menuActual.getPosition() == 0 && teclado.firePresionado()) {
            switch(menuActual.nombreMenu){
                case MenuCanvas.PRINCIPAL:
                    samuraiMidlet.mostrarPrologo();
                    break;
                case MenuCanvas.OPCIONES:
                    crearMenu(MenuCanvas.SONIDO);
                    cambiarMenu(menuSonido);
                    break;
                case MenuCanvas.SONIDO:
                    Global.sonidoOn();
                    if(inicio){
                        crearMenu(MenuCanvas.PRINCIPAL);
                        cambiarMenu(menuPrincipal);
                        inicio=true;
                    }else{
                        crearMenu(MenuCanvas.OPCIONES);
                        cambiarMenu(menuOpciones);
                    }
                    break;
                case MenuCanvas.DIFICULTAD:
                    Global.dificultad = Global.DIFICULTAD_DIFICIL;
                    cambiarMenu(this.menuOpciones);
                    break;
                case MenuCanvas.SALIR:
                    crearMenu(MenuCanvas.PRINCIPAL);
                    cambiarMenu(menuPrincipal);
                    break;
            }
        //Preguntamos si estamos en la SEGUNDA posición y si fue elegida con FIRE.
        }else if(this.menuActual.getPosition() == 1 && teclado.firePresionado()){
            switch(menuActual.nombreMenu){
                case MenuCanvas.PRINCIPAL:
                    if(estaGuardado){
                        samuraiMidlet.continuarJuego();
                    }
                    break;
                case MenuCanvas.OPCIONES:
                    samuraiMidlet.mostrarCreditos();
                    break;
                case MenuCanvas.SONIDO:
                    Global.sonidoOff();
                    if(inicio){
                        crearMenu(MenuCanvas.PRINCIPAL);
                        cambiarMenu(menuPrincipal);
                        inicio=false;
                    }else{
                        crearMenu(MenuCanvas.OPCIONES);
                        cambiarMenu(menuOpciones);
                    }
                    break;
                case MenuCanvas.DIFICULTAD:
                    Global.dificultad = Global.DIFICULTAD_MEDIA;
                    cambiarMenu(this.menuOpciones);
                    break;
                case MenuCanvas.SALIR:
                    samuraiMidlet.destroyApp(true);
                    samuraiMidlet.notifyDestroyed();
                    break;
            }
        //Preguntamos si estamos en la TERCER posición y si fue elegida con FIRE.
        }else if(this.menuActual.getPosition() == 2 && teclado.firePresionado()){
            switch(menuActual.nombreMenu){
                case MenuCanvas.PRINCIPAL:
                    samuraiMidlet.mostrarPuntajes();
                    break;
                case MenuCanvas.OPCIONES:
                    samuraiMidlet.mostrarTutorial();
                    break;
                 case MenuCanvas.DIFICULTAD:
                    Global.dificultad = Global.DIFICULTAD_FACIL;
                    cambiarMenu(this.menuOpciones);
                    break;
            }
        //Preguntamos si estamos en la CUARTA posición y si fue elegida con FIRE.
        }else if(this.menuActual.getPosition() == 3 && teclado.firePresionado()){
            switch(menuActual.nombreMenu){
                case MenuCanvas.PRINCIPAL:
                    crearMenu(MenuCanvas.OPCIONES);
                    cambiarMenu(menuOpciones);
                    break;
                case MenuCanvas.OPCIONES:
                    crearMenu(MenuCanvas.DIFICULTAD);
                    cambiarMenu(menuDificultad);
                    break;
            }
        //Preguntamos si estamos en la QUINTA posición y si fue elegida con FIRE.
        }else if(this.menuActual.getPosition() == 4 && teclado.firePresionado()){
            switch(menuActual.nombreMenu){
                case MenuCanvas.PRINCIPAL:
                    crearMenu(MenuCanvas.SALIR);
                    cambiarMenu(menuSalir);
                    break;
                case MenuCanvas.OPCIONES:
                    crearMenu(MenuCanvas.PRINCIPAL);
                    cambiarMenu(menuPrincipal);
                    break;
            }
        }
    }

    private void cambiarMenu(Menu nuevoMenuActual){
        menuActual = nuevoMenuActual;
    }

    // Método que creará los Menús si no están reado y a la vez volverá NULLS a aquellos Menús que probablemente no se vuelvan a usar.
    private void crearMenu(int nombreMenu){
        //Preguntamos cuál fue el nombre de Menú que fue suministrado, todos declarados desde los nombre de Menú declarados en MenuCanvas
        //Se debe tener cuidado en siempre volver NULL a los Menús que no se estén utlizando.
        try {
            switch( nombreMenu ){
                case MenuCanvas.PRINCIPAL:
                    menuOpciones = null;
                    break;
                case MenuCanvas.OPCIONES:
                    menuOpciones = new Menu(5, "/samurai/imagenes/titulos/tituloOpciones.png", "/samurai/imagenes/slash.png", MenuCanvas.OPCIONES);
                    this.creaBotonesOpciones();
                    menuSonido = null;      //Es poco probable que se vuelva a ingresar a él.
                    break;
                case MenuCanvas.SONIDO:
                    menuSonido = new Menu(2, "/samurai/imagenes/titulos/tituloSonido.png", "/samurai/imagenes/slash.png", MenuCanvas.SONIDO);
                    this.creaBotonesSonido();
                    break;
                case MenuCanvas.SALIR:
                    menuSalir = new Menu(2, "/samurai/imagenes/titulos/tituloSalir.png", "/samurai/imagenes/slash.png", MenuCanvas.SALIR);
                    this.creaBotonesSalir();
                    break;
                case MenuCanvas.DIFICULTAD:
                    this.menuDificultad = new Menu(3, "/samurai/imagenes/titulos/tituloDificultad.png", "/samurai/imagenes/slash.png", MenuCanvas.DIFICULTAD);
                    this.crearBotonesDificultad();
                    switch(Global.dificultad){
                        case Global.DIFICULTAD_DIFICIL:
                            this.menuDificultad.setPosition(0);
                            break;
                        case Global.DIFICULTAD_MEDIA:
                            this.menuDificultad.setPosition(1);
                            break;
                        case Global.DIFICULTAD_FACIL:
                            this.menuDificultad.setPosition(2);
                    }
                    break;
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.gc();
    }

    /**
     * tipo de canvas
     * @return tippo de canvas
     */
    public String tipoCanvas() {
        return Actualizable.MENU;
    }

    /**
     * destructor: interrumpe el animador y nulifica los objetos
     */
    public void destruir() {
        animador.interrumpir();
        animador = null;

        samuraiMidlet=null;
        g=null;
        teclado=null;
        menuActual=null;
        menuPrincipal=null;
        botonNuevo=null; botonContinuar=null; botonPuntajes=null; botonOpciones=null; botonSalir=null;
        opcionSonido=null; opcionCreditos=null; opcionAtras=null;
        menuOpciones=null;
        opcionSi=null; opcionNo=null;
        menuSonido=null; menuSalir=null;
    }

    /**
     * interrumpe el animador
     */
    public void interrumpir() {
        animador.interrumpir();
    }

    /**
     * inicia el animador
     */
    public void correr() {
        if( !animador.estaCorriendo() ){
            animador.iniciar();
        }
    }
    private void verificarGuardado(){
         AdministradorData data = new AdministradorData(AdministradorData.STORE_AVANCE);
         estaGuardado = !data.regresarDato(AdministradorData.REGISTRO_SCORE_ACTUAL).equals(AdministradorData.SVacio);
    }
}
