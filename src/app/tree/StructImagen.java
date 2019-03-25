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
public class StructImagen extends Struct
{
    public String path;
    public Integer alto;
    public Integer ancho;
    public LinkedList<Atributo> atributos;
    
    public StructImagen()
    {
        this.atributos = new LinkedList<>();
    }
    
    public StructImagen(LinkedList<Atributo> atributos)
    {
        this.atributos = atributos;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<img src=\"";
        if(atributos.size() < 1)
        {
            consola.setText(consola.getText() + "Error imagen, faltan parametros\n");
            return null;
        }
        else if(atributos.size() > 3)
        {
            consola.setText(consola.getText() + "Error imagen, sobran parametros\n");
            return null;
        }
        boolean f1,f2,f3;
        f1 = f2 = f3 = false;
        for (Atributo atr : atributos)
        {
            Object resAtr = atr.ejecutar(ctx, consola);
            if(resAtr != null)
            {
                if(atr.tipo == Atributo.Tipo.PATH)
                {
                    if(resAtr instanceof String)
                    {
                        f1 = true;
                        path = resAtr.toString();
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error imagen, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
                else if(atr.tipo == Atributo.Tipo.ALTO)
                {
                    if(resAtr instanceof Integer)
                    {
                        f2 = true;
                        alto = (Integer)resAtr;
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error imagen, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
                else if(atr.tipo == Atributo.Tipo.ANCHO)
                {
                    if(resAtr instanceof Integer)
                    {
                        f3 = true;
                        ancho = (Integer)resAtr;
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error imagen, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
            }
        }
        if(!f1)
        {
            consola.setText(consola.getText() + "Error imagen, falta atributo obligatorio 'path'\n");
            return null;
        }
        text += path + "\"";
        if(f2)
        {
            text += " height=\"" + alto.intValue() + "\"";
        }
        if(f3)
        {
            text += " width=\"" + ancho.intValue() + "\"";
        }
        text += ">\n";
        return text;
    }
}
