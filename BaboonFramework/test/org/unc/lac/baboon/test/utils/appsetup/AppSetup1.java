package org.unc.lac.baboon.test.utils.appsetup;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.unc.lac.baboon.main.BaboonApplication;

/**
 * <b>AppSetup1</b>
 * 
 * @author Ariel Ivan Rabinovich & Juan Jose Arce Giacobbe
 *         <p>
 *         Class used by {@link BaboonMainApplicationSetupTest}
 */
public class AppSetup1 implements BaboonApplication {
    private final static Logger LOGGER = Logger.getLogger(AppSetup1.class.getName());

    @Override
    public void declare() {
        LOGGER.log(Level.INFO, "Declaring 1");
    }

    @Override
    public void subscribe() {
        LOGGER.log(Level.INFO, "Subscribing 1");
    }

}
