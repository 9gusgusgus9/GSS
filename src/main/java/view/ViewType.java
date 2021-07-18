package view;

import controller.Controller;
import controller.ControllerImpl;
import controller.StartViewController;
import controller.TestController;

/**
 * Enum for the Views, each one has her Style-File ".fxml" and her specific
 * Controller. Type
 * 
 */
public enum ViewType {

    /*
     * 
     */
	TEST("Test", new TestController()),
    START("StartView", new StartViewController()),
	BASIC("HomeView", new StartViewController()),
	CATEGORY("CategoryView", new StartViewController()),
	CALENDAR("CalendarView", new StartViewController()),
	SINGLECATEGORY("SingleCategoryView", new StartViewController()),
	INSERTPLAYERVIEW("InsertPlayerView", new StartViewController()),
	INSERTCATEGORY("InsertCategoryView", new StartViewController()),
<<<<<<< HEAD
	SINGLEEVENT("SingleEventView", new StartViewController());
=======
	DIRIGENT("DirigentView", new StartViewController()),
	INSERTSTAFF("InsertStaffView", new StartViewController()),
	INSERTDIRIGENT("InsertDirigentView", new StartViewController());
>>>>>>> a12935d90855f92e3ee86381f816bc5bc9451e61

    /**
     * Style files path.
     */
    private static final String DIRECTORY = "viewStyle/";
    private static final String FORMAT = ".fxml";

    private String fileName;
    private Controller controller;

    ViewType(final String string, final Controller controller) {
        this.fileName = string;
        this.controller = controller;
    }

    /**
     * Get the path of FXML Style file.
     * 
     * @return The Path
     */
    public String getPath() {
        return DIRECTORY + this.fileName + FORMAT;
    }

    /**
     * Return the specific controller linked to the specific view.
     * 
     * @return the Controller
     */
    public Controller getController() {
        return this.controller;
    }

}

