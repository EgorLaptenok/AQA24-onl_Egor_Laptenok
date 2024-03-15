package tests.API;

import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import testngUtils.Listener;

import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.core.IsEqual.equalTo;
import static propertyUtils.PropertyReader.getProperties;

@Listeners(Listener.class)
public class OzonTests {
    private String id;
    private Map<String, String> cookie;

    @BeforeTest
    public void precondition() {
        baseURI = getProperties().getProperty("baseUrl");
    }

    @Test(priority = 1)
    public void addToCart() {
        Response response = given().contentType(JSON).body("[\n" +
                "    {\n" +
                "    \"id\":1379765870,\n" +
                "    \"quantity\":1\n" +
                "    }\n" +
                "]").basePath("api/composer-api.bx/_action/addToCart").post();
        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("success", equalTo(true));
        cookie = response.cookies();
        id = response.jsonPath().getString("cart.cartItems[0].id");
        response.prettyPrint();
        System.out.println("ID :: " + id);
    }

    @Test(priority = 2)
    public void checkCart() {
        given().cookies(cookie).when()
                .get("api/composer-api.bx/_action/summary")
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("[0].id", equalTo(Integer.parseInt(id)));
    }

    @Test(priority = 3)
    public void deleteCart() {
        given().cookies(cookie)
                .when()
                .post("api/entrypoint-api.bx/page/json/v2?url=/cart?delete=" + id + "&pos=0")
                .then()
                .statusCode(200);
    }

    @Test(priority = 4)
    public void checkDeleteCart() {
        given().cookies(cookie)
                .when()
                .get("api/composer-api.bx/_action/summary")
                .then().assertThat()
                .statusCode(200)
                .and()
                .body("", Matchers.empty());
    }

    @Test(priority = 5)
    public void clothingCatalog() {
        given().cookies(cookie)
                .param("menuId", "185").param("categoryId", "7500").param("hash")
                .when().get("api/composer-api.bx/_action/v2/categoryChildV3")
                .then().assertThat().statusCode(200).and()
                .body("data.columns[0].categories[0].title", equalTo("Женщинам"))
                .body("data.columns[0].categories[1].title", equalTo("Мужчинам"))
                .body("data.columns[1].categories[0].title", equalTo("Детям"))
                .body("data.columns[1].categories[1].title", equalTo("Униформа и рабочая одежда"))
                .body("data.columns[2].categories[0].title", equalTo("Уход за одеждой"));
    }
}
