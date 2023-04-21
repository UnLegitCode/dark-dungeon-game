package future.code.dark.dungeon;

import future.code.dark.dungeon.config.Configuration;
import lombok.Getter;

import javax.swing.JFrame;

public class Main {

    @Getter
    private static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame(Configuration.GAME_NAME);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(new GameFrame(frame));
        frame.setVisible(true);
    }
}