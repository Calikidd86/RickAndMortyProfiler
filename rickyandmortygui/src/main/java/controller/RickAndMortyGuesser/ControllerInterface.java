package controller.RickAndMortyGuesser;

import model.ModelInterface;
import view.ViewInterface;

/**
 * ControllerInterface for Profiler MVC
 * @author Dante anthony
 */
public interface ControllerInterface {
    /**
     * Set the view of the Controller
     * @param view a valid ViewInterface
     */
   void setViewInterface(ViewInterface view);

    /**
     * Set the model of the Controller
     * @param model data model implementing ModelInterface
     */
   void setModelInterface(ModelInterface model);
}
