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
public class Boton implements Instruccion
{
    public String id;
    public String textoLabel;
    public String textoOnClick;
    

    LinkedList<Operacion> params;
    
    public Boton(LinkedList<Operacion> params)
    {
        
        this.params = params;
        textoOnClick = null;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        boolean f1,f2;
        f1 = f2 = false;
        if(params.size() < 2)
        {
            consola.setText(consola.getText() + "Error 'CREARBOTON', faltan parametros\n");
        }
        else if(params.size() > 2)
        {
            consola.setText(consola.getText() + "Error 'CREARBOTON', sobran parametros\n");
        }
        else
        {
            for (Operacion op : params)
            {
                Object resOp = op.ejecutar(ctx, consola);
                if(!f1 && resOp instanceof String)
                {
                    f1 = true;
                    id = resOp.toString();
                }
                else if(!f2 && resOp instanceof String)
                {
                    f2 = true;
                    textoLabel = resOp.toString();
                }
                else
                {
                    consola.setText(consola.getText() + "Error 'CREARBOTON', parametro del tipo incorrecto\n");
                }
            }
            if(!f1 || !f2)
            {
                consola.setText(consola.getText() + "Error 'CREARBOTON', faltan parametros obligaroios\n");
                return null;
            }
            return this;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String text = "\t<button";
        if(textoOnClick != null)
        {
            text += " onclick=\"onClick_" + id + "()\"";
        }
        text += ">" + textoLabel + "</button>\n";
        if(textoOnClick != null)
        {
            text += "\t<script> function onClick_" + id + "(){alert(\"" + textoOnClick + "\");}</script>\n";
        }
        return text;
    }
    
}
