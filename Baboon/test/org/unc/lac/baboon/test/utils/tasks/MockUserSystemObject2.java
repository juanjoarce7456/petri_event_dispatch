package org.unc.lac.baboon.test.utils.tasks;

import org.unc.lac.baboon.annotations.GuardProvider;
import org.unc.lac.baboon.annotations.HappeningController;
import org.unc.lac.baboon.annotations.TaskController;
import org.unc.lac.baboon.test.cases.TasksAndHappeningControllersSubscriptionTest;
import org.unc.lac.baboon.test.utils.tasks.parameters.AbstractParameter;
import org.unc.lac.baboon.test.utils.tasks.parameters.Parameter;
import org.unc.lac.baboon.test.utils.tasks.parameters.Parameter2;
import org.unc.lac.baboon.test.utils.tasks.parameters.Parameter3;
import org.unc.lac.baboon.test.utils.tasks.parameters.Testable;

/**
 * MockController2 is used by {@link TasksAndHappeningControllersSubscriptionTest}
 * for testing purposes.
 * 
 * @author Ariel Ivan Rabinovich
 * @author Juan Jose Arce Giacobbe
 * 
 * @see Task
 * @see HappeningHandler
 */
public class MockUserSystemObject2 {

    boolean guard1Value = false;
    boolean guard2Value = false;
    boolean guard3Value = false;

    @HappeningController
    public void mockHappeningHandler() {
    }

    @TaskController
    public void mockTask() {
    }

    public void mockNotSubscribableMethod() {
    }

    @HappeningController
    public void mockHappeningHandler2() {
    }

    @TaskController
    public void mockTask2(Parameter e) {
        
    }
    
    @TaskController
    public void mockTask3(Testable e) {
        
    }
    
    @TaskController
    public void mockTask4(AbstractParameter e) {
        
    }
    
    @TaskController
    public void mockTask5(Parameter e, Parameter2 e2, Parameter3 e3) {
        
    }

    public void setGuard1Value(boolean newValue) {
        guard1Value = newValue;
    }

    public void setGuard2Value(boolean newValue) {
        guard2Value = newValue;
    }
    
    public void setGuard3Value(boolean newValue) {
        guard3Value = newValue;
    }

    @GuardProvider("g1")
    public boolean mockGuard1Provider() {
        return guard1Value;
    }

    @GuardProvider("g2")
    public boolean mockGuard2Provider() {
        return guard2Value;
    }
    
    @GuardProvider("g3")
    public boolean mockGuard3Provider() {
        return guard3Value;
    }

}
