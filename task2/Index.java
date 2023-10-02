package AI_THB1_agentAB.task2;

public class Index<R, C> {
    private R row;
    private C column;

    public Index(R row, C column) {
        this.row = row;
        this.column = column;
    }

    public R getRow() {
        return row;
    }

    public void setRow(R row) {
        this.row = row;
    }

    public C getColumn() {
        return column;
    }

    public void setColumn(C column) {
        this.column = column;
    }
}
