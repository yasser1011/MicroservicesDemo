package com.yasser.ecommerce.order;

import com.yasser.ecommerce.customer.CustomerClient;
import com.yasser.ecommerce.customer.CustomerResponse;
import com.yasser.ecommerce.kafka.OrderConfirmation;
import com.yasser.ecommerce.kafka.OrderProducer;
import com.yasser.ecommerce.orderline.OrderLineRequest;
import com.yasser.ecommerce.orderline.OrderLineService;
import com.yasser.ecommerce.payment.PaymentClient;
import com.yasser.ecommerce.payment.PaymentRequest;
import com.yasser.ecommerce.product.ProductClient;
import com.yasser.ecommerce.product.PurchaseRequest;
import com.yasser.ecommerce.product.PurchaseResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final PaymentClient paymentClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    public Long createOrder(OrderRequest request) {
        var customer = customerClient.findCustomerById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("customer id not found " + request.getCustomerId()));

        List<PurchaseResponse> purchaseResponse = productClient.purchaseProducts(request.getProducts());

        var order = orderRepository.save(orderMapper.toOrder(request));

//        for(PurchaseRequest purchaseRequest: request.getProducts()){
//            orderLineService.saveOrderLine(
//                    OrderLineRequest.builder()
//                            .orderId(order.getId())
//                            .productId(purchaseRequest.productId())
//                            .quantity(purchaseRequest.quantity())
//                            .build()
//            );
//        }
        List<OrderLineRequest> orderLineRequests = request.getProducts().stream().map(
                purchaseRequest -> OrderLineRequest.builder()
                        .orderId(order.getId())
                        .productId(purchaseRequest.productId())
                        .quantity(purchaseRequest.quantity())
                        .build()
        ).collect(Collectors.toList());
        orderLineService.saveOrderLineList(orderLineRequests);
//        var customer = new CustomerResponse(1L, "first", "last", "email");
        // payment process
        var paymentRequest = new PaymentRequest(request.getAmount(), request.getPaymentMethod(),
                order.getId(), order.getReference(), customer);
        paymentClient.requestOrderPayment(paymentRequest);

        // send to kafka
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.getReference(), request.getAmount(), request.getPaymentMethod(),
                        customer, purchaseResponse
                )
        );
        return order.getId();
    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Long id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided id %d", id)));
    }
}
