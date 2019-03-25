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
public class Repetir implements Instruccion
{
    Operacion param;
    LinkedList<Instruccion> cuerpo;

    public Repetir(Operacion param, LinkedList<Instruccion> cuerpo)
    {
        this.param = param;
        this.cuerpo = cuerpo;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "";
        Object resParam = param.ejecutar(ctx, consola);
        if(resParam instanceof Double)
        {
            for(int i = ((Double)resParam).intValue(); i > 0; i--)
            {
                if(cuerpo != null)
                {
                    for (Instruccion ins : cuerpo)
                    {
                        Object resIns = ins.ejecutar(ctx, consola);
                        if(resIns != null)
                        {
                            text += resIns.toString();
                        }
                    }
                }
            }
        }
        else if(resParam instanceof Integer)
        {
            for(int i = (Integer)resParam; i > 0; i--)
            {
                if(cuerpo != null)
                {
                    for (Instruccion ins : cuerpo)
                    {
                        Object resIns = ins.ejecutar(ctx, consola);
                        if(resIns != null)
                        {
                            text += resIns.toString();
                        }
                    }
                }
            }
        }
        else
        {
            consola.setText(consola.getText() + "Error REPETIR: parametro no es un numero\n");
        }
        if(!text.isEmpty())
        {
            return text;
        }
        return null;
    }
    
}
