package utilities;

import java.awt.Desktop;
import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;
    String repName;
    public static final String REPORT_DIR = System.getProperty("user.dir") + "\\reports\\";

    public void onStart(ITestContext testcontext) {
        String timestamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        repName = "Test-Report-" + timestamp + ".html";
        String reportPath = REPORT_DIR + repName;

        sparkReporter = new ExtentSparkReporter(reportPath);
        sparkReporter.config().setDocumentTitle("Opencart Automation Report");
        sparkReporter.config().setReportName("Opencart Functional testing");
        sparkReporter.config().setTheme(Theme.DARK);

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application", "Opencart");
        extent.setSystemInfo("Module", "Admin");
        extent.setSystemInfo("Submodule", "Customers");
        extent.setSystemInfo("User Name", System.getProperty("user.name"));
        extent.setSystemInfo("Environment", "QA");

        String os = testcontext.getCurrentXmlTest().getParameter("os");
        extent.setSystemInfo("Operating System", os != null ? os : "Not Specified");

        String browser = testcontext.getCurrentXmlTest().getParameter("browser");
        extent.setSystemInfo("Browser", browser != null ? browser : "Not Specified");

        List<String> includedGroups = testcontext.getCurrentXmlTest().getIncludedGroups();
        if (includedGroups != null && !includedGroups.isEmpty()) {
            extent.setSystemInfo("Groups", includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result) {
        test = extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.PASS, result.getName() + " got successfully Executed");
    }

    public void onTestFailure(ITestResult result) {
        test = extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.FAIL, result.getName() + " got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        try {
            String imgPath = new BaseClass().captureScreen(result.getName());
            if (imgPath != null) {
                test.addScreenCaptureFromPath(imgPath);
            } else {
                test.log(Status.INFO, "Screenshot capture failed for " + result.getName());
            }
        } catch (Exception e) {
            test.log(Status.INFO, "Exception occurred while capturing screenshot: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void onTestSkipped(ITestResult result) {
        test = extent.createTest(result.getClass().getName());
        test.assignCategory(result.getMethod().getGroups());
        test.log(Status.SKIP, result.getName() + " got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());
    }

    public void onFinish(ITestContext testcontext) {
        extent.flush();
        String pathofExtentReport = REPORT_DIR + repName;
        File extentReport = new File(pathofExtentReport);

        try {
            if (extentReport.exists()) {
                Desktop.getDesktop().browse(extentReport.toURI());
                System.out.println("Extent Report generated successfully: " + pathofExtentReport);
            } else {
                System.out.println("Report file not found: " + pathofExtentReport);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}