/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tree;

import javax.swing.JTextArea;
import uweb.language.Contexto;

/**
 *
 * @author ozmarescobar
 */
public class Echo implements Instruccion
{
    Operacion op;
    
    public Echo(Operacion op)
    {
        this.op = op;
    }
    
    @Override
    public Object ejecutar(Contexto contextoPadre, JTextArea consola)
    {
        Object txt = op.ejecutar(contextoPadre, consola);
        if(txt != null)
            consola.setText(consola.getText() + txt + "\n");
        return null;
    }

}
