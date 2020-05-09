package hellojpa;

import javax.persistence.*;

@Entity
public class MemberProduct {

    @Id @GeneratedValue
    private Long Id;

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    private Member member;

    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    private Product product;

}
