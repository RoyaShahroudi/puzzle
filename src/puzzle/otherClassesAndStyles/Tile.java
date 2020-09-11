package puzzle.otherClassesAndStyles;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import puzzle.controllers.Game;
import puzzle.controllers.Levels;
import puzzle.controllers.Result;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Tile extends StackPane {

    private int tileSize;
    private Text text = new Text();
    private Rectangle tile;
    private int x;
    private int y;
    private int index;
    private int size;
    private User user;
    ArrayList<Tile> tiles = new ArrayList<>();
    private static Game game = new Game();


    public Tile(int tileSize, int index, int x, int y, int size, ArrayList<Tile> tiles, User user) {
        this.tileSize = tileSize;
        this.size = size;
        this.index = index;
        this.x = x;
        this.y = y;
        this.tiles = tiles;
        this.user = user;

        game.setBlankTile((size*size)-1);
        game.setBlankTileX(size-1);
        game.setBlankTileY(size-1);

        tile = new Rectangle(tileSize, tileSize);

        tile.setStroke(Color.rgb(33, 0, 85));
        tile.setId("tiles");
        text.setId("numbers");

        setOnMouseClicked(event -> {

            if (((game.getBlankTileX() - x == 1 || game.getBlankTileX() - x == -1) && game.getBlankTileY() == y) ||
                    ((game.getBlankTileY() - y == 1 || game.getBlankTileY() - y == -1) && game.getBlankTileX() == x)) {
                // convert blankTile to a normal tile with this number of text

                tiles.get(game.getBlankTile()).setNormalTile(getTextTile());

                // convert this tile to a blank tile
                setBlankTile();

               // rightPos(Game.tiles.get(Game.blankTile));
                rightPos(tiles.get(game.getBlankTile()));

                // update blankTile properties
                game.setBlankTile(index);
                game.setBlankTileY(y);
                game.setBlankTileX(x);
            }
            if (endGame()) {

                if (user.getHighRank() < size * 1000 && !user.getEmail().equals("Guest")) {
                    user.setHighRank(size * 1000);
                    ObjectOutputStream os = null;
                    try {
                        os = new ObjectOutputStream(new FileOutputStream("../" + user.getUsername() + ".txt"));
                        os.writeObject(user);
                        os.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../fxmlFiles/result.fxml"));
                Parent levelsParent = null;
                try {
                    levelsParent = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Result result = loader.getController();
                result.setUser(user);

                Scene levelsScene = new Scene(levelsParent);
                Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
                window.setScene(levelsScene);
                window.show();
            }

        });

        getChildren().addAll(tile, text);
    }

    private boolean endGame() {
        
        for (int i = 0; i < tiles.size() ; i++) {
            if (tiles.get(i).getTextTile() != i + 1 )
                return false;
        }
        return true;
    }

    public static void rightPos(Tile t) {
        if (t.getTextTile() == t.index + 1)
           t.text.setFill(Color.rgb(23, 199, 53));
        else t.text.setFill(Color.rgb(35, 8, 66));
    }

    public void setBlankTile() {
        tile.setFill(Color.rgb(33, 0, 85));
        text.setFill(Color.rgb(33, 0, 85));
        text.setText("-");
    }

    public void setNormalTile(int number) {
        tile.setFill(Color.rgb(115, 61, 202));
        text.setText(number + "");
    }

    public int getTextTile() {
        if (text.getText().equals("-")) return (size*size);
        return Integer.parseInt(text.getText());
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
