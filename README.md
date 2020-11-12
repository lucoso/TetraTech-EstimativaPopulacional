# TetraTech-EstimativaPopulacional

REST API para Calcular a Estimativa Populacional de uma data futura.

Esta API pode ser testada localmente ou fazendo o deploy em um servidor cloud à sua escolha.

Para testes locais, basta acessar os seguintes endpoints:

1- http://localhost:8080/projecoes/{dataFutura}

   - Acesse este endpoint, utilizando o método HTTP GET, para trazer a estimativa populacional da data futura informada ao final da URI. Com isso, no lugar de {dataFutura}, o usuário ou desenvolvedor, deve colocar uma data futura no formato Timestamp. Por exemplo: "19-11-2021 19:50:10". Caso a data não esteja nesse formato a API irá retornar o código 400 devido o erro do usuário.

2- http://localhost:8080/projecoes

   - Acesse este endpoint, utilizando o método HTTP GET, para trazer um pequeno relatório contendo os últimos 10 acessos ao endpoint anterior. Esse relatório contém os seguintes dados:
     - Data da chamada (no formato Timestamp).
     - Data futura que foi usada para solicitar a projeção. 
     - População estimada atual (no exato momento que foi feito a chamada). 
     - População projetada para a data futura solicitada.

A cada chamada ao endpoint 1, é gerado um Log que é gravado numa pasta de nome "SistemaProjeçãoPopulacional", presente na raiz da partição C, de onde a aplicação está sendo executada. Para cálculo da estimativa estão sendo usados dados de uma API externa do IBGE.

Por fim, para executar testes após deploy da aplicação, basta alterar as URI's dos endpoints, substituindo o "localhost:8080" pelo IP ou domínio do servidor cloud onde a aplicação foi hospedada. 
