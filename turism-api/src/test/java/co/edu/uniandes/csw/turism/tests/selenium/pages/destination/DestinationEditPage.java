/*
The MIT License (MIT)

Copyright (c) 2015 Los Andes University

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package co.edu.uniandes.csw.turism.tests.selenium.pages.destination;

import co.edu.uniandes.csw.turism.dtos.minimum.DestinationDTO;
import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DestinationEditPage {

    @FindBy(id = "name")
    private WebElement nameInput;
    @FindBy(id = "initialDate")
    private WebElement initialDate;
    @FindBy(id = "finalDate")
    private WebElement finalDate;
    @FindBy(id = "duration")
    private WebElement duration;
    @FindBy(id = "activities")
    private WebElement activities;

    @FindBy(id = "save-destination")
    private WebElement saveBtn;

    @FindBy(id = "cancel-destination")
    private WebElement cancelBtn;

    public void saveDestination(DestinationDTO destination) {
         waitGui().until().element(nameInput).is().visible();
         nameInput.clear();
         nameInput.sendKeys(destination.getName());
         waitGui().until().element(initialDate).is().visible();
         initialDate.clear();
         initialDate.sendKeys(destination.getInitialDate().toString());
         waitGui().until().element(finalDate).is().visible();
         finalDate.clear();
         finalDate.sendKeys(destination.getFinalDate().toString());
         waitGui().until().element(duration).is().visible();
         duration.clear();
         duration.sendKeys(String.valueOf(destination.getDuration()));
         waitGui().until().element(activities).is().visible();
         activities.clear();
         activities.sendKeys(destination.getActivities());
        guardAjax(saveBtn).click();
    }
}
