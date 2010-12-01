/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package samurai.almacenamiento;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author Pablo
 */
public class AlmacenamientoServidor {

    private static final String PAGINA = "http://samurai.gevcomcenters.com.mx/setNewScore.php";
    private static final String VAR_COMPROBANTE = "comprobante=midoriSekai10";
    private static final String VAR_INICIALES = "&iniciales=";
    private static final String VAR_PUNTAJE = "&puntaje=";

    /**
     *
     * @param iniciales
     * @param puntaje
     * @throws IOException
     */
    public static void subirPuntaje(String iniciales, int puntaje) throws IOException {
        HttpConnection conexion = null;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        int responseCode;           //Código de respuesta del servidor.

        try {
            // Abrimos la página a la que mandaremos la petición.
            conexion = (HttpConnection) Connector.open(PAGINA);

            // Establecemos el método de envío.
            conexion.setRequestMethod(HttpConnection.POST);
            //Identificamos nuestro papel de Agente de Usuario
            conexion.setRequestProperty("User-Agent",
                "Profile/MIDP-2.0 Configuration/CLDC-1.0");


            //conexion.setRequestProperty("Content-Language", "en-US");

            //The mime type of the body of the request (used with POST and PUT requests)
            conexion.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // Getting the output stream may flush the headers
            outputStream = conexion.openOutputStream();

            String parametros = VAR_COMPROBANTE + VAR_INICIALES+iniciales+ VAR_PUNTAJE + puntaje;
            outputStream.write(parametros.getBytes());
//            os.flush();           // Optional, getResponseCode will flush

            // Getting the response code will open the connection,
            // send the request, and read the HTTP response headers.
            // The headers are stored until requested.
            responseCode = conexion.getResponseCode();
            if (responseCode != HttpConnection.HTTP_OK) {
                throw new IOException("HTTP response code: " + responseCode);
            }

            inputStream = conexion.openInputStream();

            //http://en.wikipedia.org/wiki/List_of_HTTP_header_fields
            // Get the ContentType
//            String type = c.getType();
//            processType(type);

            // Get the length and process the data
            int len = (int) conexion.getLength();
            if (len > 0) {
                int actual = 0;
                int bytesread = 0;
                byte[] data = new byte[len];
                while ((bytesread != len) && (actual != -1)) {
                    actual = inputStream.read(data, bytesread, len - bytesread);
                    bytesread += actual;
                }
            } else {
                int ch;
                while ((ch = inputStream.read()) != -1) {
                    System.out.println((byte) ch);
                }
            }
        } catch (ClassCastException e) {
            throw new IllegalArgumentException("Not an HTTP URL");
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            if (conexion != null) {
                conexion.close();
            }
        }
    }
}
