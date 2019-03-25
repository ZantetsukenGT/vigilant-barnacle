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
public class StructColumnaC extends Struct
{
    public LinkedList<Instruccion> instrucciones;
    
    public StructColumnaC()
    {
        this.instrucciones = new LinkedList<>();
    }
    
    public StructColumnaC(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t\t\t<th>\n";
        for (Instruccion i : instrucciones)
        {
            Struct st = (Struct)i;
            if(st.tipo == Tipo.SALTO || st.tipo == Tipo.PARRAFO
                    || st.tipo == Tipo.IMAGEN || st.tipo == Tipo.BOTON
                    || st.tipo == Tipo.HSCRIPT || st.tipo == Tipo.TEXTOLIBRE)
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
        text += "\t\t\t</th>\n";
        return text;
    }
}
