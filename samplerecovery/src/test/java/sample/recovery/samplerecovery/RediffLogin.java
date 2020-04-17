package sample.recovery.samplerecovery;

import java.util.Hashtable;


import org.testng.SkipException;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import sample.recovery.samplerecovery.utils.Constants;
import sample.recovery.samplerecovery.utils.DataUtil;
import sample.recovery.samplerecovery.utils.Retry;


public class RediffLogin extends BaseTest {
	
//@Test(dataProvider="getData",retryAnalyzer = Retry.class)
@Test(dataProvider="getData")
	
	public void RediffLogintest(Hashtable<String, String> data) throws Exception{
		
		
		test.log(Status.INFO, "starting");

		if(DataUtil.isSkip(testName, xls)||data.get(Constants.RUNMODE_COL).equals(Constants.RUNMODE_NO)){
			test.log(Status.SKIP, "Test Case configured as No: "+ testName);
			throw new SkipException("Run mode is No");
		}
		
		System.out.println("Running Login test  ");	
		
		ds.executeKeywords(testName, xls, data);
	
}








}
