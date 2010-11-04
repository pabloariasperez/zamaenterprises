/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package samurai.escenarios;

/**
 *Clase que almacena valores estándar del número e.
 * @author Pablo
 */
public class E {
    public static int xp( int parametro ){
        switch(parametro){
            case 0:return 64>>6;
            case 1:return 78>>6;
            case 2:return 95>>6;
            case 3:return 117>>6;
            case 4:return 142>>6;
            case 5:return 174>>6;
            case 6:return 212>>6;
            case 7:return 260>>6;
            case 8:return 317>>6;
            case 9:return 387>>6;
            case 10:return 473>>6;
            case 11:return 578>>6;
            case 12:return 705>>6;
            case 13:return 862>>6;
            case 14:return 1052>>6;
            case 15:return 1285>>6;
            case 16:return 1570>>6;
            case 17:return 1918>>6;
            case 18:return 2342>>6;
            case 19:return 2861>>6;
            case 20:return 3494>>6;
            default:return 0;
        }
    }
}
