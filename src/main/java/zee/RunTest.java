package zee;

import java.util.*;

import java.io.IOException;

public class RunTest {
    public static void main(String[] args) {
        BaseTest baseTest = new BaseTest();
        baseTest.setup();

        List<String[]> results = new ArrayList<>();

        // SEO Tag Testing
        SEOPage seoPage = new SEOPage(baseTest.driver);
        seoPage.testSEOAttributes();
        results.addAll(seoPage.getResults());

        // Google Analytics Testing
        GAValidationPage gaPage = new GAValidationPage(baseTest.driver);
        gaPage.validateGATrackingIDs();
        results.addAll(gaPage.getResults());

        // Writing results to Excel
        try {
            String[] headers = {"URL", "Tag Name", "Expected Content", "Actual Content", "Status"};
            WriteClass.writeResultsToExcel("SEO Validation Results", headers, results, true);
        } catch (IOException e) {
            System.err.println("Failed to write results to Excel: " + e.getMessage());
        }

        baseTest.teardown();
    }
}


