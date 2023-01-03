package Controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.ItemService;
import vo.Item;
import vo.ItemImg;


@WebServlet("/Fileupload")
public class AddItemController extends HttpServlet {

	// 상품등록 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/fileupload.jsp").forward(request, response);
	}

	// 상품등록 액션
	private ItemService itemService; 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 인코딩
		request.setCharacterEncoding("utf-8");
		
		// 원본 request 객체를 cos api로 랩핑
		// new MultipartRequest(원본request, 업로드폴더, 최대파일사이즈(byte), 인코딩, 중복이름정책)
		
		// 프로젝트 안 upload폴더의 실제 물리적 위치를 반환
		String dir = request.getServletContext().getRealPath("/upload");

		// 1KB : 1024 byte, 1MB : 1024*1024 byte, 1GB : 1024*1024*1024 byte ...
		int maxFileSize = 1024 * 1024 * 100; // 100Mbyte(MB)
		
		// 업로드 폴더 내 동일한 이름이 있으면 뒤에 숫자를 추가하여 중복을 방지
		DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();

		MultipartRequest mreq 
			= new MultipartRequest(request, dir, maxFileSize, "utf-8", fp); // 5가지의 매개변수 (request,디렉토리,최대사이즈,인코딩방식,이름정책)
		
		// 이미지파일 검사 -> (jpg와 png 이외의 타입은 불허)
		String contentType = mreq.getContentType("itemImg");
		if(contentType.equals("image/jpg") || contentType.equals("image/png")) {			
			String itemName = mreq.getParameter("itemName"); // MultipartRequest.getParameter("itemName");
			String fileSystemName = mreq.getFilesystemName("itemImg"); // 저장된 파일 이름(Default)
			
			Item item = new Item();
			item.setItemName(itemName);
			
			ItemImg itemImg = new ItemImg();
			itemImg.setFilename(fileSystemName);
			
			this.itemService = new ItemService();
			
			itemService.addItem(item, itemImg, dir);
			System.out.println("업로드 성공");
		} else {
			System.out.println("*.jpg, *.png파일만 업로드 가능");
			File f = new File(dir+"\\"+mreq.getFilesystemName("itemImg"));
			if(f.exists()) {
				f.delete(); // 이미지가 아닌 파일이 업로드 되었기때문에 삭제
			}
		}
			
		
		
		response.sendRedirect(request.getContextPath()+"/Fileupload");
	}

}
