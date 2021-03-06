package database;

import java.sql.Connection;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Dominik Roth, ProjectPrison, 10.06.2015
 */
public class CachedConnection {

    private Queue<Statement> statQueue = new LinkedList<>();
    private Connection con = null;

    public CachedConnection(Connection con) {
        this.con = con;
    }

    public Statement getStatement() throws Exception {

        if (con == null) {
            throw new Exception("not connected");
        }
        if (!statQueue.isEmpty()) {
            return statQueue.poll();
        }

        return con.createStatement();
    }

    public void releaseStatement(Statement stat) throws Exception {

        if (con == null) {
            throw new Exception("not connected");
        }
        statQueue.offer(stat);
    }
}
