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
public class StructTabla extends Struct
{
    public Boolean borde;
    public LinkedList<Instruccion> instrucciones;
    public LinkedList<Atributo> atributos;
    
    public StructTabla()
    {
        this.instrucciones = new LinkedList<>();
        this.atributos = new LinkedList<>();
    }
    
    public StructTabla(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
        this.atributos = new LinkedList<>();
    }
    
    public StructTabla(LinkedList<Atributo> atributos, LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
        this.atributos = atributos;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<table";
        if(atributos.size() > 1)
        {
            consola.setText(consola.getText() + "Error tabla, sobran parametros\n");
            return null;
        }
        for (Atributo atr : atributos)
        {
            Object resAtr = atr.ejecutar(ctx, consola);
            if(resAtr != null)
            {
                if(atr.tipo == Atributo.Tipo.BORDE)
                {
                    if(resAtr instanceof Boolean)
                    {
                        borde = (Boolean)resAtr;
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error tabla, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
            }
        }
        if(borde != null)
        {
            text += " border=\"" + ((borde)?"1":"0") + "\"";
        }
        text += ">\n";
        if(instrucciones != null)
        {
            for (Instruccion i : instrucciones)
            {
                Struct st = (Struct)i;
                if(st.tipo == Tipo.FILA)
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
        }
        text += "\t</table>\n";
        return text;
    }
}
