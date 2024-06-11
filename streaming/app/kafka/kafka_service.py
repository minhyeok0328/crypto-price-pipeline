import json

from kafka import KafkaProducer
from app.config.kafka_config import KAFKA_CONFIG

class KafkaService:
    topic: str
    producer: KafkaProducer

    def __init__(self, topic: str) -> None:
        self.topic = topic
        self.producer = KafkaProducer(
            **KAFKA_CONFIG,
            value_serializer=lambda v: json.dumps(v).encode('utf-8')
        )

    def send_message(self, message: object) -> None:
        self.producer.send(self.topic, value=message)
        self.producer.flush()
