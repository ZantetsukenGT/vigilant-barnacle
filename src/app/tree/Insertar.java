/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tree;

import javax.swing.JTextArea;
import uweb.language.Contexto;
import uweb.language.Simbolo;

/**
 *
 * @author ozmarescobar
 */
public class Insertar implements Instruccion
{
    String id;
    public Insertar(String id)
    {
        this.id = id;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        Simbolo s = ctx.findSymbol(id);
        if(s != null)
        {
            String text = "";
            if(s.value instanceof Parrafo)
            {
                Parrafo p = (Parrafo)s.value;
                text = p.toString();
            }
            else if(s.value instanceof TextoA)
            {
                TextoA ta = (TextoA)s.value;
                text = ta.toString();
            }
            else if(s.value instanceof TextoB)
            {
                TextoB tb = (TextoB)s.value;
                text = tb.toString();
            }
            else if(s.value instanceof Imagen)
            {
                Imagen im = (Imagen)s.value;
                text = im.toString();
            }
            else if(s.value instanceof Tabla)
            {
                Tabla t = (Tabla)s.value;
                text = t.toString();
            }
            else if(s.value instanceof Boton)
            {
                Boton b = (Boton)s.value;
                text = b.toString();
            }
            return text;
        }
        consola.setText(consola.getText() + "Error 'INSERTAR', el elemento HTML no existe\n");
        return null;
    }
    
}
