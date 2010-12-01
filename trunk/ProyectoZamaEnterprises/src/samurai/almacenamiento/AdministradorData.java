/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.almacenamiento;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * Clase encargada de guardar
 * @author Pablo, Erik, Daniel
 * @version 2.0
 */
public class AdministradorData {
    private RecordStore recordStore;
    private String nombreStore;
    /**
     * String que representa un registro vacio
     */
    public static final String SVacio = "vacio";
    /**
     * Enum que representa el registro del score actual
     */
    public static final int REGISTRO_SCORE_ACTUAL = 1;
    /**
     * Enum que representa el registro de la vida
     */
    public static final int REGISTRO_VIDA = 2;
    /**
     * Enum que representa el registro del nivel
     */
    public static final int REGISTRO_NIVEL = 3;
    /**
     * Enum que representa el registro del tiempo
     */
    public static final int REGISTRO_TIEMPO = 4;
    /**
     * Enum que representa el registro de las iniciales de los scores
     */
    public static final int REGISTRO_INICIALES = 1;
    /**
     * Enum que representa el registro del puntaje de los scores altos
     */
    public static final int REGISTRO_PUNTAJE = 2;
    /**
     * String del nombre del store utilizado para el avance
     */
    public static final String STORE_AVANCE = "AVANCE";
    /**
     * String del nombre del store utilizado para los enemigos
     */
    public static final String STORE_ENEMIGO = "ENEMIGO";
    /**
     * String del nombre del store utilizado para el total de enemigos
     */
    public static final String STORE_TOTAL_ENEMIGOS = "TOTAL_ENEMIGOS";
    /**
     * String del nombre del store utilizado para los puntajes
     */
    public static final String STORE_PUNTAJE_ = "PUNTAJE_";
    /**
     * String del nombre del store utilizado para los puntajes default
     */
    public static final String STORE_PUNTAJES_ESTABLECIDOS = "PUNTAJES_DEFAULT";
    public static  int VALOR_ERROR=-1;


    /**
     * Constructor que abre el store y lo crea si este no existiera
     * @param nombreStore nombre del store a abrir
     */
    public AdministradorData(String nombreStore) {
        this.nombreStore = nombreStore;
        try {
            //Abrimos el Store y pedimos al método que lo cree si aún no existe.
            recordStore = RecordStore.openRecordStore(this.nombreStore, true);
            //LO cerramos. El objetivo de abrirlo y cerrarlo es simplemente asegurarnos de que existe.
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Agrega un registro con la String enviada
     * @param valorRegistro String que se desea guardar
     */
    public void agregarRegistro(String valorRegistro) {
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            byte datosRegistro[] = valorRegistro.getBytes();
            //OFFSET el índice dentro de lso datos del buffer del primer byte relevante en el record.
            recordStore.addRecord(datosRegistro, 0, datosRegistro.length);
            recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Agrega un registro con el valor int enviado
     * @param valorRegistro int que se desea guardar
     */
    public void agregarRegistro(int valorRegistro) {
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            byte datosRegistro[] = intToBytes(valorRegistro);
            recordStore.addRecord(datosRegistro, 0, datosRegistro.length);
            recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia el valor de un registro
     * @param nuevoDato valor nuevo a guardar
     * @param index registro a cambiar
     */
    public void cambiarRegistro(String nuevoDato, int index) {
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            byte datoRegistro[] = nuevoDato.getBytes();
            recordStore.setRecord(index, datoRegistro, 0, datoRegistro.length);
            recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Cambia el valor de un registro
     * @param nuevoDato valor nuevo a guardar
     * @param index registro a cambiar
     */
    public void cambiarRegistro(int nuevoDato, int index) {
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            byte datoRegistro[] = intToBytes(nuevoDato);
            recordStore.setRecord(index, datoRegistro, 0, datoRegistro.length);
            recordStore.closeRecordStore();
        } catch (RecordStoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * Regresa un String con el valor de todos los registros de un store
     * @return String con todos los registros
     */
    public String regresarRegistroCompleto() {
        String registro = "";
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);

            for (int i = 1; i < recordStore.getNextRecordID(); i++) {
                registro += new String(recordStore.getRecord(i));
            }
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return registro;
    }

    /**
     * Regresa el dato de un registro como String
     * @param index registro
     * @return valor del registro como string
     */
    public String regresarDato(int index) {
        String dato = "";
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            if (index > 0 && index <= recordStore.getNumRecords()) {
                dato = new String(recordStore.getRecord(index));
            } else {
                dato = AdministradorData.SVacio;
            }
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return dato;
    }

    /**
     * regresa el dato de un registro como int
     * @param index registro
     * @return valor del registro como int
     */
    public int regresarValorDato(int index) {
        int valor = VALOR_ERROR;
        try {
            recordStore = RecordStore.openRecordStore(this.nombreStore, false);
            if (index > 0 && index <= recordStore.getNumRecords()) {
                valor = bytesToInt( recordStore.getRecord(index) );
            }
            
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return valor;
    }

    /**
     * borra todo lo de un store
     */
    public void borrarTodo() {
        try {
            RecordStore.deleteRecordStore(nombreStore);

            recordStore = RecordStore.openRecordStore(this.nombreStore, true);
            recordStore.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    private byte[] intToBytes(int entero) {
        byte bytes[] = new byte[4];
        for (int i = 0; i < 3; i++) {
            bytes[i] = (byte) entero;
            entero >>= 8;
        }
        bytes[3] = (byte) entero;

        return bytes;
    }

    private int bytesToInt(byte[] bytes) {
        int entero = 0;
        for (int i = 3; i > 0; i--) {
            entero += bytes[i] & 0xFF;
            entero <<= 8;
        }
        entero += bytes[0] & 0xFF;
        return entero;
    }
}