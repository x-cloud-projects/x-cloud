#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.admin.web.vo.message;

/*-
 * ${symbol_pound}%L
 * Message Admin Service
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


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Admin
 */
@Data
public class MessageSendVO {

    @NotBlank(message = "模板编号不能为空")
    private String templateCode;

    @NotNull(message = "发送目标不能为空")
    @Size(min = 1, message = "发送目标不能为空")
    private List<String> targetList;

    private List<MessageData> messageDataList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MessageData {

        private String target;

        private String jsonData;
    }
}
