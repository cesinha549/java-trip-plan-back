#!/bin/bash

# Prompt for feature name
read -p "Enter the feature name (e.g. user, trip, notification): " FEATURE

# Lowercase and Capitalize for naming
FEATURE_LC=$(echo "$FEATURE" | tr '[:upper:]' '[:lower:]')
FEATURE_CAP=$(echo "$FEATURE_LC" | sed -E 's/(^|_)([a-z])/\U\2/g')

# Base path
BASE="src/main/java/com/travelplanner/core/${FEATURE_LC}"

# Create folders
mkdir -p "${BASE}/domain/port/in"
mkdir -p "${BASE}/domain/port/out"
mkdir -p "${BASE}/application"
mkdir -p "${BASE}/adapter/in/kafka"
mkdir -p "${BASE}/adapter/out/kafka"

# Domain Ports
cat > "${BASE}/domain/port/in/${FEATURE_CAP}UseCase.java" <<EOF
package com.travelplanner.core.${FEATURE_LC}.domain.port.in;

public interface ${FEATURE_CAP}UseCase {
    // Define use-case methods here
}
EOF

cat > "${BASE}/domain/port/out/${FEATURE_CAP}Port.java" <<EOF
package com.travelplanner.core.${FEATURE_LC}.domain.port.out;

public interface ${FEATURE_CAP}Port {
    // Define external port methods here
}
EOF

# Application Service
cat > "${BASE}/application/${FEATURE_CAP}Service.java" <<EOF
package com.travelplanner.core.${FEATURE_LC}.application;

import com.travelplanner.core.${FEATURE_LC}.domain.port.in.${FEATURE_CAP}UseCase;
import com.travelplanner.core.${FEATURE_LC}.domain.port.out.${FEATURE_CAP}Port;
import org.springframework.stereotype.Service;

@Service
public class ${FEATURE_CAP}Service implements ${FEATURE_CAP}UseCase {

    private final ${FEATURE_CAP}Port ${FEATURE_LC}Port;

    public ${FEATURE_CAP}Service(${FEATURE_CAP}Port ${FEATURE_LC}Port) {
        this.${FEATURE_LC}Port = ${FEATURE_LC}Port;
    }

    // Implement methods here
}
EOF

# Kafka Consumer (Inbound Adapter)
cat > "${BASE}/adapter/in/kafka/${FEATURE_CAP}Consumer.java" <<EOF
package com.travelplanner.core.${FEATURE_LC}.adapter.in.kafka;

import com.travelplanner.core.${FEATURE_LC}.domain.port.in.${FEATURE_CAP}UseCase;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class ${FEATURE_CAP}Consumer {

    private final ${FEATURE_CAP}UseCase ${FEATURE_LC}UseCase;

    public ${FEATURE_CAP}Consumer(${FEATURE_CAP}UseCase ${FEATURE_LC}UseCase) {
        this.${FEATURE_LC}UseCase = ${FEATURE_LC}UseCase;
    }

    @KafkaListener(topics = "${FEATURE_LC}-events", groupId = "${FEATURE_LC}-group")
    public void consume(String message) {
        // Convert and process the message
    }
}
EOF

# Kafka Producer (Outbound Adapter)
cat > "${BASE}/adapter/out/kafka/${FEATURE_CAP}Producer.java" <<EOF
package com.travelplanner.core.${FEATURE_LC}.adapter.out.kafka;

import com.travelplanner.core.${FEATURE_LC}.domain.port.out.${FEATURE_CAP}Port;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class ${FEATURE_CAP}Producer implements ${FEATURE_CAP}Port {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public ${FEATURE_CAP}Producer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendEvent(String message) {
        kafkaTemplate.send("${FEATURE_LC}-events", message);
    }
}
EOF

echo "✅ Hexagonal scaffold for '${FEATURE_LC}' created successfully."
