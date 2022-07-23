package PasswordLogic;

import com.maven.passwordsecurity.PasswordService;

public class runner {

	public static void main(String[] args) {
		PasswordService service=new PasswordService();
		String name1=service.encode("mukesh");
		System.out.println("byte code:"+name1);
		
		String name2=service.decode(name1);
		System.out.println("string code:"+name2);
	}

}
