
Diret�rio no github para download: https://github.com/Ranzolin77/DBserver.git

No diret�rio mostrado tem a codifica��o realizada em java no eclipse NEON, nele tamb�m tem uma pasta com o nome arquivos aonde est� o geckodriver.exe para que o teste possa ser rodado no Firefox, na linha 19 na classe Automacao � preciso apontar o .exe para o diretorio aonde o mesmo foi colocado na maquina que o teste ser� rodado.
Ex: System.setProperty("webdriver.gecko.driver", ""Diret�rio da m�quina local"\\geckodriver.exe");

Eu rodei os testes com o Firefox na vers�o 61.0.2 (64-bit)

Basta fazer esta breve configura��o, e rodar selecionado o java application.

D�vidas estou a disposi��o, obrigado!