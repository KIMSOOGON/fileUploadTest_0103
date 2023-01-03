<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="com.oreilly.servlet.*"%>
<%@ page import="com.oreilly.servlet.multipart.*"%>
<%
	// ���� request ��ü�� cos api�� ����
	// new MultipartRequest(����request, ���ε�����, �ִ����ϻ�����(byte), ���ڵ�, �ߺ��̸���å)
	
	// ������Ʈ �� upload������ ���� ������ ��ġ�� ��ȯ
	String dir = request.getServletContext().getRealPath("/upload");

	// 1KB : 1024 byte, 1MB : 1024*1024 byte, 1GB : 1024*1024*1024 byte ...
	int maxFileSize = 1024 * 1024 * 100; // 100Mbyte(MB)
	
	// ���ε� ���� �� ������ �̸��� ������ �ڿ� ���ڸ� �߰��Ͽ� �ߺ��� ����
	DefaultFileRenamePolicy fp = new DefaultFileRenamePolicy();

	MultipartRequest mreq 
		= new MultipartRequest(request, dir, maxFileSize, "utf-8", fp); // 5������ �Ű����� (request,���丮,�ִ������,���ڵ����,�̸���å)
		
	// MultipartRequest�� ���� request�� �����Ŀ��� ��Ʈ���� ���� �ʿ����
	// MultipartRequest�� api�� ����ϸ�ȴ�
	
	// input type="text"
	String itemName = mreq.getParameter("itemName"); // MultipartRequest.getParameter("itemName");
	
	// input type="file" ���̳ʸ� ������ ����Ÿ�������� ���Ϸ� ��ȯ�Ǿ� upload������ �ڵ����� ����
	String contentType = mreq.getContentType("itemImg");
	String originalFileName = mreq.getOriginalFileName("itemImg"); // ���� ���� �̸�
	String fileSystemName = mreq.getFilesystemName("itemImg"); // ����� ���� �̸�(Default)
	
	System.out.println("---���ڿ� �Ű���---");
	System.out.println(itemName);
	
	System.out.println("---���� �Ű���---");
	System.out.println(contentType);
	System.out.println(originalFileName);
	System.out.println(fileSystemName);
%>