package co.lq.modules.mnt.rest;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.lq.aop.log.Log;
import co.lq.modules.mnt.domain.ServerDeploy;
import co.lq.modules.mnt.service.ServerDeployService;
import co.lq.modules.mnt.service.dto.ServerDeployQueryCriteria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author zhanghouying
 * @date 2019-08-24
 */
@Api(tags = "服务器管理")
@RestController
@RequestMapping("/api/serverDeploy")
public class ServerDeployController {

    private final ServerDeployService serverDeployService;

    public ServerDeployController(ServerDeployService serverDeployService) {
        this.serverDeployService = serverDeployService;
    }

    @Log("导出服务器数据")
    @ApiOperation("导出服务器数据")
    @GetMapping(value = "/download")
    @PreAuthorize("@el.check('admin','serverDeploy:list')")
    public void download(HttpServletResponse response, ServerDeployQueryCriteria criteria) throws IOException {
        serverDeployService.download(serverDeployService.queryAll(criteria), response);
    }

    @Log("查询服务器")
    @ApiOperation(value = "查询服务器")
    @GetMapping
    @PreAuthorize("@el.check('admin','serverDeploy:list')")
    public ResponseEntity<Object> getServers(ServerDeployQueryCriteria criteria, Pageable pageable) {
        return new ResponseEntity<>(serverDeployService.queryAll(criteria, pageable), HttpStatus.OK);
    }

    @Log("新增服务器")
    @ApiOperation(value = "新增服务器")
    @PostMapping
    @PreAuthorize("@el.check('admin','serverDeploy:add')")
    public ResponseEntity<Object> create(@Validated @RequestBody ServerDeploy resources) {
        return new ResponseEntity<>(serverDeployService.create(resources), HttpStatus.CREATED);
    }

    @Log("修改服务器")
    @ApiOperation(value = "修改服务器")
    @PutMapping
    @PreAuthorize("@el.check('admin','serverDeploy:edit')")
    public ResponseEntity<Object> update(@Validated @RequestBody ServerDeploy resources) {
        serverDeployService.update(resources);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Log("删除服务器")
    @ApiOperation(value = "删除Server")
    @DeleteMapping
    @PreAuthorize("@el.check('admin','serverDeploy:del')")
    public ResponseEntity<Object> delete(@RequestBody Set<Long> ids) {
        serverDeployService.delete(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Log("测试连接服务器")
    @ApiOperation(value = "测试连接服务器")
    @PostMapping("/testConnect")
    @PreAuthorize("@el.check('admin','serverDeploy:add')")
    public ResponseEntity<Object> testConnect(@Validated @RequestBody ServerDeploy resources) {
        return new ResponseEntity<>(serverDeployService.testConnect(resources), HttpStatus.CREATED);
    }
}
