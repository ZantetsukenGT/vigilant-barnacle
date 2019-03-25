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
public class Tabla implements Instruccion
{
    public Boolean borde;
    
    LinkedList<LinkedList<String>> filas;

    LinkedList<LinkedList<Operacion>> params;
    
    public Tabla(LinkedList<LinkedList<Operacion>> params)
    {
        this.params = params;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        filas = new LinkedList<>();
        if(params.size() < 1)
        {
            consola.setText(consola.getText() + "Error 'CREARTABLA', faltan parametros\n");
        }
        else
        {
            for (int i = 0; i < params.size(); i++)
            {
                LinkedList<String> fila = new LinkedList<>();
                LinkedList<Operacion> arreglo = params.get(i);
                for (int j = 0; j < arreglo.size(); j++)
                {
                    Object resOp = arreglo.get(j).ejecutar(ctx, consola);
                    fila.add(resOp.toString());
                }
                filas.add(fila);
            }
            return this;
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        String text = "\t<table";
        if(borde != null)
        {
            text += " border=\"";
            text += (borde)?"1\"":"0\"";
        }
        text += ">\n";
        
        for (int i = 0; i < filas.size(); i++)
        {
            LinkedList<String> fila = filas.get(i);
            text += "\t\t<tr>\n";
            if(i == 0)
            {
                for (int j = 0; j < fila.size(); j++)
                {  
                    text += "\t\t\t<th>\n";
                    text += "\t\t\t\t" + fila.get(j) + "\n";
                    text += "\t\t\t</th>\n";
                }
            }
            else
            {
                for (int j = 0; j < fila.size(); j++)
                {  
                    text += "\t\t\t<td>\n";
                    text += "\t\t\t\t" + fila.get(j) + "\n";
                    text += "\t\t\t</td>\n";
                }
            }
            text += "\t\t</tr>\n";
        }
        
        text += "\t</table>\n";
        return text;
    }
}
