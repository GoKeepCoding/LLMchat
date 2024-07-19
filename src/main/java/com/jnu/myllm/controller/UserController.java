package com.jnu.myllm.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.jnu.myllm.common.ServerResponseEntity;
import com.jnu.myllm.domain.LUser;
import com.jnu.myllm.dto.LoginDTO;
import com.jnu.myllm.dto.RegisterDTO;
import com.jnu.myllm.service.LUserService;
import com.jnu.myllm.utils.JwtUtil;
import com.jnu.myllm.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import static com.jnu.myllm.constant.ResponseCode.SUCCESS;

@Slf4j
@RestController
@RequestMapping("/home")
public class UserController {

    @Autowired
    private LUserService lUserService;

    /**
     * 处理用户登录请求。
     * 通过接收登录DTO（数据传输对象），验证用户名和密码的正确性，并返回相应的登录结果。
     *
     * @param loginDto 包含用户名和密码的登录请求DTO。
     * @return 如果用户名或密码错误，返回错误的响应实体；如果登录成功，返回包含用户信息的响应实体。
     */
    @PostMapping("/login")
    public ServerResponseEntity<String> login(@RequestBody LoginDTO loginDto,  HttpServletRequest request, HttpServletResponse response){
        // 记录登录尝试的日志，包括用户名和密码。
        log.info("用户登录,用户名:{},密码:{}", loginDto.getUsername(),loginDto.getPassword());

        // 创建查询条件，指定查询用户名与登录请求中提供的用户名相同的用户。
        LambdaQueryWrapper<LUser> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LUser::getUsername,loginDto.getUsername());

        // 根据用户名查询用户信息。
        LUser lUser = lUserService.getOne(lambdaQueryWrapper);

        // 如果查询结果为空，表示用户名不存在，返回错误响应。
        if(lUser == null){
            return ServerResponseEntity.error("用户名错误",null);
        }

        // 如果查询到的用户密码与登录请求中的密码不匹配，返回错误响应。
        if(!lUser.getPassword().equals(loginDto.getPassword())){
            return ServerResponseEntity.error("密码错误",null);
        }

        // 登录成功，创建并返回包含用户基本信息的VO对象。
        UserVO userVO = new UserVO();
        userVO.setUserId(lUser.getUserId());
        userVO.setUsername(lUser.getUsername());
        userVO.setPassword(lUser.getPassword());
        userVO.setStatus(lUser.getStatus());

        // 获取当前请求的会话对象，并存储登陆数据到session中
        request.getSession().setAttribute("user", userVO);

        String token = JwtUtil.generateToken(lUser.getUsername());
        response.setHeader("Authorization", token);


        return ServerResponseEntity.success(SUCCESS.getCode(),token);
        //return new ResponseEntity<>(ServerResponseEntity.success(SUCCESS.getCode(), userVO), headers, HttpStatus.OK)
    }

    @PostMapping("/register")
    public ServerResponseEntity<UserVO> register(@RequestBody RegisterDTO registerDto, HttpServletRequest httpServletRequest){
        log.info("用户注册，用户名:{}，密码:{}",registerDto.getUsername(),registerDto.getPassword());

        LUser lUser = new LUser();
        lUser.setUsername(registerDto.getUsername());
        lUser.setPassword(registerDto.getPassword());
        lUser.setPassword("123456");
        lUser.setStatus(1);
        lUser.setCreateTime(new java.util.Date());
        lUser.setUpdateTime(new java.util.Date());
        if(lUserService.save(lUser)){
            UserVO userVO = new UserVO();
            userVO.setUserId(lUser.getUserId());
            userVO.setUsername(lUser.getUsername());
            userVO.setPassword(lUser.getPassword());
            userVO.setStatus(lUser.getStatus());

            httpServletRequest.getSession().setAttribute("user",userVO);
            return ServerResponseEntity.success(SUCCESS.getCode(),userVO);
        }

        return ServerResponseEntity.error("注册失败",null);
    }

    @PostMapping("/logout")
    public ServerResponseEntity<String> logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().removeAttribute("user");
        return ServerResponseEntity.success(SUCCESS.getCode(),"退出成功");
    }

}
