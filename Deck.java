import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private ArrayList<Card> deck;

   public Deck() {
        deck = new ArrayList<>();
        String[] suits = {"♥", "♦", "♣", "♠"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        for (String suit : suits) {
            for (String rank : ranks) {
                deck.add(new Card(suit, rank));
            }
        }

   }

   public void setDeck(ArrayList<Card> deck) {
          this.deck = deck;
   }

   public ArrayList<Card> getDeck() {
     return deck;
   }

   public Card getCard() {
        return deck.isEmpty() ? null : deck.remove(0);
   }

   public void setCard(Card card) {
        deck.add(card);
   }

   public void shuffelDeck() {
        Collections.shuffle(deck);
   }

   public int size() {
     return deck.size();
   }

   public boolean isEmpty() {
     return deck.isEmpty();
   } 

   public Deck setDeck(int start, int end) {
          Deck subDeck = new Deck();
          subDeck.setDeck(new ArrayList<>(deck.subList(start, end)));
          return subDeck;
     }

     public void unloadWarPile(ArrayList<Card> pile) {
          for (Card card : pile) {
               this.setCard(card);
          }
          pile.clear();
     }

     public void returnCarToTheDeck(Card card) {
          this.deck.add(0, card);
     }
}
