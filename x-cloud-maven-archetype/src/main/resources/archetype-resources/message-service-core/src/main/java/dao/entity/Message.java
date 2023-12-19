#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.dao.entity;

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


import com.xfeel.support.jpa.entity.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Admin
 */
@Entity
@Table(name = "message")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@DynamicInsert
public class Message extends BaseEntity {

    @ApiModelProperty("关联模板ID")
    @Column(name = "template_id",nullable = false)
    private Long templateId;

    @ApiModelProperty("发送目标,多个英文逗号(,)分割")
    @Column(name = "target",length = 2000,nullable = false)
    private String target;

    @ApiModelProperty("消息数据，格式:{${symbol_escape}"target${symbol_escape}":${symbol_escape}"${symbol_escape}",${symbol_escape}"jsonData${symbol_escape}":${symbol_escape}"{${symbol_escape}${symbol_escape}${symbol_escape}"username${symbol_escape}${symbol_escape}${symbol_escape}":${symbol_escape}${symbol_escape}${symbol_escape}"test${symbol_escape}${symbol_escape}${symbol_escape}",${symbol_escape}${symbol_escape}${symbol_escape}"status${symbol_escape}${symbol_escape}${symbol_escape}":${symbol_escape}${symbol_escape}${symbol_escape}"禁用${symbol_escape}${symbol_escape}${symbol_escape}"}${symbol_escape}"}")
    @Column(name = "json_data",length = 5000)
    private String jsonData;
}
