package egovframework.dev.testStarter;

public class SkysoftAddr {

	private static String[][] addrArr = {
											{"개발팀1\t사원\t김동섭"}, {"개발팀1\t주임\t이효리"}, {"개발팀1\t대리\t강호동"}, {"개발팀1\t과장\t유재석"}, {"개발팀1\t차장\t노홍철"},
											{"개발팀2\t사원\t데프콘"}, {"개발팀2\t주임\t강개리"}, {"개발팀2\t대리\t송지효"}, {"개발팀2\t과장\t김종국"}, {"개발팀2\t차장\t송혜교"},
											{"개발팀3\t사원\t박명수"}, {"개발팀3\t주임\t정준하"}, {"개발팀3\t대리\t차태현"}, {"개발팀3\t과장\t이승철"}, {"개발팀3\t차장\t최홍만"},
											{"개발팀4\t사원\t성유리"}, {"개발팀4\t주임\t박한별"}, {"개발팀4\t대리\t이정재"}, {"개발팀4\t과장\t황정민"}, {"개발팀4\t차장\t배두나"},

										};


	public static void main(String[] args) {


		for(int i=0; i < addrArr.length; i++){

			for(int j=0; j < addrArr[i].length; j++){

				System.out.println(addrArr[i][j]);

			}



		}



	}

}
