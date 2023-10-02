package AI_THB1_agentAB.task2;

import java.util.List;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;
	private Agent agent = null;

	public Environment(LocationState locAState, LocationState locBState, LocationState locDState, LocationState locCState) {
		envState = new EnvironmentState(locAState, locBState, locDState, locCState); //???
	}


	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);
		
	}

	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	public EnvironmentState executeAction(Action action) {
		Index<Integer, Integer> index = AgentProgram.convertLocationToIndex(envState.getAgentLocation());
		if(action.equals(Environment.SUCK_DIRT)){
			envState.setLocationState(envState.getAgentLocation(), LocationState.CLEAN);
		} else if(action==Environment.MOVE_RIGHT){
			Index<Integer, Integer> newIndex = new Index<>(index.getRow(), index.getColumn()+1);
			String newLoc = AgentProgram.convertIndexToLocation(newIndex);
			envState.setAgentLocation(newLoc);
		} else if(action==Environment.MOVE_LEFT){
			Index<Integer, Integer> newIndex = new Index<>(index.getRow(), index.getColumn()-1);
			String newLoc = AgentProgram.convertIndexToLocation(newIndex);
			envState.setAgentLocation(newLoc);
		} else if(action==Environment.MOVE_UP){
			Index<Integer, Integer> newIndex = new Index<>(index.getRow()-1, index.getColumn());
			String newLoc = AgentProgram.convertIndexToLocation(newIndex);
			envState.setAgentLocation(newLoc);
		} else if(action==Environment.MOVE_DOWN){
			Index<Integer, Integer> newIndex = new Index<>(index.getRow()+1, index.getColumn());
			String newLoc = AgentProgram.convertIndexToLocation(newIndex);
			envState.setAgentLocation(newLoc);
		}
		return envState;
	}

	
	public Percept getPerceptSeenBy() {
		String location = envState.getAgentLocation(); //A
		return new Percept(location, envState.getLocationState(location)); 
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation(); 
		Action anAction = agent.execute(getPerceptSeenBy()); 
		EnvironmentState es = executeAction(anAction); // 

			System.out.print("Agent Loc.: " + agentLocation);
			if(AgentProgram.errorCode!=-1) {
				System.out.print("\tAction: " + AgentProgram.printAction(AgentProgram.errorCode) + anAction + " "+ AgentProgram.printAction(AgentProgram.errorCode) + ")");
				AgentProgram.errorCode = -1;
				System.out.println("\tPoint: " + AgentProgram.point);
			} else {
				System.out.print("\tAction: " + anAction);
				System.out.println("\t\t\t\t\t\tPoint: " + AgentProgram.point);
			}





		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
			step();
			System.out.println("-------------------------");
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
		}
	}
}
