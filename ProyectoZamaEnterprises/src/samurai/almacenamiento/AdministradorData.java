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

    private RecordStore rs;
    private String nombreData;
    public static final String SVacio = "vacio";
    public static final int REGISTRO_SCORE=1;
    public static final int REGISTRO_VIDA=2;
    public static final int REGISTRO_NIVEL=3;
    public static final int REGISTRO_TIEMPO=4;

    public AdministradorData(String nombreData){
        this.nombreData = nombreData;
        try {
            rs = RecordStore.openRecordStore(this.nombreData, true);
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
    public void cambiarTabla(String nombreData){
        this.nombreData = nombreData;
         try {
            rs = RecordStore.openRecordStore(this.nombreData, true);
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }

    public void agregarRegistro(String registro){
        try{
        rs = RecordStore.openRecordStore(this.nombreData, false);
         byte data[] = registro.getBytes();
         rs.addRecord(data, 0, data.length);
          rs.closeRecordStore();
            
      } catch (RecordStoreException e) {
          e.printStackTrace();
      } 
    }
    public void agregarRegistro(int registro){
        try{
        rs = RecordStore.openRecordStore(this.nombreData, false);
         byte data[] = {(byte)registro, (byte)20};
         rs.addRecord(data, 0, data.length);
          rs.closeRecordStore();

      } catch (RecordStoreException e) {
          e.printStackTrace();
      }
    }
      public void cambiarRegistro(String registro, int index){
        try{
        rs = RecordStore.openRecordStore(this.nombreData, false);
         byte data[] = registro.getBytes();
         rs.addRecord(data, 0, data.length);
         rs.setRecord(index, data, 0, data.length);
         rs.closeRecordStore();

      } catch (RecordStoreException e) {
          e.printStackTrace();
      } 
    }

    public String regresarRegistroCompleto(){
        String registro = "";
        try {

            rs = RecordStore.openRecordStore(this.nombreData, false);

            for (int i = 1; i < rs.getNextRecordID(); i++) {
                registro += new String(rs.getRecord(i));
            }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        } 
        return registro;
    }
    public String regresarDato(int index){
        String dato = "";
        try {
            rs = RecordStore.openRecordStore(this.nombreData, false);
            if(index>0 && index<=rs.getNumRecords()){
            dato = new String(rs.getRecord(index));
            }
             else{
             dato = AdministradorData.SVacio;
             }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return dato;
    }

    public int regresarValorDato(int index){
        int dato=-1;
       try {
            rs = RecordStore.openRecordStore(this.nombreData, false);
            if(index>0 && index<=rs.getNumRecords()){
            dato = (int)(rs.getRecord(index)[0]);
            }
             else{
             dato = -1;
             }
            rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
        return dato;
    }

    public void borrarTodo(){
        try {
           RecordStore.deleteRecordStore(nombreData);
           rs = RecordStore.openRecordStore(nombreData, true);
           rs.closeRecordStore();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }
    }
}