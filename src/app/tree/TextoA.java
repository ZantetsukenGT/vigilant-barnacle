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
public class TextoA implements Instruccion
{
    public String contenido;

    LinkedList<Operacion> params;
    
    public TextoA(LinkedList<Operacion> params)
    {
        this.params = params;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        boolean f1 = false;
        if(params.size() < 1)
        {
            consola.setText(consola.getText() + "Error 'CREAR TEXTOA', faltan parametros\n");
        }
        else if(params.size() > 1)
        {
            consola.setText(consola.getText() + "Error 'CREAR TEXTOA', sobran parametros\n");
        }
        else
        {
            for (Operacion op : params)
            {
                Object resOp = op.ejecutar(ctx, consola);
                if(resOp instanceof String)
                {
                    f1 = true;
                    contenido = resOp.toString();
                }
                else
                {
                    consola.setText(consola.getText() + "Error 'CREAR TEXTOA', parametro del tipo no correspondiente\n");
                }
            }
            if(!f1)
            {
                consola.setText(consola.getText() + "Error 'CREAR TEXTOA', falta parametro obligatorio\n");
                return null;
            }
            return this;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String text = "\t<h1>\n";
        if(contenido != null)
        {
            text += "\t\t" + contenido + "\n";
        }
        text+="\t</h1>\n";
        return text;
    }
    
}
