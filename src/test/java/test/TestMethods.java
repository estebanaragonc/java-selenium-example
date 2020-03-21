package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import core.BaseTest;
import pages.CruiseSearch;
import pages.Home;
import pages.Itinerary;

public class TestMethods extends BaseTest{

	@Test(description = "TC1 - Validate results in grid style")
    public void TC1_ValidateCruiseResultListInGridStyle() {
		
		//page objects
        Home home = new Home(getDriver());
        CruiseSearch cruiseSearch = new CruiseSearch(getDriver());
        
        //click close in modal opened
        home.dismissModals();
        //set the filters in home page
        home.clickSailTo();     
        home.selectSailOptionByText("The Bahamas");
        home.clickSailFrom();
        //home.selectRandomEnabledSailFromOption();
        home.selectSailFromOptionByText("Baltimore, MD");
        home.clickDates();
        home.selectAvailableMonthsFromYear("2021");
        home.clickDuration();
        home.selectDurationByRange("6 - 9 Days");
        //click search cruises
        home.clickSearchCruises();
        
        //validation if grid is activated
        Assert.assertTrue(cruiseSearch.isGridSelected());        
    }
	
	@Test(description = "Validate the result list are affected by the 'price' filter")
	public void TC2_ValidateResultsListIsAffectedByThePriceFilter()
	{
		
		//page objects
        Home home = new Home(getDriver());
        CruiseSearch cruiseSearch = new CruiseSearch(getDriver());        
        //click close in modal opened
        home.dismissModals();
        //set the filters in home page
        home.clickSailTo();     
        home.selectSailOptionByText("The Bahamas");
        home.clickSailFrom();
        //home.selectRandomEnabledSailFromOption();
        home.selectSailFromOptionByText("Charleston, SC");
        home.selectSailFromOptionByText("Baltimore, MD");
        home.clickDates();
        home.selectAvailableMonthsFromYear("2021");
        home.clickDuration();
        home.selectDurationByRange("6 - 9 Days");
        //click search cruises
        home.clickSearchCruises();
        
        // count number of cards displayed in grid
        int totalResultList = cruiseSearch.countTotalCruisesResults();
        int lastCardAmount = cruiseSearch.getLastCardAmount();
        //click in the link 'pricing' in the filter section
        cruiseSearch.clickPricing();
        //move the filter to dismiss last card. Example if last card amount = 530, move filter to 520 and get new result
        cruiseSearch.adjustMaximumPricePerPersonTo(lastCardAmount);
        
        int resultListUpdated = cruiseSearch.countTotalCruisesResults();
        
        //validate the last card was deleted in the result, they should not be the same
        Assert.assertNotEquals(resultListUpdated, totalResultList);                
	}
	
	@Test(description = "Validate the result list is sorted by price")
	public void TC3_ValidateTheResultListIsSortedByPrice()
	{
		//page objects
        Home home = new Home(getDriver());
        CruiseSearch cruiseSearch = new CruiseSearch(getDriver());        
        //click close in modal opened
        home.dismissModals();
        //set the filters in home page
        home.clickSailTo();     
        home.selectSailOptionByText("The Bahamas");
        home.clickSailFrom();
        //home.selectRandomEnabledSailFromOption();
        home.selectSailFromOptionByText("Charleston, SC");
        home.selectSailFromOptionByText("Baltimore, MD");
        home.clickDates();
        home.selectAvailableMonthsFromYear("2021");
        home.clickDuration();
        home.selectDurationByRange("6 - 9 Days");
        //click search cruises
        home.clickSearchCruises();
        
        // Validate the result list is sorted by price
        Assert.assertTrue(cruiseSearch.isResultListSortedByPrice());
	}
	
	@Test(description = "Validate itinerary page is loaded")
	public void TC4_validateItineraryPageIsLoaded()
	{
		//page objects
        Home home = new Home(getDriver());
        CruiseSearch cruiseSearch = new CruiseSearch(getDriver());   
        Itinerary itinerary = new Itinerary(getDriver()); 
        //click close in modal opened
        home.dismissModals();       
        //click search cruises and use default filter, all cruises will be displayed
        home.clickSearchCruises();
        
        cruiseSearch.seletFirstCruiseInResultList();
        
        // Validate the itinerary page was loaded contains all its sections
        Assert.assertTrue(itinerary.isItineraryMenuDisplayed());
        Assert.assertTrue(itinerary.isItineraryDaysSectionDisplayed());
        Assert.assertTrue(itinerary.isItineraryActivitiesSectionDisplayed());
        Assert.assertTrue(itinerary.isItineraryDiningSectionDisplayed());
        Assert.assertTrue(itinerary.isItineraryStateRoomSectionDisplayed());
        Assert.assertTrue(itinerary.isItineraryMoreDatesSectionDisplayed());       
        
        
	}
		
	@Test(description = "Check the 'book now' button in itinerary page")
	public void TC5_checkTheBookNowButtonInItineraryPage()
	{
		//page objects
        Home home = new Home(getDriver());
        CruiseSearch cruiseSearch = new CruiseSearch(getDriver());   
        Itinerary itinerary = new Itinerary(getDriver()); 
        //click close in modal opened
        home.dismissModals();       
        //click search cruises and use default filter, all cruises will be displayed
        home.clickSearchCruises();
        
        cruiseSearch.seletFirstCruiseInResultList();
        
        // Validate the itinerary page was loaded using the book now button
        Assert.assertTrue(itinerary.isBookNowPresentInPage());
	}

}
