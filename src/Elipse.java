import java.awt.*;
import java.util.*;

public class Elipse extends Figura
{
    protected Ponto centro;

    protected int raio1, raio2;
	
    public Elipse (int x, int y, int r1, int r2)
    {
        this (x, y, r1, r2, Color.BLACK, new Color(0f,0f,0f,0f));
    }
	
    public Elipse (int x, int y, int r1, int r2, Color cor, Color corPreenchimento)
    {
        super (cor, corPreenchimento);

        this.centro = new Ponto (x,y);

        this.raio1  = r1;
        this.raio2  = r2;
    }

    public Elipse (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   r1  = Integer.parseInt(quebrador.nextToken());
        int   r2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                               Integer.parseInt(quebrador.nextToken()),  // G
                               Integer.parseInt(quebrador.nextToken())); // B

        this.centro = new Ponto (x,y,cor);
        this.raio1  = r1;
        this.raio2  = r2;
        this.cor    = cor;
    }

    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

        graphSettings.setColor (this.corPreenchimento);
        graphSettings.fillOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);

        graphSettings.setColor (this.cor);
        graphSettings.drawOval (this.centro.getX()-raio1, this.centro.getY()-raio2, 2*raio1, 2*raio2);
			
    }

    public Ponto getCentro ()
    {
        return this.centro;
    }

    public int getRaio1 ()
    {
        return this.raio1;
    }

    public int getRaio2 ()
    {
        return this.raio2;
    }

    public void setCentro (int x, int y)
    {
        this.centro = new Ponto (x,y,this.getCor());
    }

    public void setRaio1 (int r1)
    {
        this.raio1 = r1;
    }

    public void setRaio2 (int r2)
    {
        this.raio2 = r2;
    }

    public String toString()
    {
        return "e:" +
               this.centro.getX() +
               ":" +
               this.centro.getY() +
               ":" +
               this.raio1 +
               ":" +
               this.raio2 +
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

        Elipse outraElipse = (Elipse)obj;
        if (outraElipse.centro!=this.centro || outraElipse.raio1!=this.raio1 || outraElipse.raio2!=this.raio2)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + Integer.valueOf(this.raio1).hashCode();
        hashCode = 7*hashCode + Integer.valueOf(this.raio2).hashCode();
        hashCode = 7*hashCode + this.centro.hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Elipse (Elipse objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.centro = objModelo.centro;
        this.raio1 = objModelo.raio1;
        this.raio2 = objModelo.raio2;

    }
    public Object clone ()
    {
        Elipse clone = null;

        try
        {
            clone = new Elipse (this);
        }
        catch (Exception erro)
        {} // sabemos que nao vai ocorrer excecao no try acima

        return clone;
    };

    public int compareTo (Elipse outraElipse) throws Exception
    {
        if (outraElipse==null) throw new Exception ("objeto inválido");

        int areaThis = this.raio1 * this.raio2 * 3; //não possui e nem precisa de uma precisão grande, por isso foi usado pi=3
        int areaOutraElipse = outraElipse.raio1 * outraElipse.raio2 * 3;

        if (areaThis < areaOutraElipse){
            return -1;
        }
        if (areaThis > areaOutraElipse){
            return 1;
        }

        return 0;
    }

}