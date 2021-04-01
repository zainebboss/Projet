package IService;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface DaoSession {
void createSession();
void updateSession(int id);
void deleteSession(int id);
ResultSet afficheSession() throws SQLException;

}
