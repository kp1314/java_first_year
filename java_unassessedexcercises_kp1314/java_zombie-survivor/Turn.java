public class Turn {

  private static final int NUM_EASY_ZOMBIES = 20;
  private static final int NUM_MEDIUM_ZOMBIES = 20;
  private static final int NUM_HARD_ZOMBIES = 10;

  private static final int MAX_NUM_ZOMBIES = NUM_EASY_ZOMBIES
      + NUM_MEDIUM_ZOMBIES + NUM_HARD_ZOMBIES;

  private static final int BITE_LIMIT = 6;
  private static final int HAND_SIZE = 5;

  private final int player;

  private final Zombies zombiePopulation = new Zombies(MAX_NUM_ZOMBIES);
  private final Zombies destroyed = new Zombies(MAX_NUM_ZOMBIES);
  private final Zombies hit = new Zombies(HAND_SIZE);
  private final Zombies bitten = new Zombies(MAX_NUM_ZOMBIES);

  public Turn(int player) {
    this.player = player;
    for (int i = 0; i < NUM_EASY_ZOMBIES; i++) {
      zombiePopulation.addZombie(Zombie.EASY);
    }
    for (int j = 0; j < NUM_MEDIUM_ZOMBIES; j++) {
      zombiePopulation.addZombie(Zombie.MEDIUM);
    }
    for (int k = 0; k < NUM_HARD_ZOMBIES; k++) {
      zombiePopulation.addZombie(Zombie.HARD);
    } 
  }

  public int getCurrentPlayer() {
   return player;
  }

  public boolean hasBeenBittenTooManyTimes() {
    return (bitten.getNumberOfZombies() >= BITE_LIMIT);
  }

  public int getCurrentScore() {
    if (!hasBeenBittenTooManyTimes()) {
      return destroyed.getNumberOfZombies();
    }
    return 0;
  }

  private void addZombies(Zombies hand, int extraZombiesNeeded) {
    if (zombiePopulation.getNumberOfZombies() <= extraZombiesNeeded) {
      hand.takeAllZombies(zombiePopulation);
    } else {
      for (int i = 0; i < extraZombiesNeeded; i++) {
      int random = (int)((Math.random())*(zombiePopulation.getNumberOfZombies()));
      hand.addZombie(zombiePopulation.removeZombie(random));
      }
    }
  }

  private Outcome[] getOutcomesForHand(Zombies hand) {
    Outcome[] outcomes = new Outcome[hand.getNumberOfZombies()];
    int number = hand.getNumberOfZombies();
    for (int i = 0; i < number; i++) {
      Zombie zombieOut = hand.removeZombie(i);
      switch (zombieOut.randomOutcome()) {
        case BITTEN:    outcomes[i] = Outcome.BITTEN;
                        bitten.addZombie(zombieOut);
                        break;
        case HIT:       outcomes[i] = Outcome.HIT;
                        hit.addZombie(zombieOut);
                        break;
        case DESTROYED: outcomes[i] = Outcome.DESTROYED;
                        destroyed.addZombie(zombieOut);
                        break;
        default: continue;
      } 
     }
     return outcomes;
  }

  public Outcome[] drawAndGetOutcomes() {
    Zombies playersHand = new Zombies(HAND_SIZE);
    playersHand.takeAllZombies(hit);
    addZombies(playersHand, (HAND_SIZE - playersHand.getNumberOfZombies()));
    return getOutcomesForHand(playersHand); 
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Destroyed: ");
    sb.append(destroyed);
    sb.append("\n");
    sb.append("Hits: ");
    sb.append(hit);
    sb.append("\n");
    sb.append("Bites: ");
    sb.append(bitten);
    return sb.toString();
  }

}
