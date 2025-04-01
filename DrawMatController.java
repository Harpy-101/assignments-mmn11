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
    
    private void drawMat() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        int rectSize = 10;
        int sizeX = (int) canvas.getWidth() / 10;
        int sizeY = (int) canvas.getHeight() / 10;
        int count = 0;
        HashSet<Integer> filledRectLocations = populateSet(sizeX, sizeY, 10);
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int i = 0; i < sizeX; i++) {
            for (int j = 0; j < sizeY; j++) {
                count++;
                if (filledRectLocations.contains(count)) {
                    gc.setFill(Color.BLACK);
                    gc.fillRect(rectSize * i, rectSize * j, rectSize, rectSize);
                }
                else {
                    gc.strokeRect(rectSize * i, rectSize * j , rectSize, rectSize);
                }
            }
        }
    }

    private HashSet<Integer> populateSet(int sizeX, int sizeY, int prepercentage) {
        int setSize = (sizeX * sizeY) / prepercentage;
        HashSet<Integer> set = new HashSet<>();
        while (set.size() < setSize) {
            set.add((int)(Math.random()* sizeX * sizeY));
        }

        return set;
    }

}
