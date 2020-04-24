package com.rest.api.test;


import lombok.extern.java.Log;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class profileTest {
   /* @Autowired
    UserJpaRepo userRepo;

    @Autowired
    ProfileJpaRepo profileJpaRepo;

    @Test
    public void testInsertUsers() {

        IntStream.range(1, 101).forEach(i -> {
            User user = new User();
            user.setUid("user" + i);
            user.setUpw("pw" + i);
            user.setUname("사용자" + i);

            userRepo.save(user);

        });

    }// end method

    @Test
    public void testInsertProfile() {

        User user = new User();
        user.setUid("user1");

        for (int i = 1; i < 5; i++) {

            Profile profile1 = new Profile();
            profile1.setFname("face" + i + ".jpg");

            if (i == 1) {
                profile1.setCurrent(true);
            }

            profile1.setUser(user);

            profileRepo.save(profile1);

        }*/
}
