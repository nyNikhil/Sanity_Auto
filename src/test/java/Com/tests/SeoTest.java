package Com.tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import zee.BaseTest;
import zee.SEOPage;
import zee.WriteClass;

public class SeoTest {


	@Test
	public void seoTest() {
		BaseTest baseTest = new BaseTest();
		baseTest.setup();

		List<String[]> results = new ArrayList<>();

		// SEO Tag Testing
		SEOPage seoPage = new SEOPage(baseTest.driver);
		seoPage.testSEOAttributes();
		results.addAll(seoPage.getResults());

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
