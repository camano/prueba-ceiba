package com.ceiba.prueba.domain.notification.gateway;

import java.util.Map;

public interface INotificationPort {
    public void enqueue(Long userId, Long subscriptionId, String channel, Map<String, Object> payload);
}
