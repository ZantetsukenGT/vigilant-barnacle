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
public class Parrafo implements Instruccion
{
    public String alineacion;
    public String contenido;

    LinkedList<Operacion> params;
    
    public Parrafo(LinkedList<Operacion> params)
    {
        this.params = params;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        boolean f1,f2;
        f1 = f2 = false;
        if(params.size() < 1)
        {
            consola.setText(consola.getText() + "Error 'CREARIMAGEN', faltan parametros\n");
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
                    contenido = resOp.toString();
                }
                else if(!f2 && resOp instanceof String)
                {
                    switch(resOp.toString().toLowerCase())
                    {
                        case "izquierda":
                            f2 = true;
                            alineacion = resOp.toString();
                            break;
                        case "derecha":
                            f2 = true;
                            alineacion = resOp.toString();
                            break;
                        case "centrado":
                            f2 = true;
                            alineacion = resOp.toString();
                            break;
                        case "justificado":
                            f2 = true;
                            alineacion = resOp.toString();
                            break;
                        default:
                            consola.setText(consola.getText() + "Error 'CREARPARRAFO', alineacion invalida\n");
                    }
                }
                else
                {
                    consola.setText(consola.getText() + "Error 'CREARPARRAFO', parametros del tipo incorrecto\n");
                }
            }
            if(!f1)
            {
                consola.setText(consola.getText() + "Error 'CREARPARRAFO', falta parametro obligatorio\n");
                return null;
            }
            return this;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String text = "\t<p";
        if(alineacion != null)
        {
            text += " align=\"";
            switch(alineacion.toLowerCase())
            {
                case "izquierda":
                    text += "left\"";
                    break;
                case "derecha":
                    text += "right\"";
                    break;
                case "centrado":
                    text += "center\"";
                    break;
                case "justificado":
                    text += "justify\"";
                    break;
            }
        }
        text += ">\n"
                + "\t\t" + contenido + "\n"
                + "\t</p>\n";
        return text;
    }
}
