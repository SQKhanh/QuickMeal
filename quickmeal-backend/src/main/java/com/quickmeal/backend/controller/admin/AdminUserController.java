/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickmeal.backend.controller.admin;

import com.quickmeal.backend.constant.ConstAccount;
import com.quickmeal.backend.dto.user.*;
import com.quickmeal.backend.service.UserAdminService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

/**
 *
 * @author <a href="https://www.facebook.com/khanhdepzai.pro/">KhanhDzai</a>
 */
@RestController
@RequestMapping("/api/admin/users")
@PreAuthorize(ConstAccount.Role.HAS_AUTHORITY_ADMIN)
public class AdminUserController {

    private final UserAdminService userAdminService;

    public AdminUserController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @GetMapping
    public Page<UserResponseDTO> getUsers(
            @PageableDefault(size = 10, sort = "id") Pageable pageable,
            @RequestParam(required = false) String search
    ) {
        return userAdminService.getUsers(pageable, search);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody UserCreateDTO dto) {
        return userAdminService.createUser(dto);
    }

    @PutMapping("/{id}")
    public UserResponseDTO updateUser(@PathVariable Long id,
            @RequestBody UserUpdateDTO dto) {
        return userAdminService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userAdminService.deleteUser(id);
    }
}
