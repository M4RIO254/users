package com.ntt.usersManager;



import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ntt.usersManager.UsersManagerApplication;

@ExtendWith(value = { SpringExtension.class })
public class ApplicationStartTest {

	 @Test
	  public void appStartsTest() {
		 UsersManagerApplication.main(new String[] {});
	  }
}
