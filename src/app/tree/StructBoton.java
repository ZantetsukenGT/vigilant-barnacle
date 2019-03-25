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
public class StructBoton extends Struct
{
    public String id;
    public String texto;
    public LinkedList<Atributo> atributos;
    
    public StructBoton()
    {
        this.atributos = new LinkedList<>();
    }
    
    public StructBoton(LinkedList<Atributo> params)
    {
        this.atributos = params;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<button id=\"";
        if(atributos.size() < 2)
        {
            consola.setText(consola.getText() + "Error botOn, faltan parametros\n");
            return null;
        }
        else if(atributos.size() > 2)
        {
            consola.setText(consola.getText() + "Error botOn, sobran parametros\n");
            return null;
        }
        boolean f1,f2;
        f1 = f2 = false;
        for (Atributo atr : atributos)
        {
            Object resAtr = atr.ejecutar(ctx, consola);
            if(resAtr != null)
            {
                if(atr.tipo == Atributo.Tipo.ID)
                {
                    if(resAtr instanceof String)
                    {
                        f1 = true;
                        id = resAtr.toString();
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error boton, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
                else if(atr.tipo == Atributo.Tipo.TEXTO)
                {
                    if(resAtr instanceof String)
                    {
                        f2 = true;
                        texto = resAtr.toString();
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error boton, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
            }
        }
        if(!f1)
        {
            consola.setText(consola.getText() + "Error boton, falta atributo obligatorio 'id'\n");
            return null;
        }
        if(!f2)
        {
            consola.setText(consola.getText() + "Error boton, falta atributo obligatorio 'texto'\n");
            return null;
        }
        text += id + "\">\n";
        text += "\t\t" + texto + "\n";
        text += "\t</button>\n";
        return text;
    }
}
