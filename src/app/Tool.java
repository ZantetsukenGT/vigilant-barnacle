package app;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ozmarescobar
 */
public class Tool
{
    private Tool()
    {
    }
    
    public static File crearArchivo(String texto, String ruta) throws IOException
    {
        if(ruta.length() > 0)
        {
            if(ruta.charAt(ruta.length() - 1) != '/')
            {
                File file = new File(ruta);
                File parent = file.getParentFile();
                if(parent != null)
                {
                    parent.mkdirs();
                }
                if(!file.exists())
                {
                    file.createNewFile();
                }
                if(file.isFile())
                {
                    PrintWriter pw = new PrintWriter(file);
                    pw.print(texto);
                    pw.close();
                }
                return file;
            }
        }
        return null;
    }
    public static File appendText(String texto, String ruta) throws IOException
    {
        if(ruta.length() > 0)
        {
            if(ruta.charAt(ruta.length() - 1) != '/')
            {
                File file = new File(ruta);
                File parent = file.getParentFile();
                if(parent != null)
                {
                    parent.mkdirs();
                }
                if(!file.exists())
                {
                    file.createNewFile();
                }
                if(file.isFile())
                {
                    PrintWriter pw = new PrintWriter(new FileOutputStream(file, true));
                    pw.print(texto);
                    pw.close();
                }
                return file;
            }
        }
        return null;
    }
    
    public static File crearDirectorio(String ruta) throws IOException
    {
        if(ruta.length() > 0)
        {
            File file = new File(ruta);
            if(!file.exists())
            {
                file.mkdirs();
            }
            return file;
        }
        return null;
    }
}
