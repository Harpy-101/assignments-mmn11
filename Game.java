import java.util.ArrayList;

public class Game { 
    private Player player1; 
    private Player player2;
    private Card player1CurrCard;
    private Card player2CurrCard;
    private ArrayList<Card> warPile;
    private String gameStatus;
    
    public Game() {
        Deck deck = new Deck();
        deck.shuffelDeck();
        warPile = new ArrayList<>();

        this.player1 = new Player("Player 1");
        this.player2 = new Player("Player 2");

        player1.getDeck().setDeck(new ArrayList<>(deck.getDeck().subList(0, 26)));
        player2.getDeck().setDeck(new ArrayList<>(deck.getDeck().subList(26, 52)));
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public Card getPlayer1CurrCard() {
        return player1CurrCard;
    }

    public Card getPlayer2CuCard() {
        return player2CurrCard;
    }

    /**
     * This function handles each turn based on the three potential outcomes; player 1 won, player 2 won, war. 
     */
    public void handleTurn() {
        if (player1.getDeck().size() == 0 || player2.getDeck().isEmpty()) {
            gameStatus = player1.getDeck().size() == 0 ? player2.getName() + "won the war!" : player1.getName() + "won the war!";
            return;
        }

        this.player1CurrCard = player1.getDeck().getCard();
        this.player2CurrCard = player2.getDeck().getCard();

        int res = calcRank(player1CurrCard) - calcRank(player2CurrCard);

        if (res > 0) {
            player1.getDeck().setCard(player1CurrCard);
            player1.getDeck().setCard(player2CurrCard);
            this.gameStatus = "Player 1 won the round";
        }
        else if (res < 0) {
            player2.getDeck().setCard(player1CurrCard);
            player2.getDeck().setCard(player2CurrCard);
            this.gameStatus = "Player 2 won the round";
        }
        else {
            handleWar();
            this.gameStatus = "WAR!";
        }
    }

    /**
     * This function determines the numerical value of a given card
     */
    private int calcRank(Card card) {
        String ranks = "2345678910JQKA";
        return ranks.indexOf(card.getRank());
    }

    /**
     * This function handles the case where the players are at war. It draws 2 cards from each player and adds them into a war pile. It then draws another card from each and compares them. If any of the players won, he gets the pile and the extra two cards that were drawn and compared. But, if there is another war, the last 2 cards a re-inserted to the start of their respective decks to be re-drawn by the next button press (which will trigger another war, re-calling the function until we get a winner). 
     */
    private void handleWar() {
        if (player1.getDeck().size() < 4 || player2.getDeck().size() < 4) {
            gameStatus = player1.getDeck().size() < 4 ? player2.getName() + "won the war!" : player1.getName() + "won the war!";
            return;
        }
        else {
            for (int i = 0; i < 2; i++) {
                warPile.add(player1.getDeck().getCard());
                warPile.add(player2.getDeck().getCard());
            }
            
            warPile.add(player1CurrCard);
            warPile.add(player2CurrCard);
            Card p1LastCard = player1.getDeck().getCard(); 
            Card p2LastCard = player2.getDeck().getCard();
            player1CurrCard = p1LastCard;
            player2CurrCard = p2LastCard;

            int res = calcRank(player1CurrCard) - calcRank(player2CurrCard);

            if (res > 0) {
                player1.getDeck().unloadWarPile(warPile);
                player1.getDeck().setCard(player1CurrCard);
                player1.getDeck().setCard(player2CurrCard);
            }

            else if (res < 0) {
                player2.getDeck().unloadWarPile(warPile);
                player2.getDeck().setCard(player1CurrCard);
                player2.getDeck().setCard(player2CurrCard);
            }
            else {
                player1.getDeck().returnCarToTheDeck(player1CurrCard);
                player2.getDeck().returnCarToTheDeck(player2CurrCard);
            }
        }
    }
}
