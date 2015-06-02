package egovframework.dev.testStarter;

import java.io.*;
import javax.swing.JOptionPane;

public class FileReader {


	public static void main(String[] args) {


		// 쓰기
		String input = JOptionPane.showInputDialog("메시지를 입력하세요.");

		File file = new File("C:/Users/Skysoft_D001/Desktop/ex/sample.txt");
		PrintWriter pw = null;

		try {
				pw = new PrintWriter(file);
				pw.println(input);
				pw.flush();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("이것은 텍스트 파일입니다.");


		// 읽기
		BufferedReader br = null;

		try {
				br = new BufferedReader(new java.io.FileReader(file));
				String line = "";
				while((line = br.readLine())!=null){

					System.out.printf("내용은 이것과 같습니다. \n %s",line);

				}

		} catch (Exception e) {
			e.printStackTrace();
		}


	}

}
