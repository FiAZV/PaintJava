import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.*;
import java.util.*;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Janela extends JFrame 
{
    protected static final long serialVersionUID = 1L;

    protected JButton btnPonto   = new JButton ("Ponto"),
                      btnLinha   = new JButton ("Linha"),
                      btnCirculo = new JButton ("Circulo"),
                      btnElipse  = new JButton ("Elipse"),
                      btnQuadrado  = new JButton ("Quadrado"),
                      btnRetangulo = new JButton ("Retângulo"),
                      btnTexto = new JButton ("Texto"),
                      btnCores   = new JButton ("Cores"),
                      btnCores2   = new JButton ("Preenchimento"),
                      btnAbrir   = new JButton ("Abrir"),
                      btnSalvar  = new JButton ("Salvar"),
                      btnApagar  = new JButton ("Apagar"),
                      btnSair    = new JButton ("Sair");

    protected MeuJPanel pnlDesenho = new MeuJPanel ();

    protected JLabel statusBar1 = new JLabel ("Mensagem:"),
                     statusBar2 = new JLabel ("Coordenada:");

    protected boolean esperaPonto;
    protected boolean esperaInicioReta, esperaFimReta;
    protected boolean esperaInicioCirculo, esperaFimCirculo;
    protected boolean esperaInicioElipse, esperaFimElipse;
    protected boolean esperaInicioQuadrado, esperaFimQuadrado;
    protected boolean esperaInicioRetangulo, esperaFimRetangulo;
//    protected boolean escolhaDeCor;

    protected Color corPrincipal = Color.BLACK, corPreenchimento = new Color(0f, 0f, 0f, 0f);
    protected Ponto p1;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gráfico");

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("images/ponto.png"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("images/linha.png"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("images/circulo.png"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("images/elipse.png"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try // mudar caminho do arquivo para imagem
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("images/quadrado.png"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo elipse.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try // mudar caminho do arquivo para imagem
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("images/retangulo.png"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo elipse.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try // mudar caminho do arquivo para imagem
        {
            Image btnTextoImg = ImageIO.read(getClass().getResource("images/texto.png"));
            btnTexto.setIcon(new ImageIcon(btnTextoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo elipse.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("images/cor.png"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCores2Img = ImageIO.read(getClass().getResource("images/preencher.png"));
            btnCores2.setIcon(new ImageIcon(btnCores2Img));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo cores.jpg não foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("images/abrir.png"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("images/salvar.png"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("images/apagar.png"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("images/sair.png"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg não foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnAbrir.addActionListener (new Abrir());
        btnSalvar.addActionListener (new Salvar());

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener (new DesenhoDeElipse ());
        btnQuadrado.addActionListener (new DesenhoDeQuadrado ());
        btnRetangulo.addActionListener (new DesenhoDeRetangulo ());
        btnTexto.addActionListener (new DesenhoDeTexto ());

        btnCores.addActionListener (new SelecaoDeCorPrincipal());
        btnCores2.addActionListener (new SelecaoDeCorPreenchimento());

        btnApagar.addActionListener (new Apagar()); //mudar classe
        btnSair.addActionListener ( new Sair());

        JPanel     pnlBotoes = new JPanel();
        FlowLayout flwBotoes = new FlowLayout(); 
        pnlBotoes.setLayout (flwBotoes);

        pnlBotoes.add (btnAbrir);
        pnlBotoes.add (btnSalvar);
        pnlBotoes.add (btnPonto);
        pnlBotoes.add (btnLinha);
        pnlBotoes.add (btnCirculo);
        pnlBotoes.add (btnElipse);
        pnlBotoes.add (btnQuadrado);
        pnlBotoes.add (btnRetangulo);
        pnlBotoes.add (btnTexto);
        pnlBotoes.add (btnCores);
        pnlBotoes.add (btnCores2);
        pnlBotoes.add (btnApagar);
        pnlBotoes.add (btnSair);

        JPanel     pnlStatus = new JPanel();
        GridLayout grdStatus = new GridLayout(1,2);
        pnlStatus.setLayout(grdStatus);

        pnlStatus.add(statusBar1);
        pnlStatus.add(statusBar2);

        Container cntForm = this.getContentPane();
        cntForm.setLayout (new BorderLayout());
        cntForm.add (pnlBotoes,  BorderLayout.NORTH);
        cntForm.add (pnlDesenho, BorderLayout.CENTER);
        cntForm.add (pnlStatus,  BorderLayout.SOUTH);
        
        this.addWindowListener (new FechamentoDeJanela());

        this.setSize (700,500);
        this.setVisible (true);
    }

    protected class MeuJPanel extends JPanel implements MouseListener, MouseMotionListener
    {
	public MeuJPanel()

        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

//        public void paint (Graphics g)
//        {
//            for (int i=0 ; i<figuras.size(); i++)
//                figuras.get(i).torneSeVisivel(g);
//        }

        int  xInicio, yInicio, xFim, yFim;

        public void mousePressed (MouseEvent e)
        {

            if (esperaPonto)
            {
                figuras.add (new Ponto (e.getX(), e.getY(), corPrincipal));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                esperaPonto = false;
            }
            else if (esperaInicioReta)
            {
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioReta = false;
                esperaFimReta = true;
                statusBar1.setText("Mensagem: clique o ponto final da reta");

                xInicio = p1.getX();
                yInicio = p1.getY();
            }
            else if (esperaInicioCirculo){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioCirculo = false;
                esperaFimCirculo = true;
                statusBar1.setText("Mensagem: clique o ponto final do circulo");

                xInicio = p1.getX();
                yInicio = p1.getY();
            }
            else if (esperaInicioElipse){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioElipse = false;
                esperaFimElipse = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");

                xInicio = p1.getX();
                yInicio = p1.getY();
            }
            else if (esperaInicioQuadrado){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioQuadrado = false;
                esperaFimQuadrado = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");

                xInicio = p1.getX();
                yInicio = p1.getY();
            }
            else if (esperaInicioRetangulo){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioRetangulo = false;
                esperaFimRetangulo = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");

                xInicio = p1.getX();
                yInicio = p1.getY();
            }

        }

        public void mouseReleased (MouseEvent e)
        {
            xFim = e.getX();
            yFim = e.getY();

            if (esperaFimReta)
            {
                esperaInicioReta = false;
                esperaFimReta = false;
                figuras.add (new Linha(xInicio, yInicio, xFim, yFim, corPrincipal));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimCirculo){
                esperaInicioCirculo = false;
                esperaFimCirculo = false;
                int r = (int) sqrt(pow((xFim-xInicio), 2) + pow((yFim-yInicio),2));
                figuras.add (new Circulo(xInicio, yInicio, r, corPrincipal, corPreenchimento));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimElipse){
                esperaInicioElipse = false;
                esperaFimElipse = false;
                int r1 = (int) sqrt(pow((xFim-xInicio), 2));
                int r2 = (int) sqrt(pow((yFim-yInicio), 2));
                figuras.add (new Elipse(xInicio, yInicio, r1, r2, corPrincipal, corPreenchimento));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimQuadrado){
                esperaInicioQuadrado = false;
                esperaFimQuadrado = false;
                int b = (int) sqrt(pow((xFim-xInicio), 2));
                int h = b;
                figuras.add (new Quadrilatero(xInicio, yInicio, b, h, corPrincipal, corPreenchimento));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimRetangulo){
                esperaInicioRetangulo = false;
                esperaFimRetangulo = false;
                int b = (int) sqrt(pow((xFim-xInicio), 2));
                int h = (int) sqrt(pow((yFim-yInicio), 2));
                figuras.add (new Quadrilatero(xInicio, yInicio, b, h, corPrincipal, corPreenchimento));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
        }
        
        public void mouseClicked (MouseEvent e)
        {}
        
        public void mouseEntered (MouseEvent e)
        {}

        public void mouseExited (MouseEvent e)
        {}
        
        public void mouseDragged(MouseEvent e)
        {
                // Atualiza as coordenadas finais à medida que o mouse é arrastado
                xFim = e.getX();
                yFim = e.getY();

                // Limpa o painel e desenha a linha contínua
                pnlDesenho.repaint();
        }

        public void paint(Graphics g)
        {
            super.paint(g);
            // Desenha todas as figuras existentes
            for (Figura figura : figuras) {
                figura.torneSeVisivel(g);
            }

            // Desenha a linha contínua enquanto o mouse é arrastado
            if (esperaFimReta) {
//                g.setColor(corPrincipal);
                Linha linhaGuia = new Linha(xInicio, yInicio, xFim, yFim, corPrincipal);
                linhaGuia.torneSeVisivel(g);
            }
            if (esperaFimCirculo) {

                int r = (int) sqrt(pow((xFim-xInicio), 2) + pow((yFim-yInicio),2));
                Circulo circuloGuia = new Circulo(xInicio, yInicio, r, corPrincipal, corPreenchimento);
                circuloGuia.torneSeVisivel(g);
            }
            if (esperaFimElipse) {

                int r1 = (int) sqrt(pow((xFim-xInicio), 2));
                int r2 = (int) sqrt(pow((yFim-yInicio), 2));
                Elipse elipseGuia = new Elipse(xInicio, yInicio, r1, r2, corPrincipal, corPreenchimento);
                elipseGuia.torneSeVisivel(g);
            }
            if (esperaFimQuadrado) {
                int b = (int) sqrt(pow((xFim-xInicio), 2));
                int h = b;
                Quadrilatero quadradoGuia = (new Quadrilatero(xInicio, yInicio, b, h, corPrincipal, corPreenchimento));
                quadradoGuia.torneSeVisivel(g);
            }
            if (esperaFimRetangulo) {
                int b = (int) sqrt(pow((xFim-xInicio), 2));
                int h = (int) sqrt(pow((yFim-yInicio), 2));
                Quadrilatero retanguloGuia = (new Quadrilatero(xInicio, yInicio, b, h, corPrincipal, corPreenchimento));
                retanguloGuia.torneSeVisivel(g);
            }

        }

        public void mouseMoved(MouseEvent e)
        {
            statusBar2.setText("Coordenada: "+e.getX()+","+e.getY());
        }
    }

    protected class DesenhoDePonto implements ActionListener
    {
          public void actionPerformed (ActionEvent e)    
          {
              esperaPonto           = true;
              esperaInicioReta      = false;
              esperaInicioCirculo   = false;
              esperaInicioElipse    = false;
              esperaInicioQuadrado  = false;
              esperaInicioRetangulo = false;
              esperaFimReta         = false;
              esperaFimCirculo      = false;
              esperaFimElipse       = false;
              esperaFimQuadrado     = false;
              esperaFimRetangulo    = false;

              statusBar1.setText("Mensagem: clique o local do ponto desejado");
          }
    }

    protected class DesenhoDeReta implements ActionListener
    {
        public void actionPerformed (ActionEvent e)    
        {
            esperaPonto           = false;
            esperaInicioReta      = true;
            esperaInicioCirculo   = false;
            esperaInicioElipse    = false;
            esperaInicioQuadrado  = false;
            esperaInicioRetangulo = false;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeCirculo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto           = false;
            esperaInicioReta      = false;
            esperaInicioCirculo   = true;
            esperaInicioElipse    = false;
            esperaInicioQuadrado  = false;
            esperaInicioRetangulo = false;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeElipse implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto           = false;
            esperaInicioReta      = false;
            esperaInicioCirculo   = false;
            esperaInicioElipse    = true;
            esperaInicioQuadrado  = false;
            esperaInicioRetangulo = false;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeQuadrado implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto           = false;
            esperaInicioReta      = false;
            esperaInicioCirculo   = false;
            esperaInicioElipse    = false;
            esperaInicioQuadrado  = true;
            esperaInicioRetangulo = false;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeRetangulo implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto           = false;
            esperaInicioReta      = false;
            esperaInicioCirculo   = false;
            esperaInicioElipse    = false;
            esperaInicioQuadrado  = false;
            esperaInicioRetangulo = true;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    protected class DesenhoDeTexto implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            esperaPonto           = false;
            esperaInicioReta      = false;
            esperaInicioCirculo   = false;
            esperaInicioElipse    = false;
            esperaInicioQuadrado  = false;
            esperaInicioRetangulo = false;
            esperaFimReta         = false;
            esperaFimCirculo      = false;
            esperaFimElipse       = false;
            esperaFimQuadrado     = false;
            esperaFimRetangulo    = false;

            statusBar1.setText("Mensagem: clique o ponto inicial da reta");
        }
    }

    public class SelecaoDeCorPrincipal implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
                corPrincipal = JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
        }
    };

    public class SelecaoDeCorPreenchimento implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            corPreenchimento = JColorChooser.showDialog(null,  "Pick a Stroke", Color.BLACK);
        }
    };

    protected class Sair implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            System.exit(0);
        }
    }

    protected class Apagar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Limpa a lista de figuras
            figuras.clear();

            // Cria uma imagem temporária para o duplo-buffer
            BufferedImage tempImage = new BufferedImage(pnlDesenho.getWidth(), pnlDesenho.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D tempGraphics = tempImage.createGraphics();

            // Pinta o fundo branco na imagem temporária
            tempGraphics.setColor(Color.WHITE);
            tempGraphics.fillRect(0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight());

            // Desenha todas as figuras na imagem temporária
            for (Figura figura : figuras) {
                figura.torneSeVisivel(tempGraphics);
            }

            // Libera o contexto gráfico temporário
            tempGraphics.dispose();

            // Obtém o contexto gráfico do painel
            Graphics g = pnlDesenho.getGraphics();

            // Desenha a imagem temporária no painel
            g.drawImage(tempImage, 0, 0, pnlDesenho);

            // Libera o contexto gráfico do painel
            g.dispose();
        }
    }




    protected class Salvar implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            BufferedImage image = new BufferedImage(pnlDesenho.getWidth(), pnlDesenho.getHeight(), BufferedImage.TYPE_INT_RGB);

            // Obtém o contexto gráfico da imagem
            Graphics g = image.getGraphics();

            // Pinta o fundo branco
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, image.getWidth(), image.getHeight());

            // Desenha todas as figuras na imagem
            for (Figura figura : figuras) {
                figura.torneSeVisivel(g);
            }

            // Libera o contexto gráfico
            g.dispose();

            // Salva a imagem em um arquivo
            try {
                File outputFile = new File("saved\\paint-java"+System.currentTimeMillis()+".png");
                ImageIO.write(image, "png", outputFile);
                JOptionPane.showMessageDialog(null, "Imagem salva com sucesso em: " + outputFile.getAbsolutePath());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public class Abrir implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            JFileChooser fileChooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png", "gif");
            fileChooser.setFileFilter(filter);

            int returnVal = fileChooser.showOpenDialog(null);

            if (returnVal == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();

                try {
                    // Carrega a imagem do arquivo
                    BufferedImage imagem = ImageIO.read(file);

                    // Redimensiona a imagem para o tamanho do painel
                    BufferedImage resizedImage = new BufferedImage(pnlDesenho.getWidth(), pnlDesenho.getHeight(), BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = resizedImage.createGraphics();
                    g.drawImage(imagem, 0, 0, pnlDesenho.getWidth(), pnlDesenho.getHeight(), null);
                    g.dispose();

                    // Desenha a imagem no painel
                    pnlDesenho.getGraphics().drawImage(resizedImage, 0, 0, pnlDesenho);

                    JOptionPane.showMessageDialog(null, "Imagem carregada com sucesso!");
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao carregar a imagem: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }



}
