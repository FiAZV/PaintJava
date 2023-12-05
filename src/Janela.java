import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.*;
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
                      btnRetangulo = new JButton ("Ret�ngulo"),
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

    protected Color corPrincipal = Color.BLACK, corPreenchimento = Color.BLACK;
    protected Ponto p1;
    
    protected Vector<Figura> figuras = new Vector<Figura>();

    public Janela ()
    {
        super("Editor Gr�fico");

        try
        {
            Image btnPontoImg = ImageIO.read(getClass().getResource("images/ponto.jpg"));
            btnPonto.setIcon(new ImageIcon(btnPontoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo ponto.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnLinhaImg = ImageIO.read(getClass().getResource("images/linha.jpg"));
            btnLinha.setIcon(new ImageIcon(btnLinhaImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo linha.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCirculoImg = ImageIO.read(getClass().getResource("images/circulo.jpg"));
            btnCirculo.setIcon(new ImageIcon(btnCirculoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo circulo.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnElipseImg = ImageIO.read(getClass().getResource("images/elipse.jpg"));
            btnElipse.setIcon(new ImageIcon(btnElipseImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo elipse.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try // mudar caminho do arquivo para imagem
        {
            Image btnQuadradoImg = ImageIO.read(getClass().getResource("images/elipse.jpg"));
            btnQuadrado.setIcon(new ImageIcon(btnQuadradoImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo elipse.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try // mudar caminho do arquivo para imagem
        {
            Image btnRetanguloImg = ImageIO.read(getClass().getResource("images/elipse.jpg"));
            btnRetangulo.setIcon(new ImageIcon(btnRetanguloImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo elipse.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCoresImg = ImageIO.read(getClass().getResource("images/cores.jpg"));
            btnCores.setIcon(new ImageIcon(btnCoresImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo cores.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnCores2Img = ImageIO.read(getClass().getResource("images/cores.jpg"));
            btnCores2.setIcon(new ImageIcon(btnCores2Img));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                    "Arquivo cores.jpg n�o foi encontrado",
                    "Arquivo de imagem ausente",
                    JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnAbrirImg = ImageIO.read(getClass().getResource("images/abrir.jpg"));
            btnAbrir.setIcon(new ImageIcon(btnAbrirImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo abrir.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSalvarImg = ImageIO.read(getClass().getResource("images/salvar.jpg"));
            btnSalvar.setIcon(new ImageIcon(btnSalvarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo salvar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnApagarImg = ImageIO.read(getClass().getResource("images/apagar.jpg"));
            btnApagar.setIcon(new ImageIcon(btnApagarImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo apagar.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        try
        {
            Image btnSairImg = ImageIO.read(getClass().getResource("images/sair.jpg"));
            btnSair.setIcon(new ImageIcon(btnSairImg));
        }
        catch (IOException e)
        {
            JOptionPane.showMessageDialog (null,
                                           "Arquivo sair.jpg n�o foi encontrado",
                                           "Arquivo de imagem ausente",
                                           JOptionPane.WARNING_MESSAGE);
        }

        btnAbrir.addActionListener (new DesenhoDePonto()); //mudar classe
        btnSalvar.addActionListener (new DesenhoDePonto()); //mudar classe

        btnPonto.addActionListener (new DesenhoDePonto());
        btnLinha.addActionListener (new DesenhoDeReta ());
        btnCirculo.addActionListener (new DesenhoDeCirculo ());
        btnElipse.addActionListener (new DesenhoDeElipse ());
        btnQuadrado.addActionListener (new DesenhoDeQuadrado ());
        btnRetangulo.addActionListener (new DesenhoDeRetangulo ());

        btnCores.addActionListener (new SelecaoDeCorPrincipal());
        btnCores2.addActionListener (new SelecaoDeCorPreenchimento());

        btnApagar.addActionListener (new DesenhoDePonto()); //mudar classe
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

    protected class MeuJPanel extends    JPanel 
                              implements MouseListener,
                                         MouseMotionListener
    {
	public MeuJPanel()
        {
            super();

            this.addMouseListener       (this);
            this.addMouseMotionListener (this);
        }

        public void paint (Graphics g)
        {
            for (int i=0 ; i<figuras.size(); i++)
                figuras.get(i).torneSeVisivel(g);
        }
        
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
            }
            else if (esperaInicioCirculo){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioCirculo = false;
                esperaFimCirculo = true;
                statusBar1.setText("Mensagem: clique o ponto final do circulo");
            }
            else if (esperaInicioElipse){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioElipse = false;
                esperaFimElipse = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");
            }
            else if (esperaInicioQuadrado){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioQuadrado = false;
                esperaFimQuadrado = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");
            }
            else if (esperaInicioRetangulo){
                p1 = new Ponto (e.getX(), e.getY(), corPrincipal);
                esperaInicioRetangulo = false;
                esperaFimRetangulo = true;
                statusBar1.setText("Mensagem: clique o ponto final da elipse");
            }

        }
        
        public void mouseReleased (MouseEvent e)
        {
            if (esperaFimReta)
            {
                esperaInicioReta = false;
                esperaFimReta = false;
                figuras.add (new Linha(p1.getX(), p1.getY(), e.getX(), e.getY(), corPrincipal));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimCirculo){
                esperaInicioCirculo = false;
                esperaFimCirculo = false;
                int r = (int) sqrt(pow((e.getX()-p1.getX()), 2) + pow((e.getY()-p1.getY()),2));
                figuras.add (new Circulo(p1.getX(), p1.getY(), r, corPrincipal, corPreenchimento));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimElipse){
                esperaInicioElipse = false;
                esperaFimElipse = false;
                int r1 = e.getX()-p1.getX();
                int r2 = e.getY()-p1.getY();
                figuras.add (new Elipse(p1.getX(), p1.getY(), r1, r2, corPrincipal));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimQuadrado){
                esperaInicioQuadrado = false;
                esperaFimQuadrado = false;
                int b = e.getX()-p1.getX();
                int h = b;
                figuras.add (new Quadrilatero(p1.getX(), p1.getY(), b, h, corPrincipal));
                figuras.get(figuras.size()-1).torneSeVisivel(pnlDesenho.getGraphics());
                statusBar1.setText("Mensagem:");
            }
            else if (esperaFimRetangulo){
                esperaInicioRetangulo = false;
                esperaFimRetangulo = false;
                int b = e.getX()-p1.getX();
                int h = e.getY()-p1.getY();
                figuras.add (new Quadrilatero(p1.getX(), p1.getY(), b, h, corPrincipal));
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

    protected class FechamentoDeJanela extends WindowAdapter
    {
        public void windowClosing (WindowEvent e)
        {
            System.exit(0);
        }
    }
}
