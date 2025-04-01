/**
 *  A class that represents a deck and its associated methods
 */
public class Player {
   private String name; 
   private Deck deck;

   /**
    * A constructor that creates a player, assigns the name and creates a deck (that will be overwriten later on) 
    * @param name
    */
   public Player(String name) {
      this.name = name;
      this.deck = new Deck();
   }

   public String getName() {return name;}

   /**
    * TBH, I'm not sure if putting this method here complies with OOP concepts. Coming from a functional programming standpoint, I really struggle to grasp the relationships between objects while trying to encapsulate each class variables...
    * @return
    */
   public Deck getDeck() {
      return deck;
   }
}
