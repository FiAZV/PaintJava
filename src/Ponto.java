import java.awt.*;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Ponto extends Figura
{
    protected int x,  y;

    public Ponto (int x, int y)
    {
        this (x, y, Color.BLACK);
    }
	  
    public Ponto (int x, int y, Color cor)
    {
        super (cor);

        this.x = x;
        this.y = y;
    }

    public Ponto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        this.x = Integer.parseInt(quebrador.nextToken());
        this.y = Integer.parseInt(quebrador.nextToken());

        this.cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                              Integer.parseInt(quebrador.nextToken()),  // G
                              Integer.parseInt(quebrador.nextToken())); // B
    }

    public void setX (int x)
    {
        this.x = x;
    }
	  
    public void setY (int y)
    {
        this.y = y;
    }
	  
    public int getX ()
    {
        return this.x;
    }
	  
    public int getY ()
    {
    	return this.y;
    }
	  
    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

    	g.setColor (this.cor);
    	g.drawLine (this.x,this.y,this.x,this.y);
    }

    public String toString()
    {
        return "p:" +
               this.x +
               ":" +
               this.y +
               ":" +
               this.getCor().getRed() +
               ":" +
               this.getCor().getGreen() +
               ":" +
               this.getCor().getBlue();
    }

    public boolean equals (Object obj){
        if (obj==this) return true;
        if (obj==null) return false;
        if (obj.getClass()!=this.getClass()) return false;

        Ponto outroPonto = (Ponto)obj;
        if (outroPonto.x!=this.x || outroPonto.y!=this.y)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + Integer.valueOf(this.x).hashCode();
        hashCode = 7*hashCode + Integer.valueOf(this.y).hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Ponto (Ponto objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.x = objModelo.x;
        this.y = objModelo.y;

    }
    public Object clone ()
    {
        Ponto clone = null;

        try
        {
            clone = new Ponto (this);
        }
        catch (Exception erro)
        {} // sabemos que nao vai ocorrer excecao no try acima

        return clone;
    };

}