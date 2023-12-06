import java.awt.*;

public abstract class Figura
{
    protected Color cor;
    protected Color corPreenchimento;

    protected Figura ()
    {
        this (Color.BLACK, new Color(0f,0f,0f,0f));
    }

    protected Figura (Color cor)
    {
        this.cor = cor;
        this.corPreenchimento = new Color(0f,0f,0f,0f);
    }
	  
    protected Figura (Color cor, Color corPreenchimento)
    {
        this.cor = cor;
        this.corPreenchimento = corPreenchimento;
    }
	  
    public void setCor (Color cor)
    {
        this.cor = cor;
    }
	  
    public Color getCor()
    {
    	return this.cor;
    }

    public abstract boolean equals         (Object obj);
    public abstract int     hashCode       ();
    public abstract Object  clone          ();
    public abstract String  toString       ();
    public abstract void    torneSeVisivel (Graphics g);

}