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
public class StructSalto extends Struct
{
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        return "\t<br>\n";
    }
}
