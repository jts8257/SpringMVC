package com.example.mvc_corepro.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MemberRepository {

    /**
     * 동시성 문제가 고려되어 있지 않음,
     * 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
     */
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    // 생성을 static으로 돌리기 때문에 프로세스 초기부터 끝까지 계속 있음.
    private static final MemberRepository instance = new MemberRepository();

    //임의로 싱글톤으로 만들기 때문에 생성자를 private 로 막아둠
    private MemberRepository () {

    }

    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

    public static MemberRepository getInstance() {
        return instance;
    }
}
