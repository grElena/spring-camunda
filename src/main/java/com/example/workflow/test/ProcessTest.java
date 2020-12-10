package com.example.workflow.test;

import com.example.workflow.service.jpa.HolidaysServiceImpl;
import com.example.workflow.task.DateAssignmentTask;
import com.example.workflow.task.RecordColorTask;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.spring.boot.starter.test.helper.AbstractProcessEngineRuleTest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

@Deployment(resources = {"process.bpmn"})
public class ProcessTest extends AbstractProcessEngineRuleTest{
    @Mock
    private ProcessScenario sampleApplication;

    @Before
    public void defineScenarioActions() {
        MockitoAnnotations.initMocks(this);
        Mocks.register("recordColorTask", new RecordColorTask());

        Mocks.register("dateAssignmentTask", new DateAssignmentTask(new HolidaysServiceImpl()));

        when(sampleApplication.waitsAtUserTask("UserTask_15pcfy6")).thenReturn((task) ->
                task.complete(var("favoriteColor", "brown"))
        );

        when(sampleApplication.waitsAtUserTask("Task_06ia23h")).thenReturn((task) ->
                task.complete()
        );

    }

    Map<String,Object> var(String name, String value) {
        Map<String,Object> m = new HashMap<>();
        m.put(name, value);
        return m;
    }
    @Test
    public void fullRunProcess() {
        Scenario.run(sampleApplication).startByKey("testColor").execute();
        verify(sampleApplication, times(1)).hasFinished("EndEvent_1ecq75r");
    }
}
