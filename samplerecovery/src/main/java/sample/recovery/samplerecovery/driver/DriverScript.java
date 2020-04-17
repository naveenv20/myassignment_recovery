package sample.recovery.samplerecovery.driver;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Hashtable;
import java.util.Properties;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sample.recovery.samplerecovery.keywords.AppKeywords;
import sample.recovery.samplerecovery.keywords.GenericKeywords;
import sample.recovery.samplerecovery.utils.Constants;
import sample.recovery.samplerecovery.utils.Xls_Reader;


public class DriverScript {

	
	public Properties envProp;
	public Properties prop;
	AppKeywords app;
	public ExtentTest test;



public AppKeywords getApp() {
		return app;
	}




	public void setApp(AppKeywords app) {
		this.app = app;
	}




public ExtentTest getTest() {
		return test;
	}




	public void setTest(ExtentTest test) {
		this.test = test;
	}




public void executeKeywords (String testName, Xls_Reader xls, Hashtable<String, String> data) {
	int rows=xls.getRowCount(Constants.KEYWORDS_SHEET);
System.out.println("num of rows "+ rows);

app=new AppKeywords();
//send properties to keywords class
app.setEnvProp(envProp);
app.setProp(prop);
//send the hashtable data aswell to keywords class
app.setData(data);
//send the extent test object to keywords class, for logging there
app.setTest(test);
for(int rNum=2;rNum<=rows;rNum++) {
String tcid=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.TCID_COL, rNum);
if(tcid.equalsIgnoreCase(testName)) {
String keyword=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.KEYWORD_COL, rNum);
String objectkey=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.OBJECT_COL , rNum);
String datakey=xls.getCellData(Constants.KEYWORDS_SHEET, Constants.DATA_COL, rNum);
String datavalue=data.get(datakey);
String ProceedonFail=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.PROCEED_COL,rNum);
String ReRun=xls.getCellData(Constants.KEYWORDS_SHEET,Constants.ReRun_COL,rNum);
//System.out.println(tcid+"--"+keyword+"--"+objectkey+"--"+datakey+"--"+datavalue+"--");
/******* if we need log into extent reports then use the test object***************/
//test.log(Status.INFO, tcid+"--"+keyword+"--"+objectkey+"--"+datakey+"--"+datavalue+"--");
//System.out.println("Object key has the value as : "+prop.getProperty(objectkey));


app.setDatakey(datakey);
app.setObjectkey(objectkey);
app.setProceedonFail(ProceedonFail);
GenericKeywords.setRerun(ReRun);

//Use Reflections api method to invoke the methods
Method method;


		try {
			method=app.getClass().getMethod(keyword);
			method.invoke(app);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			test.log(Status.INFO, "Issue in invoking method : " + keyword +" at row number : "+rNum);
			app.reportFailure( "Issue in invoking method : " + keyword +" at row number : "+rNum);
		} catch(Exception e){
			e.printStackTrace();
			test.log(Status.FAIL, "Other than method type : Issue in invoking method : " + keyword +" at row number : "+rNum);
			app.assertall();
		}
	
		
	
	
	
	
//	catch (Exception e) {
//		// TODO Auto-generated catch block
//		test.log(Status.ERROR, "Issue in invoking method : " + keyword +"at row number : "+rNum);
////		String ee=e.getStackTrace().toString();		
////		test.log(Status.ERROR, ee);
////		StringWriter sw = new StringWriter();
////		PrintWriter pw = new PrintWriter(sw);
////		e.printStackTrace(pw);
////		test.log(Status.FAIL, sw.toString());
////			
//		app.reportFailure( "Issue in invoking method : " + keyword +"at row number : "+rNum);
//	} 
	


/*
if(keyword.equals("openBrowser")) {
	app.openBrowser();
}

else if(keyword.equals("navigate")) {
	app.navigate();
}

else if(keyword.equals("input")) {
	app.type();
}

else if(keyword.equals("click")) {
	app.click();
}
*/
}
}
app.assertall();
}




public Properties getEnvProp() {
	return envProp;
}




public void setEnvProp(Properties envProp) {
	this.envProp = envProp;
}




public Properties getProp() {
	return prop;
}




public void setProp(Properties prop) {
	this.prop = prop;
}
	

public void quit(){
	if(app!=null){
		app.quit();
	}
}

}
