package Elements.Blocks;

import Physicsengine.Physics;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class  Block extends Rectangle implements Physics {

    public Block(){

        setWidth(50);
        setHeight(50);



    }

    /**
     * Setting the TileCover for every single Tile
     * @param tile
     * @param in
     */
    public static void setTileImage(Block tile, InputStream in){

        Image img = new Image(in);
        tile.setFill(new ImagePattern(img));

    }

}
