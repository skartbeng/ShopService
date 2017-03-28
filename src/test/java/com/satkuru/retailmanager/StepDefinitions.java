package com.satkuru.retailmanager;

import com.satkuru.retailmanager.model.Shop;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class StepDefinitions {


    @Given("^A new shop$")
    public void a_new_shop( List<Shop> shops) throws Throwable {
        Assert.assertNotNull(shops);
        shops.forEach(System.out::println);
    }

    @When("^I call the rest service$")
    public void i_call_the_rest_service() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^there should be one new shop$")
    public void there_should_be_one_new_shop() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Given("^shop details$")
    public void shop_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }

    @Then("^it should update the shop details$")
    public void it_should_update_the_shop_details() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        throw new PendingException();
    }
}
