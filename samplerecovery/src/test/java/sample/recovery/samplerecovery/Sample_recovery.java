package sample.recovery.samplerecovery;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.Test;

import sample.recovery.samplerecovery.utils.Retry;

public class Sample_recovery {

	
	
	 @Test(retryAnalyzer = Retry.class)
	    public void testGenX() {
		 
		 Random ran = new Random();
		 int x = ran.nextInt(6);
		 if(x%2==0) {
			 System.out.println("####################>>>>X is "+x);
	        Assert.assertEquals("james", "james"); // ListenerTest fails
	    }
		 else {
			 Assert.assertEquals("james", "JamesFail"); // ListenerTest fails
		 }
	 }

	    @Test(retryAnalyzer = Retry.class,dependsOnMethods="testGenX")
	    public void testGenY() {
	        Assert.assertEquals("hello", "World"); // ListenerTest fails

	    }
}
