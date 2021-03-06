package view.scenemaker;

import entity.MultiChannelSegment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.chart.MultiSegmentChart;

import java.util.List;

public class MultipleSegmentViewSceneMaker extends AbstractSceneMaker {

    private List<MultiChannelSegment> segments;
    private int indexOfSegmentToShow;

    private Button btnBack;

    private Button btnNextSegment;
    private Button btnPreviousSegment;

    public MultipleSegmentViewSceneMaker(Stage stage, List<MultiChannelSegment> segments, int indexOfSegmentToShow) {
        super(stage);
        this.segments = segments;
        this.indexOfSegmentToShow = indexOfSegmentToShow;
    }

    public Scene makeScene() {

        btnNextSegment = new Button();
        btnNextSegment.setText("Next");
        addActionHandlerForNextButton();
        btnPreviousSegment = new Button();
        btnPreviousSegment.setText("Previous");
        addActionHandlerForPreviousButton();
        btnBack = new Button();
        btnBack.setText("Back to menu");
        addActionHandlerForBackButton();

        HBox hBox = new HBox();
//        hBox.getChildren().addAll(paneWithFlowControl());

        MultiSegmentChart lineChartFromMultiSegment = new MultiSegmentChart();
        hBox.getChildren()
                .addAll(lineChartFromMultiSegment.generateChartFromMultiSegment(segments.get(indexOfSegmentToShow)));

        VBox vBox = new VBox();
        vBox.getChildren().addAll(hBox, this.paneWithFlowControl());

        Scene scene = new Scene(vBox, LENGTH_STAGE, HIGH_STAGE);
        scene.getStylesheets().add("file:src/resources/stylesheet.css");

        return scene;
    }

    @SuppressWarnings("restriction")
    private void addActionHandlerForBackButton() {
        btnBack.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
//				logger.info("back");
                ListOfRegionsSceneMaker sm = new ListOfRegionsSceneMaker(stage);
                stage.setScene(sm.makeScene());
            }
        });
    }

    @SuppressWarnings("restriction")
    private void addActionHandlerForNextButton() {
        btnNextSegment.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (indexOfSegmentToShow < segments.size() - 1) {
                    indexOfSegmentToShow++;
                    MultipleSegmentViewSceneMaker sm = new MultipleSegmentViewSceneMaker(stage, segments,
                            indexOfSegmentToShow);
                    stage.setScene(sm.makeScene());
                } else {
//					logger.info("no more segments");
                }
            }
        });
    }

    @SuppressWarnings("restriction")
    private void addActionHandlerForPreviousButton() {
        btnPreviousSegment.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                if (indexOfSegmentToShow > 0) {
                    indexOfSegmentToShow--;
                    MultipleSegmentViewSceneMaker sm = new MultipleSegmentViewSceneMaker(stage, segments,
                            indexOfSegmentToShow);
                    stage.setScene(sm.makeScene());
                } else {
//					logger.info("no more segments");
                }
            }
        });
    }


    private GridPane scrollPaneWithRegister() {

        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.TOP_CENTER);
        pane1.setHgap(50);
        pane1.setVgap(50);
        pane1.setPadding(new Insets(1, 1, 1, 1));
//        initIndexLabel.setText(initIndexLabel.getText() + segments.get(indexOfSegmentToShow).getInitIdx());
        pane1.add(btnNextSegment, 0, 1);
        pane1.add(btnPreviousSegment, 0, 2);
//        pane1.add(initIndexLabel, 0, 3);
        pane1.add(btnBack, 0, 0);
        return pane1;
    }

    private GridPane paneWithFlowControl() {

        GridPane pane1 = new GridPane();
        // pane1.setAlignment(Pos.TOP_CENTER);
        pane1.setHgap(50);
        pane1.setVgap(50);
        pane1.setPadding(new Insets(1, 1, 1, 1));
//        initIndexLabel.setText(initIndexLabel.getText()
//                + segments.get(indexOfSegmentToShow).getInitIdx());
        pane1.add(btnNextSegment, 12, 0);
        pane1.add(btnPreviousSegment, 11, 0);
        pane1.add(btnBack, 10, 0);

        return pane1;
    }

}
