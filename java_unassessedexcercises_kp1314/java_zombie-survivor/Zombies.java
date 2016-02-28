import java.util.Arrays;

public class Zombies {

  private final Zombie[] zombies;
  private int numberOfZombies;

  public Zombies(int maxZombies) {
    numberOfZombies = 0;
    zombies = new Zombie[maxZombies]; 
  }

  public int getNumberOfZombies() {
    return this.numberOfZombies;
  }

  public void addZombie(Zombie zombie) {
  assert numberOfZombies < zombies.length: "Can't store zombie!";
    int index = getNumberOfZombies();
    zombies[index] = zombie;
    numberOfZombies++;
  }

  public Zombie removeZombie(int zombieIndex) {
  assert (zombieIndex < numberOfZombies || 
          zombieIndex >= 0):"Must be a valid index";
    int prevNumOfZombies = numberOfZombies;
    Util.swap(zombies, zombieIndex, numberOfZombies-1); 
    numberOfZombies--;
    return zombies[prevNumOfZombies-1];
  }

  public void takeAllZombies(Zombies other) {
    for (int i = 0; i < other.getNumberOfZombies(); i++) {
      addZombie(other.removeZombie(i));
    }     
  }

  public String toString() {
    Zombie[] smaller = Arrays.copyOf(zombies, numberOfZombies);
    return Arrays.toString(smaller);
  }

}
