//package zee;
//
//import org.openqa.selenium.*;
//import java.util.*;
//
//public class SEOPage {
//    private WebDriver driver;
//    private List<String[]> results = new ArrayList<>();
//
//    public SEOPage(WebDriver driver) {
//        this.driver = driver;
//    }
//
//    public void testSEOAttributes() {
//        List<String> urls = Arrays.asList(
//            "https://zeenews.india.com/",
//        	"https://www.dnaindia.com/"
//           
//        );
//
//        for (String url : urls) {
//            Map<String, String> expectedValues = getExpectedValues(url);
//            if (expectedValues != null) {
//                System.out.println("Testing SEO for: " + url);
//                checkSEOAttributes(url, expectedValues);
//            }
//        }
//    }
//
//    private Map<String, String> getExpectedValues(String url) {
//        Map<String, String> expectedValues = new HashMap<>();
//
//        if (url.equals("https://zeenews.india.com/")) {
//            expectedValues.put("viewport", "width=device-width, initial-scale=1");
//            expectedValues.put("robots", "max-image-preview:large");
//            expectedValues.put("theme-color", "#345678");
//            expectedValues.put("description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
//            expectedValues.put("keywords", "news, latest news, today news, breaking news, news headlines, bollywood news, India news, top news, political news, business news, technology news, sports news");
//            expectedValues.put("og:type", "website");
//            expectedValues.put("og:site_name", "Zee News");
//            expectedValues.put("og:title", "Zee News: Latest News, Live Breaking News, Today News, India Political News Updates");
//            expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
//            expectedValues.put("og:url", "https://zeenews.india.com");
//            expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
//            expectedValues.put("canonical", "https://zeenews.india.com/");
//        }
//
//        return expectedValues.isEmpty() ? null : expectedValues;
//    }
//    if (url.equals("https://www.dnaindia.com/")) {
//        expectedValues.put("viewport", "width=device-width, initial-scale=1, maximum-scale=1");
//        expectedValues.put("robots", "max-image-preview:large");
//        expectedValues.put("description", "Latest News Headlines and Live Updates - DNA India Covers all latest and breaking news on Politics, Business, Sports, Bollywood, technology &amp; health from India &amp; the world at Daily News &amp; Analysis");
//        expectedValues.put("keywords", "news, latest news, breaking news, live news, live updates, bollywood news, health news, news headlines, India news, top news, political news, business news, technology news, sports news");
//        expectedValues.put("og:type", "website");
//        expectedValues.put("og:site_name", "DNA India");
//        expectedValues.put("og:title", "DNA India | Latest News, Live Breaking News on India, Politics, World, Business, Sports, Bollywood");
//        expectedValues.put("og:description", "Latest News Headlines and Live Updates - DNA India Covers all latest and breaking news on Politics, Business, Sports, Bollywood, technology &amp; health from India &amp; the world at Daily News &amp; Analysis");
//        expectedValues.put("og:url", "https://www.dnaindia.com");
//        expectedValues.put("og:image", "https://cdn.dnaindia.com/images/2018/DNA-1200.png");
//        expectedValues.put("canonical", "https://www.dnaindia.com");
//    }
//
//    return expectedValues.isEmpty() ? null : expectedValues;
//}
//
//    private void checkSEOAttributes(String url, Map<String, String> expectedValues) {
//        try {
//            driver.get(url);
//            List<WebElement> metaTags = driver.findElements(By.tagName("meta"));
//
//            for (WebElement metaTag : metaTags) {
//                String name = metaTag.getAttribute("name");
//                String property = metaTag.getAttribute("property");
//                String content = metaTag.getAttribute("content");
//
//                String key = (name != null && !name.isEmpty()) ? name : property;
//                if (key != null && expectedValues.containsKey(key)) {
//                    String expected = expectedValues.get(key);
//                    String status = expected.equals(content) ? "PASS" : "FAIL";
//                    results.add(new String[]{url, key, expected, content, status});
//                }
//            }
//
//            // Check for canonical link
//            List<WebElement> linkTags = driver.findElements(By.tagName("link"));
//            for (WebElement linkTag : linkTags) {
//                String rel = linkTag.getAttribute("rel");
//                String href = linkTag.getAttribute("href");
//
//                if ("canonical".equalsIgnoreCase(rel)) {
//                    String expected = expectedValues.get("canonical");
//                    if (expected != null) {
//                        String status = expected.equals(href) ? "PASS" : "FAIL";
//                        results.add(new String[]{url, "Canonical Link", expected, href, status});
//                    }
//                }
//            }
//        } catch (Exception e) {
//            results.add(new String[]{url, "SEO Test", "N/A", "N/A", "Error: " + e.getMessage()});
//        }
//    }
//
//    public List<String[]> getResults() {
//        return results;
//    }
//}
package zee;

import org.openqa.selenium.*;

import java.util.*;

public class SEOPage {
    private WebDriver driver;
    private List<String[]> results = new ArrayList<>();

    public SEOPage(WebDriver driver) {
        this.driver = driver;
    }

    public void testSEOAttributes() {
        List<String> urls = Arrays.asList(
            "https://zeenews.india.com/",
            "https://zeenews.india.com/india",
            "https://zeenews.india.com/india/supreme-court-stays-ed-probe-into-tamil-nadus-tasmac-issues-notice-to-agency-2904591.html"
//            "https://www.dnaindia.com/"
        );

        for (String url : urls) {
            Map<String, String> expectedValues = getExpectedValues(url);
            if (expectedValues != null) {
                System.out.println("Testing SEO for: " + url);
                checkSEOAttributes(url, expectedValues);
            }
        }
    }

    private Map<String, String> getExpectedValues(String url) {
        Map<String, String> expectedValues = new HashMap<>();

        if (url.equals("https://zeenews.india.com/")) {
            expectedValues.put("viewport", "width=device-width, initial-scale=1");
            expectedValues.put("robots", "max-image-preview:large");
            expectedValues.put("theme-color", "#345678");
            expectedValues.put("description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
            expectedValues.put("keywords", "news, latest news, today news, breaking news, news headlines, bollywood news, India news, top news, political news, business news, technology news, sports news");
            expectedValues.put("og:type", "website");
            expectedValues.put("og:site_name", "Zee News");
            expectedValues.put("og:title", "Zee News: Latest News, Live Breaking News, Today News, India Political News Updates");
            expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events.");
            expectedValues.put("og:url", "https://zeenews.india.com");
            expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
            expectedValues.put("canonical", "https://zeenews.india.com/");
        } 
        else if (url.equals("https://www.dnaindia.com/")) {  // Moved inside method
            expectedValues.put("viewport", "width=device-width, initial-scale=1, maximum-scale=1");
            expectedValues.put("robots", "max-image-preview:large");
            expectedValues.put("description", "Latest News Headlines and Live Updates - DNA India Covers all latest and breaking news on Politics, Business, Sports, Bollywood, technology &amp; health from India &amp; the world at Daily News &amp; Analysis");
            expectedValues.put("keywords", "news, latest news, breaking news, live news, live updates, bollywood news, health news, news headlines, India news, top news, political news, business news, technology news, sports news");
            expectedValues.put("og:type", "website");
            expectedValues.put("og:site_name", "DNA India");
            expectedValues.put("og:title", "DNA India | Latest News, Live Breaking News on India, Politics, World, Business, Sports, Bollywood");
            expectedValues.put("og:description", "Latest News Headlines and Live Updates - DNA India Covers all latest and breaking news on Politics, Business, Sports, Bollywood, technology &amp; health from India &amp; the world at Daily News &amp; Analysis");
            expectedValues.put("og:url", "https://www.dnaindia.com");
            expectedValues.put("og:image", "https://cdn.dnaindia.com/images/2018/DNA-1200.png");
            expectedValues.put("canonical", "https://www.dnaindia.com/");
        }
        else if (url.equals("https://zeenews.india.com/india")) {
        	 expectedValues.put("viewport", "width=device-width, initial-scale=1");
             expectedValues.put("robots", "max-image-preview:large");
             expectedValues.put("theme-color", "#345678");
             expectedValues.put("description", "India News, National News: Zee News brings latest news from India including breaking news, current India news live, political news, Indian sports news, and news headlines which gives you exclusive information about all that happening in India.");
             expectedValues.put("keywords", "India News, latest news india,national news, india news live, breaking news india, current news india, news headlines india");
             expectedValues.put("og:type", "website");
             expectedValues.put("og:site_name", "Zee News");
             expectedValues.put("og:title", "India News, National News, Latest News India, India News Live, Breaking News India, News Headlines India: Zee News");
             expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events");
             expectedValues.put("og:url", "https://zeenews.india.com/india");
             expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
             expectedValues.put("canonical", "https://zeenews.india.com/india");
        }
        else if (url.equals("https://zeenews.india.com/india/supreme-court-stays-ed-probe-into-tamil-nadus-tasmac-issues-notice-to-agency-2904591.html")) {
        	 expectedValues.put("viewport", "width=device-width, initial-scale=1");
             expectedValues.put("robots", "max-image-preview:large");
             expectedValues.put("theme-color", "#345678");
             expectedValues.put("description", "The stay was issued in response to a petition filed by the Tamil Nadu government, which challenged the recent raids conducted by the central agency at various TASMAC locations.");
             expectedValues.put("keywords", "Tasmac,Tasmac");
             expectedValues.put("og:type", "website");
             expectedValues.put("og:site_name", "Zee News");
             expectedValues.put("og:title", "Supreme Court Stays ED Probe Into Tamil Nadus TASMAC; Issues Notice To Agency");
             expectedValues.put("og:description", "The stay was issued in response to a petition filed by the Tamil Nadu government, which challenged the recent raids conducted by the central agency at various TASMAC locations.");
             expectedValues.put("og:url", "https://zeenews.india.com/india/supreme-court-stays-ed-probe-into-tamil-nadus-tasmac-issues-notice-to-agency-2904591.html");
             expectedValues.put("og:image", "https://english.cdn.zeenews.com/sites/default/files/2025/05/22/1754129-mixcollage-20-may-2025-11-38-am-6830.jpg");
             expectedValues.put("canonical", "https://zeenews.india.com/india/supreme-court-stays-ed-probe-into-tamil-nadus-tasmac-issues-notice-to-agency-2904591.html");
        }

        return expectedValues.isEmpty() ? null : expectedValues;
    }

    private void checkSEOAttributes(String url, Map<String, String> expectedValues) {
        try {
            driver.get(url);
            List<WebElement> metaTags = driver.findElements(By.tagName("meta"));

            for (WebElement metaTag : metaTags) {
                String name = metaTag.getAttribute("name");
                String property = metaTag.getAttribute("property");
                String content = metaTag.getAttribute("content");

                String key = (name != null && !name.isEmpty()) ? name : property;
                if (key != null && expectedValues.containsKey(key)) {
                    String expected = expectedValues.get(key);
                    String status = expected.equals(content) ? "PASS" : "FAIL";
                    results.add(new String[]{url, key, expected, content, status});
                }
            }

            // Check for canonical link
            List<WebElement> linkTags = driver.findElements(By.tagName("link"));
            for (WebElement linkTag : linkTags) {
                String rel = linkTag.getAttribute("rel");
                String href = linkTag.getAttribute("href");

                if ("canonical".equalsIgnoreCase(rel)) {
                    String expected = expectedValues.get("canonical");
                    if (expected != null) {
                        String status = expected.equals(href) ? "PASS" : "FAIL";
                        results.add(new String[]{url, "Canonical Link", expected, href, status});
                    }
                }
            }
        } catch (Exception e) {
            results.add(new String[]{url, "SEO Test", "N/A", "N/A", "Error: " + e.getMessage()});
        }
    }

    public List<String[]> getResults() {
        return results;
    }
}

