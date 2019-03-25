/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uweb.language;

/**
 *
 * @author ozmarescobar
 */
public class SymbolAnalisis extends ErrorAnalisis
{

    public SymbolAnalisis()
    {
        super(null, null, null, 0, 0);
    }
    
    public SymbolAnalisis(String tipo, String token, String descripcion, int linea, int columna){
        super(tipo, token, descripcion, linea, columna);
    }
    
    @Override
    public String toString()
    {
        String text = ""
                + "\t\t\t\t\t<td>" + this.gravedad + "</td>\n"//tipo
                + "\t\t\t\t\t<td>" + this.token + "</td>\n"//lexema
                + "\t\t\t\t\t<td>" + this.linea + "</td>\n"
                + "\t\t\t\t\t<td>" + this.columna + "</td>\n";
        return text;
    }
}
