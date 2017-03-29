package com.satkuru.retailmanager.controller;


import com.google.gson.Gson;
import com.satkuru.retailmanager.AppConfig;
import com.satkuru.retailmanager.Application;
import com.satkuru.retailmanager.model.LatitudeLongitude;
import com.satkuru.retailmanager.model.Shop;
import com.satkuru.retailmanager.model.ShopAddress;
import com.satkuru.retailmanager.repository.ShopRepository;
import com.satkuru.retailmanager.util.GeoLocationImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
/**
 * Created by karthi on 28/03/2017.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WebAppConfiguration
public class ShopControllerTest {


    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    private Shop shop;

    private List<Shop> shopList = new ArrayList<>();
    @Autowired
    AppConfig myConfig;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

    }


    @Test
    public void postShouldReturnCreated() throws Exception {
        Shop shop1 = new Shop("Shop1",new ShopAddress("10","RM2 6ER"));

        mockMvc.perform(post("/shops/")
                .content(this.json(shop1))
                .contentType(contentType))
                .andExpect(status().isCreated());
    }

    @Test
    public void postShouldReturnUpdated() throws Exception {
        Shop shop1 = new Shop("Shop2",new ShopAddress("8","E14 5HQ"));
        Shop shop1edit = new Shop("Shop2",new ShopAddress("8","E14 5HQ"));
        mockMvc.perform(post("/shops/")
                .content(this.json(shop1))
                .contentType(contentType));
        mockMvc.perform(post("/shops/")
                .content(this.json(shop1edit))
                .contentType(contentType))
                .andExpect(status().isOk());
    }

    @Test
    public void getNearestShop() throws Exception {
        Shop shop1 = new Shop("Shop1",new ShopAddress("1","E14 5HQ"));
        Shop shop2 = new Shop("Shop2",new ShopAddress("1","EC2N 2DB"));
        Shop shop3 = new Shop("Shop3",new ShopAddress("2","RM2 6ER"));
        Shop shop4 = new Shop("Shop4",new ShopAddress("26","RM2 6ER"));

        LatitudeLongitude latLng = new GeoLocationImpl().getLatLng(myConfig.getApiKey(), shop4.getShopAddress().getNumber(), shop4.getShopAddress().getPostCode());
        mockMvc.perform(post("/shops/")
                .content(this.json(shop1))
                .contentType(contentType));
        mockMvc.perform(post("/shops/")
                .content(this.json(shop2))
                .contentType(contentType));
        mockMvc.perform(post("/shops/")
                .content(this.json(shop3))
                .contentType(contentType));
        MvcResult mvcResult1 = mockMvc.perform(get("/findNearest/").param("longitude", String.valueOf(latLng.lat)).param("latitude",String.valueOf(latLng.lng))).andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult1.getResponse().getContentAsString();
        Gson gson = new Gson();
        Shop shop = gson.fromJson(contentAsString, Shop.class);
        Assert.assertThat(shop.getName(),is(shop3.getName()));
    }

    protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}
