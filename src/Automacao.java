import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Automacao
{
	private static WebDriver driver;
	
	public static void main(String[] args) throws InterruptedException
	{
		Automacao objeto = new Automacao();
		
		System.setProperty("webdriver.gecko.driver", "C:\\firefoxdriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("http://www.automationpractice.com");
		driver.manage().window().maximize();		
		
		objeto.selecionaProduto();
		objeto.adicionaCarrinho();
		objeto.validaProdutosCarrinho();
		objeto.realizaCadastro();
		objeto.validaEndereco();
		objeto.confirmaCadastro();
		objeto.aceitaTermos();
		objeto.validaValorCompra();
		objeto.realizaPagamento();
		objeto.validaCompra();
	}
	
	public void selecionaProduto()
	{
		driver.findElement(By.xpath(".//*[@id='block_top_menu']/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='categories_block_left']/div/ul/li[2]/a")).click();
		driver.findElement(By.xpath(".//*[@id='center_column']/ul/li[1]/div/div[1]/div/a[1]/img")).click();
	}
	
	public void adicionaCarrinho() throws InterruptedException
	{
		WebElement valor = driver.findElement(By.id("our_price_display"));		
		driver.findElement(By.name("Submit")).click();
		
		Thread.sleep(10000); 
		
		driver.findElement(By.xpath(".//*[@id='layer_cart']/div[1]/div[2]/div[4]/a/span")).click();		
		driver.findElement(By.xpath(".//*[@id='center_column']/p[2]/a[1]/span")).click();
	}
	
	public void validaProdutosCarrinho()
	{
		WebElement campo = driver.findElement(By.xpath(".//*[@id='header']/div[3]/div/div/div[3]/div/a/span[2]"));
		
		if(campo.getText().equals("Product") || campo.getText().equals("Products"))
		{
			System.out.println("Um produto ou mais foi adicionado ao carrinho!");
		}
		else
		{
			System.out.println("Não existem produtos adicionados no carrinho");
			driver.close();
			System.exit(0);
		}
	}
	
	public void realizaCadastro() throws InterruptedException
	{
		WebElement inputEmail = driver.findElement(By.id("email_create"));				
		inputEmail.sendKeys(retornaEmail());
		driver.findElement(By.id("SubmitCreate")).click();	
		
		Thread.sleep(10000);
		
		WebElement inputFirstName = driver.findElement(By.xpath(".//*[@id='customer_firstname']"));
		inputFirstName.sendKeys("Usuario");
		WebElement inputLastName = driver.findElement(By.id("customer_lastname"));
		inputLastName.sendKeys("Teste");
		WebElement inputPassword = driver.findElement(By.id("passwd"));
		inputPassword.sendKeys("Key011235813");
		WebElement inputAddress = driver.findElement(By.id("address1"));
		inputAddress.sendKeys("Rua testador de sofware - 711");
		WebElement inputCity = driver.findElement(By.id("city"));
		inputCity.sendKeys("Desenvolvedores de teste");
		
		WebElement selectStateButton = driver.findElement(By.id("id_state"));
		selectStateButton.click();
		Thread.sleep(10000);
		WebElement selectState = driver.findElement(By.xpath(".//*[@id='id_state']/option[6]"));
		selectState.click();
		
		WebElement inputPostCode = driver.findElement(By.id("postcode"));
		inputPostCode.sendKeys("00000");

		WebElement inputPhoneNumber = driver.findElement(By.id("phone_mobile"));
		inputPhoneNumber.sendKeys("99999999999");
		
		Thread.sleep(10000);
		driver.findElement(By.id("submitAccount")).click();		
	}
	
	public String retornaEmail()
	{
		String email = null;
		
		Date data = new Date();
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(data);
		
		int hora = cal.get(Calendar.HOUR_OF_DAY);
		int minuto = cal.get(Calendar.MINUTE);
		int segundo = cal.get(Calendar.SECOND);
		int dia = cal.get(Calendar.DAY_OF_MONTH);
		int mes = cal.get(Calendar.MONTH);
		int ano = cal.get(Calendar.YEAR);
		
		email = "dbservermail" + Integer.toString(hora) + Integer.toString(minuto) + Integer.toString(segundo) + 
				Integer.toString(dia) + Integer.toString(mes) + Integer.toString(ano) + "@gmail.com";
		System.out.println("email cadastrado" + email);
		
		return email;
	}
	
	public void validaEndereco()
	{
		WebElement enderecoRua = driver.findElement(By.xpath(".//*[@id='address_delivery']/li[3]"));
		WebElement enderecoCidadeEstado = driver.findElement(By.xpath(".//*[@id='address_delivery']/li[4]"));
		
		if(enderecoRua.getText().equals("Rua testador de sofware - 711") && enderecoCidadeEstado.getText().equals("Desenvolvedores de teste, California 00000"))
		{
			System.out.println("Informações de endereços cadastrado x esperado coerentes! Teste em andamento!");
		}
		else
		{
			System.out.println("Informações de endereço divergentes do cadastrado x esperado!");
			driver.close();
			System.exit(0);			
		}
	}

	public void confirmaCadastro()
	{
		driver.findElement(By.name("processAddress")).click();
	}
	
	public void aceitaTermos()
	{
		driver.findElement(By.id("cgv")).click();
		driver.findElement(By.name("processCarrier")).click();
	}
	
	public void validaValorCompra()
	{
		WebElement valorCompras = driver.findElement(By.id("total_product"));
		WebElement valorEnvio = driver.findElement(By.id("total_shipping"));
		WebElement totalValor = driver.findElement(By.id("total_price"));
		
		String converteValorCompras = valorCompras.getText().substring(1);
		String converteValorEnvio = valorEnvio.getText().substring(1);
		String converteValorTotal = totalValor.getText().substring(1);
		
		double valorCompraDouble = Double.parseDouble(converteValorCompras);
		double valorEnvioDouble = Double.parseDouble(converteValorEnvio);
		double valorTotalDouble = Double.parseDouble(converteValorTotal);
		
		if(valorCompraDouble + valorEnvioDouble == valorTotalDouble)
		{
			System.out.println("Valores de compra corretos!");
		}
		else
		{
			System.out.println("Valores de compra divergentes do esperado!");
			driver.close();
			System.exit(0);				
		}
	}
	
	public void realizaPagamento()
	{
		driver.findElement(By.xpath(".//*[@id='HOOK_PAYMENT']/div[1]/div/p/a")).click();
		driver.findElement(By.xpath(".//*[@id='cart_navigation']/button")).click();
	}
	
	public void validaCompra()
	{
		WebElement validaCompra = driver.findElement(By.xpath(".//*[@id='center_column']/div/p/strong"));
		
		if(validaCompra.getText().equals("Your order on My Store is complete."))
		{
			System.out.println("Compra finalizada com sucesso!");
			driver.close();
		}
		else
		{
			System.out.println("A compra não foi finalizada com sucesso!");
			driver.close();
		}
	}
}

