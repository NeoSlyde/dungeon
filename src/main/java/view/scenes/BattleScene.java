package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.sound.sampled.Clip;

import eventhandlers.BattleSceneEventHandler;
import eventhandlers.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import model.entities.Player;
import model.entities.monsters.Monster;
import model.misc.Direction;
import model.misc.Vec2;
import model.world.World;
import view.DrawableVisitor;

public class BattleScene implements Scene {
    private EventHandler evtHandler;
    private World world;
    private SceneContext ctx;
    private Clip battleTheme;

    public BattleScene(SceneContext ctx, World world) {
        this.ctx = ctx;
        this.evtHandler = new BattleSceneEventHandler(world, ctx);

        this.world = world;
        battleTheme = ctx.getAudioPlayer().play(ctx.getAudioDataFactory().battleMenuTheme());
    }

    @Override
    public void onEnter() {
        battleTheme.start();
    }

    @Override
    public void onLeave() {
        battleTheme.stop();
    }

    @Override
    public Node getUI() {
        Player player = world.getPlayer();
        Monster enemy = player.getEnemy().get();

        Pane battleScene = new Pane();
        battleScene.setPrefSize(ctx.windowSize.x, ctx.windowSize.y);
        battleScene.getStylesheets().addAll("/style/battle.css");

        VBox combatOptions = new VBox();
        combatOptions.setAlignment(Pos.CENTER);
        combatOptions.setSpacing(10);
        combatOptions.setPrefSize(414, 203);
        combatOptions.setLayoutX(5);
        combatOptions.setLayoutY(420);
        combatOptions.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        VBox magicOptions = new VBox();
        magicOptions.setAlignment(Pos.CENTER);
        magicOptions.setSpacing(10);
        magicOptions.setPrefSize(414, 203);
        magicOptions.setLayoutX(5);
        magicOptions.setLayoutY(420);
        magicOptions.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        // Player

        Pane playerStatus = new Pane();
        playerStatus.setPrefSize(684, 203);
        playerStatus.setLayoutX(425);
        playerStatus.setLayoutY(420);
        playerStatus.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        Text playerName = new Text(player.getName());
        playerName.setLayoutX(50);
        playerName.setLayoutY(50);
        playerName.setFont(Font.font("Arctic", 40));
        playerName.setStyle("-fx-fill: white;");

        Text playerHP = new Text(String.format("%.0f/%.0f HP", player.getHealth(), player.getMaxHealth()));
        playerHP.setLayoutX(50);
        playerHP.setLayoutY(100);
        playerHP.setFont(Font.font("Arctic", 40));
        playerHP.setStyle("-fx-fill: green;");

        Text playerMP = new Text(String.format("%.0f/%.0f MP", player.getMana(), player.getMaxMana()));
        playerMP.setLayoutX(50);
        playerMP.setLayoutY(150);
        playerMP.setFont(Font.font("Arctic", 40));
        playerMP.setStyle("-fx-fill: blue;");

        playerStatus.getChildren().addAll(playerName, playerHP, playerMP);

        // Enemy

        Pane enemyStatus = new Pane();
        enemyStatus.setPrefSize(275, 140);
        enemyStatus.setLayoutX(840);
        enemyStatus.setLayoutY(5);
        enemyStatus.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        Text enemyName = new Text(enemy.getName());
        enemyName.setLayoutX(50);
        enemyName.setLayoutY(50);
        enemyName.setFont(Font.font("Arctic", 40));
        enemyName.setStyle("-fx-fill: white;");

        Text enemyHp = new Text(String.format("%.0f/%.0f HP", enemy.getHealth(), enemy.getMaxHealth()));
        enemyHp.setLayoutX(50);
        enemyHp.setLayoutY(100);
        enemyHp.setFont(Font.font("Arctic", 40));
        enemyHp.setStyle("-fx-fill: green;");
        enemyStatus.getChildren().addAll(enemyName, enemyHp);

        Button attack = new Button("ATTACK");
        combatOptions.getChildren().add(attack);
        attack.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
        });

        Button magic = new Button("MAGIC");
        combatOptions.getChildren().add(magic);
        magic.setOnAction(e -> {
            battleScene.getChildren().remove(combatOptions);
            battleScene.getChildren().add(magicOptions);
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
        });

        Button fire = new Button("FIRE: 20MP");
        magicOptions.getChildren().add(fire);
        fire.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
        });

        Button thunder = new Button("THUNDER: 30MP");
        magicOptions.getChildren().add(thunder);
        thunder.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
        });

        Button back = new Button("BACK");
        magicOptions.getChildren().add(back);
        back.setOnAction(e -> {
            battleScene.getChildren().add(combatOptions);
            battleScene.getChildren().remove(magicOptions);
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSoundEffect());
        });

        try {
            player.setMoving(false);
            player.setFacingDirection(Direction.RIGHT);
            Canvas playerCanvas = new Canvas();
            playerCanvas.setWidth(150);
            playerCanvas.setHeight(150);
            playerCanvas.setTranslateX(200);
            playerCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            player.draw(new DrawableVisitor(Vec2.ZERO,
                    new Vec2(playerCanvas.getWidth(), playerCanvas.getHeight()),
                    playerCanvas.getGraphicsContext2D(), ctx.getSpriteFactory()).scaledUp(0.5));

            enemy.setMoving(false);
            enemy.setFacingDirection(Direction.LEFT);
            Canvas enemyCanvas = new Canvas();
            enemyCanvas.setHeight(150);
            enemyCanvas.setWidth(150);
            enemyCanvas.setTranslateX(ctx.windowSize.x - 350);
            enemyCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            enemy.draw(new DrawableVisitor(Vec2.ZERO,
                    new Vec2(enemyCanvas.getWidth(), enemyCanvas.getHeight()),
                    enemyCanvas.getGraphicsContext2D(), ctx.getSpriteFactory()).scaledUp(0.5));

            battleScene.getChildren().addAll(playerCanvas, enemyCanvas);

            Image backgroundImage = new Image(new FileInputStream("src/main/resources/gui/battle.png"));
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            battleScene.setBackground(new Background(background));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        battleScene.getChildren().addAll(combatOptions, enemyStatus, playerStatus);

        return battleScene;
    }

    @Override
    public EventHandler getEventHandler() {
        return evtHandler;
    }

    public World getWorld() {
        return world;
    }

}
