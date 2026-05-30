/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.quickmeal.backend.collection.jwt;

import com.quickmeal.util.Logger;
import com.quickmeal.backend.constant.ConstSecurity;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *
 * @author <a href="https://www.facebook.com/khanhdepzai.pro/">KhanhDzai</a>
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtTokenWhiteList {

    // Sử dụng ConcurrentHashMap để đảm bảo an toàn đa luồng mà không cần lock thủ công
    private static final ConcurrentHashMap<String, String> tokenUsernameMap = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, Long> tokenExpirationMap = new ConcurrentHashMap<>();

    static {
        // Sử dụng ScheduledExecutorService thay vì Thread.startVirtualThread + LockSupport
        // Nó chuyên dụng cho các tác vụ định kỳ, tiết kiệm tài nguyên hơn
        var scheduler = Executors.newSingleThreadScheduledExecutor(runnable -> {
            var thread = Thread.ofVirtual().unstarted(runnable);
            thread.setDaemon(true); // Đảm bảo thread này không chặn JVM tắt
            return thread;
        });

        scheduler.scheduleAtFixedRate(JwtTokenWhiteList::checkExpiredTokens, 1, 1, TimeUnit.SECONDS);
    }

    public static void addToken(String token, String username) {
        tokenExpirationMap.put(token, System.currentTimeMillis() + ConstSecurity.JWT.JWT_EXPIRATION_TIME);
        tokenUsernameMap.put(token, username);
    }

    public static void removeToken(String token) {
        tokenExpirationMap.remove(token);
        tokenUsernameMap.remove(token);
    }

    private static void checkExpiredTokens() {
        try {
            final var currentTime = System.currentTimeMillis();

            // Sử dụng entrySet().removeIf() cực kỳ tối ưu và an toàn trong ConcurrentHashMap
            tokenExpirationMap.entrySet().removeIf(entry -> {
                if (entry.getValue() < currentTime) {
                    tokenUsernameMap.remove(entry.getKey());
                    Logger.DebugLogic("Đã xóa token hết hạn: " + entry.getKey());
                    return true;
                }
                return false;
            });
        } catch (Exception e) {
            Logger.error("ERROR loop check token", e);
        }
    }

    public static boolean isTokenWhitelisted(String token, String username) {
        // Kiểm tra tồn tại và hạn sử dụng một cách tinh gọn
        Long expirationTime = tokenExpirationMap.get(token);
        if (expirationTime == null || !tokenUsernameMap.containsKey(token)) {
            return false;
        }
        return System.currentTimeMillis() < expirationTime;
    }
}
