package org.unc.lac.baboon.test.utils.tasks;


import org.unc.lac.baboon.annotations.GuardProvider;
import org.unc.lac.baboon.annotations.HappeningController;
import org.unc.lac.baboon.annotations.TaskController;
import org.unc.lac.baboon.test.cases.ComplexTaskControllersSubscriptionTest;
import org.unc.lac.baboon.test.cases.HappeningControllerJoinPointTest;
import org.unc.lac.baboon.test.cases.TasksAndHappeningControllersSubscriptionTest;
import org.unc.lac.baboon.test.utils.TransitionEventObserver;
/**
 * {@link MockUserSystemObject} is used by {@link TasksAndHappeningControllersSubscriptionTest},
 * {@link ComplexTaskControllersSubscriptionTest} and {@link HappeningControllerJoinPointTest}
 * for testing purposes. It simulates an object that could be present on a system developed using this framework.
 * 
 * @author Ariel Ivan Rabinovich
 * @author Juan Jose Arce Giacobbe
 * 
 * @see TaskController
 * @see HappeningController
 * @see @GuardProvider
 */
public class MockUserSystemObject {

    boolean guard1Value = false;
    boolean guard2Value = false;

    @HappeningController
    public void mockHappeningController() {
    }

    @TaskController
    public void mockTask() {
    }
    
    @TaskController
    public void mockTaskCounter(CustomCounter count) {
        count.increase();
    }

    public void mockNotSubscribableMethod() {
    }

    @HappeningController
    public void mockHappeningController2() {
    }
    
    /**
     * Test Method.</br>
     * Appends eventToInsert to tObs event list.
     * 
     * 
     * @param tObs
     *            A {@link TransitionEventObserver} object from where it checks that only one event has been received and it corresponds to the expected transition event.
     * @param eventToInsert
     *            Event to be appended on tObs eventsRecieved.

     */
    @HappeningController
    public void eventInserterHappeningController(TransitionEventObserver tObs, String eventToInsert ){
        tObs.getEvents().add(eventToInsert);
    }


    @TaskController
    public void mockTask2() {
    }
    
    @TaskController
    public void mockTask3() {
    }
    
    @TaskController
    public static void staticMockTask() {
    }


    public void setGuard1Value(boolean newValue) {
        guard1Value = newValue;
    }

    public void setGuard2Value(boolean newValue) {
        guard2Value = newValue;
    }

    @GuardProvider("g1")
    public boolean mockGuard1Provider() {
        return guard1Value;
    }

    @GuardProvider("g2")
    public boolean mockGuard2Provider() {
        return guard2Value;
    }

}
