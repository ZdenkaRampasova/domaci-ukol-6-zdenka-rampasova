package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    private static final String URL_APLIKACE = "https://cz-test-jedna.herokuapp.com/";

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void rodisSUctemSeMusiBytSchopenPrihlasitDoAppky() {
        prohlizec.navigate().to(URL_APLIKACE);

        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//header//div[2]/a/i")); // By.xpath("//a/i[text() = 'Přihlásit                ']"));  -nefunguje
        tlacitkoPrihlasit.click();
        WebElement email = prohlizec.findElement(By.id("email"));
        email.sendKeys("testovaci.mail@seznam.cz");
        WebElement heslo = prohlizec.findElement(By.id("password"));
        heslo.sendKeys("Test01");
        WebElement buttonPrihlasit = prohlizec.findElement(By.xpath("//form/div[3]/div/button"));   //div[contains(@class, 'qa-firstName’)] -nefunguje
        buttonPrihlasit.click();
        WebElement prihlasky = prohlizec.findElement(By.xpath("//header/div/h1"));
        String napis = prihlasky.getText();

        Assertions.assertEquals("Přihlášky", napis);
    }

    @Test
    public void rodicSUctemMusiBytSchopenVybratKurzPrihlasitSeVyplnitPrihlaskuAZkontrolovatJiVSystemu() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/1-digitalni-akademie-testovani");

        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.xpath("//div[2]/div[1]/div/div[2]/a"));
        buttonVytvoritPrihlasku.click();
        WebElement email = prohlizec.findElement(By.id("email"));
        email.sendKeys("testovaci.mail@seznam.cz");
        WebElement heslo = prohlizec.findElement(By.id("password"));
        heslo.sendKeys("Test01");
        WebElement buttonPrihlasit = prohlizec.findElement(By.xpath("//form/div[3]/div/button"));
        buttonPrihlasit.click();
        WebElement termin = prohlizec.findElement(By.className("filter-option-inner-inner"));
        termin.click();
        WebElement input = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        input.sendKeys("05.06.\n");
        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.click();
        krestniJmenoZaka.sendKeys("Testovací");
        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.click();
        prijmeniZaka.sendKeys("Žák");
        WebElement datumNarozeniZaka = prohlizec.findElement(By.id("birthday"));
        datumNarozeniZaka.click();
        datumNarozeniZaka.sendKeys("12.12.2012");
        WebElement uhradaBankovnimPrevodem = prohlizec.findElement(By.xpath("//label[text() = 'Bankovní převod']"));
        uhradaBankovnimPrevodem.click();
        WebElement souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju = prohlizec.findElement(By.xpath("//label[@for = 'terms_conditions']"));
        souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//tbody//input[@value = 'Vytvořit přihlášku']"));
        tlacitkoVytvoritPrihlasku.click();
        WebElement stahnoutPrihlasku = prohlizec.findElement(By.xpath("//table/tbody/tr[15]/td[2]/a"));

        Assertions.assertEquals("Stáhnout potvrzení o přihlášení", stahnoutPrihlasku.getText());
    }

    @Test
    public void rodicSUctemSeMusiBytSchopenPrihlasitVybratKurzVyplnitPrihlaskuAzkontrolovatJi() {
        prohlizec.navigate().to(URL_APLIKACE);

        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.className("fa-user"));      // (By.xpath("//a/i[text() = 'Přihlásit                ']")); -nefunguje "//header//div[2]/a/i" -funguje
        tlacitkoPrihlasit.click();
        WebElement email = prohlizec.findElement(By.id("email"));
        email.sendKeys("testovaci.mail@seznam.cz");
        WebElement heslo = prohlizec.findElement(By.id("password"));
        heslo.sendKeys("Test01");
        WebElement buttonPrihlasit = prohlizec.findElement(By.xpath("//form/div[3]/div/button"));
        buttonPrihlasit.click();

        prohlizec.navigate().to(URL_APLIKACE);
        WebElement trimesicniKurzyWebu = prohlizec.findElement(By.xpath("//div[1]/div[2]/div/div[2]/a"));
        trimesicniKurzyWebu.click();
        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.linkText("Vytvořit přihlášku"));
        buttonVytvoritPrihlasku.click();
        WebElement termin = prohlizec.findElement(By.className("filter-option-inner-inner"));
        termin.click();
        WebElement input = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        input.sendKeys("21.06.\n");
        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.click();
        krestniJmenoZaka.sendKeys("Testovací");
        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.click();
        prijmeniZaka.sendKeys("Žák");
        WebElement datumNarozeniZaka = prohlizec.findElement(By.id("birthday"));
        datumNarozeniZaka.click();
        datumNarozeniZaka.sendKeys("12.12.2012");
        WebElement uhradaBankovnimPrevodem = prohlizec.findElement(By.xpath("//label[text() = 'Bankovní převod']"));
        uhradaBankovnimPrevodem.click();
        WebElement souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju = prohlizec.findElement(By.xpath("//label[@for = 'terms_conditions']"));
        souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//tbody//input[@value = 'Vytvořit přihlášku']"));
        tlacitkoVytvoritPrihlasku.click();
        WebElement stahnoutPrihlasku = prohlizec.findElement(By.xpath("//table/tbody/tr[15]/td[2]/a"));

        Assertions.assertEquals("Stáhnout potvrzení o přihlášení", stahnoutPrihlasku.getText());
    }

    @Test
    public void rodicSUctemMusiBytSchopenDiteZKurzuOdhlasitZDuvoduNemoci() {
        prohlizec.navigate().to(URL_APLIKACE);

        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.className("fa-user"));    // (By.xpath("//a/i[text() = 'Přihlásit                ']")); -nefunguje "//header//div[2]/a/i" -funguje
        tlacitkoPrihlasit.click();
        WebElement email = prohlizec.findElement(By.id("email"));
        email.sendKeys("testovaci.mail@seznam.cz");
        WebElement heslo = prohlizec.findElement(By.id("password"));
        heslo.sendKeys("Test01");
        WebElement buttonPrihlasit = prohlizec.findElement(By.xpath("//form/div[3]/div/button"));
        buttonPrihlasit.click();

        prohlizec.navigate().to(URL_APLIKACE);
        WebElement trimesicniKurzyWebu = prohlizec.findElement(By.xpath("//div[1]/div[2]/div/div[2]/a"));
        trimesicniKurzyWebu.click();
        WebElement buttonVytvoritPrihlasku = prohlizec.findElement(By.linkText("Vytvořit přihlášku"));
        buttonVytvoritPrihlasku.click();
        WebElement termin = prohlizec.findElement(By.className("filter-option-inner-inner"));
        termin.click();
        WebElement input = prohlizec.findElement(By.xpath("//input[@type = 'search']"));
        input.sendKeys("21.06.\n");
        WebElement krestniJmenoZaka = prohlizec.findElement(By.id("forename"));
        krestniJmenoZaka.click();
        krestniJmenoZaka.sendKeys("Testovací");
        WebElement prijmeniZaka = prohlizec.findElement(By.id("surname"));
        prijmeniZaka.click();
        prijmeniZaka.sendKeys("Žák");
        WebElement datumNarozeniZaka = prohlizec.findElement(By.id("birthday"));
        datumNarozeniZaka.click();
        datumNarozeniZaka.sendKeys("12.12.2012");
        WebElement uhradaBankovnimPrevodem = prohlizec.findElement(By.xpath("//label[text() = 'Bankovní převod']"));
        uhradaBankovnimPrevodem.click();
        WebElement souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju = prohlizec.findElement(By.xpath("//label[@for = 'terms_conditions']"));
        souhlasSVseobecnymiPodpinkamiAZpracovanimUdaju.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//tbody//input[@value = 'Vytvořit přihlášku']"));
        tlacitkoVytvoritPrihlasku.click();
        WebElement tlacitkoOdhlaseniUcasti = prohlizec.findElement(By.xpath("//div/a[text () = 'Odhlášení účasti']"));
        tlacitkoOdhlaseniUcasti.click();
        WebElement duvodNemoc = prohlizec.findElement(By.xpath("//td/span/label[text () = 'Nemoc']"));
        duvodNemoc.click();
        WebElement tlacitkoOdhlasitZaka = prohlizec.findElement(By.xpath("//tr/td/input[@value = 'Odhlásit žáka']"));
        tlacitkoOdhlasitZaka.click();
        WebElement zakBylOdhlasen = prohlizec.findElement(By.xpath("//ul/li[contains (text (), 'Žák byl odhlášen ')]"));

        Assertions.assertTrue(zakBylOdhlasen.getText().contains("Žák byl odhlášen "));
    }

    @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
