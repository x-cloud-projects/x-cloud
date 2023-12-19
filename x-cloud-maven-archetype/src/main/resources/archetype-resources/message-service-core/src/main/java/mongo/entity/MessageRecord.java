#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.mongo.entity;

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


import ${package}.dao.entity.Message;
import ${package}.dao.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;

/**
 * @author Admin
 */
@Document(collection = "message_record")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageRecord {

    @MongoId
    private String id;

    @Field("message")
    private Message message;

    @Field("template")
    private Template template;

    @Field("target")
    private String target;

    @Field("content")
    private String content;

    @CreatedDate
    @Field(value = "gmt_create")
    private Date gmtCreate;

    @LastModifiedDate
    @Field(value = "gmt_modified")
    private Date gmtModified;
}
