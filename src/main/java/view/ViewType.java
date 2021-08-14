package view;

import controller.Controller;
import controller.StartViewController;

/**
 * Enum for the Views, each one has her Style-File ".fxml" and her specific
 * Controller. Type
 * 
 */
public enum ViewType {

    /*
     * 
     */
    START("StartView"),
	BASIC("HomeView"),
	CATEGORY("CategoryView"),
	CALENDAR("CalendarView"),
	SINGLECATEGORY("SingleCategoryView"),
	INSERTPLAYERVIEW("InsertPlayerView"),
	INSERTCATEGORY("InsertCategoryView"),
	SINGLEEVENT("SingleEventView"),
	EVENTCONVENE("ConveneToEventView"),
	DIRIGENT("DirigentView"),
	INSERTSTAFF("InsertStaffView"),
	SCHEDAGIOCATORE("SchedaGiocatoreView"),
	INSERTEVENT("InsertEventView"),
	CONVOCATIONS("Convocations"),
	INSERTDIRIGENT("InsertDirigentView");
	

    /**
     * Style files path.
     */
    private static final String DIRECTORY = "viewStyle/";
    private static final String FORMAT = ".fxml";

    private String fileName;

    ViewType(final String string) {
        this.fileName = string;
    }

    /**
     * Get the path of FXML Style file.
     * 
     * @return The Path
     */
    public String getPath() {
        return DIRECTORY + this.fileName + FORMAT;
    }
}

