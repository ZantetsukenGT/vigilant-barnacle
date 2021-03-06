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
public class StructTextoA extends Struct
{
    public LinkedList<Instruccion> instrucciones;
    
    public StructTextoA()
    {
        this.instrucciones = new LinkedList<>();
    }
    
    public StructTextoA(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<h1>\n";
        for (Instruccion i : instrucciones)
        {
            Struct st = (Struct)i;
            if(st.tipo == Struct.Tipo.TEXTOLIBRE)
            {
                Object res = i.ejecutar(ctx, consola);
                if(res != null)
                {
                    text += "\t\t" + res.toString() + "\n";
                }
            }
            else
            {
                consola.setText(consola.getText() + "Error, etiqueta: '" + st.tipo + "' no permitida dentro de etiqueta '" + tipo + "'\n");
            }
        }
        text += "\t</h1>\n";
        return text;
    }
}
