package com.vertace.javapostgre.controller;
import com.vertace.javapostgre.model.*;
import com.vertace.javapostgre.service.BannerService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/banner")
@RequiredArgsConstructor
public class BannerController {

    private final BannerService BannerService;

    @PostMapping
    public BannerModel createBanner(@RequestBody BannerModel BannerModel) throws Exception {
        try {
            return BannerService.createBanner(BannerModel);
        } catch (Exception e) {
            throw e;
        }
    }

    @PostMapping("/all")
    public GenericDataPagedResponse getBanner(@RequestBody CustomPageable customPageable) {
        return BannerService.listBanners(customPageable);
    }

    @PutMapping
    public ApiResponse updateBanner(@RequestBody BannerModel BannerModel) throws Exception {
        return BannerService.updateBanner(BannerModel);
    }

    @DeleteMapping
    public void deleteBanner(@RequestParam("id") Long id) throws Exception {
        BannerService.deleteBanner(id);
    }
    @GetMapping
    public ResponseEntity<?> getBanner(@RequestParam("id") Long id) throws Exception {
        System.out.println(id);
        return ResponseEntity.ok(BannerService.getBanner(id));
    }

}
