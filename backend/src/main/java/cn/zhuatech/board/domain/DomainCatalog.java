/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.domain;

import org.springframework.stereotype.Component;
import java.util.*;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Component
public class DomainCatalog {
    private final Map<String, WorkflowAction> actions = new LinkedHashMap<>();

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public DomainCatalog() {
        actions.put("CONVENE", new WorkflowAction("CONVENE", "正式召集", List.of("草稿"), "已召集", "ADMIN"));
        actions.put("START", new WorkflowAction("START", "开始会议", List.of("已召集"), "会议中", "ADMIN"));
        actions.put("VOTE", new WorkflowAction("VOTE", "发起表决", List.of("会议中"), "表决中", "ADMIN"));
        actions.put("RESOLVE", new WorkflowAction("RESOLVE", "形成决议", List.of("表决中"), "决议形成", "ADMIN"));
        actions.put("SIGN", new WorkflowAction("SIGN", "完成签署", List.of("决议形成"), "已签署", "ADMIN"));
        actions.put("CLOSE", new WorkflowAction("CLOSE", "归档闭会", List.of("已签署"), "已归档", "ADMIN"));
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String systemName() { return "知华科技董事会与公司治理系统"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String scene() { return "董事会会议、议程、材料、利益冲突、表决、决议、签署、行动项与归档"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String initialStatus() { return "草稿"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String partyLabel() { return "董事/治理机构"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String amountLabel() { return "事项金额"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String quantityLabel() { return "议题数量"; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public String dueLabel() { return "会议日期"; }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public List<ModuleDefinition> modules() {
        return List.of(
            new ModuleDefinition("MEETING", "会议管理", "管理年度计划、会议类型、召集通知和会务"),
            new ModuleDefinition("AGENDA", "议程管理", "收集议案、排序、时间分配与临时议题"),
            new ModuleDefinition("MATERIAL", "会议材料", "控制版本、密级、水印、分发和撤回"),
            new ModuleDefinition("ATTENDEE", "参会管理", "维护董事、列席人、授权代表与出席状态"),
            new ModuleDefinition("CONFLICT", "利益冲突", "申报关联关系并触发回避和披露"),
            new ModuleDefinition("VOTE", "表决管理", "支持记名、匿名、累积投票与委托表决"),
            new ModuleDefinition("RESOLUTION", "决议管理", "生成决议文本、编号、生效和对外披露"),
            new ModuleDefinition("SIGNATURE", "电子签署", "组织董事签署、验证证书与时间戳"),
            new ModuleDefinition("ACTION", "行动项", "分派决议任务、期限、证据和督办升级"),
            new ModuleDefinition("ARCHIVE", "治理档案", "归档通知、材料、签到、录音、纪要和决议")
        );
    }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public Map<String, WorkflowAction> actions() { return Collections.unmodifiableMap(actions); }

    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record ModuleDefinition(String code, String name, String description) {}
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public record WorkflowAction(String code, String label, List<String> from, String to, String requiredRole) {}
}
