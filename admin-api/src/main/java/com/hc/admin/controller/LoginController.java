package com.hc.admin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hc.admin.authorization.JWTToken;
import com.hc.admin.authorization.JWTUtil;
import com.hc.admin.bean.OnLineUser;
import com.hc.admin.bean.User;
import com.hc.admin.common.Rets;
import com.hc.admin.common.UserManager;
import com.hc.admin.config.prop.HcProperties;
import com.hc.admin.common.utils.*;
import com.hc.admin.common.exception.HcException;
import com.hc.admin.service.RedisService;
import com.hc.admin.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.DbSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Slf4j
@Validated
@RestController
public class LoginController {

    @Autowired
    private RedisService redisService;
    @Autowired
    private UserManager userManager;
    @Autowired
    private UserService userService;
    @Autowired
    private HcProperties properties;
    @Autowired
    private ObjectMapper mapper;

    @PostMapping("/login")
    //@Limit(key = "login", period = 60, count = 20, name = "登录接口", prefix = "limit")
    public Object login(
            @NotBlank(message = "{required}") String username,
            @NotBlank(message = "{required}") String password, HttpServletRequest request) throws Exception {
        username = StringUtils.lowerCase(username);
        password = MD5Util.encrypt(username, password);

        final String errorMessage = "用户名或密码错误";
        User user = this.userManager.getUser(username);

        if (user == null){
            throw new HcException(errorMessage);}
        if (!StringUtils.equals(user.getPassword(), password)){
            throw new HcException(errorMessage);}
        if (User.STATUS_LOCK.equals(user.getStatus())){
            throw new HcException("账号已被锁定,请联系管理员！");}

        // todo 更新用户登录时间
        //this.userService.updateLoginTime(username);
        //todo 保存登录记录
        //LoginLog loginLog = new LoginLog();
        //loginLog.setUsername(username);
        //this.loginLogService.saveLoginLog(loginLog);

        String token = HcUtil.encryptToken(JWTUtil.sign(username, password));
        log.info("token="+token);
        LocalDateTime expireTime = LocalDateTime.now().plusSeconds(properties.getShiro().getTokenTimeOut());
        String expireTimeStr = DateUtil.formatFullTime(expireTime);
        JWTToken jwtToken = new JWTToken(token, expireTimeStr);

        Long userId = this.saveTokenToRedis(user, jwtToken, request);
        user.setUserId(userId);

        //Map<String, Object> userInfo = this.generateUserInfo(jwtToken, user);
        return Rets.success(token);
    }

//    @GetMapping("index/{username}")
//    public FebsResponse index(@NotBlank(message = "{required}") @PathVariable String username) {
//        Map<String, Object> data = new HashMap<>();
//        // 获取系统访问记录
//        Long totalVisitCount = loginLogMapper.findTotalVisitCount();
//        data.put("totalVisitCount", totalVisitCount);
//        Long todayVisitCount = loginLogMapper.findTodayVisitCount();
//        data.put("todayVisitCount", todayVisitCount);
//        Long todayIp = loginLogMapper.findTodayIp();
//        data.put("todayIp", todayIp);
//        // 获取近期系统访问记录
//        List<Map<String, Object>> lastSevenVisitCount = loginLogMapper.findLastSevenDaysVisitCount(null);
//        data.put("lastSevenVisitCount", lastSevenVisitCount);
//        User param = new User();
//        param.setUsername(username);
//        List<Map<String, Object>> lastSevenUserVisitCount = loginLogMapper.findLastSevenDaysVisitCount(param);
//        data.put("lastSevenUserVisitCount", lastSevenUserVisitCount);
//        return new FebsResponse().data(data);
//    }
//
//    @RequiresPermissions("user:online")
//    @GetMapping("online")
//    public FebsResponse userOnline(String username) throws Exception {
//        String now = DateUtil.formatFullTime(LocalDateTime.now());
//        Set<String> userOnlineStringSet = redisService.zrangeByScore(FebsConstant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
//        List<ActiveUser> activeUsers = new ArrayList<>();
//        for (String userOnlineString : userOnlineStringSet) {
//            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
//            activeUser.setToken(null);
//            if (StringUtils.isNotBlank(username)) {
//                if (StringUtils.equalsIgnoreCase(username, activeUser.getUsername())){
//                    activeUsers.add(activeUser);}
//            } else {
//                activeUsers.add(activeUser);
//            }
//        }
//        return new FebsResponse().data(activeUsers);
//    }
//
//    @DeleteMapping("kickout/{id}")
//    @RequiresPermissions("user:kickout")
//    public void kickout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
//        String now = DateUtil.formatFullTime(LocalDateTime.now());
//        Set<String> userOnlineStringSet = redisService.zrangeByScore(FebsConstant.ACTIVE_USERS_ZSET_PREFIX, now, "+inf");
//        ActiveUser kickoutUser = null;
//        String kickoutUserString = "";
//        for (String userOnlineString : userOnlineStringSet) {
//            ActiveUser activeUser = mapper.readValue(userOnlineString, ActiveUser.class);
//            if (StringUtils.equals(activeUser.getId(), id)) {
//                kickoutUser = activeUser;
//                kickoutUserString = userOnlineString;
//            }
//        }
//        if (kickoutUser != null && StringUtils.isNotBlank(kickoutUserString)) {
//            // 删除 zset中的记录
//            redisService.zrem(FebsConstant.ACTIVE_USERS_ZSET_PREFIX, kickoutUserString);
//            // 删除对应的 token缓存
//            redisService.del(FebsConstant.TOKEN_CACHE_PREFIX + kickoutUser.getToken() + "." + kickoutUser.getIp());
//        }
//    }
//
//    @GetMapping("logout/{id}")
//    public void logout(@NotBlank(message = "{required}") @PathVariable String id) throws Exception {
//        this.kickout(id);
//    }
//
//    @PostMapping("regist")
//    public void regist(
//            @NotBlank(message = "{required}") String username,
//            @NotBlank(message = "{required}") String password) throws Exception {
//        this.userService.regist(username, password);
//    }
//
    private Long saveTokenToRedis(User user, JWTToken token, HttpServletRequest request) throws Exception {
        String ip = IPUtil.getIpAddr(request);

        // 构建在线用户
        OnLineUser onLineUser = new OnLineUser();
        onLineUser.setUsername(user.getUserName());
        onLineUser.setIp(ip);
        onLineUser.setToken(token.getToken());
        onLineUser.setLoginAddress(AddressUtil.getCityInfo(DbSearcher.BTREE_ALGORITHM, ip));

        // zset 存储登录用户，score 为过期时间戳
        this.redisService.zadd(HcEnum.ACTIVE_USERS_ZSET_PREFIX.getValue(), Double.valueOf(token.getExipreAt()), mapper.writeValueAsString(onLineUser));
        // redis 中存储这个加密 token，key = 前缀 + 加密 token + .ip
        this.redisService.set(HcEnum.TOKEN_CACHE_PREFIX.getValue() + token.getToken() + StringPool.DOT + ip, token.getToken(), properties.getShiro().getTokenTimeOut() * 1000);
        return onLineUser.getId();
    }
//
//    /**
//     * 生成前端需要的用户信息，包括：
//     * 1. token
//     * 2. Vue Router
//     * 3. 用户角色
//     * 4. 用户权限
//     * 5. 前端系统个性化配置信息
//     *
//     * @param token token
//     * @param user  用户信息
//     * @return UserInfo
//     */
//    private Map<String, Object> generateUserInfo(JWTToken token, User user) {
//        String username = user.getUsername();
//        Map<String, Object> userInfo = new HashMap<>();
//        userInfo.put("token", token.getToken());
//        userInfo.put("exipreTime", token.getExipreAt());
//
//        Set<String> roles = this.userManager.getUserRoles(username);
//        userInfo.put("roles", roles);
//
//        Set<String> permissions = this.userManager.getUserPermissions(username);
//        userInfo.put("permissions", permissions);
//
//        UserConfig userConfig = this.userManager.getUserConfig(String.valueOf(user.getUserId()));
//        userInfo.put("config", userConfig);
//
//        user.setPassword("it's a secret");
//        userInfo.put("user", user);
//        return userInfo;
//    }
}
