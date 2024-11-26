import sys
import types

# Compatibility fix for Python >= 3.12
m = types.ModuleType('kafka.vendor.six.moves', 'Mock module')
setattr(m, 'range', range)
sys.modules['kafka.vendor.six.moves'] = m

from kafka import KafkaProducer
import json
import time
import random

# Kafka Configuration
bootstrap_servers = 'localhost:29092'  # Adjust if your Kafka broker is at a different address
topic_name = f'lab05_quotes_real_time'

# Initialize Kafka Producer with JSON serialization
producer = KafkaProducer(
    bootstrap_servers=bootstrap_servers,
    value_serializer=lambda m: json.dumps(m).encode('utf-8')
)

# 
averageHeartRate = 160.0 # bmp
averageRespiratoryRate = 16.0 # rpm
averageBodyTemperature = 36.5 # ºC

# Standard deviation
standardDeviationHeartRate = 20.0
standardDeviationRespiratoryRate = 2.0
standardDeviationBodyTemperature = 0.5

# Generate new quotes for movies every 5 to 10 seconds and introduce them in a Kafka topic. 
try:
    while True:
        # Generate random values following a normal distribution
        heartRate = random.gauss(averageHeartRate, standardDeviationHeartRate)
        respiratoryRate = random.gauss(averageRespiratoryRate, standardDeviationRespiratoryRate)
        bodyTemperature = random.gauss(averageBodyTemperature, standardDeviationBodyTemperature)

        message = {
            'heartRate': heartRate,
            'respiratoryRate': respiratoryRate,
            'bodyTemperature': bodyTemperature
        }
        producer.send(topic_name, value=message)
        print(f"Produced: {message}")

        time.sleep(random.uniform(5, 10))

except KeyboardInterrupt:
    print("Stopping producer...")
finally:
    # Ensure all messages are sent and close the producer
    producer.flush()
    producer.close()
    print("Producer closed.")
