package controller;

import view.View;

/**
 * This represent a generic Controller, every type of Controller extends it.
 *
 */
public interface Controller {

    /**
     * This method attach the view to the Controller.
     * 
     * @param view the View to attach
     */
    void attachView(View view);

    /**
     * This method return the View attached to the Controller.
     * 
     * @return View, the view attached
     */
    View getView();

    /**
     * This method Initialize the Controller.
     */
    void init();
}
