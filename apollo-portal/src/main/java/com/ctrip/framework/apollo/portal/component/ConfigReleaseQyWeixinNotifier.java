/*
 * Copyright 2021 Apollo Authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package com.ctrip.framework.apollo.portal.component;

import com.ctrip.framework.apollo.portal.entity.bo.ReleaseHistoryBO;
import com.ctrip.framework.apollo.portal.environment.Env;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * publish qyWeixin
 *
 * @author chiang
 */
@Component
public class ConfigReleaseQyWeixinNotifier {

  private static final Logger logger = LoggerFactory.getLogger(ConfigReleaseQyWeixinNotifier.class);

  private final RestTemplateFactory restTemplateFactory;

  private RestTemplate restTemplate;

  public ConfigReleaseQyWeixinNotifier(RestTemplateFactory restTemplateFactory) {
    this.restTemplateFactory = restTemplateFactory;
  }

  @PostConstruct
  public void init() {
    // init restTemplate
    restTemplate = restTemplateFactory.getObject();
  }

  private String markdownMsg(ReleaseHistoryBO releaseHistory,Env env) {

    StringBuilder markdown = new StringBuilder();
//    markdown.append("股票监控预警:\n")
//        .append(">[股票代码]").append("(").append(stockUrl).append(")").append(":<font color=\"warning\">").append(alarm.getTsCode()).append("</font>\n")
//        .append(">[股票名称]").append("(").append(companyUrl).append(")").append(":<font color=\"warning\">").append(stock.getName()).append("</font>\n")
//        .append(">最低价:<font color=\"warning\">").append(alarm.getMinClosePrice()).append("</font>\n")
//        .append(">最高价:<font color=\"warning\">").append(alarm.getMaxClosePrice()).append("</font>\n")
//        .append(">交易日:<font color=\"comment\">").append(alarm.getTradeDate()).append("</font>\n")
//        .append(">当前价:<font color=\"comment\">").append(alarm.getCurrentPrice()).append("</font>\n")
//        .append(">涨跌幅:<font color=\"comment\">").append(alarm.getChange()).append("</font>\n")
//        .append(">低估指数:<font color=\"info\">").append(alarm.getPctChg() * 100).append("%").append("</font>\n")
//        .append(">参考天数:<font color=\"info\">").append(alarm.getDays()).append("</font>\n")
//        .append(">所有制性质:<font color=\"info\">").append(StringUtils.defaultString(stock.getOwnership(), "-")).append("</font>\n")
//        .append(">实际控制人:<font color=\"info\">").append(stock.getActualController()).append("</font>\n")
//        .append(">行业:<font color=\"info\">").append(stock.getIndustry()).append("</font>\n");
    //qyWeChatRobot.sendMarkdownMsg(null, markdown.toString());

    return markdown.toString();
  }
  public void notify(Env env,String robotUrl,ReleaseHistoryBO releaseHistory) {
    //TODO

  }
}
