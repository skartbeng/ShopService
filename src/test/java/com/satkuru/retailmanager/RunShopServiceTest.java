package com.satkuru.retailmanager;

/**
 * Created by karthi on 28/03/2017.
 */
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
public class RunShopServiceTest {
}
