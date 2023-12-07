import javax.swing.*;
import java.awt.*;
import java.util.StringTokenizer;

public class Texto extends Figura{
    private String texto;
    private int x, y;
    private Font fonte;

    public Texto(int x, int y) {
        this.x = x;
        this.y = y;
        this.cor = Color.BLACK;

        // Cria uma instância da caixa de diálogo personalizada
        CaixaTexto dialog = new CaixaTexto(null);
        dialog.setVisible(true);

        // Obtém o texto e a fonte selecionada da caixa de diálogo
        this.texto = dialog.getTexto();
        this.fonte = dialog.getFonteSelecionada();
    }

    public Texto(int x, int y, Color corTexto) {
        this.x = x;
        this.y = y;
        this.cor = corTexto;

        // Cria uma instância da caixa de diálogo personalizada
        CaixaTexto dialog = new CaixaTexto(null);
        dialog.setVisible(true);

        // Obtém o texto e a fonte selecionada da caixa de diálogo
        this.texto = dialog.getTexto();
        this.fonte = dialog.getFonteSelecionada();
    }

    public Texto (String s)
    {
        StringTokenizer quebrador = new StringTokenizer(s,":");

        quebrador.nextToken();

        String txt = quebrador.nextToken();
        int    x   = Integer.parseInt(quebrador.nextToken());
        int    y   = Integer.parseInt(quebrador.nextToken());
        Font   fnt = Font.getFont(quebrador.nextToken());

        Color cor = new Color (Integer.parseInt(quebrador.nextToken()),  // R
                Integer.parseInt(quebrador.nextToken()),  // G
                Integer.parseInt(quebrador.nextToken())); // B

        this.x = x;
        this.y = y;
        this.texto = txt;
        this.fonte = fnt;
    }

    public void torneSeVisivel(Graphics g) {
        g.setColor(cor);
        g.setFont(fonte);
        g.drawString(texto, x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Font getFonte() {
        return fonte;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public void setFonte(Font fonte) {
        this.fonte = fonte;
    }

    public String toString()
    {
        return "c:" +
                this.texto +
                ":" +
                this.x +
                ":" +
                this.y +
                ":" +
                this.fonte +
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

        Texto outroTexto = (Texto)obj;
        if (outroTexto.texto!=this.texto || outroTexto.fonte!=this.fonte || outroTexto.x!=this.x || outroTexto.y!=this.y)
            return false;

        return true;
    };
    public int hashCode (){
        int hashCode = 11;

        hashCode = 7*hashCode + Integer.valueOf(this.x).hashCode();
        hashCode = 7*hashCode + Integer.valueOf(this.y).hashCode();
        hashCode = 7*hashCode + this.texto.hashCode();
        hashCode = 7*hashCode + this.fonte.hashCode();

        if (hashCode<0) hashCode = -hashCode;

        return hashCode;
    };

    public Texto (Texto objModelo) throws Exception
    {
        if (objModelo==null) throw new Exception ("modelo ausente");

        this.x     = objModelo.x;
        this.y     = objModelo.y;
        this.texto = objModelo.texto;
        this.fonte = objModelo.fonte;

    }
    public Object clone ()
    {
        Texto clone = null;

        try
        {
            clone = new Texto (this);
        }
        catch (Exception erro)
        {} // sabemos que nao vai ocorrer excecao no try acima

        return clone;
    };

    public int compareTo (Texto outroTexto) throws Exception
    {
        if (outroTexto==null) throw new Exception ("objeto inválido");

        if (this.texto.length() < outroTexto.texto.length()){
            return -1;
        }
        if (this.texto.length() > outroTexto.texto.length()){
            return 1;
        }

        return 0;
    }
}
