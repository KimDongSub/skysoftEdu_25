package egovframework.dev.testStarter;

public class Gugudan {


	public static void main(String[] args) {

		int dan1 = 1;
		int dan2 = 2;
		int dan3 = 3;

		for(int i=1; i<=9; i++){

			System.out.printf("%d * %d = %d \t %d * %d = %d \t %d * %d = %d \n", dan1, i, dan1*i, dan2, i, dan2*i, dan3, i, dan3*i);

			if(i==9){
				System.out.printf("....[%d단] \t ....[%d단] \t ....[%d단]\n",dan1,dan2,dan3);
				i=0;
				dan1+=3;
				dan2+=3;
				dan3+=3;
			}
			if(dan3==12){
				break;
			}
		}



	}

}
