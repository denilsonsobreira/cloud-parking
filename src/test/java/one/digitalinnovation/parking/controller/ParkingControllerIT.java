package one.digitalinnovation.parking.controller;

import io.restassured.RestAssured;
import one.digitalinnovation.parking.controller.dto.ParkingCreateDTO;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ParkingControllerIT {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTest() {
        RestAssured.port = randomPort;
    }

    @Test
    void whenFindAllThenCheckResult() {
        RestAssured.given()
                .when()
                .get("/parking")
                .then()
                .statusCode(200)
                .body("license[0]", Matchers.equalTo("DMS-1111"));
    }

    @Test
    void whenCreateThenCheckIsCreate() {

        ParkingCreateDTO createDTO = new ParkingCreateDTO();
        createDTO.setColor("yellow");
        createDTO.setModel("MOBI");
        createDTO.setState("CE");
        createDTO.setLicense("SSS-1111");
        RestAssured.given()
                .when()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(createDTO)
                .post("/parking")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body("color", Matchers.equalTo("yellow"))
                .body("model", Matchers.equalTo("MOBI"))
                .body("state", Matchers.equalTo("CE"))
                .body("license", Matchers.equalTo("SSS-1111"));
    }
}