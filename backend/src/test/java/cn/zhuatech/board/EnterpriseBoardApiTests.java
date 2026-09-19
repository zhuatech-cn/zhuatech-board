/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board;
import org.junit.jupiter.api.Test; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.context.SpringBootTest; import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc; import org.springframework.http.MediaType; import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic; import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post; import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@SpringBootTest @AutoConfigureMockMvc class EnterpriseBoardApiTests { @Autowired MockMvc mvc;

 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void quorumAndThresholdProduceValidResolution() throws Exception {mvc.perform(post("/api/enterprise/board/evaluate-vote").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"meetingNo":"BM-001","totalDirectors":7,"presentDirectors":6,"recused":1,"yesVotes":4,"noVotes":1,"abstentions":0,"approvalThreshold":66.67}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.quorumMet").value(true)).andExpect(jsonPath("$.data.approvalRate").value(80.00)).andExpect(jsonPath("$.data.passed").value(true));}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 @Test void missingQuorumFailsResolution() throws Exception {mvc.perform(post("/api/enterprise/board/evaluate-vote").with(httpBasic("operator","operator123")).contentType(MediaType.APPLICATION_JSON).content("""
 {"meetingNo":"BM-002","totalDirectors":9,"presentDirectors":3,"recused":0,"yesVotes":2,"noVotes":1,"abstentions":0,"approvalThreshold":66.67}
 """)).andExpect(status().isOk()).andExpect(jsonPath("$.data.quorumMet").value(false)).andExpect(jsonPath("$.data.decision").value("RESOLUTION_FAILED"));}
}

