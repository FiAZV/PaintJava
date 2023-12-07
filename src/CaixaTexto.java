import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CaixaTexto extends JDialog {
    private JTextField textField;
    private JComboBox<String> fontComboBox;
    private JComboBox<Integer> sizeComboBox;
    private JCheckBox boldCheckBox;
    private JCheckBox italicCheckBox;
    private JButton btnOK;
    private JButton btnCancel;

    private String texto;
    private Font fonteSelecionada;

    public CaixaTexto(JFrame parent) {
        super(parent, "Configurar Texto", true);
        setSize(400, 200);
        setLocationRelativeTo(parent);

        initUI();
    }

    private void initUI() {
        // Layout da caixa de diálogo
        setLayout(new GridLayout(5, 2));

        // Componentes da caixa de diálogo
        textField = new JTextField();
        fontComboBox = new JComboBox<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        sizeComboBox = new JComboBox<>(new Integer[]{10, 12, 14, 16, 18, 20});
        boldCheckBox = new JCheckBox("Negrito");
        italicCheckBox = new JCheckBox("Itálico");
        btnOK = new JButton("OK");
        btnCancel = new JButton("Cancelar");

        // Adiciona os componentes à caixa de diálogo
        add(new JLabel("Texto:"));
        add(textField);
        add(new JLabel("Fonte:"));
        add(fontComboBox);
        add(new JLabel("Tamanho:"));
        add(sizeComboBox);
        add(boldCheckBox);
        add(italicCheckBox);
        add(btnOK);
        add(btnCancel);

        // Configuração do botão OK
        btnOK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = textField.getText();
                int estilo = Font.PLAIN;
                estilo += boldCheckBox.isSelected() ? Font.BOLD : 0;
                estilo += italicCheckBox.isSelected() ? Font.ITALIC : 0;
                int tamanho = (Integer) sizeComboBox.getSelectedItem();
                fonteSelecionada = new Font((String) fontComboBox.getSelectedItem(), estilo, tamanho);

                // Fecha a caixa de diálogo
                dispose();
            }
        });

        // Configuração do botão Cancelar
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Define texto e fonte como nulos (não haverá texto adicionado)
                texto = null;
                fonteSelecionada = null;

                // Fecha a caixa de diálogo
                dispose();
            }
        });
    }

    public String getTexto() {
        return texto;
    }

    public Font getFonteSelecionada() {
        return fonteSelecionada;
    }
}
