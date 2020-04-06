package com.rest.api.service.cache;

import com.rest.api.common.CacheKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheSevice {

    @Caching(evict = {
            @CacheEvict(value = CacheKey.POST, key = "#postId"),
            @CacheEvict(value = CacheKey.POSTS, key = "#boardName")
//            @CacheEvict(value = CacheKey.ROLE, key = "#roleId"),
//            @CacheEvict(value = CacheKey.ROLES, key = "#roleName")
    })
    public boolean deleteBoardCache(long postId, String boardName) {
        log.debug("deleteBoardCache - postId {}, boardName {}", postId, boardName);
        return true;
    }
 //   public boolean deleteDeptCache(long roleId, String deptName) {
  //      log.debug("deleteDeptCache - roleId {}, deptName {}", roleId, deptName);
  //      return true;
  // }
}
