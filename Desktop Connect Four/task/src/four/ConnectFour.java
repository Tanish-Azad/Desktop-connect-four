package four;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ConnectFour extends JFrame {
    private final Map<String, JButton> BUTTONS = new HashMap<>();
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;

    public ConnectFour() {
        super("Connect Four");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setVisible(true);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel gameField = new JPanel();
        gameField.setLayout(new GridLayout(ROWS, COLUMNS));

        boolean[] x = {true};
        for (int i = ROWS; i >= 1; i--) {
            for (int j = 1; j <= COLUMNS; j++) {
                String cellCharacter = String.valueOf((char) (64 + j));
                String cellId = cellCharacter + i;
                JButton button = new JButton(" ");
                button.setBackground(Color.DARK_GRAY);
                button.setName("Button" + cellId);
                button.setFocusPainted(false);
                button.setForeground(Color.white);
                button.setFont(new Font("Arial", Font.BOLD, 40));
                button.addActionListener(actionEvent -> {
                    for (int k = 1; k < COLUMNS; k++) {
                        JButton jButton = BUTTONS.get(cellCharacter + k);

                        if (jButton.getText().equals(" ")) {
                            jButton.setText(x[0] ? "X" : "O");
                            break;
                        }
                    }
                    x[0] = !x[0];
                    if (gameFinished()) {
                        for (JButton jButton : BUTTONS.values()) {
                            jButton.setEnabled(false);
                        }
                    }
                });
                BUTTONS.put(cellId, button);
                gameField.add(button);
            }
        }
        add(gameField, BorderLayout.CENTER);

        JPanel footer = new JPanel();
        footer.setLayout(new BorderLayout());
        JButton resetButton = new JButton("Reset");
        resetButton.setName("ButtonReset");
        resetButton.setFocusPainted(false);
        resetButton.addActionListener(actionEvent -> {
            for (JButton button : BUTTONS.values()) {
                button.setText(" ");
                button.setEnabled(true);
                button.setBackground(Color.DARK_GRAY);
            }

            x[0] = true;
        });
        footer.add(resetButton, BorderLayout.EAST);
        add(footer, BorderLayout.SOUTH);
    }

    private boolean gameFinished() {
        // Check rows and columns
        for (int i = ROWS; i >= 1; i--) {
            for (int j = 1; j <= COLUMNS; j++) {
                if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(" ")) continue;

                // For rows
                if (j <= COLUMNS - 3) {
                    if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 1)) + i).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j + 1)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 2)) + i).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j + 2)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 3)) + i).getText())) {
                        BUTTONS.get(String.valueOf((char) (64 + j)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 1)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 2)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 3)) + i).setBackground(Color.CYAN);

                        return true;
                    }
                }

                // For columns
                if (i >= 4) {
                    if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 1)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 1)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 2)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 2)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 3)).getText())) {
                        BUTTONS.get(String.valueOf((char) (64 + j)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 1)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 2)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j)) + (i - 3)).setBackground(Color.CYAN);

                        return true;
                    }
                }
            }
        }

        // Check diagonals
        for (int i = ROWS; i >= 4; i--) {
            for (int j = 1; j <= COLUMNS; j++) {
                if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(" ")) continue;
                System.out.println(BUTTONS.get(String.valueOf((char) (64 + j)) + i).getName());

                // For left to right diagonals
                if (j <= COLUMNS - 3) {
                    if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 1)) + (i - 1)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j + 1)) + (i - 1)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 2)) + (i - 2)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j + 2)) + (i - 2)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j + 3)) + (i - 3)).getText())) {
                        BUTTONS.get(String.valueOf((char) (64 + j)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 1)) + (i - 1)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 2)) + (i - 2)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j + 3)) + (i - 3)).setBackground(Color.CYAN);

                        return true;
                    }
                }

                // For right to left diagonals
                if (j >= 4) {
                    if (BUTTONS.get(String.valueOf((char) (64 + j)) + i).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j - 1)) + (i - 1)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j - 1)) + (i - 1)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j - 2)) + (i - 2)).getText()) &&
                            BUTTONS.get(String.valueOf((char) (64 + j - 2)) + (i - 2)).getText().equals(BUTTONS.get(String.valueOf((char) (64 + j - 3)) + (i - 3)).getText())) {
                        BUTTONS.get(String.valueOf((char) (64 + j)) + i).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j - 1)) + (i - 1)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j - 2)) + (i - 2)).setBackground(Color.CYAN);
                        BUTTONS.get(String.valueOf((char) (64 + j - 3)) + (i - 3)).setBackground(Color.CYAN);

                        return true;
                    }
                }
            }
        }

        return false;
    }
}
