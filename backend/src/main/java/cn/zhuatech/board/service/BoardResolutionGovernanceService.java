/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.service;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Service
public class BoardResolutionGovernanceService {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Result evaluate(Request request) {
        List<String> blockers = new ArrayList<>();
        int eligible = request.totalDirectors() - request.conflictedDirectors();
        int quorumRequired = eligible / 2 + 1;
        if (!request.noticePeriodSatisfied()) blockers.add("会议通知期不满足章程要求");
        if (!request.conflictsDeclared()) blockers.add("董事利益冲突披露不完整");
        if (request.presentEligibleDirectors() < quorumRequired) blockers.add("出席人数未达到法定人数");
        if (request.yesVotes() < quorumRequired) blockers.add("赞成票未达到通过门槛");
        if (!request.minutesEvidenceComplete()) blockers.add("会议记录与表决证据不完整");
        String decision = blockers.isEmpty() ? "ADOPTED" : "NOT_ADOPTED";
        return new Result(request.resolutionNo(), decision, eligible, quorumRequired,
                List.copyOf(blockers), blockers.isEmpty());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Request(@NotBlank String resolutionNo, @Min(1) int totalDirectors,
                          @Min(0) int conflictedDirectors, @Min(0) int presentEligibleDirectors,
                          @Min(0) int yesVotes, boolean noticePeriodSatisfied,
                          boolean conflictsDeclared, boolean minutesEvidenceComplete) {
        /**
         * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
         */
        public Request {
            if (resolutionNo == null || resolutionNo.isBlank()) throw new IllegalArgumentException("resolutionNo is required");
            if (totalDirectors < 1 || conflictedDirectors < 0 || conflictedDirectors >= totalDirectors)
                throw new IllegalArgumentException("invalid director counts");
            if (presentEligibleDirectors < 0 || yesVotes < 0 || yesVotes > presentEligibleDirectors)
                throw new IllegalArgumentException("invalid attendance or votes");
        }
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record Result(String resolutionNo, String decision, int eligibleDirectors,
                         int quorumRequired, List<String> blockers, boolean effective) {}
}
