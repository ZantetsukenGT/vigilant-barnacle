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
public class If implements Instruccion
{
    Operacion cond;
    LinkedList<Instruccion> cuerpoIf;
    LinkedList<Instruccion> cuerpoElse;
    
    public If(Operacion cond, LinkedList<Instruccion> cuerpoIf)
    {
        this.cond = cond;
        this.cuerpoIf = cuerpoIf;
        this.cuerpoElse = null;
    }
    
    public If(Operacion cond, LinkedList<Instruccion> cuerpoIf, LinkedList<Instruccion> cuerpoElse)
    {
        this.cond = cond;
        this.cuerpoIf = cuerpoIf;
        this.cuerpoElse = cuerpoElse;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "";
        Object resCond = cond.ejecutar(ctx, consola);
        if(resCond instanceof Boolean)
        {
            if((Boolean)resCond)
            {
                if(cuerpoIf != null)
                {
                    for (Instruccion i : cuerpoIf)
                    {
                        Object resIns = i.ejecutar(ctx, consola);
                        if(resIns != null)
                        {
                            text += resIns.toString();
                        }
                    } 
                }
            }
            else
            {
                if(cuerpoElse != null)
                {
                    for (Instruccion i : cuerpoElse)
                    {
                        Object resIns = i.ejecutar(ctx, consola);
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
            consola.setText(consola.getText() + "Error 'IF', condición no es un booleano\n");
        }
        if(!text.isEmpty())
        {
            return text;
        }
        return null;
    }
}
