//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import zee.BaseTest;
//import zee.GAValidationPage;
//import zee.WriteClass;
//
//public class GATest {
//
//	public static void main(String[] args) {
//		BaseTest baseTest = new BaseTest();
//		baseTest.setup();
//
//		List<String[]> results = new ArrayList<>();
//
//		// Google Analytics Testing
//
//		GAValidationPage gaPage = new GAValidationPage(baseTest.driver);
//		gaPage.validateGATrackingIDs();
//		results.addAll(gaPage.getResults());
//
//		// Writing results to Excel
//
//		try {
//			String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
//			WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
//		} catch (IOException e) {
//			System.err.println("Failed to write results to Excel: " + e.getMessage());
//		}
//
//		baseTest.teardown();
//
//	}
//}


import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import zee.BaseTest;
import zee.GAValidationPage;
import zee.WriteClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GATest extends BaseTest {

    private List<String[]> results;

    @BeforeClass
    public void init() {
        results = new ArrayList<>();
    }

    @Test
    public void validateGoogleAnalyticsTags() {
        GAValidationPage gaPage = new GAValidationPage(driver);
        gaPage.validateGATrackingIDs();
        results.addAll(gaPage.getResults());
    }

    @AfterClass
    public void writeResultsToExcel() {
        try {
            String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
            WriteClass.writeResultsToExcel("GA Validation Results", headers, results, true);
        } catch (IOException e) {
            System.err.println("Failed to write GA results: " + e.getMessage());
        }
    }
}

