- Introduction - 2 min - (1) O que é o projeto? 2) As personas que o utilizam, o que ele faz, etc; 3) Mais valias do projeto (dar ênfase no real-time))

- User Stories - 1 min:
    Inicialmente definimos as User Stories fundamentais, mas como adoptámos uma metodologia ágil, fomos adaptando e adicionando novas User Stories ao longo do desenvolvimento do projeto. Temos agora 23 user stories, 20 das quais foram implementadas e 3 definidas como Future Work.

    (1): Administrator: Monitor System Performance, Including the Number of Sensors Data
    (2): Coach: View Real-Time Player Statistics

- Future Work - 1 min -
    Como dissemos anteiormente como future work temos estas três: 
    (1): Player: See Performance History
    (2): Coach: View Team Ranking System
    (3): Administrator: Monitor System Performance, Including the Number of Requests Made to Each Endpoint
    
    Para melhorar a qualidade do sistema poderíamos ainda
    (4): Implementar um Classificador Naive Bayes para classificar o estado do jogador como 'CRITICAL' ou 'NORMAL'.

- Board - 30 s - 
    - O board  permitiu-nos organizar o trabalho e definir as prioridades.
    - O board foi criado no GitHub e foi dividido em 4 colunas: Todo, In Progress, Review e Done.
    
    - Número de User Stories: 23
    - Número de Branchs: 
    - Número de Pull Requests (Separar por Label):
    - Número de Issues:
    - Número de Commits:      

- Architecture - 1 min 30s - Não esquecer de dar ênfase no ElasticSearch ("Monitoring")


- Demo - 4 min 
Vamos agora fazer uma demonstração do nosso projeto e já temos equipas e jogadores criados.

Equipa:
- 10 Players (5 dsstes já se encontram numa sessão criada pelo PT)
- 1 Team Director
- 1 Personal Trainer

- Team Director da equipa X cria um Coach e gera o Registration Code que este usa para dar Sign Up. O Coach tem acessos diferentes do Personal Trainer, podendo por exemplo começar Jogos e ver as estatísticas em sessões anteriores.

- Coach entra na sua conta e vê o estado das sessões da equipa.
- Coach cria uma match e seleciona todos os jogadores da equipa. (Nota: Para não perder o tempo a preencher o forms na apresentação, o Coach clica em criar a Match e passado 1s o form é preenchido automaticamente)
- Coach vê os dados jogadores em tempo real (cards) e clica num deles para ver mais detalhes. Coach observa que o Jogador X está 'CRITICAL' e pondera substituí-lo.

- (Deixamos 2 min e depois terminamos a sessão) Coach termina a sessão. Neste momento o sistema continua apenas com uma sessão ativa porque a outra foi terminada.
- Player entra na sua conta e como estamos no ínicio da época, participou em poucas sessões.
- Player vê os detalhes da sua última sessão, como se pode ver é mantida a confidencialidade e só consegue ver os seus dados.

- Admin entra na sua conta e vê o número de dados dos sensores que estão a ser enviados para o sistema.
Deste modo ele conseguirá monitorizar o sistema e perceber se está a ser usado de forma correta.


