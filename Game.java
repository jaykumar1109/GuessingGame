import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Game extends JFrame implements ActionListener {
    private JLabel scoreLabel;
    private JButton playButton;
    private int score = 0;

    public Game() {
        //set up the JFrame
        setTitle("Simple Game");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        //set up the GUI components
        scoreLabel = new JLabel("Socre:" + score);
        scoreLabel.setFont(new Font("Arial", Font.PLAIN, 24));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        playButton = new JButton("play");
        playButton.addActionListener(this);
        playButton.setFont(new Font("Arial", Font.PLAIN, 24));
        //ADD the compoonenets to thjw frame
        Container c = getContentPane();
        c.setLayout(new BorderLayout());
        c.add(scoreLabel, BorderLayout.NORTH);
        c.add(playButton, BorderLayout.CENTER);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        //handle  button clicks
        if (e.getSource() == playButton) {
            score++;
            scoreLabel.setText("Score:" + score);
        }
    }

    public static void main(String[] args) {
        new Game();

    }
}


