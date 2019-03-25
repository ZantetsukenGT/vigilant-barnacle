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
public class Contexto extends LinkedList<Simbolo>
{

    public Contexto()
    {
        super();
    }

    public Object findValue(String id)
    {
        for (Simbolo s : this)
        {
            if (s.getId().equals(id))
            {
                return s.getValue();
            }
        }
        return null;
    }

    public Simbolo findSymbol(String id)
    {
        for (Simbolo s : this)
        {
            if (s.getId().equals(id))
            {
                return s;
            }
        }
        return null;
    }

    public void setValue(String id, Object value)
    {
        for (Simbolo s : this)
        {
            if (s.getId().equals(id))
            {
                s.setValue(value);
                return;
            }
        }
    }
}
