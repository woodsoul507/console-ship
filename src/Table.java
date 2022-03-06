import java.util.HashSet;
import java.util.List;

public class Table {
  final private String blankTile = " - ";
  final private String failTile = " O ";
  final private String successTile = " X ";
  final private Ship ship = new Ship();

  public Table() {
  }

  public void drawingGrid(HashSet<Coordinate> shootRecord, List<HashSet<Coordinate>> existingShips) {
    String[] colsHead = { "   ", " A ", " B ", " C ", " D ", " E ", " F ", " G ", " H ", " I ", " J " };
    for (int i = 0; i < 11; i++) {
      System.out.print(colsHead[i]);
    }
    System.out.println();
    System.out.print("  ");
    for (int i = 0; i < 32; i++) {
      System.out.print("=");
    }
    System.out.println();
    for (int i = 0; i < 10; i++) {
      System.out.print(i);
      System.out.print(" |");
      for (int j = 0; j < 10; j++) {
        Coordinate newCoordinate = new Coordinate(j, i);
        Boolean exists = ship.checkExistingShipPoint(newCoordinate, existingShips);
        Boolean shootResolution = shootRecord.contains(newCoordinate);
        if (exists && shootResolution) {
          System.out.print(successTile);
        } else if (shootResolution) {
          System.out.print(failTile);
        } else {
          System.out.print(blankTile);
        }
      }
      System.out.println("|");
    }
    System.out.print("  ");
    for (int i = 0; i < 32; i++) {
      System.out.print("=");
    }
    System.out.println();
  }
}