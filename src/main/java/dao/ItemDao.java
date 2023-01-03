package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import vo.Item;

public class ItemDao {
	
	// 상품 상세보기 : 조인으로 해결
	public ArrayList<HashMap<String, Object>> selectItemList(Connection conn) throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		String sql = "SELECT it.item_no itemNo, it.item_name itemName, img.filename fileName"
					+ " FROM item it INNER JOIN item_img img"
					+ " ON it.item_no = img.item_no";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<String, Object>();
			m.put("itemNo",  rs.getInt("itemNo"));
			m.put("itemName", rs.getString("itemName"));
			m.put("fileName", rs.getString("fileName"));
			list.add(m);
		}
		
		return list;
	}
	
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
