/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.service;
import jakarta.validation.Valid; import jakarta.validation.constraints.*; import org.springframework.http.*; import org.springframework.stereotype.Service; import org.springframework.web.server.ResponseStatusException; import java.math.*; import java.util.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service public class EnterpriseBoardService {
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public VoteResult evaluate(@Valid VoteRequest r){
  int eligible=r.totalDirectors()-r.recused(); if(eligible<=0) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"无可参与表决的董事");
  int counted=r.yesVotes()+r.noVotes()+r.abstentions(); if(counted>r.presentDirectors()-r.recused()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"表决票数超过可表决出席人数");
  int quorumRequired=eligible/2+1; boolean quorum=(r.presentDirectors()-r.recused())>=quorumRequired; int decisive=r.yesVotes()+r.noVotes();
  BigDecimal approvalRate=decisive==0?BigDecimal.ZERO:BigDecimal.valueOf(r.yesVotes()*100L).divide(BigDecimal.valueOf(decisive),2,RoundingMode.HALF_UP);
  boolean passed=quorum&&decisive>0&&approvalRate.compareTo(r.approvalThreshold())>=0;
  List<String> blockers=new ArrayList<>(); if(!quorum) blockers.add("出席人数未达到法定人数"); if(decisive==0) blockers.add("没有有效赞成或反对票"); if(quorum&&decisive>0&&!passed) blockers.add("赞成比例未达到决议门槛");
  return new VoteResult(r.meetingNo(),eligible,quorumRequired,quorum,approvalRate,passed,blockers,passed?"RESOLUTION_PASSED":"RESOLUTION_FAILED");
 }
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record VoteRequest(@NotBlank String meetingNo,@Min(1) int totalDirectors,@Min(0) int presentDirectors,@Min(0) int recused,@Min(0) int yesVotes,@Min(0) int noVotes,@Min(0) int abstentions,@NotNull @DecimalMin("50") @DecimalMax("100") BigDecimal approvalThreshold){}
 /**
  * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
  */
 public record VoteResult(String meetingNo,int eligibleDirectors,int quorumRequired,boolean quorumMet,BigDecimal approvalRate,boolean passed,List<String> blockers,String decision){}
}

