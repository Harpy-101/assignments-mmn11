/**
 * A class that represents a card and its associated methods
 */
public class Card {
   private String suit;
   private String rank;
 
   /**
  * This method constructs a card from the rank and suit
  * @param suit clubs (♣), diamonds (♦), hearts (♥) or spades (♠)
  * @param rank Ace, 2, 3, 4, 5, 6, 7, 8, 9, 10, Jack, Queen, King
  */
   public Card(String suit, String rank) {
      this.suit = suit;
      this.rank = rank;
   }

   public String getSuit() {return suit;}

   public String getRank() {return rank;}

}
