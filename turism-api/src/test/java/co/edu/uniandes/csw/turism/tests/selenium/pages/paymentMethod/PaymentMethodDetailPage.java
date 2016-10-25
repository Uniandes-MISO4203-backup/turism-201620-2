/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.paymentMethod;

import co.edu.uniandes.csw.turism.dtos.minimum.PaymentMethodDTO;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author jd.cepeda
 */
public class PaymentMethodDetailPage {

    @FindBy(id = "delete-paymentMethod")
    private WebElement deleteBtn;

    @FindBy(id = "edit-paymentMethod")
    private WebElement editBtn;

    @FindBy(id = "list-paymentMethod")
    private WebElement listBtn;

    @FindBy(id = "name")
    private WebElement name;
    @FindBy(id = "cardType")
    private WebElement cardType;
    @FindBy(id = "cardNumber")
    private WebElement cardNumber;
    @FindBy(id = "securityCode")
    private WebElement securityCode;
    @FindBy(id = "expirationMonth")
    private WebElement expirationMonth;
    @FindBy(id = "expirationYear")
    private WebElement expirationYear;

    public void list() {
        listBtn.click();
    }

    public void edit() {
        editBtn.click();
    }

    public void delete() {
        deleteBtn.click();
    }

    public PaymentMethodDTO getData() {
        PaymentMethodDTO paymentMethod = new PaymentMethodDTO();
        paymentMethod.setName(name.getText());
        paymentMethod.setCardType(cardType.getText());
        paymentMethod.setCardNumber(Long.parseLong(cardNumber.getText()));
        paymentMethod.setSecurityCode(Integer.parseInt(securityCode.getText()));
        paymentMethod.setExpirationMonth(Integer.parseInt(expirationMonth.getText()));
        paymentMethod.setExpirationYear(Integer.parseInt(expirationYear.getText()));
        return paymentMethod;
    }

}
