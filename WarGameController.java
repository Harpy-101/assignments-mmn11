import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class WarGameController {

    private Game game;

    @FXML
    private Canvas canvas1;

    @FXML
    private Canvas canvas2;

    @FXML
    public void initialize() {
        drawCardButton.setDisable(false);
        game = new Game();
    }

    @FXML
    private Button drawCardButton;    
    
    @FXML
    void DrawCardBtn(ActionEvent event) {
        game.handleTurn();
        drawCard(canvas1, game.getPlayer1CurrCard());
        drawCard(canvas2, game.getPlayer2CuCard());
        displayRemainingPile(canvas1, game.getPlayer1());
        displayRemainingPile(canvas2, game.getPlayer2());
    }

    private void displayRemainingPile(Canvas canvas, Player p) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.setFont(new Font(20));
        gc.fillText(p.getName() + " remaining cards: " + p.getDeck().size(), canvas.getWidth()-10, 50);
        gc.fillText("GameStatus: " + game.getGameStatus(), canvas.getWidth()-10, 100);
    }

    private void drawCard(Canvas canvas, Card drawnCard) {        
        if (drawnCard != null) {
            GraphicsContext gc = canvas.getGraphicsContext2D();
            double centerX = 100;
            double centerY = 150;
            double topLeftX = 20;
            double topLeftY = 50;
            double botomRightX = 200;
            double botomRightY = 280; 
            double lineSpacing = 40;
            Color color = (drawnCard.getSuit() == "♥" || drawnCard.getSuit() == "♦") ? Color.RED : Color.BLACK;

            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            
            // Draw card outline
            gc.setFill(Color.WHITE);
            gc.fillRoundRect(10, 10, 180, 280, 15, 15);
            gc.setStroke(color);
            gc.strokeRoundRect(10, 10, 180, 280, 20, 20);

            // Draw card rank
            gc.setFill(color);
            gc.setFont(new Font(45));
            gc.setTextAlign(TextAlignment.LEFT);
            gc.fillText(drawnCard.getRank(), topLeftX, topLeftY);
            gc.fillText(drawnCard.getSuit(), calcSuitPosition(drawnCard, topLeftX), topLeftY + lineSpacing);
            // gc.fillText(drawnCard.getRank(), 70, 100);
            // gc.fillText(drawnCard.getSuit(), 70, 150);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText(drawnCard.getRank(), centerX, centerY - lineSpacing / 2);
            gc.fillText(drawnCard.getSuit(), calcSuitPosition(drawnCard, centerX), centerY + lineSpacing / 2);

            gc.setTextAlign(TextAlignment.RIGHT);
            gc.fillText(drawnCard.getRank(), botomRightX - 20, botomRightY - lineSpacing);
            gc.fillText(drawnCard.getSuit(), botomRightX - 20, botomRightY);
        }
    }

    private double calcSuitPosition(Card drawnCard, double x) {
        String currRank = drawnCard.getRank();
        return currRank.equals("10") ? x + 5 : x;
    }
}

