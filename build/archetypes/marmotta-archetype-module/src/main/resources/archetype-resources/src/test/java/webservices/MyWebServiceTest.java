/**
 * Copyright (C) 2013 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ${package}.webservices;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

import com.jayway.restassured.RestAssured;
import com.jayway.restassured.config.DecoderConfig;
import com.jayway.restassured.config.RestAssuredConfig;
import com.jayway.restassured.http.ContentType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import kiwi.core.test.base.JettyLMF;

public class MyWebServiceTest {
    private static JettyLMF lmf;

    @BeforeClass
    public static void beforeClass() {
        lmf = new JettyLMF("/${moduleKey}-test", 9090, MyWebService.class);

        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 9090;
        RestAssured.basePath = "/${moduleKey}-test";
        RestAssured.config = RestAssuredConfig.newConfig().decoderConfig(DecoderConfig.decoderConfig().defaultContentCharset("UTF-8"));
    }

    @AfterClass
    public static void afterClass() {
        if (lmf != null) {
            lmf.shutdown();
        }
    }

    @Test
    public void testHello() {
        /*
         * GET ?name=<xxx>
         */
        given()
        .param("name", "Steve")
        .expect()
        .content(containsString("Hello Steve"))
        .when()
        .get("/${moduleKey}");

        given()
        .contentType(ContentType.HTML)
        .param("name", "Jürgen")
        .expect()
        .content(containsString("Hello Jürgen"))
        .when()
        .get("/${moduleKey}");

        expect()
        .statusCode(400)
        .when()
        .get("/${moduleKey}");
    }

    @Test
    public void testDoThis() {
        /*
         * POST ?turns=i default 2
         */
        given()
        .param("turns", 1)
        .expect().statusCode(200)
        .when()
        .post("/${moduleKey}");

        given()
        .param("turns", 10)
        .expect().statusCode(200)
        .when()
        .post("/${moduleKey}");

        expect().statusCode(200)
        .when()
        .post("/${moduleKey}");
    }

}
