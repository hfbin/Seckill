package cn.hfbin.seckill.interceptor;

import cn.hfbin.seckill.entity.User;
import cn.hfbin.seckill.redis.RedisService;
import cn.hfbin.seckill.redis.UserKey;
import cn.hfbin.seckill.result.CodeMsg;
import cn.hfbin.seckill.result.Result;
import cn.hfbin.seckill.util.CookieUtil;
import cn.hfbin.seckill.util.JsonUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/**
 * 使用拦截器统一校验用户权限
 */
@Component
public class AuthorityInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //请求controller中的方法名

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //解析HandlerMethod

        String methodName = handlerMethod.getMethod().getName();
        String className = handlerMethod.getBean().getClass().getSimpleName();

        StringBuffer requestParamBuffer = new StringBuffer();
        Map paramMap = request.getParameterMap();
        Iterator it = paramMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            String mapKey = (String) entry.getKey();
            String mapValue = "";

            //request的这个参数map的value返回的是一个String[]
            Object obj = entry.getValue();
            if (obj instanceof String[]) {
                String[] strs = (String[]) obj;
                mapValue = Arrays.toString(strs);
            }
            requestParamBuffer.append(mapKey).append("=").append(mapValue);
        }
        //对于拦截器中拦截manage下的login.do的处理,对于登录不拦截，直接放行
        if (!StringUtils.equals(className, "SeckillController")) {
            //如果是拦截到登录请求，不打印参数，因为参数里面有密码，全部会打印到日志中，防止日志泄露
            logger.info("权限拦截器拦截到请求,className:{},methodName:{}", className, methodName);
            return true;
        }
        /*
        if (StringUtils.equals(className, "LoginController") && StringUtils.equals(methodName, "doLogin")) {
            //如果是拦截到登录请求，不打印参数，因为参数里面有密码，全部会打印到日志中，防止日志泄露
            logger.info("权限拦截器拦截到请求,className:{},methodName:{}", className, methodName);
            return true;
        }
        if (StringUtils.equals(className, "PageController") || StringUtils.equals(className, "GoodsController")) {
            //如果是拦截到登录请求，不打印参数，因为参数里面有密码，全部会打印到日志中，防止日志泄露
            logger.info("权限拦截器拦截到请求,className:{},methodName:{}", className, methodName);
            return true;
        }
*/
        logger.info("--> 权限拦截器拦截到请求,className:{},methodName:{},param:{}", className, methodName, requestParamBuffer);

//		User user = (User)request.getSession().getAttribute(Const.CURRENT_USER);

        User user = null;
        String loginToken = CookieUtil.readLoginToken(request);
        if (StringUtils.isNotEmpty(loginToken)) {
            user = redisService.get(UserKey.getByName, loginToken, User.class);
        }


        if (user == null) {
            //重置 重写response一定要重置 这里要添加reset，否则报异常 getWriter() has already been called for this response
            response.reset();
            //geelynote 这里要设置编码，否则会乱码
            response.setCharacterEncoding("UTF-8");
            // 这里要设置返回值类型，因为全部是json接口。
           /* response.setContentType("application/json;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.print(JsonUtil.obj2String(Result.error(CodeMsg.USER_NO_LOGIN)));*/

            response.sendRedirect("/page/login");
            // 这里要关闭流
            //out.flush();
            //out.close();
            return false;
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
