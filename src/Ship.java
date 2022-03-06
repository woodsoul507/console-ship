import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Ship {
  final private Random random = new Random();

  public Ship() {
  }

  public void generator(int length, List<HashSet<Coordinate>> existingShips) {
    HashSet<Coordinate> newShip = new HashSet<Coordinate>();
    int axisOrientation = random.nextInt(2); // 0 = vertical, 1 horizontal
    Coordinate shipInitialPoint = settingShipInitialPosition(length, axisOrientation, existingShips);
    for (int i = 0; i < length; i++) {
      Coordinate newPoint;
      if (axisOrientation == 0) {
        newPoint = new Coordinate(shipInitialPoint.col(), shipInitialPoint.row() + i);
      } else {
        newPoint = new Coordinate(shipInitialPoint.col() + i, shipInitialPoint.row());
      }
      newShip.add(newPoint);
      Boolean exists = checkExistingShipPoint(newPoint, existingShips);
      if (exists) {
        newShip.clear();
        shipInitialPoint = settingShipInitialPosition(length, axisOrientation, existingShips);
        i = -1; // Rebooting the for loop
      }
    }
    existingShips.add(newShip);
  }

  public Boolean checkExistingShipPoint(Coordinate newShipPoint, List<HashSet<Coordinate>> existingShips) {
    for (int i = 0; i < existingShips.size(); i++) {
      Boolean exists = existingShips.get(i).contains(newShipPoint);
      if (exists) {
        return true;
      }
    }
    return false;
  }

  private Coordinate settingShipInitialPosition(int length, int axisOrientation,
      List<HashSet<Coordinate>> existingShips) {
    // Determining ship's initial position
    Coordinate shipInitialPoint;
    while (true) {
      shipInitialPoint = new Coordinate(random.nextInt(10), random.nextInt(10));
      if (axisOrientation == 0 && shipInitialPoint.row() + length > 9) {
        shipInitialPoint = new Coordinate(shipInitialPoint.col(), shipInitialPoint.row() - length);
      }
      if (axisOrientation == 1 && shipInitialPoint.col() + length > 9) {
        shipInitialPoint = new Coordinate(shipInitialPoint.col() - length, shipInitialPoint.row());
      }
      Boolean exists = checkExistingShipPoint(shipInitialPoint, existingShips);
      if (!exists) {
        return shipInitialPoint;
      }
    }
  }
}