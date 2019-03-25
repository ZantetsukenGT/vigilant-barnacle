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
public class StructCabecera extends Struct 
{
    public LinkedList<Instruccion> instrucciones;
    
    public StructCabecera()
    {
        this.instrucciones = new LinkedList<>();
    }
    
    public StructCabecera(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<head>\n"
                + "\t\t<meta charset=\"UTF-8\">\n";
        for (Instruccion i : instrucciones)
        {
            Struct st = (Struct)i;
            if(st.tipo == Tipo.TITULO)
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
        text += "\t</head>\n";
        return text;
    }
}
