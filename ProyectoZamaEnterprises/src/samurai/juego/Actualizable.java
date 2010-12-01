package samurai.juego;

/**
 * Interface que obliga a que se deba actualizar y a que regresen un Graphics.
 * @author Pablo, Erik, Daniel
 * @version 1.0 Octubre 2010
 */
public interface Actualizable {

    /**
     * String que indica que un game canvas es tipo juego
     */
    public static final String JUEGO = "JUEGO";
    /**
     * String que indica que un game canvas es tipo menu
     */
    public static final String MENU = "MENU";
    /**
     * String que indica que un game canvas es tipo presentación
     */
    public static final String PRESENTACION = "PRESENTACION";
    /**
     * String que indica que un game canvas es tipo splash
     */
    public static final String SPLASH = "SPLASH";
    /**
     * String que indica que un game canvas es tipo puntajes
     */
    public static final String PUNTAJES = "PUNTAJES";

    /**
     * Actualiza
     */
    public abstract void actualizar();

    /**
     * Dibuja
     */
    public abstract void dibujar();

    /**
     * regresa el tipo de gameCanvas que es
     * @return tipo de gameCanvas que es
     */
    public abstract String tipoCanvas();

    /**
     * Destructor
     */
    public abstract void destruir();

    /**
     * obliga a pausar
     */
    public void pausar();

    /**
     * corre el animador
     */
    public void correr();
}
