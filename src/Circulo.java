import java.awt.*;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Circulo extends Figura
{
    protected Ponto centro;
    protected int   raio;
	
    public Circulo (int x, int y, int r)
    {
        this (x, y, r, Color.BLACK, new Color(0f,0f,0f,0f));
    }
	
    public Circulo (int x, int y, int r, Color cor, Color corPreenchimento)
    {
        super (cor, corPreenchimento);

        this.centro = new Ponto (x,y);
        this.raio   = r;
    }

    public Circulo (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto (x,y,cor);
        this.raio   = r;
        this.cor    = cor;
    }

    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

        graphSettings.setColor(this.corPreenchimento);
        graphSettings.fillOval(this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);

        graphSettings.setColor(this.cor);
        graphSettings.drawOval(this.centro.getX()-raio, this.centro.getY()-raio, 2*raio, 2*raio);
			
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio ()
    {
        return this.raio;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public void setRaio (int r)
    {
        this.raio = r;
    }

    public String toString()
    {
        return "c:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio +
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

        Circulo outroCirculo = (Circulo)obj;
        if (outroCirculo.centro!=this.centro || outroCirculo.raio!=this.raio)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + Integer.valueOf(this.raio).hashCode();
        hashCode = 7*hashCode + this.centro.hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Circulo (Circulo objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.centro = objModelo.centro;
        this.raio = objModelo.raio;

    }
    public Object clone ()
    {
        Circulo clone = null;

        try
        {
            clone = new Circulo (this);
        }
        catch (Exception erro)
        {} // sabemos que nao vai ocorrer excecao no try acima

        return clone;
    };

    public int compareTo (Circulo outroCirculo) throws Exception
    {
        if (outroCirculo==null) throw new Exception ("objeto inválido");

        if (this.raio < outroCirculo.raio){
            return -1;
        }
        if (this.raio > outroCirculo.raio){
            return 1;
        }

        return 0;
    }

}