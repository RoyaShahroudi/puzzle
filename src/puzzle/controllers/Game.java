package puzzle.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import puzzle.otherClassesAndStyles.Tile;
import puzzle.otherClassesAndStyles.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class Game implements Initializable {

    @FXML
    private AnchorPane boardAnchor;

    @FXML
    private Label usernameLabel;
    @FXML
    private Label levelLabel;
    @FXML
    private Label highRankLabel;


    private Group tileGroup = new Group();
    private static final Random RANDOM = new Random();

    public ArrayList<Tile> tiles = new ArrayList<>();
    private User user = new User();

    private int tileSize;
    private boolean gameOver;
    private int numTiles; // size * size
    private int size; // number of rows
    private boolean first = true;

    public static int blankTile;
    public static int blankTileX;
    public static int blankTileY;

    public void setSize(int size, int tileSize, String level, User user) {
        this.size = size;
        this.tileSize = tileSize;
        this.user = user;

        usernameLabel.setText(user.getUsername());

        levelLabel.setText(level);
        highRankLabel.setText(user.getHighRank() + "");
        numTiles = size * size;

        game();
    }

    @FXML
    private void levelsBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/levels.fxml"));
        Parent levelsParent = loader.load();

        Levels levels = loader.getController();
        levels.setUser(user);

        Scene levelsScene = new Scene(levelsParent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(levelsScene);
        window.show();
    }

    @FXML
    private void menuBtn(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../fxmlFiles/firstPage.fxml"));
        Parent parent = loader.load();

        Scene scene = new Scene(parent);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }



    private void game() {

        int index = -1;
        for (int i = 0; i < size ; i++) {
            for (int j = 0; j < size ; j++) {
                index++;
                Tile tile = new Tile(tileSize, index, j+1, i+1, size, tiles, user);
                tile.setTranslateX((j * tileSize));
                tile.setTranslateY((i * tileSize));
                tiles.add(tile);
                tile.setNormalTile(index + 1);
                if (i == size-1 && j == size-1){
                    tile.setBlankTile();

                    blankTile = index;
                    blankTileX = tile.getX();
                    blankTileY = tile.getY();
                }
                tileGroup.getChildren().add(tile);
            }
        }
        do {
            shuffle();
        }
        while (isSolvable());

        boardAnchor.getChildren().addAll(tileGroup);
    }

    private void shuffle() {

        for (int i = 0; i < (size*size)-1 ; i++) {
            int random = RANDOM.nextInt(size * size - 1);

            int temp = tiles.get(random).getTextTile();
            tiles.get(random).setNormalTile(tiles.get(i).getTextTile());
            tiles.get(i).setNormalTile(temp);
            Tile.rightPos(tiles.get(i));
            Tile.rightPos(tiles.get(random));
        }
    }

    private boolean isSolvable() {
        int countInversion = 0;

        for (int i = 0; i < size*size - 1 ; i++) {
            for (int j = 0; j < i ; j++) {
                if (tiles.get(j).getTextTile() > tiles.get(i).getTextTile())
                    countInversion++;
            }
        }
        return countInversion % 2 == 1;
    }

    public int getBlankTile() {
        return blankTile;
    }

    public void setBlankTile(int blankTile) {
        this.blankTile = blankTile;
    }

    public int getBlankTileX() {
        return blankTileX;
    }

    public void setBlankTileX(int blankTileX) {
        this.blankTileX = blankTileX;
    }

    public int getBlankTileY() {
        return blankTileY;
    }

    public void setBlankTileY(int blankTileY) {
        this.blankTileY = blankTileY;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
