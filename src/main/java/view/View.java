package view;

import javafx.stage.Stage;

/**
 * Basic View.
 *
 */
public interface View {

    /**
     * Initialize the View.
     */
    void init();

    /**
     * Attach a stage to the View.
     * 
     * @param stage
     */
    void setStage(Stage stage);

    /**
     * Return stage attached to the View.
     * 
     * @return the Stage
     */
    Stage getStage();

}
