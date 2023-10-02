package AI_THB1_agentAB.task2;

public class TestSimpleReflexAgent {

	public static void main(String[] args) {
		Environment env = new Environment(Environment.LocationState.CLEAN, Environment.LocationState.DIRTY,
				Environment.LocationState.CLEAN, Environment.LocationState.DIRTY);
		Agent agent = new Agent(new AgentProgram());
		env.addAgent(agent, Environment.LOCATION_A);

		env.step(10);
	}
}
