package view.scenes;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import animatefx.animation.BounceOutLeft;
import animatefx.animation.BounceOutRight;
import animatefx.animation.FadeIn;
import animatefx.animation.FadeOut;
import animatefx.animation.FadeOutRightBig;
import animatefx.animation.Flash;
import eventhandlers.BattleSceneEventHandler;
import eventhandlers.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import model.attacks.FireAttack;
import model.attacks.PhysicalAttack;
import model.attacks.ThunderAttack;
import model.entities.LivingEntity;
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
    private MediaPlayer battleTheme;

    public BattleScene(SceneContext ctx, World world) {
        this.ctx = ctx;
        this.evtHandler = new BattleSceneEventHandler(world, ctx);

        this.world = world;
    }

    @Override
    public void onEnter() {
        battleTheme = ctx.getAudioPlayer().play(ctx.getAudioDataFactory().battleMenuTheme());
        ctx.getAudioPlayer().play(ctx.getAudioDataFactory().encounterSFX());
        battleTheme.play();
    }

    @Override
    public void onLeave() {
        battleTheme.stop();
        if (world.getPlayer().getEnemy().map(e -> e.getHealth()).orElse(0.0) <= 0)
            world.getPlayer().clearEnemy();
    }

    @Override
    public Node getUI() {
        Player player = world.getPlayer();
        Monster enemy = player.getEnemy().get();
        Canvas playerCanvas = new Canvas();
        Canvas enemyCanvas = new Canvas();
        ImageView fireBall = new ImageView();
        ImageView thunderAttack = new ImageView();
        Font font = Font.loadFont("file:src/main/resources/font/Arctic.ttf", 40);

        Pane battleScene = new Pane();
        battleScene.setPrefSize(ctx.windowSize.x, ctx.windowSize.y);
        battleScene.getStylesheets().addAll("/style/battle.css");

        Flash battleSceneOpen = new Flash(battleScene);
        battleSceneOpen.setSpeed(2);
        battleSceneOpen.play();

        FadeOut sceneFadeOut = new FadeOut(battleScene);
        sceneFadeOut.setDelay(Duration.seconds(2.5));

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

        try {
            player.setMoving(false);
            player.setFacingDirection(Direction.RIGHT);
            playerCanvas.setWidth(150);
            playerCanvas.setHeight(150);
            playerCanvas.setTranslateX(200);
            playerCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            player.draw(new DrawableVisitor(Vec2.ZERO,
                    new Vec2(playerCanvas.getWidth(), playerCanvas.getHeight()),
                    playerCanvas.getGraphicsContext2D(), ctx.getGraphicsFactory()).scaledUp(0.5));

            enemy.setMoving(false);
            enemy.setFacingDirection(Direction.LEFT);
            enemyCanvas.setHeight(150);
            enemyCanvas.setWidth(150);
            enemyCanvas.setTranslateX(ctx.windowSize.x - 350);
            enemyCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            enemy.draw(new DrawableVisitor(Vec2.ZERO,
                    new Vec2(enemyCanvas.getWidth(), enemyCanvas.getHeight()),
                    enemyCanvas.getGraphicsContext2D(), ctx.getGraphicsFactory()).scaledUp(0.5));

            Image fireBallImage = new Image(new FileInputStream("src/main/resources/magic/fire.gif"));

            fireBall.setImage(fireBallImage);

            fireBall.setX(310);
            fireBall.setY(ctx.windowSize.y / 2 - 50);
            fireBall.setVisible(false);

            Image thunderImage = new Image(new FileInputStream("src/main/resources/magic/thunder.gif"));

            thunderAttack.setImage(thunderImage);

            thunderAttack.setFitHeight(thunderImage.getHeight() * 2);

            thunderAttack.setX(ctx.windowSize.x - 380);
            thunderAttack.setVisible(false);

            battleScene.getChildren().addAll(playerCanvas, enemyCanvas, fireBall, thunderAttack);

            Image backgroundImage = new Image(new FileInputStream("src/main/resources/gui/battle.png"));
            BackgroundImage background = new BackgroundImage(backgroundImage, null, null, null, null);
            battleScene.setBackground(new Background(background));

        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }

        // Player

        Pane playerStatus = new Pane();
        playerStatus.setPrefSize(684, 203);
        playerStatus.setLayoutX(425);
        playerStatus.setLayoutY(420);
        playerStatus.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");

        Text playerName = new Text(player.getName());
        playerName.setLayoutX(50);
        playerName.setLayoutY(50);
        playerName.setFont(font);
        playerName.setStyle("-fx-fill: white;");

        Text playerHP = new Text();
        setHpText(playerHP, player);
        playerHP.setLayoutX(50);
        playerHP.setLayoutY(100);
        playerHP.setFont(font);
        playerHP.setStyle("-fx-fill: green;");

        Text playerMP = new Text();
        setMpText(playerMP, player);
        playerMP.setLayoutX(50);
        playerMP.setLayoutY(150);
        playerMP.setFont(font);
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
        enemyName.setFont(font);
        enemyName.setStyle("-fx-fill: white;");

        Text enemyHp = new Text();
        setHpText(enemyHp, enemy);
        enemyHp.setLayoutX(20);
        enemyHp.setLayoutY(100);
        enemyHp.setFont(font);
        enemyHp.setStyle("-fx-fill: green;");
        enemyStatus.getChildren().addAll(enemyName, enemyHp);

        BounceOutRight playerAttackAnimation = new BounceOutRight(playerCanvas);
        FadeOutRightBig fireMagicAnimation = new FadeOutRightBig(fireBall);
        BounceOutLeft enemyAttackAnimation = new BounceOutLeft(enemyCanvas);
        FadeIn thunderMagicAnimation = new FadeIn(fireBall);
        FadeOut enemyFadeOut = new FadeOut(enemyCanvas);
        FadeOut playerFadeOut = new FadeOut(playerCanvas);

        Button attack = new Button("ATTACK");
        Button magic = new Button("MAGIC");

        combatOptions.getChildren().add(attack);
        attack.setOnAction(e -> {
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
            playerAttackAnimation.play();
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().attackSFX());
            battleScene.getChildren().remove(combatOptions);
        });

        enemyAttackAnimation.setDelay(Duration.seconds(0.5));
        enemyAttackAnimation.setOnFinished(d -> {
            enemyCanvas.setTranslateX(ctx.windowSize.x - 350);
            enemyCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            enemy.attack(player, new PhysicalAttack());
            updateAfterEnemyAttack(playerHP, player, playerFadeOut);
            if (player.getHealth() > 0) {
                battleScene.getChildren().add(combatOptions);
            }
        });
        enemyAttackAnimation.setResetOnFinished(true);

        playerAttackAnimation.setOnFinished(d -> {
            playerCanvas.setTranslateX(200);
            playerCanvas.setTranslateY(ctx.windowSize.y / 2 - 50);
            player.attack(enemy, new PhysicalAttack());
            updateAfterPlayerAttack(playerMP, enemyHp, player, enemy, enemyFadeOut, sceneFadeOut);
            if (enemy.getHealth() > 0) {
                enemyAttackAnimation.play();
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().attackSFX());
            }
        });
        playerAttackAnimation.setResetOnFinished(true);

        // Fire

        fireMagicAnimation.setOnFinished(d -> {
            fireBall.setX(310);
            fireBall.setY(ctx.windowSize.y / 2 - 50);
            fireBall.setVisible(false);

            if (enemy.getHealth() > 0) {
                enemyAttackAnimation.play();
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().attackSFX());
            }
        });

        // Thunder

        thunderMagicAnimation.setOnFinished(d -> {
            thunderAttack.setX(ctx.windowSize.x - 380);
            thunderAttack.setVisible(false);

            if (enemy.getHealth() > 0) {
                enemyAttackAnimation.play();
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().attackSFX());
            }
        });

        combatOptions.getChildren().add(magic);
        magic.setOnAction(e -> {
            battleScene.getChildren().remove(combatOptions);
            battleScene.getChildren().add(magicOptions);
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
        });

        Button fire = new Button("FIRE: 20MP");
        magicOptions.getChildren().add(fire);
        fire.setOnAction(e -> {
            if (!player.canDoAttack(new FireAttack())) {
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().errorSFX());
            } else {
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
                fireBall.setVisible(true);
                battleScene.getChildren().remove(magicOptions);
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().fireSFX());
                player.attack(enemy, new FireAttack());
                updateAfterPlayerAttack(playerMP, enemyHp, player, enemy, enemyFadeOut, sceneFadeOut);

                fireMagicAnimation.play();
            }
        });

        Button thunder = new Button("THUNDER: 30MP");
        magicOptions.getChildren().add(thunder);
        thunder.setOnAction(e -> {
            if (!player.canDoAttack(new ThunderAttack())) {
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().errorSFX());
            } else {
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
                thunderAttack.setVisible(true);
                battleScene.getChildren().remove(magicOptions);
                ctx.getAudioPlayer().play(ctx.getAudioDataFactory().fireSFX());
                player.attack(enemy, new ThunderAttack());
                updateAfterPlayerAttack(playerMP, enemyHp, player, enemy, enemyFadeOut, sceneFadeOut);

                thunderMagicAnimation.play();
            }
        });

        Button back = new Button("BACK");
        magicOptions.getChildren().add(back);
        back.setOnAction(e -> {
            battleScene.getChildren().add(combatOptions);
            battleScene.getChildren().remove(magicOptions);
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().selectSFX());
        });

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

    private void setHpText(Text t, LivingEntity e) {
        t.setText(String.format("%.0f/%.0f HP", e.getHealth(), e.getMaxHealth()));
    }

    private void setMpText(Text t, LivingEntity e) {
        t.setText(String.format("%.0f/%.0f MP", e.getMana(), e.getMaxMana()));
    }

    private void updateAfterPlayerAttack(Text playerMP, Text enemyHp, Player player, Monster enemy,
            FadeOut enemyFadeOut, FadeOut sceneFadeOut) {
        Pane tempPane = new Pane();
        setHpText(enemyHp, enemy);
        setMpText(playerMP, player);
        FadeIn fadeIn = new FadeIn(tempPane);
        fadeIn.setDelay(Duration.seconds(3.2));
        fadeIn.setOnFinished(d -> {
            enemy.getRoom().removeEntity(enemy);
            ctx.switchScene(new WorldScene(ctx, world));
        });
        if (enemy.getHealth() <= 0) {
            battleTheme.stop();
            ctx.getAudioPlayer().play(ctx.getAudioDataFactory().winSFX());
            enemyFadeOut.play();
            fadeIn.play();
            sceneFadeOut.play();
        }
    }

    private void updateAfterEnemyAttack(Text playerHP, Player player, FadeOut playerFadeOut) {
        setHpText(playerHP, player);
        playerFadeOut.setSpeed(0.6);
        playerFadeOut.setOnFinished(value -> {
            ctx.switchScene(new DeathScene(ctx));
        });

        if (player.getHealth() <= 0) {
            battleTheme.stop();
            playerFadeOut.play();
        }
    }
}
