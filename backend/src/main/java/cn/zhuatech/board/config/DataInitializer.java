/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.config;

import cn.zhuatech.board.domain.DomainCatalog;
import cn.zhuatech.board.model.*;
import cn.zhuatech.board.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@Configuration
public class DataInitializer {
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @Bean
    CommandLineRunner seed(BusinessRecordRepository records, SystemSettingRepository settings, DomainCatalog catalog) {
        return args -> {
            if (records.count() > 0) return;
        settings.save(new SystemSetting("quorumRule", "过半数"));
        settings.save(new SystemSetting("defaultVoteThreshold", "66.67"));
        settings.save(new SystemSetting("materialWatermark", "启用"));
        settings.save(new SystemSetting("signaturePolicy", "全体应签董事"));
            int sequence = 1;
            for (var module : catalog.modules()) {
                String no = "BOARD-DEMO-" + String.format("%03d", sequence);
                records.save(new BusinessRecord(
                    no, module.code(), module.name() + "标准业务事项", "上海总部",
                    sequence % 3 == 0 ? "内控经理" : "业务专员", catalog.initialStatus(),
                    BigDecimal.valueOf(sequence * 12500L), sequence * 2,
                    LocalDate.now().plusDays(sequence * 3L), sequence % 4 == 0 ? "关注" : "正常",
                    module.description() + "；用于演示完整台账、状态流、权限和审计能力"));
                sequence++;
            }
        };
    }
}

