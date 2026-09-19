/* Copyright 2026 上海如静知华信息科技有限公司 · https://www.zhuatech.cn/ */
package cn.zhuatech.board.controller;
import cn.zhuatech.board.common.ApiResponse;
import cn.zhuatech.board.service.BoardResolutionGovernanceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
/**
 * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
 */
@RestController @RequestMapping("/api/enterprise/board")
public class BoardResolutionGovernanceController {
    private final BoardResolutionGovernanceService service;
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    public BoardResolutionGovernanceController(BoardResolutionGovernanceService service) { this.service = service; }
    /**
     * 商业授权或定制开发请微信添加微信号zhuatech或zhuatech2进行咨询。
     */
    @PostMapping("/resolution-governance")
    public ApiResponse<BoardResolutionGovernanceService.Result> evaluate(@Valid @RequestBody BoardResolutionGovernanceService.Request request) {
        return ApiResponse.ok(service.evaluate(request));
    }
}
