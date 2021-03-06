package neu.train.project.bvo.Controller;

import io.swagger.annotations.ApiOperation;
import neu.train.framework.web.controller.BaseController;
import neu.train.framework.web.domain.AjaxResult;
import neu.train.framework.web.page.TableDataInfo;
import neu.train.project.bvo.Service.StoreService;
import neu.train.project.bvo.domain.Store;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bvo")
public class StoreController extends BaseController {
    @Autowired
    StoreService storeService;
    @GetMapping("/store/list")
    @ApiOperation("try to get store list")
    @PreAuthorize("@ss.hasPermi('bvo:store:query')")
    public TableDataInfo getStoreList(Store store) {
        startPage();
        List<Store> storeList = storeService.getStoreList(store);
        return getDataTable(storeList);
    }

    @GetMapping("/store/{id}")
    @ApiOperation("try to get store by Id")
    @PreAuthorize("@ss.hasPermi('bvo:store:query')")
    public AjaxResult getStoreById(@PathVariable("id") Long storeId) {
        Store store = storeService.selectStoreByStoreId(storeId);
        return AjaxResult.success(store);
    }

    @PutMapping("/store")
    @ApiOperation("try to update store")
    @PreAuthorize("@ss.hasPermi('bvo:store:edit')")
    public AjaxResult updateStore(@RequestBody Store store) {
        storeService.updateStore(store);
        return AjaxResult.updateSuccess();
    }

    @PostMapping("/store")
    @ApiOperation("try to add store")
    @PreAuthorize("@ss.hasPermi('bvo:store:add')")
    public AjaxResult addStore(@RequestBody Store store) {
        storeService.insertStore(store);
        return AjaxResult.insertSuccess();
    }

    @DeleteMapping("/store/{ids}")
    @ApiOperation("try to delete store")
    @PreAuthorize("@ss.hasPermi('bvo:store:delete')")
    public AjaxResult delStore(@PathVariable("ids")int[] ids ){
        storeService.deleteStoreByIds(ids);
        return AjaxResult.deleteSuccess();
    }

}
