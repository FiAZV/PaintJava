//import java.awt.*;
//
//public class Texto extends Figura {
//
//    protected Ponto p1;
//    protected String texto;
//    protected Font fonte;
//
//    public Texto(String texto, Font fonte, Color cor, int x, int y) {
//        super(cor);
//        this.texto = texto;
//        this.fonte = fonte;
//        this.p1 = new Ponto(x,y);
//    }
//
//    public void torneSeVisivel(Graphics g) {
//        Graphics2D graphSettings = (Graphics2D) g;
//
//        // Antialiasing cleans up the jagged lines and defines rendering rules
//        graphSettings.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//
//        // Defines the line width of the stroke
//        graphSettings.setStroke(new BasicStroke(4));
//
//        graphSettings.setFont(fonte);
//
//        // Obtém as métricas da fonte para calcular a largura do texto
//        FontMetrics metrics = g.getFontMetrics(fonte);
//        int larguraTexto = metrics.stringWidth(texto);
//
//        // Define a posição para centralizar o texto no ponto
//        int x = this.p1.getX() - larguraTexto / 2;
//        int y = this.p1.getY();
//
//        graphSettings.drawString(texto, x, y);
//        g.setColor(this.cor);
//    }
//
//    public String toString() {
//        return texto;
//    }
//}
