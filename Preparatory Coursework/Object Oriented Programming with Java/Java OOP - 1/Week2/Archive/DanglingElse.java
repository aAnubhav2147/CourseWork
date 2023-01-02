public class DanglingElse{
	public static void main(String[] args){
	int num = 9;
	if (num > 0)
	if (num < 10)
	System.out.println("aaa");
	else
	System.out.println("bbb");
	}
}