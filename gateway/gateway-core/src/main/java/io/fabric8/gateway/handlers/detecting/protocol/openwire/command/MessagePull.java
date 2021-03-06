/**
 *  Copyright 2005-2016 Red Hat, Inc.
 *
 *  Red Hat licenses this file to you under the Apache License, version
 *  2.0 (the "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 *  implied.  See the License for the specific language governing
 *  permissions and limitations under the License.
 */
package io.fabric8.gateway.handlers.detecting.protocol.openwire.command;

import org.fusesource.hawtbuf.UTF8Buffer;

/**
 * Used to pull messages on demand.
 * 
 * @openwire:marshaller code="20" version="2"
 * 
 */
public class MessagePull extends BaseCommand {

    public static final byte DATA_STRUCTURE_TYPE = CommandTypes.MESSAGE_PULL;

    protected ConsumerId consumerId;
    protected ActiveMQDestination destination;
    protected long timeout;
    private MessageId messageId;
    private UTF8Buffer correlationId;

    public byte getDataStructureType() {
        return DATA_STRUCTURE_TYPE;
    }

    /**
     * Configures a message pull from the consumer information
     */
    public void configure(ConsumerInfo info) {
        setConsumerId(info.getConsumerId());
        setDestination(info.getDestination());
    }

    /**
     * @openwire:property version=1 cache=true
     */
    public ConsumerId getConsumerId() {
        return consumerId;
    }

    public void setConsumerId(ConsumerId consumerId) {
        this.consumerId = consumerId;
    }

    /**
     * @openwire:property version=1 cache=true
     */
    public ActiveMQDestination getDestination() {
        return destination;
    }

    public void setDestination(ActiveMQDestination destination) {
        this.destination = destination;
    }

    /**
     * @openwire:property version=1
     */
    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    /**
     * An optional correlation ID which could be used by a broker to decide which messages are pulled
     * on demand from a queue for a consumer
     *
     * @openwire:property version=3
     */
    public UTF8Buffer getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(UTF8Buffer correlationId) {
        this.correlationId = correlationId;
    }


    /**
     * An optional message ID which could be used by a broker to decide which messages are pulled
     * on demand from a queue for a consumer
     *
     * @openwire:property version=3
     */
    public MessageId getMessageId() {
        return messageId;
    }

    public void setMessageId(MessageId messageId) {
        this.messageId = messageId;
    }
}
