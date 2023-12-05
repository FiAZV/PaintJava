import java.awt.*;

public class Quadrilatero extends Figura
{
    protected Ponto p1;
    protected int largura, altura;

    public Quadrilatero(int x1, int y1, int x2, int y2)
    {
        this (x1, y1, x2, y2, Color.BLACK);
    }

    public Quadrilatero(int x1, int y1, int b, int h , Color cor)
    {
        super (cor);

        this.p1 = new Ponto (x1,y1);
        this.largura = b;
        this.altura = h;

    }

//    public Quadrado (String s)
//    {
//        StringTokenizer quebrador = new StringTokenizer(s,":");
//
//        quebrador.nextToken();
//
//        int   x   = Integer.parseInt(quebrador.nextToken());
//        int   y   = Integer.parseInt(quebrador.nextToken());
//
//        int   r   = Integer.parseInt(quebrador.nextToken());
//
//        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
//                Integer.parseInt(quebrador.nextToken()),  // G
//                Integer.parseInt(quebrador.nextToken())); // B
//
//        this.centro = new Ponto (x,y,cor);
//        this.raio   = r;
//        this.cor    = cor;
//    }

    public void setP1 (int x, int y)
    {
        this.p1 = new Ponto (x,y,this.getCor());
    }

    public Ponto getP1 () {
        return this.p1;
    }


    public void torneSeVisivel (Graphics g)
    {
        Graphics2D graphSettings = (Graphics2D)g;

        // Antialiasing cleans up the jagged lines and defines rendering rules
        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Defines the line width of the stroke
        graphSettings.setStroke(new BasicStroke(4));

        g.setColor (this.cor);
        g.drawRect (this.p1.getX(), this.p1.getY(), this.largura, this.altura);

    }

    public String toString()
    {
        return "c:" +
                this.p1.getX() +
                ":" +
                this.largura +
                ":" +
                this.altura +
                ":" +
                this.getCor().getRed() +
                ":" +
                this.getCor().getGreen() +
                ":" +
                this.getCor().getBlue();
    }
}