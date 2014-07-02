package com.eighth.airrent.proxy.service.impl;

import com.eighth.airrent.domain.OpenPage;
import com.eighth.airrent.domain.UserOrder;
import com.eighth.airrent.proxy.exception.RemoteInvokeException;
import com.eighth.airrent.proxy.service.UserOrderService;

/**
 * Created by dam on 2014/7/2.
 */
public class UserOrderServiceImpl implements UserOrderService {
    @Override
    public OpenPage<UserOrder> findUserOrder(OpenPage openPage, String userId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String payOrder(String userId, String orderId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public UserOrder findOrderById(String orderId) throws RemoteInvokeException {
        return null;
    }

    @Override
    public String deleteOrderById(String orderId) throws RemoteInvokeException {
        return null;
    }
}
