package AI_THB1_agentAB.task2;

import java.util.*;

public class AgentProgram {
	private String[][] matrix = {{"A", "B"}, {"D", "C"}};
	public static int errorCode = -1;
	public static int point = 0;
	public static final int UP_ACTION = 0;
	public static final int DOWN_ACTION = 1;
	public static final int LEFT_ACTION = 2;
	public static final int RIGHT_ACTION = 3;
	final int SUCK_DIRT_POINT = 500;
	final int CANT_MOVE_POINT = -100;
	final int OTHER_ACTION_POINT = -10;
	private static int random(){
		Random random = new Random();
		int action = random.nextInt(4);
		return action;
	}

	public static String printAction(int action){
		switch (action){
			case UP_ACTION:
				return "UP";
			case DOWN_ACTION:
				return "DOWN";
			case RIGHT_ACTION:
				return "RIGHT";
			case LEFT_ACTION:
				return "LEFT";
		}
		return null;
	}

	public static Index<Integer, Integer> convertLocationToIndex(String location){
		switch (location){
			case Environment.LOCATION_A:
				return new Index<>(0, 0);
			case Environment.LOCATION_B:
				return new Index<>(0, 1);
			case Environment.LOCATION_C:
				return new Index<>(1, 1);
			case Environment.LOCATION_D:
				return new Index<>(1, 0);
		}
		return null;
	}

	public static String convertIndexToLocation(Index<Integer, Integer> index){
		if(index.getRow()==0 && index.getColumn()==0) return Environment.LOCATION_A;
		if(index.getRow()==1 && index.getColumn()==0) return Environment.LOCATION_D;
		if(index.getRow()==0 && index.getColumn()==1) return Environment.LOCATION_B;
		if(index.getRow()==1 && index.getColumn()==1) return Environment.LOCATION_C;
		return null;
	}

	public static Index<Integer, Integer> move(int action, Percept p){
		Integer row = convertLocationToIndex(p.getAgentLocation()).getRow();
		Integer col = convertLocationToIndex(p.getAgentLocation()).getColumn();
		switch (action){
			case UP_ACTION: //0
				return new Index<>(row-1, col);
			case DOWN_ACTION: //1
				return new Index<>(row+1, col);
			case LEFT_ACTION: //2
				return new Index<>(row, col-1);
			case RIGHT_ACTION: //3
				return new Index<>(row, col+1);
		}
		return null;
	}

	private Action doWhenLocationStateClean(int rand, Percept p){
		Index<Integer, Integer> newIndex = move(rand, p);
		if(newIndex.getRow()<0 || newIndex.getColumn()<0 || newIndex.getRow()>= matrix.length || newIndex.getColumn()>=matrix.length){
			point += CANT_MOVE_POINT;
			errorCode = rand;

		} else {
			point += OTHER_ACTION_POINT;
			Index<Integer, Integer> currentIndex = convertLocationToIndex(p.getAgentLocation());
			// success Action ăn theo p(percept)?
			if(currentIndex.getRow()==newIndex.getRow()){
				if(currentIndex.getColumn()<newIndex.getColumn()) {
					currentIndex.setColumn(newIndex.getColumn());
					return Environment.MOVE_RIGHT;
				} else if(currentIndex.getColumn()>newIndex.getColumn()) {
					currentIndex.setColumn(newIndex.getColumn());
					return Environment.MOVE_LEFT;
				}
			}
			if(currentIndex.getColumn()==newIndex.getColumn()){
				if(currentIndex.getRow()<newIndex.getRow()) {
					currentIndex.setRow(newIndex.getRow());
					return Environment.MOVE_DOWN;
				} else if(currentIndex.getRow()>newIndex.getRow()) {
					currentIndex.setRow(newIndex.getRow());
					return Environment.MOVE_UP;
				}
			}
		}
		return NoOpAction.NO_OP;
	}

	
	public Action execute(Percept p) {
		if(p.getLocationState().equals(Environment.LocationState.DIRTY)) {
			point += SUCK_DIRT_POINT;
			return Environment.SUCK_DIRT;
		} else {
			int rand = random();
			return doWhenLocationStateClean(rand, p);
		}
	}
}