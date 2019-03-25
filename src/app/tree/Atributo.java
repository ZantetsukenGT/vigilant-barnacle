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
public class Atributo implements Instruccion
{
    public static enum Tipo
    {
        //tipos de dato 
        FONDO,
        ALINEACION,
        PATH,
        ALTO,
        ANCHO,
        ID,
        TEXTO,
        BORDE
    }
    
    Object value;
    Tipo tipo;
    
    public Atributo(Object value, Tipo tipo)
    {
        this.value = value;
        this.tipo = tipo;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        return value;
    }
}
