package com.tedspsecuritydemo.spsecurity.service;

import com.tedspsecuritydemo.spsecurity.config.StatusEnum;
import com.tedspsecuritydemo.spsecurity.dto.PaymentActionRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentRequestDto;
import com.tedspsecuritydemo.spsecurity.dto.PaymentResponseDto;
import com.tedspsecuritydemo.spsecurity.dto.mapper.PaymentPaymentResponseDtoMapper;
import com.tedspsecuritydemo.spsecurity.model.Company;
import com.tedspsecuritydemo.spsecurity.model.Payment;
import com.tedspsecuritydemo.spsecurity.model.Users;
import com.tedspsecuritydemo.spsecurity.repository.CompanyRepository;
import com.tedspsecuritydemo.spsecurity.repository.PaymentRepository;
import com.tedspsecuritydemo.spsecurity.repository.UsersRepository;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private CompanyRepository companyRepository;

    private PaymentPaymentResponseDtoMapper paymentPaymentResponseDtoMapper = Mappers.getMapper(PaymentPaymentResponseDtoMapper.class);

    public PaymentResponseDto initiatePayment(PaymentRequestDto payment){
        log.info("Payment service initiate payment!");
        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> usr = usersRepository.findByName(user.getName());

        // generate reference number -- 11 digit unique
        // ad-time
        LocalDateTime localDateTime = LocalDateTime.now().minusSeconds(9);

        String payRef = "ad"+localDateTime.getNano();

        // set status to 'initiated'
        Optional<Company> company = companyRepository.findById(payment.getCompany_id());

        int status = 1;
        List<Users> assignManager = assignManagerForEachPayment(payment.getAmount());

        Payment payment1 = Payment.builder()
                .referenceNumber(payRef)
                .amount(payment.getAmount())
                .company(company.orElse(null))
                .pay_description(payment.getPay_description())
                .status(status)
                .users(assignManager)
                .payeeId(usr.get().getId())
                        .build();

        // save payment
        Payment p = paymentRepository.save(payment1);
        PaymentResponseDto paymentResponseDto = returnDto(p);

        return paymentResponseDto;
//        return null;
    }

    private List<Users> assignManagerForEachPayment(int amount){
        log.info("INSIDE assignment method");
        Optional<List<Users>> users = usersRepository.findManagerBasedOnApprovalLimit(amount);
        return users.orElse(null);
    }

    private PaymentResponseDto returnDto(Payment p){
        String paymentStatus = "";
        switch (p.getStatus()){
            case 1:
                paymentStatus = StatusEnum.INITIATED.name();
                break;
            case 2:
                paymentStatus = StatusEnum.ON_HOLD.name();
                break;
            case 3:
                paymentStatus = StatusEnum.CANCELED.name();
                break;
            case 4:
                paymentStatus = StatusEnum.APPROVED.name();
                break;
            default:
                paymentStatus = StatusEnum.PENDING_APPROVAL.name();
                break;
        }
        return  PaymentResponseDto.builder()
                .id(p.getId())
                .pay_description(p.getPay_description())
                .amount(p.getAmount())
                .referenceNumber(p.getReferenceNumber())
                .status(paymentStatus)
                .build();
    }

    public List<PaymentResponseDto> getAllPayment(){
        log.info("Get All Payment");
        List<Payment> payments = paymentRepository.findAllPayment();

        List<PaymentResponseDto> paymentResponseDtos = paymentPaymentResponseDtoMapper.getAll(payments);

        return paymentResponseDtos;
    }

    public List<PaymentResponseDto> getMyPayment(String uId){

        Authentication user = SecurityContextHolder.getContext().getAuthentication();
        Optional<Users> usr = usersRepository.findByName(user.getName());

        if(usr.orElseThrow().getId()  != Integer.parseInt(uId)) return null;

        List<Payment> payments = paymentRepository.findMyPayments(Integer.parseInt(uId));
        List<PaymentResponseDto> paymentResponseDtos = paymentPaymentResponseDtoMapper.getAll(payments);
        return paymentResponseDtos;
    }

    public String applyActionMangerRo(String action, Integer paymentId){
        log.info("Inside applyActionMangerRo");

        // get payment by Id ... change status of that payment ... return success or fail
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if(payment == null) return "no payment found.";
        int stTs;
        switch (action){
            case "INITIATED":
                stTs = 1;
                break;
            case "ON_HOLD":
                stTs = 2;
                break;
            case "CANCELED":
                stTs = 3;
                break;
            case "APPROVED":
                stTs = 4;
                break;
            default:
                stTs = 5;
                break;
        }
        payment.setStatus(stTs);
        paymentRepository.save(payment);
//        paymentRepository.updatePayment(paymentId, stTs);
        return new String("Success");
    }

}




/*
   when payment creation
    - pay description
    - company id
    - amount

    - user payee id

    - reference number 9 digit unique alphanumeric
    - status initially should be 'initiated'

*/