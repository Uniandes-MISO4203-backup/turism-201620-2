/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.turism.tests.selenium.pages.paymentMethod;

import co.edu.uniandes.csw.turism.dtos.minimum.PaymentMethodDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

/**
 *
 * @author jd.cepeda
 */
public class PaymentMethodCreatePage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "cardType")
    private WebElement cardTypeInput;
    @FindBy(id = "cardNumber")
    private WebElement cardNumberInput;
    @FindBy(id = "securityCode")
    private WebElement securityCodeInput;
    @FindBy(id = "expirationMonth")
    private WebElement expirationMonthInput;
    @FindBy(id = "expirationYear")
    private WebElement expirationYearInput;

    @FindBy(id = "save-paymentMethod")
    private WebElement saveBtn;

    @FindBy(id = "cancel-paymentMethod")
    private WebElement cancelBtn;

    public void savePaymentMethod(PaymentMethodDTO paymentMethod) {
        waitGui().until().element(nameInput).is().visible();
        nameInput.clear();
        nameInput.sendKeys(paymentMethod.getName());
        waitGui().until().element(cardTypeInput).is().visible();
        cardTypeInput.clear();
        cardTypeInput.sendKeys(paymentMethod.getCardType());
        waitGui().until().element(cardNumberInput).is().visible();
        cardNumberInput.clear();
        cardNumberInput.sendKeys(paymentMethod.getCardNumber().toString());
        waitGui().until().element(securityCodeInput).is().visible();
        securityCodeInput.clear();
        securityCodeInput.sendKeys(String.valueOf(paymentMethod.getSecurityCode()));
        waitGui().until().element(expirationMonthInput).is().visible();
        expirationMonthInput.clear();
        expirationMonthInput.sendKeys(String.valueOf(paymentMethod.getExpirationMonth()));
        waitGui().until().element(expirationYearInput).is().visible();
        expirationYearInput.clear();
        expirationYearInput.sendKeys(String.valueOf(paymentMethod.getExpirationYear()));
        guardAjax(saveBtn).click();
    }

}
