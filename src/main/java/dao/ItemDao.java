package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

import vo.Item;

public class ItemDao {
	public HashMap<String, Integer> insertItem(Connection conn, Item item) throws Exception {
		int autoKey = 0;
		
		String sql = "INSERT INTO item(item_name) VALUES(?)";
		// Statement.RETURN_GENERATED_KEYS 옵션사용 -> 쿼리 실행후 생성된 auto_increment값을 ResultSet에 반환
		PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, item.getItemName());
		int row = stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if(rs.next()) {
			autoKey = rs.getInt(1); // stmt.executeUpdate(); 생성된 auto_increment값이 대입
		}
		
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("row", row);
		map.put("autoKey", autoKey);
		
		return map;
	}
}
