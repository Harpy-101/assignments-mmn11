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

    public void handleTurn() {
            if (player1.getDeck().size() == 0 || player2.getDeck().isEmpty()) {
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

    private int calcRank(Card card) {
        String ranks = "2345678910JQKA";
        return ranks.indexOf(card.getRank());
    }

    private void handleWar() {
        if (player1.getDeck().size() < 4 || player2.getDeck().size() < 4) {
            gameStatus = player1.getDeck().size() < 4 ? player2.getName() + "won the war!" : player1.getName() + "won the war!";
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
