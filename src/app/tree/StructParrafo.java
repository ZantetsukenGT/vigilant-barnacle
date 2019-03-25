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
public class StructParrafo extends Struct
{
    public String alineacion;
    public LinkedList<Atributo> atributos;
    public LinkedList<Instruccion> instrucciones;
    
    public StructParrafo()
    {
        this.instrucciones = new LinkedList<>();
        this.atributos = new LinkedList<>();
    }
    
    public StructParrafo(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
        this.atributos = new LinkedList<>();
    }
    
    public StructParrafo(LinkedList<Atributo> atributos, LinkedList<Instruccion> instrucciones)
    {
        this.atributos = atributos;
        this.instrucciones = instrucciones;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<p";
        if(atributos.size() > 1)
        {
            consola.setText(consola.getText() + "Error parrafo, demasiados parametros\n");
            return null;
        }
        for (Atributo atr : atributos)
        {
            Object resAtr = atr.ejecutar(ctx, consola);
            if(resAtr != null)
            {
                if(atr.tipo == Atributo.Tipo.ALINEACION)
                {
                    if(resAtr instanceof String)
                    {
                        alineacion = resAtr.toString();
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error parrafo, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
            }
        }
        if(alineacion != null)
        {
            text += " align=\"";
            switch(alineacion.toLowerCase())
            {
                case "izquierda":
                    text += "left";
                    break;
                case "derecha":
                    text += "right";
                    break;
                case "centrado":
                    text += "center";
                    break;
                case "justificado":
                    text += "justify";
                    break;
                default:
                    consola.setText(consola.getText() + "Error parrafo, alineacion no valida: '" + alineacion + "'\n");
            }
            text += "\"";
        }
        text += ">\n";
        if(instrucciones != null)
        {
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
        }
        text += "\t</p>\n";
        return text;
    }
}
