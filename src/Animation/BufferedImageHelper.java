package Animation;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class BufferedImageHelper
{
    private static Image spriteSheet;
    private static ImageView spriteSheetView;
    private static final int TILE_SIZE = 32;

    public static Image loadSprite(String file) {

        try {
            spriteSheet = new Image(new File(file).toURI().toString());
            spriteSheetView = new ImageView(spriteSheet);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return spriteSheet;
    }

    public static ImageView getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("AnimationSpriteSheet");
        }

        spriteSheetView.setViewport(new Rectangle2D(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE));
        return spriteSheetView;
    }
}
