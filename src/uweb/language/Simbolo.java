/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uweb.language;

import java.util.LinkedList;

/**
 *
 * @author ozmarescobar
 */
public class Simbolo {

    public static enum Tipo
    {
        //HS
        ENTERO,
        DECIMAL,
        CADENA,
        BOOLEANO,
        //HTML
        PARRAFO,
        TEXTOA,
        TEXTOB,
        IMAGEN,
        TABLA,
        BOTON
        
    }
    
    public final Tipo tipo;
    public String id;
    public Object value;

    public Simbolo(Tipo tipo, String id, Object value) {
        this.tipo = tipo;
        this.id = id;
        this.value = value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString()
    {
        String text = "<Declaracion Tipo: " + tipo.toString() + ", Id: " + id + " valor: " + value.toString() + ">";
        return text;
    }
}
