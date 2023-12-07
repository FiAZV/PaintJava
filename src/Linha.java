import java.awt.*;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Linha extends Figura
{
    protected Ponto p1, p2;

    public Linha (int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }

    public Linha (int x1, int y1, int x2, int y2, Color cor)
    {
        super(cor);

        this.p1 = new Ponto (x1,y1,cor);
        this.p2 = new Ponto (x2,y2,cor);
    }

    public Linha (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x1  = Integer.parseInt(quebrador.nextToken());
        int   y1  = Integer.parseInt(quebrador.nextToken());

        int   x2  = Integer.parseInt(quebrador.nextToken());
        int   y2  = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.p1  = new Ponto (x1,y1,cor);
        this.p2  = new Ponto (x2,y2,cor);
        this.cor = cor;
    }

    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

        g.setColor(this.cor);
        g.drawLine(this.p1.getX(), this.p1.getY(), this.p2.getX(), this.p2.getY());
    }

    public Ponto getP1 ()
    {
        return this.p1;
    }

    public Ponto getP2 ()
    {
        return this.p2;
    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public void setP2 (int x, int y)
    {
        this.p2 = new Ponto (x,y,this.getCor());
    }

    public String toString()
    {
        return "r:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.p2.getX() +
                ":" +
                this.p2.getY() +
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

        Linha outraLinha = (Linha)obj;
        if (outraLinha.p1!=this.p1 || outraLinha.p2!=this.p2)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + this.p1.hashCode();
        hashCode = 7*hashCode + this.p2.hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Linha (Linha objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.p1 = objModelo.p1;
        this.p2 = objModelo.p2;

    }
    public Object clone ()
    {
            Linha clone = null;

            try
            {
                clone = new Linha (this);
            }
            catch (Exception erro)
            {} // sabemos que nao vai ocorrer excecao no try acima

            return clone;
    };

    public int compareTo (Linha outraLinha) throws Exception
    {
        if (outraLinha==null) throw new Exception ("objeto inválido");

        double distThis = sqrt(pow(this.p1.getX() - this.p2.getX(), 2) + pow(this.p1.getY() - this.p2.getY(), 2));
        double distOutraLinha = sqrt(pow(outraLinha.p1.getX() - outraLinha.p2.getX(), 2) + pow(outraLinha.p1.getY() - outraLinha.p2.getY(), 2));

        if (distThis < distOutraLinha){
            return -1;
        }
        if (distThis > distOutraLinha){
            return 1;
        }

        return 0;
    }
}