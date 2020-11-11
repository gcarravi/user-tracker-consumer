package com.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class Main {

    public static void main(String[] args){

        SuggestionEngine suggestionEngine = new SuggestionEngine();

        Properties properties = new Properties();
        properties.put("bootstrap.servers","localhost:9093, localhost:9094");
        properties.put("group.id","user-tracker-consumer");
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        consumer.subscribe(Arrays.asList("user-tracking"));

        // a poll operation will only happens once so in order to keep the app running we wrap this code in an infinite while loop.
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(100));
            for(ConsumerRecord<String, String> record : records){
                suggestionEngine.processSuggestion(record.key(), record.value());
            }
        }
    }






}
