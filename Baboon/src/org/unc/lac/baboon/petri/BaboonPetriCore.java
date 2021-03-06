package org.unc.lac.baboon.petri;

import java.lang.reflect.InvocationTargetException;
import org.unc.lac.baboon.exceptions.BadPolicyException;
import org.unc.lac.javapetriconcurrencymonitor.errors.IllegalTransitionFiringError;
import org.unc.lac.javapetriconcurrencymonitor.exceptions.PetriNetException;
import org.unc.lac.javapetriconcurrencymonitor.monitor.PetriMonitor;
import org.unc.lac.javapetriconcurrencymonitor.monitor.policies.FirstInLinePolicy;
import org.unc.lac.javapetriconcurrencymonitor.monitor.policies.TransitionsPolicy;
import org.unc.lac.javapetriconcurrencymonitor.petrinets.PetriNet;
import org.unc.lac.javapetriconcurrencymonitor.petrinets.factory.PetriNetFactory;
import org.unc.lac.javapetriconcurrencymonitor.petrinets.factory.PetriNetFactory.petriNetType;

import rx.Observer;
import rx.Subscription;

/**
 * BaboonPetriCore is a wrapper containing the objects that are necessary for
 * initialize and execute petri nets.
 * 
 * @author Ariel Ivan Rabinovich
 * @author Juan Jose Arce Giacobbe
 * @version 1.0
 * @see PetriMonitor
 * @see PetriNet
 */

public class BaboonPetriCore {
    private PetriNetFactory factory;
    private PetriMonitor monitor;
    private PetriNet petri;

    /**
     * Creates the Petri Net core of the application by using the pnml file
     * provided as an argument, the petri net type and the transition firing
     * policy.
     * <p>
     * If the petri net type provided is null then
     * {@link petriNetType#PLACE_TRANSITION} is used by default.
     * </p>
     * <p>
     * If the transition firing policy provided is null then
     * {@link FirstInLinePolicy} is used by default.
     * </p>
     * <p>
     * If pnml file path is null then {@link IllegalArgumentException} is
     * thrown.
     * </p>
     * 
     * 
     * @param pnmlFilePath
     *            The path to the file containing the PNML (Tina dialect)
     *            representation of the petri net.
     * @param type
     *            Indicates if the petri net to be created is a timed petri net
     *            or a place-transition petri net.
     * @param firingPolicy
     *            A {@link Class} object that extends {@link TransitionsPolicy} used by 
     *            petri monitor to decide which transition to fire next. It might be null, 
     *            in which case {@link FirstInLinePolicy} will be used.
     * @param <A> 
     *            A {@link Class} object that extends {@link TransitionsPolicy}-
     * @throws BadPolicyException 
     *      If the transitions policy provided is not correctly formed.
     */
    public <A extends TransitionsPolicy> BaboonPetriCore(String pnmlFilePath, petriNetType type, Class<A> firingPolicy) throws BadPolicyException {
        if (pnmlFilePath == null) {
            throw new IllegalArgumentException("The pnml file path can not be null");
        }
        petriNetType typeChecked = type == null ? petriNetType.PLACE_TRANSITION : type;
        factory = new PetriNetFactory(pnmlFilePath);
        petri = factory.makePetriNet(typeChecked);
        TransitionsPolicy firingPolicyChecked;
        try {
            firingPolicyChecked = firingPolicy == null ? new FirstInLinePolicy(petri) : firingPolicy.getDeclaredConstructor(PetriNet.class).newInstance(petri);
            monitor = new PetriMonitor(petri, firingPolicyChecked);
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException e) {
            throw new BadPolicyException("Failed to create an instance with the policy class provided.");
        }
    }

    /**
     * Initializes the petri net. This method is called by Baboon framework on
     * its setup.
     */
    public void initializePetriNet() {
        petri.initializePetriNet();
    }

    /**
     * Changes the petri monitor transition firing policy. If the policy
     * provided is null then no actions are taken.
     * 
     * @param firingPolicy
     *            A {@link TransitionsPolicy} object used by petri monitor to
     *            decide which transition to fire next.
     * @see PetriMonitor
     * @see PetriMonitor#setTransitionsPolicy(TransitionsPolicy)
     */
    public void changeFiringPolicy(TransitionsPolicy firingPolicy) {
        monitor.setTransitionsPolicy(firingPolicy);
    }

    /**
     * Fires a transition by using petri monitor. This method is called
     * automatically by Baboon framework and is not intended to be used by user,
     * 
     * @param transitionName
     *            The name of the transition to be fired.
     * @param perennialFiring
     *            Indicates if the firing is perennial or not.
     * @throws PetriNetException
     *            If an error regarding petri nets occurs.
     * 
     * @see PetriMonitor
     * @see PetriMonitor#fireTransition(String, boolean)
     */
    public void fireTransition(String transitionName, boolean perennialFiring)
            throws IllegalArgumentException, IllegalTransitionFiringError, PetriNetException {
        monitor.fireTransition(transitionName, perennialFiring);
    }

    /**
     * Sets a guard by using petri monitor. This method is called automatically
     * by Baboon framework and is not intended to be used by user,
     * 
     * @param guardName
     *            The name of the guard to be modified.
     * @param newValue
     *            the new boolean value to be set on the guard.
     * @throws PetriNetException
     *           If an error regarding petri nets occurs.
     * 
     * @see PetriMonitor
     * @see PetriMonitor#setGuard(String, boolean)
     */
    public void setGuard(String guardName, boolean newValue)
            throws IndexOutOfBoundsException, NullPointerException, PetriNetException {
        monitor.setGuard(guardName, newValue);
    }
    
    /**
     * Subscribe the given observer to the given transition events if it's informed
     * @param _transitionName the name of the transition to subscribe to
     * @param _observer the observer to subscribe
     * @throws IllegalArgumentException if the given transition is not informed
     * @return a Subscription object used to unsubscribe
     * @see PetriMonitor#subscribeToTransition(String, Observer)
     */
    public Subscription listenToTransitionInforms (final String _transitionName, final Observer<String> _observer){
        return monitor.subscribeToTransition(_transitionName, _observer);
    }
    
    /**
     * This method returns the current marking on the Petri Net core of the application
     * @return the tokens in each place of the Petri Net
     */
    public Integer[] getMarking(){
        return petri.getCurrentMarking();
    }

}
