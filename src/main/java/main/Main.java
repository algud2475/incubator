package main;

import driver.Driver;
import pages.ElementsPage;
import pages.MainPage;

public class Main {
    public static void main(String[] args) {
        Driver.getUrl("https://demoqa.com");
        MainPage mainPage = new MainPage();

        mainPage.clickButtonElements();

        ElementsPage elementsPage = new ElementsPage();

        elementsPage.getLeftMenu().getElementsForm().openForm();
        elementsPage.getLeftMenu().getElementsForm().clickButtonWebTables();
    }
}
