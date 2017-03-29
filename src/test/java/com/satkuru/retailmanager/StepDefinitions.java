package com.satkuru.retailmanager;

import com.satkuru.retailmanager.model.Shop;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StepDefinitions {
    String serviceResourceUrl = "http://localhost:8080/shops";
    private List<Shop> newshops=null;

    @Before
    public void  setup(){

    }
    @Autowired
    private RestTemplate restTemplate;

    @Given("^A new shop$")
    public void a_new_shop( DataTable shopsTable) throws Throwable {
        newshops = extractShopListFromDataTable(shopsTable);
    }



    @When("^I call the rest service$")
    public void i_call_the_rest_service() throws Throwable {
        ResponseEntity<Shop> responseEntity = restTemplate.exchange(serviceResourceUrl, HttpMethod.POST, new HttpEntity<Shop>(newshops.get(0)), Shop.class);
        Assert.assertThat(responseEntity.getStatusCode(),is(HttpStatus.CREATED));
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

    private Map<String, Shop> extractShopMapFromDataTable(DataTable shopsTable) {
        Assert.assertNotNull(shopsTable);
        List<Map<String, String>> maps = shopsTable.asMaps(String.class, String.class);
        Map<String,Shop> shops = new HashMap<>();
        for (Map<String,String> map:maps) {
            shops.put(map.get("name"),new Shop(map.get("name"),map.get("number"),map.get("postCode")));
        }
        return shops;
    }
    private List<Shop> extractShopListFromDataTable(DataTable shopsTable) {
        Assert.assertNotNull(shopsTable);
        List<Map<String, String>> maps = shopsTable.asMaps(String.class, String.class);
        List<Shop> shops = new ArrayList<>(maps.size());
        for (Map<String,String> map:maps) {
            shops.add(new Shop(map.get("name"),map.get("number"),map.get("postCode")));
        }
        return shops;
    }
}
