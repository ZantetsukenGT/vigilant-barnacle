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
public class CodigoHS extends Struct
{
    LinkedList<Instruccion> instrucciones;
    
    public CodigoHS()
    {
        instrucciones = new LinkedList();
    }
    
    public CodigoHS(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
    }
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "";
        for (Instruccion i : instrucciones)
        {
            Object res = i.ejecutar(ctx, consola);
            if(res != null)
            {
                text += res.toString();
            }
        }
        return text;
    }
    
}
