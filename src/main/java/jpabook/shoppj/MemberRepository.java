//package jpabook.shoppj;
//
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import jpabook.shoppj.domain.Member;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class MemberRepository {
//    @PersistenceContext
//    private EntityManager em;
//
//    public long save(Member member) {
//        em.persist(member);
//        return member.getId();
//    }
//
//    public Member find(long id) {
//        return em.find(Member.class, id);
//    }
//}
