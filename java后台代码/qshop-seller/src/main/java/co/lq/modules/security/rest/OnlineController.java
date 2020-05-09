package co.lq.modules.security.rest;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.lq.aop.log.Log;
import co.lq.modules.security.service.OnlineUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author billy
 */
@RestController
@RequestMapping("/auth/online")
@Api(tags = "系统：在线用户管理")
public class OnlineController {

    private final OnlineUserService onlineUserService;

    public OnlineController(OnlineUserService onlineUserService) {
        this.onlineUserService = onlineUserService;
    }

    @ApiOperation("查询在线用户")
    @GetMapping
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> getAll(@RequestParam(value = "filter", defaultValue = "") String filter,
                                         @RequestParam(value = "type", defaultValue = "0") int type,
                                         Pageable pageable) {
        return new ResponseEntity<>(onlineUserService.getAll(filter, type, pageable), HttpStatus.OK);
    }

    @Log("导出数据")
    @ApiOperation("导出数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check()")
    public void download(HttpServletResponse response, @RequestParam(value = "filter", defaultValue = "") String filter,
                         @RequestParam(value = "type", defaultValue = "0") int type)
            throws IOException {
        onlineUserService.download(onlineUserService.getAll(filter, type), response);
    }

    @ApiOperation("踢出用户")
    @DeleteMapping
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> delete(@RequestBody Set<String> keys) throws Exception {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        for (String key : keys) {
            onlineUserService.kickOut(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("踢出移动端用户")
    @PostMapping("/delete")
    @PreAuthorize("@el.check()")
    public ResponseEntity<Object> deletet(@RequestBody Set<String> keys) throws Exception {
        //if(StrUtil.isNotEmpty("22")) throw new BadRequestException("演示环境禁止操作");
        for (String key : keys) {
            onlineUserService.kickOutT(key);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
