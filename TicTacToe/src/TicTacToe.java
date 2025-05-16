import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextLayout;
import javax.swing.*;
import javax.swing.border.Border;

public class TicTacToe {
    int boarderWidth = 600;
    int boarderHeight = 650;

    JFrame frame = new JFrame("Tic-Tac-Toe");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel borderPanel = new JPanel();

    JButton[][] boarder = new JButton[3][3];
    String playerX = "X";
    String playerO = "O";
    String currentPlayer = playerX;


    Boolean gameOver = false;
    int turns = 0;

    TicTacToe () {
        frame.setVisible(true);
        frame.setSize(boarderWidth, boarderHeight);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setBackground(Color.pink);
        textLabel.setForeground(Color.black);
        textLabel.setFont(new Font("Arial", Font.BOLD, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        borderPanel.setLayout(new GridLayout(3, 3));
        borderPanel.setBackground(Color.pink);
        frame.add(borderPanel);

        for (int i=0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                JButton tile = new JButton();
                boarder[i][j] = tile;
                borderPanel.add(tile);

                tile.setBackground(Color.pink);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial", Font.BOLD, 120));
                tile.setFocusable(false);
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (gameOver) return;
                        JButton tile = (JButton) e.getSource();
                        if (tile.getText() == "") {
                            tile.setText(currentPlayer);
                            turns++;
                            checkWinner();
                                if (!gameOver) {
                                    currentPlayer = currentPlayer == playerX ? playerO : playerX;
                                    textLabel.setText(currentPlayer + "! It's your turn!");
                                }
                            }
                        }
                    });
                }
            }
        }
        void checkWinner() {
        //horizontal
            for (int j=0; j < 3; j++) {
                if (boarder[j][0].getText() == "") continue;

                if (boarder[j][0].getText() == boarder[j][1].getText() &&
                        boarder[j][1].getText() == boarder[j][2].getText()) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(boarder[j][i]);
                    }       
                    gameOver = true;
                    return;
                }
            }
            //vertical
            for (int c = 0; c < 3; c++) {
                if (boarder[0][c].getText() == "") continue;

                if (boarder[0][c].getText() == boarder[1][c].getText() &&
                        boarder[1][c].getText() == boarder[2][c].getText()) {
                    for (int i = 0; i < 3; i++) {
                        setWinner(boarder[i][c]);
                    }

                    gameOver = true;
                    return;
                }
            }

            //diagonal
            if (boarder[0][0].getText() == boarder[1][1].getText() &&
                    boarder[1][1].getText() == boarder[2][2].getText() &&
                    boarder[0][0].getText() != "") {
                for (int i = 0; i < 3; i++) {
                    setWinner(boarder[i][i]);
                }
                gameOver = true;
                return;
            }
            //anti-diagonal
            if (boarder[0][2].getText() == boarder[1][1].getText() &&
                    boarder[1][1].getText() == boarder[2][0].getText() &&
                    boarder[0][2].getText() != "") {
                setWinner(boarder[0][2]);
                setWinner(boarder[1][1]);
                setWinner(boarder[2][0]);
                gameOver = true;
                return;
            }

            if (turns == 9) {
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        setTie(boarder[r][c]);
                    }
                }
                gameOver = true;
            }

}
            void setWinner(JButton tile) {
        tile.setForeground(Color.pink);
        tile.setBackground(Color.white);
        textLabel.setText(currentPlayer + ", you are the winner!");
            }
            void setTie(JButton tile) {
                tile.setForeground(Color.pink);
                tile.setBackground(Color.white);
                textLabel.setText("It's a tie!");
            }
}

