package TreinamentoSelenium;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void meuPrimeiroTeste(){
        String fruta = "Pera";
        assertTrue(fruta.equals("Pera"));
    }
    @Test
    public void meuSegundoTeste(){
        String empresa = "Base2";
        assertTrue(empresa.equals("Base2"));
    }
    @Test
    public void meuTerceiroTeste(){
        String cidade = "Belo Horizonte";
        assertTrue(cidade.equals("Belo Horizonte"));
    }
    @Test
    public void meuQuartoTeste(){

        assertEquals(4, 2+2);
    }
    @Test
    public void meuQuintoTeste(){
        String fruta ="pera";
        //assertEquals("pera", fruta);
        assertEquals("Validação do valor na variável fruta","pera", fruta); //exibe uma mensagem explicando o erro.

    }

    @Test
    public void primeiroCenarioSelenium(){
        //WebDriver inicia a execução do teste
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://www.google.com.br");
        //driver.quit finaliza a execução para não manter o teste aberto.
        driver.quit();
    }

    @Test
    public void validarTituloSiteGoogle(){
        //Variaveis criadas para facilitar manutenção futura.
        String url= "http://www.google.com.br";
        String tituloEsperado="Google";

        WebDriver driver = new ChromeDriver();
        //Execução do teste usando as variaveis ja criadas
        driver.navigate().to(url);
        assertEquals(tituloEsperado,driver.getTitle());
        driver.quit();
    }
    @Test
    public void validarLinkSiteGoogle(){
        String url= "http://www.google.com.br";
        String urlEsperada= "https://www.google.com.br/?gws_rd=ssl";

        WebDriver driver = new ChromeDriver();

        driver.navigate().to(url);
        assertEquals(urlEsperada,driver.getCurrentUrl());
        driver.quit();
    }
    @Test
    public void localizarElementosLoginMantis(){
        String url="https://mantis-prova.base2.com.br/login_page.php";

        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        //Elemento de campo para preencher nome do usuario.
        driver.findElement(By.name("username"));
        //Elemento de campo para preencher a senha.
        driver.findElement(By.name("password"));
        //Elemento que simboliza o botão de login;
        driver.findElement(By.className("button"));

        driver.quit();
    }
    @Test
    public void localizarElementosLoginMantisXpath(){
        String url="https://mantis-prova.base2.com.br/login_page.php";

        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        //Elemento de campo para preencher nome do usuario.
        driver.findElement(By.xpath("//*[@name='username']"));
        //Elemento de campo para preencher a senha.
        driver.findElement(By.xpath("//*[@name='password']"));
        //Elemento que simboliza o botão de login;
        driver.findElement(By.xpath("//*[@class='button']"));

        driver.quit();
    }

    @Test
    public void testeLoginComSucessoMantis(){
        String url="https://mantis-prova.base2.com.br/login_page.php";

        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        //Elemento de campo para preencher nome do usuario.
        driver.findElement(By.xpath("//*[@name='username']")).clear(); //Para limpar campo, caso preenchido.
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("treinamento01"); //.sendKeys preenche os dados.
        //Elemento de campo para preencher a senha.
        driver.findElement(By.xpath("//*[@name='password']")).clear();
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("123456");
        //Elemento que simboliza o botão de login;
        driver.findElement(By.xpath("//*[@class='button']")).click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(By.xpath("//td[@class='login-info-left']"));
        assertTrue(driver.findElement(By.xpath("//td[@class='login-info-left']")).isDisplayed());

        driver.quit();
    }

    @Test
    public void testeLoginSenhaIncorreta(){
        String url="https://mantis-prova.base2.com.br/login_page.php";
        String mensagemErroEsperada = "Your account may be disabled or blocked or the username/password you entered is incorrect.";

        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        //Elemento de campo para preencher nome do usuario.
        driver.findElement(By.xpath("//*[@name='username']")).clear(); //Para limpar campo, caso preenchido.
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys("treinamento01"); //.sendKeys preenche os dados.
        //Elemento de campo para preencher a senha.
        driver.findElement(By.xpath("//*[@name='password']")).clear();
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys("senhaErrada");
        //Elemento que simboliza o botão de login;
        driver.findElement(By.xpath("//*[@class='button']")).click();

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String mensagemDeErro = driver.findElement(By.xpath("//font[@color='red']")).getText();
        assertEquals("Validando mensagem de erro de login",mensagemErroEsperada, mensagemDeErro);

        driver.quit();
    }
    @Test
    public void testePreencherFormularioComSucesso(){

        String url="https://ultimateqa.com/filling-out-forms/";
        String mensagemDeSucessoEsperada = "Thanks for contacting us";
        String name = "joao teste";
        String valorCampoMensagem = "Mensagem exemplo";

        //1º Instanciando o driver do selenium e navegando ate a pagina do teste
        WebDriver driver = new ChromeDriver();
        driver.navigate().to(url);

        //2º Localizando os elementos que serão utilizados para o teste
        WebElement campoName = driver.findElement(By.id("et_pb_contact_name_0"));
        WebElement campoMessage = driver.findElement(By.id("et_pb_contact_message_0"));

        WebElement submitBtn = driver.findElement(By.xpath("//*[@id='et_pb_contact_form_0']//*[@name='et_builder_submit_button']"));

        //3º Realizando as acoes do meu cenario de teste
        campoName.clear();
        campoName.sendKeys(name);
        campoMessage.clear();
        campoMessage.sendKeys(valorCampoMensagem);

        //Adicionei pois o clieque do botão era muito rápido e as informações preenchidas não tinha tempo de serem
        //colocadas nos campos corretamente, gerando outra mensagem diferente da esperada

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        submitBtn.click();

        //atraso colocado para observar a tela testada.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement messageSucess = driver.findElement(By.xpath("//*[@id='et_pb_contact_form_0']//p"));
        //extraindo o texto da mensagem de sucesso
        String mensagemDesSucessoObtida = messageSucess.getText();

        //4º Validação cenario
        assertEquals("validando a mensagem de sucesso do preenchimento do formulario", mensagemDeSucessoEsperada,mensagemDesSucessoObtida);
        driver.quit();
    }
    @Test public void testeLoginCrowdtestComThreadSleep(){
        String url = "https://blackmirror.crowdtest.me/auth";

        String usuario = "joaoteste@gmail.com";
        String senha = "senhaerrada";
        String mensagemDeErroEsperada = "E-mail ou senha inválidos.";

        WebDriver driver = new ChromeDriver();
        //Necessária inicialização para usar um wait
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.manage().window().maximize();
        driver.navigate().to(url);

        //espera botão prosseguir
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='cc-compliance']")));
        //clica no botão prosseguir quando a página entra
        driver.findElement(By.xpath("//div[@class='cc-compliance']")).click();

        driver.findElement(By.id("login")).sendKeys(usuario);
        driver.findElement(By.id("password")).sendKeys(senha);
        driver.findElement(By.xpath("//*[@type='submit']")).click();

        /*try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@class='error-msg']")));
        String mensagemDeErroObtida = driver.findElement(By.xpath("//*[@class='error-msg']")).getText();

        assertEquals("validando mensagem de erro de login", mensagemDeErroEsperada, mensagemDeErroObtida);

        driver.quit();
    }

    @Test
    public void testCadastroDeOcorrencia (){
        String url="https://mantis-prova.base2.com.br/login_page.php";
        String usuario = "treinamento01";
        String senha = "123456";

        String resumo = "Resumo do Luscão";
        String descricao = "Descrição do Luscão";
        String passoAPasso = "1º- Passo do Luscão \n 2º Passo do Luscão \n 3º Passo do Luscão";

        String mensagemOperacaoRealizadaComSucessoEsperada = "Operação realizada com sucesso.";

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.navigate().to(url);

        //Elemento de campo para preencher nome do usuario.
        driver.findElement(By.xpath("//*[@name='username']")).clear(); //Para limpar campo, caso preenchido.
        driver.findElement(By.xpath("//*[@name='username']")).sendKeys(usuario); //.sendKeys preenche os dados.
        //Elemento de campo para preencher a senha.
        driver.findElement(By.xpath("//*[@name='password']")).clear();
        driver.findElement(By.xpath("//*[@name='password']")).sendKeys(senha);
        //Elemento que simboliza o botão de login;
        driver.findElement(By.xpath("//*[@class='button']")).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//td[@class='login-info-left']")));
        driver.findElement(By.xpath("//td[@class='login-info-left']"));
        //assertTrue(driver.findElement(By.xpath("//td[@class='login-info-left']")).isDisplayed());

        WebElement relatarCasoBtn = driver.findElement(By.linkText("Relatar Caso"));
        relatarCasoBtn.click();

        Select categoriaCombobox = new Select(driver.findElement(By.name("category_id")));
        Select frequenciaCombobox = new Select(driver.findElement(By.name("reproducibility")));
        Select gravidadeCombobox = new Select(driver.findElement(By.name("severity")));

        WebElement resumoField = driver.findElement(By.name("summary"));
        WebElement descricaoField = driver.findElement(By.name("description"));
        WebElement stepsField = driver.findElement(By.name("steps_to_reproduce"));

        WebElement enviarRelatorioBtn = driver.findElement(By.xpath("//*[@value='Enviar Relatório']"));

        categoriaCombobox.selectByValue("65");
        frequenciaCombobox.selectByValue("30");
        gravidadeCombobox.selectByValue("20");

        resumoField.sendKeys(resumo);
        descricaoField.sendKeys(descricao);
        stepsField.sendKeys(passoAPasso);

        enviarRelatorioBtn.click();

        WebElement mensagemOperacaoRealizadaComSucesso = driver.findElement(By.xpath("//div[@align='center']"));
        String mensagemOperacaoRealizadaComSucessoText = mensagemOperacaoRealizadaComSucesso.getText().substring(0,31);

        assertEquals(mensagemOperacaoRealizadaComSucessoEsperada, mensagemOperacaoRealizadaComSucessoText);
        //outra opção de asserção para validar, neste caso.
        //assertTrue(mensagemOperacaoRealizadaComSucesso.getText().toString().contains(mensagemOperacaoRealizadaComSucessoEsperada));

        //coleta html da página que é carregada e mostra na tela de teste.
        //System.out.println(driver.getPageSource());

        driver.quit();
    }

    // continuar Aula 6 1:00 min

}
