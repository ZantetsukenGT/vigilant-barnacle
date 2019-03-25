/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tree;

import java.util.LinkedList;
import javax.swing.JTextArea;
import uweb.language.Contexto;

/**
 *
 * @author ozmarescobar
 */
public class StructFila extends Struct
{
    public LinkedList<Instruccion> instrucciones;
    
    public StructFila()
    {
        this.instrucciones = new LinkedList<>();
    }
    
    public StructFila(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t\t<tr>\n";
        for (Instruccion i : instrucciones)
        {
            Struct st = (Struct)i;
            if(st.tipo == Tipo.COLUMNA || st.tipo == Tipo.COLUMNAC)
            {
                Object res = i.ejecutar(ctx, consola);
                if(res != null)
                {
                    text += res.toString();
                }
            }
            else
            {
                consola.setText(consola.getText() + "Error, etiqueta: '" + st.tipo + "' no permitida dentro de etiqueta '" + tipo + "'\n");
            }
        }
        text += "\t\t</tr>\n";
        return text;
    }
}
