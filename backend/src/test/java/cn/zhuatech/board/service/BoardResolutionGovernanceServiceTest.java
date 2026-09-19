/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.service;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
class BoardResolutionGovernanceServiceTest {
    private final BoardResolutionGovernanceService service = new BoardResolutionGovernanceService();
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void adoptsResolutionWithQuorumAndEvidence() {
        var r = service.evaluate(new BoardResolutionGovernanceService.Request("RES-001", 7, 1, 5, 4, true, true, true));
        assertEquals("ADOPTED", r.decision()); assertEquals(4, r.quorumRequired()); assertTrue(r.effective());
    }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Test void rejectsResolutionWithoutQuorumAndMinutes() {
        var r = service.evaluate(new BoardResolutionGovernanceService.Request("RES-002", 7, 1, 3, 2, false, false, false));
        assertEquals("NOT_ADOPTED", r.decision()); assertEquals(5, r.blockers().size()); assertFalse(r.effective());
    }
}
