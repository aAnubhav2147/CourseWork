package domain;

public class ExceptionTest {

	public static void main(String[] args)throws Exception {
		int[]arr = new int[10];
		try{
			int a  = arr[9];// You can change subscript for avoiding exception
			System.out.println(a);
		}catch(ArrayIndexOutOfBoundsException ex){
			throw new Exception("out of bounds",ex);
		}finally{
			System.out.println("validate that finally is running");
		}
    }

}