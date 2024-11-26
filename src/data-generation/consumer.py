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
nMec = 104384  # Replace with your actual nMec number
topic_name = f'lab05_{nMec}'
group_id = f'lab05_consumer_{nMec}'  # Unique group ID
# group_id = 'new_unique_group_id'

# Create a Kafka consumer instance
consumer = KafkaConsumer(
    topic_name,
    bootstrap_servers=bootstrap_servers,
    group_id=group_id,
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
    print(f"Received message: {msg}")
