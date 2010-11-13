/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.almacenamiento;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

/**
 *
 * @author mi16
 */
public class AdministradorData {

    RecordStore rs;
    String nombreData;

    public AdministradorData(String nombreData){
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
         byte data[] = this.convertirAByte(registro);
         rs.addRecord(data, 0, data.length);

            
      } catch (Exception e) {
      } finally {
            try {
                rs.closeRecordStore();
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
            }
      }
    }
      public void cambiarRegistro(String registro, int index){
        try{
        rs = RecordStore.openRecordStore(this.nombreData, false);
         byte data[] = this.convertirAByte(registro);
         rs.addRecord(data, 0, data.length);
         rs.setRecord(index, data, 0, data.length);


      } catch (Exception e) {
      } finally {
            try {
                rs.closeRecordStore();
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
            }
      }
    }

    private byte[] convertirAByte(String string){
        byte data[]= new byte[string.length()];
        for(int i=0; i<string.length(); i++){
            data[i] = (byte) (int)string.charAt(i);
        }
        return data;

    }

    public String regresarRegistroCompleto(){
        String registro = "";
        try {

            rs = RecordStore.openRecordStore(this.nombreData, false);

            for (int i = 1; i < rs.getNextRecordID(); i++) {
                registro += new String(rs.getRecord(i));
            }
        } catch (RecordStoreNotOpenException ex) {
            ex.printStackTrace();
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }finally{
            try {
                rs.closeRecordStore();
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
            }
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
             dato = "vacio";
             }
        } catch (RecordStoreException ex) {
            ex.printStackTrace();
        }finally{
            try {
                rs.closeRecordStore();
            } catch (RecordStoreNotOpenException ex) {
                ex.printStackTrace();
            } catch (RecordStoreException ex) {
                ex.printStackTrace();
            }
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

