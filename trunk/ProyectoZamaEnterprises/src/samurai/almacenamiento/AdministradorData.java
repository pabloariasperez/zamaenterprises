/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.almacenamiento;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 * @author mi16
 */
public class AdministradorData {

    private RecordStore recordStore;
    private String nombreStore;
    public static final String SVacio = "vacio";
    public static final int REGISTRO_SCORE_ACTUAL = 1;
    public static final int REGISTRO_VIDA = 2;
    public static final int REGISTRO_NIVEL = 3;
    public static final int REGISTRO_TIEMPO = 4;

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

    public int regresarValorDato(int index) {
        int valor = -1;
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

    public void borrarTodo() {
        try {
            RecordStore.deleteRecordStore(nombreStore);
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