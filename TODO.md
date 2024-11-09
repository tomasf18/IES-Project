# GET /sessions/historical-extra-details

1. IF MATCH, show the Opposite Team, weather, etc... -> Adicionar estes atributos na classe HistoricalExtraDetailsResponse (ou na HistoricalInfoResponse).

2. Adicionar na classe Match atributo result. Quando faço o end do Match devia puder inserir o result.

# GET /sessions/real-time-extra-details

Diferença relativamente ao anterior, não faço o display do average heart, faço do último valor.
    private Double lastHeartRate;
    private Double lastBodyTemperature;
    private Double lastRespiratoryRate;
    
Acho que nem preciso usar "RealTimeInfoResponse" posso continuar a usar "HistoricalInfoResponse".


# GET /sessions/real-time-info

1. Adicionar à classe "RealTimeExtraDetailsPlayer" os seguintes atributos:
    1. private Double lastHeartRate;
    2. private Double lastBodyTemperature;
    3. private Double lastRespiratoryRate;

    Deste modo, tenho os últimos valores de cada uma das 3 métricas para cada um dos jogadores.
    Definir um segundo construtor com estes atributos extra.

    
# DANILO: GET /sessions/historical-info

1. Adicionar à classe "RealTimeExtraDetailsPlayer" os seguintes atributos:
    1. private Double averageHeartRate;
    2. private Double averageBodyTemperature;
    3. private Double averageRespiratoryRate;

    Deste modo, tenho os valores médios de cada uma das 3 métricas para cada um dos jogadores.
    Definir um segundo construtor com estes atributos extra.

De resto é muito semelhante ao **GET /sessions/historical-extra-details**.

Provalvemente posso dividir a função getHistoricalExtraDetails em diferentes funções que depois posso reutilizar porque é basicamente a mesma coisa.

Diferenças:
1. "RealTimeExtraDetailsPlayer" vai ter mais atributos. Para todas as métricas fazer o historicalDataPlayer.setAverageHeartRate(timeSeriesQueries.getAverageValue(metricData)).
2. LocalDateTime endTime = session.getEndTime(); agora é null, portanto tenho que converter para a data atual antes de fazer o resto. LocalDateTime endTime = LocalDateTime.now();

