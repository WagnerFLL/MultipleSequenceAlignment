import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class GUI {
    private JFrame frame;
    private JTextField textField;
    private String[] Seqs = new String[10000];
    private int stringCounter = 0;
    private JTextField textField_1;
    private JTextArea txtrD;
    private String file = "";
    private char[][] matrix = new char[24][3];

    private GUI() {
        this.initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GUI window = new GUI();
                    window.frame.setVisible(true);
                } catch (Exception var2) {
                    var2.printStackTrace();
                }

            }
        });
    }

    private void initialize() {
        this.frame = new JFrame();
        this.frame.setBounds(100, 100, 800, 660);
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.getContentPane().setLayout(null);
        JButton btnNewButton = new JButton("Add Sequence");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                GUI.this.Seqs[GUI.this.stringCounter] = GUI.this.textField.getText();
                GUI.this.txtrD.append(GUI.this.Seqs[GUI.this.stringCounter] + "\n");
                CenterStar.sequence[GUI.this.stringCounter] = GUI.this.Seqs[GUI.this.stringCounter];
                GUI.this.stringCounter = GUI.this.stringCounter + 1;
                GUI.this.textField.setText(">");
            }
        });
        btnNewButton.setBounds(24, 96, 122, 23);
        this.frame.getContentPane().add(btnNewButton);
        this.textField = new JTextField();
        this.textField.setText(">");
        this.textField.setBounds(10, 52, 764, 20);
        this.frame.getContentPane().add(this.textField);
        this.textField.setColumns(10);
        JLabel lblEnterSequences = new JLabel("Enter Sequences :");
        lblEnterSequences.setFont(new Font("Tahoma", Font.PLAIN, 12));
        lblEnterSequences.setBounds(341, 26, 102, 15);
        this.frame.getContentPane().add(lblEnterSequences);
        JButton btnNewButton_1 = new JButton("Cumpute");
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (GUI.this.file.equals("")) {
                    JOptionPane.showMessageDialog(null, "File needs to be choose first.");
                } else {
                    GUI.this.txtrD.setText(CenterStar.cumpute(GUI.this.matrix, GUI.this.stringCounter));
                    GUI.this.textField_1.setText(String.valueOf(CenterStar.totalScore));
                }

            }
        });
        btnNewButton_1.setBounds(637, 96, 137, 23);
        this.frame.getContentPane().add(btnNewButton_1);
        JButton btnNewButton_2 = new JButton("Subtitution Matrix File");
        btnNewButton_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fs = new JFileChooser(new File("c:\\"));
                fs.setDialogTitle("Open a File");
                fs.showOpenDialog(null);
                File fi = fs.getSelectedFile();

                try {
                    BufferedReader br = new BufferedReader(new FileReader(fi.getPath()));
                    String line;

                    while (null != (line = br.readLine())) {
                        GUI.this.file = GUI.this.file.concat(line);
                    }

                    br.close();
                } catch (Exception var6) {
                    JOptionPane.showMessageDialog(null, var6.getMessage());
                }

                GUI.this.initMatrix();
            }
        });
        btnNewButton_2.setBounds(156, 96, 167, 23);
        this.frame.getContentPane().add(btnNewButton_2);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 130, 764, 418);
        this.frame.getContentPane().add(scrollPane);
        this.txtrD = new JTextArea();
        scrollPane.setViewportView(this.txtrD);
        this.txtrD.setEnabled(false);
        this.txtrD.setFont(new Font("Courier New", 1, 16));
        this.txtrD.setEditable(false);
        this.txtrD.setForeground(Color.magenta);
        JLabel lblNewLabel = new JLabel("Score calculated by the file values :");
        lblNewLabel.setFont(new Font("Tahoma", 0, 16));
        lblNewLabel.setBounds(10, 582, 227, 14);
        this.frame.getContentPane().add(lblNewLabel);
        this.textField_1 = new JTextField();
        this.textField_1.setBounds(231, 580, 86, 20);
        this.frame.getContentPane().add(this.textField_1);
        this.textField_1.setColumns(10);
    }

    private void initMatrix() {
        this.file = this.file.replace(" ", "");

        for (int i = 0; i < 24; ++i) {
            this.matrix[i] = this.file.substring(i * 3, i * 3 + 3).toCharArray();
        }

    }
}
