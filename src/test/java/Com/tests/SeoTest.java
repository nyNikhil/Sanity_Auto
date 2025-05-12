//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import zee.BaseTest;
//import zee.SEOPage;
//import zee.WriteClass;
//
//public class SeoTest {
//
//	public static void main(String[] args) {
//		BaseTest baseTest = new BaseTest();
//		baseTest.setup();
//		
//		List<String[]> results = new ArrayList<>();
//
//		// SEO Tag Testing
//		SEOPage seoPage = new SEOPage(baseTest.driver);
//		seoPage.testSEOAttributes();
//		results.addAll(seoPage.getResults());
//
//		// Writing results to Excel
//
//		try {
//		String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
//		WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
//		} catch (IOException e) {
//		System.err.println("Failed to write results to Excel: " + e.getMessage());
//
//		}
//		baseTest.teardown();
//
//		}
//		
//	}
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

import zee.BaseTest;
import zee.SEOPage;
import zee.WriteClass;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SeoTest extends BaseTest {

    private List<String[]> results;

    @BeforeClass
    public void init() {
        results = new ArrayList<>();
    }

    @Test
    public void validateSEOTags() {
        SEOPage seoPage = new SEOPage(driver);
        seoPage.testSEOAttributes();
        results.addAll(seoPage.getResults());
    }

    @AfterClass
    public void writeResultsToExcel() {
        try {
            String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
            WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
        } catch (IOException e) {
            System.err.println("Failed to write SEO results: " + e.getMessage());
        }
    }
}


