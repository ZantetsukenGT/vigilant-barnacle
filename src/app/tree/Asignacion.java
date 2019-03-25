/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tree;

import javax.swing.JTextArea;
import uweb.language.Contexto;
import uweb.language.Simbolo;

/**
 *
 * @author ozmarescobar
 */
public class Asignacion implements Instruccion
{
    public static enum Tipo
    {
        SETPATH,
        SETALTO,
        SETANCHO,
        SETALINEACION,
        SETCONTENIDO,
        SETTEXTO,
        SETBORDE,
        CLICKBOTON
    }
    
    String id;
    Operacion value;
    Tipo tipo;//opcional, solo para SETs
    
    public Asignacion(String id, Operacion value)
    {
        this.id = id;
        this.value = value;
        this.tipo = null;
    }
    
    public Asignacion(String id, Operacion value, Tipo tipo)
    {
        this.id = id;
        this.value = value;
        this.tipo = tipo;
    }
    
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        Object v = value.ejecutar(ctx, consola);
        Simbolo s = ctx.findSymbol(id);
        boolean exists = (s != null);
        if(v != null)
        {
            if(tipo == null)//es una asignacion simple
            {
                if(v instanceof Double)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.ENTERO)
                        {
                            ctx.setValue(id, ((Double)v).intValue()); 
                        }
                        else if(s.tipo == Simbolo.Tipo.DECIMAL)
                        {
                            ctx.setValue(id, v); 
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un numero\n");
                        }   
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.DECIMAL, id, v));
                    }
                }
                else if(v instanceof Integer)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.ENTERO)
                        {
                            ctx.setValue(id, v);
                        }
                        else if(s.tipo == Simbolo.Tipo.DECIMAL)
                        {
                            ctx.setValue(id, ((Integer)v).doubleValue());
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un numero\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.ENTERO, id, Integer.valueOf(v.toString())));
                    }
                }
                else if(v instanceof Boolean)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.BOOLEANO)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un booleano\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.BOOLEANO, id, v));
                    }
                }
                else if(v instanceof String)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.CADENA)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es una cadena\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.CADENA, id, v));
                    }
                }
                else if(v instanceof Parrafo)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.PARRAFO)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un parrafo \n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.PARRAFO, id, v));
                    }
                }
                else if(v instanceof TextoA)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.TEXTOA)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un TextoA\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.TEXTOA, id, v));
                    }
                }
                else if(v instanceof TextoB)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.TEXTOB)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un TextoB\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.TEXTOB, id, v));
                    }
                }
                else if(v instanceof Imagen)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.IMAGEN)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es una imagen\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.IMAGEN, id, v));
                    }
                }
                else if(v instanceof Tabla)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.TABLA)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es una tabla\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.TABLA, id, v));
                    }
                }
                else if(v instanceof Boton)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.BOTON)
                        {
                            ctx.setValue(id, v);
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error tipo asignacion, el destino no es un boton\n");
                        }
                    }
                    else
                    {
                        ctx.add(new Simbolo(Simbolo.Tipo.BOTON, id, v));
                    }
                }
            }
            else//es un set
            {
                if(v instanceof Double)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.PARRAFO)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un parrafo\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOA)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoA\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOB)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoB\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.IMAGEN)
                        {
                            if(tipo == Asignacion.Tipo.SETALTO)
                            {
                                ((Imagen)s.value).alto = ((Double)v).intValue();
                            }
                            else if(tipo == Asignacion.Tipo.SETANCHO)
                            {
                                ((Imagen)s.value).ancho = ((Double)v).intValue();
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una imagen\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.TABLA)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una tabla\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.BOTON)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un boton\n");
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no es un elemento STRUCT\n");
                        }
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no existe\n");
                    }
                }
                else if(v instanceof Integer)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.PARRAFO)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un parrafo\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOA)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoA\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOB)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoB\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.IMAGEN)
                        {
                            if(tipo == Asignacion.Tipo.SETALTO)
                            {
                                ((Imagen)s.value).alto = ((Integer)v).intValue();
                            }
                            else if(tipo == Asignacion.Tipo.SETANCHO)
                            {
                                ((Imagen)s.value).ancho = ((Integer)v).intValue();
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una imagen\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.TABLA)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una tabla\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.BOTON)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un boton\n");
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no es un elemento STRUCT\n");
                        }
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no existe\n");
                    }
                }
                else if(v instanceof Boolean)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.PARRAFO)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un parrafo\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOA)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoA\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOB)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoB\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.IMAGEN)
                        {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una imagen\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.TABLA)
                        {
                            if(tipo == Asignacion.Tipo.SETBORDE)
                            {
                                ((Tabla)s.value).borde = ((Boolean)v).booleanValue();
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una tabla\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.BOTON)
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un boton\n");
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no es un elemento STRUCT\n");
                        }
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no existe\n");
                    }
                }
                else if(v instanceof String)
                {
                    if(exists)
                    {
                        if(s.tipo == Simbolo.Tipo.PARRAFO)
                        {
                            if(tipo == Asignacion.Tipo.SETALINEACION)
                            {
                                ((Parrafo)s.value).alineacion = (String)v;
                            }
                            else if(tipo == Asignacion.Tipo.SETCONTENIDO)
                            {
                                ((Parrafo)s.value).contenido = (String)v;
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un parrafo\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOA)
                        {
                            if(tipo == Asignacion.Tipo.SETCONTENIDO)
                            {
                                ((TextoA)s.value).contenido = (String)v;
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoA\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.TEXTOB)
                        {
                            if(tipo == Asignacion.Tipo.SETCONTENIDO)
                            {
                                ((TextoB)s.value).contenido = (String)v;
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un TextoB\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.IMAGEN)
                        {
                            if(tipo == Asignacion.Tipo.SETPATH)
                            {
                                ((Imagen)s.value).path = (String)v;
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una imagen\n");
                            }
                        }
                        else if(s.tipo == Simbolo.Tipo.TABLA)
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es una tabla\n");
                        }
                        else if(s.tipo == Simbolo.Tipo.BOTON)
                        {
                            if(tipo == Asignacion.Tipo.SETTEXTO)
                            {
                                ((Boton)s.value).textoLabel = (String)v;
                            }
                            else if(tipo == Asignacion.Tipo.CLICKBOTON)
                            {
                                ((Boton)s.value).textoOnClick = (String)v;
                            }
                            else
                            {
                                consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' es un boton\n");
                            }
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no es un elemento STRUCT\n");
                        }
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error '" + tipo.toString() + "', el destino '" + id +  "' no existe\n");
                    }
                }
            }
        }
        else
        {
            consola.setText(consola.getText() + "Abortando asignacion, el destino era: '" + id + "'\n");
        }
        return null;
    }
}
