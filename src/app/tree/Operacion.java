/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.tree;

import java.util.LinkedList;
import javax.swing.JTextArea;
import uweb.language.Contexto;
import uweb.language.Simbolo;

/**
 *
 * @author ozmarescobar
 */
public class Operacion implements Instruccion
{
    public static enum Tipo
    {
        //tipos de dato 
        CADENA,
        ENTERO,
        DECIMAL,
        BOOLEANO,
        IDENTIFICADOR,
        
        //tipos de operacion
        SUMA,
        RESTA,
        MULTIPLICACION,
        DIVISION,
        OR,
        AND,
        NOT,
        MAYORQUE,
        MENORQUE,
        MAYORIGUALQUE,
        MENORIGUALQUE,
        IGUALQUE,
        DISTINTOQUE,
        CONCATENACION,
        //htlm dato minimo
        PARRAFO,
        TEXTOA,
        TEXTOB,
        IMAGEN,
        TABLA,
        BOTON,
        
        //html operaciones compuestas
        GETPATH,
        GETANCHO,
        GETALTO,
        GETALINEACION,
        GETCONTENIDO,
        GETTEXTO
    }
    
    Object value;
    
    Tipo tipo;
    
    Operacion op1;
    Operacion op2;
    
    //solo utilizado para construir las hojas del arbol
    public Operacion(Object value, Tipo tipo)
    {
        this.value = value;
        this.tipo = tipo;
        this.op1 = null;
        this.op2 = null;
    }
    
    //utilizado para construir las ramas del arbol con operaciones unarias
    public Operacion(Operacion op1, Tipo tipo)
    {
        this.value = null;
        this.tipo = tipo;
        this.op1 = op1;
        this.op2 = null;
    }
    
    //utilizado para construir las ramas del arbol con operaciones binarias
    public Operacion(Operacion op1, Operacion op2, Tipo tipo)
    {
        this.value = null;
        this.tipo = tipo;
        this.op1 = op1;
        this.op2 = op2;
    }
    @Override
    public Object ejecutar(Contexto ctx, JTextArea consola)
    {
        Object resOp1;
        Object resOp2;
        Simbolo s;
        switch(tipo)
        {
            case CADENA:
                return value.toString();
            case ENTERO:
                return value;
            case DECIMAL:
                return value;
            case IDENTIFICADOR:
                Object resVa = ctx.findValue(value.toString());
                if(resVa != null)
                {
                    return resVa;
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, variable '" + value.toString() + "' no fue declarada\n");
                }
            case PARRAFO:
                return ((Parrafo)value).ejecutar(ctx, consola);
            case TEXTOA:
                return ((TextoA)value).ejecutar(ctx, consola);
            case TEXTOB:
                return ((TextoB)value).ejecutar(ctx, consola);
            case TABLA:
                return ((Tabla)value).ejecutar(ctx, consola);
            case IMAGEN:
                return ((Imagen)value).ejecutar(ctx, consola);
            case BOTON:
                return ((Boton)value).ejecutar(ctx, consola);
            case GETPATH:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.IMAGEN)
                    {
                        return ((Imagen)s.value).path;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET PATH' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case GETANCHO:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.IMAGEN)
                    {
                        return ((Imagen)s.value).ancho;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET ANCHO' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case GETALTO:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.IMAGEN)
                    {
                        return ((Imagen)s.value).alto;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET ALTO' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case GETALINEACION:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.PARRAFO)
                    {
                        return ((Parrafo)s.value).alineacion;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET ALINEACION' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case GETCONTENIDO:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.PARRAFO)
                    {
                        return ((Parrafo)s.value).contenido;
                    }
                    else if(s.tipo == Simbolo.Tipo.TEXTOA)
                    {
                        return ((TextoA)s.value).contenido;
                    }
                    else if(s.tipo == Simbolo.Tipo.TEXTOB)
                    {
                        return ((TextoB)s.value).contenido;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET CONTENIDO' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case GETTEXTO:
                s = ctx.findSymbol(value.toString());
                if(s != null)
                {
                    if(s.tipo == Simbolo.Tipo.BOTON)
                    {
                        return ((Boton)s.value).textoLabel;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'GET TEXTO' de: '" + s.getId() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una variable STRUCT no fue declarada\n");
                }
                break;
            case BOOLEANO:
                return value;
            case SUMA:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 + (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 + (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 + (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 + (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'SUMA' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case RESTA:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 - (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 - (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 - (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 - (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'RESTA' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case MULTIPLICACION:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 * (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 * (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 * (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 * (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'MULTIPLICACION' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                    
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case DIVISION:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        if(((Integer)resOp2).intValue() != 0)
                        {
                            return (Integer)resOp1 / (Integer)resOp2;
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Division por 0: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                        }
                    }
                    else if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        if(((Integer)resOp2).intValue() != 0)
                        {
                            return (Double)resOp1 / (Integer)resOp2;
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Division por 0: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                        }
                    }
                    else if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        if(((Double)resOp2).doubleValue() != 0.0)
                        {
                            return (Integer)resOp1 / (Double)resOp2;
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Division por 0: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                        }
                    }
                    else if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        if(((Double)resOp2).doubleValue() != 0.0)
                        {
                            return (Double)resOp1 / (Double)resOp2;
                        }
                        else
                        {
                            consola.setText(consola.getText() + "Division por 0: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                        }
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error de tipo, 'DIVISION' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                    }
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case OR:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Boolean && resOp2 instanceof Boolean)
                    {
                        return (Boolean)resOp1 || (Boolean)resOp2;
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error de tipo, 'OR' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                    }
                }
                consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                break;
            case AND:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Boolean && resOp2 instanceof Boolean)
                    {
                        return (Boolean)resOp1 && (Boolean)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'AND' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case NOT:
                resOp1 = op1.ejecutar(ctx, consola);
                if(resOp1 != null)
                {
                    if(resOp1 instanceof Boolean)
                    {
                        return !(Boolean)resOp1;
                    }
                    else
                    {
                        consola.setText(consola.getText() + "Error de tipo, 'NOT' de: '" + resOp1.toString() + "'\n");
                    }
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case MAYORQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 > (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 > (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 > (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 > (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'MAYOR QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case MENORQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 < (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 < (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 < (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 < (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'MENOR QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case MAYORIGUALQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 >= (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 >= (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 >= (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 >= (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'MAYOR O IGUAL QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case MENORIGUALQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return (Integer)resOp1 <= (Integer)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return (Double)resOp1 <= (Integer)resOp2;
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return (Integer)resOp1 <= (Double)resOp2;
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return (Double)resOp1 <= (Double)resOp2;
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'MENOR O IGUAL QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");
                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case IGUALQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return ((Integer)resOp1).intValue() == ((Integer)resOp2).intValue();
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return ((Double)resOp1).doubleValue() == ((Integer)resOp2).intValue();
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return ((Integer)resOp1).intValue() == ((Double)resOp2).doubleValue();
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return ((Double)resOp1).doubleValue() == ((Double)resOp2).doubleValue();
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'IGUAL QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");

                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case DISTINTOQUE:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    if(resOp1 instanceof Integer && resOp2 instanceof Integer)
                    {
                        return ((Integer)resOp1).intValue() != ((Integer)resOp2).intValue();
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Integer)
                    {
                        return ((Double)resOp1).doubleValue() != ((Integer)resOp2).intValue();
                    }
                    if(resOp1 instanceof Integer && resOp2 instanceof Double)
                    {
                        return ((Integer)resOp1).intValue() != ((Double)resOp2).doubleValue();
                    }
                    if(resOp1 instanceof Double && resOp2 instanceof Double)
                    {
                        return ((Double)resOp1).doubleValue() != ((Double)resOp2).doubleValue();
                    }
                    consola.setText(consola.getText() + "Error de tipo, 'DISTINTO QUE' de: '" + resOp1.toString() + "' y '" + resOp2.toString() + "'\n");

                }
                else
                {
                    consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                }
                break;
            case CONCATENACION:
                resOp1 = op1.ejecutar(ctx, consola);
                resOp2 = op2.ejecutar(ctx, consola);
                if(resOp1 != null && resOp2 != null)
                {
                    return resOp1.toString() +  resOp2.toString();
                }
                consola.setText(consola.getText() + "Error semantico, una o más variables no fueron declaradas\n");
                break;
        }
        return null;
    }
    
}
