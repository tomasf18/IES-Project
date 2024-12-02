import sys
import types

# Compatibility fix for Python >= 3.12
m = types.ModuleType('kafka.vendor.six.moves', 'Mock module')
setattr(m, 'range', range)
sys.modules['kafka.vendor.six.moves'] = m

from kafka import KafkaConsumer
import json
from kafka import TopicPartition

# Kafka Configuration
bootstrap_servers = 'localhost:29092'  # Adjust if needed
topic_name = f'players_data_real_time'

# Create a Kafka consumer instance
consumer = KafkaConsumer(
    topic_name,
    bootstrap_servers=bootstrap_servers,
    enable_auto_commit=True,
    auto_offset_reset='earliest',  # Start consuming from the beginning of the topic if no offset is stored
)

PARTITIONS = []
for partition in consumer.partitions_for_topic(topic_name):
    PARTITIONS.append(TopicPartition(topic_name, partition))

end_offsets = consumer.end_offsets(PARTITIONS)
print(end_offsets)

print(f"Consuming messages from topic '{topic_name}':")

# Consume messages
for message in consumer:
    msg = message.value
    print(f"Message: {msg}")

    # Store the message in a file
    with open('consumed_messages.txt', 'a') as file:
        file.write(f"{msg}\n")

