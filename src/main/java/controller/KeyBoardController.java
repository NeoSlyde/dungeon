package controller;

import java.util.Scanner;

import model.entities.Player;

public class KeyboardController {
  Player player;

  public KeyboardController(Player player) {
    this.player = player;
  }

  public void read() {
    @SuppressWarnings("resource")
    Scanner scanner = new Scanner(System.in);
    while (true) {
      System.out.println("enter your move");
      switch (scanner.next()) {
      case "z":
        break;
      case "q":
        break;
      case "s":
        break;
      case "d":
        break;
      default:
        System.out.println("Invalid move");
        break;
      }
    }
  }
}
