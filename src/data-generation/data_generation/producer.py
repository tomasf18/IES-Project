
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

# Check if sensors IDs are provided
if len(sys.argv) < 2:
    print("Usage: producer.py sensor_id1 [sensor_id2 ...]")
    sys.exit(1)

# Get sensor IDs from command line arguments
sensor_ids = [int(id_str) for id_str in sys.argv[1:]]

# Kafka Configuration
bootstrap_servers = 'localhost:29092'  # Adjust if your Kafka broker is at a different address
topic_name = f'sensor_data'

# Initialize Kafka Producer with JSON serialization
producer = KafkaProducer(
    bootstrap_servers=bootstrap_servers,
    value_serializer=lambda m: json.dumps(m).encode('utf-8')
)

# Average Heart Rate, Respiratory Rate, and Body Temperature
average_heart_rate = 90.0 # bmp
average_respiratory_rate = 16.0 # rpm
average_body_temperature = 36.5 # ºC

# Standard deviation
standard_deviation_heart_rate = 30.0
standard_deviation_respiratory_rate = 2.0
standard_deviation_body_temperature = 0.8

# Generate new quotes for movies every 5 to 10 seconds and introduce them in a Kafka topic. 
try:
    while True:
        message_list = []
        for sensor_id in sensor_ids:
            # Generate random values following a normal distribution
            heartRate = int(random.gauss(average_heart_rate, standard_deviation_heart_rate))
            respiratoryRate = int(random.gauss(average_respiratory_rate, standard_deviation_respiratory_rate))
            bodyTemperature = round(random.gauss(average_body_temperature, standard_deviation_body_temperature), 1)

            sensor_data = {
                'sensor_id': sensor_id,
                'heart_rate': round(heartRate, 2),
                'respiratory_rate': round(respiratoryRate, 2),
                'body_temperature': round(bodyTemperature, 2)
            }
            message_list.append(sensor_data)
        
        # Send the list of sensor data as a single message
        value = {
            "records": message_list
        }
        producer.send(topic_name, value=value)
        print(f"Produced: {value}")
        time.sleep(1.0)

except KeyboardInterrupt:
    print("Stopping producer...")
finally:
    # Ensure all messages are sent and close the producer
    producer.flush()
    producer.close()
    print("Producer closed.")
