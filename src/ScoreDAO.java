// =============================
// 4. DAO
// =============================

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ScoreDAO {
    public static void saveScore(String name, int score) {
        try (Connection conn = DBConnection.getConnection()) {

            String sql = "INSERT INTO SNAKE_SCORE (PLAYER_NAME, SCORE, FECHA) VALUES (?, ?, SYSDATE)";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, name);
            ps.setInt(2, score);

            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
