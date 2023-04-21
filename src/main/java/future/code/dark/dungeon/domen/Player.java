package future.code.dark.dungeon.domen;

import future.code.dark.dungeon.config.Configuration;
import future.code.dark.dungeon.service.GameMaster;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class Player extends DynamicObject {

    private static final int stepSize = 1;

    @Getter
    int coins;

    public Player(int xPosition, int yPosition) {
        super(xPosition, yPosition, Configuration.PLAYER_SPRITE);
    }

    public void move(Direction direction) {
        if (super.move(direction, stepSize)) {
            char positionCharacter = GameMaster.getInstance().getMap().getMap()[yPosition][xPosition];

            switch (positionCharacter) {
                case Configuration.COIN_CHARACTER -> {
                    coins++;
                    GameMaster.getInstance().removeEnemy(xPosition, yPosition);
                    GameMaster.getInstance().getMap().getMap()[yPosition][xPosition] = Configuration.LAND_CHARACTER;
                }
                case Configuration.EXIT_CHARACTER -> GameMaster.getInstance().setWin(true);
            }
        }
    }

    @Override
    protected boolean isAllowedSurface(int x, int y) {
        if (GameMaster.getInstance().getMap().getMap()[y][x] == Configuration.EXIT_CHARACTER && coins < GameMaster.TOTAL_COINS) {
            return false;
        }

        return super.isAllowedSurface(x, y);
    }

    @Override
    public String toString() {
        return "Player{[" + xPosition + ":" + yPosition + "]}";
    }
}
