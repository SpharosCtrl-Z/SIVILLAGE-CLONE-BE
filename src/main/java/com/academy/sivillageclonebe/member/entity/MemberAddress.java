package com.academy.sivillageclonebe.member.entity;

import com.academy.sivillageclonebe.member.dto.MemberAddressDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@NoArgsConstructor
public class MemberAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String zipCode;

    private String request;

    @Column(nullable = false)
    private String receiverName;

    @Column(nullable = false)
    private String receiverPhone;

    @Column(nullable = false)
    private boolean defaultAddress;

    @Builder
    public MemberAddress(
            Long id,
            Member member,
            String address,
            String zipCode,
            String request,
            String receiverName,
            String receiverPhone,
            boolean defaultAddress
    ) {
        this.id = id;
        this.member = member;
        this.address = address;
        this.zipCode = zipCode;
        this.request = request;
        this.receiverName = receiverName;
        this.receiverPhone = receiverPhone;
        this.defaultAddress = defaultAddress;
    }

    public void editMemberAddress(MemberAddressDto memberAddressDto) {
        this.address = memberAddressDto.getAddress();
        this.zipCode = memberAddressDto.getZipCode();
        this.request = memberAddressDto.getRequest();
        this.receiverName = memberAddressDto.getReceiverName();
        this.receiverPhone = memberAddressDto.getReceiverPhone();
        this.defaultAddress = memberAddressDto.isDefaultAddress();
    }

    public void editDefaultAddress(boolean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

}
