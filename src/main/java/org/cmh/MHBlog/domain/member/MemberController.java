package org.cmh.MHBlog.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 로그인 페이지
    @GetMapping("/login.do")
    public String openLogin() {
        return "member/login";
    }

    // 로그인
    @PostMapping("/login")
    @ResponseBody
    public MemberResponse login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId = request.getParameter("loginId");
        String password = request.getParameter("password");
        MemberResponse member = memberService.login(loginId, password);

        System.out.println("일반로그인");

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
            System.out.println("로그인세션 저장 완료");
        }

        System.out.println("로그인 컨트롤러>>>>"+member);
        return member;
    }

    // 로그아웃
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login.do";
    }


    // 회원 정보 저장 (회원가입)
    @PostMapping("/members")
    @ResponseBody
    public Long saveMember(@RequestBody final MemberRequest params) {
        return memberService.saveMember(params);
    }

    // 회원 상세정보 조회
    @GetMapping("/members/{loginId}")
    @ResponseBody
    public MemberResponse findMemberByLoginId(@PathVariable final String loginId) {
        return memberService.findMemberByLoginId(loginId);
    }

    // 회원 정보 수정
    @PatchMapping("/members/{id}")
    @ResponseBody
    public Long updateMember(@PathVariable final Long id, @RequestBody final MemberRequest params) {
        return memberService.updateMember(params);
    }

    // 회원 정보 삭제 (회원 탈퇴)
    @DeleteMapping("/members/{id}")
    @ResponseBody
    public Long deleteMemberById(final Long id) {
        return memberService.deleteMemberById(id);
    }

    // 회원 수 카운팅 (ID 중복 체크)
    @GetMapping("/member-count")
    @ResponseBody
    public int countMemberByLoginId(@RequestParam final String loginId) {
        return memberService.countMemberByLoginId(loginId);
    }



    //카카오 로그인
    @RequestMapping(value="/kakaoLogin", method=RequestMethod.GET )
    @ResponseBody
    public String kakaoLogin(@RequestParam(value = "code", required = false) String code, HttpServletRequest request) throws Exception {

        final MemberRequest params = new MemberRequest();

        //1. 액세스 토큰 받아오기
        String access_Token = memberService.getAccessToken(code);
        System.out.println("#########" + code);


        // 2. 엑세스 토큰을 통해 회원정보 받아오기
        HashMap<String, Object> userInfo = memberService.getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###nickname#### : " + userInfo.get("nickname"));
        System.out.println("###email#### : " + userInfo.get("email"));



        //계정정보
        String loginId = (String)userInfo.get("email"); //아이디는 이메일로 대체
        String password = access_Token; //패스워드 대신 엑세스토큰을 통해 로그인

        userInfo.put("loginId", loginId);
        userInfo.put("password", password);


        //가입여부 0 : 미가입 | 1: 기존회원
        int Regist_YN = memberService.countMemberByLoginId(loginId);



        System.out.println(">>>>>>>>>>>>" + Regist_YN);




        //신규회원
        if(Regist_YN == 0){

            memberService.saveMemberAPI(userInfo);
            System.out.println("API회원가입완료");


        }

        //로그인
        MemberResponse member = memberService.login(loginId, password);
        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (member != null) {
            HttpSession session = request.getSession();
            session.setAttribute("loginMember", member);
            session.setMaxInactiveInterval(60 * 30);
        }
        System.out.println("로그인완료");

        return "redirect:/post/list.do";



    }


}
