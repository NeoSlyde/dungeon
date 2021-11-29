package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.Clip;

import eventhandlers.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import model.world.WorldFactory;

public class MainMenuScene implements Scene {
    private WorldFactory worldFactory;
    private EventHandler evtHandler;

    private SceneContext ctx;
    private Clip menuTheme;

    public MainMenuScene(SceneContext ctx, WorldFactory worldFactory) {
        this.worldFactory = worldFactory;
        this.evtHandler = new EventHandler() {
        };
        this.ctx = ctx;

        menuTheme = ctx.getAudioPlayer().play(ctx.getAudioDataFactory().mainMenuTheme());
    }

    @Override
    public Node getUI() {
        VBox menu = new VBox();
        menu.setPrefSize(ctx.windowSize.x, ctx.windowSize.y);
        menu.setAlignment(Pos.CENTER);
        menu.setSpacing(10);

        menu.getStylesheets().addAll("/style/menu.css");

        try {
            Image logoImage = new Image(new FileInputStream("src/main/resources/gui/logo2.png"));
            ImageView logo = new ImageView(logoImage);

            menu.getChildren().add(logo);

            Image backgroundImage = new Image(new FileInputStream("src/main/resources/gui/bg.png"));
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            menu.setBackground(new Background(background));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        Button play = new Button("START A NEW GAME");
        menu.getChildren().add(play);
        play.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
            ctx.switchScene(new WorldScene(ctx, worldFactory.generate()));
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
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
            menu.getChildren().remove(option);
            menu.getChildren().remove(play);
            menu.getChildren().remove(quit);

            menu.getChildren().add(musicOnOff);
            menu.getChildren().add(invisible);
            menu.getChildren().add(goBack);
        });

        goBack.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().closeSoundEffect());
            menu.getChildren().add(play);
            menu.getChildren().add(option);
            menu.getChildren().add(quit);

            menu.getChildren().remove(musicOnOff);
            menu.getChildren().remove(invisible);
            menu.getChildren().remove(goBack);
        });

        musicOnOff.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
            if (musicOnOff.getText().equals("MUSIC: ON")) {
                musicOnOff.setText("MUSIC: OFF");
                menuTheme.stop();
            } else {
                musicOnOff.setText("MUSIC: ON");
                menuTheme.start();
            }
        });

        menu.getChildren().add(quit);

        quit.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().closeSoundEffect());
            System.exit(0);
        });

        return menu;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

}
