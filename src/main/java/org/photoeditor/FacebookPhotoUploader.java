package org.photoeditor;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.*;

class FacebookPhotoUploader {
    public void uploadPhotoToFacebook(String filePath, JLabel lblSharePhotoProgress, String emailFromUser, String passwordFromUser) throws InterruptedException {
        
        // Selenium WebDriver'ı başlatılır.
        System.out.println("%0 - Program Başlatılıyor...");
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-notifications");
        WebDriver driver = new ChromeDriver(options);
        System.out.println("%10 - WebDriver çalıştırıldı.");

        // Facebook'a gidilir.
        driver.get("https://www.facebook.com");

        // Kullanıcı adı ve parolası girilir.
        WebElement username = driver.findElement(By.id("email"));
        WebElement password = driver.findElement(By.id("pass"));
        username.sendKeys(emailFromUser);
        password.sendKeys(passwordFromUser);
        System.out.println("%20 - Kullanıcı adı ve parola girildi.");

        //Facebook'a giriş yapılır.
        WebElement loginButton = driver.findElement(By.name("login"));
        loginButton.click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label='Oluştur']")));
        System.out.println("%30 - Facebook'a giriş yaptı.");

        // Oluştur menüsüne tıklanır.
        WebElement menuButton = driver.findElement(By.xpath("//div[@aria-label='Oluştur']"));
        menuButton.click();
        Thread.sleep(2000);
        System.out.println("%40 - Oluştur menüsüne tıkladı.");

        // Gönderi oluştur butonuna tıklanır.
        WebElement createPostButton = driver.findElement(By.xpath("//div[@class='x1i10hfl x1qjc9v5 xjbqb8w xjqpnuy xa49m3k xqeqjp1 x2hbi6w x13fuv20 xu3j5b3 x1q0q8m5 x26u7qi x972fbf xcfux6l x1qhh985 xm0m39n x9f619 x1ypdohk xdl72j9 x2lah0s " +
                "xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r x2lwn1j xeuugli xexx8yu x4uap5 x18d9i69 xkhd6sd x1n2onr6 x16tdsg8 x1hl2dhg xggy1nq x1ja2u2z x1t137rt x1q0g3np x87ps6o x1lku1pv x1a2a7pz x1lq5wgf xgqcy7u x30kzoy x9jhf4c x1lliihq' and @role='button']"));
        createPostButton.click();
        new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@aria-label='Fotoğraf/video']")));
        System.out.println("%50 - Gönderi oluştur butonuna tıkladı.");

        // Gönderi tipi seçilir.
        WebElement postTypeButton = driver.findElement(By.xpath("//div[@aria-label='Fotoğraf/video']"));
        postTypeButton.click();
        Thread.sleep(2000);
        System.out.println("%60 - Gönderi tipi seçti.");

        // Gönderi yükleme butonuna tıklanır.
        WebElement photoUploadButton = driver.findElement(By.xpath("//div[@class='x1i10hfl x1qjc9v5 xjbqb8w xjqpnuy xa49m3k xqeqjp1 x2hbi6w x13fuv20 xu3j5b3 x1q0q8m5 x26u7qi x972fbf xcfux6l x1qhh985 xm0m39n x9f619 x1ypdohk xdl72j9 x2lah0s " +
                "xe8uvvx xdj266r x11i5rnm xat24cr x1mh8g0r x2lwn1j xeuugli xexx8yu x4uap5 x18d9i69 xkhd6sd x1n2onr6 x16tdsg8 x1hl2dhg xggy1nq x1ja2u2z x1t137rt x1o1ewxj x3x9cwd x1e5q0jg x13rtm0m x3nfvp2 x1q0g3np x87ps6o x1lku1pv x1a2a7pz' and not(@aria-label)]"));
        photoUploadButton.click();
        Thread.sleep(2000);
        System.out.println("%70 - Gönderi yükleme butonuna tıkladı.");

        // Dosya seçme iletişim kutusu bulunur.
        WebElement fileInput = driver.findElement(By.xpath("//input[@type='file']"));

        // Fotoğrafın yolu olan dosya yükleme iletişim kutusuna gönderilir.
        fileInput.sendKeys(filePath);
        Thread.sleep(2000);

        // Gönderiyi paylaşma butonuna tıklanır.
        WebElement shareButton = driver.findElement(By.xpath("//div[@aria-label='Paylaş']"));
        shareButton.click();
        System.out.println("%80 - Gönderi paylaşılıyor.");
        Thread.sleep(5000);

        // Tarayıcı kapatılır.
        driver.quit();
        lblSharePhotoProgress.setText("Paylaşma işlemi tamamlandı.");
        System.out.println("%100 - Paylaşma işlemi tamamlandı.");
        
    }
}
