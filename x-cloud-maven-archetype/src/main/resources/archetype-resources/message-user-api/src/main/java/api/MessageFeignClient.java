#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.api;

/*-
 * ${symbol_pound}%L
 * ${artifactId}
 * %%
 * Copyright (C) 2022 xfeel
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ${symbol_pound}L%
 */

import ${package}.api.vo.MessageSendVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * @author Admin
 */
@FeignClient(name = "${symbol_dollar}{x-module.message.name:message}",url = "${symbol_dollar}{x-module.message.base-url}",fallbackFactory = MessageFeignClient.MessageFeignFallbackFactory.class)
public interface MessageFeignClient extends MessageApi{

    @Slf4j
    @Component
    class MessageFeignFallbackFactory implements FallbackFactory<MessageFeignClient> {
        @Override
        public MessageFeignClient create(Throwable cause) {
            return new MessageFeignClient() {
                @Override
                public ResponseEntity<?> send(MessageSendVO sendParam) {
                    return null;
                }
            };
        }
    }
}
