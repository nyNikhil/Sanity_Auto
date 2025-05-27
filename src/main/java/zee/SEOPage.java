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
				"https://zeenews.india.com/india/new-chapter-in-india-canada-relations-jaishankar-anand-hold-talks-to-improve-ties-2906345.html",
				"https://zeenews.india.com/videos",
				"https://zeenews.india.com/video/news/dna-army-chief-asim-munir-who-has-become-field-marshal-in-pakistan-is-creating-new-dramas-every-day-2906006.html",
				"https://zeenews.india.com/photos/india/6-books-you-should-read-if-you-are-a-classics-lover-from-metamorphosis-to-time-machine-2906675/metamorphosis-2906682",
				"https://zeenews.india.com/photos"
				
				
				
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
		else if (url.equals("https://zeenews.india.com/india/new-chapter-in-india-canada-relations-jaishankar-anand-hold-talks-to-improve-ties-2906345.html")) {
			expectedValues.put("viewport", "width=device-width, initial-scale=1");
			expectedValues.put("robots", "max-image-preview:large");
			expectedValues.put("theme-color", "#345678");
			expectedValues.put("description", "The phone call between Jaishankar and Anand marks a renewed effort to strengthen bilateral relations, potentially paving the way for high-level visits and cooperation in areas such as Trade and Investment, Diplomatic Engagement, and Multilateral Cooperation.");
			expectedValues.put("keywords", "Dr S Jaishankar, Anita Anand, India, Canada, G7 ,Dr S Jaishankar, Anita Anand, India, Canada, G7");
			expectedValues.put("og:type", "website");
			expectedValues.put("og:site_name", "Zee News");
			expectedValues.put("og:title", "New Chapter In India-Canada Relations? Jaishankar-Anand Hold Talks To Improve Ties");
			expectedValues.put("og:description", "The phone call between Jaishankar and Anand marks a renewed effort to strengthen bilateral relations, potentially paving the way for high-level visits and cooperation in areas such as Trade and Investment, Diplomatic Engagement, and Multilateral Cooperation.");
			expectedValues.put("og:url", "https://zeenews.india.com/india/new-chapter-in-india-canada-relations-jaishankar-anand-hold-talks-to-improve-ties-2906345.html");
			expectedValues.put("og:image", "https://english.cdn.zeenews.com/sites/default/files/2025/05/26/1756496-mixcollage-26-may-2025-09-53-am-9217.jpg");
			expectedValues.put("canonical", "https://zeenews.india.com/india/new-chapter-in-india-canada-relations-jaishankar-anand-hold-talks-to-improve-ties-2906345.html");
		}
		else if (url.equals("https://zeenews.india.com/videos")) {  
			expectedValues.put("viewport", "width=device-width, initial-scale=1");
			expectedValues.put("robots", "max-image-preview:large");
			expectedValues.put("description", "Latest News Videos: Watch videos on headlines & live updates, watch latest news video online, live news video clips online, video news covers all breaking news on politics, business, sports, bollywood, technology & health from India & the world at Zee News");
			expectedValues.put("keywords", "Latest News Video Online, Watch News Headlines, Video On Breaking News, Live Updates Video On Politics, Online Videos On News, Sports Videos, News Videos, News Headlines Videos, Fashion Videos, Event Videos, Video Gallery");
			expectedValues.put("og:type", "website");
			expectedValues.put("og:site_name", "Zee News");
			expectedValues.put("og:title", "Videos");
			expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events");
			expectedValues.put("og:url", "https://zeenews.india.com/videos");
			expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
			expectedValues.put("canonical", "https://zeenews.india.com/videos");
		}
		else if (url.equals("https://zeenews.india.com/video/news/dna-army-chief-asim-munir-who-has-become-field-marshal-in-pakistan-is-creating-new-dramas-every-day-2906006.html")) {  
			expectedValues.put("viewport", "width=device-width, initial-scale=1");
			expectedValues.put("robots", "max-image-preview:large");
			expectedValues.put("description", "Army Chief Asim Munir, who has become Field Marshal in Pakistan, is creating new dramas every day. Today Asim Munir has given a gift to the President and Prime Minister of the country. Everyone is talking about this gift. Watch video on Zee News");
			expectedValues.put("keywords", "dna with raul sinha, army chief asim munir, who has become Field Marshal in Pakistan, Pakistan news, Asim Munir news, given a gift to the President, Prime Minister of the country, DNA, DNA, Ranul Sinha live now, rahul Sinha today, Zee News live, asim munir, Pakistan, Shehbaz Sharif, Operation Sindoor, Pahalgam Terror Attack, Asim Munir Field Marshal, Asim munir promoted field marshal, Asim Munir family");
			expectedValues.put("og:type", "website");
			expectedValues.put("og:site_name", "Zee News");
			expectedValues.put("og:title", "DNA: Army Chief Asim Munir, who has become Field Marshal in Pakistan, is creating new dramas every day");
			expectedValues.put("og:description", "Army Chief Asim Munir, who has become Field Marshal in Pakistan, is creating new dramas every day. Today Asim Munir has given a gift to the President and Prime Minister of the country. Everyone is talking about this gift. Watch video on Zee News");
			expectedValues.put("og:url", "https://zeenews.india.com/video/news/dna-army-chief-asim-munir-who-has-become-field-marshal-in-pakistan-is-creating-new-dramas-every-day-2906006.html");
			expectedValues.put("og:image", "https://english.cdn.zeenews.com/sites/default/files/2025/05/25/00000003_7.jpg");
			expectedValues.put("canonical", "https://zeenews.india.com/video/news/dna-army-chief-asim-munir-who-has-become-field-marshal-in-pakistan-is-creating-new-dramas-every-day-2906006.html");
		}
		else if (url.equals("https://zeenews.india.com/photos/india/6-books-you-should-read-if-you-are-a-classics-lover-from-metamorphosis-to-time-machine-2906675/metamorphosis-2906682")) {  
			expectedValues.put("viewport", "width=device-width, initial-scale=1");
			expectedValues.put("robots", "max-image-preview:large");
			expectedValues.put("description","<p>Old stories are kept alive in classic literature, inviting readers to revisit them as many times as they want. Check out seven books every classic literature enthusiast should read:</p>\r\n"
					+ "\r\n"
					+ "<p> </p>\r\n");
			expectedValues.put("keywords", "news, latest news, today news, breaking news, news headlines, bollywood news, India news, top news, political news, business news, technology news, sports news");
			expectedValues.put("og:type", "website");
			expectedValues.put("og:site_name", "Zee News");
			expectedValues.put("og:title", "6 Books You Should Read If You Are A Classics Lover - From Metamorphosis To Time Machine");
			expectedValues.put("og:description", "<p>Old stories are kept alive in classic literature, inviting readers to revisit them as many times as they want. Check out seven books every classic literature enthusiast should read:</p>\r\n"
					+ "\r\n"
					+ "<p> </p>\r\n");
			expectedValues.put("og:url", "https://zeenews.india.com/photos/india/6-books-you-should-read-if-you-are-a-classics-lover-from-metamorphosis-to-time-machine-2906675");
			expectedValues.put("og:image", "https://english.cdn.zeenews.com/sites/default/files/2025/05/26/1756925-slide-7.png?im=FitAndFill=(1200,900)");
			expectedValues.put("canonical", "https://zeenews.india.com/");
		}
		else if (url.equals("https://zeenews.india.com/photos")) {  
			expectedValues.put("viewport", "width=device-width, initial-scale=1");
			expectedValues.put("robots", "max-image-preview:large");
			expectedValues.put("description", "Latest News Photo Gallery: Check out top news photo, latest news picture, exclusives photos, best news photos at Zee News. Stay connected to know more about Latest photo galleries, sports photos, event photos, fashions photos");
			expectedValues.put("keywords", "Latest News Photo, Photo Gallery Of Top News, News Pictures, Latest Photos Galleries On News, Online Photos, Photos, Fashion Photos, News Photos, Sports Photos, Entertainment Photos, Entertainment Photo Gallery, Sports Photo Gallery, Lifestyle Photo Gallery, News Photo Gallery, Bollywood Photo Gallery, Event Photos, Image Gallery");
			expectedValues.put("og:type", "website");
			expectedValues.put("og:site_name", "Zee News");
			expectedValues.put("og:title", "PhotoGallery");
			expectedValues.put("og:description", "Zee News brings latest news from India and World on breaking news, today news headlines, politics, business, technology, bollywood, entertainment, sports and others. Find exclusive news stories on Indian politics, current affairs, cricket matches, festivals and events");
			expectedValues.put("og:url", "https://zeenews.india.com/photos");
			expectedValues.put("og:image", "https://english.cdn.zeenews.com/images/logo/placeholder_image.jpg");
			expectedValues.put("canonical", "https://zeenews.india.com/photos");
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

