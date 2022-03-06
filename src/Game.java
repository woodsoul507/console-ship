import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Game {
  final private Ship ship = new Ship();
  final private Table table = new Table();
  final private HashSet<Coordinate> shootingRecord = new HashSet<Coordinate>();
  final private List<HashSet<Coordinate>> existingShips = new ArrayList<HashSet<Coordinate>>();

  public Game() {
  }

  public void drawGame() {
    table.drawingGrid(shootingRecord, existingShips);
  }

  public void start() {
    ship.generator(5, existingShips);
    ship.generator(4, existingShips);
    ship.generator(4, existingShips);
  }

  public void restart() {
    existingShips.clear();
    shootingRecord.clear();
    start();
  }

  /*
   * return 0 = invalid input
   * return 1 = repeated input
   * return 2 = miss
   * return 3 = hit
   */
  public int shoot(String shoot) {
    if (!shoot.matches("^[a-jA-J][0-9]$")) {
      return 0;
    }

    String col = Character.toString(shoot.charAt(0));
    String row = Character.toString(shoot.charAt(1));

    Coordinate newShootPosition = new Coordinate(letterToNumberConverting(col), Integer.valueOf(row));

    if (shootingRecord.contains(newShootPosition)) {
      return 1;
    }

    shootingRecord.add(newShootPosition);

    if (!ship.checkExistingShipPoint(newShootPosition, existingShips)) {
      return 2;
    }

    return 3;
  }

  public int getMissingShoots() {
    int missingShoots = 0;
    Iterator<Coordinate> value = shootingRecord.iterator();
    while (value.hasNext()) {
      Boolean exists = ship.checkExistingShipPoint(value.next(), existingShips);
      if (!exists) {
        missingShoots++;
      }
    }
    return missingShoots;
  }

  public int getTotalShoots() {
    return shootingRecord.size();
  }

  public int getSunkShips() {
    int sunkShips = 0;
    for (int i = 0; i < existingShips.size(); i++) {
      Iterator<Coordinate> value = shootingRecord.iterator();
      int hits = 0;
      while (value.hasNext()) {
        Boolean wasHit = ship.checkExistingShipPoint(value.next(), List.of(existingShips.get(i)));
        if (wasHit) {
          hits++;
        }
      }
      if (hits == existingShips.get(i).size()) {
        sunkShips++;
      }
    }
    return sunkShips;
  }

  private int letterToNumberConverting(String colLetter) {
    int colNumber = 0;
    switch (colLetter.toLowerCase()) {
      case "a":
        colNumber = 0;
        break;
      case "b":
        colNumber = 1;
        break;
      case "c":
        colNumber = 2;
        break;
      case "d":
        colNumber = 3;
        break;
      case "e":
        colNumber = 4;
        break;
      case "f":
        colNumber = 5;
        break;
      case "g":
        colNumber = 6;
        break;
      case "h":
        colNumber = 7;
        break;
      case "i":
        colNumber = 8;
        break;
      case "j":
        colNumber = 9;
        break;
      default:
        break;
    }

    return colNumber;
  }

}
