FROM debezium/connect:2.6

# Ajoute les converters AVRO de Confluent
ADD https://packages.confluent.io/maven/io/confluent/kafka-connect-avro-converter/7.6.0/kafka-connect-avro-converter-7.6.0.jar /kafka/connect/libs/
ADD https://packages.confluent.io/maven/io/confluent/common/config/7.6.0/common-config-7.6.0.jar /kafka/connect/libs/
ADD https://packages.confluent.io/maven/io/confluent/common/utils/7.6.0/common-utils-7.6.0.jar /kafka/connect/libs/
