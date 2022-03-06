import java.util.Scanner;

public class UI {
  final private Scanner scanner = new Scanner(System.in);

  public UI() {
  }

  public void uiLoop() {
    Game game = new Game();
    game.start();

    gretting();
    int sunkShipsCounter = 0;

    while (true) {
      game.drawGame();
      int sunkShips = game.getSunkShips();
      int totalShoots = game.getTotalShoots();
      int missingShoots = game.getMissingShoots();
      int onTargetShoots = totalShoots - missingShoots;
      hud(sunkShips, totalShoots, missingShoots, onTargetShoots);

      System.out.println(" Type a combination of column and row to\n shoot on the aiming tile.");
      System.out.print(" (ie: A4, c8, J6, b0...) ");
      String shoot = scanner.nextLine();

      int shootResult = game.shoot(shoot);

      if (shootResult == 0) {
        System.out.println();
        System.out.println("  Invalid shoot input. Try again.");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();
        continue;
      } else if (shootResult == 1) {
        System.out.println();
        System.out.println("  Repeated shoot input. Try again.");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();
        continue;
      } else if (shootResult == 2) {
        System.out.println();
        System.out.println("  You miss the blank.");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();
      } else {
        System.out.println();
        System.out.println("  You hit the blank!");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();
      }

      sunkShips = game.getSunkShips();
      totalShoots = game.getTotalShoots();
      missingShoots = game.getMissingShoots();
      onTargetShoots = totalShoots - missingShoots;

      if (sunkShips > sunkShipsCounter) {
        System.out.println();
        System.out.println("  You sunk an enemy's ship!");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();
        sunkShipsCounter++;
      }

      if (game.getSunkShips() >= 3) {
        System.out.println();
        System.out.println("  YOU WIN!!!");
        System.out.println();
        System.out.println();
        pressEnterKeyToContinue();

        Boolean repeateGame;

        while (true) {
          System.out.println("  Want to play again?       Type \"yes\".");
          System.out.println("  No. I want exit the game. Type \"no\".");
          String wantNewGame = scanner.nextLine();

          if (wantNewGame.toLowerCase().compareTo("yes") == 0
              || wantNewGame.toLowerCase().compareTo("y") == 0) {
            game.restart();
            repeateGame = true;
            break;
          } else if (wantNewGame.toLowerCase().compareTo("no") == 0
              || wantNewGame.toLowerCase().compareTo("n") == 0) {
            repeateGame = false;
            break;
          } else {
            System.out.println("  Invalid input. Try again.");
            System.out.println();
            continue;
          }
        }

        if (repeateGame) {
          continue;
        } else {
          break;
        }

      }
    }

    scanner.close();
  }

  public void hud(int sunkShips, int totalShoots, int missingShoots, int onTargetShoots) {
    System.out.println("                          Total/Miss/Hit");
    System.out.println(" Sunk Ships = " + sunkShips + "  Shoots =   " + totalShoots + "    " + missingShoots
        + "    " + onTargetShoots);
    System.out.println();
  }

  public void gretting() {
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println("                  Welcome to ConsoleShip!");
    System.out.println("  The console version of the famous Battleship table game.");
    System.out.println();
    System.out.println();
    System.out.println();
    System.out.println();
    pressEnterKeyToContinue();
  }

  public void pressEnterKeyToContinue() {
    System.out.println("Press Enter key to continue...");
    scanner.nextLine();
    return;
  }
}
