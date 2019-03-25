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
public abstract class Struct implements Instruccion
{
    public static enum Tipo
    {
        COMPI,
        CABECERA,
        TITULO,
        CUERPO,
        BOTON,
        ESPACIO,
        IMAGEN,
        PARRAFO,
        SALTO,
        TEXTOA,
        TEXTOB,
        HSCRIPT,
        TABLA,
        FILA,
        COLUMNAC,
        COLUMNA,
        TEXTOLIBRE
    }
    public Tipo tipo;
    
    public void setTipo(Tipo tipo)
    {
        this.tipo = tipo;
    }
}
