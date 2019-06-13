
Diretório no github para download: https://github.com/Ranzolin77/DBserver.git

No diretório mostrado tem a codificação realizada em java no eclipse NEON, nele também tem uma pasta com o nome arquivos aonde está o geckodriver.exe para que o teste possa ser rodado no Firefox, na linha 19 na classe Automacao é preciso apontar o .exe para o diretorio aonde o mesmo foi colocado na maquina que o teste será rodado.
Ex: System.setProperty("webdriver.gecko.driver", ""Diretório da máquina local"\\geckodriver.exe");

Eu rodei os testes com o Firefox na versão 61.0.2 (64-bit)

Basta fazer esta breve configuração, e rodar selecionado o java application.

Dúvidas estou a disposição, obrigado!