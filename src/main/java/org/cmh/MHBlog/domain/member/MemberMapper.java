package org.cmh.MHBlog.domain.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;

@Mapper
public interface MemberMapper {

    /**
     * 회원 정보 저장 (회원가입)
     * @param params - 회원 정보
     */
    void save(MemberRequest params);

    /**
     * 회원 상세정보 조회
     * @param loginId - UK
     * @return 회원 상세정보
     */
    MemberResponse findByLoginId(String loginId);

    /**
     * 회원 정보 수정
     * @param params - 회원 정보
     */
    void update(MemberRequest params);

    /**
     * 회원 정보 삭제 (회원 탈퇴)
     * @param id - PK
     */
    void deleteById(Long id);

    /**
     * 회원 수 카운팅 (ID 중복 체크)
     * @param loginId - UK
     * @return 회원 수
     */
    int countByLoginId(String loginId);


    /**
     * API 회원 정보 저장 (회원가입)
     * @param  - 회원 정보
     */
    void saveAPI(HashMap<String, Object> userInfo);


    /**
     * API 로그인 조회 (가입여부)
     * @param loginId - UK
     * @return 회원 수
     */
    int ApiLoginId(String loginId);


}
