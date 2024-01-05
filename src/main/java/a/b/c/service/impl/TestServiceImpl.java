package a.b.c.service.impl;

import a.b.c.bean.User;
import a.b.c.mapper.TestMapper;
import a.b.c.service.TestService;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;


    @Override
    public boolean execute() {
        boolean result = false;
        List<User> userList = testMapper.execute();

        int cnt = 0;
        for( User user : userList ){
            cnt++;
            log.info("[{}] user : {}", cnt, user);
        }

        if( userList.size() > 0 ){
            result = true;
        }
        return result;
    }
}
