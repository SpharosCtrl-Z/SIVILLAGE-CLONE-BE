package com.academy.sivillageclonebe.cart.controller;

import com.academy.sivillageclonebe.cart.dto.CartDto;
import com.academy.sivillageclonebe.cart.dto.ProductsByCartDto;
import com.academy.sivillageclonebe.cart.service.CartService;
import com.academy.sivillageclonebe.cart.service.ProductsByCartService;
import com.academy.sivillageclonebe.cart.vo.CartRequestVo;
import com.academy.sivillageclonebe.cart.vo.ProductsByCartUpdateVo;
import com.academy.sivillageclonebe.common.entity.CommonResponseEntity;
import com.academy.sivillageclonebe.common.entity.CommonResponseMessage;
import com.academy.sivillageclonebe.common.utills.SecurityUtils;
import com.academy.sivillageclonebe.member.entity.Member;
import com.academy.sivillageclonebe.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/member/cart")
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final SecurityUtils securityUtils;
    private final ProductsByCartService productsByCartService;

    @Operation(summary = "장바구니 등록 API", description = "장바구니 등록 API 입니다.", tags = {"Cart"})
    @PostMapping("/add")
    public CommonResponseEntity<Void> addProductToCart(@RequestBody CartRequestVo cartRequestVo) {

        Member member = securityUtils.getAuthenticatedMember();
        CartDto cartDto = CartDto.builder()
                .memberId(member.getId())
                .brandId(productService.getBrandIdByProductCode(cartRequestVo.toDto().getProductCode()))
                .build();
        ProductsByCartDto productsByCartDto = cartRequestVo.toDto();

        productsByCartService.addProductToCart(cartDto, productsByCartDto);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "장바구니 조회 API", description = "장바구니 조회 API 입니다.", tags = {"Cart"})
    @GetMapping
    public CommonResponseEntity<List<CartDto>> getAllCart() {
        Member member = securityUtils.getAuthenticatedMember();
        List<CartDto> carts = cartService.findAllByMemberId(member.getId());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                carts
        );
    }

    @Operation(summary = "장바구니 삭제 API", description = "장바구니 삭제 API 입니다.", tags = {"Cart"})
    @DeleteMapping("/{id}")
    public CommonResponseEntity<Void> deleteProductsByCart(@PathVariable Long id) {

        productsByCartService.removeProductFromCart(id);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "장바구니 업데이트 API", description = "장바구니 업데이트 API 입니다.", tags = {"Cart"})
    @PutMapping("/{id}")
    public CommonResponseEntity<Void> updateCheck(@PathVariable Long id) {

        productsByCartService.updateCheck(id);

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

    @Operation(summary = "장바구니 옵션 업데이트 API", description = "장바구니 옵션 업데이트 API 입니다.", tags = {"Cart"})
    @PutMapping("/option/{id}")
    public CommonResponseEntity<Void> updateOption(@PathVariable Long id, @RequestBody ProductsByCartUpdateVo productsByCartUpdateVo) {

        productsByCartService.updateOption(id, productsByCartUpdateVo.toDto());

        return new CommonResponseEntity<>(
                HttpStatus.OK,
                CommonResponseMessage.SUCCESS.getMessage(),
                null
        );
    }

}
