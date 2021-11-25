package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javax.sound.sampled.Clip;

import audio.AudioPlayer;
import audio.StandardAudioDataFactory;
import eventhandlers.EventHandler;
import eventhandlers.MainMenuSceneEventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import model.Room;
import model.World;
import model.entities.Player;
import model.misc.Vec2;

public class MainMenuScene implements Scene {

    private EventHandler evtHandler;

    private SceneContext ctx;
    Clip menuTheme;

    public MainMenuScene(SceneContext ctx) {
        this.evtHandler = new MainMenuSceneEventHandler();
        this.ctx = ctx;

        AudioPlayer audioPlayer = new AudioPlayer();

        menuTheme = audioPlayer.play(new StandardAudioDataFactory().MainMenuTheme());
    }

    @Override
    public void onEnter() {
    }

    @Override
    public void onLeave() {
    }

    @Override
    public Node getUI() {
        final Vec2 windowSize = new Vec2(16 * 70, 9 * 70);
        VBox menu = new VBox();
        menu.setPrefSize(windowSize.x, windowSize.y);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);

        menu.getStylesheets().addAll("/style/menu.css");

        var room = new Room();
        var player = new Player(room, new Vec2(1, 1));
        room.addEntity(player);

        try {
            Image logoImage = new Image(new FileInputStream("src/main/resources/gui/logo2.png"));
            ImageView logo = new ImageView(logoImage);

            menu.getChildren().add(logo);

            Image backgroundImage = new Image(new FileInputStream("src/main/resources/gui/bg.png"));
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            menu.setBackground(new Background(background));

        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        Button play = new Button("START A NEW GAME");
        menu.getChildren().add(play);
        play.setOnAction(e -> {
            ctx.switchScene(new WorldScene(ctx, new World(List.of(room), player)));
            menuTheme.stop();
        });

        Button quit = new Button("LEAVE THE GAME");

        Button goBack = new Button("GO BACK");

        Button musicOnOff = new Button("MUSIC: ON");
        Button invisible = new Button("HAHA YOU CAN'T SEE ME");
        invisible.setVisible(false);

        Button option = new Button("OPTIONS");
        menu.getChildren().add(option);

        option.setOnAction(e -> {
            menu.getChildren().remove(option);
            menu.getChildren().remove(play);
            menu.getChildren().remove(quit);

            menu.getChildren().add(musicOnOff);
            menu.getChildren().add(invisible);
            menu.getChildren().add(goBack);
        });

        goBack.setOnAction(e -> {
            menu.getChildren().add(play);
            menu.getChildren().add(option);
            menu.getChildren().add(quit);

            menu.getChildren().remove(musicOnOff);
            menu.getChildren().remove(invisible);
            menu.getChildren().remove(goBack);
        });

        musicOnOff.setOnAction(e -> {
            if (musicOnOff.getText().equals("MUSIC: ON")) {
                musicOnOff.setText("MUSIC: OFF");
                menuTheme.stop();
            } else {
                musicOnOff.setText("MUSIC: ON");
                menuTheme.start();
            }
        });

        menu.getChildren().add(quit);

        quit.setOnAction(e -> System.exit(0));

        return menu;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

}
