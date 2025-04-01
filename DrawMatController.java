/**
 * This is the controller for DarwMat. It draws a matrix in a given size and randomly fills a percentage of all the cells 
 */
import java.util.HashSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawMatController {
    @FXML
    private Canvas canvas;

    @FXML
    void drawBtnPressed(ActionEvent event) {
        drawMat();
    }

    private final int RECT_SIZE = 10;
    private final int FILLED_PERCENTAGE = 10;
    
    /**
     * This function draws the mat while checking if the current cell is a part of the set, if so, it fills it, if not it only draws its outline
     */
    private void drawMat() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        // int rectSize = 10;
        int sizeX = (int) canvas.getWidth() / RECT_SIZE;
        int sizeY = (int) canvas.getHeight() / RECT_SIZE;
        int count = 0;
        HashSet<Integer> filledRectLocations = populateSet(sizeX, sizeY, FILLED_PERCENTAGE);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                count++;
                if (filledRectLocations.contains(count)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(RECT_SIZE * i, RECT_SIZE * j, RECT_SIZE, RECT_SIZE);
                }
                else {
                    gc.strokeRect(RECT_SIZE * i, RECT_SIZE * j , RECT_SIZE, RECT_SIZE);
                }
            }
        }
    }

    /**
     * This function randomly generates the indexes of cells needed to be filled  based on a given canvas size, and the requested filled percentage.  
     * @param sizeX the canvas width
     * @param sizeY the canvas height
     * @param prepercentage the requested filled percentage out of the canvass size 
     * @return a set of the random indexes generated
     */
    private HashSet<Integer> populateSet(int sizeX, int sizeY, int prepercentage) {
        int setSize = (sizeX * sizeY) / prepercentage;
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < setSize) {
            set.add((int)(Math.random()* sizeX * sizeY));
        }

        return set;
    }

}
