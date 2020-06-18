/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.streamnative.pulsar.handlers.amqp.test.frame;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Amqp client channel for receive responses from server.
 */
public class AmqpClientChannel {

    private final BlockingQueue<Object> responses;

    public AmqpClientChannel() {
        this.responses = new LinkedBlockingQueue<>();
    }

    public void add(Object response) {
        responses.add(response);
    }

    public Object poll() {
        int count = 3;
        Object object = responses.poll();
        while (object == null && count > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            object = responses.poll();
            count--;
        }
        return object;
    }
}
