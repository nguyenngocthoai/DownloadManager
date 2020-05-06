package downloadmanager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DownloadManager {
	static Scanner sc = new Scanner(System.in);
//	static String urls = "https://www.quanlykhachhang.vn/uploads/news/2014_09/nlpaction-man-1411449129307-crop-1411449138141.jpg";

	public static void main(String[] args) throws IOException {
		System.out.print("Enter URL download:");
		String urls = sc.nextLine();
		URL url = verify(urls);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		InputStream in = null;
		String filename = url.getFile();
		filename = filename.substring(filename.lastIndexOf('/') + 1);
		System.out.print("Save at:");
		String saveAt = sc.nextLine();
//		FileOutputStream out = new FileOutputStream(
//				"C:\\Users\\Admin\\OneDrive\\Máy tính\\TestURL" + File.separator + filename);
		FileOutputStream out = new FileOutputStream(saveAt + File.separator + filename);
		in = connection.getInputStream();
		int read = -1;
		byte[] buffer = new byte[4096];
		while ((read = in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
			System.out.println("[SYSTEM/INFO]: Downloading file...");
		}
		in.close();
		out.close();
		System.out.println("[SYSTEM/INFO]: File Downloaded!");
	}

	private static URL verify(String url) {
		if (url.toLowerCase().startsWith("http://")) {
////		if (!url.toLowerCase().startsWith(
////				"https://www.quanlykhachhang.vn/uploads/news/2014_09/nlpaction-man-1411449129307-crop-1411449138141.jpg")) {
			return null;
		}
		URL verifyUrl = null;

		try {
			verifyUrl = new URL(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return verifyUrl;
	}
}