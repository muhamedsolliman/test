package org.example.stepDefs;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.pages.P01_register;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.Select;
import org.testng.asserts.SoftAssert;

public class D01_RegisterStepDef {
    P01_register register = new P01_register();
    @Given("user go to register page")
    public void user_go_to_register_page() {
    register.registerLink.click();
    }

    @When("user select gender type")
    public void userSelectGenderType() {
    register.male.click();
    }

    @And("user Enter {string} & {string}")
    public void userEnter(String firstname, String lastname) {
        register.firstname.sendKeys(firstname);
        register.lastname.sendKeys(lastname);
    }

    @And("user select date of birth")
    public void userSelectDateOfBirth() {
        //select day
        Select selectDay = new Select(register.daylist);
        selectDay.selectByValue("6");
        //select month
        Select SelectMonth =new Select(register.monthlist);
        SelectMonth.selectByVisibleText("March");
        //select year
        Select Selectyear = new Select(register.yearlist);
        Selectyear.selectByIndex(9);

    }

    @And("user add Enter email")
    public void userAddEnter() {
        // genrate random email
        Faker fake =new Faker();
        String EmailAddress = fake.internet().safeEmailAddress();
        register.Email.sendKeys(EmailAddress);
    }

    @And("User Enter {string} password and confirm password")
    public void userEnterPasswordAndConfirmPassword(String password) {
register.password.sendKeys(password);
register.confirmPassword.sendKeys(password);
    }

    @And("user click on register button")
    public void userClickOnRegisterButton() {
register.registerButton.click();
    }

    @Then("The account is created Successfully")
    public void theAccountIsCreatedSuccessfully() {
        SoftAssert soft =new SoftAssert();
//1- get text and verify it contains "your registration completed:
soft.assertTrue(register.SuccessMsg.getText().contains("Your registration completed"));


//2- get cssvalue and verify color is green #4cb17c hex
        String actualColor = register.SuccessMsg.getCssValue("color");
        // actual color is rgba so we will change it into hex
        Color.fromString(actualColor).asHex();
        soft.assertEquals(Color.fromString(actualColor).asHex(),"#4cb17c");

        soft.assertAll();
    }
}
