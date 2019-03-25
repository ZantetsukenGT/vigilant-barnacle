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
public class Imagen implements Instruccion
{
    public String path;
    public Integer alto;
    public Integer ancho;

    LinkedList<Operacion> params;
    
    public Imagen(LinkedList<Operacion> params)
    {
        this.params = params;
        this.alto = null;
        this.ancho = null;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        boolean f1,f2,f3;
        f1 = f2 = f3 = false;
        if(params.size() < 1)
        {
            consola.setText(consola.getText() + "Error 'CREARIMAGEN', faltan parametros\n");
        }
        else if(params.size() > 3)
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
                    path = resOp.toString();
                }
                else if(!f2 && resOp instanceof Double)
                {
                    f2 = true;
                    alto = ((Double)resOp).intValue();
                }
                else if(!f2 && resOp instanceof Integer)
                {
                    f2 = true;
                    alto = ((Integer)resOp).intValue();
                }
                else if(!f3 && resOp instanceof Double)
                {
                    f3 = true;
                    ancho = ((Double)resOp).intValue();
                }
                else if(!f3 && resOp instanceof Integer)
                {
                    f3 = true;
                    ancho = ((Integer)resOp).intValue();
                }
                else
                {
                    consola.setText(consola.getText() + "Error 'CREARIMAGEN', parametros del tipo incorrecto\n");
                }
            }
            if(!f1)
            {
                consola.setText(consola.getText() + "Error 'CREARIMAGEN', falta parametro obligatorio\n");
                return null;
            }
            return this;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String text = "\t<img src=\"";
        text += path + "\"";
        if(ancho != null)
        {
            text += " width=\"" + ancho.toString() + "\"";
        }
        if(alto != null)
        {
            text += " height=\"" + alto.toString() + "\">\n";
        }
        return text;
    }
    
}
