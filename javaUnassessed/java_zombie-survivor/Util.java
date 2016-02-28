public class Util {

  public static Outcome calculateOutcome(double index, double biteChance,
      double hitChance, double destroyChance) {
    assert index >= 0 && index < 1: "index isn't in range [0, 1)";
    if (index < biteChance) {
      return Outcome.BITTEN;
    } else if (index < (biteChance + hitChance)) {
      return Outcome.HIT;
    }
    return Outcome.DESTROYED;

  }

  public static int findIndexGreaterThanOrEqualTo(int[] numbers, int target) {
    int n = 0;
    for (int num : numbers) {
     if (num >= target) {
       return n;
     }
    n++;
   }
    return -1;
  }

  public static void swap(Zombie[] zombies, int x, int y) {
   Zombie zombHolder = zombies[x];
   zombies[x] = zombies[y];
   zombies[y] = zombHolder;
  }

}
