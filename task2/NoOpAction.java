package AI_THB1_agentAB.task2;

public class NoOpAction extends Action {
	public static final NoOpAction NO_OP = new NoOpAction();

	public boolean isNoOp() {
		return true;
	}

	@Override
	public String toString() {
		return " (ERROR: DON'T";
	}
}