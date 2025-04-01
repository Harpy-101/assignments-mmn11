/**
 *  A class that represents a deck and its associated methods
 */
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
     private ArrayList<Card> deck;

     /**
      * A constructor that builds the original deck containing all 52 cards  
      */
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

     /**
      * This function creates a new (smaller) deck from the start index to the end index and returns it. 
      * @param start Starting index
      * @param end Ending index
      * @return the new smaller deck
      */
     public Deck setDeck(int start, int end) {
          Deck subDeck = new Deck();
          subDeck.setDeck(new ArrayList<>(deck.subList(start, end)));
          return subDeck;
     }

     /**
      * This function takes in the winner's deck and unloads the war pile into his deck
      * @param pile the winner's pile
      */
     public void unloadWarPile(ArrayList<Card> pile) {
          for (Card card : pile) {
               this.setCard(card);
          }
          pile.clear();
     }

     /**
      * This function returns the drawn card to the pile. This function is here to handle the case of repeated wars, that way the card would get re-drawn and trigger the new war with the same logic until we find a winner.
      * @param card the card thats getting re-inserted
      */
     public void returnCarToTheDeck(Card card) {
          this.deck.add(0, card);
     }
}
