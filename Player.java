public class Player {
   private String name; 
   private Deck deck;

   public Player(String name) {
        this.name = name;
        this.deck = new Deck();
   }

   public String getName() {return name;}

   public Deck getDeck() {
      return deck;
   }
}
