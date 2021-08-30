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

import com.ctrip.framework.apollo.common.constants.ReleaseOperation;
import com.ctrip.framework.apollo.portal.entity.bo.ReleaseHistoryBO;
import com.ctrip.framework.apollo.portal.entity.vo.Change;
import com.ctrip.framework.apollo.portal.entity.vo.ReleaseCompareResult;
import com.ctrip.framework.apollo.portal.environment.Env;
import java.util.List;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * publish dingTalk
 *
 * @author chiang
 */
@Component
public class ConfigReleaseDingTalkNotifier {

  private static final Logger logger = LoggerFactory.getLogger(ConfigReleaseDingTalkNotifier.class);

  private final RestTemplateFactory restTemplateFactory;

  private RestTemplate restTemplate;

  public ConfigReleaseDingTalkNotifier(RestTemplateFactory restTemplateFactory) {
    this.restTemplateFactory = restTemplateFactory;
  }

  @PostConstruct
  public void init() {
    // init restTemplate
    restTemplate = restTemplateFactory.getObject();
  }


  private String action(int op) {
    switch (op) {
      case ReleaseOperation.GRAY_RELEASE: {
        return "灰度发布";
      }
      case ReleaseOperation.NORMAL_RELEASE: {
        return "配置发布";
      }
      case ReleaseOperation.ROLLBACK: {
        return "配置回滚";
      }
      case ReleaseOperation.GRAY_RELEASE_MERGE_TO_MASTER: {
        return "全量发布";
      }
      default:
        return null;
    }
  }

  private String markdownMsg(String action,ReleaseHistoryBO releaseHistory, Env env) {

    StringBuilder markdown = new StringBuilder();
    markdown.append(action).append(":\n")
        .append(">appId:*").append(releaseHistory.getAppId()).append("* \n\n")
        .append(">环境:*").append(env.getName()).append("* \n\n")
        .append(">集群:*").append(releaseHistory.getClusterName()).append("* \n\n")
        .append(">命名空间:*").append(releaseHistory.getNamespaceName()).append("* \n\n")
        .append(">操作人:*").append(releaseHistory.getOperatorDisplayName()).append("*\n\n")
        .append(">标题:*").append(releaseHistory.getReleaseTitle()).append("* \n\n")
//        .append(">key:*").append("").append("* \n\n")
//        .append(">旧值:*").append("").append("* \n\n")
//        .append(">新值:*").append("").append("* \n\n")
        .append(">发布日期:*").append(releaseHistory.getReleaseTimeFormatted()).append("* \n\n")
        .append(">发布备注:*").append(
        releaseHistory.getReleaseComment() == null ? "" : releaseHistory.getReleaseComment())
        .append("* \n\n")
        .append(">发布分支:*")
        .append(releaseHistory.getBranchName() == null ? "" : releaseHistory.getBranchName())
        .append("* \n\n");

    return markdown.toString();
  }

  public void notify(Env env, String robot, int security, String signKey, List<String> atList,
      ReleaseHistoryBO releaseHistory) {
    //TODO
    String action = action(releaseHistory.getOperation());
  }
}
