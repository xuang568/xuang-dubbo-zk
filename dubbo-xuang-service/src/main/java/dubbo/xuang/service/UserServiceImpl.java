package dubbo.xuang.service;

import dubbo.xuang.serviceApi.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String sayHello(String name) {
        System.out.println("服务被调用了。。。");
        return "myname is "+name;
    }
}
