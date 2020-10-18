import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import java.io.File
import java.util.concurrent.TimeUnit


fun main() {

    val questions = File("answer.txt").readLines().map {
        it.split(";")[0] to it.split(";")[1]
    }.toMap()

    System.setProperty("webdriver.chrome.driver", "")
    val driver = ChromeDriver()
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    // https://join.stagecast.se/api/web/code/3563//Een3YuuCj4jU2b7W3fHOrnSaqE70Cjrl7xkL

    driver.get("https://join.stagecast.se/api/web/code/3563//Een3YuuCj4jU2b7W3fHOrnSaqE70Cjrl7xkL")
    driver.switchTo().frame(driver.findElement(By.id("frame")))
    driver.findElementByClassName("mc-checkmark").click()
    driver.findElementByClassName("main-button").click()

    Thread.sleep(1000)
    driver.findElementByClassName("custom-button").click()

    var counter = 0;
    do {
        counter++;

        val choicesElement = driver.findElements(By.className("choice"))

        var question = ""
        do {
            question = driver.findElementByTagName("h1").text
        } while (question.isBlank())

        if (counter==1) {
            Thread.sleep(230)
        }

        val choices = listOf(
            choicesElement[0].findElement(By.cssSelector("*")),
            choicesElement[1].findElement(By.cssSelector("*")),
            choicesElement[2].findElement(By.cssSelector("*"))
        )

        if (questions.containsKey(question)) {
            choices.first { it.text.equals(questions[question]) }.click()
        } else {
            println(question)
            println(choices.map { it.text })
            choices[0].click()
        }

        Thread.sleep(4070)
    } while (counter <= 6)
}