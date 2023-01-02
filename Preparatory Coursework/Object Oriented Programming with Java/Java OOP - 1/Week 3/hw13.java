public class hw13{
	public static void main(String[] args){
	int upNext = 1332;
	switch (upNext){
	case 1:
	     upNext -= 1;
	case 3:
	     upNext -= 2;
	case 13:
	     upNext -= 3;
	case 1332:
	     upNext++ ;
	case 1333:
	     upNext-- ;                     
	}
	System.out.println(upNext);
	}
}