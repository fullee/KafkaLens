import org.apache.kafka.clients.admin.KafkaAdminClient
import org.apache.kafka.common.config.SaslConfigs
import java.util.*

class KafkaClient() {
    fun configure(): Properties {
        val username = "ZmFpdGhmdWwtaG9uZXliZWUtMTA5NTgkRRCktSbSc85odqixDJroDDQIz95f-co"
        val password = "MjlhMWMxZjAtZTcyOC00MDI2LWE0NzUtNWQ4NDVkOTgxZmE3"

        val props = Properties()
        props["bootstrap.servers"] = "https://faithful-honeybee-10958-us1-kafka.upstash.io:9092"
        props["sasl.mechanism"] = "SCRAM-SHA-256"
        props["security.protocol"] = "SASL_SSL"
        props[SaslConfigs.SASL_JAAS_CONFIG] =
            """
            org.apache.kafka.common.security.scram.ScramLoginModule required
            username="$username"
            password="$password";
            """.trimMargin()
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["key.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"
        props["value.deserializer"] = "org.apache.kafka.common.serialization.StringDeserializer"

        return props
    }

    init {
        val properties = configure()
        val client = KafkaAdminClient.create(properties)
    }

}