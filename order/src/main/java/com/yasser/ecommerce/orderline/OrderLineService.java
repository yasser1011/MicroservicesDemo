package com.yasser.ecommerce.orderline;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Long saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }

    public Integer saveOrderLineList(List<OrderLineRequest> orderLineRequests){
        try{
            var orders = orderLineRequests.stream().map(orderLineMapper::toOrderLine)
                    .collect(Collectors.toList());
            orderLineRepository.saveAll(orders);
            return 0;
        }catch (Exception e){
            return 1;
        }
    }

    public List<OrderLineResponse> findAllByOrderId(Long orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}