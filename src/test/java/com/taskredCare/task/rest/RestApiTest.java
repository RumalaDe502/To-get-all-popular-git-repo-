package com.taskredCare.task.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

    public class RestApiTest {

        @BeforeClass
        public static void setup() {
            RestAssured.baseURI = "http://localhost:8080/api";
        }
        @Test
        public void testGetRequest() {
            Response response = get("/popular-repos?size=100&date=2020-01-10&language=python");
            assertEquals(200, response.getStatusCode());
            System.out.println(response.getBody().asString());
        }
    }
