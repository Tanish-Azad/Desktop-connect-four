package four;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class ConnectFour extends JFrame {
    private static final int ROWS = 6;
    private static final int COLUMNS = 7;
    private final Map<String, JButton> BUTTONS = new HashMap<>();

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
        BiFunction<Integer, Integer, JButton> button = (i, j) -> BUTTONS.get(String.valueOf((char) (64 + j)) + i);

        // Check rows and columns
        for (int i = ROWS; i >= 1; i--) {
            for (int j = 1; j <= COLUMNS; j++) {
                if (button.apply(i, j).getText().equals(" ")) continue;

                // For rows
                if (j <= COLUMNS - 3) {
                    if (button.apply(i, j).getText().equals(button.apply(i, j + 1).getText()) &&
                            button.apply(i, j).getText().equals(button.apply(i, j + 2).getText()) &&
                            button.apply(i, j).getText().equals(button.apply(i, j + 3).getText())) {
                        button.apply(i, j).setBackground(Color.CYAN);
                        button.apply(i, j + 1).setBackground(Color.CYAN);
                        button.apply(i, j + 2).setBackground(Color.CYAN);
                        button.apply(i, j + 3).setBackground(Color.CYAN);

                        return true;
                    }
                }

                // For columns and diagonals
                if (i >= 4) {
                    // For columns
                    if (button.apply(i, j).getText().equals(button.apply(i - 1, j).getText()) &&
                            button.apply(i, j).getText().equals(button.apply(i - 2, j).getText()) &&
                            button.apply(i, j).getText().equals(button.apply(i - 3, j).getText())) {
                        button.apply(i, j).setBackground(Color.CYAN);
                        button.apply(i - 1, j).setBackground(Color.CYAN);
                        button.apply(i - 2, j).setBackground(Color.CYAN);
                        button.apply(i - 3, j).setBackground(Color.CYAN);

                        return true;
                    }

                    // For left to right diagonals
                    if (j <= COLUMNS - 3) {
                        if (button.apply(i, j).getText().equals(button.apply(i - 1, j + 1).getText()) &&
                                button.apply(i, j).getText().equals(button.apply(i - 2, j + 2).getText()) &&
                                button.apply(i, j).getText().equals(button.apply(i - 3, j + 3).getText())) {
                            button.apply(i, j).setBackground(Color.CYAN);
                            button.apply(i - 1, j + 1).setBackground(Color.CYAN);
                            button.apply(i - 2, j + 2).setBackground(Color.CYAN);
                            button.apply(i - 3, j + 3).setBackground(Color.CYAN);

                            return true;
                        }
                    }

                    // For right to left diagonals
                    if (j >= 4) {
                        if (button.apply(i, j).getText().equals(button.apply(i - 1, j - 1).getText()) &&
                                button.apply(i, j).getText().equals(button.apply(i - 2, j - 2).getText()) &&
                                button.apply(i, j).getText().equals(button.apply(i - 3, j - 3).getText())) {
                            button.apply(i, j).setBackground(Color.CYAN);
                            button.apply(i - 1, j - 1).setBackground(Color.CYAN);
                            button.apply(i - 2, j - 2).setBackground(Color.CYAN);
                            button.apply(i - 3, j - 3).setBackground(Color.CYAN);

                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }
}
