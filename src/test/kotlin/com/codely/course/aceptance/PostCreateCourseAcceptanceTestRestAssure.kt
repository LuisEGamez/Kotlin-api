package com.codely.course.aceptance

import com.codely.shared.BaseAcceptanceTestRestAssure
import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Extract
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import kotlin.test.assertTrue

class PostCreateCourseAcceptanceTestRestAssure : BaseAcceptanceTestRestAssure() {

    @Test
    fun `should create a course successfully`() {
        val response = Given {
            contentType(ContentType.JSON)
            body(
                """
               {
                    "id": "97fa5af4-bd81-45d5-974f-d5a3970af252",
                    "name": "Test Acceptance"
               }     
           """
            )
        } When {
            post("/course")
        } Then {
            statusCode(HttpStatus.CREATED.value())
        } Extract {
            body().asString()
        }
        assertTrue(response.isEmpty())
    }

    @Test
    fun `should throw a InvalidCourseDescriptionException`() {
        Given {
            contentType(ContentType.JSON)
            body(
                """
               {
                    "id": "97fa5af4-bd81-45d5-974f-d5a3970af252",
                    "name": "Test Acceptance",
                    "description": "asdgffagfaebgrtahteaghjadhfkdwjhbfLIOEWDGFCVILO;DABHC;Obe;OHUC;OHdl;cehQSO;DCJHO;SHfc;oBO;EBCO;ADBFVOLbvol;AJKCFHV;OPDWNfvcopENVLADJSBF;ladJFAJLSDHSAJDHSADJhasDJ"
               }     
           """
            )
        } When {
            post("/course")
        } Then {
            statusCode(HttpStatus.BAD_REQUEST.value())
        }
    }
}