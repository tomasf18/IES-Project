# GET /sessions/historical-extra-details

1. IF MATCH, show the Opposite Team, weather, etc... -> Adicionar estes atributos na classe HistoricalInfoResponse.

# GET /sessions/historical-info

1. Criar classe "HistoricalExtraDetailsPlayer" que extende "RealTimeExtraDetailsPlayer" e tem os atributos:
    1. private Double averageHeartRate;
    2. private Double averageBodyTemperature;
    3. private Double averageRespiratoryRate;

    Deste modo, tenho os valores médios de cada uma das 3 métricas para cada um dos jogadores.

De resto é muito semelhante ao **GET /sessions/historical-extra-details**.

Provalvemente posso dividir a função getHistoricalExtraDetails em diferentes funções que depois posso reutilizar porque é basicamente a mesma coisa.

Diferenças:
1. "HistoricalExtraDetailsPlayer" em vez de "RealTimeExtraDetailsPlayer" e para todos os users e todas as métricas fazer o historicalDataPlayer.setAverageHeartRate(timeSeriesQueries.getAverageValue(metricData)).
2. LocalDateTime endTime = session.getEndTime(); agora é null, portanto tenho que converter para a data atual antes de fazer o resto.