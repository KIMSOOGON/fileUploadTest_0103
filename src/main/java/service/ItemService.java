
package service;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

import dao.*;
import vo.*;

public class ItemService {
   private ItemDao itemDao;
   private ItemImgDao itemImgDao;
   
   public ArrayList<HashMap<String, Object>> getItemList() {
	   ArrayList<HashMap<String,Object>> list = null;
	   Connection conn = null;
	   
	   try {
		 Class.forName("org.mariadb.jdbc.Driver");
		 conn = DriverManager.getConnection(
		       "jdbc:mariadb://localhost:3306/fileupload","root","java1234");
		 conn.setAutoCommit(false);
		 itemDao = new ItemDao();
		 list = itemDao.selectItemList(conn);
		 conn.commit();
	   } catch(Exception e) {
		   try {
			   conn.rollback();
		   } catch (SQLException e1) {
			   e1.printStackTrace();
		   }
		   e.printStackTrace();
	   } finally {
		   try {
			   conn.close();
		   } catch (SQLException e) {
			   e.printStackTrace();
		   }
	   }
	   return list;
   }
   
   public void addItem(Item item, ItemImg itemImg, String dir) {
      itemDao = new ItemDao();
      itemImgDao = new ItemImgDao();
      Connection conn = null;
      try {
         Class.forName("org.mariadb.jdbc.Driver");
         conn = DriverManager.getConnection(
               "jdbc:mariadb://localhost:3306/fileupload","root","java1234");
         conn.setAutoCommit(false);
         
         HashMap<String, Integer> map = itemDao.insertItem(conn, item);
         
         itemImg.setItemNo(map.get("autoKey"));
         itemImgDao.insertItemImg(conn, itemImg); // itemImg.getItemNo() --> 0
         
         conn.commit();
      } catch(Exception e) {
         try {
            conn.rollback();
            // db작업에 실패시 이미 업로드되어 버린 파일을 불러와 삭제
            File f = new File(dir+"\\"+itemImg.getFilename());
            if(f.exists()) {
            	f.delete();
            }
         } catch (SQLException e1) {
            e1.printStackTrace();
         }
      } finally {
         try {
            conn.close();
         } catch (SQLException e) {
            e.printStackTrace();
         }
      }
   }
}