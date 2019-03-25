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
public class StructCuerpo extends Struct
{
    public String fondo;
    public LinkedList<Instruccion> instrucciones;
    public LinkedList<Atributo> atributos;
    
    public StructCuerpo()
    {
        this.instrucciones = new LinkedList<>();
        this.atributos = new LinkedList<>();
    }
    
    public StructCuerpo(LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
        this.atributos = new LinkedList<>();
    }
    
    public StructCuerpo(LinkedList<Atributo> atributos, LinkedList<Instruccion> instrucciones)
    {
        this.instrucciones = instrucciones;
        this.atributos = atributos;
    }

    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        String text = "\t<body";
        if(atributos.size() > 1)
        {
            consola.setText(consola.getText() + "Error cuerpo, demasiados parametros\n");
            return null;
        }
        for (Atributo atr : atributos)
        {
            Object resAtr = atr.ejecutar(ctx, consola);
            if(resAtr != null)
            {
                if(atr.tipo == Atributo.Tipo.FONDO)
                {
                    if(resAtr instanceof String)
                    {
                        fondo = resAtr.toString();
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error cuerpo, atributo '" + atr.tipo + "' no es del tipo correcto\n");
                    }
                }
            }
        }
        if(fondo != null)
        {
            text += " bgcolor=\"";
            switch(fondo.toLowerCase())
            {
                case "rojo":
                    text += "red";
                    break;
                case "azul":
                    text += "blue";
                    break;
                case "verde":
                    text += "green";
                    break;
                case "gris":
                    text += "gray";
                    break;
                case "negro":
                    text += "black";
                    break;
                case "amarillo":
                    text += "yellow";
                    break;
                case "rosado":
                    text += "pink";
                    break;
                default:
                    boolean errorSemantico = !(fondo.length() == 7 && fondo.charAt(0) == 35);
                    for (int i = 1;!errorSemantico && i < fondo.length(); i++)
                    {
                        char c = fondo.charAt(i);
                        if(!(c >= 48 && c <= 57||c >= 65 && c <= 70||c >= 97 && c <= 102))
                        {
                            errorSemantico = true;
                        }
                    }
                    if(errorSemantico)   
                    {
                        consola.setText(consola.getText() + "Error, literal de color no valida: '" + fondo + "'\n");
                        return null;
                    }
                    text += fondo;
            }
            text += "\"";
        }
        text += ">\n";
        if(instrucciones != null)
        {
            for (Instruccion i : instrucciones)
            {
                Struct st = (Struct)i;
                if(st.tipo == Tipo.PARRAFO || st.tipo == Tipo.SALTO
                        || st.tipo == Tipo.TABLA || st.tipo == Tipo.IMAGEN
                        || st.tipo == Tipo.TEXTOA || st.tipo == Tipo.TEXTOB
                        || st.tipo == Tipo.BOTON || st.tipo == Tipo.ESPACIO
                        || st.tipo == Tipo.HSCRIPT || st.tipo == Tipo.TEXTOLIBRE)
                {
                    Object res = i.ejecutar(ctx, consola);
                    if(res != null)
                    {
                        text += res.toString();
                    }
                }
                else
                {
                    consola.setText(consola.getText() + "Error, etiqueta: '" + st.tipo + "' no permitida dentro de etiqueta '" + tipo + "'\n");
                }
            }
        }
        text += "\t</body>\n";
        return text;
    }
}
