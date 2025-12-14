package edu.innotech;


import io.restassured.internal.common.assertion.AssertionSupport;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;


public class Tests {

    WebDriver driver;

   @BeforeEach
   public void StartUp(){
       System.setProperty("webdriver.chrome.driver", "C:\\Users\\koche\\IdeaProjects\\StepUpCourse\\chromedriver-win64\\chromedriver.exe");
       ChromeOptions options = new ChromeOptions();
       options.addArguments("–-no-sandbox");
       options.addArguments("–-disable-dev-shm-usage");
       options.addArguments("–-disable-application-cache");
       options.addArguments("–-disable-gpu");
       options.addArguments("–-disable-extensions");
       options.addArguments("--disable-gcm-user");
       options.addArguments("--disable-notifications");
       options.addArguments("--remote-allow-origins=*");
       options.addArguments("--disable-popup-blocking");
       //options.setBrowserVersion("116.0.5845.111");
       driver = new ChromeDriver(options);
       driver.get("https://pikabu.ru/");

   }


    //  @AfterEach
   public void Finished(){
       driver.quit();
   }

   @Test //Проверка заголовка страницы
   public void CheckTitle(){

       String actualTitle = driver.getTitle();
       String expectedTitle = "Горячее – самые интересные и обсуждаемые посты | Пикабу";

       if (actualTitle.equals(expectedTitle)) {
           System.out.println("PASSED. Заголовок совпадает.");
       } else {
           System.out.println("FAILED. Заголовок НЕ совпадает. Получили заголовок:"+actualTitle + ". Ожидали заголовок:"+expectedTitle);
       }
       Assertions.assertEquals(expectedTitle,actualTitle);
   }

   @Test
   public void CheckEnterForm(){

       try {
           WebElement loginButton = driver.findElement(By.xpath("//button[@class='pkb-normal-btn header-right-menu__login-button']"));
           loginButton.click();
           System.out.println("Кнопка 'Войти' нажата.");
       } catch (Exception e) {
           System.out.println("Кнопка 'Войти' не найдена: " + e.getMessage());
       }

       boolean WinScenario = false;
       try {
           WebElement AuthWindow = driver.findElement(By.xpath("//div[@class='auth-modal']"));
           System.out.println("Модальное окно авторизации найдено");
       } catch(Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Модальное окно авторизации не найдено!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
       }

       WebElement Login = null;
       WebElement Password = null;
       WebElement InputButton = null;
       try {
           Login = driver.findElement(By.xpath("//div[@class='auth-modal']//input[@class='input__input'][@name='username'][@placeholder='Логин']"));
           System.out.println("Поле Логин найдено");
       } catch(Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Поле Логин не найдено!!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
       }
       try {
           Password = driver.findElement(By.xpath("//div[@class='auth-modal']//input[@class='input__input'][@name='password'][@placeholder='Пароль']"));
           System.out.println("Поле Пароль найдено");
       } catch(Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Поле Пароль не найдено!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
       }
       try {
           InputButton = driver.findElement(By.xpath("//div[@class='auth-modal']//button[@class='button_success button_width_100'][span[text()='Войти']]"));
           System.out.println("Кнопка Войти найдена");
       } catch(Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Кнопка Войти не найдена!!!!!!!!!!!!!!!!!!!!!" + e.getMessage());
       }
       try{
           Login.sendKeys("Qwerty");
           System.out.println("Логин успешно введен.");
       }
       catch (Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Не удалось ввести логин.!!!!!!!!!!!!!!!!!!!!!");
       }

       try {
           Password.sendKeys("Qwerty");
           System.out.println("Пароль успешно введен.");
         } catch(Exception e)
       {
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Не удалось ввести пароль.!!!!!!!!!!!!!!!!!!!!!");
       }

       try {
              InputButton.click();
              System.out.println("Кнопка войти успешно нажата.");
       } catch(Exception e)
       {
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Кнопка войти НЕ нажата.!!!!!!!!!!!!!!!!!!!!!");
       }

       try{
           WebElement errorMsg=driver.findElement(By.xpath("//span[text()='Ошибка. Вы ввели неверные данные авторизации']"));
           System.out.println("Сообщение: 'Ошибка. Вы ввели неверные данные авторизации' найдено.");
           WinScenario = true;
       } catch (Exception e){
           System.out.println("!!!!!!!!!!!!!!!!!!!!!Сообщение: Ошибка. 'Вы ввели неверные данные авторизации' НЕ найдено.!!!!!!!!!!!!!!!!!!!!!");
       }
       Assertions.assertTrue(WinScenario);

   }


//Qwerty/Qwerty и нажать «Войти».
}
