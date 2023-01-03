package Controller;

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
public class Fileupload extends HttpServlet {

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
			
		// MultipartRequest로 원본 request를 랩핑후에는 스트림을 받을 필요없이
		// MultipartRequest의 api를 사용하면된다
		
		// input type="text"
		String itemName = mreq.getParameter("itemName"); // MultipartRequest.getParameter("itemName");
		
		// input type="file" 바이너리 파일은 마임타입형태의 파일로 변환되어 upload폴더에 자동으로 저장
		String contentType = mreq.getContentType("itemImg");
		String originalFileName = mreq.getOriginalFileName("itemImg"); // 원본 파일 이름
		String fileSystemName = mreq.getFilesystemName("itemImg"); // 저장된 파일 이름(Default)
		
		System.out.println("---문자열 매개값---");
		System.out.println(itemName);
		
		System.out.println("---파일 매개값---");
		System.out.println(contentType);
		System.out.println(originalFileName);
		System.out.println(fileSystemName);
		
		Item item = new Item();
		item.setItemName(itemName);
		
		ItemImg itemImg = new ItemImg();
		itemImg.setFilename(fileSystemName);
		
		this.itemService = new ItemService();
		
		itemService.addItem(item, itemImg, dir);
		
		response.sendRedirect(request.getContextPath()+"/Fileupload");
	}

}
