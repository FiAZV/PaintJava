import java.awt.*;
import java.util.*;

public class Quadrilatero extends Figura
{
    protected Ponto p1;
    protected int base, altura;

    public Quadrilatero(int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK, new Color(0f,0f,0f,0f));
    }

    public Quadrilatero(int x1, int y1, int b, int h , Color cor, Color corPreenchimento)
    {
        super (cor, corPreenchimento);

        this.p1 = new Ponto (x1,y1);
        this.base = b;
        this.altura = h;

    }

    public Quadrilatero (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        int   x   = Integer.parseInt(quebrador.nextToken());
        int   y   = Integer.parseInt(quebrador.nextToken());

        int   b   = Integer.parseInt(quebrador.nextToken());
        int   h   = Integer.parseInt(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.p1     = new Ponto (x,y,cor);
        this.base   = b;
        this.altura = h;
        this.cor    = cor;
    }


    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

        g.setColor (this.corPreenchimento);
        g.fillRect (this.p1.getX(), this.p1.getY(), this.base, this.altura);

        g.setColor (this.cor);
        g.drawRect (this.p1.getX(), this.p1.getY(), this.base, this.altura);

    }

    public Ponto getP1 () {
        return this.p1;
    }

    public int getBase() {
        return base;
    }

    public int getAltura() {
        return altura;
    }

    public void setP1(Ponto p1) {
        this.p1 = p1;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public String toString()
    {
        return "c:" +
                this.p1.getX() +
                ":" +
                this.p1.getY() +
                ":" +
                this.base +
                ":" +
                this.altura +
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

        Quadrilatero outroQuadrilatero = (Quadrilatero)obj;
        if (outroQuadrilatero.base!=this.base || outroQuadrilatero.altura!=this.altura || outroQuadrilatero.p1!=this.p1)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + Integer.valueOf(this.base).hashCode();
        hashCode = 7*hashCode + Integer.valueOf(this.altura).hashCode();
        hashCode = 7*hashCode + this.p1.hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Quadrilatero (Quadrilatero objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.p1     = objModelo.p1;
        this.base   = objModelo.base;
        this.altura = objModelo.altura;

    }
    public Object clone ()
    {
        Quadrilatero clone = null;

        try
        {
            clone = new Quadrilatero (this);
        }
        catch (Exception erro)
        {} // sabemos que nao vai ocorrer excecao no try acima

        return clone;
    };

    public int compareTo (Quadrilatero outroQuadrilatero) throws Exception
    {
        if (outroQuadrilatero==null) throw new Exception ("objeto inválido");

        int areaThis = this.base * this.altura;
        int areaOutroQuadrilatero = outroQuadrilatero.base * outroQuadrilatero.altura;

        if (areaThis < areaOutroQuadrilatero){
            return -1;
        }
        if (areaThis > areaOutroQuadrilatero){
            return 1;
        }

        return 0;
    }

}