package com.satkuru.retailmanager;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.google.gson.Gson;
import com.satkuru.retailmanager.model.Shop;
import cucumber.api.DataTable;
import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.willReturn;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class StepDefinitions {
    String serviceResourceUrl = "http://localhost:8080/shops";
    private List<Shop> newshops=null;
    private CloseableHttpClient httpClient;
    private WireMockServer wireMockServer;

    @Before
    public void  setup(){
        wireMockServer = new WireMockServer();
        httpClient = HttpClients.createDefault();
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(post(urlEqualTo("/create")).withHeader("content-type", equalTo("application/json")).withRequestBody(containing("shop")).willReturn(aResponse().withStatus(201)));


    }

    @After
    public void cleanup(){
        wireMockServer.stop();
    }

    @Given("^A new shop$")
    public void a_new_shop( DataTable shopsTable) throws Throwable {
        newshops = extractShopListFromDataTable(shopsTable);
    }



    @When("^I call the rest service$")
    public void i_call_the_rest_service() throws Throwable {
        HttpPost request = new HttpPost(serviceResourceUrl);
        StringEntity entity = new StringEntity(this.json(newshops.get(0)));
        request.addHeader("content-type", "application/json");
        request.setEntity(entity);
        HttpResponse response = httpClient.execute(request);
        assertEquals(201, response.getStatusLine().getStatusCode());
        verify(postRequestedFor(urlEqualTo("/shops")).withHeader("content-type", equalTo("application/json")));
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
    protected String json(Object o) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(o);
        return json;
    }
}
